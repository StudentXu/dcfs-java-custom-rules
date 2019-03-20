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
import org.sonar.plugins.java.api.tree.BaseTreeVisitor;
import org.sonar.plugins.java.api.tree.LiteralTree;
import org.sonar.plugins.java.api.tree.ModifiersTree;
import org.sonar.plugins.java.api.tree.Tree.Kind;
import org.sonar.plugins.java.api.tree.VariableTree;

@Rule(key = "DontUseErrorValue")
public class DontUseErrorValueRule extends BaseTreeVisitor implements JavaFileScanner {

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
	 * @date 2017-6-26
	 * @description This scanning plug-in can greatly improve code
	 *              specifications
	 */

	@Override
	public void visitVariable(VariableTree tree) {
		String a = "";
		String b = "";
		try {
		if (tree.type() != null) {
			String parameterA = tree.type().toString();
			if (parameterA.equals("String")) {
				if(tree.initializer()!=null){
					if (tree.initializer().is(Kind.STRING_LITERAL) || tree.initializer().is(Kind.NULL_LITERAL)) {
						LiteralTree literalTree = (LiteralTree)tree.initializer();
						String literValue = literalTree.value();	
						if(literValue.equals('"'+""+'"') || literValue.equals("null")){
							 a = "1";
						}
					}
				}
	
				if(tree.modifiers()!=null){
					if (tree.modifiers().is(Kind.MODIFIERS)) {
						ModifiersTree modifiersTree = (ModifiersTree)tree.modifiers();
						if(modifiersTree.firstToken()!=null){
							String literValue = modifiersTree.firstToken().text();
							if(literValue.equals("private")){		
								b = "2";
							}
						}

					}
				}
				
			}
			
			if(a.equals("1") && b.equals("2")){
				context.reportIssue(this, tree, "错误的赋值");
			}
			
			super.visitVariable(tree);
			
		}
		}
		catch(Exception e){
			e.printStackTrace();    					 
			}
	}
}
