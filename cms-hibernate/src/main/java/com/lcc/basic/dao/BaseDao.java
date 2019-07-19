package com.lcc.basic.dao;

import com.lcc.basic.model.Pager;

import java.util.List;
import java.util.Map;

public class BaseDao<T> implements IBaseDao<T> {

    public T add(T t) {
        return null;
    }

    public void update(T t) {

    }

    public void delete(int id) {

    }

    public T load(int id) {
        return null;
    }

    @Override
    public List<T> list(String hql, Object[] args) {
        return null;
    }

    @Override
    public List<T> list(String hql, Object arg) {
        return null;
    }

    @Override
    public List<T> list(String hql) {
        return null;
    }

    @Override
    public List<T> list(String hql, Object[] args, Map<String, Object> alias) {
        return null;
    }

    @Override
    public List<T> list(String hql, Map<String, Object> alias) {
        return null;
    }

    @Override
    public Pager<T> find(String hql, Object[] args) {
        return null;
    }

    @Override
    public Pager<T> find(String hql, Object arg) {
        return null;
    }

    @Override
    public Pager<T> find(String hql) {
        return null;
    }

    @Override
    public Pager<T> find(String hql, Object[] args, Map<String, Object> alias) {
        return null;
    }

    @Override
    public Pager<T> find(String hql, Map<String, Object> alias) {
        return null;
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
    public List<T> listBySql(String sql, Map<String, Object> alias, Class<T> clz, boolean hasEntity) {
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
    public Pager<T> findBySql(String sql, Map<String, Object> alias, Class<T> clz, boolean hasEntity) {
        return null;
    }
}
