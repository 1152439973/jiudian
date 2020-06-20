package com.gx.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONArray;
import com.google.gson.Gson;
import com.gx.page.Page;
import com.gx.po.AttributePo;
import com.gx.po.CommodityPo;
import com.gx.po.RoomSetPo;
import com.gx.service.AttributeService;
import com.gx.service.CommodityService;


@Controller
@RequestMapping("/Commodity")
public class Commodity {

	@Autowired
	private CommodityService commodityService;
	
	@Autowired
	private AttributeService attributeService;
	
	
	
	//测试自定义异常  NullPointerException(空指针) 会先在Exception.java类进行处理然后跳到error1.jsp
	@RequestMapping("/show")
	public String showInfo(){
		String str = null;
		str.length();
		return "xxx";
	}
	
	
	
	@RequestMapping("/show2")
	public String showInfo2(){
		int a = 10/0;
		return "xxx2";
	}

	

	    //分页和模糊查询
		@RequestMapping("/tolist")
		public ModelAndView list(HttpServletRequest request, Integer currentPage, String txtname,Integer commodityTypeID){
			ModelAndView mv=null;
			List<AttributePo> listOne=attributeService.selectCommodityType();//查出所有商品类别

			mv=new ModelAndView("/commodity/list");  //jsp页面
			
			Page<CommodityPo> vo=new Page<CommodityPo>();
			if (commodityTypeID==null) {
				commodityTypeID=16;
			}
			if (currentPage==null) {
				currentPage=1;
			}else if (currentPage==0) {
				currentPage=1;
			}
			if(txtname==null)
			{
				txtname="";
			}
			vo.setCurrentPage(currentPage);
			vo=this.commodityService.pageFuzzyselect(txtname,commodityTypeID, vo);
			mv.addObject("list",vo);
			mv.addObject("txtname",txtname);
			mv.addObject("listOne",listOne);
			mv.addObject("commodityType",commodityTypeID);
			return mv;
			
		}
		
		
	     //分页模糊查询所用商品信息(前后端对接打法)  produces="text/html;charset=UTF-8"  有效解决jsp页面出现中文乱码的问题。
		 @RequestMapping(value="/tolist2",produces = "text/html;charset=utf-8")
		 @ResponseBody
		 public  String list2( HttpServletRequest request, 
				              @RequestParam(value = "currentPage",     required = false)  Integer currentPage,
				              @RequestParam(value = "txtname",         required = false)  String txtname,
				              @RequestParam(value = "commodityTypeID", required = false)  Integer commodityTypeID,
				              HttpServletResponse response){
        	
			System.out.println("條件1:"+txtname);
			System.out.println("當前頁1:"+currentPage);
			System.out.println("分類1:"+commodityTypeID);
     
			Map modelMap = new HashMap<String, Object>();                     //HashMap你可以理解成是一对对数据的集合
			
			List<AttributePo> listOne=attributeService.selectCommodityType(); //查出所有商品类别

			// 可以理解成Page<T>类里面定义了一个结果集result ，可以用来专门存储CommodityPo的数据
			Page<CommodityPo> vo=new Page<CommodityPo>();  
			
			//分类
		 	if (commodityTypeID==null) {
				commodityTypeID=16;
			}
			
		 	//当前页
			if (currentPage==null) {
				currentPage=1;        
			}else if (currentPage==0) {
				currentPage=1;
			}
			
			//条件
			if(txtname==null)
			{
				txtname="";
			}
			
		    vo.setCurrentPage(currentPage);                                           //赋值当前页到vo对象里面
		    
			vo=this.commodityService.pageFuzzyselect(txtname,commodityTypeID, vo);
			
			modelMap.put("listOne", listOne);                                        //返回的是查询到的所有类别
			modelMap.put("txtname", txtname);                                        //返回的是商品名称
			modelMap.put("commodityType", commodityTypeID);                          //返回的是商品类型 ,默认是16 饮料类
			modelMap.put("list", vo);
			return JSONArray.toJSONString(modelMap);
			
		}
		
	
		
		//跳到新增页面
		@RequestMapping("/toadd")
		public ModelAndView toadd(){
			ModelAndView mv=null;
			List<AttributePo> listOne=attributeService.selectUOM();//计量单位
			List<AttributePo> listTwo=attributeService.selectCommodityType();//商品类别
			mv=new ModelAndView("/commodity/add");
			
			mv.addObject("listOne",listOne);
			mv.addObject("listTwo",listTwo);
			return mv;
		}


	//新增
	@RequestMapping("/add")
	public ModelAndView add(CommodityPo commodityPo){
		ModelAndView mv=null;
		commodityService.insertAll(commodityPo);
		mv=new ModelAndView("redirect:/Commodity/tolist.do");//跳到控制器
		return mv;
	}






