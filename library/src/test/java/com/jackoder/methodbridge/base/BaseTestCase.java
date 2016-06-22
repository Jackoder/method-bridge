package com.jackoder.methodbridge.base;

import com.jackoder.methodbridge.BuildConfig;

import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowLog;

/**
 * @author Jackoder
 * @version 2015/12/31.
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = IConfig.EMULATE_SDK, manifest = IConfig.MANIFEST)
@Ignore("基类不处理")
public class BaseTestCase {

    protected void configLog() {
        ShadowLog.stream = System.out;
    }
}
