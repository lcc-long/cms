package com.lcc.basic.spring;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.junit.Test;

import java.lang.reflect.Method;

public class CglibTest {
    /**
     * CGLib采用了非常底层的字节码技术，
     * 其原理是通过字节码技术为一个类创建子类，
     * 并在子类中采用方法拦截的技术拦截所有父类方法的调用
     */
    @Test
    public void cglibTest() {
        final UserDaoImp userDaoImp = new UserDaoImp();
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(userDaoImp.getClass());
        enhancer.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
                System.out.println("执行前");
                Object obj = method.invoke(userDaoImp, args);
//              或者  Object obj = methodProxy.invokeSuper(o, args); //执行代理类（子类）的父类方法（父类就是目标类）
                System.out.println("执行后");
                return obj;
            }
        });
        UserDaoImp proxyObj = (UserDaoImp) enhancer.create();
        proxyObj.add();
    }


}

class UserDaoImp {
    public void add() {
        System.out.println("invoke add()");
    }
}
