package com.gx.web;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.gx.dao.PredetermineDao;
import com.gx.page.Page;
import com.gx.po.AttributePo;
import com.gx.po.PassengerPo;
import com.gx.po.PredeterminePo;
import com.gx.po.ReceiveTargetPo;
import com.gx.po.RoomSetPo;
import com.gx.po.StayRegisterPo;
import com.gx.service.AttributeService;
import com.gx.service.PassengerService;
import com.gx.service.PredetermineService;
import com.gx.service.ReceiveTargetService;
import com.gx.service.RoomSetService;
import com.gx.service.StayRegisterService;

@Controller
@RequestMapping("/Predetermine")
public class Predetermine {
	
	@Autowired
	private PassengerService passengerService;
	
	@Autowired
	private ReceiveTargetService receiveTargetService;
	
	@Autowired
	private AttributeService attributeService;
	
	@Autowired
	private RoomSetService roomSetService;
	
	
	@Autowired
	private PredetermineService predetermineService;  //预定
	
	@Autowired
	private StayRegisterService stayRegisterService;
	
	int idP[];     //预订id
	int fangJianId[];
	
	
	//客房预定（查询出以预定啦人的信息）
	@RequestMapping("/tolist")
	public ModelAndView tolist(HttpServletRequest request, Integer currentPage, String txtname,Integer state){
			                      
		ModelAndView mv=null;
	
	    Date date=new Date();
 	    DateFormat dformat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");   //设置日期格式
	    Timestamp timestamp=Timestamp.valueOf(dformat.format(date)) ;     //将当前时间转为字符串
		   
	    
	    
	     //74到83行没什么用，就是查出已预定的用户的所有信息，看他们要不要修改预订到时提示
		List<PredeterminePo> list=this.predetermineService.selectAll();  //查询已预定的用户的所有信息
		long shiJianCha;//时间差
		for (int i = 0; i < list.size(); i++) {
			
			Timestamp huoQuShiJian=list.get(i).getArriveTime();           //获取到达时间
			shiJianCha=timestamp.getTime()-huoQuShiJian.getTime();        //计算时间差      = 当前时间-到达时间
			if (shiJianCha>=0) {
				Integer huoQuId=list.get(i).getId();//获取旅客的id
				this.predetermineService.updateRemind(huoQuId);//根据旅客的id修改预订到时提示
			}
			
			
		}
		
		if (currentPage==null) {
			currentPage=1;
		}else if (currentPage==0) {
			currentPage=1;
		}
		
		/*state=66是默认查询未安排的*/
		if (state==null) {
			state=66;
		}
		if(txtname==null)
		{
			txtname="";
		}
		
		List<AttributePo> listOne=attributeService.selectPredetermineState(); //查询出预定状态有二总  安排和为安排
		
	    	Page page=new Page();
		    page.setCurrentPage(currentPage);
		
		  //下面是大查询
    	 List<PredeterminePo> list2=predetermineService.pageFuzzyselect(txtname, txtname, state, page.getCurrentPage());
		
		mv=new ModelAndView("/predetermine/list");
		mv.addObject("listOne",listOne);
		mv.addObject("txtname",txtname);
		mv.addObject("state",state);
		mv.addObject("list",list2);
		return mv;
	}
	
	
	
	
	//ajax旅客
	@ResponseBody
	@RequestMapping(value="/selectPassenger")
	public Object selectPassenger(String txtname){
		if(txtname==null){
			txtname="";
		}
		List<PassengerPo> list=passengerService.selectAjaxList(txtname);
       Gson gson=new Gson();
       return gson.toJson(list);
	}
	
	
	//点击选择对象查询出来的所有数据
	@ResponseBody
	@RequestMapping(value="/selectTarget")
	public Object selectTarget(String txtname){
		if (txtname==null) {
			txtname="";
		}
 	   List<ReceiveTargetPo> list=receiveTargetService.ajaxSelect(txtname);
       Gson gson=new Gson();
       System.out.println(gson.toJson(list));
       return gson.toJson(list); //gson方法先转为json串
	}
	
	
	
	
	@ResponseBody
	@RequestMapping(value="/confirmPassenger")
	
	public Object confirmPassenger(Integer id){
 	   PassengerPo list=passengerService.selectById(id);
       Gson gson=new Gson();
       return gson.toJson(list.getContactPhoneNumber());
	}
	
	
	//去新增界面
	@RequestMapping("/toadd")
	public ModelAndView toadd(Integer id,String name,Integer type){
		ModelAndView mv=null;
		List<AttributePo> listOne=attributeService.selectPayWay();
		mv=new ModelAndView("/predetermine/add");
		mv.addObject("id",id);
		mv.addObject("name",name);
		mv.addObject("type",type);
		mv.addObject("listOne",listOne);
		return mv;
	}
	
