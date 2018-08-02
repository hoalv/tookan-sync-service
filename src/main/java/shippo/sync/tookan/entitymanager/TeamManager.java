package shippo.sync.tookan.entitymanager;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import shippo.sync.tookan.entity.v0.Team;

import java.util.Iterator;
import java.util.List;

public class TeamManager {
    protected SessionFactory sessionFactory;

    public void setup() {

        try {
            sessionFactory = new Configuration().configure("hibernate_rider_service.cfg.xml").buildSessionFactory();

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

    public Team getTeamById(long id) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        Team team = null;
        try {
            tx = session.beginTransaction();
            team = (Team) session.get(Team.class, id);
            System.out.println(team.getName());
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

    public static void main(String[] args) {
        // code to run the program
        TeamManager manager = new TeamManager();
        manager.setup();
        manager.getTeamById(Long.parseLong("109"));
        manager.exit();
    }
}
