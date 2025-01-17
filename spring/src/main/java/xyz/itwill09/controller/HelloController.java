package xyz.itwill09.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.slf4j.Slf4j;

//@Controller : 요청 처리 클래스(Controller 클래스)를 Spring Bean으로 등록하기 위한 어노테이션
// => 클래스의 이름을 Spring Bean을 구분하는 식별자(beanName)으로 사용 - 첫문자는 소문자로 변환
// => value 속성을 사용해 식별자(beanName) 변경 가능
//@Controller 어노테이션을 사용하여 Controller 인터페이스를 상속받지 않아도 요청 처리 클래스로 사용
// => 메소드에 @RequestMapping 어노테이션을 사용해 요청 처리 메소드로 처리되도록 작성
// => @RequestMapping 어노테이션을 사용한 요청 처리 메소드를 여러개 등록하여 사용 가능
@Controller
@Slf4j
public class HelloController {
	//요청 처리 메소드는 Front Controller에게 반드시 뷰이름(ViewName) 제공
	// => Front Controller는 제공받은 뷰이름을 사용해 ViewResolver 객체로 응답 처리되도록 구현
	//1.요청 처리 메소드의 반환형을 [void]로 작성한 경우 @RequestMapping 어노테이션의 value
	//속성값을 뷰이름으로 제공
	//2.요청 처리 메소드의 반환형을 [String]로 작성한 경우 메소드의 반환값(문자열)을 뷰이름으로 제공 
	//3.요청 처리 메소드의 반환형을 [ModelAndView]로 작성한 경우 ModelAndView 객체에 뷰이름을 저장하여 제공 
	
	//@RequestMapping : Controller 클래스의 메소드를 요청 처리 메소드로 설정하기 위한 어노테이션
	// => 클라이언트의 모든 요청방식(Method - GET, POST, PUT, PATCH, DELETE 등)에 의해 호출되는
	//요청 처리 메소드를 작성할 때 사용
	// => 클라이언트의 요청방식을 구분하여 요청 처리 메소드를 호출할 경우 @GetMapping, @PostMappingt
	//, @PutMapping, @PatchMapping, @DeleteMapping 등의 어노테이션 사용 가능
	//value 속성 : 클라이언트의 요청정보(Command - URL 주소)를 속성값으로 설정
	// => value 속성외에 다른 속성이 없는 경우 속성값만 설정 가능
	// => value 속성값으로 설정된 URL 주소로 요청한 경우 요청 처리 메소드를 호출하여 명령 실행
	// => @RequestMapping 어노테이션의 value 속성값이 중복될 경우 WAS 프로그램 실행시 에러 발생
	@RequestMapping(value = "/hello")
	public void hello() {
		log.info("[/hello] 페이지 요청 : HelloController 클래스의 hello() 메소드 호출");
	}
	
	@RequestMapping("/helloViewName")
	public String helloViewName() {
		log.info("[/helloViewName] 페이지 요청 : HelloController 클래스의 helloViewName() 메소드 호출");
		return "hello";
	}
	
	@RequestMapping("/helloMav")
	public ModelAndView helloModelAndView() {
		log.info("[/helloMav] 페이지 요청 : HelloController 클래스의 helloModelAndView() 메소드 호출");

		/*
		//ModelAndView 객체 : 요청에 대한 처리결과와 뷰이름을 저장하기 위한 객체
		ModelAndView modelAndView=new ModelAndView();
		modelAndView.setViewName("hello");//Setter 메소드로 뷰이름 저장
		return modelAndView;
		*/
		
		ModelAndView modelAndView=new ModelAndView("hello");//생성자로 뷰이름 저장
		return modelAndView;
	}
}












