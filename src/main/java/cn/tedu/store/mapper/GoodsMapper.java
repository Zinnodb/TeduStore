package cn.tedu.store.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.tedu.store.entity.Goods;

public interface GoodsMapper {
	
	
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
		 * @param offset  ƫ����
		 * @param count  ��ʾ����
		 * @return	 ������Ʒ��Ϣ
		 */
		List<Goods> getListByCategoryId(@Param("categoryId")Long categoryId,@Param("offset")Integer offset,@Param("count")Integer count);

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
