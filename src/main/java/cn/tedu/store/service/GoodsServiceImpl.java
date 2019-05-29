package cn.tedu.store.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.tedu.store.entity.Goods;
import cn.tedu.store.mapper.GoodsMapper;

@Service("goodsService")
public class GoodsServiceImpl implements IGoodsService {
	
	@Autowired 
	private GoodsMapper goodsMapper;
	
	public List<Goods> getHotGoods(Long categoryId, Integer count) {
		
		return goodsMapper.getHotGoods(categoryId, count);
	}

	public List<Goods> getListByCategoryId(Long categoryId, Integer page) {
		//判断page参数
		if (page==null || page<1) {
			page=1;
		}
		Integer goodsCount = getCountByCategoryId(
				categoryId);
		if (goodsCount==0) {
			return new ArrayList<Goods>();
		}
		int maxPage = (int) Math.ceil(1.0*goodsCount/COUNT_PRE_PAGE);
		
		if (page > maxPage) {
			page = maxPage;
		}
		Integer offset=COUNT_PRE_PAGE*(page-1);
		// 调用持久层的	getListByCategoryId()的方法
		List<Goods> goods=goodsMapper.getListByCategoryId(categoryId, offset, COUNT_PRE_PAGE);
		return goods;
	}

	public Integer getCountByCategoryId(Long categoryId) {
		return goodsMapper.getCountByCategoryId(categoryId);
	}

	public Goods getGoodsById(Long id) {
		return goodsMapper.getGoodsById(id);
	}

}
