import com.lcc.basic.test.User;
import com.lcc.basic.util.HibernateUtil;
import org.hibernate.Session;
import org.junit.Test;

public class HibernateStatusTest {
    @Test
    public void testTransient() {
        Session session = null;
        try {
            session = HibernateUtil.openSession();
            session.beginTransaction();
            User user = new User();
            user.setName("小明");
            //在save之前，user就是瞬时状态，没有被session管理
            session.save(user);
            //在save之后，user就是持久化状态，被session管理
            user.setName("小刘");
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
    public void testDetachedToPersistent() {
        Session session = null;
        try {
            session = HibernateUtil.openSession();
            session.beginTransaction();
            User user = session.get(User.class, 1);
            session.evict(user);
//            user.setName("小蛇");
            session.update(user);
//            System.out.println(session.get(User.class, 1).getName());
//            user.setName("小蛇");
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
}
