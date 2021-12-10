package com.chinatelecom.operations.metricsservice;
import com.chinatelecom.udp.core.datarouter.IDataResponse;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.chinatelecom.udp.core.lang.structure.LocalThreadMap;
import com.chinatelecom.udp.core.userrights.ILoginUserInfo;
import com.chinatelecom.udp.core.userrights.entity.LoginUserInfo;

import java.sql.SQLException;

public class MetricsDataServiceTest{
	private ApplicationContext context =new ClassPathXmlApplicationContext("applicationContext.xml");
	
	@Test
	public void test() throws SQLException {
		LoginUserInfo userInfo=new LoginUserInfo();
		userInfo.setEmpeeAcct("060824");
		LocalThreadMap.setData(ILoginUserInfo.class,userInfo);
		MetricsDataService data=context.getBean(MetricsDataService.class);
		String[] kpiCodeList={"102","103"};
		IDataResponse metric = data.getTrendMetrics(kpiCodeList);
		System.out.println(metric.toString());
	}
	
}