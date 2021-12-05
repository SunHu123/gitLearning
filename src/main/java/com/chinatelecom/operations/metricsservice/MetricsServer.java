
package com.chinatelecom.operations.metricsservice;

import java.io.IOException;
import java.net.URL;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.chinatelecom.udp.core.lang.text.TextHelper;
import com.chinatelecom.udp.core.server.ServerRuntime;

public class MetricsServer{
    
	private final static Logger logger=LogManager.getLogger(MetricsServer.class);
	
	public static void main(String args[]) throws IOException {		
		ApplicationContext context =new ClassPathXmlApplicationContext("applicationContext.xml");
		try{
			URL url = MetricsServer.class.getResource("MetricsServer.class");
			ServerRuntime runtime=new ServerRuntime(context, 8777, "/metrics",true,url.getProtocol().equals("file"));
			String resourcePath;
			if(url.getProtocol().equals("file")) {
				resourcePath=TextHelper.getLeftPart(url.getPath().substring(1),"MetricsService",true) + "/MetricsService/src/main/webapp";
				runtime.addResource(resourcePath, "/");
			}
			else {
				resourcePath =TextHelper.getRightPart(TextHelper.getLeftPart(url.getFile(),"!",true),":",false);	
				runtime.addResource(resourcePath, "/html");
			}
			logger.info("loaded from " + resourcePath);
			
			runtime.startAndWait();
		}
		catch(Exception e) {
			logger.error(e.getMessage(),e);
		}
		
	}

	
}