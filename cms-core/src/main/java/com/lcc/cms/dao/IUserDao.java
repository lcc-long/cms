package com.lcc.cms.dao;

import com.lcc.basic.dao.IBaseDao;
import com.lcc.basic.test.User;
import com.lcc.cms.model.Role;

import java.util.List;

public interface IUserDao extends IBaseDao<User> {

    public List<Role> listUserRoles(int userId);
}
