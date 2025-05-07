package com.myhome.controller;

import java.io.File;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.text.html.HTMLDocument.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.myhome.dto.DataDto;
import com.myhome.service.DataService;

@Controller
public class DataController {
	
	@Autowired
	DataService dataService;
	
	@PostMapping("dataBoardInsert")
	@ResponseBody
	public String insertDataBoard( MultipartHttpServletRequest request
									,DataDto dto) throws Exception {
		
		String path = "C:\\srpingWorkspace";
		
		// 넘어온 데이터를 가져옴
		Map map = request.getFileMap();
		
		// Map의 키값(들)을 가져옴
		Iterator it = (Iterator) map.entrySet().iterator();
		
		// 키값이 있는 위치로 커서를 내려보냄
		Entry entry = (Entry) it.next();
		
		// 해당 위치에서 파일의 정보들을 가져온다.
		MultipartFile file = (MultipartFile)entry.getValue();
		if( file.getSize() > 0 ) {
		
			String filename = System.currentTimeMillis() + "";
			
			// abc.jpg -> f2[0] = "abc"; f2[1] = "jpg";
			String f1 = file.getOriginalFilename();
			String[] f2 = f1.split("\\.");
			String exe = f2[f2.length-1];
			filename += "."+exe;
			
			String filepath = path + "/" + filename;
			
			// {실저장} / {파일카피}
			file.transferTo(new File(filepath));
			filenames += filename + "/";
		} 
		
		String dirname = "/webapp/data";
		
		
		dto.setFilepath(dirname);
		dto.setFilename(filename);
		dto.getFilesize(Integer.parseInt(file.getSize()+""));
		
		int result = dataService.insertDataboard(dto);
		
		System.out.println("이름 : "+filename);
		System.out.println("크기 : "+file.getSize());
		System.out.println("용량 : "+file.getBytes());
		System.out.println("종류 : "+file.getContentType());
		System.out.println("이름 : "+file.getName());
	
		return "1";
	}
	
	@GetMapping("dataDetail/{seqid}")
	public String selectDataboardDetail( @PathVariable int seqid
											, ModelMap model) throws Exception {
	
	dataService.updateDataboardHits(seqid);
	
	DataDto dto = dataService.selectDataboardDetail(seqid);
	
	String files = dto.getFilename().trim();
	files = filesNewMake(files);
	
	
	dto.setFilename(files);
	model.addAttribute("dto", dto);
	
	return "board/dataDetail";
			
	}
	
	public static String filesNewMake(String files) {
		if( !files.equals("") ) {
			String[] str = files.split("/");
			for(int i=0; i<str.length; i++) {
				String fname = str[i];
				String filepath = path +"/" + fname;
				
				File file = new File(filepath);
				if(file.exists() == false ) {
					files = files.replace(fname+"/", "");
				}
			}
		}
	}
}
