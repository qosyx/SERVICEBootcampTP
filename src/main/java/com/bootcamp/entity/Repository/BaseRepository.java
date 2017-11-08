/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bootcamp.entity.Repository;


import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author Archange
 */

public abstract class BaseRepository<T> {

    private EntityManager em;
    private String persistUnit;
    private Class entityClass;
    private EntityManagerFactory emf;

    
 

    public BaseRepository(String persistUnit, Class entityClass) {
        
        this.persistUnit = persistUnit;
        emf=Persistence.createEntityManagerFactory(persistUnit);
        this.em=getEntityManager();
        this.entityClass=entityClass;

    }
    
     public final EntityManager getEntityManager() {
        em= emf.createEntityManager();
        return em;
        
    }

  /**
   * methode genetique de creation dans la bdd
   * @param obj
   * @return
   * @throws SQLException 
   */
    public boolean create(T obj) throws SQLException {
        em.getTransaction().begin();
        em.persist(obj);
        em.getTransaction().commit();
       
        return true;
    }

    /**
     * M�thode pour supprimer une entite dans la bdd
     *
     * @param obj
     * @return boolean
     * @throws java.sql.SQLException
     */
    public boolean delete(T obj) throws SQLException {
        em.getTransaction().begin();
        em.remove(obj);
        em.getTransaction().commit();
        
        return true;
    }

    /**
     * M�thode de mise � jour
     *
     * @param obj
     * @return boolean
     * @throws java.sql.SQLException
     */
    public boolean update(T obj) throws SQLException {
        em.getTransaction().begin();
        em.merge(obj);
        em.getTransaction().commit();
       
        return true;
    }
    
    //a revoir
    public T updateById(int id, Object value) throws SQLException {
        
          String className = entityClass.getSimpleName();
         
        String request = "select t from " + className + " t where t." + id + "=:param";
        Query query = getEm().createQuery(request);
        query.setParameter("param", value);
        return (T) query.getSingleResult();

    }

    /**
     * M�thode de recherche des informations
     *
     * @param propertyName
     * @param value
     * @return T
     * @throws java.sql.SQLException
     */
    public List<T> findByProperty(String propertyName, Object value) throws SQLException {
        String className = entityClass.getSimpleName();
        String query = "select t from " + className + " t where t." + propertyName + "=:param";
        Query qry = getEntityManager().createQuery(query);
        qry.setParameter("param", value);
        return qry.getResultList();

    }
    /**
     * 
     * @param propertyName
     * @param value
     * @return
     * @throws SQLException 
     */
      public T findByPropertyUnique(String propertyName, Object value) throws SQLException {
        
          String className = entityClass.getSimpleName();
         
        String request = "select t from " + className + " t where t." + propertyName + "=:param";
        Query query = getEm().createQuery(request);
        query.setParameter("param", value);
        return (T) query.getSingleResult();

    }
    
     /*
	  * M�thode de recherche de tous les objets T
     */
    public  List<T> findAll() throws SQLException{
       String className = entityClass.getSimpleName();
       String req="select t from " + className + " t";
       Query query=getEm().createQuery(req);
        return query.getResultList();
    }
    


    /**
     * 
     * @return the entity manager
     */
    public EntityManager getEm() {
        return em;
    }

    public Class getEntityClass() {
        return entityClass;
    }

    public void setEntityClass(Class entityClass) {
        this.entityClass = entityClass;
    }

    public EntityManagerFactory getEmf() {
        return emf;
    }

    public void setEmf(EntityManagerFactory emf) {
        this.emf = emf;
    }

    /**
     * 
     * @param em to set
     */
    public void setEm(EntityManager em) {
        this.em = em;
    }

    /**
     * 
     * @return the persistence unit
     */
    public String getPersistUnit() {
        return persistUnit;
    }

    /**
     * 
     * @param persistUnit 
     */
    public void setPersistUnit(String persistUnit) {
        this.persistUnit = persistUnit;
    }



}
