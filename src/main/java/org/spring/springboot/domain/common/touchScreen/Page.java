package org.spring.springboot.domain.common.touchScreen;

import java.util.ArrayList;
import java.util.List;

public class Page {
	private List list = new ArrayList();
	private long totalCount = 0;
	private Integer pageNo = 1;
	private long pageCount = 1;
	private Integer pageSize = 15;
	private String orderBy = "";
	private OrderType orderType = OrderType.desc;
	
	// 排序方式
	public enum OrderType{
		asc, desc
	}
	
	public List getList() {
		return list;
	}
	public void setList(List list) {
		this.list = list;
	}
	public long getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(long totalCount) {
		this.totalCount = totalCount;
	}
	public Integer getPageNo() {
		return pageNo;
	}
	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}
	public long getPageCount() {
		return pageCount;
	}
	public void setPageCount(long pageCount) {
		this.pageCount = pageCount;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public String getOrderBy() {
		return orderBy;
	}
	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}
	public OrderType getOrderType() {
		return orderType;
	}
	public void setOrderType(OrderType orderType) {
		this.orderType = orderType;
	}
}
