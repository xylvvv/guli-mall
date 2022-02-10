package cn.xylvvv.gulimall.product.exception;

import cn.xylvvv.common.exception.BizCodeEnum;
import cn.xylvvv.common.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;

/**
 * 集中处理所有异常
 */
@Slf4j
//@ResponseBody
//@ControllerAdvice(basePackages = "cn.xylvvv.gulimall.product.controller")
@RestControllerAdvice(basePackages = "cn.xylvvv.gulimall.product.controller")
public class MallExceptionControllerAdvice {

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public R handleValidException(MethodArgumentNotValidException e) {
        log.error("数据校验出现问题：{}，异常类型：{}", e.getMessage(), e.getClass());

        BindingResult result = e.getBindingResult();
        HashMap<String, String> map = new HashMap<>();
        // 获取校验的错误结果
        result.getFieldErrors().forEach((item) -> {
            // FieldError获取错误提示
            String message = item.getDefaultMessage();
            // 获取错误的属性名称
            String field = item.getField();
            map.put(field, message);
        });
        return R.error(BizCodeEnum.VALID_EXCEPTION.getCode(), BizCodeEnum.VALID_EXCEPTION.getMsg()).put("data", map);
    }

    /**
     * 处理其它任意类型的异常
     * @return
     */
    @ExceptionHandler(value = Throwable.class)
    public R handleException(Throwable throwable) {
        log.error("错误：",throwable);
        return R.error(BizCodeEnum.UNKNOWN_EXCEPTION.getCode(), BizCodeEnum.UNKNOWN_EXCEPTION.getMsg());
    }
}
