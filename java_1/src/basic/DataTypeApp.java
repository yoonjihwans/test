package basic;

//자료형(DataType) : 값 또는 객체를 표현하기 위한 단어(키워드 또는 식별자)
// => 기본형(PrimitiveType) : 값을 표현하기 위한 자료형 - 키워드
// => 참조형(ReferenceType) : 객체를 표현하기 위한 자료형 - 식별자(클래스)

//기본형 : 숫자형(정수형, 실수형, 문자형)과 논리형으로 구분하며 값을 저장하기 위한 변수를 선언할 때 사용
public class DataTypeApp {
	public static void main(String[] args) {
		//정수형(IntegerType) : 소숫점이 없는 숫자값 - 정수값
		// => Java 언어는 정수값을 기본적으로 4Byte(-2147483648~2147483647)로 표현
		System.out.println("<<정수형(IntegerType)>>");
		System.out.println("정수값(10진수 100) = "+100);//정수값 : 10진수 - IntegerType
		//println() 메소드는 정수값을 무조건 10진수로 변환하여 출력 처리
		System.out.println("정수값(8진수 100) = "+0100);//0정수값 : 8진수
		System.out.println("정수값(16진수 100) = "+0x100);//0x정수값 : 16진수
		System.out.println("정수값(10진수 100) = "+100L);//정수값L : 10진수(8Byte) - LongType
		
		System.out.println("정수값 = "+2147483647);
		//4Byte로 표현할 수 없는 정수값을 사용할 경우 에러 발생
		//System.out.println("정수값 = "+2147483648);
		//4Byte로 표현할 수 없는 정수값은 LongType으로 표현하여 사용
		System.out.println("정수값 = "+2147483648L);
		
		//정수값을 표현하기 위한 자료형 - 키워드
		// => byte(1Byte), short(2Byte), int(4Byte), long(8Byte)
		byte a1=127;//1Byte : -128~127
		short a2=32767;//2Byte : -32768~32767
		int a3=2147483647;//4Byte : -2147483648~2147483647
		long a4=2147483648L;//8Byte
		
		System.out.println("a1 = "+a1);
		System.out.println("a2 = "+a2);
		System.out.println("a3 = "+a3);
		System.out.println("a4 = "+a4);
		
		//int a5=100L;//값에 대한 손실이 발생할 수 있으므로 에러 발생
		long a5=100;//자료형이 큰 변수에는 자료형이 작은 리터럴(값) 저장 가능
		System.out.println("a5 = "+a5);
		System.out.println("==============================================================");
		//실수형(DoubleType) : 소숫점이 있는 숫자값 - 실수값
		// => Java 언어는 정수값을 기본적으로 8Byte로 표현 - 부동소숫점 방식으로 표현
		System.out.println("<<실수형(DoubleType)>>");
		System.out.println("실수값(4Byte) = "+12.3F);//실수값F >> 4Byte(FloatType)
		System.out.println("실수값(8Byte) = "+12.3);//실수값 >> 8Byte(DoubleType)
		//println() 메소드는 실수값이 아주 작거나 큰 경우 지수형태로 변환하여 출력
		System.out.println("실수값(8Byte) = "+0.000000000123);
		//실수값을 지수형태로 표현하여 사용 가능
		System.out.println("실수값(8Byte) = "+1.23E10);
		
		//정수값을 표현하기 위한 자료형 - float(4Byte), double(8Byte)
		float b1=1.23456789F;//가수부로 표현 가능한 크기(정밀도) : 소숫점 7자리
		double b2=1.23456789;//가수부로 표현 가능한 크기(정밀도) : 소숫점 15자리

		System.out.println("b1 = "+b1);
		System.out.println("b2 = "+b2);
		System.out.println("==============================================================");
		//문자형(CharacterType) : ' ' 안에 하나의 문자로 표현되는 값 - 문자값
		// => Java 언어는 문자값을 기본적으로 2Byte로 표현
		// => 문자값은 일반 문자외에 회피문자(제어문자)로 표현
		//회피문자(Escape Character) : 표현 불가능한 문자를 표현하기 위해 만들어진 문자값 
		// => [\]기호 사용하여 문자 표현
		// => '\n' : Enter, '\t' : Tab, '\'' : ' 문자, '\"' : " 문자, '\\' : \ 문자, '\0' : NULL 문자
		//문자값은 내부적으로 약속된 정수값(0~65535)으로 표현되어 사용 - 유니코드(Unicode)
		// => 'A' : 65, 'a' : 97, '0' : 48, ' ' : 32, '\n' : 13, '가' : 44032
		System.out.println("<<문자형(CharacterType)>>");
		System.out.println("문자값 = "+'A');
		System.out.println("문자값 = "+'가');
		System.out.println("문자값 = "+'\'');
		System.out.println("문자값 = "+'\\');
		
		//문자값을 표현하기 위한 자료형 - char(2Byte)
		char c1='A';
		char c2=65;//문자변수에는 문자값 대신 약속된 정수값 저장 가능
		char c3='a'-32;//문자값에 대한 연산 가능
		
		System.out.println("c1 = "+c1);
		System.out.println("c2 = "+c2);
		System.out.println("c3 = "+c3);
		
		char c4=45000;
		System.out.println("c4 = "+c4);
		System.out.println("==============================================================");
		//논리형(BooleanType) : false(거짓) 또는 true(참) - 논리값
		// => Java 언어는 논리값을 기본적으로 1Byte로 표현
		System.out.println("<<논리형(BooleanType)>>");
		System.out.println("논리값 = "+false);
		System.out.println("논리값 = "+true);
		//관계연산자(비교연산자)를 이용한 연산식의 결과값으로 논리값 제공
		System.out.println("논리값 = "+(20 < 10));
		System.out.println("논리값 = "+(20 > 10));
		
		//논리값을 표현하기 위한 자료형 - boolean(1Byte)
		boolean d1=false;
		boolean d2=20>10;
		
		System.out.println("d1 = "+d1);
		System.out.println("d2 = "+d2);
		System.out.println("==============================================================");
		//문자열(StringType) : " " 안에 0개 이상의 문자들을 사용하여 표현된 값 - String 객체
		// => 문자열은 기본형(원시형)이 아닌 String 클래스(참조형)를 사용하여 표현
		System.out.println("<<문자열(StringType)>>");
		System.out.println("문자열 = "+"홍길동");
		System.out.println("문자열 = "+"유관순 열사가 \"대한민국 만세\"를 외쳤습니다.");
		
		//문자열을 표현하기 위한 자료형 - String 클래스
		String name="임꺽정";
		System.out.println("이름 = "+name);
		System.out.println("==============================================================");
	}
}