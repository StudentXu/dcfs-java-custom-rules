/*
 * SonarQube Java Custom Rules Example
 * Copyright (C) 2016-2016 SonarSource SA
 * mailto:contact AT sonarsource DOT com
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */
package org.sonar.samples.java.checks;

import org.sonar.check.Rule;
import org.sonar.plugins.java.api.JavaFileScanner;
import org.sonar.plugins.java.api.JavaFileScannerContext;
import org.sonar.plugins.java.api.tree.Arguments;
import org.sonar.plugins.java.api.tree.BaseTreeVisitor;
import org.sonar.plugins.java.api.tree.MemberSelectExpressionTree;
import org.sonar.plugins.java.api.tree.MethodInvocationTree;
import org.sonar.plugins.java.api.tree.MethodTree;
import org.sonar.plugins.java.api.tree.Tree.Kind;
import org.sonar.plugins.java.api.tree.TypeCastTree;
import org.sonar.plugins.java.api.tree.VariableTree;

@Rule(key = "DontUseTotalData")
public class DontUseTotalDataRule extends BaseTreeVisitor implements JavaFileScanner {

	private JavaFileScannerContext context;
	String parameter1 = "";
	String parameter2 = "";
	String parameterA = "";
	String parameterB = "";
	String fun1 = "";
	String fun2 = "";
	String []funs = new String[100];

	@Override
	public void scanFile(JavaFileScannerContext context) {
		 parameter1 = "";
		 parameter2 = "";
		 parameterA = "";
		 parameterB = "";
		 fun1 = "";
		 fun2 = "";
		 funs = new String[100];
		this.context = context;

		// The call to the scan method on the root of the tree triggers the
		// visit of the AST by this visitor
		scan(context.getTree());

		// For debugging purpose, you can print out the entire AST of the
		// analyzed file
		// System.out.println(PrinterVisitor.print(context.getTree()));
	}

	/**
	 * Overriding the visitor method to implement the logic of the rule.
	 * 
	 * @param tree
	 *            AST of the visited method.
	 */

	/**
	 * @author wxb
	 * @version 1
	 * @date 2017-6-26
	 * @description This scanning plug-in can greatly improve code
	 *              specifications
	 */

	  @Override
	  public void visitMethod(MethodTree tree) {
	    scan(tree.modifiers());
	    scan(tree.typeParameters());
	    scan(tree.returnType());
	    scan(tree.parameters());
	    scan(tree.defaultValue());
	    scan(tree.throwsClauses());
	    scan(tree.block());
	  }
	 


