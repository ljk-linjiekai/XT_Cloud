package com.xintu.manager.services.impl;

import java.util.List;

import com.xt.manage.api.interfaces.TestService;
import com.xt.manage.api.mapper.TestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class TestServiceImpl implements TestService {

	@Autowired
	public TestMapper testMapper;
	
	@Override
	public String queryCurrentDate() {
		
		return testMapper.queryCurrentDate();
	}

	
	
	

}
