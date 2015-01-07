package cn.zmdx.locker.service.interfaces;

import java.util.List;
import java.util.Map;

import cn.zmdx.locker.entity.Notification;


public interface NotifyService {
	
	/**
	 * 查询通知List
	 */
	List<Notification> queryNotifyList(Map<String, String> filterMap);
}
