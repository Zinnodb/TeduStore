package cn.tedu.store.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cn.tedu.store.entity.Goods;
import cn.tedu.store.service.IGoodsService;
@Controller
@RequestMapping("/goods")
public class GoodsController extends BaseController {
		@Autowired
		private IGoodsService goodsService;
		
		@RequestMapping("/list.do")
		public String showGoodsList(@RequestParam("categoryId") Long categoryId,Integer page,ModelMap modelMap) {
			//调用业务层获取数据
			List<Goods> goodsList=goodsService.getListByCategoryId(categoryId, page);
			//调用业务层获取数据的数量,并计算最大业务
			Integer goodsCount = goodsService.getCountByCategoryId(
					categoryId);
			int maxPage = (int) Math.ceil(1.0*goodsCount/IGoodsService.COUNT_PRE_PAGE);
			modelMap.addAttribute("maxPage", maxPage);
			//当前查看的商品的分类id
			modelMap.addAttribute("categoryId", categoryId);
			//封装数据,以转发
			modelMap.addAttribute("goodsList", goodsList);
			//执行转发
			return "goods_list";
		}
		
		
		@RequestMapping("/details.do")
		public String showDetails(@RequestParam("id")Long id,ModelMap modelMap) {
			//获取商品数据
			Goods goods=goodsService.getGoodsById(id);
			//封装:商品数据,转发
			modelMap.addAttribute("goods", goods);
			//转发
			return "goods_details";
		}
}
