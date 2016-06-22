package com.jackoder.methodbridge;

import android.util.Log;

import com.jackoder.methodbridge.ann.ExportMethod;
import com.jackoder.methodbridge.base.BaseTestCase;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Jackoder
 * @version 2015/12/30.
 */
public class BasicTestCase extends BaseTestCase {

    private static final String TAG = "TestCaseOne";

    @Test
    public void testEmptyMethod() {
        MethodBridge.call("emptyMethod");
    }

    @Test
    public void testReturnIntMethod() {
        Assert.assertEquals(1, MethodBridge.call("returnIntMethod"));
    }

    @Test
    public void testReturnObjectMethod() {
        Object result = MethodBridge.call("returnObjectMethod");
        Assert.assertNotNull(result);
        Assert.assertTrue(result instanceof Data);
    }

    /**
     * 如果TestCaseTwo执行，这里报错
     */
    @Test
    public void testStaticMethod() {
        Assert.assertEquals(1, MethodBridge.call("staticMethod"));
    }

    @Test
    public void testNoMethod() {
        Assert.assertEquals(null, MethodBridge.call("unknown"));
    }

    @Test
    public void testMethodWithParam() {
        Assert.assertEquals(1, MethodBridge.call("methodWithParam", 1));
    }

    @Test
    public void testMethodWithParams() {
        Object result = MethodBridge.call("methodWithParams", 1, "2");
        Assert.assertNotNull(result);
        Assert.assertTrue(result instanceof Object[]);
        Object[] result2 = (Object[])result;
        Assert.assertTrue(result2[0] == Integer.valueOf(1));
        Assert.assertTrue(result2[1].equals("2"));
    }

    @Before
    public void setUp() throws Exception {
        configLog();
        MethodBridge.registerAnnotatedClass(this);
    }

    @After
    public void tearDown() throws Exception {
        MethodBridge.unregisterAnnotatedClass(this);
    }

    @ExportMethod(name = "emptyMethod")
    public void emptyMethod() {
        Log.d(TAG, "call emptyMethod");
    }

    @ExportMethod(name = "returnIntMethod")
    public int returnIntMethod() {
        Log.d(TAG, "call returnIntMethod");
        return 1;
    }

    @ExportMethod(name = "returnObjectMethod")
    public Data returnObjectMethod() {
        Log.d(TAG, "call returnObjectMethod");
        return new Data();
    }

    @ExportMethod(name = "methodWithParam")
    public int methodWithParam(int param) {
        Log.d(TAG, "call methodWithParam, param = " + param);
        return param;
    }

    @ExportMethod(name = "methodWithParams")
    public Object[] methodWithParams(int param1, String param2) {
        Log.d(TAG, String.format("call methodWithParams, param1 = %d, param2 = %s", param1, param2));
        return new Object[]{param1, param2};
    }

    @ExportMethod(name = "staticMethod")
    public static int staticMethod() {
        Log.d(TAG, String.format("call staticMethod"));
        return 1;
    }
}
