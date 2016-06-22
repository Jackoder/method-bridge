package com.jackoder.methodbridge.inner.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jackoder
 * @version 2015/12/30.
 */
public class ReflectionUtils {

    public static List<Class<?>> buildClassHierarchy(Class<?> cls) {
        List<Class<?>> hierarchy = new ArrayList<>();
        do {
            hierarchy.add(0, cls);
            cls = cls.getSuperclass();
        } while (cls != null);
        return hierarchy;
    }
}
