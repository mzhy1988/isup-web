package test.singleton;
/**
 * 饿汉模式，天生线程安全，类初始化时，已经自行实例化
 * @author Administrator
 *
 */
public class SingletonHungry {

	private SingletonHungry(){

	}
	private static final SingletonHungry instance = new SingletonHungry();

	public static SingletonHungry getInstance(){
		return instance;
	}
}
