## Introduction

**Language**: English | [中文](README_CN.md)

Code Generation Documentation: https://iohao.github.io/game/docs/examples/code_generate

This project is a joint debugging demonstration related to the ionet SDKs (C# Sdk, Ts Sdk). The server provides content such as `actions, broadcasts, and error codes` for interactive demonstrations.

**This project primarily demonstrates 3 aspects of functionality**

1.  Joint debugging and interaction with various front-end SDKs: `actions, broadcasts, error codes`. It also showcases support for [Protocol Fragments](https://iohao.github.io/ionet/docs/manual_high/protocol_fragment) and List data transmission.
2.  Heartbeat processing.
3.  Code generation for connecting each SDK: generating interactive code for `actions, broadcasts, error codes`.

**Several Advantages of SDK Code Generation**

1.  Helps client developers **reduce a massive amount of work** by **eliminating the need to write extensive boilerplate code**.
2.  **Clear and unambiguous semantics**. The generated interactive code clearly defines the required parameter types and whether the server will return a value. These details are established during interface generation.
3.  Because we can clearly define the interaction interfaces, we can consequently define parameter types. This makes the **interface method parameter types safe and explicit**, effectively avoiding potential safety hazards and thus **reducing low-level errors during joint debugging**.
4.  Reduces communication costs between the server and client during integration; **code is the documentation**. The generated integration code includes documentation and usage examples. The examples on the methods will teach you how to use them, allowing even beginners to achieve a **zero learning curve**.
5.  Helps client developers abstract away the server interaction part, allowing them to **focus more effort on the actual business logic**.
6.  Reduces the cognitive burden for both parties during joint debugging. The integration code is simple to use and **as smooth as a local method call**.
7.  Abandons the traditional protocol-oriented integration approach in favor of an **interface-method-oriented integration approach**.
8.  When our Java code is finished, our documentation and interaction interfaces are updated synchronously, eliminating the need to spend extra time maintaining integration documentation and its content.

## Start the Server

> Run `SdkApplication.java` to start the game server

!(./doc/server.png)

## Generate Code

After running `GenerateTest`:

1.  Files for front-end interaction will be generated in the `./target/code` directory.
2.  `.proto` files will be generated in the `./target/proto` directory.

## Use Cases for SDK Code Generation

Automatically generate the interactive code required by the front-end client based on the `actions, broadcasts, and error codes` provided by the server. Currently, support is provided for multiple languages: C#, TypeScript, and GDScript, with their corresponding game engines:

| SDK            | Supported Game Engines     |
|----------------|----------------------------|
| C# SDK         | Godot, Unity               |
| TypeScript SDK | Cocos Creator, Laya        |
| GDScript SDK   | Godot                      |

In addition to game engines, the TypeScript SDK can also be used in various modern front-end frameworks such as React, Vue, and Angular. Simply put, the TypeScript SDK is suitable for any project that supports TypeScript. Similarly, the C# SDK is suitable for any project that supports C#.

Suppose your server project now provides hundreds of action methods, and the front-end engine needs to switch from TypeScript to a C#-related engine. In that case, the framework's code generation will be extremely useful, as it can instantly generate the necessary code for front-end interaction. This operation will save the front-end a massive amount of work and greatly reduce communication costs. The generated interaction API is intuitive and as smooth as a local method call.

In other words, you can take your ionet project and use it in your next new project (or a new company's project). Regardless of whether the new project's front end uses a TypeScript game engine or a C# game engine, the framework can generate the interactive code for the front end.

Furthermore, besides the scenario of switching front-end engines, the framework also supports simultaneous interaction with different game engines, as the usage experience is virtually consistent regardless of which language's interactive API is generated.

> Other example: When developing a personal project and unable to find a front-end developer for collaboration, you can still write the game server-related code first. If you meet a suitable collaborator in the future, you can generate the existing actions for them via code generation.

## Front-end Example Projects

The following demos have been successfully integrated with this project. The front-end code for **actions, broadcasts, and error codes** is generated by the framework. Front-end developers will find the communication interaction as smooth as a local method call, allowing them to focus more effort on the actual business logic rather than writing boilerplate interactive code.

| Github                                                                            | Language   | Description                                                                                                   |
|-----------------------------------------------------------------------------------|------------|---------------------------------------------------------------------------------------------------------------|
| [SdkGDScriptExampleGodot](https://github.com/iohao/ioGameSdkGDScriptExampleGodot) | GDScript   | An example of interoperability with Godot. Godot, Protobuf, Netty, ionet, GDScript, WebSocket                   |
| [SdkC#ExampleGodot](https://github.com/iohao/ioGameSdkCsharpExampleGodot)         | C#         | An example of interoperability with Godot. Godot, Protobuf, Netty, ionet, C#, Csharp, WebSocket                 |
| [SdkC#ExampleUnity](https://github.com/iohao/ioGameSdkCsharpExampleUnity)         | C#         | An example of interoperability with Unity. Unity, Protobuf, Netty, ionet, C#, Csharp, WebSocket                 |
| [SdkTsExampleCocos](https://github.com/iohao/ioGameSdkTsExampleCocos)             | TypeScript | An example of interoperability with Cocos Creator. CocosCreator, Protobuf, Netty, ionet, TypeScript, WebSocket  |
| [SdkTsExampleVue](https://github.com/iohao/ioGameSdkTsExampleVue)                 | TypeScript | An example of interoperability with Vue. Vue, Protobuf, Netty, ionet, TypeScript, WebSocket                     |
| [SdkTsExampleAngular](https://github.com/iohao/ioGameSdkTsExampleAngular)         | TypeScript | An example of interoperability with Angular. Angular, Protobuf, Netty, ionet, TypeScript, WebSocket             |
| [SdkTsExampleHtml](https://github.com/iohao/ioGameSdkTsExampleHtml)               | TypeScript | An example of interoperability with webpack. (webpack: html + ts), Protobuf, Netty, ionet, TypeScript, WebSocket |

## Write Once, Integrate Everywhere; Achieve Massive Productivity Gains

The framework can generate `action, broadcast, and error code` related code for various front ends. This means you only need to write your business logic once in Java, and you can interact with these game engines or modern front-end frameworks simultaneously.

**Write Once** refers to writing the Java business code one time, while **Integrate Everywhere** refers to generating server interaction code for different front-end projects.

### Action Java Code

Let's look at a piece of Java code. We only need to pay attention to the Javadoc comments on the class and the action methods.

We provide descriptions for the action methods, including:

1.  Method description
2.  Parameter description
3.  Return value description

!(./doc/MyAction.png)

### Generated TypeScript Code Demonstration

The corresponding Ts code generated from `MyAction.java` includes:

1.  Generated method description.
2.  Generated method parameter types and their descriptions.
3.  Generated method return types and their descriptions.
4.  More importantly, it generates relevant usage examples. The examples on the methods will teach you how to use them, allowing even beginners to achieve a zero learning curve.

The framework generates two coding styles for each action method:

1.  Code style: **Callback style**, with method names starting with `of`. **Advantage: Concise, monolithic**.
2.  Code style: **Async/await style**, with method names starting with `ofAwait`. **Advantage: Avoids callback hell**.

**How to Choose Between the Two Styles?**

There is no significant difference between the two coding styles fundamentally. Choose callback for simple business logic, and async/await is recommended for complex business logic. Complexity here means needing to continue requesting the game server within a callback, which can lead to callback hell, a problem that the async/await coding style effectively avoids.

**Code Style: Callback**

The image below demonstrates the `code style: callback`. **Advantage: Concise, monolithic**.

> `hello` action

!(./doc/TsHello1.png)

> `loginVerify` action

!(./doc/TsLoginVerify1.png)

**Code Style: Async/Await**

The image below demonstrates the `code style: async await`. **Advantage: Avoids callback hell**.

> **Note** the usage examples within the method; the framework generates corresponding examples for different coding styles.

> `hello` action

!(./doc/TsHello2.png)

> `loginVerify` action

!(./doc/TsLoginVerify2.png)

### Generated C# Code Demonstration

The corresponding C# code generated from `MyAction.java` includes:

1.  Generated method description.
2.  Generated method parameter types and their descriptions.
3.  Generated method return types and their descriptions.
4.  More importantly, it generates relevant usage examples. The examples on the methods will teach you how to use them, allowing even beginners to achieve a zero learning curve.

The framework generates two coding styles for each action method:

1.  Code style: **Callback style**, with method names starting with `Of`. **Advantage: Concise, monolithic**.
2.  Code style: **Async/await style**, with method names starting with `OfAwait`. **Advantage: Avoids callback hell**.

**How to Choose Between the Two Styles?**

There is no significant difference between the two coding styles fundamentally. Choose callback for simple business logic, and async/await is recommended for complex business logic. Complexity here means needing to continue requesting the game server within a callback, which can lead to callback hell, a problem that the async/await coding style effectively avoids.

**Code Style: Callback**

The image below demonstrates the `code style: callback`. **Advantage: Concise, monolithic**.

> `hello` action

!(./doc/CsHello1.png)

> `loginVerify` action

!(./doc/CsLoginVerify1.png)

**Code Style: Async/Await**

The image below demonstrates the `code style: async await`. **Advantage: Avoids callback hell**.

> Note the usage examples within the method; the framework generates corresponding examples for different coding styles.

> `hello` action

!(./doc/CsHello2.png)

> `loginVerify` action

!(./doc/CsLoginVerify2.png)

### Summary

Now we understand the power of "write once, integrate everywhere." We only need to write one piece of Java code to generate the integration code for various clients, significantly reducing the workload for front-end developers. The generated interactive API is intuitive and as smooth as a local method call.

Code generation allows developers to focus on business logic rather than documentation writing. It also improves the efficiency and quality of team collaboration, ensuring the documentation is synchronized and accurate. Without this game documentation generation, you would have to spend time writing and maintaining integration documentation. Furthermore, as the team grows, documentation issues like being disorganized, unsynchronized, outdated, or forgotten to be updated will arise.

The framework ensures that Java method comments serve as documentation and the action code serves as the integration interface. Once your code is finished, the corresponding documentation and interactive code are also complete. This eliminates the need to spend extra time writing and maintaining documentation, saving more time.

The framework generates two coding styles for each action method:

1.  Code style: **Callback style**, with method names starting with `of` (or `Of` in C#). **Advantage: Concise, monolithic**.
2.  Code style: **Async/await style**, with method names starting with `ofAwait` (or `OfAwait` in C#). **Advantage: Avoids callback hell**.

## How to Generate SDK Interactive Code

Refer to `GenerateTest.java` for code generation details.

Generating interactive code for various SDKs using the framework is simple, typically involving the following steps:

1.  Load the game logic server (business logic server) classes that require code generation, for generating `xxxAction`.
2.  Add the interactive languages you want to generate, e.g., `generateCocosXXX`.
3.  Add the error code class: `addErrorCodeClass`, for generating error codes.
4.  Generate `.proto` protocol: `generateProtoFile`, for generating `.proto` files.

For now, we only need to focus on the methods starting with `generateCodeXXX`. Although the code below shows multiple `generateCodeXXX` calls, in actual development, we typically only need to generate one of them. The interactive code is generated by default into the `target/code` directory of the current project.

Currently, we only provide generation implementations for TypeScript, C#, and GDScript:

| Class                      | Description                   |
|----------------------------|-------------------------------|
| GDScriptDocumentGenerate   | Used to generate GDScript code    |
| CSharpDocumentGenerate     | Used to generate C# code          |
| TypeScriptDocumentGenerate | Used to generate TypeScript code  |

> The next section will detail each `generateCodeXXX` method.


```java
public final class GenerateTest {
    // setting root path
    static String rootPath = "/Users/join/gitme/test-sdk/";

    static void main() {
        // i18n: CHINA or US
        Locale.setDefault(Locale.US);
        CoreGlobalConfig.setting.parseDoc = true;

        /*
         * ExternalServer accessAuthentication
         * cn: 对外服访问权限，不生成权限控制的 action
         */
        SdkApplication.extractedAccess();
        DocumentAccessAuthentication reject = ExternalGlobalConfig.accessAuthenticationHook::reject;
        DocumentHelper.setDocumentAccessAuthentication(reject);

        // Load the business framework of each gameLogicServer
        // cn: 加载游戏逻辑服的业务框架
        SdkApplication.listLogic().forEach(logicServer -> {
            var builder = BarSkeleton.builder();
            logicServer.settingBarSkeletonBuilder(builder);
            builder.build();
        });

        /*
         * Generate actions, broadcasts, and error codes.
         * cn: 生成 action、广播、错误码
         */

        // ----- About generating TypeScript code -----
//        generateCodeVue();
//        generateCodeAngular();
//        generateCodeHtml();
//        generateCocosCreator();
//
        // ----- About generating C# code -----
//        generateCodeCsharpGodot();
//        generateCodeCsharpUnity();

        // ----- About generating GDScript code -----
        generateCodeGDScriptGodot();

        // Added an enumeration error code class to generate error code related information
        DocumentHelper.addErrorCodeClass(ErrorCode.class);
        // Generate document
        DocumentHelper.generateDocument();

        // Generate .proto
        generateProtoFile();
    }

    static void generateProtoFile() {
        /*
         * .proto generate
         * document https://iohao.github.io/game/docs/extension_module/jprotobuf
         */

        // By default, it will be generated in the target/proto directory
        // .proto 默认生成的目录为 target/proto

        // The package name to be scanned
        String packagePath = "com.iohao.example.sdk.data";
        GenerateFileKit.generate(packagePath);
    }
}
```

### Generate TypeScript

`generateCodeVue()` and `generateCocosCreator()` are both generated via the `TypeScriptDocumentGenerate` class.

> Below is the code for generating the TypeScript language, which generates the interactive code required for Vue and Cocos Creator projects, respectively.



```java
public final class GenerateTest {
    String rootPath = "/Users/join/gitme/example-sdk/";
    
    static void main() {
        ... 
        /*
         * Generate actions, broadcasts, and error codes.
         * cn: 生成 action、广播、错误码
         */
        
        // About generating TypeScript code
        generateCodeVue();
        generateCocosCreator();        
        ...
    }
    
    private static void generateCodeVue() {
        var documentGenerate = new TypeScriptDocumentGenerate();

        // 设置代码生成所存放的路径，如果不做任何设置，将会生成在 target/code 目录中
        // By default, it will be generated in the target/code directory
        String path = rootPath + "ioGameSdkTsExampleVue/src/assets/gen/code";
        documentGenerate.setPath(path);

        DocumentHelper.addDocumentGenerate(documentGenerate);
    }

    private static void generateCocosCreator() {
        var documentGenerate = new TypeScriptDocumentGenerate();

        // 设置代码生成所存放的路径，如果不做任何设置，将会生成在 target/code 目录中
        // By default, it will be generated in the target/code directory
        String path = rootPath + "ioGameSdkTsExampleCocos/assets/scripts/gen/code";
        documentGenerate.setPath(path);

        DocumentHelper.addDocumentGenerate(documentGenerate);
    }
}
```

### Generate C#

`generateCodeCsharpUnity()` and `generateCodeCsharpGodot()` are both generated via the `CsharpDocumentGenerate` class.

> Below is the code for generating the C# language, which generates the interactive code required for Unity and Godot projects, respectively.

In the examples below, Unity and Godot use the same approach for code generation.



```java
public final class GenerateTest {
    String rootPath = "/Users/join/gitme/example-sdk/";
    
    static void main() {
        ... 
        /*
         * Generate actions, broadcasts, and error codes.
         * cn: 生成 action、广播、错误码
         */
        
        // ----- About generating C# code -----
        generateCodeCsharpUnity();
        generateCodeCsharpGodot(); 
        ...
    }
    
    private static void generateCodeCsharpUnity() {
        var documentGenerate = new CsharpDocumentGenerate();
        // 设置代码生成所存放的路径，如果不做任何设置，将会生成在 target/code 目录中
        // By default, it will be generated in the target/code directory
        String path = rootPath + "SdkCsharpExampleUnity/Assets/Scripts/Gen/Code";
        documentGenerate.setPath(path);

        DocumentHelper.addDocumentGenerate(documentGenerate);
    }

    private static void generateCodeCsharpGodot() {
        var documentGenerate = new CsharpDocumentGenerate();
        // 设置代码生成所存放的路径，如果不做任何设置，将会生成在 target/code 目录中
        // By default, it will be generated in the target/code directory
        String path = rootPath + "SdkCsharpExampleGodot/script/gen/code";
        documentGenerate.setPath(path);

        DocumentHelper.addDocumentGenerate(documentGenerate);
    }
}
```

### Broadcast Code

For TypeScript and C# (Csharp), the generated broadcast-related code files are uniformly named `Listener`, such as `Listener.cs` and `Listener.ts`. The `Listener` file contains all proactive broadcast actions from the server. Typically, you only need to write your business logic within the broadcast listener callback, as shown below:

**C#**

```c#
Listener.ListenBulletBroadcast(result =>
{
    var bulletMessage = result.GetValue<BulletMessage>();
    result.Log(bulletMessage);
});

// This method adds print behavior for all broadcast listeners, aiming to inform developers which broadcast methods haven't been handled.
// cn: 该方法为所有广播监听添加了打印的行为，目的是让开发者知道有哪些广播方法没有处理
Listener.Listener_all();
```



**Typescript**

```typescript
Listener.listenBulletBroadcast(result => {
    const bullet = result.getValue(BulletMessageSchema);
    result.log(bullet);
})

// This method adds print behavior for all broadcast listeners, aiming to inform developers which broadcast methods haven't been handled.
// cn: 该方法为所有广播监听添加了打印的行为，目的是让开发者知道有哪些广播方法没有处理
Listener.listener_all();
```


### Error Codes

The code file related to error codes is uniformly named `GameCode`. When an error is triggered, the server places the error code in the `result.responseStatus` field.

The following demonstrates error handling for the two coding styles:



```c#
public static async Task OnTestError()
{    
    var value = _testErrorValue++;
    Log("------- OnTestError ------");

    // code style: callback.
    SdkAction.OfTestError(value, result =>
    {
        result.Log(result.GetInt());
    }).OnError(result =>
    {
        // error
        result.Log(result.GetErrorInfo());
        // result.GetResponseStatus()
    });

    // code style: await promise.
    var result = await SdkAction.OfAwaitTestError(value);
    // result.GetResponseStatus()
    if (result.Success())
        result.Log(result.GetInt());
    else
        result.Log(result.GetErrorInfo());
}
```

## Notes and Precautions

By default, the method names for the generated action interaction code use the Java action method names. If your Java action method names change, it will affect the client-side code.

To prevent this situation, there are two approaches:

1.  Do not change the method names of already published actions.
2.  Add the `@DocumentMethod` annotation to the action to fix the method name. When generating the integration code, the framework will prioritize using the value specified in the `@DocumentMethod` annotation.

```java
@ActionController(SdkCmd.cmd)
public final class SdkAction {    
    @ActionMethod(SdkCmd.loginVerify)
    @DocumentMethod("loginVerifyName")
    public UserMessage loginVerify(LoginVerifyMessage verifyMessage) {
        ...
    }
}
```

Typically, if you do not frequently modify your action method names, you can omit the use of the `@DocumentMethod` annotation to keep your code cleaner. However, you must remember not to change method names that have already been published, as this may cause unnecessary frustration for front-end developers.


