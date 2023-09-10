package com.healthcode.healthcodeserver.aspect;

import com.healthcode.healthcodeserver.common.Result;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 处理Log的切面
 * 包含两个切点：CallInfo，ReportError
 * 前者用于打印调用方法的名称、参数
 * 后者在返回Result对象时，若遇到isError的Result，自动打印Error日志
 */
@Aspect
@Component
public class LogAspect {
    private static final Logger logger = LoggerFactory.getLogger(LogAspect.class);

    @Pointcut("@annotation(com.healthcode.healthcodeserver.aspect.pointCutAnnotation.CallInfoLog) " +
            "|| @annotation(com.healthcode.healthcodeserver.aspect.pointCutAnnotation.FullLog)")
    public void CallInfoLogPointCut() {}

    @Pointcut("@annotation(com.healthcode.healthcodeserver.aspect.pointCutAnnotation.ReportErrorLog)" +
            "|| @annotation(com.healthcode.healthcodeserver.aspect.pointCutAnnotation.FullLog)")
    public void ReportErrorLogPointCut() {}

    @Around("CallInfoLogPointCut()")
    public Object LogMethodCallInfo(ProceedingJoinPoint point) throws Throwable {
        var signature = (MethodSignature) point.getSignature();
        var args = point.getArgs();
        var parameterNames = signature.getParameterNames();
        StringBuilder builder = new StringBuilder();
        builder.append("Calling method ").append(signature.getName()).append(" with args: ");
        for (int i = 0; i < args.length; i++) {
            builder.append(parameterNames[i]).append("(").append(args[i]).append(")");
            if (i < args.length - 1) {
                builder.append(", ");
            }
        }
        logger.info(builder.toString());
        return point.proceed();
    }

    @Around("ReportErrorLogPointCut()")
    public Object LogMethodReportError(ProceedingJoinPoint point) throws Throwable {
        Result result = (Result) point.proceed();
        if (result.isError()) {
            logger.error(result.getMessage());
        }
        return result;
    }
}
