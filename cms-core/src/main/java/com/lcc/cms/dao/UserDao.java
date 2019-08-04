package com.lcc.cms.dao;

import com.lcc.basic.dao.BaseDao;
import com.lcc.cms.model.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author lcc
 * @create 2019 - 08 - 03 20:54
 */
@Repository("userDao")
public class UserDao extends BaseDao<User> implements IUserDao {
	@Override
	public void add(User user, Integer[] rids, Integer[] gids) {

	}

	@Override
	public void update(User user, Integer[] rids, Integer[] gids) {

	}

	@Override
	public List<Role> listUserRoles(int userId) {
		String hql = "select ur.role from UserRole ur where ur.user.id=?0";
		return this.getSession().createQuery(hql).setParameter(0, userId).list();
	}

	@Override
	public List<Integer> listUserRoleIds(int userId) {
		String hql = "select ur.role.id from UserRole ur where ur.user.id=?";
		return this.getSession().createQuery(hql).setParameter(0, userId).list();
	}

	@Override
	public List<Group> listUserGroups(int userId) {
		String hql = "select ug.group from UserGroup ug where ug.user.id=?";
		return this.getSession().createQuery(hql).setParameter(0, userId).list();
	}

	@Override
	public List<Integer> listUserGroudIds(int userId) {
		String hql = "select ug.group.id from UserGroup ug where ug.user.id=?";
		return this.getSession().createQuery(hql).setParameter(0, userId).list();
	}

	@Override
	public UserRole loadUserRole(int userId, int roleId) {
		String hql = "select ur from UserRole ur where ur.user.id=? and ur.role.id=?";
		return (UserRole) this.getSession().createQuery(hql)
				.setParameter(0, userId).setParameter(1, roleId).uniqueResult();
	}

	@Override
	public UserGroup loadUserGroup(int userId, int groupId) {
		String hql = "select ug from UserGroup ug where ug.user.id=? and ug.group.id=?";
		return (UserGroup) this.getSession().createQuery(hql)
				.setParameter(0, userId).setParameter(1, groupId).uniqueResult();
	}

	@Override
	public User loadByUserName(String userName) {
		String hql = "from User where username=?";
		return (User) this.queryObject(hql, userName);
	}

	@Override
	public List<User> listRoleUsers(int roleId) {
		String hql = "select ur.user from UserRole ur where ur.role.id=?";
		return this.list(hql, roleId);
	}

	@Override
	public List<User> listRoleUsers(RoleType roleType) {
		String hql = "select ur.user from UserRole ur where ur.role.roleType=?";
		return this.list(hql, roleType);
	}

	@Override
	public List<User> listGroupUsers(int gid) {
		String hql = "select ug.user from UsrGroup ug where ug.group.id=?";
		return this.list(hql, hql);
	}
}
