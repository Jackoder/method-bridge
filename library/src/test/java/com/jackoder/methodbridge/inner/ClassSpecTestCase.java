package com.jackoder.methodbridge.inner;

import com.jackoder.methodbridge.ann.ExportMethod;
import com.jackoder.methodbridge.base.BaseTestCase;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Jackoder
 * @version 2016/6/21
 */
public class ClassSpecTestCase extends BaseTestCase {

    public class TestAnnotation {

        @ExportMethod(name = "just do it")
        public void justForFun() {

        }

        @ExportMethod(name = {"just do it", "don't do it"})
        public void justForFun2() {

        }
    }

    @Test
    public void testClassSpec() {
        try {
            ExportMethodAnn exportMethodAnn = ClassSpecRegistry.getExportMethodAnn(TestAnnotation.class.getDeclaredMethod("justForFun"));
            Assert.assertNotNull(exportMethodAnn);
            Assert.assertArrayEquals(exportMethodAnn.names, new String[]{"just do it"});
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            Assert.fail();
        }
    }

    @Test
    public void testClassSpec2() {
        try {
            ExportMethodAnn exportMethodAnn = ClassSpecRegistry.getExportMethodAnn(TestAnnotation.class.getDeclaredMethod("justForFun2"));
            Assert.assertNotNull(exportMethodAnn);
            Assert.assertArrayEquals(exportMethodAnn.names, new String[]{"just do it", "don't do it"});
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            Assert.fail();
        }
    }
}
