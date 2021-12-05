
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
	
	private ConcurrentHashMap<String, TypeHttpRequest> waitUsers=null;
	
	public ConcurrentHashMap<String, TypeHttpRequest> getWaitUsers() {
		return waitUsers;
	}

	public MetricsDataService() {
		waitUsers=new ConcurrentHashMap<>();
	}
	
	public IDataResponse publish(String content) {
		final JSONObject jsonResult=new JSONObject();
		if(content.length()>10000) {
			jsonResult.put("result", 2);
			jsonResult.put("message", "too large");
		}
		else {
			ShareData addDataDAO = DAOFactory.getDAOObject(ShareData.class);
			String id = UUID.randomUUID().toString().substring(1, 28);
			ILoginUserInfo userInfo = WebContextHolder.getLoginUserInfo();
			try {
				addDataDAO.setModified("create_time", "now()");
				addDataDAO.setDataid(id);
				addDataDAO.setContent(content);
				addDataDAO.setCreator(userInfo.getEmpeeAcct());
				addDataDAO.doInsert(true,true);
				jsonResult.put("result", 0);
				jsonResult.put("id", id);
				TypeHttpRequest waitUserRequest = waitUsers.get(userInfo.getEmpeeAcct());
				if(waitUserRequest!=null) {
					JSONObject jsonMessage=new JSONObject();
					jsonMessage.put("content", content);
					waitUserRequest.asyncResponse(new JSONResponse(jsonMessage));
				}				
			} catch (SQLException | IOException e) {
				logger.error(e.getMessage(), e);
				jsonResult.put("result", 1);
				jsonResult.put("message", e.getMessage());
			}
		}
		JSONResponse response=new JSONResponse(jsonResult);
		return response;
	}

	/**
	 * 显示最新的数据
	 * @param startindex 
	 * @param length
	 * @return {result:0成功1失败,message:错误信息}
	 */
	public IDataResponse list(int startIndex, int length)  {
		JSONObject jsonResult = new JSONObject();
		ShareData queryDataDAO=DAOFactory.getDAOObject(ShareData.class);
		try {
			ILoginUserInfo userInfo = WebContextHolder.getLoginUserInfo();
			queryDataDAO.setSelected(new String[] {"create_time","content"});
			queryDataDAO.addDesc("create_time");
			queryDataDAO.andEqual("creator",new StringExpression(userInfo.getEmpeeAcct(), true));
			ArrayList<ShareData> queryResult=queryDataDAO.doQuery(startIndex, length);
			JSONArray jsonPages=new JSONArray();
			for(ShareData data: queryResult) {
				JSONObject jsonPage=new JSONObject();
				jsonPage.put("createtime",DateTimeHelper.formatDate(data.getCreate_time().getTime(),"Y-m-d H:M:S"));
				jsonPage.put("content",data.getContent());
				jsonPages.put(jsonPage);
			}
			jsonResult.put("result", 0);
			jsonResult.put("data", jsonPages);
		} catch (SQLException e) {
			logger.error(e.getMessage(),e);
			jsonResult.put("result", 1);
			jsonResult.put("message", e.getMessage());
		}
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