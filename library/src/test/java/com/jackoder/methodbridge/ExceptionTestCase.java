package com.jackoder.methodbridge;

import com.jackoder.methodbridge.ann.ExportMethod;
import com.jackoder.methodbridge.base.BaseTestCase;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Jackoder
 * @version 2015/12/31.
 */
public class ExceptionTestCase extends BaseTestCase {

    @ExportMethod(name = "throwException")
    public void throwException() throws Exception {
        throw new NullPointerException("yes");
    }

    @Test
    public void testThrowException() {
        try {
            MethodBridge.callEx("throwException");
            Assert.fail();
        } catch (Throwable e) {
            Assert.assertTrue(e instanceof NullPointerException);
        }
    }

    @Test
    public void testNotThrowException() {
        try {
            MethodBridge.call("throwException");
        } catch (Exception e) {
            Assert.fail();
        }
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
}
