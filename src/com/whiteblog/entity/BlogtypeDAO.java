package com.whiteblog.entity;

import java.util.List;

import org.hibernate.LockMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.whiteblog.entity.Blogtype;

/**
 * A data access object (DAO) providing persistence and search support for
 * Blogtype entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.whiteblog.entity.Blogtype
 * @author MyEclipse Persistence Tools
 */
public class BlogtypeDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory
			.getLogger(BlogtypeDAO.class);
	// property constants
	public static final String TYPENAME = "typename";
	public static final String USER_ID = "userId";
	public static final String SUPER_TYPENAME = "superTypename";

	protected void initDao() {
		// do nothing
	}

	public void save(Blogtype transientInstance) {
		log.debug("saving Blogtype instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Blogtype persistentInstance) {
		log.debug("deleting Blogtype instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Blogtype findById(java.lang.Integer id) {
		log.debug("getting Blogtype instance with id: " + id);
		try {
			Blogtype instance = (Blogtype) getHibernateTemplate().get(
					"com.whiteblog.entity.Blogtype", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Blogtype> findByExample(Blogtype instance) {
		log.debug("finding Blogtype instance by example");
		try {
			List<Blogtype> results = (List<Blogtype>) getHibernateTemplate()
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
		log.debug("finding Blogtype instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Blogtype as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<Blogtype> findByTypename(Object typename) {
		return findByProperty(TYPENAME, typename);
	}

	public List<Blogtype> findByUserId(Object userId) {
		return findByProperty(USER_ID, userId);
	}

	public List<Blogtype> findBySuperTypename(Object superTypename) {
		return findByProperty(SUPER_TYPENAME, superTypename);
	}

	public List findAll() {
		log.debug("finding all Blogtype instances");
		try {
			String queryString = "from Blogtype";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Blogtype merge(Blogtype detachedInstance) {
		log.debug("merging Blogtype instance");
		try {
			Blogtype result = (Blogtype) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Blogtype instance) {
		log.debug("attaching dirty Blogtype instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Blogtype instance) {
		log.debug("attaching clean Blogtype instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static BlogtypeDAO getFromApplicationContext(ApplicationContext ctx) {
		return (BlogtypeDAO) ctx.getBean("BlogtypeDAO");
	}
}