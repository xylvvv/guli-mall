package cn.xylvvv.gulimall.product.service.impl;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.xylvvv.common.utils.PageUtils;
import cn.xylvvv.common.utils.Query;

import cn.xylvvv.gulimall.product.dao.CategoryDao;
import cn.xylvvv.gulimall.product.entity.CategoryEntity;
import cn.xylvvv.gulimall.product.service.CategoryService;


@Service("categoryService")
public class CategoryServiceImpl extends ServiceImpl<CategoryDao, CategoryEntity> implements CategoryService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CategoryEntity> page = this.page(
                new Query<CategoryEntity>().getPage(params),
                new QueryWrapper<CategoryEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public List<CategoryEntity> listWithTree() {
        // 1、查出所有分类
        List<CategoryEntity> entities = baseMapper.selectList(null);

        // 2、组装成父子树形结构
        // 2.1 找到所有的一级分类
        List<CategoryEntity> level1Menus = entities
                .stream()
                .filter(categoryEntity -> categoryEntity.getParentCid() == 0)
                .map(menu -> {
                    menu.setChildren(getChildren(menu, entities));
                    return menu;
                })
                .sorted((menu1, menu2) -> menu1.getSort() - menu2.getSort())
                .collect(Collectors.toList());
        return level1Menus;
    }

    // 递归查找所有菜单的子菜单
    private List<CategoryEntity> getChildren(CategoryEntity root, List<CategoryEntity> all) {
        List<CategoryEntity> children = all
                .stream()
                .filter(categoryEntity -> categoryEntity.getParentCid() == root.getCatId())
                .map(menu -> {
                    menu.setChildren(getChildren(menu, all));
                    return menu;
                })
                .sorted((menu1, menu2) -> menu1.getSort() - menu2.getSort())
                .collect(Collectors.toList());
        return children;
    }

}