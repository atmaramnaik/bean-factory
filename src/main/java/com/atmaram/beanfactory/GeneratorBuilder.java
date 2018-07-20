package com.atmaram.beanfactory;

import com.atmaram.beanfactory.generators.Generator;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class GeneratorBuilder {
    public Generator getGenerator() {
        return generator;
    }

    public void setGenerator(Generator generator) {
        this.generator = generator;
    }

    private Generator generator;

    public Class<?> gettClass() {
        return tClass;
    }

    public void settClass(Class<?> tClass) {
        this.tClass = tClass;
    }

    private Class<?> tClass;

    private Method method;

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public GeneratorBuilder(Generator generator) {
        this.generator = generator;
    }
    public <T> T on(Class<T> tClass){
        this.tClass=tClass;
        Enhancer enhancer=new Enhancer();
        enhancer.setSuperclass(tClass);
        enhancer.setCallback(new GeneratorMethodInterceptor(this));
        T obj= (T)enhancer.create();
        return obj;
    }
    private static class GeneratorMethodInterceptor implements MethodInterceptor{
        private GeneratorBuilder generatorBuilder;

        public GeneratorMethodInterceptor(GeneratorBuilder generatorBuilder) {
            this.generatorBuilder = generatorBuilder;
        }

        @Override
        public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
            if(method.getName().startsWith("get")){
                generatorBuilder.method=method.getDeclaringClass().getMethod("set"+method.getName().substring(3),method.getReturnType());
                return proxy.invokeSuper(obj,args);
            } else if(method.getName().startsWith("is")){
                generatorBuilder.method=method.getDeclaringClass().getMethod("set"+method.getName().substring(2),method.getReturnType());
                return proxy.invokeSuper(obj,args);
            }
            return proxy.invokeSuper(obj,args);
        }
    }
}
