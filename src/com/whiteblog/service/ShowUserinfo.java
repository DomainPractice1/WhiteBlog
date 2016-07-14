package com.whiteblog.service;

import java.util.ArrayList;
import java.util.List;

import javassist.bytecode.Descriptor.Iterator;

import com.whiteblog.dao.AttentionDAO;
import com.whiteblog.dao.BlogDAO;
import com.whiteblog.dao.BlogtypeDAO;
import com.whiteblog.dao.CityDAO;
import com.whiteblog.dao.CollectionDAO;
import com.whiteblog.dao.CountryDAO;
import com.whiteblog.dao.HobbyDAO;
import com.whiteblog.dao.JobDAO;
import com.whiteblog.dao.ProvinceDAO;
import com.whiteblog.dao.SupertypeDAO;
import com.whiteblog.dao.UserDAO;
import com.whiteblog.entity.Attention;
import com.whiteblog.entity.Blog;
import com.whiteblog.entity.City;
import com.whiteblog.entity.Collection;
import com.whiteblog.entity.Country;
import com.whiteblog.entity.Hobby;
import com.whiteblog.entity.Job;
import com.whiteblog.entity.Province;
import com.whiteblog.entity.Supertype;
import com.whiteblog.entity.User;
import com.whiteblog.entity.Blogtype;

public class ShowUserinfo {
	private CityDAO cityDAO;
	private ProvinceDAO provinceDAO;
	private JobDAO jobDAO;
	private HobbyDAO hobbyDAO;
	private SupertypeDAO supertypeDAO;
	private UserDAO userDAO;
	private BlogDAO blogDAO;
	private CountryDAO countryDAO;
	private CollectionDAO collectionDAO;
	private AttentionDAO attentionDAO;
	private BlogtypeDAO blogtypeDAO;

	public BlogtypeDAO getBlogtypeDAO() {
		return blogtypeDAO;
	}

	public void setBlogtypeDAO(BlogtypeDAO blogtypeDAO) {
		this.blogtypeDAO = blogtypeDAO;
	}

	public CollectionDAO getCollectionDAO() {
		return collectionDAO;
	}

	public void setCollectionDAO(CollectionDAO collectionDAO) {
		this.collectionDAO = collectionDAO;
	}

	public AttentionDAO getAttentionDAO() {
		return attentionDAO;
	}

	public void setAttentionDAO(AttentionDAO attentionDAO) {
		this.attentionDAO = attentionDAO;
	}

	public CountryDAO getCountryDAO() {
		return countryDAO;
	}

	public void setCountryDAO(CountryDAO countryDAO) {
		this.countryDAO = countryDAO;
	}

	public SupertypeDAO getSupertypeDAO() {
		return supertypeDAO;
	}

	public void setSupertypeDAO(SupertypeDAO supertypeDAO) {
		this.supertypeDAO = supertypeDAO;
	}

	public JobDAO getJobDAO() {
		return jobDAO;
	}

	public void setJobDAO(JobDAO jobDAO) {
		this.jobDAO = jobDAO;
	}

	public HobbyDAO getHobbyDAO() {
		return hobbyDAO;
	}

	public void setHobbyDAO(HobbyDAO hobbyDAO) {
		this.hobbyDAO = hobbyDAO;
	}

	public CityDAO getCityDAO() {
		return cityDAO;
	}

	public void setCityDAO(CityDAO cityDAO) {
		this.cityDAO = cityDAO;
	}

	public ProvinceDAO getProvinceDAO() {
		return provinceDAO;
	}

	public void setProvinceDAO(ProvinceDAO provinceDAO) {
		this.provinceDAO = provinceDAO;
	}

	public BlogDAO getBlogDAO() {
		return blogDAO;
	}

	public void setBlogDAO(BlogDAO blogDAO) {
		this.blogDAO = blogDAO;
	}

	public UserDAO getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	// 原创的文章数量
	public int findtheCreat(User user) {
		System.out.println("来到了原创的service中");
		
		List<Blog> blog = blogDAO.findByUserId(user.getUserId());
		int count = 0;
		if (blog.isEmpty() == true) {
			return 0;
		} else {
			for (int i = 0; i < blog.size(); i++) {
				if (blog.get(i).getStatus() == 0)
					count++;
			}
		}
		System.out.println("原创"+count);
		return count;
	}

