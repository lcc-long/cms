package com.lcc.cms.dao;

import com.lcc.basic.dao.IBaseDao;
import com.lcc.cms.model.*;

import java.util.List;

public interface IUserDao extends IBaseDao<User> {

    public void add(User user, Integer[] rids, Integer[] gids);


    public void update(User user, Integer[] rids, Integer[] gids);

    /**
     * 获取用户的所有角色信息
     *
     * @param userId
     * @return
     */
    public List<Role> listUserRoles(int userId);

    public List<Integer> listUserRoleIds(int userId);

    public List<Group> listUserGroups(int userId);

    public List<Integer> listUserGroudIds(int userId);

    public UserRole loadUserRole(int userId, int roleId);

    public UserGroup loadUserGroup(int userId, int groupId);

    public User loadByUserName(String userName);

    public List<User> listRoleUsers(int roleId);

    public List<User> listRoleUsers(RoleType roleType);

    public List<User> listGroupUsers(int gid);

}
