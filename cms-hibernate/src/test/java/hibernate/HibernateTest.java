package hibernate;

import com.lcc.basic.test.User;
import com.lcc.basic.util.HibernateUtil;
import org.hibernate.Session;
import org.junit.Test;

import java.util.List;

public class HibernateTest {
    @Test
    public void testAdd() {
        Session session = HibernateUtil.openSession();
        try {
            session.beginTransaction();
            User user = new User();
            user.setName("chang");
            user.setAge(32);
            session.save(user);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (session != null) {
                session.getTransaction().rollback();
            }
        } finally {
            if (session != null) {
                HibernateUtil.close(session);
            }
        }
    }

    @Test
    public void testLoad() {
        Session session = null;
        try {
            session = HibernateUtil.openSession();
            User user = session.load(User.class, 1);
            System.out.println(user.getName());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session!=null) session.close();
        }
    }

    @Test
    public void testUpdate() {
        Session session = HibernateUtil.openSession();
        try {
            session.beginTransaction();
            User user = session.load(User.class, 6);
            user.setName("chun");
            session.update(user);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (session != null) {
                session.getTransaction().rollback();
            }
        } finally {
            if (session != null) {
                HibernateUtil.close(session);
            }
        }
    }

    @Test
    public void testDelete() {
        Session session = HibernateUtil.openSession();
        try {
            session.beginTransaction();
            User user = new User();
            user.setId(1);
            session.delete(user);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (session != null) {
                session.getTransaction().rollback();
            }
        } finally {
            if (session != null) {
                HibernateUtil.close(session);
            }
        }
    }

    public void testList() {
        Session session = null;
        try {
            session = HibernateUtil.openSession();
            List<User> users = session.createQuery("from User").list();
            for (User u : users) {
                System.out.println(u.getName());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session!=null) session.close();
        }
    }

    @Test
    public void testPager() {
        Session session = null;
        try {
            session = HibernateUtil.openSession();
            List<User> users = session.createQuery("from User").setFirstResult(0)
                        .setMaxResults(2).list();
            for (User u : users) {
                System.out.println(u.getName());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session!=null) session.close();
        }
    }
}
