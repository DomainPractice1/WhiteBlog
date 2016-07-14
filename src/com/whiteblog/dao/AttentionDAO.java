package com.whiteblog.dao;

import java.util.List;

import org.hibernate.LockMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.whiteblog.entity.Attention;

/**
 * A data access object (DAO) providing persistence and search support for
 * Attention entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.whiteblog.dao.Attention
 * @author MyEclipse Persistence Tools
 */
public class AttentionDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory
			.getLogger(AttentionDAO.class);
	// property constants
	public static final String USER_ID = "userId";
	public static final String PASSIVESIDE_ID = "passivesideId";

	protected void initDao() {
		// do nothing
	}

	public void save(Attention transientInstance) {
		log.debug("saving Attention instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Attention persistentInstance) {
		log.debug("deleting Attention instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Attention findById(java.lang.Integer id) {
		log.debug("getting Attention instance with id: " + id);
		try {
			Attention instance = (Attention) getHibernateTemplate().get(
					"com.whiteblog.entity.Attention", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Attention> findByExample(Attention instance) {
		log.debug("finding Attention instance by example");
		try {
			List<Attention> results = (List<Attention>) getHibernateTemplate()
					.findByExample(instance);
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding Attention instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Attention as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<Attention> findByUserId(Object userId) {
		return findByProperty(USER_ID, userId);
	}

	public List<Attention> findByPassivesideId(Object passivesideId) {
		return findByProperty(PASSIVESIDE_ID, passivesideId);
	}

	public List findAll() {
		log.debug("finding all Attention instances");
		try {
			String queryString = "from Attention";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Attention merge(Attention detachedInstance) {
		log.debug("merging Attention instance");
		try {
			Attention result = (Attention) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Attention instance) {
		log.debug("attaching dirty Attention instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Attention instance) {
		log.debug("attaching clean Attention instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static AttentionDAO getFromApplicationContext(ApplicationContext ctx) {
		return (AttentionDAO) ctx.getBean("AttentionDAO");
	}
}