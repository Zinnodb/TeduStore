package cn.tedu.store.entity;

import java.io.Serializable;
import java.util.Date;

public class Goods implements Serializable{
	
	private static final long serialVersionUID = 1471216889689752516L;
		
		private Long id;
		private Long	categoryId;
		private String itemType;
		private String title;
		private String sellPoint;
		private Long price;
		private Integer num;
		private String barcode;
		private  String image;
		private Integer status;
		private Integer priority;
		private Date createTime;
		private Date modifiedTime;
		private String createUser;
		private String modifiedUser;
		
		public Goods() {
			super();
		}

		
		public Long getId() {
			return id;
		}



		public void setId(Long id) {
			this.id = id;
		}

		public Long getCategoryId() {
			return categoryId;
		}

		public void setCategoryId(Long categoryId) {
			this.categoryId = categoryId;
		}

		public String getItemType() {
			return itemType;
		}

		public void setItemType(String itemType) {
			this.itemType = itemType;
		}

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public String getSellPoint() {
			return sellPoint;
		}

		public void setSellPoint(String sellPoint) {
			this.sellPoint = sellPoint;
		}

		public Long getPrice() {
			return price;
		}

		public void setPrice(Long price) {
			this.price = price;
		}

		public Integer getNum() {
			return num;
		}

		public void setNum(Integer num) {
			this.num = num;
		}

		public String getBarcode() {
			return barcode;
		}

		public void setBarcode(String barcode) {
			this.barcode = barcode;
		}

		public String getImage() {
			return image;
		}

		public void setImage(String image) {
			this.image = image;
		}

		public Integer getStatus() {
			return status;
		}

		public void setStatus(Integer status) {
			this.status = status;
		}

		public Integer getPriority() {
			return priority;
		}

		public void setPriority(Integer priority) {
			this.priority = priority;
		}

		public Date getCreateTime() {
			return createTime;
		}

		public void setCreateTime(Date createTime) {
			this.createTime = createTime;
		}

		public Date getModifiedTime() {
			return modifiedTime;
		}

		public void setModifiedTime(Date modifiedTime) {
			this.modifiedTime = modifiedTime;
		}

		public String getCreateUser() {
			return createUser;
		}

		public void setCreateUser(String createUser) {
			this.createUser = createUser;
		}

		public String getModifiedUser() {
			return modifiedUser;
		}

		public void setModifiedUser(String modifiedUser) {
			this.modifiedUser = modifiedUser;
		}


		
		
}
