package com.xintu.manager.services.impl;

import java.util.ArrayList;
import java.util.List;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.xt.manage.api.interfaces.ContentCategoryService;
import com.xt.manage.api.mapper.ContentCategoryMapper;
import com.xt.manage.domain.model.ContentCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ContentCategoryServiceImpl extends BaseServiceImpl<ContentCategory> implements ContentCategoryService {

	@Autowired
	private ContentCategoryMapper contentCategoryMapper;

	@Override
	public ContentCategory saveContentCategory(ContentCategory contentCategory) {

		// 1.保存内容分类
		contentCategory.setIsParent(false);// 不是父节点
		contentCategory.setStatus(1);// 状态为正常
		contentCategory.setSortOrder(100);
		saveSelective(contentCategory);// 更新完后，id将会回填到这个contentCategory中

		// 2.更新该分类的父类目为父节点,isParent为true
		ContentCategory parent = new ContentCategory();
		parent.setId(contentCategory.getParentId());
		parent.setIsParent(true);
		contentCategoryMapper.update(parent, new UpdateWrapper<ContentCategory>());

		return contentCategory;
	}

	@Override
	public void deleteContentCategory(ContentCategory contentCategory) {

		List<Long> ids = new ArrayList<>();
		ids.add(contentCategory.getId());
		// 1.1、获取所有该节点子孙节点
		getCategoryId(ids,contentCategory);
		// 2 删除 
		deleteByIds(ids.toArray(new Long[]{}));
		
		//2、判断该节点的父节点是否还为父节点（如果父节点没有其它子节点了，那么它就不再是父节点，应该更新它的isParent为false）
		ContentCategory param = new ContentCategory();
		param.setParentId(contentCategory.getParentId());
		//判断父节点是否还有其它子节点
		long children = queryCountByWhere(param);
		//如果没有
		if(children == 0){
			ContentCategory parent = new ContentCategory();
			parent.setId(contentCategory.getParentId());
			parent.setIsParent(false);
			//更新该节点为普通节点
			updateSelectiveById(parent);
		}
	}

	/**
	 * 
	 * getCategoryId:().  递归获取所有该节点子孙节点
	 * @author 林捷凯 
	 * @Time：2017年2月16日 下午6:47:56
	 * @param ids
	 * @param contentCategory
	 */
	private void getCategoryId(List<Long> ids, ContentCategory contentCategory) {
		
		ContentCategory parent = new ContentCategory();
		parent.setParentId(contentCategory.getId());
		//根据查询条件查询总记录数
		List<ContentCategory> list = queryListByWhere(parent);
		if(list != null && list.size() > 0) {
			for (ContentCategory category : list) {
				ids.add(category.getId());
				//直接递归
				getCategoryId(ids, category);
			}
		}
	}

}
