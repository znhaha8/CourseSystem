package main.com.WCZZ.util.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * 先决条件不满足，行不通
 *
 */
@Component
@Aspect
public class IsLogin {
    @Before("@annotation(main.com.WCZZ.util.annotation.UserAccess)")
    public void isLogin(){

    }
}
