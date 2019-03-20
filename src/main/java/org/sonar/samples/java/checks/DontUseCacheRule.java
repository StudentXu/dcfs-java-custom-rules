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
import org.sonar.plugins.java.api.tree.ConditionalExpressionTree;
import org.sonar.plugins.java.api.tree.ExpressionStatementTree;
import org.sonar.plugins.java.api.tree.MemberSelectExpressionTree;
import org.sonar.plugins.java.api.tree.MethodInvocationTree;
import org.sonar.plugins.java.api.tree.Tree.Kind;
import org.sonar.plugins.java.api.tree.VariableTree;

@Rule(key = "DontUseCache")
public class DontUseCacheRule extends BaseTreeVisitor implements JavaFileScanner {

	private JavaFileScannerContext context;
	String parameter1 = "";
	String parameter2 = "";
	String map1 = "";
	String map2 = "";
	
	@Override
	public void scanFile(JavaFileScannerContext context) {
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
	public void visitConditionalExpression(ConditionalExpressionTree tree) {}

	@Override
	public void visitMemberSelectExpression(MemberSelectExpressionTree tree) {	
		try {
		parameter1 = tree.identifier().toString();
		if (parameter1.equals("getCacheData")) {
			if(tree.parent().parent().parent().is(Kind.VARIABLE)){
				VariableTree variableTree = (VariableTree) tree.parent().parent().parent();
				if(variableTree.simpleName() != null){
					map1 = variableTree.simpleName().toString();
				}			
			}

			if (tree.expression().is(Kind.METHOD_INVOCATION)) {
				MethodInvocationTree methodInvocationTree = (MethodInvocationTree) tree.expression();
				if (methodInvocationTree.methodSelect().is(Kind.MEMBER_SELECT)) {
					MemberSelectExpressionTree memberSelectExpressionTree = (MemberSelectExpressionTree) methodInvocationTree
							.methodSelect();
					parameter2 = memberSelectExpressionTree.lastToken().text();
					if (parameter1 != "" && parameter2 != "") {
						if ((parameter1.equals("getCacheData") || (parameter1.equals("setCacheData")))
								&& (parameter2.equals("getInstance"))) {
							if(map1.equals(map2)){
								//context.reportIssue(this, tree, "不允许使用缓存");
							}
							
						}
					}
					super.visitMemberSelectExpression(tree);
				}
			}
		}
		
		
		if (parameter1.equals("setCacheData")) {
			if(tree.parent().parent().is(Kind.EXPRESSION_STATEMENT)){
				ExpressionStatementTree expressionStatementTree = (ExpressionStatementTree) tree.parent().parent();
				if(expressionStatementTree.expression().is(Kind.METHOD_INVOCATION)){
					MethodInvocationTree methodInvocationTree = (MethodInvocationTree) expressionStatementTree.expression();
					if(methodInvocationTree.arguments().is(Kind.ARGUMENTS)){
						Arguments argumentListTree = (Arguments) methodInvocationTree.arguments();
						if(argumentListTree.size()>0){
							map2 = argumentListTree.get(0).toString();
						}
					}
				}	
			}
			if (tree.expression().is(Kind.METHOD_INVOCATION)) {
				MethodInvocationTree methodInvocationTree = (MethodInvocationTree) tree.expression();
				if (methodInvocationTree.methodSelect().is(Kind.MEMBER_SELECT)) {
					MemberSelectExpressionTree memberSelectExpressionTree = (MemberSelectExpressionTree) methodInvocationTree
							.methodSelect();
					parameter2 = memberSelectExpressionTree.lastToken().text();
					if (parameter1 != "" && parameter2 != "") {
						if ((parameter1.equals("getCacheData") || (parameter1.equals("setCacheData")))
								&& (parameter2.equals("getInstance"))) {
							//if(map1.equals(map2)){
								context.reportIssue(this, tree, "不允许使用缓存");
							//}
							
						}
					}
					super.visitMemberSelectExpression(tree);
				}
			}
		}

		}
		catch(Exception e){
			e.printStackTrace();    					 
			}
	}

}
