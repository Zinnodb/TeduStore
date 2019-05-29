package cn.tedu.store.service;

import java.util.List;

import cn.tedu.store.entity.Address;
import cn.tedu.store.service.ex.InsertDataException;

public interface IAddressService {
		/**
		 * 锟斤拷锟斤拷锟铰碉拷锟秸伙拷锟斤拷址
		 * @param username	锟斤拷前锟斤拷录锟斤拷锟矫伙拷锟斤拷
		 * @param address	锟秸伙拷锟斤拷址锟斤拷锟斤拷
		 * @return		锟斤拷锟斤拷id锟侥碉拷址锟斤拷息锟斤拷锟斤拷
		 * @throws InsertDataException() 锟斤拷锟斤拷锟斤拷锟斤拷失锟斤拷
		 */
		Address add(String username,Address address);
		
		/**
		 * 锟斤拷取某锟斤拷锟矫伙拷锟斤拷锟秸伙拷锟斤拷址锟叫憋拷
		 * @param uid 锟矫伙拷锟斤拷id
		 * @return 锟矫伙拷锟斤拷锟秸伙拷锟斤拷址锟叫憋拷
		 */
		List<Address> getAddressList(Integer uid);
		
		/**
		 * 锟斤拷取锟矫伙拷锟秸伙拷锟斤拷址锟斤拷锟斤拷锟斤拷
		 * @param uid 锟矫伙拷id
		 * @return 锟秸伙拷锟斤拷址锟斤拷锟斤拷锟斤拷
		 */
		Integer getAddressCountByuid(Integer uid);
		
		/**
		 * 执锟斤拷锟睫改碉拷址默锟较诧拷锟斤拷
		 * @param uid 锟矫伙拷id
		 * @param id		锟矫伙拷锟斤拷锟捷碉拷址id
		 * @return 锟缴癸拷锟斤拷锟斤拷1,失锟斤拷锟阶筹拷锟届常
		 * @throws dataNotException
		 */
		Integer setDefault(Integer uid, Integer id);
		
		/**
		 * 锟斤拷锟斤拷id锟斤拷取锟秸伙拷锟斤拷址锟斤拷息
		 * @param id 锟秸伙拷锟斤拷址id
		 * @return 锟秸伙拷锟斤拷址锟斤拷息锟斤拷锟斤拷锟矫伙拷锟狡ワ拷锟斤拷锟斤拷锟捷ｏ拷锟津返伙拷null
		 */
		Address getAddressById(Integer id);
		
		/**
		 * 锟矫伙拷删锟斤拷锟斤拷址锟斤拷息
		 * @param uid 锟矫伙拷id
		 * @param id	 锟斤拷锟斤拷锟斤拷删锟斤拷锟斤拷锟矫伙拷锟侥碉拷址id
		 * @return  锟斤拷影锟斤拷锟斤拷锟斤拷锟�
		 */
		Integer deleteAddressById(Integer uid,Integer id ,Integer nid);
}
