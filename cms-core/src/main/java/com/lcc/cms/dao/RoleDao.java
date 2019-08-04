package com.lcc.cms.dao;

import com.lcc.basic.dao.BaseDao;
import com.lcc.cms.model.Role;
import org.springframework.stereotype.Repository;

/**
 * @author lcc
 * @create 2019 - 08 - 03 20:55
 */
@Repository("roleDao")
public class RoleDao extends BaseDao<Role> implements IRoleDao {
}
