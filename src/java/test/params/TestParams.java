package test.params;

import java.util.ArrayList;

public class TestParams {

	public static void testOP(String name ,Object... params){

		for(int i=0;i<params.length;i++){
			System.out.println(params[i]);
		}
	}

	public static void main(String[] args) {
		ArrayList<Integer> l = new ArrayList<Integer>();
		l.add(1);
		l.add(2);
		l.add(3);
		l.add(4);
		TestParams.testOP("Test", 1,2,3);
		TestParams.testOP("Test", l.toArray());
	}

}
