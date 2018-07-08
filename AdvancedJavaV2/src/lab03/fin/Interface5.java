package lab03.fin;

@FunctionalInterface
public interface Interface5 {

	public boolean isEqualToTen(int a);
	
	default boolean isNotEqualTo10(int a) {
		return !isEqualToTen(a);
	}
}
