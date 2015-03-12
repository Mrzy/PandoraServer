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
import cn.zmdx.locker.entity.Data_tag;
import cn.zmdx.locker.entity.WallPaper;
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
		if (null == lastModified || "".equals(lastModified)
				|| "null".equals(lastModified)) {
			lastModified = "0";
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
		// 获取id
		String id = ServletActionContext.getRequest().getParameter("id");
		Map<String, String> filterMap = new HashMap();
		filterMap.put("id", id);
		// 根据id查询相关的图文信息
		List list = lockerService.queryDataById(filterMap);
		// 根据id获取标签信息
		List taglist = lockerService.queryDataTagById(filterMap);
		// StringBuffer sb=new StringBuffer("");
		// for (int i = 0; i < list.size(); i++) {
		// Object [] obj=(Object [])list.get(i);
		// sb.append("<img src=\""+obj[1]+"\" alt=\"\" style=\"word-wrap: break-word !important; box-sizing: border-box !important; visibility: visible !important; width: auto !important;\"/><br/>");
		// sb.append("<p style=\"max-width: 100%; word-wrap: break-word !important; box-sizing: border-box !important; line-height: 25px; text-indent: 2em;\">"+obj[2]+"</p><br style=\"max-width: 100%; word-wrap: break-word !important; box-sizing: border-box !important;\"/>");
		// }
		// 根据id获取DataImgTable对象
		Data_img_table dataImgTable = lockerService.getDataImgTableById(id);
		// ServletActionContext.getRequest().setAttribute("sb", sb);
		ServletActionContext.getRequest().setAttribute("list", list);
		ServletActionContext.getRequest().setAttribute("taglist", taglist);
		ServletActionContext.getRequest().setAttribute("dataImgTable",
				dataImgTable);
		return "viewDataImg";
	}

	/**
	 * 查询壁纸数据
	 * 
	 * @throws IOException
	 */
	public void queryWallPaper() throws IOException {
		ServletActionContext.getResponse().setContentType(
				"text/json; charset=utf-8");
		String webSite = ServletActionContext.getRequest().getParameter(
				"webSite");
		PrintWriter out = ServletActionContext.getResponse().getWriter();
		Map<String, String> filterMap = new HashMap();
		filterMap.put("webSite", webSite);
		try {
			List<WallPaper> list = lockerService.queryWallPaper(filterMap);
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
	 * 增加浏览量 张加宁
	 * 
	 * @throws IOException
	 */
	public void addViews() throws IOException {
		ServletActionContext.getResponse().setContentType(
				"text/json; charset=utf-8");
		String id = ServletActionContext.getRequest().getParameter("id");
		PrintWriter out = ServletActionContext.getResponse().getWriter();
		try {
			lockerService.addViews(id);
			out.print("{\"state\":\"success\"}");
		} catch (Exception e) {
			out.print("{\"state\":\"error\"}");
			logger.error(e);
			e.printStackTrace();
		}
		out.flush();
		out.close();
	}
	
	/**
	 * 新版本的图片数据查询（1.5.2版后）
	 * @author louxiaojian
	 * @date： 日期：2015-3-9 时间：上午11:45:29
	 * @throws IOException
	 */
	public void queryDataImgTableNew() throws IOException {
		ServletActionContext.getResponse().setContentType(
				"text/json; charset=utf-8");
		ServletActionContext.getResponse().setHeader("Cache-Control", "max-age=300");
		PrintWriter out = ServletActionContext.getResponse().getWriter();
		//数据类别:1头条,2八卦,3微精选,4美女,5搞笑
		String type = ServletActionContext.getRequest().getParameter("type");
		//lastModified
		String lastModified = ServletActionContext.getRequest().getParameter("lastModified");
		//查询数据数量
		String limit=ServletActionContext.getRequest().getParameter("limit");
		//标示，0查询lastModified之后的数据，1查询lastModified之前的数据
		String flag=ServletActionContext.getRequest().getParameter("flag");
		if (null == lastModified || "".equals(lastModified)
				|| "null".equals(lastModified)) {
			lastModified = "0";
		}
		if ("".equals(limit)||limit==null|| "0".equals(limit)){
			limit = "20";
		}
		Map<String, String> filterMap = new HashMap();
		filterMap.put("limit", limit);
		filterMap.put("type", type);
		filterMap.put("lastModified", lastModified);
		filterMap.put("flag", flag);
		try {
			List<Data_img_table> list = lockerService
					.queryDataImgTableNew(filterMap);
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
	 * 新版本的壁纸数据查询（1.5.2版后）
	 * @author louxiaojian
	 * @date： 日期：2015-3-9 时间：下午4:36:09
	 * @throws IOException
	 */
	public void queryWallPaperNew() throws IOException {
		ServletActionContext.getResponse().setContentType(
				"text/json; charset=utf-8");
		ServletActionContext.getResponse().setHeader("Cache-Control", "max-age=21600");
		PrintWriter out = ServletActionContext.getResponse().getWriter();
		String lastModified = ServletActionContext.getRequest().getParameter("lastModified");
		//查询数据数量
		String limit=ServletActionContext.getRequest().getParameter("limit");
		if (null == lastModified || "".equals(lastModified)
				|| "null".equals(lastModified)) {
			lastModified = "0";
		}
		if (null == limit || "".equals(limit)
				|| "null".equals(limit)|| "0".equals(limit)) {
			limit = "15";
		}
		Map<String, String> filterMap = new HashMap();
		filterMap.put("lastModified", lastModified);
		filterMap.put("limit", limit);
		try {
			List<WallPaper> list = lockerService.queryWallPaperNew(filterMap);
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
}
