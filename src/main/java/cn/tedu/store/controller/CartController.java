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
			//获取当前登录用户的id
			Integer uid=getUidFromSession(session);
			//获取用户购物车里的商品
			List<Cart> carts=cartService.getListByUid(uid);
			System.out.println("控制器层"+carts);
			//封装 数据
			modelMap.addAttribute("carts", carts);
			//转发
			return "cart_list";
		}
		
		
		@ResponseBody
		@RequestMapping("/add.do")
		public ResponseResult<Void> handeAddToCart( @RequestParam("goods_id") Long goodsId,@RequestParam("num") Integer num,
			    HttpSession session){
					//声明ResponseResult返回值
			ResponseResult<Void> rr;
					//从session获取uid  
			Integer uid=getUidFromSession(session);
					//调用业务层的添加购物车的方法
					try {
						cartService.addToCart(uid,goodsId,num);
						rr=new  ResponseResult<Void>(ResponseResult.STATE_OK);
					} catch (ServiceException e) {
						rr=new ResponseResult<Void>(e);
					}
			return rr;
		}
}
