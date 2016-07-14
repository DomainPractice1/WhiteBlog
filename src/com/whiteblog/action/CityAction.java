package com.whiteblog.action;

import java.util.ArrayList;
import java.util.List;

import com.whiteblog.dao.CityDAO;
import com.whiteblog.entity.City;

public class CityAction {
	private List<City> cl = new ArrayList<City>();
	private int i;
	private CityDAO cityDAO;
	
	public CityDAO getCityDAO() {
		return cityDAO;
	}
	public void setCityDAO(CityDAO cityDAO) {
		this.cityDAO = cityDAO;
	}
	public List<City> getCl() {
		return cl;
	} 
	public void setCl(List<City> cl) {
		this.cl = cl;
	} 
	public int getI() {
		return i;
	} 
	public void setI(int i) {
		this.i = i;
	} 
	/*载入时显示city*/
	public String onloadCity(){
		@SuppressWarnings("unchecked")
		List<City> l = cityDAO.findAll();
		cl.clear();
		if(i == 1){
			for(int s = 0; s < 18; s++)
				this.cl.add(l.get(s));
		}else{
			for(int s = 19; s < l.size(); s++)
				this.cl.add(l.get(s));
		}
		return "success";
	}
	
}
