package com.jackoder.methodbridge;

import android.util.Log;

import com.jackoder.methodbridge.ann.ExportMethod;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

/**
 * @author Jackoder
 * @version 2015/12/30.
 */
public class OverrideTestCase extends BasicTestCase {

    @Override
    @Before
    public void setUp() throws Exception {
        configLog();
        MethodBridge.registerAnnotatedClass(this);
    }

    @Override
    @After
    public void tearDown() throws Exception {
        MethodBridge.unregisterAnnotatedClass(this);
    }

    @ExportMethod(name = "staticMethod")
    public static int staticMethod() {
        Log.d("MethodTest2", "test duplicate name");
        Log.d("MethodTest2", String.format("call staticMethod"));
        return 2;
    }

    @ExportMethod(name = "override")
    public int returnIntMethod1() {
        Log.d("MethodTest2", "call returnIntMethod1");
        return 1;
    }

    @ExportMethod(name = "override")
    public int returnIntMethod2() {
        Log.d("MethodTest2", "call returnIntMethod2");
        return 2;
    }

    @Ignore("TestCaseTwo不执行")
    @Test
    @Override
    public void testStaticMethod() {
        super.testStaticMethod();
    }

    @Test
    public void testDuplicateName() {
        Assert.assertEquals(2, MethodBridge.call("staticMethod"));
    }

    @Ignore("TODO 同个类的方法存在不确定性，待确认")
    @Test
    public void testOverrideMethod() {
        Assert.assertEquals(2, MethodBridge.call("override"));
    }

    @Test
    public void testPassNull() {
        MethodBridge.call("override", null);
        Assert.assertNull(MethodBridge.call("methodWithParam", null));
    }

}
