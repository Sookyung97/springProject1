package com.myhome.service;

import java.util.List;

import com.myhome.dto.DefaultDto;
import com.myhome.dto.MyboardDto;
import com.myhome.dto.ReboardDto;

public interface MyService {

	/**
	 * 입력 처리
	 * @param dto
	 * @return Integer
	 * @throws Exception
	 */
	int insertMyboard(MyboardDto dto) throws Exception;

	/**
	 * 댓글 게시판 목록
	 * @param dto
	 * @return List
	 */
	List<?> selectMyboardList(DefaultDto dto) throws Exception;

	/**
	 * 댓글 총 개수
	 * @param defaultDto
	 * @return
	 */
	int selectMyboardTotal(DefaultDto defaultDto) throws Exception;

	/**
	 * 조회수 증가
	 */
	void updateMyboardHits(int seqid) throws Exception;

	/**
	 * 상세보기
	 * @param seqid
	 * @return
	 */
	MyboardDto selectMyboardDetail(int seqid) throws Exception;

	/**
	 * 댓글저장
	 * @param reboardto
	 * @return
	 */
	int insertReboard(ReboardDto reboardto) throws Exception;

	/**
	 * 댓글목록
	 * @return
	 */
	List<?> selectReboardList(int seqid) throws Exception;

}



