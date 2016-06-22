package com.jackoder.methodbridge.inner.utils;

import com.jackoder.methodbridge.ann.ExportMethod;
import com.jackoder.methodbridge.base.BaseTestCase;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import java.lang.reflect.Method;
import java.util.HashMap;

/**
 * @author Jackoder
 * @version 2016/6/21
 */
public class AnnReaderTestCase extends BaseTestCase {

    public class TestAnnotation {

        @ExportMethod(name = "just do it")
        public void justForFun() {

        }
    }

    @Ignore
    @Test
    public void testReadAnnotation() {
        try {
            TestAnnotation target = new TestAnnotation();
            Method method = target.getClass().getDeclaredMethod("justForFun");
            HashMap<String, Object> result = AnnotationElementsReader.getElements(method.getDeclaredAnnotations()[0]);
            Assert.assertNull(result);
            Assert.assertFalse(result.isEmpty());
            Assert.assertTrue(result.containsKey("name"));
            Assert.assertTrue(result.get("name").equals("just do it"));
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            Assert.fail();
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail();
        }
    }
}
