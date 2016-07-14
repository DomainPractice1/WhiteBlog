package com.whiteblog.service;

import java.util.List;

import com.whiteblog.dao.CollectionDAO;
import com.whiteblog.entity.Collection;
import com.whiteblog.entity.Hobby;
import com.whiteblog.entity.User;

public class CollectService {
	private CollectionDAO collectionDAO;
	private static int  collectionCount=10;

	public CollectionDAO getCollectionDAO() {
		return collectionDAO;
	}

	public void setCollectionDAO(CollectionDAO collectionDAO) {
		this.collectionDAO = collectionDAO;
	}
    public void collect (User user,int blogID ){
    	//List<Collection> collection
    	//System.out.println(blogID);
        Collection collection=new Collection();
    	collection.setBlogId(blogID);
    	collection.setUserId(user.getUserId());
    	collection.setCollectionId(collectionCount++);;
    	collectionDAO.save(collection);
    	System.out.println("收藏当前blog"+collection.getCollectionId());
    }

}
