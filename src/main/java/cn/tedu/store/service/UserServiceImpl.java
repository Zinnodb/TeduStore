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
			// �û���������ִ��,ֱ�ӵ��ó־ò�insert()��������ע��.
			// ��ȡ�����
			String uuid = UUID.randomUUID().toString();
			// �����μ���
			String md5Password = getMd5Password(user.getPassword(), uuid);
			// ������ܺ������
			user.setPassword(md5Password);
			// ����ע���uuid
			user.setUuid(uuid);
			// ��¼ע����־
			Date now = new Date();
			user.setCreatetedTime(now);
			user.setCreatedUser(user.getUsername());
			user.setModifiedUser(user.getUsername());
			user.setModifiedTime(now);
			// ����ע�᷽��
			userMapper.insert(user);
			// ����user
			return user;
		} else {
			// �û�����ռ��
			throw new UsernameConflictException("�û���(" + user.getUsername() + ")��ռ��");
		}

	}

	public User login(String username, String password) throws UsernameNotFountException, PasswordNotMatchException {
		// ���������findUserByUsername����
		User user = findUserByUsername(username);
		if (user != null) {
			// �û�������,���ü��ܷ�����ȡ����,��֤����
			String str1 = getMd5Password(password, user.getUuid());
			if (user.getPassword().equals(str1)) {
				System.out.println("��¼�ɹ�");
				return user;
			} else {
				throw new PasswordNotMatchException("�������!");
			}
		} else {
			throw new UsernameNotFountException("�û���������!��ע��");
		}

	}

	public Integer changePassword(Integer uid, String newPassword, String oldPassword) {
		Integer result;
		// ����֤�û�������,����findUserById()����,
		User user = findUserById(uid);

		if (user == null) {
			throw new UsernameNotFountException("���Բ���ʧ��");
		}
		// �þ�������ü���getMd5Password()���� ��ȡ������ļ�������
		String after = getMd5Password(oldPassword, user.getUuid());

		// ��֤�����Ƿ����
		if (after.equals(user.getPassword())) {
			// �������,�����getMd5Password()���������������
			String password = getMd5Password(newPassword, user.getUuid());
			result = userMapper.changePassword(user.getId(), password, user.getUsername(), new Date());
			if (result != 1) {
				throw new UsernameNotFountException("�����޸�ʧ��");
			} else {
				return 1;
			}
		} else {
			// �����������޸Ĳ���,�׳��쳣PasswordNotMatchException
			throw new PasswordNotMatchException("�������벻��ȷ");
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
		// ��ԭ�������
		String str1 = DigestUtils.md5DigestAsHex(password.getBytes()).toUpperCase();
		// Ȼ������ٽ��м���
		String str2 = str1 + uuid.toUpperCase();
		String str3 = DigestUtils.md5DigestAsHex(str2.getBytes()).toUpperCase();
		return str3;
	}

	public Integer changeInfo(Integer uid, String username, Integer gender, String phone, String email,String avatar)
			throws UsernameNotFountException {
		// ������ݵ���Ч��
		// ����û�����ʽ������Ϊnull
		if (!Validator.checkUsername(username)) {
			username = null;
		}
		// ���ԣ�����绰�����ʽ������Ϊnull��ͬ��
		// ���ԣ�������������ʽ������Ϊnull��ͬ��

		// ����޸��û��������û��������������û��Ѿ�ע���
		// �ж��û����Ƿ�Ϊnull��������Ϊnullʱִ�д�����
		if (username != null) {
			// ���������findUserByUsername()�����û�����ѯ
			User data = findUserByUsername(username);
			if (data == null) {
				// ���Ϊnull���û���û��ռ�ã�����ʹ�ã��û������ֲ��䣬�ȴ��������뽫����µ����ݱ���
			} else {
				// �����Ϊnull�������û����ҵ����ݣ������id����Ҫ�ж��ǲ��ǵ�ǰ�û�
				if (data.getId().equals(uid)) {
					// ���idƥ�䣺�û������Լ��ģ�û��Ҫ�޸ģ�����Ϊnull
					username = null;
				} else {
					// ���id��ƥ�䣺�û����Ǳ��ˣ��׳��쳣UsernameConflictException
					throw new UsernameConflictException("�û���(" + username + ")�Ѿ���ע�ᣡ");
				}
			}
		}

		// ������������װ��User���͵Ķ�����
		User user = new User(uid, username, gender, phone, email, avatar);
		// ��װ��־����
		User user2=findUserById(uid);
		user.setModifiedUser(user2.getUsername());
		user.setModifiedTime(new Date());
		// ���ó־ò�����changeInfo(User)����������ȡ����ֵ
		Integer rows = userMapper.changeInfo(user);
		// �жϷ���ֵ�Ƿ�Ϊ��1
		if (rows == 1) {
			// -- ���Ϊ1������1
			return 1;
		} else {
			// -- �����Ϊ1���׳��쳣UserNotFoundException
			throw new UsernameNotFountException("���Բ������û����ݲ����ڣ�");
		}
	}

}
