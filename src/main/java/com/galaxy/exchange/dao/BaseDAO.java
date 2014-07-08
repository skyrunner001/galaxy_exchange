package com.galaxy.exchange.dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

/**
 * DAO基类，提供根据hql分页查询的方法。
 *
 */
@Component
public class BaseDAO {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	private static final Log log = LogFactory.getLog(BaseDAO.class);	
	
	
	public <T> T delete(T persistentInstance) {
		if (log.isDebugEnabled())
			log.debug("deleting " + persistentInstance.getClass().getName() + " instance");
		entityManager.remove(persistentInstance);
		log.debug("delete successful");
		return persistentInstance;
	}	
	
	
	public int executeSql(String sql,Map<String, Object>params) {
		javax.persistence.Query query = entityManager.createNativeQuery(sql);
		if (params != null) {
        	Iterator<?> it = params.keySet().iterator();
        	while (it.hasNext()) {
        		String key = (String)it.next();	
        		Object value = params.get(key);        						        		
        		query.setParameter(key, value);        		
        	}
        }
		return query.executeUpdate();
	}
	
	public List<?> querySql(String sql,Map<String, Object>params) {
		javax.persistence.Query query = entityManager.createNativeQuery(sql);
		if (params != null) {
        	Iterator<?> it = params.keySet().iterator();
        	while (it.hasNext()) {
        		String key = (String)it.next();	
        		Object value = params.get(key);        						        		
        		query.setParameter(key, value);        		
        	}
        }
		return query.getResultList();
	}
	
	public <T> T save(T transientInstance) {
		if (log.isDebugEnabled()) {
			log.debug("saving " + transientInstance.getClass().getName() + " instance");
		}
		entityManager.persist(transientInstance);
		entityManager.flush();
		log.debug("save successful");
		return transientInstance;
	}	
	
	public <T> T update(T transientInstance) {
		if (log.isDebugEnabled()) {
			log.debug("update " + transientInstance.getClass().getName() + " instance");
		}

		entityManager.merge(transientInstance);
		log.debug("update successful");
		return transientInstance;
	}
	
	public void detach(Object entity) {
		entityManager.detach(entity);
	}
	
	
	
	public void clear() {
		log.info("entityManager.clear()");
		entityManager.clear();
	}
	
	public void flush() {
		entityManager.flush();
	}
	
	public List<?> queryListByHqlMapparams(final String hql,final Map<String, Object> params) {
		Session session = (Session)entityManager.getDelegate();
		Query query = session.createQuery(hql);

        if (params != null) {
        	Iterator<?> it = params.keySet().iterator();
        	while (it.hasNext()) {
        		String key = (String)it.next();	
        		Object value = params.get(key);
        		if (value instanceof Collection) {
        			query.setParameterList(key, (Collection<?>)value);
        		}else if (value instanceof Object[]) {
        			query.setParameterList(key, (Object[])value);
        		}else {				        		
        			query.setParameter(key, value);
        		}
        		
        	}
        }
      
        log.debug("query hql: " + hql);
        List<?> list = query.list();
        return list;
	}
	
	/**
	 * 根据ID查询
	 * @param entityClass
	 * @param id
	 * @return
	 */
	public <T> T find(Class<T> entityClass, Serializable id) {
		return (T)entityManager.find(entityClass, id);
	}
	
	/**
	 * 根据ID删除
	 * @param entityClass
	 * @param id
	 */
	public <T> void deleteById(Class<T> entityClass, Serializable id) {
		delete(find(entityClass, id));
	}
	
	/**
	 * 分页查询
	 * @param sql
	 * @param params
	 * @return
	 */
	public List<?> queryPageSql(String sql,int start,int pageSize,int startDiviation,Map<String, Object>params) {
		javax.persistence.Query query = entityManager.createNativeQuery(sql);
		query.setFirstResult(start+startDiviation);
		query.setMaxResults(pageSize);
		if (params != null) {
        	Iterator<?> it = params.keySet().iterator();
        	while (it.hasNext()) {
        		String key = (String)it.next();	
        		Object value = params.get(key);        						        		
        		query.setParameter(key, value);        		
        	}
        }
		return query.getResultList();
	}
	
}
