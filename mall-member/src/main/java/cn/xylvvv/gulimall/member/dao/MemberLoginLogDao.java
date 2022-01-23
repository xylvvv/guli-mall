package cn.xylvvv.gulimall.member.dao;

import cn.xylvvv.gulimall.member.entity.MemberLoginLogEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 会员登录记录
 * 
 * @author xylvvv
 * @email lxy2011w@gmail.com
 * @date 2022-01-23 20:41:02
 */
@Mapper
public interface MemberLoginLogDao extends BaseMapper<MemberLoginLogEntity> {
	
}
