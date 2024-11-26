package com.project.nameMaker.common.aop;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

@Aspect
@Component
public class RequestUrlAspect {

//    @Around("execution(* *(..))")
//    //@Before("execution(* *(..))")
//    public void setRequestUrl(ProceedingJoinPoint joinPoint, Model model, HttpServletRequest request) throws Throwable {
//        controller2.home(model, request);
//        joinPoint.proceed();
//    }

//    @Before("execution(* com.project.nameMaker..*.*(..)) && args(model, request, ..)")
//    public void addCurrentUrlToModel(Model model, HttpServletRequest request) {
//        // 요청 URL을 currentUrl로 Model에 추가
//        model.addAttribute("currentUrl", request.getRequestURI());
//    }
}
