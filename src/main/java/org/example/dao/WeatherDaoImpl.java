package org.example.dao;

import org.example.configuration.HibernateConfiguration;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import javax.persistence.criteria.CriteriaQuery;
import java.util.List;
import java.util.Map;

public class WeatherDaoImpl implements WeatherDao{
    private static Session session = HibernateConfiguration.getSessionFactory().openSession();

    public <T> void insert(T object) {
        try {
            session.beginTransaction();
            session.saveOrUpdate(object);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public <T> T retrievebyId(Class<T> clazz, int id) {
        T o = null;
        try {
            o = (T) session.get(clazz, id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return o;
    }

    public <T> List<T> retrieveByQuery(Class<T> clazz, int id, Map<String, Object> criterias) {
        Criteria criteria = buildCriteria(clazz, criterias);
        criteria.add(Restrictions.eq("userId", id));
        return criteria.list();
    }

    public <T> List<T> retrieveByField(Class<T> clazz, Map<String, Object> criterias) {
        Criteria criteria = buildCriteria(clazz, criterias);
        return criteria.list();
    }


    private static <T> Criteria buildCriteria(Class<T> clazz, Map<String, Object> criterias)  {
        Criteria criteria = session.createCriteria(clazz);
        for(Map.Entry<String, Object> entry : criterias.entrySet()) {
            criteria.add(Restrictions.eq(entry.getKey(), entry.getValue()));
        }
        return criteria;
    }

    public <T> List<T> retrieveAll(Class<T> clazz) {
        List<T> results = null;
        try {
            CriteriaQuery<T> criterias = session.getCriteriaBuilder().createQuery(clazz);
            criterias.from(clazz);
            results = session.createQuery(criterias).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return results;
    }

    public <T> void delete(Class<T> clazz, int id) {
//        try {
//            session.beginTransaction();
//            session.delete(session.load(clazz, id));
//            session.getTransaction().commit();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }
}
