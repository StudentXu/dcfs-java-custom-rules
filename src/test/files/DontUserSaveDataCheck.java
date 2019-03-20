class DontUserSaveDataCheck {

  int aField;

  public void saveData() {
	  CompositeData add  = (CompositeData)datamodel.getRequestAtomData();
	  String date = DataUtils.getCalToShortStr();
	  String date1 = DataUtils.getCurryLongTime();
	  add.addField("DATE", DataFactory.getField(date));
	  add.addField("DATE1", DataFactory.getField(date1));
	  datamodel.updateAtomData(add);
  }



}
