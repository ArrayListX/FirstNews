package com.mynews.common.aspect;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Date;

import javax.servlet.http.Cookie;

import org.apache.shiro.SecurityUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mynews.common.annotation.RequiredLog;
import com.mynews.common.util.IPUtils;
import com.mynews.entity.NewsLog;
import com.mynews.entity.NewsUser;
import com.mynews.service.NewsLogService;

import lombok.extern.slf4j.Slf4j;

/**
 * @Aspect描述的类为切面类，此类中实现
 * 1）切入点（Pointcut)的定义
 * 2）通知（advice）的定义（扩展功能）
 * @author TEDU
 */
@Slf4j
@Aspect
@Component
public class NewsLogAspect {
	/**
	 * @Around注解描述的方法为一个环境通知方法
	 * 方法中可以添加扩展业务逻辑，可以调用下一个切面对象
	 * 
	 * @Pointcut注解用于描述或定义一个切入点
	 * 切入点的定义需要遵循spring中指定的表达式规范
	 * 例如：（“bean(SysMenuServiceImpl)”）为切入点表达式的一种定义方式
	 * 
	 * ProceedingJoinPoint此连接点只应用于@Around注解
	 */
	//bean(bean名称或一个表达式)
	//@Pointcut("bean(SysMenuServiceImpl)")//类中所有的集合为切入点，某一个方法为连接点
	@Pointcut("@annotation(com.mynews.common.annotation.RequiredLog)")
	public void logPointCut() {}
	
	@Around("logPointCut()")//执行切入点包含的方法时会执行这个方法，这个小括号必须写
	public Object aroundAdvice(ProceedingJoinPoint pj) throws Throwable{
		long start=System.currentTimeMillis();
		log.info("start:"+start);
		Object result=pj.proceed();//调用下一个切面或者目标方法，目标之前，目标之后都可以执行就叫环绕around
		long end=System.currentTimeMillis();
		log.info("end:"+end);
		saveLog(pj,end-start);
		return result;
	}
	
	@Autowired
	private NewsLogService newsLogService;
	//保存日志
	private void saveLog(ProceedingJoinPoint pj, long start) throws Exception {
		//1.获取用户行为日志
		//1.1获取方法所在的类及方法名
		//①获取方法签名（通过此签名获取目标方法相关信息）：
		 MethodSignature ms=(MethodSignature) pj.getSignature();//拿到方法的相关信息
		//②获取目标对象的字节码对象
		Class<?> targetCls = pj.getTarget().getClass();//获取类的字节码对象
		//③获取目标类中的方法(方法名，参数类型)
		Method targetMethod=targetCls.getDeclaredMethod(ms.getMethod().getName(), ms.getMethod().getParameterTypes());
		System.out.println("targetMethod"+targetMethod);
		//获取目标方法上的注解
		RequiredLog requiredLog=targetMethod.getAnnotation(RequiredLog.class);
		//得到注解中的值
		String operation=requiredLog.value();
		System.out.println(operation);
		//获取目标方法名
		String targetClsName=targetCls.getName();//类名
		String targetObjectMethodName=targetClsName+"."+ms.getName();//类名和方法名
		//获取目标方法执行时的实际参数
		String targetMethodParam=Arrays.toString(pj.getArgs());
		//2、封装用户行为日志(SysLog)
		NewsLog entity=new NewsLog();
		entity.setIp(IPUtils.getIpAddr());//IP地址
		entity.setUsername("xuzhe");//用户名:登录用户
		entity.setOperation(operation);
		entity.setMethod(targetObjectMethodName);//类全名+方法名
		entity.setParams(targetMethodParam);//执行方法时传递的实际参数
		entity.setTimebig(start);//时长
		entity.setCreatedTimedate(new Date());
		//3、调用业务层对象将日志写入到数据库	
		newsLogService.saveObject(entity);
		//将日志写的操作保存在线程中，性能比较好
	}
}


































