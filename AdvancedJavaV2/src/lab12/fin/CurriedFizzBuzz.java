package lab12.fin;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import lab.util.BiHolder;

public class CurriedFizzBuzz {


	private static List<String> getFizzBuzzListWithCurrying(int start, int end) {

		// Using currying to define the modulo function once only.
		BiFunction<String, Integer, UnaryOperator<BiHolder<Integer, String>>> moduloFunction = 
				(fizzBuzz, modulo) -> 
		           h -> h.setU(Math.floorMod(h.getT(), modulo) == 0 ? h.getU() + fizzBuzz : h.getU());

		return IntStream.rangeClosed(start, end).
				mapToObj(i -> new BiHolder<Integer, String>(i, "")).
				map(h -> moduloFunction.apply("Fizz", 3).apply(h)).
				map(h -> moduloFunction.apply("Buzz", 5).apply(h)).
				filter(h -> h.getU().length() > 0).
				map(h -> h.getT() + " " + h.getU()).
				collect(Collectors.toList());
	}

	public static void main(String... args) {
		getFizzBuzzListWithCurrying(1, 100).forEach(System.out::println);
	}
}
