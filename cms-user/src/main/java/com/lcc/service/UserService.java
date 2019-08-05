package com.lcc.service;

import com.lcc.basic.model.Pager;
import com.lcc.cms.dao.IGroupDao;
import com.lcc.cms.dao.IRoleDao;
import com.lcc.cms.dao.IUserDao;
import com.lcc.cms.model.CmsException;
import com.lcc.cms.model.Group;
import com.lcc.cms.model.Role;
import com.lcc.cms.model.User;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

@Service("userService")
public class UserService implements IUserService {
    @Inject
    private IUserDao userDao;
    @Inject
    private IRoleDao roleDao;
    @Inject
    private IGroupDao groupDao;

    @Override
    public void add(User user, Integer[] rids, Integer[] gids) {
        User tu = userDao.loadByUsername(user.getUsername());
        if (tu != null) throw new CmsException("添加的用户对象已经存在，不能添加");
        userDao.add(user);
        //添加角色对象
        for (Integer rid : rids) {
            this.addUserRole(user, rid);
        }
        //添加用户组对象
        for (Integer gid : gids) {
            addUserGroup(user, gid);
        }
    }

    private void addUserRole(User user, int rid) {
        //1、检查角色对象是否存在，如果不存在，就抛出异常
        Role role = roleDao.load(rid);
        if (role == null) throw new CmsException("要添加的用户角色不存在");
        //2、检查用户角色对象是否已经存在，如果存在，就不添加
        userDao.addUserRole(user, role);
    }

    private void addUserGroup(User user, int gid) {
        Group group = groupDao.load(gid);
        if (group == null) throw new CmsException("要添加用户的组对象不存在");
        userDao.addUserGroup(user, group);
    }

    @Override
    public void delete(int id) {
        //TODO 需要进行用户是否有文章的判断

        //1、删除用户管理的角色对象
        userDao.deleteUserGroups(id);
        //2、删除用户管理的组对象
        userDao.deleteUserRoles(id);
        userDao.delete(id);
    }

    @Override
    public void update(User user, Integer[] rids, Integer[] gids) {
        //1、获取用户已经存在的组id和角色id
        List<Integer> erids = userDao.listUserRoleIds(user.getId());
        List<Integer> egids = userDao.listUserGroupIds(user.getId());
        //2、判断，如果erids中不存在rids就要进行添加
        for (Integer rid : rids) {
            if (!erids.contains(rid)) {
                addUserRole(user, rid);
            }
        }
        for (Integer gid : gids) {
            if (!egids.contains(gid)) {
                addUserGroup(user, gid);
            }
        }
        //3、进行删除
        for (Integer erid : erids) {
            if (!ArrayUtils.contains(rids, erid)) {
                userDao.deleteUserRole(user.getId(), erid);
            }
        }

        for (Integer egid : egids) {
            if (!ArrayUtils.contains(gids, egid)) {
                userDao.deleteUserGroup(user.getId(), egid);
            }
        }
    }

    @Override
    public void updateStatus(int id) {
        User u = userDao.load(id);
        if (u == null) throw new CmsException("修改状态的用户不存在");
        if (u.getStatus() == 0) u.setStatus(1);
        else u.setStatus(0);
        userDao.update(u);
    }

    @Override
    public Pager<User> findUser() {
        return userDao.findUser();
    }

    @Override
    public User load(int id) {
        return userDao.load(id);
    }

    @Override
    public List<Role> listUserRoles(int id) {
        return userDao.listUserRoles(id);
    }

    @Override
    public List<Group> listUserGroups(int id) {
        return userDao.listUserGroups(id);
    }

}
