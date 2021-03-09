package JavaIQ;

public class FibonacciSeries {

	public static void main(String[] args) {
		
		int a = 0;
		int b = 1;
		int c = 1;
		System.out.println("0,1,1,2,3,5,8,13...");

		for (int i = 0; i < 8; i++) {

			System.out.println(a + ",");
			a = b;
			b = c;
			c = a + b;
		}
	}
}
