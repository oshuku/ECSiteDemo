package cn.shukuo.dao;

import java.util.List;

import cn.shukuo.entity.SiteItem;


public interface ItemDao {
	
	public List<SiteItem> getList();

	SiteItem getItemById(long itemCode);

}
