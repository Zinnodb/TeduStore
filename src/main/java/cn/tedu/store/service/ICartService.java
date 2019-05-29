package cn.tedu.store.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.tedu.store.entity.Cart;

public interface ICartService {
	
	/**
	 * ִ�й��ﳵ��Ӳ���		
	 * @param uid	�û�id
	 * @param goodsId  ��Ʒid
	 */
	void addToCart(Integer uid, Long goodsId,Integer num);
	
	/**
	 * ��ȡ��ǰ��ӵ���Ʒ��Ϣ
	 * @param uid �û�id
	 * @param goodsId ����id
	 * @return ��Ʒ��Ϣ
	 */
	Cart getCartByUidAndGoodsId(Integer uid, Long goodsId);
	
	/**
	 * �޸���Ʒ������
	 * @param id  ��Ʒid
	 * @param goodsNum ��Ʒ����
	 * @return
	 */
	Integer changeGoodsNum(@Param("id") Integer id, @Param("goodsNum") Integer goodsNum);
	
	/**
	 * ��ȡĳ�û��Ĺ��ﳵ�����б�
	 * @param uid �û�id
	 * @return ���ﳵ�����б�
	 */
	List<Cart> getListByUid(Integer uid);
	
	/**
	 * ��������id��ȡ���ﳵ����
	 * @param id ����id
	 * @return ���ﳵ����
	 */
	Cart getCartById(Integer id);
}
