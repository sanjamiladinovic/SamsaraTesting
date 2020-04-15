package util;

public class FactorialUtil {

	public static long factorialCalculation(int fromNumber) {
		int rezultatFactorial = 1;
		for (int i = 1; i <= fromNumber; i++) {

			rezultatFactorial = rezultatFactorial * i;
		}
		return rezultatFactorial;
	}

	public static long factorialWithRecursion(int number) {
		if (number >= 1)
			return number * factorialWithRecursion(number - 1);
		else
			return 1;

	}

}
