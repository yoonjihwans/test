package example;

import java.util.Iterator;

public class ForExample {
	public static void main(String[] args) {
		// 본인 이름을 화면에 7번 출력하세요.
		// ex) 홍길동 홍길동 홍길동 홍길동 홍길동 홍길동 홍길동

		System.out.println("=========================================");
		// 1~10 범위의 정수 중 홀수를 화면에 출력하세요.
		// ex) 1 3 5 7 9

		System.out.println("=========================================");
		// 1~100 범위의 정수에서 3의 배수들의 합계와 5의 배수들의 합계를 계산하여
		// 3의 배수의 합계에서 5의 배수의 합계를 뺀 결과값을 출력하세요.
		// ex) 결과 = 633
		int thr = 0, fiv = 0;

		for (int i = 1; i <= 100; i++) {
			if (i % 3 == 0) {
				thr += i;
			}
			if (i % 5 == 0) {
				fiv += i;
			}
		}
		System.out.println(thr - fiv);

		System.out.println("=========================================");
		// 구구단 중 7단을 화면에 출력하세요.
		// ex) 7 * 1 = 7
		// 7 * 2 = 14
		// ...
		// 7 * 8 = 56
		// 7 * 9 = 63
		for (int i = 1; i <= 9; i++) {
			System.out.println("7 * " + i + "= " + i * 7);
		}

		System.out.println("=========================================");
		// 5!의 결과값을 출력하세요.(5! = 5 * 4 * 3 * 2 * 1)
		// ex) 5! = 120

		int su = 1;

		for (int i = 5; i >= 1; i--) {
			su *= i;
		}
		System.out.println(su);

		System.out.println("=========================================");
		// 두 변수에 저장된 범위의 정수값을 화면에 출력하세요.
		// 단, 한 줄에 정수값이 7개씩 출력되도록 프로그램을 작성하세요.
		// ex) 36 27 38 39 40 41 42
		// 43 44 45 46 47 48 49
		// 50 51 52 53 54 55 56
		// 57
		int begin = 36, end = 57, count = 0;

		for (int i = begin; i <= end; i++) {

			if (count % 7 == 0) {
				System.out.println();
			}
			count++;
			
			System.out.print(i + "\t");
			
		}

		System.out.println("=========================================");
	}
}