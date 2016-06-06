package com.lab.thread;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.util.concurrent.TimeUnit;

public class ConnectionDriver {
	 static class ConnectionHandler implements InvocationHandler{
			public Object invoke(Object proxy, Method method, Object[] args)
					throws Throwable {
				if(method.getName().equals("commit")){
					TimeUnit.MILLISECONDS.sleep(100);
				}
				return "Obj";
			}
	 }
	 
	 public static final Connection createConnection(){
		 Object conn= Proxy.newProxyInstance(ConnectionDriver.class.getClassLoader(),
				 new Class<?>[]{Connection.class},new ConnectionHandler());
		 return (Connection)conn;
	 }


}
