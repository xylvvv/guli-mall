package cn.xylvvv.gulimall.product.service;

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
}

