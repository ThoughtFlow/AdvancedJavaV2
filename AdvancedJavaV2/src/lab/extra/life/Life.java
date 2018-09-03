package lab.extra.life;

import java.util.Random;
import java.util.function.Function;
import java.util.stream.IntStream;

public class Life
{
    public static double execute(Function<boolean[][], boolean[][]> function, String functionName, boolean[][] firstGeneration, int generationsToSimulate)
    {
        long start = System.currentTimeMillis();
        View view = new View(functionName, firstGeneration, 4);
        boolean[][] nextGeneration = firstGeneration;
        for (int generationCount = 0; generationCount < generationsToSimulate; ++generationCount)
        {
            nextGeneration = function.apply(nextGeneration);
            view.displayNextGeneration(nextGeneration, generationCount);
        }

        return (System.currentTimeMillis() - start) / 1000.0;
    }
    
    public static void main(String[] args)
    {
    	int size;
    	int generations;
    	
    	try {
           size = Integer.parseInt(args[0]);
    	}
    	catch (Throwable e) {
    		// default to 100
    		size = 100;
    	}
    	
    	try {
    		generations = Integer.parseInt(args[1]);
    	}
    	catch (Throwable e) {
    		// Default to 1000
    		generations = 1000;
    	}
    	
    	System.out.println("Size: " + size + " generations: " + generations);
    	
        boolean[][] firstGeneration = new boolean[size][size];
        Random r = new Random();
        int finalSize = size;
        IntStream.rangeClosed(0, size - 1).forEach(y -> IntStream.rangeClosed(0, finalSize - 1).forEach(x -> firstGeneration[y][x] = Math.abs(r.nextInt()) % 2 == 0));
        	
        // You can provide your own function to calculate the next generation of cells. Simply write a function of type: 
        // Function<boolean[][], boolean[][]> whereby the input is an array of cells representing life (true) or no life (false) and the output is the next generation.
        System.out.println(execute(FunctionalLife::getNextGenerationFunctionalPlus, "Functional Parallel Plus", firstGeneration, generations));
        System.out.println(execute(FunctionalLife::getNextGenerationParallel, "Functional Parallel", firstGeneration, generations));
        System.out.println(execute(FunctionalLife::getNextGenerationSerial, "Functional Serial", firstGeneration, generations));
        System.out.println(execute(ImperativeLife::getNextGeneration, "Imperative", firstGeneration, generations));
    }
}
