package cn.tedu.store.service;

import org.apache.ibatis.annotations.Param;

import cn.tedu.store.entity.User;
import cn.tedu.store.service.ex.PasswordNotMatchException;
import cn.tedu.store.service.ex.UsernameConflictException;
import cn.tedu.store.service.ex.UsernameNotFountException;

public interface IUserService  {
	
	/**
	 * �����û�ע�����֤,������ע����׳��쳣
	 * @param user �û�ע����Ϣ
	 * @return  ����ֵΪ�û�ע��ɹ�����
	 * @throws �׳�UsernameConflictException�쳣,�û�����ռ��
	 */
	User reg(User user) throws UsernameConflictException;
	
	/**
	 * �����¼ҵ��
	 * @param username 
	 * @param password
	 * @return �û���Ϣ����������
	 * @throw UsernameNotFountException,PasswordNotMatchException �û����������
	 */
	User login(String username,String password) throws UsernameNotFountException,PasswordNotMatchException;
	
	/**
	 * �����û���Ϣ
	 * @param uid �����û���id
	 * @param username ���ĵ����û���
	 * @param gender ���ĵ����Ա�
	 * @param phone ���ĵ��µ绰����
	 * @param email ���ĵ�������
	 * @return  ��Ӱ�������
	 * @throws UsernameNotFountException  ����ʧ���׳��쳣
	 * @throws UsernameConflictException  ����ʧ���׳��쳣
	 */
	Integer changeInfo(@Param(value = "id") Integer uid, String username,Integer gender, String phone, String email,String avatar) throws UsernameNotFountException,UsernameConflictException;
	
	/**
	 * �����û��������û�����,��ҵ�����չ�־ò����һЩҵ���߼�
	 * @param username ע���û���
	 * @return  �û�������
	 */
	User findUserByUsername(String username);
	
	/**
	 * ����MD5���ν����û��������
	 * @param password �û�ԭ����  
	 * @param uuid ��
	 * @return ���ܺ������
	 */
	String getMd5Password(String password,String uuid);
	
	/**
	 * �����û�id��֤��ǰ����,���޸��������
	 * @param uid �û�id
	 * @param newPassword  �û����趨������
	 * @param oldPassword  ��ǰ����
	 * @return  ��Ӱ�������
	 * @throw  UsernameNotFountException()
	 * @throw  PasswordNotMatchException()
	 */
	Integer changePassword(Integer uid,String newPassword,String oldPassword);
	
	/**
	 * ֱ�ӵ��ó־ò�ͬ������,���������չҵ���߼�.
	 * @param id �û�id
	 * @return �û���Ϣ
	 */
	User findUserById(Integer id);
	
	
	
}