	//跳到新增页面
		       @RequestMapping(value="/toadd2",produces = "text/html;charset=utf-8")
		       @ResponseBody
				public String toadd2(){
					System.err.println("新增");
					Map modelMap = new HashMap<String, Object>();
					List<AttributePo> listOne=attributeService.selectUOM();//计量单位
					List<AttributePo> listTwo=attributeService.selectCommodityType();//商品类别
				
					modelMap.put("listOne",listOne);
					modelMap.put("listTwo",listTwo);
					return JSONArray.toJSONString(modelMap);
				}
		
		

		
		//新增
		@RequestMapping("/add2")
		@ResponseBody
		public String add2(CommodityPo commodityPo){
			int i = commodityService.insertAll(commodityPo);
			if(i>0) {
				return "200";
			}
			return "500";
		}
		





		@RequestMapping("/toupdate")
		public ModelAndView toupdate(int id){
			ModelAndView mv=null;
			List<AttributePo> listOne=attributeService.selectUOM();
			List<AttributePo> listTwo=attributeService.selectCommodityType();
			CommodityPo commodityPo=commodityService.selectById(id);//根据id查询出是商品的所有信息
			mv=new ModelAndView("/commodity/update");
			mv.addObject("listOne",listOne);
			mv.addObject("listTwo",listTwo);
			mv.addObject("listPo",commodityPo);
			return mv;
			
		}





		  //修改(跳到修改页面)
		@RequestMapping(value="/toupdate2",produces = "text/html;charset=utf-8")
		@ResponseBody
		public String toupdate2(int id){
			System.err.println("修改"+id);
			Map modelMap = new HashMap<String, Object>();
			
			List<AttributePo> listOne=attributeService.selectUOM();              //计量单位
			List<AttributePo> listTwo=attributeService.selectCommodityType();    //商品类别
			CommodityPo commodityPo=commodityService.selectById(id);             //根据id查询出所有数据
			
			modelMap.put("listOne",listOne);
			modelMap.put("listTwo",listTwo);
			modelMap.put("listPo",commodityPo);
			
			return JSONArray.toJSONString(modelMap);
			
		}
		
		
	
		
		
		//修改(真正的修改)
		@RequestMapping("/update")
		public ModelAndView update(CommodityPo commodityPo){
			ModelAndView mv=null;
			commodityService.updateById(commodityPo);
			mv=new ModelAndView("redirect:/Commodity/tolist.do");
			return mv;
		}
		
		
		//修改(真正的修改)
		@RequestMapping("/update2")
		@ResponseBody
		public String update2(CommodityPo commodityPo){
			
			int i = commodityService.updateById(commodityPo);
	        System.out.println("修改的结果为"+i);
			if(i>0) {
				return "200";
			}
			return "500";
			}
		
		
		
		//删除
		@RequestMapping("/delete")
		public ModelAndView delete(String id){
			ModelAndView mv=null;
			String[] FenGe=id.split(",");
			for (int i = 0; i < FenGe.length; i++) {
				commodityService.deleteById(Integer.parseInt(FenGe[i]));
			}
			mv=new ModelAndView("redirect:/Commodity/tolist.do");
			return mv;
		}
		
		//删除
		//@RequestMapping(value="/delete2",method = RequestMethod.POST)
		@RequestMapping(value="/delete2")
		@ResponseBody
		public String delete2(String id){
			   System.out.println("你要删除的id为"+id);
			String[] FenGe=id.split(",");
			for (int i = 0; i < FenGe.length; i++) {
				commodityService.deleteById(Integer.parseInt(FenGe[i]));
			}
	       return "200";
		}
	
		
		
		
		@RequestMapping("/openwindow")
		public ModelAndView openwindow(){
			ModelAndView mv=null;
			mv=new ModelAndView("/commodity/commoditytype");
			return mv;
		}
		
		 //新增商品類別
		@RequestMapping("/newadd")
		public ModelAndView newadd(String txtname){
			ModelAndView mv=null;
			int newid=3;
			attributeService.insertAll(newid,txtname);
			mv=new ModelAndView("redirect:/Commodity/tolist.do");
			return mv;
		}
		
		//删除商品分类
		@RequestMapping("/newdelete")
		public ModelAndView newdelete(String id){
			ModelAndView mv=null;
			String[] FenGe=id.split(",");
			for (int i = 0; i < FenGe.length; i++) {
			System.out.println("你删除的上商品分类id为"+FenGe);
				attributeService.deleteById(Integer.parseInt(FenGe[i]));
			}
			
			mv=new ModelAndView("redirect:/Commodity/tolist.do");
			return mv;
		}
		
		
		@ResponseBody
		@RequestMapping(value="/YZ")
		public Object YZ(String commodityName){
			int YorN=commodityService.selectYZ(commodityName);
			Gson gson =new Gson();
			//将数据转换成json格式
			return gson.toJson(YorN);
		}
		
		
		
		
		@ResponseBody
		@RequestMapping(value="/YZ2")
		public Object YZ2(String commodityName){
			int YorN=commodityService.selectYZ(commodityName);
			Gson gson =new Gson();
			//将数据转换成json格式
			return gson.toJson(YorN);
		}
		
}
