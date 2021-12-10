
package com.chinatelecom.operations.metricsservice;

import java.io.IOException;
import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import com.chinatelecom.operations.metricsservice.entity.KpiInfo;
import com.chinatelecom.operations.metricsservice.entity.KpiValue;
import com.chinatelecom.udp.core.dataaccess.ormapping.ConditionGroup;
import com.chinatelecom.udp.core.dataaccess.ormapping.IConditionGroup;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.chinatelecom.udp.core.dataaccess.ormapping.DAOFactory;
import com.chinatelecom.udp.core.dataaccess.ormapping.expression.StringExpression;
import com.chinatelecom.udp.core.datarouter.IDataResponse;
import com.chinatelecom.udp.core.datarouter.IWorkService;
import com.chinatelecom.udp.core.datarouter.ServiceMethodInfo;
import com.chinatelecom.udp.core.datarouter.WebContextHolder;
import com.chinatelecom.udp.core.datarouter.response.JSONResponse;
import com.chinatelecom.udp.core.datarouter.utils.TypeHttpRequest;
import com.chinatelecom.udp.core.lang.datetime.DateTimeHelper;
import com.chinatelecom.udp.core.lang.json.JSONArray;
import com.chinatelecom.udp.core.lang.json.JSONObject;
import com.chinatelecom.udp.core.userrights.ILoginUserInfo;
import org.springframework.util.StringUtils;


@Component
public class MetricsDataService implements IWorkService{

	private final static Logger logger=LogManager.getLogger(MetricsDataService.class);
	public MetricsDataService() {
	}
	
	/**
	 * 查询普通指标的信息
	 * @param kpiCodeList
	 * @return {result:0成功1失败,message:错误s信息}
	 */
	public IDataResponse getMetric(String[] kpiCodeList) throws SQLException {
		logger.info("=================>开始查询指标的详细信息,getMetric方法入参指标编码为：" + kpiCodeList);
		JSONObject jsonResult = new JSONObject();
		if (StringUtils.isEmpty(kpiCodeList)) {
			jsonResult.put("1", "指标编码不能为空");
			return new JSONResponse(jsonResult);
		}
		JSONArray jsonArray=new JSONArray();
		for (String kpiCode : kpiCodeList) {
			jsonResult=getMetricSingle(kpiCode);
			jsonArray.put(jsonResult);
		}
		return new JSONResponse(jsonArray);
	}

	public IDataResponse getTrendMetrics(String[] kpiCodeList) throws SQLException {
		logger.info("=================>开始查询趋势指标的详细信息,getMetric方法入参指标编码为：" + kpiCodeList);
		JSONObject jsonResult = new JSONObject();
		if (StringUtils.isEmpty(kpiCodeList)) {
			jsonResult.put("1", "指标编码不能为空");
			return new JSONResponse(jsonResult);
		}
		JSONArray jsonArray=new JSONArray();
		for (String kpiCode : kpiCodeList) {
			jsonResult=getTrendMetricSingle(kpiCode);
			jsonArray.put(jsonResult);
		}
		return new JSONResponse(jsonArray);
	}


	/**
	 * 查询指定团队所有指标的信息
	 * @param group
	 * @return {result:0成功1失败,message:错误信息}
	 */
	public IDataResponse getMetricsByGroup(String group) throws SQLException {
		logger.info("=================>开始查询指定团队所有指标的信息,getMetricsByGroup方法入参归属团队为："+group);
		JSONArray jsonArray = new JSONArray();
		if(StringUtils.isEmpty(group)){
			JSONObject jsonResult = new JSONObject();
			jsonResult.put("1","归属团队不能为空");
			return new JSONResponse(jsonResult);
		}
		KpiInfo qryKpiInfoDao = DAOFactory.getDAOObject(KpiInfo.class);
		//pgsql中关键字查询需要加上双引号
		qryKpiInfoDao.andEqual("\"group\"",new StringExpression(group,true));
		ArrayList<KpiInfo> kpiInfoList= qryKpiInfoDao.doQuery();
		//遍历该团队下的所有指标获取指标信息
		for (KpiInfo info : kpiInfoList) {
			JSONObject jsonResult=getMetricSingle(info.getKpi_code());
			jsonArray.put(jsonResult);
		}
		return new JSONResponse(jsonArray);
	}

