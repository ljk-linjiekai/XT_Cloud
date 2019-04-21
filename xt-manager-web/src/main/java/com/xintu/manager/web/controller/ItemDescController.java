package com.xintu.manager.web.controller;

import com.xintu.manager.web.fegin.ItemDescApi;
import com.xt.manage.domain.model.ItemDesc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping("/item/desc")
@Controller
public class ItemDescController {

	@Autowired
	private ItemDescApi itemDescApi;


	/**
	 * 根据商品id查询商品描述信息
	 * @param itemId 商品id
	 * @return
	 */
	@RequestMapping(value = "/{itemId}", method = RequestMethod.GET)
	public ResponseEntity<ItemDesc> queryItemDescByItemid(@PathVariable(value = "itemId")Long itemId) {

		try {
			ItemDesc itemDesc = itemDescApi.queryItemDescByItemid(itemId);
			// 返回ok
			return ResponseEntity.ok(itemDesc);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	}
}
