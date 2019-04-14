package com.xintu.manager.services.impl;

import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.xt.manage.api.interfaces.ItemCatService;
import com.xt.manage.api.mapper.ItemCatMapper;
import com.xt.manage.model.ItemCat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ItemCatServiceImpl extends BaseServiceImpl<ItemCat> implements ItemCatService {
	
	@Autowired
	private ItemCatMapper itemCatMapper;

	@Override
	public List<ItemCat> queryItemCatByPage(Integer page, Integer rows) {
		
		//设置分页
		PageHelper.startPage(page, rows);
		
		//查询数据
		List<ItemCat> list = itemCatMapper.selectList(new QueryWrapper<ItemCat>());
		
		return list;
	}

}
