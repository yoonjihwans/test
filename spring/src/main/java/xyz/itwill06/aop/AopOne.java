package xyz.itwill06.aop;

//핵심관심코드만 사용하여 작성된 메소드가 선언된 클래스 - 핵심관심모듈
// => 횡단관심코드가 삽입될 메소드(타겟메소드)를 지정하기 위해 인터페이스를 상속받아 작성
public class AopOne implements AopTarget {
	@Override
	public void display1() {
		//핵심관심코드
		System.out.println("*** AopOne 클래스의 display1() 메소드 호출 ***");
	}
	
	@Override
	public void display2() {
		System.out.println("*** AopOne 클래스의 display2() 메소드 호출 ***");
	}
	
	@Override
	public void display3() {
		System.out.println("*** AopOne 클래스의 display3() 메소드 호출 ***");
	}
}