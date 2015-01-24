package cn.zmdx.locker.actions;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import cn.zmdx.locker.entity.Notification;
import cn.zmdx.locker.service.impl.NotifyServiceImpl;

import com.alibaba.fastjson.JSON;
import com.opensymphony.xwork2.ActionSupport;

public class NotifyAction extends ActionSupport {
	Logger logger = Logger.getLogger(NotifyAction.class);
	private NotifyServiceImpl notifyService;

	public void setNotifyService(NotifyServiceImpl notifyService) {
		this.notifyService = notifyService;
	}

	/**
	 * 查询通知数据List
	 * 
	 * @throws IOException
	 */
	public void queryNotifyList() throws IOException {
		ServletActionContext.getResponse().setContentType(
				"text/json; charset=utf-8");
		String flag = ServletActionContext.getRequest().getParameter("limit");
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
		filterMap.put("lastModified", lastModified);
		try {
			List<Notification> list = notifyService.queryNotifyList(filterMap);
			if (null != list && list.size() > 0)
				out.print("{\"state\":\"success\",\"lastModified\":"+list.get(0).getLastModified().getTime()+",\"data\":" + JSON.toJSONString(list, true) + "}");
			else
				out.print("{\"state\":\"success\",\"data\":\"null\"}");
		} catch (Exception e) {
			out.print("{\"state\":\"error\"}");
			logger.error(e);
			e.printStackTrace();
		}
		out.flush();
		out.close();
	}

}
