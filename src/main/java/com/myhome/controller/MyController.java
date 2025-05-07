package com.myhome.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.myhome.dto.DefaultDto;
import com.myhome.dto.MyboardDto;
import com.myhome.dto.ReboardDto;
import com.myhome.service.MyService;

@Controller
public class MyController {
	
	@Autowired
	MyService myService;
	
	@GetMapping("myboardWrite")
	public String myboardWrite() {
		return "board/myboardWrite";
	}
	
	@PostMapping("myboardInsert")
	@ResponseBody
	public String insertMyboard(MyboardDto dto) throws Exception {

		String message = "ok";
		// 저장(등록) 서비스 실행
		int result = myService.insertMyboard(dto);
		if (result == 0) message = "fail";
		return message;
	}

	@PostMapping("reboardInsert")
	@ResponseBody
	public String insertReboard(ReboardDto reboardto) throws Exception {
		System.out.println("1111111111");
		
		String message = "ok";
		int result = myService.insertReboard(reboardto);
		if( result == 0 ) message = "fail";
		
		return message;
	}

	@GetMapping("myboardList")
	public String selectMyboardList(DefaultDto defaultDto, ModelMap model) throws Exception {

		int total = myService.selectMyboardTotal(defaultDto);
		// 연산처리를 위해 dto에 값을 세팅(보냄)
		defaultDto.setTotal(total);

		// 총 페이지 개수를 계산 시키는 장면
		defaultDto.setTotalPage();
		// 계산 후 결과를 얻어옴
		int totalPage  = defaultDto.getTotalPage();

		// 출력 페이지의 시작 번호를 계산 시키는 장면
		defaultDto.setPageRownum();
		// 계산 후 결과를 얻어옴
		int pageRownum = defaultDto.getPageRownum();

		// SQL에 적용할 시작번호
		// 시작 번호의 계산 후 관련 변수에 값을 넣어 줌
		defaultDto.setFirstIndex();
		
		// SQL에 적용할 종료번호
		// 끝 번호의 계산 후 관련 변수에 값을 넣어 줌
		defaultDto.setLastIndex();

		// 서비스 실행
		List<?> list = myService.selectMyboardList(defaultDto);

		model.addAttribute("resultList", list);
		model.addAttribute("total", total);
		model.addAttribute("totalPage", totalPage);
		model.addAttribute("pageRownum", pageRownum);
		
		return "board/myboardList";
	}
	
	@GetMapping("myboardDetail/{seqid}")
	public String selectMyboardDetail(@PathVariable int seqid
			                                       ,ModelMap model) 
												throws Exception {
		// 조회수 증가 서비스 실행
		myService.updateMyboardHits(seqid);

		// 상세보기 서비스 실행
		MyboardDto dto = myService.selectMyboardDetail(seqid);
		
		// 댓글 목록 서비스 실행
		List<?> list = myService.selectReboardList(seqid);

		model.addAttribute("dto", dto);
		model.addAttribute("list", list);

		return "board/myboardDetail";
	}

	
	
	
}






