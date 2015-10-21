package com.jaa.learning.aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class HavocInvocationHandler implements InvocationHandler {

    private Object obj;

    public HavocInvocationHandler(Object obj) {
        this.obj = obj;
    }
	
	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		Method realMethod = obj.getClass().getMethod(method.getName(), method.getParameterTypes());
		if(realMethod.isAnnotationPresent(Havoc.class)) {
			Havoc annotation = realMethod.getAnnotation(Havoc.class);
			Severity severity = annotation.severity();
			
			switch (severity) {
				case HIGH:
					messWithThings(args);
				case MEDIUM:
					messWithThings(args);
				case LOW:
					messWithThings(args);
			}
		}
		Object result = null;
		try {
			result = method.invoke(obj, args);
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return result;
	}

	private void messWithThings(Object[] args) {
		Integer arg0 = (Integer) args[0];
		args[0] = arg0 + 1;
	}
}
