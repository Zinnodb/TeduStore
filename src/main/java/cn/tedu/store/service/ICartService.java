package cn.tedu.store.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.tedu.store.entity.Cart;

public interface ICartService {
	
	/**
	 * 执行购物车添加操作		
	 * @param uid	用户id
	 * @param goodsId  商品id
	 */
	void addToCart(Integer uid, Long goodsId,Integer num);
	
	/**
	 * 获取当前添加的商品信息
	 * @param uid 用户id
	 * @param goodsId 货物id
	 * @return 商品信息
	 */
	Cart getCartByUidAndGoodsId(Integer uid, Long goodsId);
	
	/**
	 * 修改商品的数量
	 * @param id  商品id
	 * @param goodsNum 商品数量
	 * @return
	 */
	Integer changeGoodsNum(@Param("id") Integer id, @Param("goodsNum") Integer goodsNum);
	
	/**
	 * 获取某用户的购物车数据列表
	 * @param uid 用户id
	 * @return 购物车数据列表
	 */
	List<Cart> getListByUid(Integer uid);
	
	/**
	 * 根据数据id获取购物车数据
	 * @param id 数据id
	 * @return 购物车数据
	 */
	Cart getCartById(Integer id);
}
