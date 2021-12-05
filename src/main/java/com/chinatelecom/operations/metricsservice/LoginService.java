
package com.chinatelecom.operations.metricsservice;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.chinatelecom.udp.core.dataaccess.database.ConnectionFactory;
import com.chinatelecom.udp.core.datarouter.DataHandler;
import com.chinatelecom.udp.core.datarouter.IDataResponse;
import com.chinatelecom.udp.core.datarouter.IWorkService;
import com.chinatelecom.udp.core.datarouter.ServiceMethodInfo;
import com.chinatelecom.udp.core.datarouter.WebContextHolder;
import com.chinatelecom.udp.core.datarouter.exception.DataException;
import com.chinatelecom.udp.core.datarouter.response.RedirectResponse;
import com.chinatelecom.udp.core.datarouter.utils.TypeHttpRequest;
import com.chinatelecom.udp.core.userrights.ILoginUserInfo;
import com.chinatelecom.udp.core.userrights.entity.LoginUserInfo;
import com.chinatelecom.udp.core.websecurity.util.UnionAuthHelper;

import jakarta.servlet.http.HttpSession;

@Component
public class LoginService implements IWorkService{
    
	private static final String REDIRECTURL = "http://134.64.116.90:8777/cmdb/service/unionauth/login/0";
	private static final String CLIENT_ID = "CTAHCMDB20210914";
	private static final String PRIVATE_KEY="MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAIm7iEYS8O3iUAuBanP3tE7hYCPmK4vaGaOtg3W0nKyjJ8nLs6wT3tElNpgIAFaz/SJuIOBJItp9uB0TZCFQ6HDqpgLRisRuaBoUfppGC+zshmWiaqtDYQHEakj44ErEI229q3xuDHKJiFGsJ9M7reNGZvdoJCiMsjKGqOoZyexHAgMBAAECgYA+UQ8zRValCwRUcFWN6sE1/1rUY0vIlwGKwPL9yp4orhlOgE/mx4bE8iVlXBlrITyi21kcoXqXiNJItYFUYZsI8AjN5rvyYi/OagWFdraZB3F6uWpmq4EW/9CeEQBfBZo88JMx9UQ2gk3CDp7yJawvSB1L9nPlJ7XAtLp/FhmOQQJBANpIPeBxbxQ0QtK8eFJlc6xKPXdZpo4WrYgPFb2t0qO2IuOK9R0GEuoH5qntcF26LQRNHGl1nfMx4GQHJuHRpR8CQQChiCkGbMrRf5GNNywr+X7T6jlFpdvfY6fD+WsX2Q+KeECSOex2XzJcpELXrHHz9upVMXnaY0uc9s2bcD4Vz2vZAkAkX8933E8/VaohBekjCebpugFeJyFbIipOtvzPe+zS/SH5Owm3iXCTcTed74Q/CixAQD1Q0Zm9kf0rjgmLrezPAkAKfrA7rKHYzrMEO8tw5/rnhdZGwinw76acwv3EROQPmRSI656Dalf5UOrO3HNi6G39LwO9vqnQ/vAI41gFTr6xAkAWxL/uQu2eCvNVsp/sTd/uDF1UKOyHGRcpYHhjZA2MGOkopcOmAM1NGRwj8Xr5C/zhxIgB9Q/5Fl6wBxmtJYAy";
	private final static Logger logger=LogManager.getLogger(LoginService.class);
	@Autowired
	private ConnectionFactory connectionFactory=null;
	
	public LoginService() {
	
	}
	
	@ServiceMethodInfo(authentincation = false)
	public IDataResponse login(TypeHttpRequest request) throws DataException {
		ILoginUserInfo userInfo = WebContextHolder.getLoginUserInfo();
		if(userInfo!=null) {
			return new RedirectResponse("/cmdb/index.html");
		}
		else {
			String code=request.getString("code");
			if(code==null) {
				return new RedirectResponse(UnionAuthHelper.getRedirectUrl(CLIENT_ID, REDIRECTURL));
			}
			else {
				String account;
				try {
					account = UnionAuthHelper.getTokenInfo(CLIENT_ID, REDIRECTURL, code, PRIVATE_KEY);
					HttpSession session = request.getSession();
					//ILoginUserInfo loginUser=LoginUserInfo.getFromCache(account, connectionFactory);
					LoginUserInfo loginUser = new LoginUserInfo();
					loginUser.setEmpeeAcct(account);
					session.setAttribute(DataHandler.SessionName,loginUser);
					return new RedirectResponse("/cmdb/index.html");
				} catch (IOException | DataException e) {
					logger.error(String.format("error get token,code:%s,message:%s", code,e.getMessage()),e);
					throw new DataException(e.getMessage());
				}
				
			}
		}
	}

	@Override
	public String getCode() {
		return "unionauth";
	}


	@Override
	public String getName() {
		return "统一认证登录";
	}
	
}