package com.lcc.cms.dao;

import com.lcc.basic.dao.BaseDao;
import com.lcc.basic.model.Pager;
import com.lcc.cms.model.*;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author lcc
 * @create 2019 - 08 - 03 20:54
 */
@Repository("userDao")
public class UserDao extends BaseDao<User> implements IUserDao {
//	@Override
//	public void add(User user, Integer[] rids, Integer[] gids) {
//
//	}
//
//	@Override
//	public void update(User user, Integer[] rids, Integer[] gids) {
//
//	}

	@Override
	public List<Role> listUserRoles(int userId) {
		String hql = "select ur.role from UserRole ur where ur.user.id=?0";
		return this.getSession().createQuery(hql).setParameter(0, userId).list();
	}

	@Override
	public List<Integer> listUserRoleIds(int userId) {
		String hql = "select ur.role.id from UserRole ur where ur.user.id=?0";
		return this.getSession().createQuery(hql).setParameter(0, userId).list();
	}

	@Override
	public List<Group> listUserGroups(int userId) {
		String hql = "select ug.group from UserGroup ug where ug.user.id=?0";
		return this.getSession().createQuery(hql).setParameter(0, userId).list();
	}

	@Override
	public List<Integer> listUserGroupIds(int userId) {
		String hql = "select ug.group.id from UserGroup ug where ug.user.id=?0";
		return this.getSession().createQuery(hql).setParameter(0, userId).list();
	}

	@Override
	public UserRole loadUserRole(int userId, int roleId) {
//		String hql = "select ur from UserRole ur where ur.user.id=?0 and ur.role.id=?1";
		String hql = "select ur from UserRole ur left join fetch ur.user u left join fetch ur.role r where u.id=?0 and r.id=?1";
		return (UserRole) this.getSession().createQuery(hql)
				.setParameter(0, userId).setParameter(1, roleId).uniqueResult();
	}

	@Override
	public UserGroup loadUserGroup(int userId, int groupId) {
//		String hql = "select ug from UserGroup ug where ug.user.id=?0 and ug.group.id=?1";
		String hql = "select ug from UserGroup ug left join fetch ug.user u left join fetch ug.group g where u.id=?0 and g.id=?1";
		return (UserGroup) this.getSession().createQuery(hql)
				.setParameter(0, userId).setParameter(1, groupId).uniqueResult();
	}

	@Override
	public List<User> listRoleUsers(int roleId) {
		String hql = "select ur.user from UserRole ur where ur.role.id=?0";
		return this.list(hql, roleId);
	}

	@Override
	public List<User> listRoleUsers(RoleType roleType) {
		String hql = "select ur.user from UserRole ur where ur.role.roleType=?0";
		return this.list(hql, roleType);
	}

	@Override
	public List<User> listGroupUsers(int gid) {
		String hql = "select ug.user from UserGroup ug where ug.group.id=?0";
		return this.list(hql, gid);
	}

    @Override
    public void addUserGroup(User user, Group group) {
		UserGroup ug = this.loadUserGroup(user.getId(), group.getId());
		if(ug!=null) return;
		ug = new UserGroup();
		ug.setGroup(group);
		ug.setUser(user);
		this.getSession().save(ug);
    }

    @Override
    public void addUserRole(User user, Role role) {
        UserRole ur = this.loadUserRole(user.getId(), role.getId());
        if(ur!=null) return;
        ur = new UserRole();
        ur.setRole(role);
        ur.setUser(user);
        this.getSession().save(ur);
    }

    @Override
    public void deleteUserRoles(int uid) {
		String hql = "delete UserRole ur where ur.user.id=?0";
		this.updateByHql(hql, uid);
    }

    @Override
    public void deleteUserRole(int uid, int rid) {
		String hql = "delete UserRole ur where ur.user.id=?0 and ur.role.id=?1";
		this.updateByHql(hql,new Object[]{uid,rid});
    }

    @Override
    public void deleteUserGroup(int uid, int gid) {
		String hql = "delete UserGroup ug where ug.user.id=?0 and ug.group.id=?1";
		this.updateByHql(hql, new Object[]{uid,gid});
    }

    @Override
    public Pager<User> findUser() {
        return this.find("from User");
    }



    @Override
    public void deleteUserGroups(int gid) {
		String hql = "delete UserGroup ug where ug.user.id=?0";
		this.updateByHql(hql, gid);
    }

    @Override
    public User loadByUsername(String username) {
        String hql = "from User where username=?0";
        return (User) this.queryObject(hql, username);
    }
}
