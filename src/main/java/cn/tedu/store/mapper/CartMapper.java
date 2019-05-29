package cn.tedu.store.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.tedu.store.entity.Cart;

public interface CartMapper {
	/**
	 * ���й��ﳵ��Ʒ���
	 * @param cart ��Ʒ��Ϣ
	 * @return ��Ӱ�������
	 */
	Integer	insert(Cart cart);
	
	/**
	 * ��ȡ��ǰ��ӵ���Ʒ��Ϣ
	 * @param uid �û�id
	 * @param goodsId ����id
	 * @return ��Ʒ��Ϣ
	 */
	Cart getCartByUidAndGoodsId(@Param("uid") Integer uid, @Param("goodsId") Long goodsId);
	
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
	 * ������Ʒid��ȡ��Ʒ��Ϣ
	 * @param id ��Ʒid
	 * @return ������Ʒid��ȡ��Ʒ��Ϣ
	 */
	Cart getCartById(Integer id);
}
