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
import org.sonar.plugins.java.api.tree.BinaryExpressionTree;
import org.sonar.plugins.java.api.tree.IfStatementTree;
import org.sonar.plugins.java.api.tree.MemberSelectExpressionTree;
import org.sonar.plugins.java.api.tree.MethodInvocationTree;
import org.sonar.plugins.java.api.tree.Tree.Kind;
import org.sonar.plugins.java.api.tree.UnaryExpressionTree;

@Rule(key = "DontRepeatedValueTwo")
public class DontRepeatedValueTwoRule extends BaseTreeVisitor implements JavaFileScanner {

	private JavaFileScannerContext context;

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
	 * @date 2017-6-23
	 * @description This scanning plug-in can greatly improve code
	 *              specifications
	 */

	@Override
	public void visitIfStatement(IfStatementTree tree) {
		String getStruct1 = "";
		String getStruct2 = "";
		String str1 = "";
		String str2 = "";
		try {
		if (tree.condition().is(Kind.CONDITIONAL_AND)) {
			BinaryExpressionTree binaryExpressionTree = (BinaryExpressionTree) tree.condition();
			if (binaryExpressionTree.leftOperand().is(Kind.METHOD_INVOCATION)) {
				MethodInvocationTree binaryTree = (MethodInvocationTree) binaryExpressionTree.leftOperand();
				if (binaryTree.methodSelect().is(Kind.MEMBER_SELECT)) {
					MemberSelectExpressionTree memberSelectExpressionTree = (MemberSelectExpressionTree) binaryTree
							.methodSelect();
					if (memberSelectExpressionTree.expression().is(Kind.METHOD_INVOCATION)) {
						MethodInvocationTree methodTree = (MethodInvocationTree) memberSelectExpressionTree
								.expression();
						MemberSelectExpressionTree memberTree = (MemberSelectExpressionTree) methodTree.methodSelect();
						getStruct1 = memberTree.lastToken().text();

						Arguments argumentListTree = (Arguments) methodTree.arguments();
						if (argumentListTree.size() > 0) {
							str1 = argumentListTree.get(0).firstToken().text();
						}
					}

				}
			}

			if (binaryExpressionTree.rightOperand().is(Kind.LOGICAL_COMPLEMENT)) {
				UnaryExpressionTree binaryTree = (UnaryExpressionTree) binaryExpressionTree.rightOperand();
				if (binaryTree.expression().is(Kind.METHOD_INVOCATION)) {
					MethodInvocationTree methodTree = (MethodInvocationTree) binaryTree.expression();
					Arguments argumentListTree = (Arguments) methodTree.arguments();
					if (argumentListTree.size() > 0) {
						if (argumentListTree.get(0).is(Kind.METHOD_INVOCATION)) {
							MethodInvocationTree methodTree2 = (MethodInvocationTree) argumentListTree.get(0);
							methodTree2.methodSelect().kind();
							if (methodTree2.methodSelect().is(Kind.MEMBER_SELECT)) {
								MemberSelectExpressionTree memberSelectExpressionTree = (MemberSelectExpressionTree) methodTree2
										.methodSelect();
								memberSelectExpressionTree.expression().kind();
								if (memberSelectExpressionTree.expression().is(Kind.METHOD_INVOCATION)) {
									MethodInvocationTree methodInvocationTree = (MethodInvocationTree) memberSelectExpressionTree
											.expression();
									if (methodInvocationTree.methodSelect().is(Kind.MEMBER_SELECT)) {
										MemberSelectExpressionTree MemberSelectTree = (MemberSelectExpressionTree) methodInvocationTree
												.methodSelect();
										if (MemberSelectTree.expression().is(Kind.METHOD_INVOCATION)) {
											MethodInvocationTree methodTree3 = (MethodInvocationTree) MemberSelectTree
													.expression();
											if (methodTree3.methodSelect().is(Kind.MEMBER_SELECT)) {
												MemberSelectExpressionTree methodTree4 = (MemberSelectExpressionTree) methodTree3
														.methodSelect();
												getStruct2 = methodTree4.lastToken().text();
											}
											if (methodTree3.arguments().is(Kind.ARGUMENTS)) {
												Arguments arguments = (Arguments) methodTree3.arguments();
												//
												if (arguments.size() > 0) {
													str2 = arguments.get(0).firstToken().text();
												}

											}

										}
									}
								}
							}

						} else {
							if (methodTree.methodSelect().is(Kind.MEMBER_SELECT)) {
								MemberSelectExpressionTree memberSelectExpressionTree = (MemberSelectExpressionTree) methodTree
										.methodSelect();
								if (memberSelectExpressionTree.expression().is(Kind.METHOD_INVOCATION)) {
									MethodInvocationTree methodTree2 = (MethodInvocationTree) memberSelectExpressionTree
											.expression();
									MemberSelectExpressionTree memberTree2 = (MemberSelectExpressionTree) methodTree
											.methodSelect();
									if (memberTree2.expression().is(Kind.METHOD_INVOCATION)) {
										MethodInvocationTree methodInvocationTree = (MethodInvocationTree) memberTree2
												.expression();
										if (methodInvocationTree.methodSelect().is(Kind.MEMBER_SELECT)) {
											MemberSelectExpressionTree memberSelectExpressionTree2 = (MemberSelectExpressionTree) methodInvocationTree
													.methodSelect();
											if (memberSelectExpressionTree2.expression().is(Kind.METHOD_INVOCATION)) {
												MethodInvocationTree methodTree3 = (MethodInvocationTree) memberSelectExpressionTree2
														.expression();

												if (methodTree3.methodSelect().is(Kind.MEMBER_SELECT)) {
													MemberSelectExpressionTree memberSelectExpressionTree3 = (MemberSelectExpressionTree) methodTree3
															.methodSelect();

													if (memberSelectExpressionTree3.expression()
															.is(Kind.METHOD_INVOCATION)) {
														MethodInvocationTree methodInvocationTree2 = (MethodInvocationTree) memberSelectExpressionTree3
																.expression();
														if (methodInvocationTree2.arguments().is(Kind.ARGUMENTS)) {
															Arguments arguments = (Arguments) methodInvocationTree2
																	.arguments();
															if (arguments.size() > 0) {
																str2 = arguments.get(0).firstToken().text();
															}

														}

														if (methodInvocationTree2.methodSelect()
																.is(Kind.MEMBER_SELECT)) {
															MemberSelectExpressionTree memberSelectExpressionTree4 = (MemberSelectExpressionTree) methodInvocationTree2
																	.methodSelect();
															getStruct2 = memberSelectExpressionTree4.lastToken().text();

														}

													}

												}

											}
										}
									}

									Arguments argumentListTree2 = (Arguments) methodTree2.arguments();
									if (argumentListTree2.size() > 0) {
										str2 = argumentListTree2.get(0).firstToken().text();
									}
								}

							}
						}

					}

				}

			}

		}

		if (getStruct1 != "" && str1 != "") {
			if ((getStruct1.equals(getStruct2)) && (str1.equals(str2))) {
				context.reportIssue(this, tree, "重复取值2");
			}
		}

		super.visitIfStatement(tree);
		}
		catch(Exception e){
			e.printStackTrace();    					 
		}
	}
}
