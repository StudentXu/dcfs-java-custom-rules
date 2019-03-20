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
import org.sonar.java.model.declaration.VariableTreeImpl;
import org.sonar.java.model.expression.MemberSelectExpressionTreeImpl;
import org.sonar.java.model.expression.MethodInvocationTreeImpl;
import org.sonar.java.model.expression.TypeCastExpressionTreeImpl;
import org.sonar.java.model.statement.ExpressionStatementTreeImpl;
import org.sonar.plugins.java.api.JavaFileScanner;
import org.sonar.plugins.java.api.JavaFileScannerContext;
import org.sonar.plugins.java.api.tree.BaseTreeVisitor;
import org.sonar.plugins.java.api.tree.MethodTree;

@Rule(key = "DontUserSaveData")
public class DontUserSaveDataRule extends BaseTreeVisitor implements JavaFileScanner {

  private JavaFileScannerContext context;

  protected static final String COMPANY_NAME = "saveData";

  @Override
  public void scanFile(JavaFileScannerContext context) {
    this.context = context;
    scan(context.getTree());
    // System.out.println(PrinterVisitor.print(context.getTree()));
  }

  @Override
  public void visitMethod(MethodTree tree) {
	  VariableTreeImpl child0 = (VariableTreeImpl) tree.block().body().get(0);
	  VariableTreeImpl child1 = (VariableTreeImpl) tree.block().body().get(1);
	  VariableTreeImpl child2 = (VariableTreeImpl) tree.block().body().get(2);
	  ExpressionStatementTreeImpl child3 = (ExpressionStatementTreeImpl) tree.block().body().get(3);
	  ExpressionStatementTreeImpl child4 = (ExpressionStatementTreeImpl) tree.block().body().get(4);
	  ExpressionStatementTreeImpl child5 = (ExpressionStatementTreeImpl) tree.block().body().get(5);
	  
	  MethodInvocationTreeImpl topChild5 = (MethodInvocationTreeImpl)child5.getChildren().get(0);
	  MemberSelectExpressionTreeImpl memberSelectExpressionTreeImpl= (MemberSelectExpressionTreeImpl) topChild5.getChildren().get(0);
	  String updateAtomData = memberSelectExpressionTreeImpl.getChildren().get(2).toString();
	  String MethodName1 = child0.getChildren().get(1).firstToken().text();
	  String MethodName2 = child0.getChildren().get(2).firstToken().text();
	  String MethodName3 = child0.getChildren().get(3).firstToken().text();
	  TypeCastExpressionTreeImpl tree4 = (TypeCastExpressionTreeImpl) child0.getChildren().get(4);
	  MethodInvocationTreeImpl tree4Child = (MethodInvocationTreeImpl) tree4.getChildren().get(4);
	  String getRequestAtomData = tree4Child.getChildren().get(0).lastToken().text();
	  String MethodName5 = child0.getChildren().get(5).firstToken().text();
	  
	  if(MethodName1.equals("CompositeData")&& getRequestAtomData.equals("getRequestAtomData") && updateAtomData.equals("updateAtomData")){
		  context.reportIssue(this, tree, "我");
	  }

	  if (tree.simpleName().name().toUpperCase().contains(COMPANY_NAME.toUpperCase())) {
      //context.reportIssue(this, tree, "我");
    }
	  
    super.visitMethod(tree);
    
  }

}
