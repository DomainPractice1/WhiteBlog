package com.whiteblog.aspectJ;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;

/**
 * 切面类 （Advice 通知集合类）
 * 
 * @author tiantian
 * 
 */
public class MyAspect {
	// 日志记录器
	private Logger logger = Logger.getLogger(this.getClass());

	public void before(JoinPoint joinPoint){//throws Exception {
		logger.info("类"+joinPoint.getTarget().getClass().getSimpleName()+"---方法"+joinPoint.toShortString()+"执行---");		
	}	
	// 得到方法返回值
	// returnVal 必须要和 xml配置变量名称一致
	public void after(JoinPoint joinPoint, Object returnVal) {
		logger.info("类"+joinPoint.getTarget().getClass().getSimpleName()+"---方法"+joinPoint.toShortString()+"执行完毕。返回值：" + returnVal+"---");
	}

	// 环绕通知，在目标方法前后执行，拦截目标方法执行，返回目标方法返回值，抛出目标方法异常
	public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		long start = System.currentTimeMillis();
		Object result = proceedingJoinPoint.proceed();
		long end = System.currentTimeMillis();
		logger.info("类"+proceedingJoinPoint.getTarget().getClass().getSimpleName()+"---方法"+proceedingJoinPoint.toShortString()+"方法运行时间:" + (end - start)+"毫秒---");
		return result;
	}

	// 异常通知，获得方法出现异常
	public void afterThrowing(JoinPoint joinPoint, Throwable ex) {
		// 使用log4j记录日志
		logger.error("类"+joinPoint.getTarget().getClass().getSimpleName()+"---方法"+joinPoint.toShortString()+ "方法发送异常：" + ex.getMessage(), ex);
	}

	// 最终通知 --- 释放资源
	public void finalMethod(JoinPoint joinPoint) {
		logger.info("类"+joinPoint.getTarget().getClass().getSimpleName()+"---方法"+joinPoint.toShortString()+ "---方法最终通知---");
	}
}
