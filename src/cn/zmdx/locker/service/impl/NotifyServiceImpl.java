package cn.zmdx.locker.service.impl;

import java.util.List;
import java.util.Map;

import cn.zmdx.locker.dao.interfaces.NotifyDAO;
import cn.zmdx.locker.entity.Notification;
import cn.zmdx.locker.service.interfaces.NotifyService;

public class NotifyServiceImpl implements NotifyService {
	private NotifyDAO notifyDAO;

	public NotifyServiceImpl(NotifyDAO notifyDAO) {
		this.notifyDAO = notifyDAO;
	}

	@Override
	public List<Notification> queryNotifyList(Map<String, String> filterMap) {
		return notifyDAO.queryNotifyList(filterMap);
	}

}
