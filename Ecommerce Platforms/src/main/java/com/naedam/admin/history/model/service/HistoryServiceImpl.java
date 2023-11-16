package com.naedam.admin.history.model.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.naedam.admin.history.model.dao.HistoryDao;
import com.naedam.admin.history.model.vo.History;
import com.naedam.admin.history.model.vo.HistoryRequest;

@Service
public class HistoryServiceImpl implements HistoryService {
	@Autowired
	private HistoryDao historyDao;

	@Override
	public Map<String, Object> historyProcess(HistoryRequest historyRequest) throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> resultMap = new HashMap<>();
		History history = historyRequest.getHistory();
		HttpServletRequest request = historyRequest.getRequest();
		String mode = historyRequest.getMode();
		
			if("insert".equals(mode)) {
				resultMap = insertHistory(history, historyRequest);
			}
			if("update".equals(mode)) {
				resultMap = updateHistory(history, historyRequest);
			}
			if("delete".equals(mode)) {
				List<String> historyNoList = Arrays.asList(request.getParameterValues("list[]"));
				resultMap = deleteHistory(historyNoList);
			}
			return resultMap;
	}	
	@Override
	public History selectOneHistoryByHisNo(int historyNo) {
		return historyDao.selectOneHistoryByHisNo(historyNo);
	}
	
	@Override
	public Map<String, Object> selectHistoryList(Map<String, Object> map) throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> resultMap = new HashMap<String, Object>();
		/* 연혁 정보 */
		resultMap.put("history", historyDao.selectHistoryList(map));
		/* 기재 년도 조회 */
		resultMap.put("years", historyDao.selectYearList(map));
		/* 기재 월 조회 */
		resultMap.put("months", historyDao.selectMonthList(map));
		
		return resultMap;
	}

	@Override
	public Map<String, Object> insertHistory(History history, HistoryRequest historyRequest) throws ParseException {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
		Date date = formatter.parse(historyRequest.getFullDate());
		
		history.setHistoryDate(date);
		history.setLocale(historyRequest.getLocale());
		
		historyDao.insertHistory(history);
		resultMap.put("msg", "연혁정보가 등록되었습니다.");
		return resultMap;
	}

	@Override
	public Map<String, Object> updateHistory(History history, HistoryRequest historyRequest) throws ParseException {
		// TODO Auto-generated method stub
		Map<String, Object> resultMap = new HashMap<String, Object>();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
		Date date = formatter.parse(historyRequest.getFullDate());
		history.setHistoryDate(date);
		history.setLocale(historyRequest.getLocale());
		
		historyDao.updateHistory(history);
		resultMap.put("msg", "연혁정보가 수정되었습니다.");
		return resultMap;
	}

	@Override
	public Map<String, Object> deleteHistory(List<String> historyNoList) {
		// TODO Auto-generated method stub
		Map<String, Object> resultMap = new HashMap<String, Object>();
		for(String no : historyNoList) {
			historyDao.deleteHistory(Integer.parseInt(no));
		}
		
		resultMap.put("msg", "연혁정보가 삭제되었습니다.");
		return resultMap;
	}

	
	
}