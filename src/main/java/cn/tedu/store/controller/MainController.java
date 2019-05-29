package cn.tedu.store.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.tedu.store.entity.Goods;
import cn.tedu.store.entity.GoodsCategory;
import cn.tedu.store.service.IGoodsCategoryService;
import cn.tedu.store.service.IGoodsService;

@Controller
@RequestMapping("/main")
public class MainController extends BaseController {
		
		@Autowired
		private IGoodsCategoryService goodsCategoryService;
		
		@Autowired
		private IGoodsService goodsService;
		
		@RequestMapping("/index.do")
		public String showIndex(ModelMap modelMap) {
			//声明变量
			Long [] parentIds= {162L,171L,186L};
			Long categoryId=163L;
			Integer count=3;
			//获取数据:电脑本机下面的信息
			List<List<GoodsCategory>> goodsCategoriesList=new ArrayList<List<GoodsCategory>>();
			for (int i = 0; i < parentIds.length; i++) {
				List<GoodsCategory> goodsCategories=goodsCategoryService.getListByParentId(parentIds[i]);
				goodsCategoriesList.add(goodsCategories);
			}
			
			//获取热门商品数据
			
			List<Goods> goods=goodsService.getHotGoods(categoryId, count);
			
			modelMap.addAttribute("goodsCategoriesList", goodsCategoriesList);
			modelMap.addAttribute("goods", goods);
			return "index";
		}
}
