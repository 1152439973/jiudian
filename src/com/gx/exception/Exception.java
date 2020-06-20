package com.gx.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

@Configuration
public class Exception implements HandlerExceptionResolver {

	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			java.lang.Exception ex) {
		
		ModelAndView mv = new ModelAndView();
		//运算异常
		if(ex instanceof ArithmeticException){
			mv.setViewName("/exception/error");
		}
		
		//空指针
		if(ex instanceof NullPointerException){
			mv.setViewName("/exception/error1");
		}
		mv.addObject("error", ex.toString());
		System.out.println("出现的异常为"+ex.toString());
		return mv;
	}
}
