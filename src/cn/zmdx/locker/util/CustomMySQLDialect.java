package cn.zmdx.locker.util;

import java.sql.Types;

import org.hibernate.Hibernate;
import org.hibernate.dialect.MySQL5Dialect;

public class CustomMySQLDialect extends MySQL5Dialect {
	public CustomMySQLDialect() {
		super();
		registerHibernateType(Types.LONGVARCHAR, Hibernate.TEXT.getName());
	}
}
