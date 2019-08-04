package com.lcc.basic.dao;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.lcc.basic.model.Pager;
import com.lcc.basic.model.SystemContext;
import com.lcc.basic.util.EntitiesHelper;
import com.lcc.basic.model.User;
import org.dbunit.DatabaseUnitException;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.dbunit.operation.DatabaseOperation;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.orm.hibernate5.SessionFactoryUtils;
import org.springframework.orm.hibernate5.SessionHolder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.lcc.basic.util.AbstractDbUnitTestCase;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/beans.xml"})
//@TestExecutionListeners({DbUnitTestExecutionListener.class,
//        DependencyInjectionTestExecutionListener.class})
public class TestUserDao extends AbstractDbUnitTestCase {
    @Inject
    @Named("sessionFactory")
    private SessionFactory sessionFactory;
    @Inject
    @Named("userDao")
    private IUserDao userDao;

    @Before
    public void setUp() throws SQLException, IOException, DataSetException {
        //解决延迟加载
        Session session = sessionFactory.openSession();
        TransactionSynchronizationManager.bindResource(sessionFactory, new SessionHolder(session));
        this.backupAllTable();
    }

    @Test
    public void testLoad() throws DatabaseUnitException, SQLException {
        IDataSet ds = createDateSet("t_user");
        DatabaseOperation.CLEAN_INSERT.execute(dbunitCon, ds);
        User u = userDao.load(1);
        EntitiesHelper.assertUser(u);
    }

    @Test(expected = ObjectNotFoundException.class)
    public void testDelete() throws DatabaseUnitException, SQLException {
        IDataSet ds = createDateSet("t_user");
        DatabaseOperation.CLEAN_INSERT.execute(dbunitCon, ds);
        userDao.delete(1);
        User user = userDao.load(1);
        System.out.println(user.getUsername());
    }

    @Test
    public void testListByArgs() throws DatabaseUnitException, SQLException {
        IDataSet ds = createDateSet("t_user");
        DatabaseOperation.CLEAN_INSERT.execute(dbunitCon, ds);
        SystemContext.setOrder("desc");
        SystemContext.setSort("id");
        List<User> actuals = userDao.list("from User where id>?1 and id<?2", new Object[]{1, 4});
        List<User> expected = Arrays.asList(new User(3, "admin3"), new User(2, "admin2"));
        assertNotNull(actuals);
        assertTrue(actuals.size() == 2);
        EntitiesHelper.assertUsers(expected, actuals);
    }

    @Test
    public void testListByArgsAndAlias() throws DatabaseUnitException, SQLException {
        IDataSet ds = createDateSet("t_user");
        DatabaseOperation.CLEAN_INSERT.execute(dbunitCon, ds);
        SystemContext.setOrder("asc");
        SystemContext.setSort("id");
        Map<String, Object> alias = new HashMap<>();
        alias.put("ids", Arrays.asList(1, 2, 3, 5, 6, 7, 8, 9, 10));
        List<User> actuals = userDao.list("from User where id>?1 and id<?2 and id in(:ids)", new Object[]{1, 5}, alias);
        List<User> expected = Arrays.asList(new User(2, "admin2"), new User(3, "admin3"));
        assertNotNull(actuals);
        assertTrue(actuals.size() == 2);
        EntitiesHelper.assertUsers(expected, actuals);
    }

    @Test
    public void testFindByArgs() throws DatabaseUnitException, SQLException {
        IDataSet ds = createDateSet("t_user");
        DatabaseOperation.CLEAN_INSERT.execute(dbunitCon, ds);
        SystemContext.setOrder("desc");
        SystemContext.setSort("id");
        SystemContext.setPageOffset(0);
        SystemContext.setPageSize(3);
        Pager<User> expected = userDao.find("from User where id>=?1 and id<=?2", new Object[]{1, 10});
        List<User> actuals = Arrays.asList(new User(10, "admin10"), new User(9, "admin9"), new User(8, "admin8"));
        assertNotNull(expected);
        assertTrue(expected.getTotal() == 10);
        assertTrue(expected.getOffset() == 0);
        assertTrue(expected.getSize() == 3);
        EntitiesHelper.assertUsers(expected.getDatas(), actuals);
    }

