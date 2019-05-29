package cn.tedu.store.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.tedu.store.entity.Goods;

public interface GoodsMapper {
	
	
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
		 * @param offset  偏移量
		 * @param count  显示数量
		 * @return	 返回商品信息
		 */
		List<Goods> getListByCategoryId(@Param("categoryId")Long categoryId,@Param("offset")Integer offset,@Param("count")Integer count);

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
