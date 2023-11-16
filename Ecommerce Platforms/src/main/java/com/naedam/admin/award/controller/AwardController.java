package com.naedam.admin.award.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.naedam.admin.award.model.service.AwardService;
import com.naedam.admin.award.model.vo.Award;
import com.naedam.admin.award.model.vo.AwardRequest;

@Controller
@RequestMapping("/admin/award")
public class AwardController {

	@Autowired
	private AwardService awardService;
	
	/**
	 * 설정 => 연혁 관리 정보 조회
	 * @param historyNo
	 * @return
	 */
	@PostMapping("/getAward")
	@ResponseBody
	public Award getAward(@RequestParam("awardNo") int awardNo) {

		Award award = awardService.selectDetailByNo(awardNo);
		return award;
	}
	
	/**
	 * 설정 => 연혁 관리 DML 프로세스
	 * @param request
	 * @param award
	 * @param redirectAttr
	 * @return
	 * @throws Exception
	 */
	@PostMapping("award_process")
	public String award_process(HttpServletRequest request, Award award, 
								RedirectAttributes redirectAttr,
								@RequestParam(value="awardImage", required = false) MultipartFile awardImage,
								@RequestParam(value = "locale", defaultValue = "ko") String locale,
								@RequestParam(value = "fullDate", required = false) String fullDate
			) throws Exception {
		
		AwardRequest awardRequest = new AwardRequest();
		awardRequest.setAward(award);
		awardRequest.setMode(request.getParameter("mode"));
		awardRequest.setRequest(request);
		awardRequest.setAwardImage(awardImage);
		awardRequest.setLocale(locale);
		awardRequest.setFullDate(fullDate);
		
		Map<String, Object> resultMap = awardService.awardProcess(awardRequest);
		redirectAttr.addFlashAttribute("msg", (String)resultMap.get("msg"));
		return "redirect:/admin/setting/award";
	}
	
	
}
