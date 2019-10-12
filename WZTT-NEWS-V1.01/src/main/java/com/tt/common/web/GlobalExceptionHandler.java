package com.tt.common.web;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tt.common.vo.JsonResult;

/**
 * 
 * @ControllerAdvice 全局异常处理类
 *
 */
@ControllerAdvice
public class GlobalExceptionHandler {
	@ResponseBody
	@ExceptionHandler//描述一个异常处理方法
	public JsonResult doHandleRuntime(RuntimeException e)
	{
		e.printStackTrace();
		return new JsonResult(e);
	}
}
