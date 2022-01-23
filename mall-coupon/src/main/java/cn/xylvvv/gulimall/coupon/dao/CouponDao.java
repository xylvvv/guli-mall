package cn.xylvvv.gulimall.coupon.dao;

import cn.xylvvv.gulimall.coupon.entity.CouponEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 优惠券信息
 * 
 * @author xylvvv
 * @email lxy2011w@gmail.com
 * @date 2022-01-23 20:34:10
 */
@Mapper
public interface CouponDao extends BaseMapper<CouponEntity> {
	
}
