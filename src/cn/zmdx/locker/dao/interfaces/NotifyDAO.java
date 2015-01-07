package cn.zmdx.locker.dao.interfaces;

import java.util.List;
import java.util.Map;

import cn.zmdx.locker.entity.Notification;


public interface NotifyDAO extends ParentDAO {
	
	/**
	 * 查询通知List
	 */
	List<Notification> queryNotifyList(Map<String, String> filterMap);
}
