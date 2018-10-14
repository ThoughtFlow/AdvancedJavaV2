package lab11.init;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lab.util.Util;

@SuppressWarnings("unused")
public class StreamGrep {

	private static long grepDashC(Stream<String> in, String upperCaseSearchWord) {

		// Implement this
		return 0;
	}

	private static List<String> grepCollect(Stream<String> in, String upperCaseSearchWord) {

		// Accumulate the strings via collect
		return in.map(String::toUpperCase).filter(s -> s.contains(upperCaseSearchWord))
				.collect(ArrayList<String>::new, ArrayList<String>::add, ArrayList<String>::addAll);
	}

	public static void main(String... args) {

		final String wordSearch = "JAVA";
		final String url = "http://www.oracle.com/technetwork/java/index.html";
		
		try (BufferedReader bufferedReader = Util.getReader(url)) {
			System.out.println("Using grepDashCWithReduce - Found: " + grepDashC(bufferedReader.lines(), wordSearch));
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		
		try (BufferedReader bufferedReader = Util.getReader(url)) {
			System.out.println("Using grepCollect - found :");
			grepCollect(bufferedReader.lines(), wordSearch).forEach(System.out::println);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}		
}