import com.lcc.basic.test.Student;
import com.lcc.basic.test.Teacher;
import com.lcc.basic.util.HibernateUtil;
import org.hibernate.Session;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

public class HibernateRelTest {

    @Test
    public void manyToMany() {
        Session session = null;
        try {
            session = HibernateUtil.openSession();
            session.beginTransaction();
            Student stu = new Student();
            stu.setName("long");
            Teacher teacher = new Teacher();
            teacher.setName("xiaoming");
            teacher.getStus().add(stu);
            session.save(stu);
            session.save(teacher);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (session != null) {
                session.getTransaction().rollback();
            }
        } finally {
            HibernateUtil.close(session);
        }
    }

    @Test
    public void oneToMany2() {
        Session session = null;
        try {
            session = HibernateUtil.openSession();
            session.beginTransaction();
            Teacher teacher = new Teacher();
            teacher.setName("xiaoming");
            session.save(teacher);
            Set<Student> stus = new HashSet<>();
            Student student = new Student();
            student.setName("lcc");
            stus.add(student);
            session.save(student);
            student = new Student();
            student.setName("chang");
            stus.add(student);
            session.save(student);
            teacher.setStus(stus);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (session != null) {
                session.getTransaction().rollback();
            }
        } finally {
            HibernateUtil.close(session);
        }
    }
}
