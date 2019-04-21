package com.xt.manage.api.interfaces;


import com.xt.manage.domain.model.ItemCat;

import java.util.List;

/**
 * @Description(描述): 商品类目接口
 * @auther: Jack Lin
 * @param :
 * @return :
 * @date: 2019/4/20 23:39
 */
public interface ItemCatService extends BaseService<ItemCat>{

	/**
	 * 根据页号和页大小分页查询商品类目列表
	 * @param page 页号
	 * @param rows 页大小
	 * @return
	 */
	List<ItemCat> queryItemCatByPage(Integer page, Integer rows);

}
