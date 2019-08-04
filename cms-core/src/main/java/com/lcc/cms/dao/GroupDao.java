package com.lcc.cms.dao;

import com.lcc.basic.dao.BaseDao;
import com.lcc.cms.model.Group;
import org.springframework.stereotype.Repository;

/**
 * @author lcc
 * @create 2019 - 08 - 03 20:52
 */
@Repository("groupDao")
public class GroupDao extends BaseDao<Group> implements IGroupDao {
}
