/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;

import com.model.UsersData;
import com.util.NewHibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author josemiguel
 */
public class UserDAO {

    public void addUser(UsersData user) {
        Transaction tx = null;
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        try {
            tx = session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            session.flush();
            session.close();
        }
    }

    public void deleteUser(int userID) {
        Transaction transaction = null;
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        
        try {

            String queryString = "FROM UsersData WHERE id = :idToFind";
            UsersData user = (UsersData) session.load(UsersData.class, new Integer(userID));
            session.delete(user);
            session.getTransaction().commit();
            
        } catch (Exception e) {
            
            e.printStackTrace();
            
            if (transaction != null) {
                transaction.rollback();
            }

        } finally {

            session.flush();
            session.close();

        }
        
    }

    public void updateUser(int userID, UsersData newUser) {
        Transaction transaction = null;
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        
        try {
            transaction = session.beginTransaction();

            UsersData oldInfo = (UsersData) session.load(UsersData.class, new Integer(userID));
            
            oldInfo.setFirstName    (newUser.getFirstName()    );
            oldInfo.setLastName     (newUser.getLastName()     );
            oldInfo.setEmail        (newUser.getEmail()        );
            oldInfo.setPhoneNumber  (newUser.getPhoneNumber()  );
            oldInfo.setGender       (newUser.getGender()       );
            
            session.update(oldInfo);
            session.getTransaction().commit();
            
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            session.flush();
            session.close();
        }
                
        
    }

    public UsersData getUserByID(int userID) {
        UsersData user = null;
        Transaction transaction = null;
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        
        try {

            String queryString = "FROM UsersData WHERE id = :idToFind";

            transaction         = session.beginTransaction();
            Query mysqlQuery    = session.createQuery(queryString);
            
            mysqlQuery.setInteger("idToFind", userID);
            user = (UsersData) mysqlQuery.uniqueResult();
            
        } catch (Exception e) {

            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }

        } finally {

            session.flush();
            session.close();

        }
        return user;
    }

}
