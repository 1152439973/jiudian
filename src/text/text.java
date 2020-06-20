package text;



import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.gx.po.CommodityPo;
import com.gx.service.impl.CommodityServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration(locations = { "classpath:applicationContext.xml","classpath:spring-mvc.xml","classpath:spring-mybatis.xml" }) //这个路径错了
 
public class text {
	
	
	//@Autowired
	//@Autowired
	//private CommodityServiceImpl commodityServiceImpl;
	
	@Test
	public void xxx() {
		 //CommodityPo xxx = commodityServiceImpl.selectById(1);
         System.out.println("得到的数据为");
	}

	
}
