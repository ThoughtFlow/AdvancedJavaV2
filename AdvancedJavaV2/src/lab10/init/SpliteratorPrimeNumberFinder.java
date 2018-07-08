package lab10.init;

import java.util.ArrayList;
import java.util.List;
import java.util.Spliterator;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class SpliteratorPrimeNumberFinder {

	private static final int LIST_SIZE = 1000000;
	
	@SuppressWarnings("unused")
	private static class PrimeFinder implements Callable<Integer> {

		private final Spliterator<Integer> spliterator;

		public PrimeFinder(Spliterator<Integer> spliterator) {
			this.spliterator = spliterator;
		}
		
		@Override
		public Integer call() {
			
			// Implement this
			return 0;
		}
	}
	
	private static List<Spliterator<Integer>> getSpliterators(List<Integer> integerList) {
		
		// Implement this
		return new ArrayList<Spliterator<Integer>>();
	}	
	
	private static int countPrimes(List<PrimeFinder> primeFinders, ExecutorService pool) throws InterruptedException, ExecutionException {

		List<Future<Integer>> futures = pool.invokeAll(primeFinders);
		int totalPrimesFound = 0;
		for (Future<Integer> nextFuture : futures) {
			totalPrimesFound += nextFuture.get();
		}

		return totalPrimesFound;
	}
	
	private static int execute() {
		int totalPrimesFound;
		ExecutorService pool = Executors.newFixedThreadPool(4);
		
		// Populate the list of integers
		List<Integer> integerList = new ArrayList<>();
		for (int index = 0; index < LIST_SIZE; ++index) {
			integerList.add(index);
		}
		
		List<PrimeFinder> primeFinders = new ArrayList<>();
		for (Spliterator<Integer> next : getSpliterators(integerList)) {
			primeFinders.add(new PrimeFinder(next));
		}

		try {
			totalPrimesFound = countPrimes(primeFinders, pool);
		}
		catch (InterruptedException | ExecutionException exception) {
			totalPrimesFound = 0;
		}
		finally {
			pool.shutdown();
		}	
		
		return totalPrimesFound;
	}
	
	public static void main(String[] args)  {
		System.out.println("Total primes found: " + execute());
	}
}