	public String getFun(VariableTree tree) {
		if(tree.parent()!=null){
			if(tree.parent().is(Kind.METHOD)){
				MethodTree methodTree = (MethodTree) tree.parent();
				fun1 = methodTree.simpleName().toString();
				return fun1;
			}
		}else{
			return fun1;
		}
		
		if(tree.parent().parent()!=null){
			if(tree.parent().parent().is(Kind.METHOD)){
				MethodTree methodTree = (MethodTree) tree.parent().parent();
				fun1 = methodTree.simpleName().toString();
				return fun1;
			}
		}else{
			return fun1;
		}
		
		if(tree.parent().parent().parent()!=null){
			if(tree.parent().parent().parent().is(Kind.METHOD)){
				MethodTree methodTree = (MethodTree) tree.parent().parent().parent();
				fun1 = methodTree.simpleName().toString();
				return fun1;
			}
		}else{
			return fun1;
		}
		
		if(tree.parent().parent().parent().parent()!=null){
			if(tree.parent().parent().parent().parent().is(Kind.METHOD)){
				MethodTree methodTree = (MethodTree) tree.parent().parent().parent().parent();
				fun1 = methodTree.simpleName().toString();
				return fun1;
			}
		}else{
			return fun1;
		}

		if(tree.parent().parent().parent().parent().parent()!=null){
			if(tree.parent().parent().parent().parent().parent().is(Kind.METHOD)){
				MethodTree methodTree = (MethodTree) tree.parent().parent().parent().parent().parent();
				fun1 = methodTree.simpleName().toString();
				return fun1;
			}
		}else{
			return fun1;
		}
		if(tree.parent().parent().parent().parent().parent().parent()!=null){
			if(tree.parent().parent().parent().parent().parent().parent().is(Kind.METHOD)){
				MethodTree methodTree = (MethodTree) tree.parent().parent().parent().parent().parent().parent();
				fun1 = methodTree.simpleName().toString();
				return fun1;
			}
		}else{
			return fun1;
		}
		if(tree.parent().parent().parent().parent().parent().parent().parent()!=null){
			if(tree.parent().parent().parent().parent().parent().is(Kind.METHOD)){
				MethodTree methodTree = (MethodTree) tree.parent().parent().parent().parent().parent().parent().parent();
				fun1 = methodTree.simpleName().toString();
				return fun1;
			}
		}else{
			return fun1;
		}
		if(tree.parent().parent().parent().parent().parent().parent().parent().parent()!=null){
			if(tree.parent().parent().parent().parent().parent().parent().parent().parent().is(Kind.METHOD)){
				MethodTree methodTree = (MethodTree) tree.parent().parent().parent().parent().parent().parent().parent().parent();
				fun1 = methodTree.simpleName().toString();
				return fun1;
			}
		}else{
			return fun1;
		}
		if(tree.parent().parent().parent().parent().parent().parent().parent().parent().parent()!=null){
			if(tree.parent().parent().parent().parent().parent().parent().parent().parent().parent().is(Kind.METHOD)){
				MethodTree methodTree = (MethodTree) tree.parent().parent().parent().parent().parent().parent().parent().parent().parent();
				fun1 = methodTree.simpleName().toString();
				return fun1;
			}
		}else{
			return fun1;
		}
		if(tree.parent().parent().parent().parent().parent().parent().parent().parent().parent().parent()!=null){
			if(tree.parent().parent().parent().parent().parent().parent().parent().parent().parent().parent().is(Kind.METHOD)){
				MethodTree methodTree = (MethodTree) tree.parent().parent().parent().parent().parent().parent().parent().parent().parent().parent();
				fun1 = methodTree.simpleName().toString();
				return fun1;
			}
		}else{
			return fun1;
		}
		if(tree.parent().parent().parent().parent().parent().parent().parent().parent().parent().parent().parent()!=null){
			if(tree.parent().parent().parent().parent().parent().parent().parent().parent().parent().parent().parent().is(Kind.METHOD)){
				MethodTree methodTree = (MethodTree) tree.parent().parent().parent().parent().parent().parent().parent().parent().parent().parent().parent();
				fun1 = methodTree.simpleName().toString();
				return fun1;
			}
		}else{
			return fun1;
		}
		if(tree.parent().parent().parent().parent().parent().parent().parent().parent().parent().parent().parent().parent()!=null){
			if(tree.parent().parent().parent().parent().parent().parent().parent().parent().parent().parent().parent().parent().is(Kind.METHOD)){
				MethodTree methodTree = (MethodTree) tree.parent().parent().parent().parent().parent().parent().parent().parent().parent().parent().parent().parent();
				fun1 = methodTree.simpleName().toString();
				return fun1;
			}
		}else{
			return fun1;
		}
		return fun1;
		
	}
	
