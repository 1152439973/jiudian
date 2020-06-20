package com.gx.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gx.dao.RoomSetDao;
import com.gx.page.Page;
import com.gx.po.RoomSetPo;
import com.gx.service.RoomSetService;

@Transactional
@Service(value="roomSetService")
public class RoomSetServiceImpl implements RoomSetService {

	@Autowired
	private RoomSetDao roomSetDao;
	
	/*@Override
	public List<RoomSetPo> selectAll() {
		return roomSetDao.selectAll();
	}
*/
	@Override
	public int deleteById(Integer id) {
		return roomSetDao.deleteById(id);
	}

	@Override
	public int insertAll(RoomSetPo roomSetPo) {
		return roomSetDao.insertAll(roomSetPo);
	}

	@Override
	public RoomSetPo selectById(Integer id) {
		return roomSetDao.selectById(id);
	}

	
	//修改
	@Override
	public int updateById(RoomSetPo roomSetPo) {
		return roomSetDao.updateById(roomSetPo);
	}


	
//分页的模糊查询
	@Override
	//roomNumber是传过来的条件例如：房间号码，Page<RoomSetPo> vo是当前页面
	public List<RoomSetPo> pageFuzzyselect(String stdnum, int currentPage, int pageSize) {
	    return roomSetDao.pageFuzzyselect(stdnum,(currentPage-1)*pageSize,pageSize);
		
	
	}
	
	
	
	@Override
	public int countFuzzyselect(String roomNumber) {
		 return roomSetDao.countFuzzyselect(roomNumber);
		
	}
	
	
	

	@Override
	public List<RoomSetPo> selectAll() {
		return roomSetDao.selectAll();
	}

	@Override
	public List<RoomSetPo> selectByLeveId(Integer id) {
		return roomSetDao.selectByLeveId(id);
	}

	@Override
	public int updateByIdToRoomState(RoomSetPo roomSetPo) {
		return roomSetDao.updateByIdToRoomState(roomSetPo);
	}

	@Override
	public List<RoomSetPo> selectInformation(String roomNumber) {
		return roomSetDao.selectInformation(roomNumber);
	}

	@Override
	public List<RoomSetPo> levelSelectInformation(Integer guestRoomLevelID) {
		return this.roomSetDao.levelSelectInformation(guestRoomLevelID);
	}

	@Override
	public int selectYZ(String roomNumber) {
		return this.roomSetDao.selectYZ(roomNumber);
	}





}
