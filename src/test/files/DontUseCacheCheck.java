class DontUseCacheCheck {

  public void test() {
	  Map<String,Object> map = (Map<String,Object>)GlobalCache.getInstance().getCacheData();
	  String a="123";
	  int b = 234;
	  GlobalCache.getInstance().setCacheData(map);// Noncompliant {{不允许使用缓存}}
  }

    public void testa() {
	  Map<String,Object> mapa = (Map<String,Object>)GlobalCache.getInstance().getCacheData();
	  String a="123";
	  int b = 234;
	  GlobalCache.getInstance().setCacheData(mapa);// Noncompliant {{不允许使用缓存}}
  }

}
