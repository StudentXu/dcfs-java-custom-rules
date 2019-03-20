/**
 *This file is the sample code against we run our unit test.
 *It is placed src/test/files in order to not be part of the maven compilation.
 **/
class AvoidBrandInNamesCheck {

  int aField;

  public void methodWithMYCOMPANY() { 
/*	  if(null==datamodel.getControlValueString("EFFECT_DATE")||datamodel.getControlValueString("EFFECT_DATE").equals("")){
			return;
		}*/
	  if(datamodel.getControlValueString("CLOSE_TYPE")==null||datamodel.getControlValueString("CLOSE_TYPE").equals("")){ // Noncompliant {{重复取值1}}
		  
	  }
  }

}


