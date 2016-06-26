[![](https://jitpack.io/v/Jackoder/method-bridge.svg)](https://jitpack.io/#Jackoder/method-bridge)
[![](https://travis-ci.org/Jackoder/method-bridge.svg?branch=master)](https://travis-ci.org/Jackoder/method-bridge.svg?branch=master)

# Method Bridge
一个简单的方法调用方案

特性
-------

* 方法动态注册
* 跨对象、跨页面、跨线程等方法调用

添加依赖
-------
 
配置仓库
```gradle
allprojects {
    repositories {
        ...
        maven { url "https://jitpack.io" }
    }
}
```

配置依赖
```gradle
dependencies {
    compile 'com.github.Jackoder:method-bridge:1.0'
}
```

混淆配置
-------

```proguard
# method-bridge
-keepnames class com.jackoder.methodbridge.ann.* {*;}
-keepclassmembers class * {
    @com.jackoder.methodbridge.ann.* <methods>;
}
```

使用
-------

声明

```java
public class A {

	private UserInfo mUserInfo;

    /**
     * @params 无
     * @return UserInfo
     * @throws 无
     */
	@ExportMethod(name = "getUserInfo")
    UserInfo getUserInfo() {
    	return mUserInfo;
    }

	@ExportMethod(name = "setUserName")
    void setUserName(String userName) {
    	mUserInfo.setUserName(userName);
    }
}
```

注册

```java
public class A {

    @Override
	public void onCreate(Bundle savedInstanceState) {
        MethodBridge.registerAnnotatedClass(this);
    }

    @Override
	protected void onDestroy() {
        super.onDestroy();
        MethodBridge.unregisterAnnotatedClass(this);
    }
}
```

调用

```java
public class B {

    /**
     * 调用 A 对象的方法，得到 UserInfo
     */
    String getUserName() {
    	UserInfo userInfo = MethodBridge.call("getUserInfo");
        if (userInfo != null) {
        	return userInfo.getUserName();
        }
        return null;
    }

    /**
     * 调用 A 对象的方法，修改 UserInfo
     */
    void setUserName(String userName) {
    	MethodBridge.call("setUserName", userName);
    }
}
```

其它
-------

**判断方法是否注册**

`MethodBridge.contains(String name)` 方法可判断方法是否注册

**处理异常**

调用 `call` 方法不会抛出异常，方法未找到或方法执行异常都会返回 `null`。如果需要处理异常改用 `callEx` 方法，例如：

```java
public void handleException() {
	try {
    	MethodBridge.callEx("setUserName");
    } catch (Exception e) {
    	//handle exception here
    }
}
```

**基础类型**

当遇到基础类型返回 `null` 的情况（如方法未注册），会导致装箱操作报错，调用 `callSafe` 指定返回默认值即可。 例如：

```java
public void handleSafe() {
	MethodBridge.callSafe("setUserName", "defaultName");
}
```

问题
-------

**优点**

过去跨对象间的方法调用可以通过接口、传入对象等方式实现，但需要添加许多额外的代码，使得结构更为复杂。使用该方案一定程度上可以解耦代码，调用方更为实现更加简单。

**生命周期管理**

方法的声明周期依赖于`registerAnnotatedClass` 和 `unregisterAnnotatedClass` 执行的位置，需要由开发者控制。当方法不需要使用时，请及时取消注册，避免造成**内存泄漏**。

**方法同名**

方法注册采取同名覆盖的机制，后注册的方法如果在方法池中存在同名 `name`，则使用后注册的方法。

**跨进程调用**

暂时还不支持

**Bug与建议**

欢迎提交提交Bug与建议到 [Issues](https://github.com/Jackoder/method-bridge/issues) 中。

License
-------

    Copyright 2016 Jackoder

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
