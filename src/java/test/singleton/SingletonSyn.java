package test.singleton;

public class SingletonSyn {

	private static SingletonSyn instance;

	private SingletonSyn(){}

	public static SingletonSyn getInstance(){
		if(instance == null){
			synchronized(SingletonSyn.class){
				instance = new SingletonSyn();
			}
		}
		return instance;
	}
}
