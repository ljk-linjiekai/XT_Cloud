package com.xt.manage.api.interfaces;


import com.xt.manage.model.ContentCategory;

public interface ContentCategoryService extends BaseService<ContentCategory>{

	/**
	 * 
	 * saveContentCategory:(). 添加内容分类
	 * @author 林捷凯 
	 * @Time：2017年2月16日 下午5:05:44
	 * @param contentCategory
	 * @return
	 */
	ContentCategory saveContentCategory(ContentCategory contentCategory);

	
	/**
	 * 
	 * deleteContentCategory:(). 删除节点以及子孙节点
	 * @author 林捷凯 
	 * @Time：2017年2月16日 下午6:40:32
	 * @param contentCategory
	 */
	void deleteContentCategory(ContentCategory contentCategory);

	
	
}
