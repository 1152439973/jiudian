package com.gx.page;
import java.util.ArrayList;
import java.util.List;

import com.gx.po.RoomSetPo;


public class Page2<T> {
	
	private Integer currentPage=1;// 当前页码
	private Integer pageSize = 5;// 每页显示的条数
	private Integer totalCount;// 总条数
	private Integer totalPageCount;// 总页数

    
	private List<T> result=new ArrayList<T>();//结果集
	
	public List<T> getResult() {
		return result;
	}
	public void setResult(List<T> result) {
		this.result = result;
	}	

	public Integer getCurrentPage() {
		return currentPage;
	}
	
	
	//&&是并且的意思
	public void setCurrentPage(Integer currentPage) {
		  //当前页码>总页数
		if(currentPage>totalPageCount){
			//就显示最后一页
			this.currentPage = totalPageCount;
		}
		if(currentPage<=totalPageCount && currentPage >0){
			this.currentPage = currentPage;
		}
	}
	
	
	
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public Integer getTotalCount() {
		return totalCount;
	}
	
	//setTotalCount是总条数     ，根据总条数得到一共有多少页
	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
		
		//打个比方    一共查询出了5条数据 ，假如没页显示10条   ------5%10=0.5，小于1   ，所以总页数就只有1页
		if(totalCount/pageSize<1){
			totalPageCount=1;
		}else{
			           //第二句是判断   totalCount除pageSize余数是不是=0，
			           //等于0的话就totalPageCount=this.totalCount / this.pageSize
			           //不等于0就是第3句，totalPageCount要多加一页
			this.totalPageCount = 
					   this.totalCount % this.pageSize == 0 ? 
					  (this.totalCount / this.pageSize)
					: (this.totalCount / this.pageSize)+1;
		}
	}
	
	
	//总页数
	public Integer getTotalPageCount() {
		return totalPageCount;
	}
	public void setTotalPageCount(Integer totalPageCount) {
		this.totalPageCount = totalPageCount;
	}

	
}

