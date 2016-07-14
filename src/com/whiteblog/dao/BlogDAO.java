package com.whiteblog.dao;

import java.util.List;

import org.hibernate.LockMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.whiteblog.entity.Blog;
import com.whiteblog.entity.User;

/**
 * A data access object (DAO) providing persistence and search support for Blog
 * entities. Transaction control of the save(), update() and delete() operations
 * can directly support Spring container-managed transactions or they can be
 * augmented to handle user-managed Spring transactions. Each of these methods
 * provides additional information for how to configure it for the desired type
 * of transaction control.
 * 
 * @see com.whiteblog.entity.Blog
 * @author MyEclipse Persistence Tools
 */
public class BlogDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory.getLogger(BlogDAO.class);
	// property constants
	public static final String USER_ID = "userId";
	public static final String TYPE_ID = "typeId";
	public static final String TITLE = "title";
	public static final String CONTENT = "content";
	public static final String TIME = "time";
	public static final String USERNAME = "username";
	public static final String FILTERWORDS = "filterwords";
	public static final String LIKENUMBER = "likenumber";
	public static final String FORWARDNUMBER = "forwardnumber";
	public static final String VIEWNUMBER = "viewnumber";
	public static final String COMMENTNUMBER = "commentnumber";
	public static final String STATUS = "status";

	protected void initDao() {
		// do nothing
	}

	public void save(Blog transientInstance) {
		log.debug("saving Blog instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public String delete(Blog persistentInstance) {
		log.debug("deleting Blog instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
			return "success";
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			return "error";
			//throw re;
		}
	}

	public Blog findById(java.lang.Integer id) {
		log.debug("getting Blog instance with id: " + id);
		try {
			Blog instance = (Blog) getHibernateTemplate().get(
					"com.whiteblog.entity.Blog", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Blog> findByExample(Blog instance) {
		log.debug("finding Blog instance by example");
		try {
			List<Blog> results = (List<Blog>) getHibernateTemplate()
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
		log.debug("finding Blog instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Blog as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByPartMatch(String propertyName, Object value){
		log.debug("finding Blog instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Blog as model where model."
					+ propertyName + " like ?";
			return getHibernateTemplate().find(queryString, "%"+value+"%");
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<Blog> findByPartTitle(Object title) {
		return findByPartMatch(TITLE, title);
	}

	public List<Blog> findByPartContent(Object content) {
		return findByPartMatch(CONTENT, content);
	}

	public List<Blog> findByPartUsername(Object username) {
		return findByPartMatch(USERNAME, username);
	}
	
	public List<Blog> findByUserId(Object userId) {
		return findByProperty(USER_ID, userId);
	}

	public List<Blog> findByTypeId(Object typeId) {
		return findByProperty(TYPE_ID, typeId);
	}

	public List<Blog> findByTitle(Object title) {
		return findByProperty(TITLE, title);
	}

	public List<Blog> findByContent(Object content) {
		return findByProperty(CONTENT, content);
	}

	public List<Blog> findByTime(Object time) {
		return findByProperty(TIME, time);
	}

	public List<Blog> findByUsername(Object username) {
		return findByProperty(USERNAME, username);
	}

	public List<Blog> findByFilterwords(Object filterwords) {
		return findByProperty(FILTERWORDS, filterwords);
	}

	public List<Blog> findByLikenumber(Object likenumber) {
		return findByProperty(LIKENUMBER, likenumber);
	}

	public List<Blog> findByForwardnumber(Object forwardnumber) {
		return findByProperty(FORWARDNUMBER, forwardnumber);
	}

	public List<Blog> findByViewnumber(Object viewnumber) {
		return findByProperty(VIEWNUMBER, viewnumber);
	}

	public List<Blog> findByCommentnumber(Object commentnumber) {
		return findByProperty(COMMENTNUMBER, commentnumber);
	}

	public List<Blog> findByStatus(Object status) {
		return findByProperty(STATUS, status);
	}

	public List<Blog> findAll() {
		log.debug("finding all Blog instances");
		try {
			String queryString = "from Blog";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Blog merge(Blog detachedInstance) {
		log.debug("merging Blog instance");
		try {
			Blog result = (Blog) getHibernateTemplate().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Blog instance) {
		log.debug("attaching dirty Blog instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Blog instance) {
		log.debug("attaching clean Blog instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static BlogDAO getFromApplicationContext(ApplicationContext ctx) {
		return (BlogDAO) ctx.getBean("BlogDAO");
	}
	public void updateView(Blog instance){
		log.debug("find forward works");
		try {
			getHibernateTemplate().update(instance);;
		} catch (RuntimeException re) {
			log.error("failed", re);
			throw re;
		}
	}
	//查找原创的文章数
	@SuppressWarnings("unused")
	public int findCreat(String username) {
		log.debug("find original works");
		try {
			 List<User> user=getHibernateTemplate().find("from User as model where model.username=? ", username);
			int userID= user.get(0).getUserId();
		    int Status=0;
			List<Blog> blog=getHibernateTemplate().find("from Blog as b where b.userId=? and b.status= ? ",new Integer[]{userID,Status});
			return blog.size();
		} catch (RuntimeException re) {
			log.error("failed", re);
			throw re;
		}
		
	}
	//查找转发的文章数
		@SuppressWarnings("unused")
		public int findForward(String username) {
			log.debug("find forward works");
			try {
				 List<User> user=getHibernateTemplate().find("from User as model where model.username=?", username);
				int userID= user.get(0).getUserId();
				List<Blog> blog=getHibernateTemplate().find("from Blog as b where b.userId=? ", userID);
				if(blog.isEmpty()==true)
					return 0;
				else
				    return blog.get(0).getForwardnumber();
			} catch (RuntimeException re) {
				log.error("failed", re);
				throw re;
			}
			
		}
}