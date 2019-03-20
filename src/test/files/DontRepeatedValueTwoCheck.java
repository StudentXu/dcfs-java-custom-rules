/**
 *This file is the sample code against we run our unit test.
 *It is placed src/test/files in order to not be part of the maven compilation.
 **/
class AvoidBrandInNamesCheck {

  int aField;

  public void methodWithMYCOMPANY() { 
	  if(data.getStruct("TRAN_AMT1").contains("amt")&&!"".equals(data.getStruct("TRAN_AMT1").getField("amt").strValue())){ // Noncompliant {{重复取值2}}
	  }
  }

}


