package cn.zmdx.locker.dao.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.springframework.orm.hibernate3.HibernateTemplate;
import cn.zmdx.locker.dao.interfaces.NotifyDAO;
import cn.zmdx.locker.entity.Notification;
import cn.zmdx.locker.util.String2list2mapUtil;

public class NotifyDaoImpl extends ParentDAOImpl implements NotifyDAO {
	public NotifyDaoImpl(HibernateTemplate template) {
		super(template);
	}

	public NotifyDaoImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<Notification> queryNotifyList(Map<String, String> filterMap) {
		StringBuffer sql = new StringBuffer();
//		Date date = new Date();// 取时间
		Date lastModified = new Date(Long.parseLong(filterMap
				.get("lastModified")));// 时间戳转换为时间
//		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat dfl = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		sql.append("from Notification where 1=1 ");
		if (filterMap != null && !filterMap.isEmpty()) {
			if (!"".equals(filterMap.get("lastModified"))) {
				sql.append(" and lastModified >  '" + dfl.format(lastModified)
						+ "'  ");
			}
//			sql.append(" and start_time <  '" + dfl.format(date) + "'  ");
//			sql.append(" and end_time >  '" + dfl.format(date) + "'  ");
			sql.append(" order by lastModified desc ");
		}
		Query query = getSession().createQuery(sql.toString());
		query.setMaxResults(Integer.parseInt(filterMap.get("limit")));
		return query.list();
	}

}
