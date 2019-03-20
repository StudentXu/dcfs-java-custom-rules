class DontRepeatedValueThreeCheck {

  public void test() {
	  String phone = datamodel.getControlValueString("COMMISSION_TEL_NO")==null ? "" : datamodel.getControlValueString("COMMISSION_TEL_NO");// Noncompliant {{重复取值3}}
  }
}
