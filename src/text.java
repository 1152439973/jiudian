import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.gx.po.CommodityPo;
import com.gx.service.impl.CommodityServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)  //让junit和springj环境进行整合
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class text {
	
	

	@Autowired
	private CommodityServiceImpl commodityServiceImpl;// 注入Dao实现类依赖
	
	@Test
	public void xxx() {
		 CommodityPo xxx = commodityServiceImpl.selectById(1);
         System.out.println("得到的数据为"+xxx);
	}

}
