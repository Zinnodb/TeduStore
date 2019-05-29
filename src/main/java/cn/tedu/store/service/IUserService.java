package cn.tedu.store.service;

import org.apache.ibatis.annotations.Param;

import cn.tedu.store.entity.User;
import cn.tedu.store.service.ex.PasswordNotMatchException;
import cn.tedu.store.service.ex.UsernameConflictException;
import cn.tedu.store.service.ex.UsernameNotFountException;

public interface IUserService  {
	
	/**
	 * 进行用户注册的验证,并进行注册或抛出异常
	 * @param user 用户注册信息
	 * @return  返回值为用户注册成功数据
	 * @throws 抛出UsernameConflictException异常,用户名被占用
	 */
	User reg(User user) throws UsernameConflictException;
	
	/**
	 * 处理登录业务
	 * @param username 
	 * @param password
	 * @return 用户信息的所有数据
	 * @throw UsernameNotFountException,PasswordNotMatchException 用户名密码错误
	 */
	User login(String username,String password) throws UsernameNotFountException,PasswordNotMatchException;
	
	/**
	 * 更改用户信息
	 * @param uid 更改用户的id
	 * @param username 更改的新用户名
	 * @param gender 更改的新性别
	 * @param phone 更改的新电话号码
	 * @param email 更改的新邮箱
	 * @return  受影响的行数
	 * @throws UsernameNotFountException  操作失败抛出异常
	 * @throws UsernameConflictException  操作失败抛出异常
	 */
	Integer changeInfo(@Param(value = "id") Integer uid, String username,Integer gender, String phone, String email,String avatar) throws UsernameNotFountException,UsernameConflictException;
	
	/**
	 * 依据用户名查找用户数据,在业务层扩展持久层进行一些业务逻辑
	 * @param username 注册用户名
	 * @return  用户名数据
	 */
	User findUserByUsername(String username);
	
	/**
	 * 利用MD5加盐进行用户密码加密
	 * @param password 用户原密码  
	 * @param uuid 盐
	 * @return 加密后的密码
	 */
	String getMd5Password(String password,String uuid);
	
	/**
	 * 依据用户id验证先前密码,做修改密码操作
	 * @param uid 用户id
	 * @param newPassword  用户新设定的密码
	 * @param oldPassword  先前密码
	 * @return  受影响的行数
	 * @throw  UsernameNotFountException()
	 * @throw  PasswordNotMatchException()
	 */
	Integer changePassword(Integer uid,String newPassword,String oldPassword);
	
	/**
	 * 直接调用持久层同名方法,方便后期拓展业务逻辑.
	 * @param id 用户id
	 * @return 用户信息
	 */
	User findUserById(Integer id);
	
	
	
}
