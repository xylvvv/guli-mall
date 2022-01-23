package cn.xylvvv.gulimall.member.dao;

import cn.xylvvv.gulimall.member.entity.MemberEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 会员
 * 
 * @author xylvvv
 * @email lxy2011w@gmail.com
 * @date 2022-01-23 20:41:02
 */
@Mapper
public interface MemberDao extends BaseMapper<MemberEntity> {
	
}
