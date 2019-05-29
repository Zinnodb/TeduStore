package cn.tedu.store.entity;

import java.io.Serializable;
import java.util.Date;

public class OrderItem implements Serializable {
	 	
	
	
	private static final long serialVersionUID = 6321354544887880416L;
	
	private int  id;
	private int orderId;
	private Long goodsId;
	private String goodsImage;
	private String goodsTitle;
	private Long goodsPrice;
	private int goodsNum;
	private String createdUser;
	private Date createdTime;
	private String  modifiedUser;
	private Date modifiedTime;
	
	public OrderItem() {
		super();
	}
	
	public OrderItem(Long goodsId, String goodsImage, String goodsTitle, Long goodsPrice, int goodsNum) {
		super();
		setGoodsId(goodsId);
		setGoodsImage(goodsImage);
		setGoodsTitle(goodsTitle);
		setGoodsNum(goodsNum);
		setGoodsPrice(goodsPrice);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public Long getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(Long goodsId) {
		this.goodsId = goodsId;
	}

	public String getGoodsImage() {
		return goodsImage;
	}

	public void setGoodsImage(String goodsImage) {
		this.goodsImage = goodsImage;
	}

	public String getGoodsTitle() {
		return goodsTitle;
	}

	public void setGoodsTitle(String goodsTitle) {
		this.goodsTitle = goodsTitle;
	}

	public Long getGoodsPrice() {
		return goodsPrice;
	}

	public void setGoodsPrice(Long goodsPrice) {
		this.goodsPrice = goodsPrice;
	}

	public int getGoodsNum() {
		return goodsNum;
	}

	public void setGoodsNum(int goodsNum) {
		this.goodsNum = goodsNum;
	}

	public String getCreatedUser() {
		return createdUser;
	}

	public void setCreatedUser(String createdUser) {
		this.createdUser = createdUser;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public String getModifiedUser() {
		return modifiedUser;
	}

	public void setModifiedUser(String modifiedUser) {
		this.modifiedUser = modifiedUser;
	}

	public Date getModifiedTime() {
		return modifiedTime;
	}

	public void setModifiedTime(Date modifiedTime) {
		this.modifiedTime = modifiedTime;
	}

	@Override
	public String toString() {
		return "OrderItem [id=" + id + ", orderId=" + orderId + ", goodsId=" + goodsId + ", goodsImage=" + goodsImage
				+ ", goodsTitle=" + goodsTitle + ", goodsPrice=" + goodsPrice + ", goodsNum=" + goodsNum
				+ ", createdUser=" + createdUser + ", createdTime=" + createdTime + ", modifiedUser=" + modifiedUser
				+ ", modifiedTime=" + modifiedTime + "]";
	}
	
	
	
}
