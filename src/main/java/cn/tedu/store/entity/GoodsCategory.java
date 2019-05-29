package cn.tedu.store.entity;

import java.io.Serializable;
import java.util.Date;

public class GoodsCategory implements Serializable {
		
	private static final long serialVersionUID = -651269407708081903L;
		
		private Long id;
		private Long parentId;
		private String name;
		private Integer status;
		private Integer sortOrder;
		private Integer isParent;
		private Date createdTime;
		private Date modifiedTime;
		private String createdUser;
		private String modifiedUser;
		
		
		public GoodsCategory() {
			super();
		}


		public Long getId() {
			return id;
		}


		public void setId(Long id) {
			this.id = id;
		}


		public Long getParentId() {
			return parentId;
		}


		public void setParentId(Long parentId) {
			this.parentId = parentId;
		}


		public String getName() {
			return name;
		}


		public void setName(String name) {
			this.name = name;
		}


		public Integer getStatus() {
			return status;
		}


		public void setStatus(Integer status) {
			this.status = status;
		}


		public Integer getSortOrder() {
			return sortOrder;
		}


		public void setSortOrder(Integer sortOrder) {
			this.sortOrder = sortOrder;
		}


		public Integer getIsParent() {
			return isParent;
		}


		public void setIsParent(Integer isParent) {
			this.isParent = isParent;
		}


		public Date getCreatedTime() {
			return createdTime;
		}


		public void setCreatedTime(Date createdTime) {
			this.createdTime = createdTime;
		}


		public Date getModifiedTime() {
			return modifiedTime;
		}


		public void setModifiedTime(Date modifiedTime) {
			this.modifiedTime = modifiedTime;
		}


		public String getCreatedUser() {
			return createdUser;
		}


		public void setCreatedUser(String createdUser) {
			this.createdUser = createdUser;
		}


		public String getModifiedUser() {
			return modifiedUser;
		}


		public void setModifiedUser(String modifiedUser) {
			this.modifiedUser = modifiedUser;
		}


		@Override
		public String toString() {
			return "GoodsCategory [id=" + id + ", parentId=" + parentId + ", name=" + name + ", status=" + status
					+ ", sortOrder=" + sortOrder + ", isParent=" + isParent + ", createdTime=" + createdTime
					+ ", modifiedTime=" + modifiedTime + ", createdUser=" + createdUser + ", modifiedUser="
					+ modifiedUser + "]";
		}
		
		
}
