package com.xt.manage.api.interfaces;


import com.xintu.common.vo.DataGridResult;
import com.xt.manage.model.Item;

public interface ItemService extends BaseService<Item>{

	/**
	 * 
	 * saveItem:(). 保存商品
	 * @author 林捷凯 
	 * @Time：2017年2月15日 上午10:06:53
	 * @param item 商品信息
	 * @param desc 商品描述
	 */
	void saveItem(Item item, String desc);
	
	
	/**
	 * 
	 * updateItem:(). 更新商品
	 * @author 林捷凯 
	 * @Time：2017年2月15日 上午10:07:11
	 * @param item 商品信息
	 * @param desc 商品描述
	 */
	void updateItem(Item item, String desc);
	
	/**
	 * 
	 * queryItemList:(). 查询商品列表
	 * @author 林捷凯 
	 * @Time：2017年2月15日 上午10:07:43
	 * @param title 列表搜索条件
	 * @param page  当前页
	 * @param rows  页面大小
	 * @return
	 */
	DataGridResult queryItemList(String title, Integer page, Integer rows);
	
	
	/**
	 * 
	 * updateItemStatuByIds:(). 修改商品状态：上架、下架、删除
	 * @author 林捷凯 
	 * @Time：2017年2月15日 上午10:30:24
	 * @param ids 商品id
	 * @param statu 状态码
	 */
	void updateItemStatuByIds(Long[] ids, Integer statu);
	
	
	
}
