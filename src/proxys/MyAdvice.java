package proxys;

import java.lang.reflect.Method;

public interface MyAdvice {
public void beforeMethod(Method method);
public void afterMethod(Method method);
}
