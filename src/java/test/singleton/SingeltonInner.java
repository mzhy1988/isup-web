package test.singleton;

public class SingeltonInner {

	private SingeltonInner(){}

	private static class SingeInstance {
		private static final SingeltonInner INSTANCE = new SingeltonInner();
	}

	public static SingeltonInner getInstance(){
		return SingeInstance.INSTANCE;
	}
}
