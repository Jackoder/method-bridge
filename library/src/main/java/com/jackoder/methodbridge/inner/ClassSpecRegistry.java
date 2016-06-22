package com.jackoder.methodbridge.inner;

import com.jackoder.methodbridge.ann.ExportMethod;
import com.jackoder.methodbridge.inner.base.MethodSpec;
import com.jackoder.methodbridge.inner.utils.ReflectionUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Jackoder
 * @version 2015/12/30.
 */
public class ClassSpecRegistry {

    private static final ConcurrentHashMap<Class<?>, MethodSpec<ExportMethodAnn>[]> EXPORT_METHOD_SPECS = new ConcurrentHashMap<>();

    public static MethodSpec<ExportMethodAnn>[] getExportMethodSpecs(Class<?> cls) {
        MethodSpec<ExportMethodAnn>[] specs = EXPORT_METHOD_SPECS.get(cls);
        if (specs == null) {
            ArrayList<MethodSpec<ExportMethodAnn>> list = new ArrayList<>();
            List<Class<?>> clzHierarchy = ReflectionUtils.buildClassHierarchy(cls);
            for (Class<?> cl : clzHierarchy) {
                for (Method method : cl.getDeclaredMethods()) {
                    ExportMethodAnn ann = getExportMethodAnn(method);
                    if (ann != null) {
                        list.add(new MethodSpec<ExportMethodAnn>(method, ann));
                    }
                }
            }
            specs = list.toArray(new MethodSpec[list.size()]);
            EXPORT_METHOD_SPECS.put(cls, specs);
        }
        return specs;
    }

    static ExportMethodAnn getExportMethodAnn(Method m) {
        for (Annotation a : m.getDeclaredAnnotations()) {
            Class<?> at = a.annotationType();
            if (ExportMethod.class == at) {
                return new ExportMethodAnn((ExportMethod) a);
            }
        }
        return null;
    }
}
