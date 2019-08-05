package com.lcc.cms.service;

import com.lcc.cms.dao.IGroupDao;
import com.lcc.cms.dao.IRoleDao;
import com.lcc.cms.dao.IUserDao;
import org.easymock.EasyMock;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;

import static org.easymock.EasyMock.*;

/**
 * 完全与Dao、sessionFactory独立
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/test-beans.xml")
public class TestUserService {
    @Inject
    private IUserService userService;
    @Inject
    private IRoleDao roleDao;
    @Inject
    private IUserDao userDao;
    @Inject
    private IGroupDao groupDao;

    @Test
    public void testDelete() {
        int uid =2;
        userDao.deleteUserGroups(uid);
        expectLastCall();
        userDao.deleteUserGroups(uid);
        expectLastCall();
        userDao.delete(uid);
        expectLastCall();
        replay(userDao);
        userService.delete(uid);
        verify(userDao);
    }

}
