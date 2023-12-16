package org.fuyi.weather.app.service;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.fuyi.weather.infra.common.api.ResultCode;
import org.fuyi.weather.infra.common.context.AuthContext;
import org.fuyi.weather.infra.common.exception.PermissionDeniedException;
import org.fuyi.weather.infra.util.JwtHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Objects;

/**
 * 登录态校验
 *
 * @author: <a href="mailto:thread.zhou@gmail.com">Fuyi</a>
 * @time: 2022/2/6 上午12:00
 * @since: 1.0
 */
@Aspect
@Component
public class LoginStateCheckAop {

    private static final Logger logger = LoggerFactory.getLogger(LoginStateCheckAop.class.getName());

    @Pointcut("execution(* org.fuyi.weather.app.controller.v1.*.*(..))")
    public void authCheckCut(){}


    @Pointcut("execution(* org.fuyi.weather.app.controller.v1.WeChatOauthController.*(..))")
    public void excludeCut(){}

    @Pointcut("authCheckCut() && !excludeCut()")
    public void allCut(){}

    @Before("allCut()")
    public void beforeExecution(JoinPoint joinPoint){
        String authToken = AuthContext.getAuth();
        logger.info("current auth_token is: " + authToken);
        if (!StringUtils.hasText(authToken)){
            throw new PermissionDeniedException(ResultCode.INVALID_TOKEN, "Can not find token from request header.");
        }
        if (Objects.isNull(JwtHelper.verifyToken(authToken))){
            throw new PermissionDeniedException(ResultCode.INVALID_TOKEN);
        }
        logger.info("Token verification successful.");
    }

}
