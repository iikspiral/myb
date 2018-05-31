package com.sxca.myb.common.persistence;

//import org.aspectj.lang.JoinPoint;
//import org.aspectj.lang.annotation.Before;
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//import org.springframework.stereotype.Component;

//@Component
//@Aspect
public class MultipleDataSourceAspectAdvice {

	/*@Around("execution(void com.sxca.ggzyzt.common.config.TestC.t1())")
    public Object dataSourcePrepose(ProceedingJoinPoint jp) throws Throwable {
		System.out.println("==========================================");;
		System.out.println(jp.getTarget().getClass().getPackage());
		System.out.println("==========================================");
		MultipleDataSource.setDataSourceKey("dataSourcePrepose");
        return jp.proceed();
    }*/
	
	/*@Around("execution(* com.sxca.ggzyzt.modules.ztk.dao.regular.*.*(..))")
	public Object dataSourceRegular(ProceedingJoinPoint jp) throws Throwable {
		System.out.println("+++++++++++++++++++++++++++++++++++++++++++");;
		System.out.println(jp.getTarget().getClass().getPackage());
		System.out.println("+++++++++++++++++++++++++++++++++++++++++++");
		MultipleDataSource.setDataSourceKey("dataSourceRegular");
		return jp.proceed();
	}*/
	
	/*@Before("execution(* com.sxca.ggzyzt.modules.ztk.service.*.*(..))")
	public void beforeMethod(JoinPoint joinPoint) {
		System.out.println("======================+++++++++++++++++++");
	}*/
	
	
}
