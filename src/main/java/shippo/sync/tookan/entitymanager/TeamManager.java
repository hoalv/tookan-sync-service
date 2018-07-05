package shippo.sync.tookan.entitymanager;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import shippo.sync.tookan.entity.RiderTookanAgent;
import shippo.sync.tookan.entity.Team;

import java.util.Iterator;
import java.util.List;

public class TeamManager {
    protected   SessionFactory sessionFactory;

    public void setup() {
        // code to load Hibernate Session factory
        // final StandardServiceRegistry registry = new
        // StandardServiceRegistryBuilder()
        // .configure() // configures settings from hibernate.cfg.xml
        // .build();
        try {
            sessionFactory = new Configuration().configure().buildSessionFactory();

        } catch (Throwable ex) {
            ex.printStackTrace();
        }

    }

    public void exit() {
        // code to close Hibernate Session factory
        sessionFactory.close();
    }



    public void readAll() {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();

            List tasks = (List) session.createQuery("FROM Team").list();
            for (Iterator iterator = tasks.iterator(); iterator.hasNext();) {
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

    public void readByTookanId(int tookanId) {
        // code to get book list
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();

            List tasks = (List) session.createQuery("FROM Team T WHERE T.tookanId = " + tookanId).list();
            for (Iterator iterator = tasks.iterator(); iterator.hasNext();) {
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

    public Team getTeamById(long id) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        Team team = null;
        try {
            tx = session.beginTransaction();
            team = (Team) session.get(Team.class, id);

//            System.out.println("name: " + team.getName());
//            System.out.println("description: " + team.getDescription());
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null)
                tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

        return team;
    }

    public void delete(long taskId) {
        // code to remove a task
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Team task = (Team) session.get(Team.class, taskId);
            session.delete(task);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null)
                tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

    }
    public static void main(String[] args) {
        // code to run the program
        TeamManager manager = new TeamManager();
        manager.setup();
//        manager.readByTookanId(2);
//        manager.readAll();
//        manager.readById(Long.parseLong("46"));
        manager.exit();
    }
}
