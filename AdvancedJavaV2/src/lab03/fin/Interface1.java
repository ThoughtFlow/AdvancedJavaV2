package lab03.fin;

@FunctionalInterface
public interface Interface1 {

	public void printSquareOfA(int a);
	
	default void PrettyPrintSquareOfA(int a) {
		System.out.print("The square of " + a + " is ");
		printSquareOfA(a);
	}
}
