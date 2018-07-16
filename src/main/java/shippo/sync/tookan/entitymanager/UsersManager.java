package shippo.sync.tookan.entitymanager;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import shippo.sync.tookan.entity.v0.Team;
import shippo.sync.tookan.entity.v1.Users;

import java.util.Iterator;
import java.util.List;

public class UsersManager {
    protected SessionFactory sessionFactory;

    public void setup() {
        // code to load Hibernate Session factory
        // final StandardServiceRegistry registry = new
        // StandardServiceRegistryBuilder()
        // .configure() // configures settings from hibernate_rider_service.cfg.xml
        // .build();
        try {
            sessionFactory = new Configuration().configure("shippo_vn_1905.cfg.xml").buildSessionFactory();

        } catch (Throwable ex) {
            ex.printStackTrace();
        }

    }

    public void exit() {
        // code to close Hibernate Session factory
        sessionFactory.close();
    }

    public void readByTookanId(int tookanId) {
        // code to get book list
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();

            List teams = (List) session.createQuery("FROM Team T WHERE T.tookanId = " + tookanId).list();
            for (Iterator iterator = teams.iterator(); iterator.hasNext(); ) {
                Team team = (Team) iterator.next();
                System.out.println("name: " + team.getName());
                System.out.println("description: " + team.getDescription());
            }
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null)
                tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

    }

    public Users getUserById(long id) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        Users user = null;
        try {
            tx = session.beginTransaction();
            user = (Users) session.get(Users.class, id);
            System.out.println(user.getUsername());
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null)
                tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

        return user;
    }

    public static void main(String[] args) {
        // code to run the program
        UsersManager manager = new UsersManager();
        manager.setup();
//        manager.readByTookanId(2);
//        manager.readAll();
        manager.getUserById(52);
        manager.exit();
    }
}
