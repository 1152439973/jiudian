package com.gx.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.support.HttpRequestHandlerServlet;
import org.springframework.web.servlet.ModelAndView;
import com.google.gson.Gson;
import com.gx.page.Page2;
import com.gx.po.AttributePo;
import com.gx.po.RoomSetPo;
import com.gx.service.AttributeService;
import com.gx.service.RoomSetService;

@Controller
@RequestMapping("/RoomSet")
public class RoomSet {
	
	@Autowired
	private AttributeService attributeService;
	
	@Autowired
	private RoomSetService roomSetService;
	
	//分页和模糊查询（查询客房管理）
	@SuppressWarnings("unchecked")
	
	
	@RequestMapping("/tolist")
	public ModelAndView list( HttpServletRequest request,Integer currentPage,String roomNumber, Model model){
		ModelAndView mv=null;
		mv=new ModelAndView("/roomset/roomset");  //返回到jsp页面
         Page2 page = new Page2();
         //判断条件
         if(roomNumber==null) {
        	 roomNumber="";
         }
        
         int shuliang =  roomSetService.countFuzzyselect(roomNumber);

	     //根据条件查询数量 得到一共有多少页
	      System.out.println("查询出的条数为wei"+shuliang);
	      page.setTotalCount(shuliang);//根据查询的数量到page类中可以推算出一共有几页
	     
	     if(currentPage!=null) {
		   page.setCurrentPage(Integer.valueOf(currentPage));/*把数字字符串转化成Integer*/
	    	}

	 	  System.out.println("条件"+roomNumber+"当前页"+page.getCurrentPage()+"显示数量"+page.getPageSize());
	 	
       List<RoomSetPo> shuju= roomSetService.pageFuzzyselect(roomNumber, page.getCurrentPage(),page.getPageSize());//（查询条件和当前页）调用方法查数据
       if(shuju!=null) {
    	   page.setResult(shuju);
       }else {
    	   shuju =null;
       }
		model.addAttribute("list", page);
		return mv;//上面定义的视图对象
	}
	

	
	
		

	//新增客房1
	@RequestMapping("/toadd")
	public ModelAndView toadd(){
		System.out.println("准备跳到新增页面");
		ModelAndView mv=null;
		mv=new ModelAndView("/roomset/add");
		
		List<AttributePo> listOne=attributeService.selectGuestRoomLevel(); //客房等级
		List<AttributePo> listTwo=attributeService.selectRoomState();      //客房状态
		
		mv.addObject("listOne",listOne);
		mv.addObject("listTwo",listTwo);
		return mv;
	}
	
	
	//新增客房2
	@RequestMapping("/add")
	public ModelAndView add(RoomSetPo roomSetPo){
		ModelAndView mv=null;
		mv=new ModelAndView("redirect:/RoomSet/tolist.do");
		roomSetService.insertAll(roomSetPo);
		return mv;
	}
	
	
	//修改1
	@RequestMapping("/toupdate")
	public ModelAndView toupdate(int id){
		ModelAndView mv=null;
		mv=new ModelAndView("/roomset/update");
		
		List<AttributePo> listOne=attributeService.selectGuestRoomLevel(); //客房等级
		List<AttributePo> listTwo=attributeService.selectRoomState();      //房间状态
		
		RoomSetPo listPo=roomSetService.selectById(id);                    //根据id查询出了这个房间的所有数据
		
		mv.addObject("listOne",listOne);
		mv.addObject("listTwo",listTwo);
		mv.addObject("listPo",listPo);
		return mv;
	}
	
	//修改2
	@RequestMapping("/update")
	public ModelAndView update(RoomSetPo roomSetPo){
		ModelAndView mv=null;
		roomSetService.updateById(roomSetPo);
		mv=new ModelAndView("redirect:/RoomSet/tolist.do");
		return mv;
	}
	
	
	
	
	//删除
	@RequestMapping("/delete")
	public ModelAndView delete(String id){
		ModelAndView mv=null;
		String[] FenGe=id.split(",");
		for (int i = 0; i < FenGe.length; i++) {
			roomSetService.deleteById(Integer.parseInt(FenGe[i]));
		}
		mv=new ModelAndView("redirect:/RoomSet/tolist.do");
		return mv;
	}
	
	
	
	
	
	
	
	
	@ResponseBody
	@RequestMapping(value="/YZ")
	public Object YZ(String roomNumber){
		int YorN=roomSetService.selectYZ(roomNumber);
		Gson gson =new Gson(); //Gson应该是个工具类。用来将数据转成json格式
		return gson.toJson(YorN);
	}
	
	
}
