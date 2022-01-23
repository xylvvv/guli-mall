package cn.xylvvv.gulimall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import cn.xylvvv.common.utils.PageUtils;
import cn.xylvvv.gulimall.product.entity.AttrEntity;

import java.util.Map;

/**
 * 商品属性
 *
 * @author xylvvv
 * @email lxy2011w@gmail.com
 * @date 2022-01-23 18:47:43
 */
public interface AttrService extends IService<AttrEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

