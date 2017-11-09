package test.singleton;

//懒汉模式，线程不安全
public class Singleton {

	private static Singleton instance;

	private Singleton() {}

	public static Singleton getInstance(){
		if(instance == null){
			instance = new Singleton();
		}
		return instance;
	}
}
