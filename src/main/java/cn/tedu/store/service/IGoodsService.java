package cn.tedu.store.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.tedu.store.entity.Goods;

public interface IGoodsService {
	
	 int COUNT_PRE_PAGE=20;
	/**
	 * ���������Ʒ
	 * @param categoryId
	 * @param count
	 * @return
	 */
	List<Goods> getHotGoods(@Param("categoryId") Long categoryId,@Param("count") Integer count);
	
	/**
	 * ������Ʒ����ID����ȡ��Ʒ��Ϣ
	 * @param categoryId ��Ʒ����id	
	 * @param page ҳ��
	 * @see #COUNT_PRE_PAGE 
	 * @return	 ������Ʒ��Ϣ
	 */
	List<Goods> getListByCategoryId(Long categoryId,Integer page);
	
	/**
	 * ��ȡĳ�������µ���Ʒ������
	 * @param categoryId ����id
	 * @return ��Ʒ������
	 */
	Integer getCountByCategoryId(Long categoryId);
	
	/**
	 * ������Ʒ�����id
	 * @param id ��Ʒ�ķ���
	 * @return ��Ʒ����
	 */
	Goods getGoodsById(Long id);
}
