package com.xintu.manager.web.fegin.impl;

import com.xintu.manager.web.fegin.ItemDescApi;
import com.xintu.manager.web.fegin.ItemDescClient;
import com.xt.manage.api.interfaces.ItemDescService;
import com.xt.manage.api.mapper.ItemDescMapper;
import com.xt.manage.domain.model.ItemDesc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ItemDescApiImpl implements ItemDescApi {
	
	@Autowired
	private ItemDescClient itemDescClient;


	@Override
	public ItemDesc queryItemDescByItemid(Long id) throws  Exception{
		return itemDescClient.queryItemDescByItemid(id);
	}
}
