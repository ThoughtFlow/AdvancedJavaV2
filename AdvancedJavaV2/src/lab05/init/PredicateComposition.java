package lab05.init;

import java.util.Arrays;
import java.util.function.Predicate;

public class PredicateComposition
{
	
	private static void runTest(Predicate<Double[]> hasPassed) {
		
        // Test your lambdas
        // True: Passed all
        Double[] scores = (Double[]) Arrays.asList(.65, .90, .90, .90, .90, .90).toArray();
        System.out.println("Should be true: " + hasPassed.test(scores));

        // False: Not all passed
        scores = (Double[]) Arrays.asList(.59, .90, .90, .90, .90, .9).toArray();
        System.out.println("Should be false: " + hasPassed.test(scores));

        // False: C average - fail
        scores = (Double[]) Arrays.asList(.70, .70, .70, .70, .70, .70).toArray();
        System.out.println("Should be false: " + hasPassed.test(scores));

        // True: C average but aced last
        scores = (Double[]) Arrays.asList(.70, .70, .70, .70, .70, 1d).toArray();
        System.out.println("Should be true: " + hasPassed.test(scores));

        // True: Failed first by scored perfect on last
        scores = (Double[]) Arrays.asList(.59, .90, .90, .90, .90, 1d).toArray();
        System.out.println("Should be true: " + hasPassed.test(scores));
        
        // False: same as previous but missed a test
        scores = (Double[]) Arrays.asList(.59, .90, .90, .90, 0d, 1d).toArray();
        System.out.println("Should be false: " + hasPassed.test(scores));

        // False: Perfect but missed last - fail!
        scores = (Double[]) Arrays.asList(1d, 1d, 1d, 1d, 1d, 0d).toArray();
        System.out.println("Should be false: " + hasPassed.test(scores));
	}
	
    @SuppressWarnings("unused")
	public static void main(String[] args)
    {
    	// Implement these four independent lambdas here:
    	Predicate<Double[]> isAllPassed = l -> false;
    	Predicate<Double[]> isBAverage = l -> false;
    	Predicate<Double[]> isLastPerfect = l -> false;
    	Predicate<Double[]> isAnyMissed = l -> false;

    	// Compose the lambdas into one
    	Predicate<Double[]> hasPassed = l -> false;
    	
    	runTest(hasPassed);
    }
}
