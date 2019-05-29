package cn.tedu.store.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.tedu.store.entity.Address;
import cn.tedu.store.mapper.AddressMapper;
import cn.tedu.store.service.ex.DataNotException;
import cn.tedu.store.service.ex.InsertDataException;
@Service("addressService")
public class AddressServiceImpl implements IAddressService {
	
	@Autowired
	private AddressMapper addressMapper;
	
	@Autowired
	private IProvinceService provinceService;
	
	@Autowired
	private ICityService cityservice;
	
	@Autowired
	private IAreaService areaService;
	
	public Address add(String username, Address address) {
		//TODO 锟斤拷锟斤拷锟斤拷前锟斤拷址锟角凤拷为默锟较碉拷址
				//通锟斤拷锟矫伙拷锟斤拷id锟节碉拷址锟斤拷锟叫诧拷询锟斤拷址锟斤拷息,锟斤拷锟矫伙拷锟斤拷锟斤拷锟�,锟斤拷锟斤拷拥牡锟街肺拷系锟街�,锟斤拷之锟斤拷锟斤拷.
			//通锟斤拷session锟矫碉拷锟矫伙拷id
			Integer uid=address.getUid();
			Integer num=getAddressCountByuid(uid);
			System.out.println("锟斤拷址锟斤拷锟斤拷锟斤拷锟斤拷:"+num);
			address.setIsDefault(num==0?1:0);
//			if (num==0) {
//				//锟斤拷锟斤拷锟斤拷牡锟街凤拷锟轿拷系锟街�
//				address.setIsDefault(1);
//			}else {
//				//锟斤拷锟斤拷牡锟街凤拷锟斤拷锟轿拷锟街�
//				address.setIsDefault(0);
//			}
		//锟斤拷装省锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷,锟斤拷recvDistrict
		System.out.println("锟斤拷始锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷code为:"+address.getRecvArea());
		String recvDistrict=getRecvDistrict(address.getRecvProvince(),address.getRecvCity(),address.getRecvArea());
		
		address.setRecvDistrict(recvDistrict);
		//锟斤拷装锟斤拷志锟斤拷息
		Date now=new Date();
		address.setCreatedUser(username);
		address.setCreatedTime(now);
		address.setModifiedTime(now);
		address.setModifiedUser(username);
		// 执锟叫持久诧拷
		Integer rows=addressMapper.insert(address);
		if (rows==1) {
			//锟斤拷锟接成癸拷
			return address;
		}else {
			//锟斤拷锟接筹拷锟斤拷,锟阶筹拷锟届常
			throw new  InsertDataException("锟斤拷锟斤拷锟秸伙拷锟斤拷址锟斤拷息失锟杰ｏ拷" + address);
		}
	}
	/**
	 * 锟斤拷取省锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷
	 * @param recvProvince
	 * @param recvCity
	 * @param recvArea
	 * @return
	 */
	private String getRecvDistrict(String recvProvince, String recvCity, String recvArea) {
		
		String province=provinceService.getProvinceByCode(recvProvince).getName();
		
		String city=cityservice.getCityByCode(recvCity).getName();
		
		System.out.println("锟斤拷询锟侥诧拷锟斤拷"+recvArea);
		System.out.println("锟斤拷询锟侥碉拷锟侥凤拷锟斤拷"+areaService.getAreaByCode(recvArea));
		String area=areaService.getAreaByCode(recvArea).getName();
		
		String	recvDistrict=province+city+area;
		
		return recvDistrict;
	}
	
	
	public List<Address> getAddressList(Integer uid) {
		return addressMapper.getAddressList(uid);
	}
	
	
	public Integer getAddressCountByuid(Integer uid) {
		return addressMapper.getAddressCountByuid(uid);
	}
	
	@Transactional
	public Integer setDefault(Integer uid, Integer id) {
		// 锟斤拷锟矫持久诧拷锟絪etDefault锟斤拷锟斤拷
			//锟饺斤拷锟叫碉拷址锟斤拷锟斤拷id为null,锟斤拷说锟叫碉拷址默锟斤拷值锟斤拷锟斤拷为锟斤拷默锟斤拷
			addressMapper.setIsDefault(uid, null, 0);
			//锟劫斤拷锟斤拷锟絠d锟斤拷应锟斤拷锟斤拷锟斤拷锟睫革拷为默锟斤拷
			Integer rows=addressMapper.setIsDefault(uid, id, 1);
			//锟叫讹拷锟角凤拷执锟叫成癸拷,失锟斤拷锟阶筹拷锟届常
			if (rows==1) {
				return 1;
			}else {
				throw new DataNotException("锟斤拷锟斤拷id为"+id+"锟斤拷锟斤拷锟捷诧拷锟斤拷锟斤拷,锟斤拷锟皆诧拷锟斤拷失锟斤拷");
			}
	}
	
	
	public Integer deleteAddressById(Integer uid, Integer id,Integer nid) {
		/*
		 * 判断删除地址是否为默认地址，如果不是这直接删除。
		 * 反之，在删除之后进行默认地址的设置。
		 */
		//获取默认值
				Address address=getAddressById(id);
				System.out.println("即将要删除的地址为："+address);
				Integer isDefault=address.getIsDefault();
				System.out.println("删除的地址信息的是否是默认地址："+isDefault);
		// 删除用户指定id地址
		Integer row=addressMapper.deleteAddressById(uid, id);
		/*
		 * 判断地址是否为默认地址
		 * 即：根据id获取isdefault的值是否为1
		 */
		if (isDefault==1) {
			setDefault(uid, nid);
		}
		if (row==1 ) {
			return row;
		}else {
			throw new DataNotException("地址信息id为"+id+"的地址信息的不存在");
		}
		
	}
	@Override
	public Address getAddressById(Integer id) {
		
		return addressMapper.getAddressById(id);
	}
	
}
