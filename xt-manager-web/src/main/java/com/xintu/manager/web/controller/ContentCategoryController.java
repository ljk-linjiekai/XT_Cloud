package com.xintu.manager.web.controller;

import com.xintu.manager.web.fegin.ContentCategoryApi;
import com.xt.manage.domain.model.ContentCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author 林捷凯
 * @Time：2017年2月16日 下午4:30:28
 * @version 1.0
 */
@RequestMapping("/content/category")
@Controller
public class ContentCategoryController {

	@Autowired
	private ContentCategoryApi contentCategoryApi;

	/**
	 * 
	 * queryContentCategoryListByParentId:(). 根据父节点id查询该节点下的所有子节点
	 * 
	 * @author 林捷凯
	 * @Time：2017年2月16日 下午4:34:22
	 * @param parentId
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<ContentCategory>> queryContentCategoryListByParentId(
			@RequestParam(value = "id", defaultValue = "0") Long parentId) {
		try {
			// 创建查询条件
			ContentCategory category = new ContentCategory();
			category.setParentId(parentId);
			// 查询
			List<ContentCategory> list = contentCategoryApi.queryListByWhere(category);
			return ResponseEntity.ok(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 500
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);

	}

	/**
	 * 
	 * saveContentCategory:(). 添加内容分类
	 * 
	 * @author 林捷凯
	 * @Time：2017年2月16日 下午5:04:03
	 * @param contentCategory
	 * @return
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ResponseEntity<ContentCategory> saveContentCategory(ContentCategory contentCategory) {
		try {
			ContentCategory category = contentCategoryApi.saveContentCategory(contentCategory);
			return ResponseEntity.ok(category);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 500
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	}

	/**
	 * 
	 * updateContentCategory:(). 重命名类目
	 * 
	 * @author 林捷凯
	 * @Time：2017年2月16日 下午5:24:48
	 * @param contentCategory
	 * @return
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public ResponseEntity<Void> updateContentCategory(ContentCategory contentCategory) {
		try {
			contentCategoryApi.updateSelectiveById(contentCategory);
			return ResponseEntity.ok(null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 500
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	}

	
	/**
	 * 
	 * deleteContentCategory:(). 删除节点
	 * @author 林捷凯 
	 * @Time：2017年2月16日 下午6:39:34
	 * @param contentCategory
	 * @return
	 */
	@RequestMapping(value="/delete",method= RequestMethod.POST)
	public ResponseEntity<Void> deleteContentCategory(ContentCategory contentCategory) {
		try {
			//删除该节点以及子孙节点
			contentCategoryApi.deleteContentCategory(contentCategory);
			return ResponseEntity.ok(null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	}
}
