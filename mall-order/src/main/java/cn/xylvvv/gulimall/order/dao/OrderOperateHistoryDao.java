package cn.xylvvv.gulimall.order.dao;

import cn.xylvvv.gulimall.order.entity.OrderOperateHistoryEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 订单操作历史记录
 * 
 * @author xylvvv
 * @email lxy2011w@gmail.com
 * @date 2022-01-23 20:48:55
 */
@Mapper
public interface OrderOperateHistoryDao extends BaseMapper<OrderOperateHistoryEntity> {
	
}
