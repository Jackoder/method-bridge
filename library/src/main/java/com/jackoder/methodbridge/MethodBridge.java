package com.jackoder.methodbridge;

import android.util.Log;

import com.jackoder.methodbridge.inner.ClassSpecRegistry;
import com.jackoder.methodbridge.inner.ExportMethodAnn;
import com.jackoder.methodbridge.inner.base.MethodSpec;

import java.lang.ref.WeakReference;
import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Jackoder
 * @version 2015/12/30.
 */
public class MethodBridge {

    private static final String TAG = "MethodBridge";

    private static final ConcurrentHashMap<String, MethodDelegate<Object>> exportNameToMethod = new ConcurrentHashMap<>();

    public static void registerAnnotatedClass(Object obj) {
        MethodSpec[] specs = ClassSpecRegistry.getExportMethodSpecs(obj.getClass());
        for (MethodSpec<ExportMethodAnn> spec : specs) {
            registerExportMethod(new ReflectiveMethod(obj, spec), spec.ann.names);
        }
    }

    public static void registerExportMethod(MethodDelegate<?> methodDelegate, String... names) {
        if (names.length  == 0) {
            return;
        }
        MethodDelegate<Object> exportMethodDelegate = (MethodDelegate<Object>) methodDelegate;
        for (String name : names) {
            if (name != null && name.length() != 0) {
                exportNameToMethod.put(name, exportMethodDelegate);
            }
        }
    }

    public static void unregisterAnnotatedClass(Object obj) {
        MethodSpec<ExportMethodAnn>[] specs = ClassSpecRegistry.getExportMethodSpecs(obj.getClass());
        for (MethodSpec<ExportMethodAnn> spec : specs) {
            for (String name : spec.ann.names) {
                if (exportNameToMethod.containsKey(name)) {
                    MethodDelegate<Object> exportMethodDelegate = exportNameToMethod.get(name);
                    if (exportMethodDelegate instanceof ReflectiveMethod) {
                        if (((ReflectiveMethod) exportMethodDelegate).objectRef.get() != null &&
                                ((ReflectiveMethod) exportMethodDelegate).objectRef.get() == obj &&
                                ((ReflectiveMethod)exportMethodDelegate).spec == spec) {
                            exportNameToMethod.remove(name);
                        }
                    }
                }
            }
        }
    }

    public static void unregisterExportMethod(MethodDelegate<?> methodDelegate) {
        for (String name : exportNameToMethod.keySet()) {
            if (exportNameToMethod.get(name) == methodDelegate) {
                exportNameToMethod.remove(name);
                break;
            }
        }
    }

    /**
     * 建议在调用前判断是否有注册方法，否则当遇到基础类型返回null的情况，会导致装箱操作报错。
     * 或者改用 {@link #callSafe(String, Object, Object...)} 指定返回默认值。
     */
    public static <T> T call(String name, Object... data) {
        return callSafe(name, null, data);
    }

    public static <T> T callSafe(String name, T defaultValue, Object... data) {
        T result = defaultValue;
        if (contains(name)) {
            try {
                result = callEx(name, data);
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public static <T> T callEx(String name, Object... data) throws Throwable {
        if (contains(name)) {
            try {
                return (T) exportNameToMethod.get(name).onCall(name, data);
            } catch (InvocationTargetException e) {
                Log.e(TAG, String.format("method for name ‘%s’ throws exception, %s", name, e.getMessage()));
                throw e.getTargetException();
            } catch (Exception e) {
                Log.e(TAG, String.format("method for name ‘%s’ occurs exception, %s", name, e.getMessage()));
                throw e;
            }
        } else {
            Log.e(TAG, String.format("method for name ‘%s’ is not found", name));
        }
        return null;
    }

    public static boolean contains(String name) {
        return exportNameToMethod.containsKey(name);
    }

    private static class ReflectiveMethod implements MethodDelegate<Object> {

        final WeakReference<Object>       objectRef;
        final MethodSpec<ExportMethodAnn> spec;

        public ReflectiveMethod(Object object, MethodSpec<ExportMethodAnn> spec) {
            this.objectRef = new WeakReference<>(object);
            this.spec = spec;
        }

        @Override
        public Object onCall(String name, Object... data) throws Exception {
            Log.d(TAG, "call method by name " + name + " with arguments " + data.toString());
            Object obj = objectRef.get();
            if (spec.paramTypes.length == 0) {
                return spec.method.invoke(obj);
            } else {
                return spec.method.invoke(obj, data);
            }
        }

    }
}
