package cn.xylvvv.gulimall.order.dao;

import cn.xylvvv.gulimall.order.entity.OrderEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 订单
 * 
 * @author xylvvv
 * @email lxy2011w@gmail.com
 * @date 2022-01-23 20:48:55
 */
@Mapper
public interface OrderDao extends BaseMapper<OrderEntity> {
	
}
