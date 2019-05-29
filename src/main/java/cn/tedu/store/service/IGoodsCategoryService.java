package cn.tedu.store.service;

import java.util.List;

import cn.tedu.store.entity.GoodsCategory;

public interface IGoodsCategoryService {
	
	/**
	 * ���ݸ���id���з����ѯ
	 * @param parentId �����id
	 * @return	���ز�ѯ��������Ʒ��Ϣ
	 */
	List<GoodsCategory> getListByParentId(Long parentId);
}