	public String getFun(MethodInvocationTree tree) {
		if(tree.parent()!=null){
			if(tree.parent().is(Kind.METHOD)){
				MethodTree methodTree = (MethodTree) tree.parent();
				fun2 = methodTree.simpleName().toString();
				return fun2;
			}
		}else{
			return fun2;
		}
		
		if(tree.parent().parent()!=null){
			if(tree.parent().parent().is(Kind.METHOD)){
				MethodTree methodTree = (MethodTree) tree.parent().parent();
				fun2 = methodTree.simpleName().toString();
				return fun2;
			}
		}else{
			return fun2;
		}
		
		if(tree.parent().parent().parent()!=null){
			if(tree.parent().parent().parent().is(Kind.METHOD)){
				MethodTree methodTree = (MethodTree) tree.parent().parent().parent();
				fun2 = methodTree.simpleName().toString();
				return fun2;
			}
		}else{
			return fun2;
		}
		
		if(tree.parent().parent().parent().parent()!=null){
			if(tree.parent().parent().parent().parent().is(Kind.METHOD)){
				MethodTree methodTree = (MethodTree) tree.parent().parent().parent().parent();
				fun2 = methodTree.simpleName().toString();
				return fun2;
			}
		}else{
			return fun2;
		}

		if(tree.parent().parent().parent().parent().parent()!=null){
			if(tree.parent().parent().parent().parent().parent().is(Kind.METHOD)){
				MethodTree methodTree = (MethodTree) tree.parent().parent().parent().parent().parent();
				fun2 = methodTree.simpleName().toString();
				return fun2;
			}
		}else{
			return fun2;
		}
		if(tree.parent().parent().parent().parent().parent().parent()!=null){
			if(tree.parent().parent().parent().parent().parent().parent().is(Kind.METHOD)){
				MethodTree methodTree = (MethodTree) tree.parent().parent().parent().parent().parent().parent();
				fun2 = methodTree.simpleName().toString();
				return fun2;
			}
		}else{
			return fun2;
		}
		if(tree.parent().parent().parent().parent().parent().parent().parent()!=null){
			if(tree.parent().parent().parent().parent().parent().parent().parent().is(Kind.METHOD)){
				MethodTree methodTree = (MethodTree) tree.parent().parent().parent().parent().parent().parent().parent();
				fun2 = methodTree.simpleName().toString();
				return fun2;
			}
		}else{
			return fun2;
		}
		if(tree.parent().parent().parent().parent().parent().parent().parent().parent()!=null){
			if(tree.parent().parent().parent().parent().parent().parent().parent().parent().is(Kind.METHOD)){
				MethodTree methodTree = (MethodTree) tree.parent().parent().parent().parent().parent().parent().parent().parent();
				fun2 = methodTree.simpleName().toString();
				return fun2;
			}
		}else{
			return fun2;
		}
		if(tree.parent().parent().parent().parent().parent().parent().parent().parent().parent()!=null){
			if(tree.parent().parent().parent().parent().parent().parent().parent().parent().parent().is(Kind.METHOD)){
				MethodTree methodTree = (MethodTree) tree.parent().parent().parent().parent().parent().parent().parent().parent().parent();
				fun2 = methodTree.simpleName().toString();
				return fun2;
			}
		}else{
			return fun2;
		}
		if(tree.parent().parent().parent().parent().parent().parent().parent().parent().parent().parent()!=null){
			if(tree.parent().parent().parent().parent().parent().parent().parent().parent().parent().parent().is(Kind.METHOD)){
				MethodTree methodTree = (MethodTree) tree.parent().parent().parent().parent().parent().parent().parent().parent().parent().parent();
				fun2 = methodTree.simpleName().toString();
				return fun2;
			}
		}else{
			return fun2;
		}
		if(tree.parent().parent().parent().parent().parent().parent().parent().parent().parent().parent().parent()!=null){
			if(tree.parent().parent().parent().parent().parent().parent().parent().parent().parent().parent().parent().is(Kind.METHOD)){
				MethodTree methodTree = (MethodTree) tree.parent().parent().parent().parent().parent().parent().parent().parent().parent().parent().parent();
				fun2 = methodTree.simpleName().toString();
				return fun2;
			}
		}else{
			return fun2;
		}
		if(tree.parent().parent().parent().parent().parent().parent().parent().parent().parent().parent().parent().parent()!=null){
			if(tree.parent().parent().parent().parent().parent().parent().parent().parent().parent().parent().parent().parent().is(Kind.METHOD)){
				MethodTree methodTree = (MethodTree) tree.parent().parent().parent().parent().parent().parent().parent().parent().parent().parent().parent().parent();
				fun2 = methodTree.simpleName().toString();
				return fun2;
			}
		}else{
			return fun2;
		}
		if(tree.parent().parent().parent().parent().parent().parent().parent().parent().parent().parent().parent().parent().parent()!=null){
			if(tree.parent().parent().parent().parent().parent().parent().parent().parent().parent().parent().parent().parent().parent().is(Kind.METHOD)){
				MethodTree methodTree = (MethodTree) tree.parent().parent().parent().parent().parent().parent().parent().parent().parent().parent().parent().parent().parent();
				fun2 = methodTree.simpleName().toString();
				return fun2;
			}
		}else{
			return fun2;
		}
		return fun2;
		
	}
	
	
	
	
	@Override
	public void visitVariable(VariableTree tree) {
		try {
		String temp = tree.type().toString();
		if (temp.equals("CompositeData")) {	
			if ((tree.initializer()!=null)&&tree.initializer().is(Kind.TYPE_CAST)) {
				TypeCastTree typeCastTree = (TypeCastTree) tree.initializer();
				if (typeCastTree.expression().is(Kind.METHOD_INVOCATION)) {
					MethodInvocationTree methodInvocationTree = (MethodInvocationTree) typeCastTree.expression();
					if (methodInvocationTree.methodSelect().is(Kind.MEMBER_SELECT)) {
						MemberSelectExpressionTree memberSelectExpressionTree = (MemberSelectExpressionTree) methodInvocationTree
								.methodSelect();
						String temp2 = memberSelectExpressionTree.lastToken().text();
						fun1 = getFun(tree);
						if(temp2.equals("getRequestAtomData")){
							
							parameterA = tree.simpleName().toString();
							parameter1 = memberSelectExpressionTree.lastToken().text();	
							//fun1 = tree.symbol().owner().name();
							for(int i=0;i<funs.length;i++){
								if(parameterA!=null){
									if(funs[i]==null){
										funs[i] = parameterA;
										return;
									}
								
								}
							}
							
						}
					}
				}

			}else
			{
				if ((tree.initializer()!=null)&&tree.initializer().is(Kind.METHOD_INVOCATION)) {
					MethodInvocationTree typeCastTree = (MethodInvocationTree) tree.initializer();
					typeCastTree.methodSelect().kind();
					if (typeCastTree.methodSelect().is(Kind.MEMBER_SELECT)) {
						MemberSelectExpressionTree methodInvocationTree = (MemberSelectExpressionTree) typeCastTree.methodSelect();
						if(methodInvocationTree.lastToken()!=null){		
							String temp2 = methodInvocationTree.lastToken().text();
							if(temp2.equals("getCompositeData")){
								//parameterA = tree.simpleName().toString();
								//parameter1 = methodInvocationTree.lastToken().text();
							}else{
								//return;
							}
						}
					}

				}
			}
		}
		}
		catch(Exception e){
			e.printStackTrace();    					 
			}
		//super.visitVariable(tree);
	}
	
