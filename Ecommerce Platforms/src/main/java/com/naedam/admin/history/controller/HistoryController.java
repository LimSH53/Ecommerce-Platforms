package com.naedam.admin.history.controller;

import java.util.HashMap;
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

import com.naedam.admin.history.model.service.HistoryService;
import com.naedam.admin.history.model.vo.History;
import com.naedam.admin.history.model.vo.HistoryRequest;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/admin/history")
@Slf4j
public class HistoryController {
	@Autowired
	private HistoryService historyService;
	
	/**
	 * 설정 => 연혁 관리 정보 조회
	 * @param historyNo
	 * @return
	 */
	@PostMapping("/getHistory")
	@ResponseBody
	public History getHistory(int historyNo) {
		History history = historyService.selectOneHistoryByHisNo(historyNo);
		return history;
	}
	
	/**
	 * 설정 => 연혁 관리 DML 프로세스
	 * @param request
	 * @param history
	 * @param redirectAttr
	 * @return
	 * @throws Exception
	 */
	@PostMapping("history_process")
	public String history_process(HttpServletRequest request, History history, RedirectAttributes redirectAttr,
								  @RequestParam(value="historyImage", required = false) MultipartFile historyImage,
								  @RequestParam(value = "locale", defaultValue = "ko") String locale,
								  @RequestParam(value = "fullDate", required = false) String fullDate ) throws Exception {
		
		HistoryRequest historyRequest = new HistoryRequest();
		historyRequest.setHistory(history);
		historyRequest.setMode(request.getParameter("mode"));
		historyRequest.setLocale(locale);
		historyRequest.setRequest(request);
		historyRequest.setFullDate(fullDate);
		
		
		Map<String, Object> resultMap = historyService.historyProcess(historyRequest);
		redirectAttr.addFlashAttribute("msg", (String)resultMap.get("msg"));
				
		return "redirect:/admin/setting/history";
	}
	
}
