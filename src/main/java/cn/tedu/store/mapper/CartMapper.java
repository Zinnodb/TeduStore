package cn.tedu.store.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.tedu.store.entity.Cart;

public interface CartMapper {
	/**
	 * 进行购物车商品添加
	 * @param cart 商品信息
	 * @return 受影响的行数
	 */
	Integer	insert(Cart cart);
	
	/**
	 * 获取当前添加的商品信息
	 * @param uid 用户id
	 * @param goodsId 货物id
	 * @return 商品信息
	 */
	Cart getCartByUidAndGoodsId(@Param("uid") Integer uid, @Param("goodsId") Long goodsId);
	
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
	 * 根据商品id获取商品信息
	 * @param id 商品id
	 * @return 根据商品id获取商品信息
	 */
	Cart getCartById(Integer id);
}
