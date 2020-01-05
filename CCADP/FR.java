package FuncionRecursiva;

public class FR {	
	public static  long Zero() {
		return 0;
	}
	
	public static long S(long x) {
		return x + 1;
	}
	
	public static long suma(long x, long y) {//suma de x, s(y)
		if(y == 0) {
			return x;
		} else {
			return S(suma(x,y-1));
		}
	}
	
	public static long prod(long x, long y) {//prod x, s(y)
		if(y == 0) {
			return Zero();
		} else {
			return suma(x,prod(x,y-1));
		}
	}
	
	public static long pow(long x, long y) {//pow x, s(y)
		if(y == 0) {
			return S(Zero());
		} else {
			return prod(x,pow(x,y-1));
		}
	}
	
	public static long factorial(long x) {
		if(x == 1) {
			return S(Zero());
		} else {
			return prod(x,factorial(x-1));
		}
	}
	
	public static void main(String[] argc) {
		System.out.println(Zero());
		System.out.println(S(0));
		System.out.println(suma(8,0));
		System.out.println(suma(17,4));
		System.out.println(prod(6,2));
		System.out.println(pow(4,2));
		System.out.println(factorial(6));
	}
	
}