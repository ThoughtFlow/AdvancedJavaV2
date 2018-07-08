package lab.extra.threading;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

import lab.util.Util;

public class ForkJoinPrimeNumberFinder {

	private static final int K_SLICES = 1000;
	private static final int K = 1000;
	
	@SuppressWarnings("serial")
	private static class Worker extends RecursiveTask<Integer> {

		private final int startRange;
		private final int endRange;
		
		public Worker(int startRange, int endRange) {
			this.startRange = startRange;
			this.endRange = endRange;
		}

		@Override
		public Integer compute() {
			int count = 0;
			if (endRange - startRange > K) {
				int halfWay = (endRange - startRange) / 2 + startRange;
				ForkJoinTask<Integer> firstHalf = doSplit(startRange, halfWay);
				ForkJoinTask<Integer> secondHalf = doSplit(halfWay + 1, endRange);
				
				count = firstHalf.join() + secondHalf.join();
			}
			else {
				for (int primeCandidate = startRange; primeCandidate < endRange; ++primeCandidate)
					count += Util.isPrime(primeCandidate) ? 1 : 0;
			}
			
			return count;
		}
		
		private ForkJoinTask<Integer> doSplit(int startRange, int endRange) {
			Worker newWorker = new Worker(startRange, endRange);
			return newWorker.fork();
		}
	}
	
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		ForkJoinPool executor = (ForkJoinPool) Executors.newWorkStealingPool();
		ForkJoinTask<Integer> totalPrimesFound = executor.submit(new Worker(0, K * K_SLICES));
		
		System.out.println("Total primes found: " + totalPrimesFound.get());
		executor.shutdown();
	}
}