	// 转发的文章数量
	public int findtheForward(User user) {
		List<Blog> blog = blogDAO.findByUserId(user.getUserId());
		int count = 0;
		if (blog.isEmpty() == true) {
			return 0;
		} else {
			for (int i = 0; i < blog.size(); i++) {
				if (blog.get(i).getStatus() ==1)
					count++;
			}
		}
		System.out.println("转发"+count);
		return count;
	}

	// 查找用户所在的国家
	public String findcountry(User user) {
		Country country = countryDAO.findById(user.getCountryId());
		return country.getCountryname();
	}

	// 查找用户所在的省份
	public String findprovince(User user) {
		Province province = provinceDAO.findById(user.getProvinceId());
		return province.getProvinceName();
	}

	// 查找用户所在的城市
	public String findcity(User user) {
		City city = cityDAO.findById(user.getCityId());
		return city.getCityname();
	}

	// 查找用户的工作
	public String findjob(User user) {
		Job job = jobDAO.findById(user.getJobId());
		return job.getJobname();
	}

	// 查找用户的喜好
	public List<Supertype> findhobby(User user) {
		List<Hobby> hobby = hobbyDAO.findByUserId(Integer.toString(user
				.getUserId()));
		List<Supertype> list = new ArrayList<Supertype>();
		if (hobby.isEmpty() == false) {
			for (int i = 0; i < hobby.size(); i++) {
				list.add(supertypeDAO.findById(Integer.parseInt((hobby.get(i)
						.getSupertypeId()))));
				System.out.println(i + list.get(i).getSupertypeName());
			}
		}
		return list;
	}

	// 查找用户的收藏
	public List<Blog> findcollection(User user) {
		List<Collection> collection = collectionDAO.findByUserId(user
				.getUserId());
		List<Blog> list = new ArrayList<Blog>();
		if (collection.isEmpty() == false) {
			for (int i = 0; i < collection.size(); i++) {
				list.add(blogDAO.findById((collection.get(i).getBlogId())));
				System.out.println(i + list.get(i).getTitle());
			}
		}
		return list;
	}

	// 查找用户的已关注列表
	public List<User> findActiveAttention(User user) {
		System.out.println("用户关注列表");
		List<Attention> attention = attentionDAO.findByUserId(user.getUserId());
		List<User> list = new ArrayList<User>();
		if (attention.isEmpty() == false) {
			for (int i = 0; i < attention.size(); i++) {
				list.add(userDAO.findById(attention.get(i).getPassivesideId()));
				System.out.println(i + list.get(i).getUsername());
			}
		}
		return list;
	}

	// 查找用户的粉丝列表
	public List<User> findPassiveAttention(User user) {
		System.out.println("用户粉丝列表");
		List<Attention> passiveAttention = attentionDAO
				.findByPassivesideId(user.getUserId());
		List<User> list = new ArrayList<User>();
		if (passiveAttention.isEmpty() == false) {
			for (int i = 0; i < passiveAttention.size(); i++) {
				list.add(userDAO.findById(passiveAttention.get(i).getUserId()));
				System.out.println(i + list.get(i).getUsername());
			}
		}
		return list;
	}

	// 向用户进行相关推荐(注意数组越界还没有解决？？！！）
	public List<Blog> findRelated(User user) {
		System.out.println("用户收藏列表");
		List<Hobby> hobby = hobbyDAO.findByUserId(Integer.toString(user
				.getUserId()));
		List<Blogtype> blogtype = new ArrayList<Blogtype>();
		List<Blog> blog = new ArrayList<Blog>();

		if (hobby.isEmpty() == false) {
			// System.out.println(hobby.get(0).getSupertypeId());
			for (int i = 0; i < hobby.size(); i++) {
				if (blogtypeDAO.findBySupertypeId(
						Integer.parseInt(hobby.get(i).getSupertypeId())).size() > 0) {
					blogtype.addAll(blogtypeDAO.findBySupertypeId(Integer
							.parseInt(hobby.get(i).getSupertypeId())));
					System.out.println(i + blogtype.get(i).getTypename());
				}
			}
		}
		// else
		// System.out.println("no hobby");
		if (blogtype.isEmpty() == false) {
			// System.out.println(blogtype.get(0).getTypeId());
			for (int i = 0; i < blogtype.size(); i++) {
				if (blogDAO.findByTypeId(blogtype.get(i).getTypeId()).size() > 0) {
					blog.addAll(blogDAO.findByTypeId(blogtype.get(i)
							.getTypeId()));
					System.out.println(i + blog.get(i).getTitle());
				}
			}
		}
		// else
		// System.out.println("no such blogtype");
		return blog;
	}

}
