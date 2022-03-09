package cn.xylvvv.gulimall.member.service;

import cn.xylvvv.gulimall.member.exception.PhoneException;
import cn.xylvvv.gulimall.member.exception.UsernameException;
import cn.xylvvv.gulimall.member.vo.MemberUserLoginVo;
import cn.xylvvv.gulimall.member.vo.MemberUserRegisterVo;
import com.baomidou.mybatisplus.extension.service.IService;
import cn.xylvvv.common.utils.PageUtils;
import cn.xylvvv.gulimall.member.entity.MemberEntity;

import java.util.Map;

/**
 * 会员
 *
 * @author xylvvv
 * @email lxy2011w@gmail.com
 * @date 2022-01-23 20:41:02
 */
public interface MemberService extends IService<MemberEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void register(MemberUserRegisterVo vo);

    /**
     * 判断邮箱是否重复
     * @param phone
     * @return
     */
    void checkPhoneUnique(String phone) throws PhoneException;

    /**
     * 判断用户名是否重复
     * @param userName
     * @return
     */
    void checkUserNameUnique(String userName) throws UsernameException;

    /**
     * 用户登录
     * @param vo
     * @return
     */
    MemberEntity login(MemberUserLoginVo vo);

}

