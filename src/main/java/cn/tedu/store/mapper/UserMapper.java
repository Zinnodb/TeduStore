package cn.tedu.store.mapper;

import java.util.Date;

import org.apache.ibatis.annotations.Param;

import cn.tedu.store.entity.User;

public interface UserMapper {
		
		/**
		 * 用户插入数据
		 * @param user 用户数据
		 * @return 返回受影响行数
		 */
		Integer insert(User user);
		
		/**
		 * 根据用户名查找用户信息
		 * @param username 用户名
		 * @return  返回用户所有数据
		 */
		User findUserByUsername(String username);
		
		/**
		 * 依据id修改密码
		 * @param uid 用户id
		 * @param password 要存入的新密码
		 * @return 返回受影响行数
		 */
		Integer changePassword(@Param("uid")Integer uid,@Param("password")String password,@Param("modifiedUser") String modifiedUser,@Param("modifiedTime") Date modifiedTime);
		
		/**
		 * 根据id查找用户信息
		 * @param id 用户id
		 * @return 用户数据
		 */
		User findUserById(Integer id);
		
		/**
		 * 依据登录用户的id,修改用户信息
		 * @param user 封装被修改的用户id,新的用户名(可选),新的性别(可选),电话,邮箱
		 * @return 受影响的行数,正确操作返回值为1,失败为0
		 */
		Integer changeInfo(User user);
}
