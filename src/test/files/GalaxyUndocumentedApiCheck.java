package org.sonar.api.java;

public interface ISysHead extends IBean{// Noncompliant 
	 void setSeqNo(String seqNo);// Noncompliant
	 protected void doSomething() {    // Noncompliant
	 }
	 public void run() {              // Noncompliant
	 }
	 
	 private void doSomething() {    // Noncompliant
	 }
	 
	 @Override
	 public void run() {             // Compliant - has @Override annotation
	 }

}   


public abstract class Employee
{
	 public abstract void cry() { // Noncompliant
		 
	 }
	 
 public abstract void cry() { // Noncompliant
		 
	 }
	 public void run() {                // Compliant - not abstract
	 }
	 protected void doSomething() {    // Compliant - not abstract
	 }
	 
	 private void doSomething() {    // Compliant - not abstract
	 }
	
}





  