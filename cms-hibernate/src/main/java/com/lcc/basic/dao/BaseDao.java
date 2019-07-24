package com.lcc.basic.dao;

import com.lcc.basic.model.Pager;
import com.lcc.basic.model.SystemContext;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import javax.annotation.Resource;
import java.lang.reflect.ParameterizedType;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class BaseDao<T> implements IBaseDao<T> {

	@Resource
	private SessionFactory sessionFacotry;


	public SessionFactory getSessionFacotry() {
		return sessionFacotry;
	}

	private Class<T> clz;

	public Class<T> getClz() {
		if (clz == null) {
			clz = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
		}
		return clz;
	}


	public void setSessionFacotry(SessionFactory sessionFacotry) {
		this.sessionFacotry = sessionFacotry;
	}

	protected Session getSession() {
		return sessionFacotry.getCurrentSession();
	}


	public T add(T t) {
		getSession().save(t);
		return t;
	}

	public void update(T t) {
		getSession().update(t);
	}

	public void delete(int id) {
		getSession().delete(this.load(id));
	}

	public T load(int id) {
		return (T) getSession().load(getClz(), id);
	}

	@Override
	public List<T> list(String hql, Object[] args) {

		return this.list(hql, args, null);
	}

	public List<T> list(String hql, Object arg) {
		return this.list(hql, new Object[]{arg});
	}

	@Override
	public List<T> list(String hql) {
		return this.list(hql, null);
	}

	private String initSort(String hql) {
		String order = SystemContext.getOrder();
		String sort = SystemContext.getSort();
		if (sort != null && !"".equals(sort.trim())) {
			hql += " order by " + sort;
			if (order != null) {
				hql += order;
			}
		}
		return hql;
	}

	private void setAliasParameter(Query query, Map<String, Object> alias) {
		if (alias != null) {
			if (alias != null) {
				Set<String> keys = alias.keySet();
				for (String key : keys) {
					Object val = alias.get(key);
					if (val instanceof Collection) {
						query.setParameterList(key, (Collection) val);
					} else {
						query.setParameter(key, val);
					}
				}
			}
		}
	}

	private void setParameter(Query query, Object[] args) {
		if (args != null && args.length > 0) {
			int index = 0;
			for (Object arg : args) {
				query.setParameter(index++, arg);
			}
		}
	}

	@Override
	public List<T> list(String hql, Object[] args, Map<String, Object> alias) {
		hql = initSort(hql);
		Query query = getSession().createQuery(hql);
		//先设置别名，避免出现异常
		setAliasParameter(query, alias);
		setParameter(query, args);
		return query.list();
	}

	@Override
	public List<T> listByAlias(String hql, Map<String, Object> alias) {
		return this.list(hql, null, alias);
	}

	@Override
	public Pager<T> find(String hql, Object[] args) {
		return this.find(hql,args,null);
	}

	@Override
	public Pager<T> find(String hql, Object arg) {
		return this.find(hql,new Object[]{arg});
	}

	@Override
	public Pager<T> find(String hql) {
		return this.find(hql,null);
	}

	private void setPagers(Query query,Pager<T> pager) {
		Integer pageSize = SystemContext.getPageSize();
		Integer pageOffset = SystemContext.getPageOffset();
		if (pageOffset == null || pageOffset < 0) {
			pageOffset = 0;
		}
		if (pageSize == null || pageSize < 0) {
			pageSize = 15;
		}
		pager.setOffset(pageOffset);
		pager.setSize(pageSize);
		query.setFirstResult(pageOffset).setMaxResults(pageSize);
	}

	private String getCountHql(String hql) {
		String e = hql.substring(hql.indexOf("from"));
		String c = "select count(*) " + e;
		c.replaceAll("fetch", "");
		return c;
	}


	@Override
	public Pager<T> find(String hql, Object[] args, Map<String, Object> alias) {
		hql = initSort(hql);
		String cq = getCountHql(hql);
		cq = initSort(cq);
		Query cquery = getSession().createQuery(cq);
		Query query = getSession().createQuery(hql);
		setAliasParameter(query, alias);
		setAliasParameter(cquery,alias);
		setParameter(query, args);
		setParameter(cquery,args);
		Pager<T> pager = new Pager<>();
		setPagers(query,pager);
		List<T> datas = query.list();
		pager.setDatas(datas);
		long total = (long) cquery.uniqueResult();
		pager.setTotal(total);
		return pager ;
	}

	@Override
	public Pager<T> findByAlias(String hql, Map<String, Object> alias) {
		return this.find(hql,null,alias);
	}

	@Override
	public Object queryObject(String hql, Object[] args) {
		return null;
	}

	@Override
	public Object queryObject(String hql, Object arg) {
		return null;
	}

	@Override
	public Object queryObject(String hql) {
		return null;
	}

	@Override
	public void updateByHql(String hql, Object[] args) {

	}

	@Override
	public void updateByHql(String hql, Object arg) {

	}

	@Override
	public void updateByHql(String hql) {

	}

	@Override
	public List<T> listBySql(String sql, Object[] args, Class<T> clz, boolean hasEntity) {
		return null;
	}

	@Override
	public List<T> listBySql(String sql, Object arg, Class<T> clz, boolean hasEntity) {
		return null;
	}

	@Override
	public List<T> listBySql(String sql, Class<T> clz, boolean hasEntity) {
		return null;
	}

	@Override
	public List<T> listBySql(String sql, Object[] args, Map<String, Object> alias, Class<T> clz, boolean hasEntity) {
		return null;
	}

	@Override
	public List<T> listByAliasSql(String sql, Map<String, Object> alias, Class<T> clz, boolean hasEntity) {
		return null;
	}

	@Override
	public Pager<T> findBySql(String sql, Object[] args, Class<T> clz, boolean hasEntity) {
		return null;
	}

	@Override
	public Pager<T> findBySql(String sql, Object arg, Class<T> clz, boolean hasEntity) {
		return null;
	}

	@Override
	public Pager<T> findBySql(String sql, Class<T> clz, boolean hasEntity) {
		return null;
	}

	@Override
	public Pager<T> findBySql(String sql, Object[] args, Map<String, Object> alias, Class<T> clz, boolean hasEntity) {
		return null;
	}

	@Override
	public Pager<T> findByAliasSql(String sql, Map<String, Object> alias, Class<T> clz, boolean hasEntity) {
		return null;
	}
}
