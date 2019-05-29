package cn.tedu.store.controller;


import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import cn.tedu.store.entity.Address;
import cn.tedu.store.entity.Province;
import cn.tedu.store.service.IAddressService;
import cn.tedu.store.service.IProvinceService;
import cn.tedu.store.service.ex.DataNotException;
import cn.tedu.store.service.ex.InsertDataException;

@Controller
@RequestMapping("/address")
public class AdressController extends BaseController{
	
	@Autowired
	private  IAddressService addressService;
	
	@Autowired
	private IProvinceService provinceService;
	
	@RequestMapping("/list.do")
	public String showList(ModelMap modelMap,HttpSession session) {
		//获取所有的省列表
		List<Province> provinces=provinceService.getProvinceList();
		//封装所有省的列表,用于转发
		modelMap.addAttribute("provinces", provinces);
		// 获取当前登录的用户的id
				Integer uid = getUidFromSession(session);
				// 获取当前登录的用户的收货地址列表
				List<Address> addresses
					= addressService.getAddressList(uid);
				// 封装收货地址列表，以转发
				modelMap.addAttribute("addresses", addresses);
		return "address_list";
	}
	
	@RequestMapping(value="handle_add.do",method=RequestMethod.POST)
	public String handleAdd(Address address,HttpSession session,ModelMap modelMap) {
			// TODO 验证数据有效性
			//根据登录session 获取uid 
			Integer uid=getUidFromSession(session);
			//将uid封装到address中
			address.setUid(uid);
			try {
				//-- 调用业务层的add方法,由于可能抛异常,所以try  catch
				String username=session.getAttribute("uname").toString();
				System.out.println("控制层传入参数值中的区code"+address.getRecvArea());
				addressService.add(username, address);
				return "redirect:list.do";
			} catch (InsertDataException e) {
				// 封装错误信息,并且需要提示,此方法使用重定向,所以在提示
				//错误的时候需要使用,转发的方式到错误页面
				modelMap.addAttribute("message",e.getMessage());
				return "error";
			}
		
	}
	
	@RequestMapping("/set_default.do")
	public String handleSetDefault(@RequestParam("id") Integer id,HttpSession session ,ModelMap modelMap) {
		//从session中获取uid
		Integer uid=getUidFromSession(session);
		try {
			//调用业务层的setDefault方法
			addressService.setDefault(uid, id);
			//重定向到当前页面,显示修改成功后的页面.
			return  "redirect:list.do";
		} catch (DataNotException e) {
			// 封装错误信息
			modelMap.addAttribute("message",e.getMessage());
			//转发到error
			return "error";
		}
	}
	
	@RequestMapping("/delete.do")
	public String handleDeleteAddress(@RequestParam("id") Integer id,@RequestParam("nid") Integer nid,HttpSession session ,ModelMap modelMap) {
		Integer uid=getUidFromSession(session);
		try {
			addressService.deleteAddressById(uid, id,nid);
			return  "redirect:list.do";
		} catch (DataNotException e) {
			// 封装错误信息
						modelMap.addAttribute("message",e.getMessage());
			//转发到error
						return "error";	
		}
	}

}
