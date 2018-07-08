package lab12.fin;

import java.util.function.Function;

public class CurriedStringConcat {

	public static void main(String... args) {
		Function<String, Function<String, Function<String, String>>> func = s -> t -> u -> s + t + u;
		
		System.out.println(func.apply("Currying").apply(" is").apply(" great!"));
	}
}
