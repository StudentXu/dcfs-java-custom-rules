class DontUserSaveDataCheck {

  public void test() {
	  String global_id_type_f=data.contains("GLOBAL_ID_TYPE_F")?data.getField("GLOBAL_ID_TYPE_F").strValue():""; // Noncompliant {{多余的contains判断}}
	  }
}
