package cn.tedu.store.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.tedu.store.entity.Cart;
import cn.tedu.store.entity.ResponseResult;
import cn.tedu.store.service.ICartService;
import cn.tedu.store.service.ex.ServiceException;

@Controller
@RequestMapping("/cart")
public class CartController extends BaseController {

	
		@Autowired
		private ICartService cartService;
		
		@RequestMapping("/list.do")
		public String showList(ModelMap modelMap,HttpSession session) {
			//��ȡ��ǰ��¼�û���id
			Integer uid=getUidFromSession(session);
			//��ȡ�û����ﳵ�����Ʒ
			List<Cart> carts=cartService.getListByUid(uid);
			System.out.println("��������"+carts);
			//��װ ����
			modelMap.addAttribute("carts", carts);
			//ת��
			return "cart_list";
		}
		
		
		@ResponseBody
		@RequestMapping("/add.do")
		public ResponseResult<Void> handeAddToCart( @RequestParam("goods_id") Long goodsId,@RequestParam("num") Integer num,
			    HttpSession session){
					//����ResponseResult����ֵ
			ResponseResult<Void> rr;
					//��session��ȡuid  
			Integer uid=getUidFromSession(session);
					//����ҵ������ӹ��ﳵ�ķ���
					try {
						cartService.addToCart(uid,goodsId,num);
						rr=new  ResponseResult<Void>(ResponseResult.STATE_OK);
					} catch (ServiceException e) {
						rr=new ResponseResult<Void>(e);
					}
			return rr;
		}
}
