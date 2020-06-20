package com.gx.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.gx.po.StayRegisterPo;
import com.gx.po.UserPo;
import com.gx.service.StayRegisterService;
import com.gx.service.UserService;

@Controller
@RequestMapping("/Login")
public class Login {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private StayRegisterService stayRegisterService;
	
	@RequestMapping("/tologin")
	public String tologin(){
		System.out.println("进来辣妈？");
		return "/login/login";//返回到login.jsp页面
	}
	
	
	//验证用户名和密码
	@RequestMapping("/tomain")
	public ModelAndView tomain(UserPo user){
		System.out.println(user.getUserName()+user.getPassword());
		ModelAndView mv=null;//创建一个视图对象
		double zongFeiYongOne=0;
		double zongFeiYongTwo=0;
		UserPo u=userService.selectLogin(user);//已经把数据查出放在u里面
		
		/*List<StayRegisterPo> list=stayRegisterService.selectAll();//查询很多张表的数据，条件是68，69在数据库中代表是否结账
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getReceiveTargetID()==2) {     //接待对象ID=2
				zongFeiYongOne+=list.get(i).getSumConst();//SumConst总费用
			}else {
				zongFeiYongTwo+=list.get(i).getSumConst();
			}
		}*/
		 //用户名正确
		if (u!=null) {
			System.err.println("用户名和密码正确，准备跳到/main/main中");
			mv=new ModelAndView("/main/main");//跳到jsp页面
		}else {
			mv=new ModelAndView("/login/login");
		}
		
		//下面的不知道返回的是什么，应该跟钱有关
		mv.addObject("zongFeiYongOne",zongFeiYongOne);
		mv.addObject("zongFeiYongTwo",zongFeiYongTwo);
		return mv;
	}
	

}
