# [Zircon](https://github.com/122006/Zircon)可以让你在Java语言代码中直接使用一些特殊的语法

> 项目自用封装插件，建议可以按照自己的使用习惯自行封装拓展方法

### 2. 全局拓展方法

`someList.stream().filter(a->a.age==25).findFirst().get()`
替换为=>
`someList.find(a->a.age==25)`
> 自定义拓展已有代码的实现方法。可以实现诸如顶级方法、方法替换等功能

## 引入依赖

### Gradle

Step 1. Add the JitPack repository to your build file

Add it in your root build.gradle at the end of repositories:

````
	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
````

Step 2. Add the dependency

````
    dependencies {
	        implementation 'com.github.122006:ExMethodUtil:1.1.5'
	}
````

[![](https://jitpack.io/v/122006/ExMethodUtil.svg)](https://jitpack.io/#122006/ExMethodUtil)

### Maven

Step 1. Add the JitPack repository to your build file

````
    <repositories>
    <repository>
    <id>jitpack.io</id>
    <url>https://jitpack.io</url>
    </repository>
    </repositories>
````

Step 2. Add the dependency

````
	<dependency>
	    <groupId>com.github.122006</groupId>
	    <artifactId>ExMethodUtil</artifactId>
	    <version>1.1.5</version>
	</dependency>
````

该项目已结合zircon模块，maven中引用可以省略zircon

[![](https://jitpack.io/v/122006/ExMethodUtil.svg)](https://jitpack.io/#122006/ExMethodUtil)


### 1.1.4不兼容性更新
> 规范部分参数定义
1. 全局替换 `.let(`  =>  `.with(`
2. 全局替换 `.nullOr(`  =>  `.orElse(`
2. 全局替换 `.nullOrThrow(`  =>  `.orElseThrow(`