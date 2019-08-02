package com.lcc.basic.dao;

import com.lcc.basic.model.Pager;

import java.util.List;
import java.util.Map;

public interface IBaseDao<T> {

    public T add(T t);

    public void update(T t);

    public void delete(int id);

    public T load(int id);

    /**
     * 不分页
     * @param hql
     * @param args 基于查询参数
     * @return
     */
    public List<T> list(String hql, Object[] args);

    public List<T> list(String hql, Object arg);

    public List<T> list(String hql);


    /**
     * 不分页
     * @param hql
     * @param args
     * @param alias 基于别名
     * @return
     */

    public List<T> list(String hql, Object[] args, Map<String,Object> alias);

    public List<T> listByAlias(String hql, Map<String, Object> alias);




    /**
     * 分页
     * @param hql
     * @param args 基于查询参数
     * @return
     */
    public Pager<T> find(String hql, Object[] args);

    public Pager<T> find(String hql, Object arg);

    public Pager<T> find(String hql);


    /**
     * 分页
     * @param hql
     * @param args
     * @param alias 基于别名
     * @return
     */

    public Pager<T> find(String hql, Object[] args, Map<String,Object> alias);

    public Pager<T> findByAlias(String hql, Map<String, Object> alias);


    /**
     * 根据hql查询对象
     * @param hql
     * @param args
     * @return
     */
    public Object queryObject(String hql,Object[] args);
    public Object queryObject(String hql,Object arg);
    public Object queryObject(String hql);

    public Object queryObject(String hql, Object[] args, Map<String, Object> alias);
    public Object queryObjectByAlias(String hql, Map<String, Object> alias);

    /**
     * 根据hql更新对象
     * @param hql
     * @param args
     */
    public void updateByHql(String hql,Object[] args);
    public void updateByHql(String hql,Object arg);
    public void updateByHql(String hql);

    /**
     * 根据sql查询对象，不包括关联对象
     * @param sql
     * @param args
     * @param clz 实体对象
     * @param hasEntity hibernate所管理的实体
     * @return
     */
    public List<T> listBySql(String sql,Object[] args,Class<T> clz,boolean hasEntity);
    public List<T> listBySql(String sql,Object arg,Class<T> clz,boolean hasEntity);
    public List<T> listBySql(String sql,Class<T> clz,boolean hasEntity);
    public List<T> listBySql(String sql,Object[] args,Map<String,Object> alias,Class<T> clz,boolean hasEntity);
    public List<T> listByAliasSql(String sql,Map<String,Object> alias,Class<T> clz,boolean hasEntity);


    public <N> Pager<N> findBySql(String sql,Object[] args,Class<?> clz,boolean hasEntity);
    public <N> Pager<N> findBySql(String sql,Object arg,Class<?> clz,boolean hasEntity);
    public <N> Pager<N> findBySql(String sql,Class<?> clz,boolean hasEntity);
    public <N> Pager<N> findBySql(String sql,Object[] args,Map<String,Object> alias,Class<?> clz,boolean hasEntity);
    public <N> Pager<N> findByAliasSql(String sql,Map<String,Object> alias,Class<?> clz,boolean hasEntity);
}
