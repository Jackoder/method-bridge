package com.jackoder.methodbridge;

/**
 * @author Jackoder
 * @version 2015/12/30.
 */
public interface MethodDelegate<T> {

    Object onCall(String name, T... data) throws Exception;
}
