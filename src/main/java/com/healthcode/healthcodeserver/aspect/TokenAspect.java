package com.healthcode.healthcodeserver.aspect;

import com.healthcode.healthcodeserver.common.Result;
import com.healthcode.healthcodeserver.util.TokenUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TokenAspect {
    private static final Logger logger = LoggerFactory.getLogger(TokenAspect.class);

    TokenUtil tokenUtil;

    public TokenAspect(TokenUtil tokenUtil) {
        this.tokenUtil = tokenUtil;
    }

    /**
     * 当第一个参数是Token时，适用于这个PointCut
     * 能够检测该Token是否为登录状态
     */
    @Pointcut("@annotation(com.healthcode.healthcodeserver.aspect.pointCutAnnotation.CheckAdminToken)")
    public void checkAdminToken() {
    }

    @Around("checkAdminToken()")
    public Object checkIfLogIn(ProceedingJoinPoint point) throws Throwable {
        var args = point.getArgs();
        var token = (String) args[0]; // 假定token必定为第一个参数
        if (tokenUtil.verify(token)) {
            return point.proceed();
        } else {
            return new Result()
                    .error(2);
        }
    }
}


