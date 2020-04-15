package util;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PrimeUtil {
	
	public static List<Integer> getFirstN(int wantedNumberOfPrimes) {
		
	
		List<Integer> primes = IntStream.range(2, Integer.MAX_VALUE).filter(n -> isPrime(n)).limit(wantedNumberOfPrimes) // Limit
				// the
				// number
				// of
				// primes
				.boxed().collect(Collectors.toList());
		return primes;
	}

	static boolean isPrime(int wantedNumberOfPrimes) {
		if (wantedNumberOfPrimes <= 2) {
			return wantedNumberOfPrimes == 2;
		} else
			return (wantedNumberOfPrimes % 2) != 0 && IntStream.rangeClosed(3, (int) Math.sqrt(wantedNumberOfPrimes))
					.filter(n -> n % 2 != 0).noneMatch(n -> (wantedNumberOfPrimes % n == 0));
	}

}

//DRUGI NACIN

//System.out.println("Uneti broj zeljenih prostih brojeva: ");
//Scanner sc = new Scanner(System.in);
//int n = sc.nextInt();
//
//// Lista prostih brojeva koje smo nasli
//List<Integer> primeNumbers = new ArrayList<>();
//// Ubaciti odmah u listu 2 jer f-ja za proveru primarnih brojeva ne radi za 2
//primeNumbers.add(2);
//
//// Sa pretragom krenuti od broja 3
//int i = 3;
//// Uslov za izlazak iz petlje je da je pronadjeni broj primarnih brojeva manji
//// od trazenog jer se u samoj petlji dodaje pronadjeni prost broj. Ako bi uslov
//// bio <=, onda bi se nasao jedan broj vise.
//while (primeNumbers.size() < n) {
//  if (isPrime(i)) {
//    primeNumbers.add(Integer.valueOf(i));
//  }
//  i++;
//}
//System.out.println(primeNumbers);
//}

/**
* Proverava da li je prosledjeni broj prost
*/
//private static boolean isPrime(int n) {
//// pretpostavimo da je broj prost
//boolean result = true;

//// prosledjeni broj podeliti sa svakim brojem od 2 do broja koji je upola manji
//// od prosledjenog broja. Nema smisla ici dalje jer rezultat deljenja ce sigurno
//// biti manji od 2
//for (int i = 2; i <= n / 2; i++) {
//  if (n % i == 0) {
//    // broj je deljiv sa trenutnom vrednosti "i", setovati resultat na false i izaci
//    // iz petlje
//    result = false;
//    break;
//  }
//}
//return result;
//}