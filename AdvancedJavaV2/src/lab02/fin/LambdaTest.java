package lab02.fin;

public class LambdaTest {

	private static int squareIt(int x) {
		return x * x;
	}
	
	public static void main(String... args) {

		// As-is
		Interface1 i1 = x -> {System.out.println(x * x);};
		i1.printSquareOfA(3);

		// Using static reference
		Interface2 i2 = LambdaTest::squareIt;
		System.out.println(i2.getSquareOfA(3));

		// Using static reference
		Interface3 i3 = Math::multiplyExact;
		System.out.println(i3.getAxB(3, 3));

		// Interface 4 cannot be converted into a constructor reference - need a new interface
		Interface4 i4 = () -> Math.PI;
		System.out.println(i4.getPi());

		// With new interface
		Interface4WithDouble i4WithDouble = Double::new;
		System.out.println(i4WithDouble.getPi(Math.PI));

		// Using instance reference
		Interface5 i5 = new Integer(10)::equals;
		System.out.println(i5.isEqualToTen(10));
	}
	
	@FunctionalInterface
	private static interface Interface4WithDouble {

		public Double getPi(double d);
	}
}
