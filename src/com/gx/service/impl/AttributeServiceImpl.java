package com.gx.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gx.dao.AttributeDao;
import com.gx.po.AttributePo;
import com.gx.service.AttributeService;

@Transactional
@Service(value="attributeService")
public class AttributeServiceImpl implements AttributeService {

	@Autowired
	private AttributeDao attributeDao;
	
	@Override
	public List<AttributePo> selectGuestRoomLevel() {
		return attributeDao.selectGuestRoomLevel();
	}

	@Override
	public List<AttributePo> selectRoomState() {
		return attributeDao.selectRoomState();
	}

	@Override
	public List<AttributePo> selectCommodityType() {
		return attributeDao.selectCommodityType();
	}

	@Override
	public List<AttributePo> selectUOM() {
		return attributeDao.selectUOM();
	}

	

	 //删除商品类别
	@Override
	public int deleteById(Integer id) {
			attributeDao.deleteById3(id);   //删除商品的会员价   i   //这SQL语句没打，是错的DELETE FROM huiyuan WHERE jiageid IN (SELECT id FROM commodity WHERE commodityTypeID = 80)就是这个
			attributeDao.deleteById2(id);   // 删除商品类别下面的关联的的一些数据
  return    attributeDao.deleteById(id);    // 根据id=80删除商品类别
	}

	@Override
	public int insertAll(int newid, String newname) {
		return attributeDao.insertAll(newid, newname);
	}

	@Override
	public List<AttributePo> selectTargetType() {
		return attributeDao.selectTargetType();
	}

	@Override
	public List<AttributePo> selectGender() {
		return attributeDao.selectGender();
	}

	@Override
	public List<AttributePo> selectNation() {
		return attributeDao.selectNation();
	}

	@Override
	public List<AttributePo> selectPassengerLevel() {
		return attributeDao.selectPassengerLevel();
	}

	@Override
	public List<AttributePo> selectEducationDegree() {
		return attributeDao.selectEducationDegree();
	}

	@Override
	public List<AttributePo> selectPapers() {
		return attributeDao.selectPapers();
	}

	@Override
	public List<AttributePo> selectThingReason() {
		return attributeDao.selectThingReason();
	}

	@Override
	public List<AttributePo> selectPassengerType() {
		return attributeDao.selectPassengerType();
	}

	@Override
	public List<AttributePo> selectBillUnit() {
		return attributeDao.selectBillUnit();
	}

	@Override
	public List<AttributePo> selectPayWay() {
		return attributeDao.selectPayWay();
	}

	@Override
	public List<AttributePo> selectRentOutType() {
		return attributeDao.selectRentOutType();
	}

	@Override
	public List<AttributePo> selectIsPay() {
		return attributeDao.selectIsPay();
	}

	@Override
	public List<AttributePo> selectPredetermineState() {
		return attributeDao.selectPredetermineState();
	}

}
