package com.whiteblog.dao;

import java.util.List;

import org.hibernate.LockMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.whiteblog.entity.Collection;

/**
 * A data access object (DAO) providing persistence and search support for
 * Collection entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.whiteblog.dao.Collection
 * @author MyEclipse Persistence Tools
 */
public class CollectionDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory
			.getLogger(CollectionDAO.class);
	// property constants
	public static final String USER_ID = "userId";
	public static final String BLOG_ID = "blogId";

	protected void initDao() {
		// do nothing
	}

	public void save(Collection transientInstance) {
		log.debug("saving Collection instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Collection persistentInstance) {
		log.debug("deleting Collection instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Collection findById(java.lang.Integer id) {
		log.debug("getting Collection instance with id: " + id);
		try {
			Collection instance = (Collection) getHibernateTemplate().get(
					"com.whiteblog.entity.Collection", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Collection> findByExample(Collection instance) {
		log.debug("finding Collection instance by example");
		try {
			List<Collection> results = (List<Collection>) getHibernateTemplate()
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
		log.debug("finding Collection instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Collection as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<Collection> findByUserId(Object userId) {
		return findByProperty(USER_ID, userId);
	}

	public List<Collection> findByBlogId(Object blogId) {
		return findByProperty(BLOG_ID, blogId);
	}

	public List findAll() {
		log.debug("finding all Collection instances");
		try {
			String queryString = "from Collection";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Collection merge(Collection detachedInstance) {
		log.debug("merging Collection instance");
		try {
			Collection result = (Collection) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Collection instance) {
		log.debug("attaching dirty Collection instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Collection instance) {
		log.debug("attaching clean Collection instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static CollectionDAO getFromApplicationContext(ApplicationContext ctx) {
		return (CollectionDAO) ctx.getBean("CollectionDAO");
	}
}