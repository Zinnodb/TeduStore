
import java.util.Date;

import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.tedu.store.entity.Address;
import cn.tedu.store.entity.User;
import cn.tedu.store.mapper.AddressMapper;
import cn.tedu.store.mapper.UserMapper;
import cn.tedu.store.service.IAddressService;

public class AdressMapperTest {
		
	@Test
	public void testAddressCountByuid() {
		AbstractApplicationContext ac=new ClassPathXmlApplicationContext("spring-dao.xml");
		AddressMapper addressMapper=ac.getBean("addressMapper",AddressMapper.class);
		Integer row=addressMapper.getAddressCountByuid(6);
		System.out.println(row);
	}
	
		@Test
		public void testFindUserByUsername() {
			AbstractApplicationContext ac=new ClassPathXmlApplicationContext("spring-dao.xml");
			UserMapper userMapper=ac.getBean("userMapper",UserMapper.class);
			String username="batis";
			User user=userMapper.findUserByUsername(username);
			System.out.println(user);
		}
	
	
	
	
		@Test
		public void add() {
				AbstractApplicationContext ac=new ClassPathXmlApplicationContext("spring-dao.xml","spring-service.xml");
				IAddressService addressService 
				= ac.getBean(
					"addressService", 
					IAddressService.class);
				
				Address address=new Address();
				address.setUid(2);
				address.setRecvName("zhangxiaoxiao");
				Address result = addressService.add("zhangxiaoxiao", address);
				System.out.println("受影响的行数=" + result+"id为:"+address.getId());
				
				ac.close();
		}
		
		@Test
		public void testUpdate() {
				AbstractApplicationContext ac=new ClassPathXmlApplicationContext("spring-dao.xml");
				UserMapper userMapper=ac.getBean("userMapper",UserMapper.class);
				
				Integer result = userMapper.changePassword(1, "123456","batis",new Date());
				System.out.println("受影响的行数=" + result);
				
				ac.close();
		}
}
