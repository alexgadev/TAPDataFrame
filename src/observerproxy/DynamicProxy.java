package observerproxy;

import java.lang.reflect.*;

//TODO: add cases to log operations inside the invoke

public class DynamicProxy implements InvocationHandler {
    private Object target = null;

    public static Object newInstance(Object target){
        Class targetClass = target.getClass();
        Class[] interfaces = targetClass.getInterfaces();
        return Proxy.newProxyInstance(targetClass.getClassLoader(),
                interfaces, new DynamicProxy(target));
    }

    private DynamicProxy(Object target){
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object invocationResult = null;
        try{
            invocationResult = method.invoke(this.target, args);
            System.out.println("Method: " + method.getName() + "\nargs: " + args[0] + ", " + args[1]);
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
