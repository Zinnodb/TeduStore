package cn.tedu.store.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.tedu.store.entity.Address;

public interface AddressMapper {
		
		/**
		 * 新增收货地址
		 * @param address 地址数据
		 * @return	受影响的行数
		 */
		Integer insert(Address address);
		
		/**
		 * 获取某个用户的收货地址列表
		 * @param uid 用户的id
		 * @return 用户的收货地址列表
		 */
		List<Address> getAddressList(Integer uid);
		
		/**
		 * 获取用户收货地址的数量
		 * @param uid 用户id
		 * @return 收货地址的数量
		 */
		Integer getAddressCountByuid(Integer uid);
		
		/**
		 * 设置默认地址
		 * @param uid 用户id
		 * @param id	用户地址数据的id
		 * @param isDefault	默认为1,不是默认为0
		 * @return
		 */
		Integer setIsDefault(@Param("uid")Integer uid,@Param("id")Integer id,@Param("isDefault")Integer isDefault);


		/**
		 * 用户删除地址信息
		 * @param uid 用户id
		 * @param id	 即将被删除的用户的地址id
		 * @return  受影响的行数
		 */
		Integer deleteAddressById(@Param("uid")Integer uid,@Param("id")Integer id );
		
		/**
		 * 根据id获取收货地址信息
		 * @param id	收货地址id
		 * @return	收货地址信息，如果没有匹配的数据，则返回null
		 */
		Address getAddressById(Integer id);

}
