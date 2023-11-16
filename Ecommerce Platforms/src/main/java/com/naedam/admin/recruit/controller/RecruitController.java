package com.naedam.admin.recruit.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import com.naedam.admin.common.NaedamUtils;
import com.naedam.admin.recruit.model.service.RecruitService;
import com.naedam.admin.recruit.model.vo.recruitContentsDTO;
import com.naedam.admin.recruit.model.vo.recruitDTO;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/admin/*")
@Slf4j
public class RecruitController {

	@Autowired
	private RecruitService recruitService;
	
	//채용번호
	private int curRecruitNo;

	// 채용 게시글 조회
	@RequestMapping("recruitList")
	public String recruitList(Model model, @RequestParam(defaultValue = "1") int cPage, HttpServletRequest request,
			@RequestParam(value= "searchKeyword",required = false) String searchKeyword,
			@RequestParam(value = "locale", defaultValue = "ko") String locale) throws Exception {
		//조회 전 마감일자 지난 list들 채용마감으로 변경
		int update = recruitService.updateContentsStatus();
		
		// 게시글 조회 한도
		int limit = 10;
		int offset = (cPage - 1) * limit;

		// 게시글 리스트
		Map<String, Object> resultMap = recruitService.getRecruitList(searchKeyword, limit, offset, locale);

		// 게시글 총 갯수
		int totalRecruitListCount = Integer.parseInt(resultMap.get("totalCount").toString());

		// 페이징 처리
		String url = request.getRequestURI();
		String pagebar = NaedamUtils.getPagebar(cPage, limit, totalRecruitListCount, url);
		
		
		model.addAttribute("pagebar", pagebar);
		model.addAttribute("list", resultMap.get("list"));
		model.addAttribute("pageCount", totalRecruitListCount);
		model.addAttribute("searchKeyword", searchKeyword);
		model.addAttribute("locale", locale);
		

		return "admin/recruit/recruitList";

	}
	
	@PostMapping("deleteRecruit")
	@ResponseBody
	public String deleteRecruit(@RequestParam(value = "postArr[]") List<Integer> postArr) {

		int deleteResult = recruitService.deleteRecruit(postArr);

		return null;

	}

	@PostMapping(value = "insertRecruit" , produces = "application/text; charset=UTF-8")
	@ResponseBody
	public String insertRecruit(@ModelAttribute recruitDTO recruit,
			@RequestParam(value = "subTitle[]", required = false)List<String> subTitle,
			@RequestParam(value = "contents[]", required = false)List<String> contents,
			@RequestParam(value = "locale", defaultValue = "ko") String locale) {
	
		int recruitRecult = 0;
		
		// 채용 게시글 입력
		recruitRecult = recruitService.insertRecruit(recruit);
		
		// 채용 게시글 번호
		curRecruitNo = recruit.getRecruitNo();
		
		int insertResult = 0;
		
		if(subTitle != null) {
			// 상세내용 추가 입력
			insertResult = recruitService.insertRecruitContents(subTitle, contents, curRecruitNo);
		}					
		log.info("insertResult >>> " + insertResult);
		
		return null;
	}
	
	@PostMapping(value = "insertFile")
	@ResponseBody
	public int insertFile(@RequestParam(value = "file", required = false) List<MultipartFile> fileList,
			HttpServletRequest request) {
		
		int fileResult = recruitService.insertFile(fileList, request, curRecruitNo);
		
		int result;
		
		if(fileResult == 1) {
			result = 1;	
		} else if(fileResult == 0){
			result = 2;
		} else {
			result = 3;
		}

		return result;
	}
	
	@GetMapping("getRecruitData")
	@ResponseBody
	public Map<String, Object> getRecruitData(Model model,@RequestParam("postNo") int recruitNo) {
		
		//채용글 1차
		recruitDTO recruitData = recruitService.getRecruitData(recruitNo);
		
		//채용글 2차 (리스트 여러개)
		List<recruitContentsDTO> contents = recruitService.getContentsData(recruitNo);
		
		Map<String, Object> resultMap = new HashMap<>();
		
		resultMap.put("recruitData", recruitData);
		resultMap.put("contents", contents);
	
		
		return resultMap;
	}
	
	
	@PostMapping(value = "updateRecruit", produces = "application/text; charset=UTF-8")
	@ResponseBody
	public String updateRecruit(@ModelAttribute recruitDTO recruit,
			@RequestParam(value = "subTitle[]", required = false)List<String> subTitle,
			@RequestParam(value = "contents[]", required = false)List<String> contents) throws Exception {

		int recruitResult = 0;
		String msg="";
		
		curRecruitNo = recruit.getRecruitNo();
		
		// 채용 게시글 업데이트
		recruitResult = recruitService.updateRecruit(recruit);


		//업데이트를 위해 상세 내용 삭제
		int deleteResult = recruitService.deleteRecruitContents(curRecruitNo);
		
		// 채용 상세 내용 업데이트
		int insertResult = recruitService.insertRecruitContents(subTitle, contents, curRecruitNo);

		return null;

	}

}
