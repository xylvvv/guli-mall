package cn.xylvvv.auth.feign;

import cn.xylvvv.auth.vo.UserLoginVo;
import cn.xylvvv.auth.vo.UserRegisterVo;
import cn.xylvvv.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("mall-member")
public interface MemberFeignService {
    @PostMapping(value = "/member/member/register")
    R register(@RequestBody UserRegisterVo vo);

    @PostMapping(value = "/member/member/login")
    R login(@RequestBody UserLoginVo vo);
}
