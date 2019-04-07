package com.xintu.sso.web.aop;

import com.xintu.common.utils.JacksonMapper;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
public class WebLogAspect {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Pointcut("execution(public * com.xintu.sso.web.controller.*.*(..))")
	public void webLog() {
	}

	@Before("webLog()")
	public void doBefore(JoinPoint joinPoint) throws Exception {
		// 接收到请求，记录请求内容
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = attributes.getRequest();

		// 记录下请求内容
		logger.info("Address : " + request.getRequestURL().toString());
		logger.info("Http-Method : " + request.getMethod());
		logger.info("IP : " + request.getRemoteAddr());
		if(joinPoint.getArgs().length>0){
			try {
				logger.info("Payload : " + JacksonMapper.toJson(joinPoint.getArgs()[0]) );
			} catch (Exception e) {
				//不处理
				logger.info("Payload : " + joinPoint.getArgs()[0].toString() );
			}finally {
				logger.info("------------------------------------------");
			}
		}


	}

	@AfterReturning(returning = "ret", pointcut = "webLog()")
	public void doAfterReturning(Object ret) throws Exception {
		// 处理完请求，返回内容
		try {
			logger.info("------------------------------------------");
			logger.info("RESPONSE : " + JacksonMapper.toJson(ret));
		} catch (Exception e) {
			// TODO: 不处理
			logger.error("异常拦截错误", e);
		}

	}

}