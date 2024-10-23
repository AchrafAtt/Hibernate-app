package org.example.service;


import org.example.dao.IDao;
import org.example.entities.Salle;
import org.example.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;


public class SalleService implements IDao<Salle> {

    @Override
    public boolean create(Salle s) {
        Session session = null;
        Transaction tx = null;
        boolean etat = false;
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            session.save(s);
            tx.commit();
            etat = true;
        }catch (HibernateException e){
            if(tx != null) tx.rollback();
            e.printStackTrace();
        }finally {
            if(session != null) session.close();
        }
        return etat;
    }

    @Override
    public boolean delete(Salle s) {
        Session session = null;
        Transaction tx = null;
        boolean etat = false;
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            session.delete(s);
            tx.commit();
            etat = true;
        }catch (HibernateException e){
            if(tx != null) tx.rollback();
            e.printStackTrace();
        }finally {
            if(session != null) session.close();
        }
        return etat;
    }

    @Override
    public boolean update(Salle s) {
        Session session = null;
        Transaction tx = null;
        boolean etat = false;
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            session.update(s);
            tx.commit();
            etat = true;
        }catch (HibernateException e){
            if(tx != null) tx.rollback();
            e.printStackTrace();
        }finally {
            if(session != null) session.close();
        }
        return etat;
    }
    @Override
    public Salle findById(int id) {
        Session session = null;
        Salle s = null;
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            s = session.get(Salle.class, id);
        }catch (HibernateException e){
            e.printStackTrace();
        }finally {
            if(session != null) session.close();
        }
        return s;
    }

    @Override
    public java.util.List<Salle> findAll() {
        Session session = null;
        java.util.List<Salle> salles = null;
        try{
            session = HibernateUtil.getSessionFactory().openSession();
            salles = session.createQuery("from Salle").list();
        }catch (HibernateException e){
            e.printStackTrace();
        }finally {
            if(session != null) session.close();
        }
        return salles;
    }
}
