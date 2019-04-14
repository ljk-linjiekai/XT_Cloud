package com.xt.manage.api.interfaces;


import com.xt.manage.model.ItemCat;

import java.util.List;

public interface ItemCatService extends BaseService<ItemCat>{

	/**
	 * 根据页号和页大小分页查询商品类目列表
	 * @param page 页号
	 * @param rows 页大小
	 * @return
	 */
	List<ItemCat> queryItemCatByPage(Integer page, Integer rows);

}
