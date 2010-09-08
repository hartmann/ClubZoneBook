package com.bekk.wa.webframework.database.service;

import com.bekk.wa.webframework.database.dao.BaseDao;

public abstract class BaseService {

	private BaseDao dao;
	
	public BaseService(BaseDao dao) {
		this.dao = dao;
	}

	public BaseDao getDao() {
		return dao;
	}

	public void setDao(BaseDao dao) {
		this.dao = dao;
	}
	
}
