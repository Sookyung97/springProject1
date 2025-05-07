package com.myhome.service;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myhome.dto.DataDto;

@Service
public class DataServiceImpl implements DataService {
	
	@Autowired
	Mapper mapper;
	
	@Override
	public int insertDataboard(DataDto dto) throws Exception {
		return 0;
	}

	
}
