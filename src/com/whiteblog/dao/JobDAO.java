package com.whiteblog.dao;

import java.util.List;

import org.hibernate.LockMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.whiteblog.entity.Job;

/**
 * A data access object (DAO) providing persistence and search support for Job
 * entities. Transaction control of the save(), update() and delete() operations
 * can directly support Spring container-managed transactions or they can be
 * augmented to handle user-managed Spring transactions. Each of these methods
 * provides additional information for how to configure it for the desired type
 * of transaction control.
 * 
 * @see com.whiteblog.dao.Job
 * @author MyEclipse Persistence Tools
 */
public class JobDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory.getLogger(JobDAO.class);
	// property constants
	public static final String JOBNAME = "jobname";

	protected void initDao() {
		// do nothing
	}

	public void save(Job transientInstance) {
		log.debug("saving Job instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Job persistentInstance) {
		log.debug("deleting Job instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Job findById(java.lang.Integer id) {
		log.debug("getting Job instance with id: " + id);
		try {
			Job instance = (Job) getHibernateTemplate().get(
					"com.whiteblog.dao.Job", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Job> findByExample(Job instance) {
		log.debug("finding Job instance by example");
		try {
			List<Job> results = (List<Job>) getHibernateTemplate()
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
		log.debug("finding Job instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Job as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Job> findByJobname(Object jobname) {
		return findByProperty(JOBNAME, jobname);
	}

	@SuppressWarnings("rawtypes")
	public List findAll() {
		log.debug("finding all Job instances");
		try {
			String queryString = "from Job";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Job merge(Job detachedInstance) {
		log.debug("merging Job instance");
		try {
			Job result = (Job) getHibernateTemplate().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Job instance) {
		log.debug("attaching dirty Job instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Job instance) {
		log.debug("attaching clean Job instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static JobDAO getFromApplicationContext(ApplicationContext ctx) {
		return (JobDAO) ctx.getBean("JobDAO");
	}
}