	@Override
	public void visitMethodInvocation(MethodInvocationTree tree) {
		try {
		String temp = tree.firstToken().text();
		if (temp.equals("datamodel")) {
			
			if (tree.methodSelect().is(Kind.MEMBER_SELECT)) {
				MemberSelectExpressionTree memberSelectExpressionTree = (MemberSelectExpressionTree) tree
						.methodSelect();
				String temp3 = memberSelectExpressionTree.identifier().toString();
				if(temp3.equals("updateAtomData")){
					parameter2 = memberSelectExpressionTree.identifier().toString();
					if (tree.arguments().is(Kind.ARGUMENTS)) {
						Arguments arguments = (Arguments) tree.arguments();
						if (arguments.size() > 0) {
							parameterB = arguments.get(0).toString();
						}
					}
					fun2 = getFun(tree);
				}
			}

			if (parameter1.equals("getRequestAtomData") && parameter2.equals("updateAtomData")) {
				if(funs.length<2){
					if (parameterA.equals(parameterB)) {
						if(fun1.equals(fun2)){
							 context.reportIssue(this, tree, "用交易的全量数据更新交易数据");
							 //parameter1 = "";
							 parameter2 = "";
							 //parameterA = "";
							 parameterB = "";
							 //fun1 = "";
							 //fun2 = "";
						}

					}
				}else{
					for(int i=0; i<funs.length; i++){
						if(funs[i] != null){
							if(funs[i].toString().equals(parameterB)){
								if(fun1.equals(fun2)){
									 context.reportIssue(this, tree, "用交易的全量数据更新交易数据");
									 //parameter1 = "";
									 parameter2 = "";
									 //parameterA = "";
									 parameterB = "";
									 //fun1 = "";
									 //fun2 = "";
								}
							}
						}
					}
				}

			}

			//super.visitMethodInvocation(tree);
		}
		}
		catch(Exception e){
			e.printStackTrace();    					 
			}
	}
	
}
