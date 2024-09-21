package de.mxzx.service;

import de.mxzx.model.Warp;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Getter
public class WarpService implements DataService<Warp> {

    private final HibernateService hibernateService;

    @Override
    public boolean exists(String id) {
        return false;
    }

    @Override
    public List<Warp> loadAll() {
        List<Warp> results = null;
        try {
            Map<Session, Transaction> txMap = getHibernateService().beginTransaction();
            Session session = txMap.keySet().toArray(new Session[0])[0];
            Transaction tx = txMap.get(session);
            List<Warp> warps = (List<Warp>) session.createCriteria(Warp.class).list();
            tx.commit();
            session.close();
            return warps;
        }
        catch (Exception e) {
            System.out.println(e.toString());
        }
        return results != null ? results : new ArrayList<>();
    }

    @Override
    public Warp load(String id) {
        Warp warp = null;
        try {
            Map<Session, Transaction> txMap = getHibernateService().beginTransaction();
            Session session = txMap.keySet().toArray(new Session[0])[0];
            Transaction tx = txMap.get(session);
            warp = session.load(Warp.class, id);
            tx.commit();
            session.close();
        }
        catch (Exception e) {
            System.out.println(e.toString());
        }
        return warp;
    }

    @Override
    public void save(Warp type) {
        try {
            Map<Session, Transaction> txMap = getHibernateService().beginTransaction();
            Session session = txMap.keySet().toArray(new Session[0])[0];
            Transaction tx = txMap.get(session);
            session.save(type);
            tx.commit();
            session.close();
        }
        catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    @Override
    public void update(Warp type) {
        try {
            Map<Session, Transaction> txMap = getHibernateService().beginTransaction();
            Session session = txMap.keySet().toArray(new Session[0])[0];
            Transaction tx = txMap.get(session);
            session.update(type);
            tx.commit();
            session.close();
        }
        catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    @Override
    public void delete(String id) {
        try {
            Map<Session, Transaction> txMap = getHibernateService().beginTransaction();
            Session session = txMap.keySet().toArray(new Session[0])[0];
            Transaction tx = txMap.get(session);
            session.delete(id);
            tx.commit();
            session.close();
        }
        catch (Exception e) {
            System.out.println(e.toString());
        }
    }
}
