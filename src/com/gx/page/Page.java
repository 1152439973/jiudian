package com.gx.page;

import java.util.ArrayList;
import java.util.List;

public class Page<T> {

	private int pageSize=5;//页面大小

	private int currentPage=1; //当前页面

	private long total;//总条数
	
	private long totalPage;//总页数
	
	private String condition;  //查询的条件
	
	
	
	private List<T> result=new ArrayList<T>();//结果集
	

	
	
	public String getCondition() {
		if(condition != null) {
			return condition;
		}
		return null;
	}
	
	
	
	public void setCondition(String condition) {
		this.condition = condition;
	}
	
	
	
	
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public long getTotal() {
		return total;
	}
	public void setTotal(long total) {
		this.total = total;
	}
	
	public List<T> getResult() {
		return result;
	}
	public void setResult(List<T> result) {
		this.result = result;
	}
	
	/**
	 * 总页数   （用到啦   pageSize页面大小 ，total 总条数）
	 * */
	
	public long getTotalPage() {
		return this.total%this.pageSize != 0 ? this.total/this.pageSize + 1 : this.total/this.pageSize;
	}
	
	
	public void setTotalPage(long totalPage) {
		this.totalPage = totalPage;
	}
	
	
}
