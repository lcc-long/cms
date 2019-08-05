package com.lcc.cms.service;

import com.lcc.basic.model.Pager;
import com.lcc.cms.model.Group;
import com.lcc.cms.model.Role;
import com.lcc.cms.model.User;

import java.util.List;

public interface IUserService {
    /**
     * 添加用户，需要判断用户名是否存在，如果存在抛出异常
     * @param user 用户对象
     * @param rids 用户的所有角色信息
     * @param gids 用户的所有组信息
     */
    public void add(User user,Integer[]rids,Integer[]gids);
    /**
     * 删除用户，注意需要把用户和角色和组的对应关系删除
     * 如果用户存在相应的文章不能删除
     * @param id
     */
    public void delete(int id);
    /**
     * 用户的更新，如果rids中的角色在用户中已经存在，就不做操作
     * 如果rids中的角色在用户中不存在就要添加，如果用户中的角色不存在于rids中需要进行删除
     * 对于group而已同样要做这个操作
     * @param user
     * @param rids
     * @param gids
     */
    public void update(User user,Integer[] rids,Integer[] gids);
    /**
     * 更新用户的状态
     * @param id
     */
    public void updateStatus(int id);
    /**
     * 列表用户
     */
    public Pager<User> findUser();

    /**
     * 获取用户信息
     * @param id
     * @return
     */
    public User load(int id);
    /**
     * 获取用户的所有角色信息
     * @param id
     * @return
     */
    public List<Role> listUserRoles(int id);
    /**
     * 获取用户的所有组信息
     * @param id
     * @return
     */
    public List<Group> listUserGroups(int id);
}
