package com.whiteblog.service;

import antlr.collections.List;

import com.whiteblog.dao.CollectionDAO;

public class DeleteCollectService {
	private CollectionDAO collectionDAO;

	public CollectionDAO getCollectionDAO() {
		return collectionDAO;
	}

	public void setCollectionDAO(CollectionDAO collectionDAO) {
		this.collectionDAO = collectionDAO;
	}
	public void deleteCollect(int blogID){
		collectionDAO.delete(collectionDAO.findByBlogId(blogID).get(0));
	}

}