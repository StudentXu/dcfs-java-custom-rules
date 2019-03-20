This example demonstrates how to write **Custom Rules** for the SonarQube Java Analyzer (aka SonarJava).

It requires to install **SonarJava** **4.8.0.9441** on your SonarQube 5.6+
# 插件模式

## [1]()版本说明

| 名称         | 版本       | 说明                       |
| ------------ | ---------- | -------------------------- |
| Eclipse Neon | JDK1.8     | 插件开发，支持JDK1.8,MAVEN |
| Sonarqube    | 5.6        | Sonarqube平台版本          |
| Java plugin  | 4.8.0.9441 | Java插件版本               |

 

## [2]()插件模式介绍

​         插件模式是使用sonarqube提供的插件机制，使用JAVA语言来编写自定义规则。编写完成后，打包jar文件，放在对应的插件目录即可使用。

插件模式与模板模式中XPATH实现相比有一定的优势。首先，这两者的开发难度都差不多，而插件在Eclipse中可以调试，这点极大的方便了开发。另外，插件在漏报、误报、维护性上都比XPath好，还支持自定义债务时间、严重级别、错误类型等，而这些都是Xpath模式缺少的。

模板模式的优势在于相对简单的需求可以使用模板快速实现。所以在实际使用中，建议模板可以快速实现的，使用模板实现，其他的需求使用插件模式实现。

## [3]()开发过程

### [3.1]()引言

插件的开发依赖于SonarQube JavaPlugin API。sonarqube官网提供了一个样例模板以便于快速高效的开发规则插件。

样例地址为：

 <https://github.com/SonarSource/sonar-custom-rules-examples/tree/master/java-custom-rules>。

插件为maven工程，因此需要进行相关MAVEN的配置。

Ø  首先下载Maven，环境变量中配置maven/bin路径。

Ø  在下载的java-custom-rule目录，运行mvnclean package，保证运行成功。

Ø  在Eclipse中 配置maven，Window-Preferences-Maven-Installations中增加Maven路径。

然后将样例源码导入进Eclipse中（Import-Maven-ExsitingMaven Projects）。导入后的目录结构如图所示：

​                              

接着，在该项目下进行Java插件的开发。开发结束后，使用mvn clean install命令将项目打包，并且复制到D:\onekeyjenkins\sonarqube-5.6.6\extensions\plugins目录下，重启Sonarqube。

### [3.2 ]()POM文件检查

POM 文件中可以修改<groupId>, <artifactId>, <version>, <name> ,<description>等内容来定义自己的版本号和插件名称。<java.plugin.version>需要保证和实际sonarqube运行的sonar-java-plugin-xxx.jar插件版本号XXX一致，或者小于该版本号。

如果依赖的是dcits-java-custom-rules开发，那么不需要进行任何修改。

 

### [3.3]() Rule开发

以下以teller9的不允许使用GlobalCache来演示实现Java插件化开发的完整过程：

 

##### 制定规则的五个文件

首先，需要明确开发一个完整的GlobalCache插件要建立5个新文件，这5个文件分别是：

1、 一个包含规则实现的规则类，该类是规则检查的核心：例如DontUseCacheRule.java（规则实现文件）；

2、 一个测试类，它包含规则的单元测试：例如DontUseCacheCheckTest.java（规则测试文件）；

3、 测试文件，包含正确和错误的代码，用于测试规则：例如DontUseCacheCheck.java（测试文件）

4、 规则参数文件，用于配置级别、类型等规则参数：DontUseCache_java.json（规则参数文件）

5、 资源文件，用于在页面上显示规则的具体描述：例如DontUseCache_java.html（规则描述文件）。

可以根据现有的例子来分别新建这5个文件，建立后再逐步开发。请注意按约定命名，例如测试用文件以Check结尾，测试类以CheckTest结尾，规则类以Rule结尾，资源文件为_java.json或_java.html。

 

##### DontUseCacheCheck.java

文件路径：src/test/files

插件以TDD模式进行开发，因此首先要做的是编写我们的规则将要针对的代码示例。在这个文件中，我们需要考虑我们的规则在分析过程中可能遇到的许多情况，并对需要违规的问题进行标记。例如：

