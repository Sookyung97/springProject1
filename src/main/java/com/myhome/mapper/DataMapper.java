package com.myhome.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.myhome.dto.DataDto;

@Mapper
public interface DataMapper {
	
	int insertDataBoard(DataDto dto);

}
