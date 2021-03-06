package cn.tedu.store.service;

import java.util.List;

import cn.tedu.store.entity.GoodsCategory;

public interface IGoodsCategoryService {
	
	/**
	 * 根据父类id进行分类查询
	 * @param parentId 父类的id
	 * @return	返回查询出来的商品信息
	 */
	List<GoodsCategory> getListByParentId(Long parentId);
}
