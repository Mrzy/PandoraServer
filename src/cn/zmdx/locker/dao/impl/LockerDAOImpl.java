package cn.zmdx.locker.dao.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.springframework.orm.hibernate3.HibernateTemplate;

import cn.zmdx.locker.dao.interfaces.LockerDAO;
import cn.zmdx.locker.entity.Data_img_table;
import cn.zmdx.locker.entity.Data_table;
import cn.zmdx.locker.entity.Img;
import cn.zmdx.locker.entity.WallPaper;
import cn.zmdx.locker.util.String2list2mapUtil;

public class LockerDAOImpl extends ParentDAOImpl implements LockerDAO {
	public LockerDAOImpl(HibernateTemplate template) {
		super(template);
	}

	public LockerDAOImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<Data_table> queryDataTable(Map<String, String> filterMap) {
		StringBuffer sql = new StringBuffer();
		sql.append("from Data_table where 1=1 ");
		if (filterMap != null && !filterMap.isEmpty()) {
			if (!"".equals(filterMap.get("webSite"))
					&& filterMap.get("webSite") != null
					&& !"''".equals(filterMap.get("webSite"))
					&& !"null".equals(filterMap.get("webSite")))
				sql.append("and collect_website in ( :siteList ) ");
			if (!"".equals(filterMap.get("dataType"))
					&& filterMap.get("dataType") != null
					&& !"''".equals(filterMap.get("dataType"))
					&& !"null".equals(filterMap.get("dataType")))
				sql.append("and data_type in ( :typeList ) ");
		}
		sql.append(" order by rand() ");
		Query query = getSession().createQuery(sql.toString());
		query.setMaxResults(Integer.parseInt(filterMap.get("limit")));
		if (!"".equals(filterMap.get("webSite"))
				&& filterMap.get("webSite") != null
				&& !"''".equals(filterMap.get("webSite"))
				&& !"null".equals(filterMap.get("webSite"))) {
			if (filterMap.get("webSite").indexOf(String.valueOf(",")) == -1)
				query.setParameter("siteList", filterMap.get("webSite"));
			else
				query.setParameterList("siteList", String2list2mapUtil
						.StringToList(filterMap.get("webSite")).toArray());
		}
		if (!"".equals(filterMap.get("dataType"))
				&& filterMap.get("dataType") != null
				&& !"''".equals(filterMap.get("dataType"))
				&& !"null".equals(filterMap.get("dataType"))) {
			if (filterMap.get("dataType").indexOf(String.valueOf(",")) == -1)
				query.setParameter("typeList", filterMap.get("dataType"));
			else
				query.setParameterList("typeList", String2list2mapUtil
						.StringToList(filterMap.get("dataType")).toArray());
		}
		return query.list();
	}

	@Override
	public List<Data_img_table> queryDataImgTable(Map<String, String> filterMap) {
		StringBuffer sql = new StringBuffer();
		Date date = new Date();// 取时间
		Date lastModified = new Date(Long.parseLong(filterMap
				.get("lastModified")));// 时间戳转换为时间
		// Calendar calendar = new GregorianCalendar();
		// calendar.setTime(date);
		// calendar.add(calendar.DATE, -1);// 把日期往后增加一天.整数往后推,负数往前移动
		// date = calendar.getTime(); // 这个时间就是日期往后推一天的结果
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat dfl = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		sql.append("from Data_img_table where 1=1 ");
		if (filterMap != null && !filterMap.isEmpty()) {
			if (!"".equals(filterMap.get("webSite"))
					&& filterMap.get("webSite") != null
					&& !"''".equals(filterMap.get("webSite"))
					&& !"null".equals(filterMap.get("webSite")))
				sql.append("and collect_website in ( :siteList ) ");
			if (!"".equals(filterMap.get("dataType"))
					&& filterMap.get("dataType") != null
					&& !"''".equals(filterMap.get("dataType"))
					&& !"null".equals(filterMap.get("dataType"))) {
				sql.append(" and data_type in ( :typeList )  ");
				// if (filterMap.get("dataType").contains("news")) {
				// }
			}
			if (!"".equals(filterMap.get("lastModified"))) {
				sql.append(" and collect_time >  '" + dfl.format(lastModified)
						+ "'  ");
			}

			// if (filterMap.get("dataType") !=
			// null&&filterMap.get("dataType").contains("news"))
			// sql.append(" order by collect_time desc ");
			// else
			sql.append(" and collect_time like  '%" + df.format(date) + "%'  ");
			sql.append(" order by rand() ");
		}
		Query query = getSession().createQuery(sql.toString());
		if (filterMap != null && !filterMap.isEmpty()) {
			query.setMaxResults(Integer.parseInt(filterMap.get("limit")));
			if (!"".equals(filterMap.get("webSite"))
					&& filterMap.get("webSite") != null
					&& !"''".equals(filterMap.get("webSite"))
					&& !"null".equals(filterMap.get("webSite"))) {
				if (filterMap.get("webSite").indexOf(String.valueOf(",")) == -1)
					query.setParameter("siteList", filterMap.get("webSite"));
				else
					query.setParameterList("siteList", String2list2mapUtil
							.StringToList(filterMap.get("webSite")).toArray());
			}
			if (!"".equals(filterMap.get("dataType"))
					&& filterMap.get("dataType") != null
					&& !"''".equals(filterMap.get("dataType"))
					&& !"null".equals(filterMap.get("dataType"))) {
				if (filterMap.get("dataType").indexOf(String.valueOf(",")) == -1)
					query.setParameter("typeList", filterMap.get("dataType"));
				else
					query.setParameterList("typeList", String2list2mapUtil
							.StringToList(filterMap.get("dataType")).toArray());
			}
		}
		return query.list();
	}

	@Override
	public List<Img> queryDataById(Map<String, String> filterMap) {
		StringBuffer sql=new StringBuffer("select i.id,i.imageUrl,i.content from img i left join Data_img d on d.img_id=i.id where 1=1 ");
		if (filterMap != null && !filterMap.isEmpty()) {
			if (!"".equals(filterMap.get("id"))
					&& filterMap.get("id") != null
					&& !"''".equals(filterMap.get("id"))
					&& !"null".equals(filterMap.get("id"))) {
				sql.append(" and d.data_id="+filterMap.get("id"));
			}
		}
		Query query = getSession().createSQLQuery(sql.toString());
		
		return query.list();
	}

	@Override
	public Data_img_table getDataImgTableById(String id) {
		return (Data_img_table)this.getHibernateTemplate().get(Data_img_table.class, Integer.parseInt(id));
	}

	public List<WallPaper> queryWallPaper(Map<String, String> filterMap) {
		StringBuffer sql = new StringBuffer();
		sql.append("from WallPaper where 1=1 ");
		if (filterMap != null && !filterMap.isEmpty()) {
			sql.append(" order by publishDATE desc ");
		}
		Query query = getSession().createQuery(sql.toString());
		if (filterMap != null && !filterMap.isEmpty()) {
			query.setMaxResults(24);
		}
		return query.list();
	}

}
