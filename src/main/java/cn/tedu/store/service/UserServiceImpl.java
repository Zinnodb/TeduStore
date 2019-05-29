package cn.tedu.store.service;

import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import cn.tedu.store.entity.User;
import cn.tedu.store.mapper.UserMapper;
import cn.tedu.store.service.ex.PasswordNotMatchException;
import cn.tedu.store.service.ex.UsernameConflictException;
import cn.tedu.store.service.ex.UsernameNotFountException;
import cn.tedu.store.util.Validator;

@Service("userService")
public class UserServiceImpl implements IUserService {

	@Autowired
	private UserMapper userMapper;

	public User reg(User user) throws UsernameConflictException {
		User result = findUserByUsername(user.getUsername());
		if (result == null) {
			// 用户名不存在执行,直接调用持久层insert()方法进行注册.
			// 获取随机盐
			String uuid = UUID.randomUUID().toString();
			// 进行盐加密
			String md5Password = getMd5Password(user.getPassword(), uuid);
			// 存入加密后的密码
			user.setPassword(md5Password);
			// 存入注册的uuid
			user.setUuid(uuid);
			// 记录注册日志
			Date now = new Date();
			user.setCreatetedTime(now);
			user.setCreatedUser(user.getUsername());
			user.setModifiedUser(user.getUsername());
			user.setModifiedTime(now);
			// 调用注册方法
			userMapper.insert(user);
			// 返回user
			return user;
		} else {
			// 用户名被占用
			throw new UsernameConflictException("用户名(" + user.getUsername() + ")被占用");
		}

	}

	public User login(String username, String password) throws UsernameNotFountException, PasswordNotMatchException {
		// 调用自身的findUserByUsername方法
		User user = findUserByUsername(username);
		if (user != null) {
			// 用户名存在,调用加密方法获取密码,验证密码
			String str1 = getMd5Password(password, user.getUuid());
			if (user.getPassword().equals(str1)) {
				System.out.println("登录成功");
				return user;
			} else {
				throw new PasswordNotMatchException("密码错误!");
			}
		} else {
			throw new UsernameNotFountException("用户名不存在!请注册");
		}

	}

	public Integer changePassword(Integer uid, String newPassword, String oldPassword) {
		Integer result;
		// 先验证用户旧密码,调用findUserById()方法,
		User user = findUserById(uid);

		if (user == null) {
			throw new UsernameNotFountException("尝试操作失败");
		}
		// 用旧密码调用加密getMd5Password()方法 获取旧密码的加密密码
		String after = getMd5Password(oldPassword, user.getUuid());

		// 验证密码是否符合
		if (after.equals(user.getPassword())) {
			// 如果符合,则调用getMd5Password()方法对新密码加密
			String password = getMd5Password(newPassword, user.getUuid());
			result = userMapper.changePassword(user.getId(), password, user.getUsername(), new Date());
			if (result != 1) {
				throw new UsernameNotFountException("密码修改失败");
			} else {
				return 1;
			}
		} else {
			// 不符合则不做修改操作,抛出异常PasswordNotMatchException
			throw new PasswordNotMatchException("输入密码不正确");
		}
	}

	public User findUserById(Integer id) {
		User user = userMapper.findUserById(id);
		return user;
	}

	public User findUserByUsername(String username) {

		User user = userMapper.findUserByUsername(username);

		return user;
	}

	public String getMd5Password(String password, String uuid) {
		// 将原密码加密
		String str1 = DigestUtils.md5DigestAsHex(password.getBytes()).toUpperCase();
		// 然后加盐再进行加密
		String str2 = str1 + uuid.toUpperCase();
		String str3 = DigestUtils.md5DigestAsHex(str2.getBytes()).toUpperCase();
		return str3;
	}

	public Integer changeInfo(Integer uid, String username, Integer gender, String phone, String email,String avatar)
			throws UsernameNotFountException {
		// 检查数据的有效性
		// 如果用户名格式错误，视为null
		if (!Validator.checkUsername(username)) {
			username = null;
		}
		// （略）如果电话号码格式错误，视为null，同上
		// （略）如果电子邮箱格式错误，视为null，同上

		// 如果修改用户名，则用户名不可是其他用户已经注册的
		// 判断用户名是否为null，仅当不为null时执行此项检查
		if (username != null) {
			// 调用自身的findUserByUsername()根据用户名查询
			User data = findUserByUsername(username);
			if (data == null) {
				// 结果为null：用户名没被占用，可以使用，用户名保持不变，等待后续代码将其更新到数据表中
			} else {
				// 如果不为null：根据用户名找到数据，则根据id还需要判断是不是当前用户
				if (data.getId().equals(uid)) {
					// 如果id匹配：用户名是自己的，没必要修改：设置为null
					username = null;
				} else {
					// 如果id不匹配：用户名是别人：抛出异常UsernameConflictException
					throw new UsernameConflictException("用户名(" + username + ")已经被注册！");
				}
			}
		}

		// 将各参数都封装到User类型的对象中
		User user = new User(uid, username, gender, phone, email, avatar);
		// 封装日志数据
		User user2=findUserById(uid);
		user.setModifiedUser(user2.getUsername());
		user.setModifiedTime(new Date());
		// 调用持久层对象的changeInfo(User)方法，并获取返回值
		Integer rows = userMapper.changeInfo(user);
		// 判断返回值是否为：1
		if (rows == 1) {
			// -- 如果为1：返回1
			return 1;
		} else {
			// -- 如果不为1：抛出异常UserNotFoundException
			throw new UsernameNotFountException("尝试操作的用户数据不存在！");
		}
	}

}
