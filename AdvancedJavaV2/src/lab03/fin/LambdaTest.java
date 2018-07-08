package lab03.fin;

public class LambdaTest {

	public static void main(String... args) {

		// Need to instantiate the lambda to access the default instance method.
		Interface1 i1 = x -> System.out.println(x * x);
		i1.PrettyPrintSquareOfA(3);

		// Need to instantiate the lambda to access the default instance method.
		Interface2 i2 = x -> x * x;
		System.out.println(i2.stringedSquareOfA(3));

		// No need to instantiate the lambda to access the static instance method.
		System.out.println(Interface3.defaultGetAxB(3, 3));

		// No need to instantiate the lambda to access the static instance method.
		System.out.println(Interface4.defaultGetPi());
		
		Interface5 i5 = x -> x == 10;
		System.out.println(i5.isNotEqualTo10(10));
	}
}
