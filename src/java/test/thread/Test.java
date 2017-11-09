package test.thread;

public class Test {

	public static void main(String[] args) {
		ExtendThread thread1 = new ExtendThread();
		ExtendThread thread2 = new ExtendThread();
		
		thread1.start();
		thread2.start();
	}
	


	
}
