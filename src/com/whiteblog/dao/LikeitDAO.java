package com.whiteblog.dao;

import java.util.List;

import org.hibernate.LockMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.whiteblog.entity.Likeit;

/**
 * A data access object (DAO) providing persistence and search support for
 * Likeit entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.whiteblog.dao.Likeit
 * @author MyEclipse Persistence Tools
 */
public class LikeitDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory.getLogger(LikeitDAO.class);
	// property constants
	public static final String BLOG_ID = "blogId";
	public static final String USER_ID = "userId";

	protected void initDao() {
		// do nothing
	}

	public void save(Likeit transientInstance) {
		log.debug("saving Likeit instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Likeit persistentInstance) {
		log.debug("deleting Likeit instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Likeit findById(java.lang.Integer id) {
		log.debug("getting Likeit instance with id: " + id);
		try {
			Likeit instance = (Likeit) getHibernateTemplate().get(
					"com.whiteblog.dao.Likeit", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Likeit> findByExample(Likeit instance) {
		log.debug("finding Likeit instance by example");
		try {
			List<Likeit> results = (List<Likeit>) getHibernateTemplate()
					.findByExample(instance);
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	@SuppressWarnings("rawtypes")
	public List findByProperty(String propertyName, Object value) {
		log.debug("finding Likeit instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Likeit as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Likeit> findByBlogId(Object blogId) {
		return findByProperty(BLOG_ID, blogId);
	}

	@SuppressWarnings("unchecked")
	public List<Likeit> findByUserId(Object userId) {
		return findByProperty(USER_ID, userId);
	}

	@SuppressWarnings("rawtypes")
	public List findAll() {
		log.debug("finding all Likeit instances");
		try {
			String queryString = "from Likeit";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Likeit merge(Likeit detachedInstance) {
		log.debug("merging Likeit instance");
		try {
			Likeit result = (Likeit) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Likeit instance) {
		log.debug("attaching dirty Likeit instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Likeit instance) {
		log.debug("attaching clean Likeit instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static LikeitDAO getFromApplicationContext(ApplicationContext ctx) {
		return (LikeitDAO) ctx.getBean("LikeitDAO");
	}
}