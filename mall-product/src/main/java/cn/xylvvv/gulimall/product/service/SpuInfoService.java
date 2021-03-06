package cn.xylvvv.gulimall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import cn.xylvvv.common.utils.PageUtils;
import cn.xylvvv.gulimall.product.entity.SpuInfoEntity;

import java.util.Map;

/**
 * spu信息
 *
 * @author xylvvv
 * @email lxy2011w@gmail.com
 * @date 2022-01-23 18:47:43
 */
public interface SpuInfoService extends IService<SpuInfoEntity> {

    PageUtils queryPage(Map<String, Object> params);

    /**
     * 商品上架
     * @param spuId
     */
    void up(Long spuId);
}

