package cn.tedu.store.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.tedu.store.entity.Goods;

public interface IGoodsService {
	
	 int COUNT_PRE_PAGE=20;
	/**
	 * 获得热门商品
	 * @param categoryId
	 * @param count
	 * @return
	 */
	List<Goods> getHotGoods(@Param("categoryId") Long categoryId,@Param("count") Integer count);
	
	/**
	 * 根据商品分类ID来获取商品信息
	 * @param categoryId 商品分类id	
	 * @param page 页数
	 * @see #COUNT_PRE_PAGE 
	 * @return	 返回商品信息
	 */
	List<Goods> getListByCategoryId(Long categoryId,Integer page);
	
	/**
	 * 获取某个分类下的商品的数量
	 * @param categoryId 分类id
	 * @return 商品的数量
	 */
	Integer getCountByCategoryId(Long categoryId);
	
	/**
	 * 根据商品分类的id
	 * @param id 商品的分类
	 * @return 商品详情
	 */
	Goods getGoodsById(Long id);
}
