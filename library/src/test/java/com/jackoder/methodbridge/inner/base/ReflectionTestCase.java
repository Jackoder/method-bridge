package com.jackoder.methodbridge.inner.base;

import com.jackoder.methodbridge.inner.utils.ReflectionUtils;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * @author Jackoder
 * @version 2016/6/21
 */
public class ReflectionTestCase {

    public interface InterfaceA {

    }

    public interface InterfaceB {

    }

    public class ClassA implements InterfaceA{

    }

    public class ClassB extends ClassA {

    }

    @Test
    public void testReflection() {
        List<Class<?>> result = ReflectionUtils.buildClassHierarchy(ClassB.class);
        Assert.assertNotNull(result);
        Assert.assertTrue(result.size() == 3);
        Assert.assertTrue(result.get(2).equals(ClassB.class) && result.get(1).equals(ClassA.class) && result.get(0).equals(Object.class));
    }

}
