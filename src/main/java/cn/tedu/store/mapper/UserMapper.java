package cn.tedu.store.mapper;

import java.util.Date;

import org.apache.ibatis.annotations.Param;

import cn.tedu.store.entity.User;

public interface UserMapper {
		
		/**
		 * �û���������
		 * @param user �û�����
		 * @return ������Ӱ������
		 */
		Integer insert(User user);
		
		/**
		 * �����û��������û���Ϣ
		 * @param username �û���
		 * @return  �����û���������
		 */
		User findUserByUsername(String username);
		
		/**
		 * ����id�޸�����
		 * @param uid �û�id
		 * @param password Ҫ�����������
		 * @return ������Ӱ������
		 */
		Integer changePassword(@Param("uid")Integer uid,@Param("password")String password,@Param("modifiedUser") String modifiedUser,@Param("modifiedTime") Date modifiedTime);
		
		/**
		 * ����id�����û���Ϣ
		 * @param id �û�id
		 * @return �û�����
		 */
		User findUserById(Integer id);
		
		/**
		 * ���ݵ�¼�û���id,�޸��û���Ϣ
		 * @param user ��װ���޸ĵ��û�id,�µ��û���(��ѡ),�µ��Ա�(��ѡ),�绰,����
		 * @return ��Ӱ�������,��ȷ��������ֵΪ1,ʧ��Ϊ0
		 */
		Integer changeInfo(User user);
}
