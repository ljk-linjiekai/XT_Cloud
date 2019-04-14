package com.xt.manage.api.interfaces;


import com.xintu.common.vo.DataGridResult;
import com.xt.manage.model.Content;

public interface ContentService extends BaseService<Content>{

	
	/**
	 * 查询类目内容分页列表
	 * queryContentListByPage:(). 
	 * @author 林捷凯 
	 * @Time：2017年2月16日 下午7:24:49
	 * @param categoryId
	 * @param page
	 * @param rows
	 * @return
	 */
	DataGridResult queryContentListByPage(Long categoryId, Integer page, Integer rows);

	
	
	/**
	 * 获取门户系统首页中展示的6条大广告数据
	 * @return 列表数据（json格式字符串）
	 * @throws Exception 
	 */
	String getPortalBigAdData() throws Exception;
	
}
