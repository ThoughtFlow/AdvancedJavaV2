package lab10.fin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Spliterator;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;
import lab.util.Util;

public class SpliteratorPrimeNumberFinder {

	private static final int LIST_SIZE = 1000000;

	private static class PrimeFinder implements Callable<Integer> {

		private final Spliterator<Integer> spliterator;

		public PrimeFinder(Spliterator<Integer> spliterator) {
			this.spliterator = spliterator;
		}
		
		@Override
		public Integer call() {
			AtomicInteger counter = new AtomicInteger(0);
			
			// Make sure the spliterator is not null
			if (spliterator != null) {
				System.out.println(Thread.currentThread().getName() + ": size: " + spliterator.getExactSizeIfKnown());
			    spliterator.forEachRemaining(nextInt -> counter.updateAndGet(containedInt -> Util.isPrime(nextInt) ? ++containedInt : containedInt));
			}

			return counter.get();
		}
	}
	
	private static List<Spliterator<Integer>> getSpliterators(List<Integer> integerList) {
		// Split in 4 equal sizes
		// Careful - the trySplit may not work and return null
		Spliterator<Integer> spliterator1 = integerList.spliterator();
		Spliterator<Integer> spliterator2 = spliterator1.trySplit();
		Spliterator<Integer> spliterator3 = spliterator1 != null ? spliterator1.trySplit() : null;
		Spliterator<Integer> spliterator4 = spliterator2 != null ? spliterator2.trySplit() : null;
		
		List<Spliterator<Integer>> spliterators = Arrays.asList(spliterator1,
																spliterator2,
																spliterator3,
																spliterator4);
		
		return spliterators;
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