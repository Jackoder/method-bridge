package com.jackoder.methodbridge.inner.base;

import java.lang.reflect.Method;

/**
 * @author Jackoder
 * @version 2015/12/30.
 */
public class MethodSpec<AnnType extends Ann<?>> {

    public final Method     method;
    public final Class<?>[] paramTypes;

    public final AnnType ann;

    public MethodSpec(Method method, AnnType ann) {
        this.method = method;
        paramTypes = method.getParameterTypes();
        this.ann = ann;
        method.setAccessible(true);
    }
}