	//新增
	@RequestMapping("/add")
	public ModelAndView add(Integer id,Integer type, String roomIdShuZu,PredeterminePo po,Integer pangduan){
		ModelAndView mv=null;
		if(pangduan!=null){
			for (int i = 0; i < idP.length; i++) {
				predetermineService.deleteById(idP[i]);
			}
			RoomSetPo roomSetPo=new RoomSetPo();
			roomSetPo.setRoomStateID(1);                       //将此房态设置为空房
			for (int i = 0; i < fangJianId.length; i++) {
				
				roomSetPo.setId(fangJianId[i]);
	            roomSetService.updateByIdToRoomState(roomSetPo);    //修改此房态
			}
		}
		String[] FenGe=roomIdShuZu.split(",");             //分割为数组
		int changDu=FenGe.length;                          //获取数组长度
		double yaJin=po.getDeposit();                      //获取押金             
		double yaJinFenGe=yaJin/changDu;                   //得到平均押金
		po.setDeposit(yaJinFenGe);                         //设po的押金
		po.setPredetermineStateID(66);                     //设置预订状态为未按排
		if(type==1){                                       //如果为团队
			po.setPassengerID(0);                          //设置预订旅客id为0
			po.setPredetermineTargetID(id);                //设置团队id
		}else if(type==2){                                 //判断是否为旅客
			po.setPassengerID(id);                         //给预订赋值旅客id
			po.setPredetermineTargetID(2);                 //设置团队id
		}
		RoomSetPo roomSetPo=new RoomSetPo();
		roomSetPo.setRoomStateID(4);                       //设置此房态为预订
		for(int i=0;i<changDu;i++){
			po.setRoomID(Integer.parseInt(FenGe[i]));      //给预订设置房间id
			predetermineService.insertAll(po);             //新增预订单
			roomSetPo.setId(Integer.parseInt(FenGe[i]));   //更改房态的 获取房间id
			roomSetService.updateByIdToRoomState(roomSetPo);//修改房态为预订
		}
		mv=new ModelAndView("redirect:/Predetermine/tolist.do");
		return mv;
	}
	
	
	@ResponseBody
	@RequestMapping(value="/selectRoom")
	public Object selectRoom(String roomNumber){
	   if(roomNumber==null){
		   roomNumber="";
	   }
	   List<RoomSetPo> list=roomSetService.selectInformation(roomNumber);
       Gson gson=new Gson();
       return gson.toJson(list);
	}
	
	//去修改界面
	@RequestMapping("/toupdate")
	public ModelAndView toupdate(Integer id){
		idP=null;
		fangJianId=null;
		ModelAndView mv=null;
		int idid=0;
		PredeterminePo zuwenPo=predetermineService.findById(id);        //根据预订id来查询预订数据
		int lvKeId=zuwenPo.getPassengerID();                            //获取预订旅客id
		int teamId=zuwenPo.getPredetermineTargetID();                   //获取预订团队id
		List<PredeterminePo> teamList=null;
		List<PredeterminePo> lvKeList=null;
		List<AttributePo> listOne=attributeService.selectPayWay();
		double yaJin=0;
		int zhengShu=0;
		String nameString="";
		List<RoomSetPo> roomSetPolist=new ArrayList<RoomSetPo>();
		RoomSetPo roomSetPo=null;
		int type=0;
		mv=new ModelAndView("/predetermine/update");
		if (lvKeId==0) {
			idid=teamId;
			teamList=predetermineService.findTeamId(teamId);
			idP=new int[teamList.size()];
			fangJianId=new int[teamList.size()];
			for (int i = 0; i < teamList.size(); i++) {
				yaJin+=teamList.get(i).getDeposit();
				roomSetPo=roomSetService.selectById(teamList.get(i).getRoomID());
				roomSetPolist.add(roomSetPo);
				idP[i]=teamList.get(i).getId();
				fangJianId[i]=teamList.get(i).getRoomID();
			}
            zhengShu=(int)yaJin;
            mv.addObject("listList",teamList);
            nameString=teamList.get(0).getReceiveTeamName();
            type=1;
		}else {
			idid=lvKeId;
			lvKeList=predetermineService.findLvKeId(lvKeId);
			idP=new int[lvKeList.size()];
			fangJianId=new int[lvKeList.size()];
			for (int i = 0; i < lvKeList.size(); i++) {
				yaJin+=lvKeList.get(i).getDeposit();
				roomSetPo=roomSetService.selectById(lvKeList.get(i).getRoomID());
				roomSetPolist.add(roomSetPo);
				idP[i]=lvKeList.get(i).getId();
				fangJianId[i]=lvKeList.get(i).getRoomID();
			}
            zhengShu=(int)yaJin;
            mv.addObject("listList",lvKeList);
            nameString=lvKeList.get(0).getPassengerName(); 
            type=2;
		}
		mv.addObject("id",idid);
		mv.addObject("listOne",listOne);
		mv.addObject("roomSetPolist",roomSetPolist);
		mv.addObject("zhengShu",zhengShu);
		mv.addObject("name",nameString);
		mv.addObject("type",type);
		mv.addObject("pangduan",1);
		return mv;
	}
	
