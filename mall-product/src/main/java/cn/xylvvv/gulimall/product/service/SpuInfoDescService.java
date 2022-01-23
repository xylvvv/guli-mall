package cn.xylvvv.gulimall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import cn.xylvvv.common.utils.PageUtils;
import cn.xylvvv.gulimall.product.entity.SpuInfoDescEntity;

import java.util.Map;

/**
 * spu信息介绍
 *
 * @author xylvvv
 * @email lxy2011w@gmail.com
 * @date 2022-01-23 18:47:43
 */
public interface SpuInfoDescService extends IService<SpuInfoDescEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

