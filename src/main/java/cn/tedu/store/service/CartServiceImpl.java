package cn.tedu.store.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.tedu.store.entity.Cart;
import cn.tedu.store.entity.Goods;
import cn.tedu.store.mapper.CartMapper;
import cn.tedu.store.service.ex.IllegalParamException;
import cn.tedu.store.service.ex.InsertDataException;

@Service("cartService")
public class CartServiceImpl implements ICartService {
	
	@Autowired
	private IGoodsService goodsService;
	@Autowired
	private CartMapper cartMapper;
	
	public void addToCart(Integer uid, Long goodsId, Integer num) {
		 // 查询数据表中是否已经存在数据:
		Cart cart=getCartByUidAndGoodsId(uid, goodsId);
		System.out.println("业务层"+uid+"商品信息"+cart);
	    // 判断返回值是否为null
		if (cart==null) {
			//如果为null,进行添加操作
			insert(uid, goodsId, num);
		}else {
			//更新商品数量,对商品数量进行判断
			//获取商品id
			Integer id=cart.getId();
			//获取已有商品数量
			Integer n=cart.getGoodsNum();
			//计算新的数量
			Integer goodsNum=num+n;
			//判断新数量是否符合逻辑
			if (goodsNum>0) {
				changeGoodsNum(id, goodsNum);
				System.out.println("业务层执行修改成功");
			}else {
				throw new IllegalParamException("不允许将购物车的商品数量修改为小于1的值!");
			}
		}
	}
	
	private void insert(
		    Integer uid, Long goodsId, Integer num) {
		    // 通过goodsId获取商品信息：image, price, title
		    Goods goods = goodsService.getGoodsById(goodsId);
		    // 封装Cart对象
		    Cart cart = new Cart();
		    cart.setUid(uid);
		    cart.setGoodsId(goodsId);
		    cart.setGoodsImage(goods.getImage());
		    cart.setGoodsPrice(goods.getPrice());
		    cart.setGoodsTitle(goods.getTitle());
		    cart.setGoodsNum(num);
		    cart.setCreatedUser(goods.getCreateUser());
		    cart.setCreatedTime(new Date());
		    cart.setModifiedUser(goods.getCreateUser());
		    cart.setModifiedTime(new Date());
		    // 执行增加
		    Integer rows = cartMapper.insert(cart);
		    // 判断增加结果
		    if (rows != 1) {
		        throw new InsertDataException(
		            "添加购物车信息失败：" + cart);
		    }
		}

	public Cart getCartByUidAndGoodsId(Integer uid, Long goodsId) {
		return cartMapper.getCartByUidAndGoodsId(uid, goodsId);
	}

	@Override
	public Integer changeGoodsNum(Integer id, Integer goodsNum) {
		return cartMapper.changeGoodsNum(id, goodsNum);
	}

	@Override
	public List<Cart> getListByUid(Integer uid) {
		return cartMapper.getListByUid(uid);
	}
	
	public Cart getCartById(Integer id) {
		return cartMapper.getCartById(id);
	}
}
