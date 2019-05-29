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
		//��ȡ���е�ʡ�б�
		List<Province> provinces=provinceService.getProvinceList();
		//��װ����ʡ���б�,����ת��
		modelMap.addAttribute("provinces", provinces);
		// ��ȡ��ǰ��¼���û���id
				Integer uid = getUidFromSession(session);
				// ��ȡ��ǰ��¼���û����ջ���ַ�б�
				List<Address> addresses
					= addressService.getAddressList(uid);
				// ��װ�ջ���ַ�б���ת��
				modelMap.addAttribute("addresses", addresses);
		return "address_list";
	}
	
	@RequestMapping(value="handle_add.do",method=RequestMethod.POST)
	public String handleAdd(Address address,HttpSession session,ModelMap modelMap) {
			// TODO ��֤������Ч��
			//���ݵ�¼session ��ȡuid 
			Integer uid=getUidFromSession(session);
			//��uid��װ��address��
			address.setUid(uid);
			try {
				//-- ����ҵ����add����,���ڿ������쳣,����try  catch
				String username=session.getAttribute("uname").toString();
				System.out.println("���Ʋ㴫�����ֵ�е���code"+address.getRecvArea());
				addressService.add(username, address);
				return "redirect:list.do";
			} catch (InsertDataException e) {
				// ��װ������Ϣ,������Ҫ��ʾ,�˷���ʹ���ض���,��������ʾ
				//�����ʱ����Ҫʹ��,ת���ķ�ʽ������ҳ��
				modelMap.addAttribute("message",e.getMessage());
				return "error";
			}
		
	}
	
	@RequestMapping("/set_default.do")
	public String handleSetDefault(@RequestParam("id") Integer id,HttpSession session ,ModelMap modelMap) {
		//��session�л�ȡuid
		Integer uid=getUidFromSession(session);
		try {
			//����ҵ����setDefault����
			addressService.setDefault(uid, id);
			//�ض��򵽵�ǰҳ��,��ʾ�޸ĳɹ����ҳ��.
			return  "redirect:list.do";
		} catch (DataNotException e) {
			// ��װ������Ϣ
			modelMap.addAttribute("message",e.getMessage());
			//ת����error
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
			// ��װ������Ϣ
						modelMap.addAttribute("message",e.getMessage());
			//ת����error
						return "error";	
		}
	}

}
