package com.chinatelecom.operations.metricsservice;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.chinatelecom.udp.core.lang.structure.LocalThreadMap;
import com.chinatelecom.udp.core.userrights.ILoginUserInfo;
import com.chinatelecom.udp.core.userrights.entity.LoginUserInfo;

public class MetricsDataServiceTest{
	private ApplicationContext context =new ClassPathXmlApplicationContext("applicationContext.xml");
	
	@Test
	public void test() {
		LoginUserInfo userInfo=new LoginUserInfo();
		userInfo.setEmpeeAcct("060824");
		LocalThreadMap.setData(ILoginUserInfo.class,userInfo);
		MetricsDataService data=context.getBean(MetricsDataService.class);
		data.list(0, 0);
	}
	
}