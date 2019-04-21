package com.xintu.manager.services.impl;

import com.xt.manage.api.interfaces.ItemDescService;
import com.xt.manage.api.mapper.ItemDescMapper;
import com.xt.manage.domain.model.ItemDesc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ItemDescServiceImpl extends BaseServiceImpl<ItemDesc> implements ItemDescService {
	
	@Autowired
	private ItemDescMapper itemDescMapper;

	

	

}
