package com.naedam.admin.common.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import lombok.extern.slf4j.Slf4j;


@Slf4j
public class LogInterceptor extends HandlerInterceptorAdapter {
	
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		log.debug("============ {preHandle} ===============", request.getRequestURI());
		
		return super.preHandle(request, response, handler);
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
		
		super.postHandle(request, response, handler, modelAndView);
		log.debug("-------------------------------");
		log.debug("modelAndView = {postHandle}", modelAndView);
		log.debug("-------------------------------");
		
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		log.debug("_____________afterCompletion view ____________");
		super.afterCompletion(request, response, handler, ex);
		log.debug("=========== end ===============");
	}
	
	
}