**class** DontUseCacheCheck {

​       **public** **void** test() {

Map<String,Object>map = (Map<String,Object>)GlobalCache.getInstance().getCacheData();

​            GlobalCache.getInstance().setCacheData(map);// Noncompliant

​               }

}

​    该文件不要求编译，但是构造应该是正确的，否则会解析错误。

 

##### DontUseCacheCheckTest.java

文件路径: src/test/java的包：org.sonar.samples.java.checks

更新测试文件后，需要更新测试类来使用它，并将测试链接到我们的（尚未实现）规则。

新建DontUseCacheCheckTest.java文件，该类的写法固定，只是名称需要改变。例如：

 

**public** **class** DontUseCacheCheckTest {

​           @Test

​           **public** **void** detected() {

​       

   JavaCheckVerifier.*verify*("src/test/files/DontUseCacheCheck.java",**new**DontUseCacheRule());

 }

}

 

JavaCheckVerifier.verify("src/test/files/DontUseCacheCheck.java",new DontUseCacheRule());

DontUseCacheCheck.java表示调试的时候分析这个文件。

newDontUseCacheRule()表示使用的是规则实现类DontUseCacheRule。

 

##### DontUseCacheRule.java

文件路径: src/main/java的包：org.sonar.samples.java.checks

编写好测试文件之后，开始编写规则实现的文件。以下为样例：

@Rule(key= "DontUseCache")

**public** **class**DontUseCacheRule **extends** BaseTreeVisitor **implements** JavaFileScanner {

 

​    **private** JavaFileScannerContext context;

​    Stringparameter1 = "";

​    Stringparameter2 = "";

​    Stringmap1 = "";

​    Stringmap2 = "";

​    

​    @Override

​    **public** **void**scanFile(JavaFileScannerContext context) {

​         **this**.context = context;

​         scan(context.getTree());

​    }

​    @Override

​    **public** **void**visitConditionalExpression(ConditionalExpressionTree tree) {}

 

​    @Override

​    **public** **void**visitMemberSelectExpression(MemberSelectExpressionTree tree) {     

​         parameter1 =tree.identifier().toString();

​         **if** (parameter1.equals("getCacheData")){

​             **if**(tree.parent().parent().parent().is(Kind.*VARIABLE*)){

​                  VariableTreevariableTree = (VariableTree) tree.parent().parent().parent();

​                  **if**(variableTree.simpleName()!= **null**){

​                      map1 =variableTree.simpleName().toString();

​                  }            

​             }

 

​             **if** (tree.expression().is(Kind.*METHOD_INVOCATION*)){

​                  MethodInvocationTreemethodInvocationTree = (MethodInvocationTree) tree.expression();

​                  **if**(methodInvocationTree.methodSelect().is(Kind.*MEMBER_SELECT*)) {

​                      MemberSelectExpressionTreememberSelectExpressionTree = (MemberSelectExpressionTree) methodInvocationTree

​                               .methodSelect();

​                      parameter2 =memberSelectExpressionTree.lastToken().text();

​                      **if** (parameter1 != "" && parameter2 != "") {

​                          **if** ((parameter1.equals("getCacheData")|| (parameter1.equals("setCacheData")))

​                                   &&(parameter2.equals("getInstance"))){

​                               **if**(map1.equals(map2)){

​                                   //context.reportIssue(this, tree, "不允许使用缓存");

​                               }

​                               

​                          }

​                      }

​                      **super**.visitMemberSelectExpression(tree);

​                  }

​             }

​         }

​         

​        

​         **if** (parameter1.equals("setCacheData")){

​             **if**(tree.parent().parent().is(Kind.*EXPRESSION_STATEMENT*)){

​                  ExpressionStatementTreeexpressionStatementTree = (ExpressionStatementTree) tree.parent().parent();

​                  **if**(expressionStatementTree.expression().is(Kind.*METHOD_INVOCATION*)){

​                      MethodInvocationTreemethodInvocationTree = (MethodInvocationTree)expressionStatementTree.expression();

​                      **if**(methodInvocationTree.arguments().is(Kind.*ARGUMENTS*)){

​                          ArgumentsargumentListTree = (Arguments) methodInvocationTree.arguments();

​                          **if**(argumentListTree.size()>0){

​                               map2 =argumentListTree.get(0).toString();

​                          }

​                      }

​                  }   

​             }

​             **if**(tree.expression().is(Kind.*METHOD_INVOCATION*)) {

​                  MethodInvocationTreemethodInvocationTree = (MethodInvocationTree) tree.expression();

​                  **if**(methodInvocationTree.methodSelect().is(Kind.*MEMBER_SELECT*)) {

​                      MemberSelectExpressionTreememberSelectExpressionTree = (MemberSelectExpressionTree) methodInvocationTree

​                               .methodSelect();

​                      parameter2 =memberSelectExpressionTree.lastToken().text();

​                      **if** (parameter1 != "" && parameter2 != "") {

​                          **if** ((parameter1.equals("getCacheData")|| (parameter1.equals("setCacheData")))

​                                   &&(parameter2.equals("getInstance"))){

​                               //if(map1.equals(map2)){

​                                   context.reportIssue(**this**, tree, "不允许使用缓存");

​                               //}

​                               

​                          }

​                      }

​                      **super**.visitMemberSelectExpression(tree);

​                  }

​             }

​         }

​    }

}

