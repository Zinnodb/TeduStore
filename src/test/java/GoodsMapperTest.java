
import java.util.List;

import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.tedu.store.entity.Goods;
import cn.tedu.store.entity.GoodsCategory;
import cn.tedu.store.mapper.GoodsCategoryMapper;
import cn.tedu.store.service.IGoodsService;

public class GoodsMapperTest {
		
	@Test
	public void testGetListByParentIdd() {
		AbstractApplicationContext ac=new ClassPathXmlApplicationContext("spring-dao.xml");
		GoodsCategoryMapper addressMapper=ac.getBean("goodsCategoryMapper",GoodsCategoryMapper.class);
		List<GoodsCategory> row=addressMapper.getListByParentId( (long) 161);
		System.out.println(row);
	}
		
	@Test
	public void testGetHotGoodsList() {
		AbstractApplicationContext ac=new ClassPathXmlApplicationContext("spring-dao.xml","spring-service.xml");
		IGoodsService goodsService
		= ac.getBean("goodsService", 
				IGoodsService.class);
		Long categoryId=163L;
		Integer count=5;
		List<Goods> row=goodsService.getHotGoods(categoryId, count);
		for (Goods goods : row) {
			System.out.println(goods);
		}
	}
}
