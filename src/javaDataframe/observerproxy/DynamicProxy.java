package javaDataframe.observerproxy;

import java.lang.reflect.*;

public class DynamicProxy implements InvocationHandler {
    private Object target = null;
    private ObserverMonitor monitor;

    public static Object newInstance(Object target, ObserverMonitor monitor){
        Class targetClass = target.getClass();
        Class[] interfaces = targetClass.getInterfaces();

        return Proxy.newProxyInstance(targetClass.getClassLoader(),
                interfaces, new DynamicProxy(target, monitor));
    }

    private DynamicProxy(Object target, ObserverMonitor monitor){
        this.target = target;
        this.monitor = monitor;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) {
        Object invocationResult = null;
        if(args == null){
            args = new Object[1];
            args[0] = method.getName();
        }

        try{
            Object[] newArgs = new Object[1];
            if(args[0].equals(method.getName())){
                args = null;
                newArgs[0] = method.getName();
            }
            else {
                newArgs = new Object[]{method.getName(), args[0], args[1]};
            }
            invocationResult = method.invoke(this.target, args);
            monitor.notifyObservers(newArgs);
        }
        catch(InvocationTargetException ite) {
            throw ite.getTargetException();
        }
        catch (Exception e){
            System.err.println("Invocation of " + method.getName() + " failed.");
        }
        finally {
            return invocationResult;
        }
    }
}
