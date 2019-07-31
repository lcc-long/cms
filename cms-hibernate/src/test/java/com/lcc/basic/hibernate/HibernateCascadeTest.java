package com.lcc.basic.hibernate;

import com.lcc.basic.test.Dept;
import com.lcc.basic.test.Staff;
import com.lcc.basic.test.Student;
import com.lcc.basic.test.Teacher;
import com.lcc.basic.util.HibernateUtil;
import org.hibernate.Session;
import org.junit.Test;

public class HibernateCascadeTest {
    @Test
    public void cascadeTest() {
        Session session = null;
        try {
            session = HibernateUtil.openSession();
            session.beginTransaction();
            Dept dept = new Dept();
            dept.setName("er部门");

            Staff staff = new Staff();
            staff.setName("www");
             //这个就是设置相关联的对象
            staff.setDept(dept);
            //这句话可以有可以没有，具体作用在讲解inverse的时候在说
            dept.getStaffSet().add(staff);

//            Staff staff1 = new Staff();
//            staff1.setName("www1");
//            //这个就是设置相关联的对象
//            staff1.setDept(dept);
//            //这句话可以有可以没有，具体作用在讲解inverse的时候在说
//            dept.getStaffSet().add(staff1);

            session.save(dept);
            //只需要保存staff，就会将dept也一并保存了。
//            session.save(staff);
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
