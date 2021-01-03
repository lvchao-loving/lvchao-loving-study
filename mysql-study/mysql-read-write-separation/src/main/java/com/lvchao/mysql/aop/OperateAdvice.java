package com.lvchao.mysql.aop;

import com.lvchao.mysql.entity.OperationLog;
import com.lvchao.mysql.service.OperationLogService;
import com.lvchao.mysql.utils.ArrayUtils;
import com.lvchao.mysql.utils.DataUtils;
import com.lvchao.mysql.utils.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;

@Component
@Aspect
@Slf4j
public class OperateAdvice {
	
	@Autowired
	private OperationLogService operationLogService;

	private static DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	// 注意不能捕获异常通知
	@Around("execution(* com.lvchao.mysql.controller.*.*(..)) && @annotation(operateLog)")
	public Object insertLogAround(ProceedingJoinPoint pjp , OperateLog operateLog) throws Throwable{
		OperationLog op = new OperationLog();

		op.setOperateTime(sdf.format(new Date()));
		op.setOperateUser(DataUtils.getRandStr(8));
		op.setOperateClass(pjp.getTarget().getClass().getName());
		op.setOperateMethod(pjp.getSignature().getName());
		
		Object[] args = pjp.getArgs();

		if (ArrayUtils.isNotEmpty(args)){
			op.setParamAndValue(JsonUtils.toString(args));
		}else{
			op.setParamAndValue("void");
		}


		long start_time = System.currentTimeMillis();

		//放行
		Object object = pjp.proceed();

		long end_time = System.currentTimeMillis();
		op.setCostTime(end_time - start_time);

		if(object != null){
			op.setReturnClass(object.getClass().getName());
			op.setReturnValue(JsonUtils.toString(object));
		}else{
			op.setReturnClass("java.lang.Object");
			op.setReturnValue("void");
		}

		operationLogService.save(op);
		return object;
	}

	// 当controller出现异常时执行这段代码
	@AfterThrowing(value = "execution(* com.lvchao.mysql.controller.*.*(..))",throwing = "ex")
	public void afterThrowing(JoinPoint joinPoint, Exception ex) {
		// TODO
		// 根据实际的业务进行开发，异常通知
		System.out.println( "请求出现异常"+ex.getMessage());
	}

}
