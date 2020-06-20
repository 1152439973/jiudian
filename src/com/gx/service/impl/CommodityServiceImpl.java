package com.gx.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gx.dao.CommodityDao;
import com.gx.page.Page;
import com.gx.po.CommodityPo;
import com.gx.service.CommodityService;

@Transactional
@Service(value="commodityService")
public class CommodityServiceImpl implements CommodityService {

	@Autowired
	private CommodityDao commodityDao;
	
	
	
	
	//删除
	@Override
	public int deleteById(Integer id) {
		return commodityDao.deleteById(id);
	}

	
	//新增
	@Override
	public int insertAll(CommodityPo commodityPo) {
               commodityDao.insertAll(commodityPo); //新增商品
          
     return   commodityDao.insertAll2(commodityPo.getId(),commodityPo.getHuiyuanjia());//往会员表加对应关系

	}

	//根据id查询商品的所有信息
	@Override
	public CommodityPo selectById(Integer id) {
		return commodityDao.selectById(id);
	}
	

	//真正的修改
	@Override
	public int updateById(CommodityPo commodityPo) {
		         commodityDao.updateById(commodityPo);   //修改商品普通属性
		return   commodityDao.updateById2(commodityPo.getId(),commodityPo.getHuiyuanjia());   //修改商品会员价格
	}

	
	//查询所有商品数据
	@Override
	public Page<CommodityPo> pageFuzzyselect(String commodityName,
		                                     int    commodityTypeID, 
		                                     Page   <CommodityPo> vo) {
		//start 当前页  = 当前页面减一*每页显示的数据量
		  int start=0; 
		  if (vo.getCurrentPage()>1) {
			start=(vo.getCurrentPage()-1)*vo.getPageSize();
		   }
		  
		  List<CommodityPo> list=commodityDao.pageFuzzyselect(commodityName, commodityTypeID, start, vo.getPageSize());
		  vo.setResult(list);
		  
		  int count=commodityDao.countFuzzyselect(commodityName, commodityTypeID);
		  vo.setTotal(count);//总条数
	   	  return vo;
	   	  
	}

	
	@Override
	public List<CommodityPo> fuzzySelect(String commodityName,int commodityTypeID) {
		return commodityDao.fuzzySelect(commodityName, commodityTypeID);
	}

	@Override
	public int selectYZ(String commodityName) {
		return this.commodityDao.selectYZ(commodityName);
	}

}
