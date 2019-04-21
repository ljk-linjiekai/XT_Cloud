package com.xt.manage.api.interfaces;


import com.xt.manage.domain.model.ContentCategory;

/**
 * @Description(描述): 内容接口
 * @auther: Jack Lin
 * @param :
 * @return :
 * @date: 2019/4/20 23:39
 */
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
