package org.foo.api.mypackage;
/**
 * some documentation
 */
public class UndocumentedApi { // Noncompliant {{Document this public class with @author.}}
  public String p; // Noncompliant [[sc=17;ec=18]]
  private String key; // Compliant - private

  public UndocumentedApi() { // Compliant - empty constructor
  }

  public UndocumentedApi(String key) { // Noncompliant
    this.key = key;
  }

  public void run() { // Noncompliant
  }

  public interface InnerUndocumentedInterface { // Noncompliant
  }

  /**
   * no violation, because documented
   * @author liujiey
   */
  public void run2() { 
  }

  public void setKey(String key) { // Compliant - setter
    this.key = key;
  }

  public String getKey() { // Compliant - getter
    return key;
  }

  @Override
  public String toString() { // Compliant - method with override annotation
    return key;
  }

  public static final int FOO = 0; // Compliant - static constant
  private static final int BAR = 0; // Compliant - private
  int a = 0; // Compliant

}

public enum FooEnum { // Noncompliant {{Document this public enum.}}
}

public interface Ainterface { // Noncompliant {{Document this public interface.}}
}

public @interface FooAnnotation { // Noncompliant {{Document this public annotation.}}
}

public class AClass { // Noncompliant {{Document this public class.}}

  public int a; // Noncompliant {{Document this public field.}}

  public A() { // Noncompliant {{Document this public constructor.}}
    System.out.println();
  }

}

/**
 * This is a Javadoc comment
 * @author liujiey
 */
public class MyClass<T> implements Runnable {    // Noncompliant {{Document the parameter(s): <T>}}

 private int status;                            // Compliant - not public

 /**
   * This is a Javadoc comment
   * @author liujiey
   */
 public String message;                         // Compliant - well documented

 public MyClass() {                             // Noncompliant
   this.status = 0;
 }

 public void setStatus(int status) {            // Compliant - setter
   this.status = status;
 }

 @Override
 public void run() {                            // Compliant - has @Override annotation
 }

 protected void doSomething() {                 // Compliant - not public
 }

 /**
   * @param value ...
   */
 public void doSomething(int value) {            // Noncompliant {{Document this public method with @author.}}
 }

 /**
  * @return foo
  * @author liujiey
   */
 public int doSomething(int value) {            // Noncompliant {{Document the parameter(s): value}}
   return value;
 }

 /** plop
  * @author liujiey
  *  */
 public int doSomething() {                     // Noncompliant {{Document this method return value.}}
   return value;
 }
}

/**
 */
interface FooInterface {
  /**
   * @author liujiey
   * void. */
  void foo(); // Compliant

  /**
   * @author liujiey
   * bar. */
  int foo(); // Noncompliant

  /**
   * @return
   * @author liujiey
   */
  int foo(); // Compliant

  /** plop.
   * @author liujiey
   */
  void foo(int a); // Noncompliant {{Document the parameter(s): a}}
}

/**
 * doc.
 * @author liujiey
 */
public class FooClass {
  /** constructor.
   * @author liujiey
   */
  public FooClass(int a) { // Noncompliant {{Document the parameter(s): a}}
    System.out.println(a);
  }

  /**
   * @param a
   * @author liujiey
   */
  public FooClass(int a) { // Compliant
    System.out.println(a);
  }
}

private class FooPrivate { // Compliant - non pubic
}

class FooPackage { // Compliant - non public
}

/** Documented.
 * @author liujiey
 */
public class Foo { // Compliant

  public int foo(int a, int b, int c) { // Noncompliant
    return 0;
  }


  public int foo(int a, int b, int c) { // Noncompliant {{Document this public method.}}
    return 0;
  }

  /**
    * @param <T> foo
    * @author liujiey
    */
  public <T> void foo() { // Compliant - does not return anything
  }

  public <T> void foo() { // Noncompliant {{Document this public method.}}
  }

  /**
   * @param <T> foo
   * @author liujiey
   */
  public <T> int foo() { // Noncompliant {{Document this method return value.}}
  }

  /**
   * @param <T> foo
   * @return foo
   * @author liujiey
   */
  public <T> int foo() { // Compliant
  }

  public void getThisThingDone() { //false negative this is interpreted as a getter.
  }
}
/**
 * */
public interface bar { // Noncompliant {{Document this public interface.}}
  /**
  * @param <A>  the annotation type
  * @param annotationType  the <tt>Class</tt> object corresponding to
  *          the annotation type
  * @return the annotation of this declaration having the specified type
  *
  * @see #getAnnotationMirrors()
  * @author liujiey
  */
  <A extends Annotation> A getAnnotation(Class<A> annotationType);
  static class A{} // Noncompliant {{Document this public class.}}
  public int i = 0;

  /**
   * documentMethod.
   * @author liujiey
   */
  default void method(){
    int j = 1;
  }
}

@interface nested{
  /**
   *
   */
  static final class DEFAULT {} // Noncompliant {{Document this public class.}}
  public int i = 0;
}
/**
 * Documented
 */
@Deprecated
public interface deprecatedInterface{
  /**
   * Doc
   */
  @Deprecated
  enum Location {
    CLASS_TREE;
  }

  public static final Object constant = new AnonymousClass(){
    public void undocumentedMethod(){};
  };

}

/**
 * Documented
 * @author liujiey
 */
public class MyRunner extends Foo {

  /**
   * {@inheritDoc}
   */
  public int foo(int a, int b, int c) { // Compliant
    return 0;
  }

  private interface Bar {
    void method();
  }

  

  /** Foo.
   * @author liujiey
   */
  public interface Foo {

    public foo(); // Noncompliant

  }

  @Target({METHOD})
  @Retention(RUNTIME)
  public @interface Transient { // Noncompliant [[sc=21;ec=30]]
      boolean value() default true; // Noncompliant
  }
}
class AnonymousInnerClass {
  Comparator<String> doJob(){
    return new Comparator<String>() { // anon-inner-class
      class Hello { // inner-class
        public void doJob() {
        }
      }

      public int compare(String o1, String o2) {
        return 0;
      }
    };
  }
}

class PublicConstructorOfNonPublicClass {
  public PublicConstructorOfNonPublicClass(int a){
  }
}

/**
 * Documented
 * @author liujiey
 */
class A {

  /**
   * Documented
   * @author liujiey
   */
  public void doSomething() { }

  /**
   * Documented
   * @author liujiey
   */
  public static class B extends A {
  }

}

@interface MyAnnotation {}

class UsesVisibleForTesting {

  @MyAnnotation
  public void doNothing() {} // Noncompliant
}

@Deprecated
public class DeprecatedAPI { //Compliant
  public void bar() {} // Noncompliant

  @Deprecated
  public void foo() {} // Compliant

  @org.foo.qix.Deprecated
  public void foo() {} // Noncompliant
}
