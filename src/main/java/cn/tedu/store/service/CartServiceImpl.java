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
		 // ��ѯ���ݱ����Ƿ��Ѿ���������:
		Cart cart=getCartByUidAndGoodsId(uid, goodsId);
		System.out.println("ҵ���"+uid+"��Ʒ��Ϣ"+cart);
	    // �жϷ���ֵ�Ƿ�Ϊnull
		if (cart==null) {
			//���Ϊnull,������Ӳ���
			insert(uid, goodsId, num);
		}else {
			//������Ʒ����,����Ʒ���������ж�
			//��ȡ��Ʒid
			Integer id=cart.getId();
			//��ȡ������Ʒ����
			Integer n=cart.getGoodsNum();
			//�����µ�����
			Integer goodsNum=num+n;
			//�ж��������Ƿ�����߼�
			if (goodsNum>0) {
				changeGoodsNum(id, goodsNum);
				System.out.println("ҵ���ִ���޸ĳɹ�");
			}else {
				throw new IllegalParamException("���������ﳵ����Ʒ�����޸�ΪС��1��ֵ!");
			}
		}
	}
	
	private void insert(
		    Integer uid, Long goodsId, Integer num) {
		    // ͨ��goodsId��ȡ��Ʒ��Ϣ��image, price, title
		    Goods goods = goodsService.getGoodsById(goodsId);
		    // ��װCart����
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
		    // ִ������
		    Integer rows = cartMapper.insert(cart);
		    // �ж����ӽ��
		    if (rows != 1) {
		        throw new InsertDataException(
		            "��ӹ��ﳵ��Ϣʧ�ܣ�" + cart);
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
