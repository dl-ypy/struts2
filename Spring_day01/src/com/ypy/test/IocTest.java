package com.ypy.test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ypy.ioc.Student;

public class IocTest {

	@Test
	public void testById() {
		//���������ļ�
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		Student stu = (Student) ac.getBean("stu");
		stu.say();
	}
	@Test
	public void testByName() {
		//���������ļ�
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		Student stu = (Student) ac.getBean("stuName");
		stu.say();
	}
	@Test
	public void testByClass() {
		//���������ļ�
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		Student stu = (Student) ac.getBean(Student.class);
		stu.say();
	}
	@Test
	public void testScope() {
		//���������ļ�
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		Student stu1 = (Student) ac.getBean(Student.class);
		Student stu2 = (Student) ac.getBean(Student.class);
		Student stu3 = (Student) ac.getBean(Student.class);
		stu1.say();
		stu2.say();
		stu3.say();
	}

}
