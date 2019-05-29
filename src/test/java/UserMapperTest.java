
import java.util.Date;

import org.apache.ibatis.javassist.expr.NewArray;
import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.tedu.store.entity.User;
import cn.tedu.store.mapper.UserMapper;

public class UserMapperTest {
		
	@Test
	public void testCahngeInfo() {
		AbstractApplicationContext ac=new ClassPathXmlApplicationContext("spring-dao.xml");
		UserMapper userMapper=ac.getBean("userMapper",UserMapper.class);
		User user=new User();
		user.setId(11);
		user.setUsername("springE");
		user.setPhone("13056322145");
		user.setEmail("inno@163.com");
		user.setModifiedTime(new Date());
		user.setModifiedUser(user.getUsername());
		Integer row=userMapper.changeInfo(user);
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
		public void testInsert() {
				AbstractApplicationContext ac=new ClassPathXmlApplicationContext("spring-dao.xml");
				UserMapper userMapper=ac.getBean("userMapper",UserMapper.class);
				
				User user = new User();
				user.setUsername("batis");
				user.setPassword("1234");
				Integer result = userMapper.insert(user);
				System.out.println("受影响的行数=" + result+"id为:"+user.getId());
				
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
