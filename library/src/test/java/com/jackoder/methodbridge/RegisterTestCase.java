package com.jackoder.methodbridge;

import com.jackoder.methodbridge.ann.ExportMethod;
import com.jackoder.methodbridge.base.BaseTestCase;

import junit.framework.Assert;

import org.junit.Test;

/**
 * @author Jackoder
 * @version 2015/12/30.
 */
public class RegisterTestCase extends BaseTestCase{

    @ExportMethod(name = "test")
    public void emptyMethod() {

    }

    @Test
    public void testUnregister() {
        MethodBridge.registerAnnotatedClass(this);
        Assert.assertTrue(MethodBridge.contains("test"));
        MethodBridge.unregisterAnnotatedClass(this);
        Assert.assertFalse(MethodBridge.contains("test"));
    }
}
