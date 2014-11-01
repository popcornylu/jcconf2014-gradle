# Introduction
此project為一個Gradle範例程式，目標是透過gradle來產生一個application script。

java要寫一個cli application比較麻煩，比較常見的做法是包裝成一個[Application Jar](http://docs.oracle.com/javase/tutorial/deployment/jar/appman.html)，透過類似下面的指令執行

```
> java -jar my-app.jar
```

但是這樣做有幾個問題

- 如何包我們相依的jars? 
- 這種執行方法需要直接指到jar檔的位置，而不能像常見的cli只要放在`PATH`環境變數執行。

通常我們可能用類似[fatjar](http://fjep.sourceforge.net/)來解決第一個問題。但是第二個問題我們更想要的是透過類似下面這種方式執行。

```
> my-app
```

透過這樣的方法，只要把`my-app`放在`PATH`當中，就可以在shell的任意地方執行我們的程式。為了這樣的目的，通常我們會再寫一個shell script來去執行java程式，但是怎麼樣都覺得有點麻煩。而gradle的application plugin可以完全地解決這個問題。

# Application Plugin

在`build.gradle`中加入下面這行

```
apply plugin:'application`
```

加上Main Class的完整名稱

```
mainClassName = "tw.jcconf.MyHello"
```

用gradle執行程式

```
> gradle run
:compileJava UP-TO-DATE
:processResources UP-TO-DATE
:classes UP-TO-DATE
:run
hello world

```

產生application script

```
> gradle installApp
```

此時會在`build/install/myhello/`此目錄產生我們的application目錄，裡面的內容如下

```
> tree
.
├── bin
│   ├── myhello
│   └── myhello.bat
└── lib
    ├── commons-cli-1.2.jar
    ├── myhello.jar
    └── slf4j-api-1.7.5.jar
```

我們可以直接在裡面玩玩看

```
> bin/myhello
hello world
```

```
> bin/myhello -h
usage: myhello [options] [name]
 -h         Print this help
 -n <arg>   Number of hello to print
```

```
> bin/myhello -n 3
hello world
hello world
hello world
```

```
> bin/myhello -n 3 jcconf
hello jcconf
hello jcconf
hello jcconf
```

之後我們也可以直接把他打包成zip或是tar檔案丟到我們的server上，或是方便拿去其他地方發佈。產生的檔案會放在`/build/distributions`

```
> gradle distZip
> gradle distTar
```