    @Test
    public void testFindByArgsAndAlias() throws DatabaseUnitException, SQLException {
        IDataSet ds = createDateSet("t_user");
        DatabaseOperation.CLEAN_INSERT.execute(dbunitCon, ds);
        SystemContext.setPageOffset(0);
        SystemContext.setPageSize(3);
        Map<String, Object> alias = new HashMap<>();
        alias.put("ids", Arrays.asList(1, 2, 4, 5, 6, 7, 8, 10));
        Pager<User> expected = userDao.find("from User where id>=?1 and id<=?2 and id in (:ids)", new Object[]{1, 10}, alias);
        List<User> actuals = Arrays.asList(new User(1, "admin1"), new User(2, "admin2"), new User(4, "admin4"));
        assertNotNull(expected);
        assertTrue(expected.getTotal() == 8);
        assertTrue(expected.getOffset() == 0);
        assertTrue(expected.getSize() == 3);
        EntitiesHelper.assertUsers(expected.getDatas(), actuals);
    }

    @Test
    public void testListSQLByArgs() throws DatabaseUnitException, SQLException {
        IDataSet ds = createDateSet("t_user");
        DatabaseOperation.CLEAN_INSERT.execute(dbunitCon, ds);
        SystemContext.setOrder("desc");
        SystemContext.setSort("id");
        List<User> expected = userDao.listBySql("select * from t_user where id>?1 and id<?2", new Object[]{1, 4}, User.class, true);
        List<User> actuals = Arrays.asList(new User(3,"admin3"),new User(2,"admin2"));
        assertNotNull(expected);
        assertTrue(expected.size()==2);
        EntitiesHelper.assertUsers(expected, actuals);
    }
    @Test
    public void testListSQLByAlias() throws DatabaseUnitException, SQLException {
        IDataSet ds = createDateSet("t_user");
        DatabaseOperation.CLEAN_INSERT.execute(dbunitCon,ds);
        SystemContext.setOrder("asc");
        SystemContext.setSort("id");
        Map<String,Object> alias = new HashMap<String,Object>();
        alias.put("id1", 1);
        alias.put("id2", 5);
        alias.put("ids", Arrays.asList(1,2,3,5,6,7,8,9,10));
        //hibernate版本 原生不支持混合参数
        List<User> expected = userDao.listByAliasSql("select * from t_user where id>:id1 and id<:id2 and id in(:ids)", alias,User.class,true);
        List<User> actuals = Arrays.asList(new User(2,"admin2"),new User(3,"admin3"));
        assertNotNull(expected);
        assertTrue(expected.size()==2);
        EntitiesHelper.assertUsers(expected, actuals);
    }

    @Test
    public void testFindSQLByArgs() throws DatabaseUnitException, SQLException {
        IDataSet ds = createDateSet("t_user");
        DatabaseOperation.CLEAN_INSERT.execute(dbunitCon,ds);
        SystemContext.setOrder("desc");
        SystemContext.setSort("id");
        SystemContext.setPageSize(3);
        SystemContext.setPageOffset(0);
        Pager<User> expected = userDao.findBySql("select * from t_user where id>=?1 and id<=?2", new Object[]{1,10},User.class,true);
        List<User> actuals = Arrays.asList(new User(10,"admin10"),new User(9,"admin9"),new User(8,"admin8"));
        assertNotNull(expected);
        assertTrue(expected.getTotal()==10);
        assertTrue(expected.getOffset()==0);
        assertTrue(expected.getSize()==3);
        EntitiesHelper.assertUsers(expected.getDatas(), actuals);
    }
    @Test
    public void testFindSQLByArgsAndAlias() throws DatabaseUnitException, SQLException {
        IDataSet ds = createDateSet("t_user");
        DatabaseOperation.CLEAN_INSERT.execute(dbunitCon,ds);
        SystemContext.removeOrder();
        SystemContext.removeSort();
        SystemContext.setPageSize(3);
        SystemContext.setPageOffset(0);
        Map<String,Object> alias = new HashMap<String,Object>();
        alias.put("id1", 1);
        alias.put("id2", 10);
        alias.put("ids", Arrays.asList(1,2,4,5,6,7,8,10));
        Pager<User> expected = userDao.findByAliasSql("select * from t_user where id>=:id1 and id<=:id2 and id in(:ids)",alias,User.class,true);
        List<User> actuals = Arrays.asList(new User(1,"admin1"),new User(2,"admin2"),new User(4,"admin4"));
        assertNotNull(expected);
        assertTrue(expected.getTotal()==8);
        assertTrue(expected.getOffset()==0);
        assertTrue(expected.getSize()==3);
        EntitiesHelper.assertUsers(expected.getDatas(), actuals);
    }


    @After
    public void tearDown() throws FileNotFoundException, DatabaseUnitException, SQLException {

        SessionHolder holder = (SessionHolder) TransactionSynchronizationManager.getResource(sessionFactory);
        Session session = holder.getSession();
//        session.flush();
        TransactionSynchronizationManager.unbindResource(sessionFactory);
        SessionFactoryUtils.closeSession(session);
        this.resumeTable();
    }
}
