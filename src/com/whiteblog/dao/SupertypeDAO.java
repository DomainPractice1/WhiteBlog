package com.whiteblog.dao;

import java.util.List;

import org.hibernate.LockMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.whiteblog.entity.Supertype;

/**
 * A data access object (DAO) providing persistence and search support for
 * Supertype entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.whiteblog.entity.Supertype
 * @author MyEclipse Persistence Tools
 */
public class SupertypeDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory
			.getLogger(SupertypeDAO.class);
	// property constants
	public static final String SUPERTYPE_NAME = "supertypeName";

	protected void initDao() {
		// do nothing
	}

	public void save(Supertype transientInstance) {
		log.debug("saving Supertype instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Supertype persistentInstance) {
		log.debug("deleting Supertype instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Supertype findById(java.lang.Integer id) {
		log.debug("getting Supertype instance with id: " + id);
		try {
			Supertype instance = (Supertype) getHibernateTemplate().get(
					"com.whiteblog.entity.Supertype", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Supertype> findByExample(Supertype instance) {
		log.debug("finding Supertype instance by example");
		try {
			List<Supertype> results = (List<Supertype>) getHibernateTemplate()
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
		log.debug("finding Supertype instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Supertype as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<Supertype> findBySupertypeName(Object supertypeName) {
		return findByProperty(SUPERTYPE_NAME, supertypeName);
	}

	public List findAll() {
		log.debug("finding all Supertype instances");
		try {
			String queryString = "from Supertype";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Supertype merge(Supertype detachedInstance) {
		log.debug("merging Supertype instance");
		try {
			Supertype result = (Supertype) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Supertype instance) {
		log.debug("attaching dirty Supertype instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Supertype instance) {
		log.debug("attaching clean Supertype instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static SupertypeDAO getFromApplicationContext(ApplicationContext ctx) {
		return (SupertypeDAO) ctx.getBean("SupertypeDAO");
	}
}