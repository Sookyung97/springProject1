package com.myhome.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myhome.dto.DefaultDto;
import com.myhome.dto.MyboardDto;
import com.myhome.dto.ReboardDto;
import com.myhome.mapper.MyMapper;

@Service
public class MyServiceImpl implements MyService {

	@Autowired
	MyMapper mapper;
	
	@Override
	public int insertMyboard(MyboardDto dto) throws Exception {
		return mapper.insertMyboard(dto);
	}

	@Override
	public List<?> selectMyboardList(DefaultDto dto) throws Exception {
		return mapper.selectMyboardList(dto);
	}

	@Override
	public int selectMyboardTotal(DefaultDto defaultDto) throws Exception {
		return mapper.selectMyboardTotal(defaultDto);
	}

	@Override
	public void updateMyboardHits(int seqid) throws Exception {
		mapper.updateMyboardHits(seqid);
	}

	@Override
	public MyboardDto selectMyboardDetail(int seqid) throws Exception {
		return mapper.selectMyboardDetail(seqid);
	}

	@Override
	public int insertReboard(ReboardDto reboardto) throws Exception {
		return mapper.insertReboard(reboardto);
	}

	@Override
	public List<?> selectReboardList(int seqid) throws Exception {
		return mapper.selectReboardList(seqid);
	}

}