可以仔细分析一下代码：

（1）public classDontUseCacheRule extends BaseTreeVisitor implements JavaFileScanner，该类的实现类和继承类都是固定的，不用改变，变的只有不同的类名。

（2）public voidscanFile(JavaFileScannerContext context)固定写法，不用更改，但该方法会在每一个扫描过程结束后执行，如果需要初始化变量，可以把初始化动作放在这里。

（3）public voidvisitMemberSelectExpression(MemberSelectExpressionTree tree)以MemberSelectExpressionTree作为开发起点。具体的业务逻辑就在这个方法内实现，其实，就是一些简单的Java判断代码，掌握几个要点就可以了。比如，if (parameter1.equals("getCacheData")){}语句，该语句判断parameter1是不是getCacheData，如果是继续下面的代码。

我们的判断依据就是从这段代码中获取符合规则的代码。首先，需要触发警报的字段就是当代码中包含了“getCacheData”，那么，我们只要通过parameter1 = tree.identifier().toString();取到parameter1，再判断parameter1是不是“getCacheData”就可以确定这个规则了。

（4）当实现了这点后，我们再观察DontUseCacheCheck.java里的代码，找到新的规则点：前后两个map变量需要保持一致，然后，最好的话，把setCacheData也作为一个判断依据。明白了这些，就可以继续下面的开发了。

**（5****）需要重点明白的一点是，使用Java****插件开发的话，必须借助于Debug****模式来开发，否则将会寸步难行。进入DontUseCacheCheckTest****，右键Debug As****，选择JUnit Test****即可调试。这样的话，通过断点来观察每一步地树形结构，来确定规则的编写。**

（6）context.reportIssue(this,tree, "不允许使用缓存");固定写法，用于产生警报。

（7）super.visitMemberSelectExpression(tree);固定写法，目前尚未明确用途，可能是继续监控的意思。

（6）其他要点：publicvoid visitMethod(MethodTree tree)从方法级别开始扫描树，一般情况下，可以把这个方法作为入口来进行插件的开发。但是，因为插件化开发也是基于树的，如果代码复杂的话，这颗树下可能有几十个几百个子节点，这样的话，开发起来就会非常困难。所以，建议从子节点直接找起，最大的方便开发。具体的做法可以参考BaseTreeVisitor里提供的节点。

PS：这里还有一个写法，不继承BaseTreeVisitor，而继承IssuableSubscriptionVisitor，具体可以参考样例中的AvoidUnmodifiableListRule.java。

在nodesToVisit确定返回哪一个tree，例如下面的例子，表示返回method tree。

@Override

publicList<Kind> nodesToVisit() {

  returnImmutableList.of(Kind.METHOD);

}

在visitNode中来确定具体的实现方法。

PS：sonarqube除了语法树直接提供的数据外，还提供了semanticAPI，例如可以提供一个方法的入参类型、出参类型、抛出的异常等等，例如：