	public JSONObject getMetricSingle(String kpiCode) throws SQLException {
		JSONObject jsonResult = new JSONObject();
		//先进行查询
		KpiInfo kpiInfo;
		KpiValue kpiValue;
		KpiInfo qryKpiInfoDao = DAOFactory.getDAOObject(KpiInfo.class);
		KpiValue qryKpiValueDao = DAOFactory.getDAOObject(KpiValue.class);
		qryKpiInfoDao.andEqual("kpi_code", new StringExpression(kpiCode, true));
		qryKpiValueDao.andEqual("kpi_code", new StringExpression(kpiCode, true));
		qryKpiValueDao.addDesc("crt_date");
		ArrayList<KpiValue> kpiValueList = qryKpiValueDao.doQuery();
		ArrayList<KpiInfo> kpiInfoList = qryKpiInfoDao.doQuery();
		if (kpiInfoList.size() > 0 && kpiInfoList != null) {
			kpiInfo = kpiInfoList.get(0);
		} else {
			jsonResult.put("1", "指标编码为:" + kpiCode + "的指标不存在");
			return jsonResult;
		}
		if (kpiValueList.size() == 0 || kpiValueList == null) {
			jsonResult.put("1", "指标编码为:" + kpiCode + "的指标暂不存在对应的数据");
			return jsonResult;
		}
		//将该指标的名称、单位、描述先放入
		jsonResult.put("kpiName", kpiInfo.getKpi_name());
		jsonResult.put("unit", kpiInfo.getUnit());
		jsonResult.put("kpiDesc", kpiInfo.getKpi_desc());
		jsonResult.put("crtDate", kpiValueList.get(0).getCrt_date());
		jsonResult.put("kpiValue", kpiValueList.get(0).getKpi_value());
		jsonResult.put("data_type", kpiValueList.get(0).getData_type());
		return jsonResult;
	}

	public JSONObject getTrendMetricSingle(String kpiCode) throws SQLException {
		JSONObject jsonResult = new JSONObject();
		//先进行查询
		KpiInfo kpiInfo;
		KpiValue kpiValue;
		KpiInfo qryKpiInfoDao = DAOFactory.getDAOObject(KpiInfo.class);
		KpiValue qryKpiValueDao = DAOFactory.getDAOObject(KpiValue.class);
		qryKpiInfoDao.andEqual("kpi_code", new StringExpression(kpiCode, true));
		qryKpiValueDao.andEqual("kpi_code", new StringExpression(kpiCode, true));
		qryKpiValueDao.addDesc("crt_date");
		ArrayList<KpiValue> kpiValueList = qryKpiValueDao.doQuery();
		ArrayList<KpiInfo> kpiInfoList = qryKpiInfoDao.doQuery();
		if (kpiInfoList.size() > 0 && kpiInfoList != null) {
			kpiInfo = kpiInfoList.get(0);
		} else {
			jsonResult.put("1", "指标编码为:" + kpiCode + "的指标不存在");
			return jsonResult;
		}
		if (kpiValueList.size() == 0 || kpiValueList == null) {
			jsonResult.put("1", "指标编码为:" + kpiCode + "的指标暂不存在对应的数据");
			return jsonResult;
		}
		//将该指标的名称、单位、描述先放入
		jsonResult.put("kpiName", kpiInfo.getKpi_name());
		jsonResult.put("unit", kpiInfo.getUnit());
		jsonResult.put("kpiDesc", kpiInfo.getKpi_desc());
		//需要根据数据周期判断该指标趋势图取几条数据返回，暂时不确定
		ArrayList returnList = new ArrayList();
		Map map;
		for (int i = 0; i <= 1; i++) {
			if (kpiValueList.get(i) == null) {
				break;
			} else {
				map = new HashMap();
				map.put("crtDate", kpiValueList.get(i).getCrt_date());
				map.put("data_id", kpiValueList.get(i).getData_id());
				map.put("kpi_value", kpiValueList.get(i).getKpi_value());
				map.put("data_type", kpiValueList.get(i).getData_type());
				returnList.add(map);
			}
		}
		jsonResult.put("trend", returnList);
		return jsonResult;
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