package cn.xylvvv.gulimall.product.service;

import cn.xylvvv.gulimall.product.vo.Catalogs2Vo;
import com.baomidou.mybatisplus.extension.service.IService;
import cn.xylvvv.common.utils.PageUtils;
import cn.xylvvv.gulimall.product.entity.CategoryEntity;

import java.util.List;
import java.util.Map;

/**
 * 商品三级分类
 *
 * @author xylvvv
 * @email lxy2011w@gmail.com
 * @date 2022-01-23 18:47:43
 */
public interface CategoryService extends IService<CategoryEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<CategoryEntity> listWithTree();

    void removeMenuByIds(List<Long> asList);

    /**
     * 找到catelogId的完整路径
     * [父/子/孙]
     * @param catelogId
     * @return
     */
    Long[] findCatelogPath(Long catelogId);

    void updateCascade(CategoryEntity category);

    /**
     * 查找一级分类，首页显示
     *
     * @return
     */
    List<CategoryEntity> getLevel1Categories();

    /**
     * 查找二级、三级分类，首页显示
     * @return
     */
    Map<String, List<Catalogs2Vo>> getCatalogJson();
}

