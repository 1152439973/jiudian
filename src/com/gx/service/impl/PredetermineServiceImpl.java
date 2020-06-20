package com.gx.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gx.dao.PredetermineDao;
import com.gx.page.Page;
import com.gx.po.PredeterminePo;
import com.gx.service.PredetermineService;

@Transactional
@Service(value="predetermineService")
public class PredetermineServiceImpl implements PredetermineService {

	@Autowired
	private PredetermineDao predetermineDao;
	private List<PredeterminePo> list2;
	
	@Override
	public int insertAll(PredeterminePo predeterminePo) {
		return predetermineDao.insertAll(predeterminePo);
	}
	
	//大查询   （Page<PredeterminePo> vo里面有当前页  ，predetermineStateID是安排的状态）
	@Override
	public List<PredeterminePo> pageFuzzyselect(String receiveTeamName,    String passengerName,                             
			                                      int  predetermineStateID,Integer currentPage  )                                                  
	                   {
	                     Page  zzz = new Page();
		
				         //start 当前页  = 当前页面减一*每页显示的数据量
					     int start=0;
					     if (currentPage>1) {
				                  start=(currentPage-1)*zzz.getPageSize();
						  }
				
				          List<PredeterminePo> list=predetermineDao.pageFuzzyselect(receiveTeamName,
							                                                        passengerName, 
							                                                        predetermineStateID, 
							                                                        start,
							                                                        zzz.getPageSize());
				          
	           int count=predetermineDao.countFuzzyselect(receiveTeamName, passengerName, predetermineStateID);
	           zzz.setTotal(count);
	
	return list;
}
	
	
	@Override
	public PredeterminePo findById(Integer id) {
		return this.predetermineDao.findById(id);
	}
	@Override
	public List<PredeterminePo> findLvKeId(Integer id) {
		return this.predetermineDao.findLvKeId(id);
	}
	@Override
	public List<PredeterminePo> findTeamId(Integer id) {
		return this.predetermineDao.findTeamId(id);
	}
	@Override
	public int deleteById(Integer id) {
		return this.predetermineDao.deleteById(id);
	}
	@Override
	public List<PredeterminePo> selectAll() {
		return this.predetermineDao.selectAll();
	}
	@Override
	public int updateRemind(Integer id) {
		return this.predetermineDao.updateRemind(id);
	}
	@Override
	public int updatePredetermineStateID(Integer id) {
		return this.predetermineDao.updatePredetermineStateID(id);
	}

}
