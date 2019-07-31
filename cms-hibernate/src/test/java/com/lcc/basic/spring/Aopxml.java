package com.lcc.basic.spring;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class Aopxml {




}
class MySpect implements MethodBeforeAdvice {

    @Override
    public void before(Method method, Object[] objects, Object o) throws Throwable {

    }
}
class UserServiceImpl{
    public void add() {
        System.out.println("spring add");
    }
}
