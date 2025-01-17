package xyz.itwill05.di;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AnnotationStudentApp {
	public static void main(String[] args) {
		System.out.println("=============== Spring Container 초기화 전 ===============");
		ApplicationContext context=new ClassPathXmlApplicationContext("05-4_diAnnotation.xml");
		System.out.println("=============== Spring Container 초기화 후 ===============");
		StudentService service=context.getBean("studentService", StudentService.class);
		service.addStudent(null);
		System.out.println("==========================================================");
		((ClassPathXmlApplicationContext)context).close();		
	}
}