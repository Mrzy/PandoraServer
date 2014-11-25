package cn.zmdx.locker.actions;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import cn.zmdx.locker.entity.Data_img_table;
import cn.zmdx.locker.entity.Data_table;
import cn.zmdx.locker.service.impl.LockerServiceImpl;

import com.alibaba.fastjson.JSON;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class LockerAction extends ActionSupport implements
		ModelDriven<Data_table> {
	Logger logger = Logger.getLogger(LockerAction.class);
	private Data_table dataTable = new Data_table();
	private LockerServiceImpl lockerService;
	private String result;

	@Override
	public Data_table getModel() {
		// TODO Auto-generated method stub
		return dataTable;
	}

	public String getResult() {
		return result;
	}

	public void setLockerService(LockerServiceImpl lockerService) {
		this.lockerService = lockerService;
	}

	/**
	 * 查询非图片数据数据
	 * 
	 * @throws IOException
	 */
	public void queryDataTable() throws IOException {
		String flag = ServletActionContext.getRequest().getParameter("limit");
		String webSite = ServletActionContext.getRequest().getParameter(
				"webSite");
		String dataType = ServletActionContext.getRequest().getParameter(
				"dataType");
		int limit = 0;
		if (null != flag && !"".equals(flag))
			limit = Integer.parseInt(flag);
		if (limit < 1)
			limit = 5;
		PrintWriter out = ServletActionContext.getResponse().getWriter();
		Map<String, String> filterMap = new HashMap();
		filterMap.put("limit", String.valueOf(limit));
		filterMap.put("webSite", webSite);
		filterMap.put("dataType", dataType);
		try {
			List<Data_table> list = lockerService.queryDataTable(filterMap);
			if (null != list && list.size() > 0)
				out.print("{\"state\":\"success\",\"data\":"
						+ JSON.toJSONString(list, true) + "}");
			else
				out.print("{\"state\":\"null\"}");
		} catch (Exception e) {
			out.print("{\"state\":\"error\"}");
			logger.error(e);
			e.printStackTrace();
		}
		out.flush();
		out.close();
	}

	/**
	 * 查询图片数据数据
	 * 
	 * @throws IOException
	 */
	public void queryDataImgTable() throws IOException {
		ServletActionContext.getResponse().setContentType(
				"text/json; charset=utf-8");
		String flag = ServletActionContext.getRequest().getParameter("limit");
		String webSite = ServletActionContext.getRequest().getParameter(
				"webSite");
		String dataType = ServletActionContext.getRequest().getParameter(
				"dataType");
		String lastModified = ServletActionContext.getRequest().getParameter(
				"lastModified");
		if(null == lastModified || "".equals(lastModified)||"null".equals(lastModified)){
			lastModified="0";
		}
		int limit = 0;
		if (null != flag && !"".equals(flag))
			limit = Integer.parseInt(flag);
		if (limit < 1 || limit > 100)
			limit = 100;
		PrintWriter out = ServletActionContext.getResponse().getWriter();
		Map<String, String> filterMap = new HashMap();
		filterMap.put("limit", String.valueOf(limit));
		filterMap.put("webSite", webSite);
		filterMap.put("dataType", dataType);
		filterMap.put("lastModified", lastModified);
		try {
			List<Data_img_table> list = lockerService
					.queryDataImgTable(filterMap);
			if (null != list && list.size() > 0)
				out.print("{\"state\":\"success\",\"data\":"
						+ JSON.toJSONString(list, true) + "}");
			else
				out.print("{\"state\":\"null\"}");
		} catch (Exception e) {
			out.print("{\"state\":\"error\"}");
			logger.error(e);
			e.printStackTrace();
		}
		out.flush();
		out.close();
	}

	/**
	 * 
	 * 
	 * @throws IOException
	 * @author louxiaojian
	 */
	public String viewDataImg() throws IOException {
		ServletActionContext.getResponse().setContentType(
				"text/json; charset=utf-8");
		//获取id
		String id = ServletActionContext.getRequest().getParameter("id");
		Map<String, String> filterMap = new HashMap();
		filterMap.put("id", id);
		//根据id查询相关的图文信息
		List list=lockerService.queryDataById(filterMap);
//		StringBuffer sb=new StringBuffer("");
//		for (int i = 0; i < list.size(); i++) {
//			Object [] obj=(Object [])list.get(i);
//			sb.append("<img src=\""+obj[1]+"\" alt=\"\" style=\"word-wrap: break-word !important; box-sizing: border-box !important; visibility: visible !important; width: auto !important;\"/><br/>");
//			sb.append("<p style=\"max-width: 100%; word-wrap: break-word !important; box-sizing: border-box !important; line-height: 25px; text-indent: 2em;\">"+obj[2]+"</p><br style=\"max-width: 100%; word-wrap: break-word !important; box-sizing: border-box !important;\"/>");
//		}
		//根据id获取DataImgTable对象
		Data_img_table dataImgTable=lockerService.getDataImgTableById(id);
//		ServletActionContext.getRequest().setAttribute("sb", sb);
		ServletActionContext.getRequest().setAttribute("list", list);
		ServletActionContext.getRequest().setAttribute("dataImgTable", dataImgTable);
		return "viewDataImg";
	}
}
