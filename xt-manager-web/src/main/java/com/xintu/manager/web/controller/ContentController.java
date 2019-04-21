package com.xintu.manager.web.controller;

import com.xintu.common.vo.DataGridResult;
import com.xintu.manager.web.fegin.ContentApi;
import com.xt.manage.domain.model.Content;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


/**
 * 	
 * @author 林捷凯
 * @Time：2017年2月16日 下午7:15:20
 * @version 1.0  
 * Function: TODO
 */
@RequestMapping("/content")
@Controller
public class ContentController {

	@Autowired
	private ContentApi contentApi;

	
	/**
	 * 分页查询类目内容列表
	 * queryContentListByPage:(). 
	 * @author 林捷凯 
	 * @Time：2017年2月16日 下午7:37:00
	 * @param categoryId
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping(method= RequestMethod.GET)
	public ResponseEntity<DataGridResult> queryContentListByPage(
			@RequestParam(value="categoryId",defaultValue="0")Long categoryId,
			@RequestParam(value="page",defaultValue="1")Integer page,
			@RequestParam(value="rows",defaultValue="20")Integer rows){
		try {
			//查询分页列表
			DataGridResult dataGridResult = contentApi.queryContentListByPage(categoryId,page, rows);
			return ResponseEntity.ok(dataGridResult);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	}

	
	
	/**
	 * 新增类目内容
	 * saveContent:(). 
	 * @author 林捷凯 
	 * @Time：2017年2月16日 下午7:43:27
	 * @param content
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> saveContent(Content content){
		try {
			//新增类目内容
			contentApi.saveSelective(content);
			return ResponseEntity.ok(null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	}
	
	
	/**
	 * 更新类目内容
	 * saveContent:(). 
	 * @author 林捷凯 
	 * @Time：2017年2月16日 下午7:43:27
	 * @param content
	 * @return
	 */
	@RequestMapping(value = "/edit",method = RequestMethod.POST)
	public ResponseEntity<Void> Content(Content content){
		try {
			//新增类目内容
			contentApi.updateSelectiveById(content);
			return ResponseEntity.ok(null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	}
	
	
	/**
	 * 删除类目内容
	 * deleteContent:(). 
	 * @author 林捷凯 
	 * @Time：2017年2月16日 下午8:58:03
	 * @param ids
	 * @return
	 */
	@RequestMapping(value="/delete",method= RequestMethod.POST)
	public ResponseEntity<Void> deleteContent(@RequestParam("ids")Long[] ids){
		try {
			//删除
			contentApi.deleteByIds(ids);
			return ResponseEntity.ok(null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	}
}
