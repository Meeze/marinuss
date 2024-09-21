package de.mxzx.service;


import de.mxzx.model.*;
import lombok.Getter;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import java.io.IOException;
import java.io.InputStream;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;


@Getter
public class HibernateService {
    public HibernateService() {
        try {
            this.setUp();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        /**
        try {
            Map<Session, Transaction> txMap = beginTransaction();
            Session session = txMap.keySet().toArray(new Session[0])[0];
            Transaction tx = txMap.get(session);
            tx.commit();
            session.close();
        }
        catch (Exception e) {
            System.out.println(e.toString());
        }
         **/
    }

    private SessionFactory sessionFactory;

    public Map<Session, Transaction> beginTransaction() {
        Map<Session, Transaction> sessionTransaction = new HashMap<>();
        Session session = getSession();
        Transaction tx = session.beginTransaction();
        sessionTransaction.put(session, tx);
        return sessionTransaction;
    }

    public Session getSession() {
        return getSessionFactory().openSession();
    }
    public void setUp() throws UnknownHostException {
        final SessionFactory sessionFactory;
        Properties dbCreds = new Properties();
        InputStream is;
        is = getClass().getClassLoader().getResourceAsStream("db.properties");
        try {
            dbCreds.load(is);
        } catch (IOException e) {
            System.out.println("FILE db.properties not found");
            throw new RuntimeException(e);
        }
        Configuration config = new Configuration();
        config.setProperty("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
        config.setProperty("hibernate.dialect", "org.hibernate.dialect.MariaDBDialect");
        config.setProperty("hibernate.connection.url", dbCreds.getProperty("url"));
        config.setProperty("hibernate.connection.username", dbCreds.getProperty("user"));
        config.setProperty("hibernate.connection.password", dbCreds.getProperty("pw"));
        config.setProperty("hibernate.hbm2ddl.auto", dbCreds.getProperty("ddl"));
        config.setProperty("hibernate.enable_lazy_load_no_trans", "true");
        config.addAnnotatedClass(PlayerData.class);
        config.addAnnotatedClass(Season.class);
       // config.addAnnotatedClass(Milestone.class);
        config.addAnnotatedClass(Home.class);
        config.addAnnotatedClass(Mine.class);
        config.addAnnotatedClass(AMS.class);
        config.addAnnotatedClass(Punishment.class);
        config.addAnnotatedClass(Clan.class);
        config.addAnnotatedClass(Warp.class);

        final StandardServiceRegistry registry =
                new StandardServiceRegistryBuilder()
                        .build();
        try {
            sessionFactory = config.buildSessionFactory();
            this.sessionFactory = sessionFactory;
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println(e.getCause());
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }

}
