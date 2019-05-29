import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.tedu.store.entity.Address;
import cn.tedu.store.entity.User;
import cn.tedu.store.service.IAddressService;
import cn.tedu.store.service.ICartService;
import cn.tedu.store.service.IUserService;
import cn.tedu.store.service.ex.IllegalParamException;
import cn.tedu.store.service.ex.ServiceException;
import cn.tedu.store.service.ex.UsernameConflictException;
import cn.tedu.store.service.ex.UsernameNotFountException;

public class AdressServiceTset {
	@Test
	public void changeGoodsNum() {
		AbstractApplicationContext ac=new ClassPathXmlApplicationContext("spring-service.xml",
				"spring-dao.xml");
		ICartService cartService=ac.getBean("cartService",ICartService.class);
		
		try {
			cartService.addToCart(6, 10000017L, -4);
			System.out.println("修改完成!");
		} catch (IllegalParamException e) {
			System.out.println(e);
		}
		
	}
	
	
	
	@Test
	public void changeInfo() {
		AbstractApplicationContext ac
			= new ClassPathXmlApplicationContext(
				"spring-service.xml",
				"spring-dao.xml");
		
		IUserService userService
			= ac.getBean("userService", IUserService.class);
		
		try {
			Integer uid =13;
			String username = "springsm";
			Integer gender = 1;
			String phone = null;
			String email = null;
			String avatar=null;
			Integer rows = 
					userService.changeInfo(
						uid, username, gender, 
						phone, email,avatar);
			System.out.println("什么东东"+rows);
		} catch (UsernameNotFountException e) {
			System.out.println(e.getMessage());
		} catch (UsernameConflictException e) {
			System.out.println(e.getMessage());
		}
		
		ac.close();
	}
	
	
	
	@Test
	public void testChangPassword() {
		AbstractApplicationContext ac=new ClassPathXmlApplicationContext("spring-dao.xml","spring-service.xml");
		IUserService userService=ac.getBean("userService",IUserService.class);
		try {
			Integer result=userService.changePassword(11, "718596", "698023");
			System.out.println(result);
		} catch (ServiceException e) {
			System.out.println(e.getMessage());
		}
		ac.close();
	}
	
	
	
	
	
	@Test
	public void testReg() {
		AbstractApplicationContext ac=new ClassPathXmlApplicationContext("spring-dao.xml","spring-service.xml");
		IUserService userService=ac.getBean("userService",IUserService.class);
		try {
			User user=new User();
			user.setUsername("ee");
			user.setPassword("698023");
			userService.reg(user);
			System.out.println(user);
		} catch (UsernameConflictException e) {
			System.out.println(e.getMessage());
		}
		ac.close();
	}
	
	
	
	@Test
	public void testLogin() {
		AbstractApplicationContext ac=new ClassPathXmlApplicationContext("spring-dao.xml","spring-service.xml");
		IUserService userService=ac.getBean("userService",IUserService.class);
		try {
			String username="ee";
			String password="718596";
			User user=userService.login(username, password);
			System.out.println(user.getPassword());
			System.out.println(user);
		} catch (ServiceException e) {
			System.out.println(e.getMessage());
		}
		ac.close();
	}
	
	
	@Test
	public void add() {
		AbstractApplicationContext ac
			= new ClassPathXmlApplicationContext(
				"spring-dao.xml", "spring-service.xml");
		
		IAddressService addressService 
			= ac.getBean(
				"addressService", 
				IAddressService.class);
		
		Address address = new Address();
		address.setUid(9);
		address.setRecvName("常老师");
		Address result = addressService.add(
				"mybatis", address);
		System.out.println("result=" + result);
		
		ac.close();
	}
}