	//修改房间，移除房间时更改房态
	/*@RequestMapping("/changRoomState")
	public ModelAndView changRoomState(){
		ModelAndView mv=null;
		
		return mv;
	}*/
	
	
	//修改房间，移除房间时更改房态
	@RequestMapping("/delete")
	public ModelAndView delete(String id){
		ModelAndView mv=null;
		String[] FenGe=id.split(",");
		
		for (int i = 0; i < FenGe.length; i++) {
			this.predetermineService.deleteById(Integer.parseInt(FenGe[i]));
		}
		
		mv=new ModelAndView("redirect:/Predetermine/tolist.do");
		return mv;
	}
	
	//修改房间，移除房间时更改房态(把空房改成預定)
	@RequestMapping("/arrangeRoom")
	public ModelAndView arrangeRoom(String id,Integer tiaoZhuang){
		ModelAndView mv=null;
		Date date=new Date();
		DateFormat dformat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");    //设置日期格式
		Timestamp timestamp=Timestamp.valueOf(dformat.format(date)) ;      //将当前时间转为字符串
		 
		String[] FenGe=id.split(",");                                      //分割到数组
		
		 // StayRegisterPo住宿表  , RoomSetPo客房设置表
		    StayRegisterPo stayRegisterPo=new StayRegisterPo();
	    	RoomSetPo roomSetPoToRoomState=new RoomSetPo();
	    	
		for (int i = 0; i < FenGe.length; i++) {
            this.predetermineService.updatePredetermineStateID(Integer.parseInt(FenGe[i]));              //修改预订状态(把空房改成預定)
            PredeterminePo predeterminePo=this.predetermineService.findById(Integer.parseInt(FenGe[i])); //查询预订信息
            RoomSetPo roomSetPo=this.roomSetService.selectById(predeterminePo.getRoomID());              //查询房间信息
            
            stayRegisterPo.setRoomID(predeterminePo.getRoomID());     //设置房间id
            stayRegisterPo.setRentOutTypeID(26);                      //设置出租方式为“全日”
            stayRegisterPo.setPassengerTypeID(29);                    //旅客是国内的
            if (predeterminePo.getPassengerID()==0) {                                             //判断是否为团队还是散客
            	stayRegisterPo.setBillUnitID(28);                                                 //结账单位ID     28是团队付款
			}else {
				stayRegisterPo.setBillUnitID(27);                                                 //旅客自付
			}
            stayRegisterPo.setReceiveTargetID(predeterminePo.getPredetermineTargetID());          //接待对象ID
            stayRegisterPo.setIsBillID(68);                                                       //结账否      68，69在数据库中代表是否结账
            stayRegisterPo.setRegisterTime(timestamp);                                            //登记时间
            stayRegisterPo.setStayNumber(predeterminePo.getPredetermineDay());                    //预定天数
            stayRegisterPo.setSumConst(roomSetPo.getStandardPriceDay()*Integer.parseInt(predeterminePo.getPredetermineDay()));      //获取房价 乘以 住宿天数
            stayRegisterService.insertAll(stayRegisterPo); //向住宿登记表stayregister添加一條記錄
            
            
            Integer stayId=stayRegisterPo.getId();//获取刚新增的 住宿登记ID
            System.out.println("获得到的預定id为"+Integer.parseInt(FenGe[i])+"新增的住宿登记ID"+stayId);
            stayRegisterService.updateStayRegisterPredetermineID(Integer.parseInt(FenGe[i]), stayId);//修改 预定id
            
            
            stayRegisterPo.setDepositStayRegisterID(stayId);
    		stayRegisterPo.setDepositRegisterTime(timestamp);
    		stayRegisterPo.setDepositPayWayID(predeterminePo.getPayWayID());
    		stayRegisterPo.setDeposit(predeterminePo.getDeposit());
  
    		stayRegisterService.insertDeposit(stayRegisterPo);                                        //新增押金明细
    		
    		
    		//roomSetPoToRoomState是RoomSetPo客房设置表對象
    		roomSetPoToRoomState.setId(predeterminePo.getRoomID());      //给新的 Po 赋房间ID的值
    		roomSetPoToRoomState.setRoomStateID(65);                     //给新的 Po 赋房态的值
    		roomSetService.updateByIdToRoomState(roomSetPoToRoomState);  //根据 房间ID 来修改 当前被选中的房间的房态
    	}
		if (tiaoZhuang==1) {
			mv=new ModelAndView("redirect:/StayRegister/tolist.do");
		}else if (tiaoZhuang==2){
			mv=new ModelAndView("redirect:/Predetermine/tolist.do");
		}
		return mv;
	}
		
		
}
