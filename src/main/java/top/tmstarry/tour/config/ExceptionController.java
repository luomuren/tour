package top.tmstarry.tour.config;

import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @ClassName ExceptionController
 * @Author 落幕人
 * <p>
 * 自定义错误控制器
 * <p>
 * @Date 2019/11/1 19:34
 * @Version 1.0
 **/
@ControllerAdvice
public class ExceptionController {
    @ExceptionHandler(value = UnauthenticatedException.class)
    public String authenticated() {
        return "login";
    }

    @ExceptionHandler(value = UnauthorizedException.class)
    public String authorization() {
        return "error/noPermission";
    }

    @ExceptionHandler(value = Exception.class)
    public String exception() {
        return "error/500";
    }

}
