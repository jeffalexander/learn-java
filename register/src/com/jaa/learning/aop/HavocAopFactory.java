package com.jaa.learning.aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class HavocAopFactory {

	public static <T> Object instanceOf(Class<T> clazz) {
		// Object newInstance = Class.forName(clazz.getName()).newInstance();
		return proxy(clazz);
	}

	private static <T> Object proxy(Class<T> clazz) {
		ClassLoader loader = clazz.getClassLoader();
		Class<?>[] interfaces = clazz.getInterfaces();
		T newInstance = null;
		
		try {
			newInstance = clazz.newInstance();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		InvocationHandler handler = new HavocInvocationHandler(newInstance);
		Object newProxyInstance = Proxy.newProxyInstance(loader, interfaces, handler);
		return newProxyInstance;
	}
}
