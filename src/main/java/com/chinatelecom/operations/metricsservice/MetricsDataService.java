
package com.chinatelecom.operations.metricsservice;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import com.chinatelecom.operations.metricsservice.entity.ShareData;
import com.chinatelecom.udp.core.dataaccess.ormapping.DAOFactory;
import com.chinatelecom.udp.core.dataaccess.ormapping.expression.StringExpression;
import com.chinatelecom.udp.core.datarouter.IDataResponse;
import com.chinatelecom.udp.core.datarouter.IWorkService;
import com.chinatelecom.udp.core.datarouter.WebContextHolder;
import com.chinatelecom.udp.core.datarouter.response.JSONResponse;
import com.chinatelecom.udp.core.datarouter.utils.TypeHttpRequest;
import com.chinatelecom.udp.core.lang.datetime.DateTimeHelper;
import com.chinatelecom.udp.core.lang.json.JSONArray;
import com.chinatelecom.udp.core.lang.json.JSONObject;
import com.chinatelecom.udp.core.userrights.ILoginUserInfo;

@Component
public class MetricsDataService implements IWorkService{
    
	private final static Logger logger=LogManager.getLogger(MetricsDataService.class);
	
	
	public MetricsDataService() {
	}
	
	/**
	 * 显示最新的数据
	 * @param startindex 
	 * @param length
	 * @return {result:0成功1失败,message:错误信息}
	 */
	public IDataResponse list(int startIndex, int length)  {
		JSONObject jsonResult = new JSONObject();
		ILoginUserInfo userInfo = WebContextHolder.getLoginUserInfo();
		jsonResult.put("result", 0);
		jsonResult.put("data", userInfo.getStaffName());
		return new JSONResponse(jsonResult);
	}
	

	@Override
	public String getCode() {
		return "metricsdata";
	}


	@Override
	public String getName() {
		return "指标数据服务";
	}
	
}