import **org.sonar.plugins.java.api.semantic.Symbol.MethodSymbol**;

import **org.sonar.plugins.java.api.semantic.Type**;

@Override

public void visitNode(Treetree) {

  MethodTreemethod = (MethodTree) tree;

  if (method.parameters().size()== 1) {

​    MethodSymbolsymbol = method.symbol();

​    TypefirstParameterType = symbol.parameterTypes().get(0);

​    TypereturnType = symbol.returnType().type();

​    reportIssue(method.simpleName(), "Neverdo that!");

  }

}

 

 

##### DontUseCache_java.json

文件路径: src/main/resources/org/sonar/l10n/java/rules/squid

规则编写后，开发配置警报相关的参数。

例如：

{

  "title": "teller9-违规使用平台内部的执行缓存",   //规则名称

  "type": "BUG",   //规则类型

  "status": "ready",

  "remediation": {

​    "func":"Constant\/Issue",

​    "constantCost": "30min"    //债务时间

  },

  "tags": [

​    "cache",

​    "teller9"   //标签

  ],

  "defaultSeverity":"Blocker"   //违规级别

}

 

##### DontUseCache_java.html

文件路径: src/main/resources/org/sonar/l10n/java/rules/squid

用于配置代码规范描述，该描述显示在sonarqube中的规则的描述页面。例如：

<p>违规使用平台内部的执行缓存</p>

<h2>Code Example</h2>

<pre>

违规使用平台内部的执行缓存

例1

Map<String,Object> map =(Map<String,Object>)GlobalCache.getInstance().getCacheData();

例2

GlobalCache.getInstance().setCacheData(map);

</pre>

<h2>Compliant Solution</h2>

<pre>

推荐使用方式：

不允许使用GlobalCache

</pre>

​    

经过以上这几个步骤，一个完整的Java自定义插件就开发完成了。

### [3.4]()注册插件

文件路径: src/main/java的包：org.sonar.samples.java

插件开发完成后，需要进行激活，否则在页面上是无法找到并使用该规则的。

修改RulesList.java文件：

public static List<Class<? extends JavaCheck>>getJavaChecks() {

  return ImmutableList.<Class<? extends JavaCheck>>builder()

​    // other rules...

​    .add(DontUseCacheRule.class)  //增加新的规则

​    .build();

}

也可以修改资源库的名称（可选步骤，非必须）：

修改MyJavaRulesDefinition.java文件：

public void define(Context context) {

   NewRepository repository = context

​     .createRepository(REPOSITORY_KEY, Java.KEY)

​      .setName("MyCompanyCustom Repository ");  //修改资源库的名称，该名称显示在sonarqube代码规则右侧搜索页面。

   List<Class> checks = RulesList.getChecks();

   new RulesDefinitionAnnotationLoader().load(repository,Iterables.toArray(checks, Class.class));

 

   for (Class ruleClass : checks) {

​     newRule(ruleClass, repository);

​    }

   repository.done();

  }

 

### [3.5]()测试

开发调试：进入DontUseCacheCheckTest，右键Debug As，选择JUnit Test即可调试。这样的话，通过断点来观察每一步地树形结构，来确定规则的编写。

部署调试：

1、 进入Java代码目录，使用mvnclean install命令将项目打包

2、 复制到sonarqube-5.6.6\extensions\plugins目录下，重启Sonarqube

3、 将自定义规则添加到默认质量配置，自定义规则的选择：代码规则-语言选择JAVA，资源库选择：MyCompanyCustom Repository或者DCITS自定义Java规则（使用dcits-java-custom-rules开发）

4、 接着输入cmd进入命令行模式，进入需要调试的工程目录。比如，C:\work\manage>，输入sonar-scanner进行代码扫描

5、 待扫描结束后，可以在Sonarqube界面查看bug情况。

 

 

### [3.6]()参考

sonar插件开发资料

https://docs.sonarqube.org/display/PLUG/Writing+Custom+Java+Rules+101

https://github.com/SonarSource/sonar-java

https://jira.sonarsource.com/browse/SONARJAVA

 


