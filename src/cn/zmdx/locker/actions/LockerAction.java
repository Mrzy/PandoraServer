package cn.zmdx.locker.actions;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import cn.zmdx.locker.entity.Data_img_table;
import cn.zmdx.locker.entity.Data_table;
import cn.zmdx.locker.entity.Font;
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
		String from = ServletActionContext.getRequest().getParameter("from");
		String theme = ServletActionContext.getRequest().getParameter("theme");//night 夜间模式
		Map<String, String> filterMap = new HashMap();
		filterMap.put("id", id);
		// 根据id查询相关的图文信息
		List list = lockerService.queryDataById(filterMap);
		// 根据id获取标签信息
		List taglist = lockerService.queryDataTagById(filterMap);
		// 根据id获取DataImgTable对象
		Data_img_table dataImgTable = lockerService.getDataImgTableById(id);
		ServletActionContext.getRequest().setAttribute("from", from);
		ServletActionContext.getRequest().setAttribute("theme", theme);
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
			List<Data_img_table> sticklist = lockerService
					.queryStickDataImgTableNew(filterMap);
			StringBuffer sb=new StringBuffer();
			if((null != list && list.size() > 0)||(null != sticklist && sticklist.size() > 0)){
				sb.append("{\"state\":\"success\"");
				if(null != list && list.size() > 0){
					sb.append(",\"data\":"+ JSON.toJSONString(list, true));
				}else{
					sb.append(",\"data\":\"null\"");
				}
				if(null != sticklist && sticklist.size() > 0){
					sb.append(",\"stickData\":"+ JSON.toJSONString(sticklist, true));
				}else{
					sb.append(",\"stickData\":\"null\"");
				}
				sb.append("}");
			}else{
				sb.append("{\"state\":\"success\",\"data\":\"null\",\"stickData\":\"null\"}");
			}
				out.print(sb.toString());
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
		ServletActionContext.getResponse().setHeader("Cache-Control", "max-age=10800");
		PrintWriter out = ServletActionContext.getResponse().getWriter();
		String lastModified = ServletActionContext.getRequest().getParameter("lastModified");
		//查询数据数量
		String limit=ServletActionContext.getRequest().getParameter("limit");
		//标示，0查询lastModified之后的数据，1查询lastModified之前的数据
		String flag=ServletActionContext.getRequest().getParameter("flag");
		String isDebug=ServletActionContext.getRequest().getParameter("isDebug");
		String heghit=ServletActionContext.getRequest().getParameter("h");
		if (null == lastModified || "".equals(lastModified)
				|| "null".equals(lastModified)) {
			lastModified = "0";
		}
		if (null == limit || "".equals(limit)
				|| "null".equals(limit)|| "0".equals(limit)) {
			limit = "15";
		}
		String type="";
        if(!"".equals(heghit)&&heghit!=null){
        	int h = Integer.parseInt(heghit);
    		if (h >= 1900) {
    			type="xh";
            } else if (h >= 1200) {
            	type="h";
            } else if (h >=800) {
            	type="m";
            } else {
            	type="m";
            }
        }else{
        	type="xh";
        }
		Map<String, String> filterMap = new HashMap();
		filterMap.put("lastModified", lastModified);
		filterMap.put("limit", limit);
		filterMap.put("flag", flag);
		filterMap.put("isDebug", isDebug);
		filterMap.put("type", type);
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
	
	/**
	 * 新闻数据点赞
	 * @author louxiaojian
	 * @date： 日期：2015-3-12 时间：上午11:21:25
	 * @throws IOException
	 */
	public void addDataImgTableTop() throws IOException {
		ServletActionContext.getResponse().setContentType(
				"text/json; charset=utf-8");
		String id = ServletActionContext.getRequest().getParameter("id");
		PrintWriter out = ServletActionContext.getResponse().getWriter();
		try {
			int top=lockerService.addDataImgTableTop(id);
			out.print("{\"state\":\"success\",\"top\":"+top+"}");
		} catch (Exception e) {
			out.print("{\"state\":\"error\"}");
			logger.error(e);
			e.printStackTrace();
		}
		out.flush();
		out.close();
	}
	
	/**
	 * 壁纸数据点赞
	 * @author louxiaojian
	 * @date： 日期：2015-3-12 时间：上午11:21:25
	 * @throws IOException
	 */
	public void addWallPaperTop() throws IOException {
		ServletActionContext.getResponse().setContentType(
				"text/json; charset=utf-8");
		String id = ServletActionContext.getRequest().getParameter("id");
		PrintWriter out = ServletActionContext.getResponse().getWriter();
		try {
			int top=lockerService.addWallPaperTop(id);
			out.print("{\"state\":\"success\",\"top\":"+top+"}");
		} catch (Exception e) {
			out.print("{\"state\":\"error\"}");
			logger.error(e);
			e.printStackTrace();
		}
		out.flush();
		out.close();
	}
	
	/**
	 * 获取字体
	 * @author louxiaojian
	 * @date： 日期：2015-4-20 时间：下午2:31:46
	 * @throws IOException
	 */
	public void queryFont() throws IOException{
		ServletActionContext.getResponse().setContentType(
				"text/json; charset=utf-8");
		PrintWriter out = ServletActionContext.getResponse().getWriter();
		try {
			List<Font> list=lockerService.queryFont();
			out.print("{\"state\":\"success\",\"data\":"+JSON.toJSONString(list, true)+"}");
		} catch (Exception e) {
			out.print("{\"state\":\"error\"}");
			logger.error(e);
			e.printStackTrace();
		}
		out.flush();
		out.close();
	}
}
