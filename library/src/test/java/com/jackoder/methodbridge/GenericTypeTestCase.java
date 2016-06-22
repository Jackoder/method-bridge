package com.jackoder.methodbridge;

import com.jackoder.methodbridge.base.BaseTestCase;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Jackoder
 * @version 2015/12/31.
 */
public class GenericTypeTestCase extends BaseTestCase {

    @Test
    public void testReturnBoolType() {
        boolean retBool = MethodBridge.callSafe("emptyMethod", false);
        boolean[] retBools1 = MethodBridge.call("emptyMethod");
        boolean[] retBools2 = MethodBridge.callSafe("emptyMethod", new boolean[]{false});
        Assert.assertFalse(retBool);
        Assert.assertNull(retBools1);
        Assert.assertArrayEquals(new boolean[]{false}, retBools2);
    }

    @Test
    public void testReturnStringType() {
        String retString = MethodBridge.callSafe("emptyMethod", "testReturnStringType");
        String[] retStrings1 = MethodBridge.call("emptyMethod");
        String[] retStrings2 = MethodBridge.callSafe("emptyMethod", new String[]{""});
        Assert.assertEquals(retString, "testReturnStringType");
        Assert.assertNull(retStrings1);
        Assert.assertArrayEquals(new String[]{""}, retStrings2);
    }

    @Test
    public void testReturnByteType() {
        byte retByte = MethodBridge.callSafe("emptyMethod", (byte)0);
        byte[] retBytes1 = MethodBridge.call("emptyMethod");
        byte[] retBytes2 = MethodBridge.callSafe("emptyMethod", new byte[]{0});
        Assert.assertEquals(retByte, (byte)0);
        Assert.assertNull(retBytes1);
        Assert.assertArrayEquals(new byte[]{0}, retBytes2);
    }

    @Test
    public void testReturnCharType() {
        char retChar = MethodBridge.callSafe("emptyMethod", (char)0);
        char[] retChars1 = MethodBridge.call("emptyMethod");
        char[] retChars2 = MethodBridge.callSafe("emptyMethod", new char[]{0});
        Assert.assertEquals(retChar, (char)0);
        Assert.assertNull(retChars1);
        Assert.assertArrayEquals(new char[]{0}, retChars2);
    }

    @Test
    public void testReturnShortType() {
        short retShort = MethodBridge.callSafe("emptyMethod", (short)0);
        short[] retShorts1 = MethodBridge.call("emptyMethod");
        short[] retShorts2 = MethodBridge.callSafe("emptyMethod", new short[]{0});
        Assert.assertEquals(retShort, (short)0);
        Assert.assertNull(retShorts1);
        Assert.assertArrayEquals(new short[]{0}, retShorts2);
    }

    @Test
    public void testReturnIntType() {
        int retShort = MethodBridge.callSafe("emptyMethod", 0);
        int[] retShorts1 = MethodBridge.call("emptyMethod");
        int[] retShorts2 = MethodBridge.callSafe("emptyMethod", new int[]{0});
        Assert.assertEquals(retShort, 0);
        Assert.assertNull(retShorts1);
        Assert.assertArrayEquals(new int[]{0}, retShorts2);
    }

    @Test
    public void testReturnLongType() {
        long retLong = MethodBridge.callSafe("emptyMethod", 0);
        long[] retLongs1 = MethodBridge.call("emptyMethod");
        long[] retLongs2 = MethodBridge.callSafe("emptyMethod", new long[]{0});
        Assert.assertEquals(retLong, 0);
        Assert.assertNull(retLongs1);
        Assert.assertArrayEquals(new long[]{0}, retLongs2);
    }

    @Test
    public void testReturnFloatType() {
        float retFloat = MethodBridge.callSafe("emptyMethod", 0);
        float[] retFloats1 = MethodBridge.call("emptyMethod");
        float[] retFloats2 = MethodBridge.callSafe("emptyMethod", new float[]{0});
        Assert.assertEquals(retFloat, 0, 0);
        Assert.assertNull(retFloats1);
        Assert.assertArrayEquals(new float[]{0}, retFloats2, 0);
    }

    @Test
    public void testReturnDoubleType() {
        double retDouble = MethodBridge.callSafe("emptyMethod", 0);
        double[] retDoubles1 = MethodBridge.call("emptyMethod");
        double[] retDoubles2 = MethodBridge.callSafe("emptyMethod", new double[]{0});
        Assert.assertEquals(retDouble, 0, 0);
        Assert.assertNull(retDoubles1);
        Assert.assertArrayEquals(new double[]{0}, retDoubles2, 0);
    }
}
