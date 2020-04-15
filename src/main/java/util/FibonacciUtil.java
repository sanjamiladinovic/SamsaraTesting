package util;

import java.util.ArrayList;
import util.FibonacciUtil;
import java.util.List;

public class FibonacciUtil {

	int noOfElements = 6;
	List<Integer> fibonacciSequence = getFibonacciNum(noOfElements);

	public static List<Integer> getFibonacciNum(int fibonacciWantedNumberOfDigits) {

		List<Integer> fibonacciNums = new ArrayList<>();
		fibonacciNums.add(0);
		if (fibonacciWantedNumberOfDigits == 1) {
			return fibonacciNums;
		}

		fibonacciNums.add(1);
		for (int i = 2; i < fibonacciWantedNumberOfDigits; i++) {

			int nextNumForArrey = fibonacciNums.get(i - 1) + fibonacciNums.get(i - 2);//iz liste uzeti i-ti clan minus 1, i i-ti clan minuas 2
			fibonacciNums.add(nextNumForArrey);

		}
		return fibonacciNums; 

	}
}
//  public static void main(String[] args) {
//    int noOfElements = 8;

//    // Prvi nacin bez rekurzije
//    List<Integer> fibonacciSequence = getFibonacciSequence(noOfElements);
//    System.out.println(fibonacciSequence);
//
//    // Drugi nacin sa rekurzijom
//    List<Integer> fibonacci2 = new ArrayList<>();
//    for (int i = 1; i <= noOfElements; i++) {
//      fibonacci2.add(getNthNumberRecursion(i));
//    }
//    System.out.println(fibonacci2);
//  }
//
//  private static List<Integer> getFibonacciSequence(int numberOfElements) {

//    List<Integer> fibonacciSequence = new ArrayList<>();

//    // Dodati u listu 0 i 1 jer pravilo F(n) = F(n-1) + F(n-2) vazi samo za brojeve
//    // vece od 2 https://en.wikipedia.org/wiki/Fibonacci_number

//    fibonacciSequence.add(0);
//    if (numberOfElements == 1) {

//      // ako je zeljeni broj elemenata 1 odmah vratiti listu

//      return fibonacciSequence;
//    }
//    fibonacciSequence.add(1);
//
//    for (int i = 2; i < numberOfElements; i++) {
//      int nextFibonacci = fibonacciSequence.get(i - 1) + fibonacciSequence.get(i - 2);
//      fibonacciSequence.add(nextFibonacci);
//    }
//
//    return fibonacciSequence;
//  }
//
//  private static int getNthNumberRecursion(int n) {
//    if (n == 1) {
//      return 0;
//    }
//    if (n == 2) {
//      return 1;
//    }
//    return getNthNumberRecursion(n - 1) + getNthNumberRecursion(n - 2);
//  }
//}
