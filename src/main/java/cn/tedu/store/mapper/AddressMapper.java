package cn.tedu.store.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.tedu.store.entity.Address;

public interface AddressMapper {
		
		/**
		 * �����ջ���ַ
		 * @param address ��ַ����
		 * @return	��Ӱ�������
		 */
		Integer insert(Address address);
		
		/**
		 * ��ȡĳ���û����ջ���ַ�б�
		 * @param uid �û���id
		 * @return �û����ջ���ַ�б�
		 */
		List<Address> getAddressList(Integer uid);
		
		/**
		 * ��ȡ�û��ջ���ַ������
		 * @param uid �û�id
		 * @return �ջ���ַ������
		 */
		Integer getAddressCountByuid(Integer uid);
		
		/**
		 * ����Ĭ�ϵ�ַ
		 * @param uid �û�id
		 * @param id	�û���ַ���ݵ�id
		 * @param isDefault	Ĭ��Ϊ1,����Ĭ��Ϊ0
		 * @return
		 */
		Integer setIsDefault(@Param("uid")Integer uid,@Param("id")Integer id,@Param("isDefault")Integer isDefault);


		/**
		 * �û�ɾ����ַ��Ϣ
		 * @param uid �û�id
		 * @param id	 ������ɾ�����û��ĵ�ַid
		 * @return  ��Ӱ�������
		 */
		Integer deleteAddressById(@Param("uid")Integer uid,@Param("id")Integer id );
		
		/**
		 * ����id��ȡ�ջ���ַ��Ϣ
		 * @param id	�ջ���ַid
		 * @return	�ջ���ַ��Ϣ�����û��ƥ������ݣ��򷵻�null
		 */
		Address getAddressById(Integer id);

}
