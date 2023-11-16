package com.naedam.admin.history.model.service;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import com.naedam.admin.history.model.vo.History;
import com.naedam.admin.history.model.vo.HistoryRequest;

public interface HistoryService {

	Map<String, Object> historyProcess(HistoryRequest historyRequest) throws Exception;

	History selectOneHistoryByHisNo(int historyNo);

	Map<String, Object> selectHistoryList(Map<String, Object> map) throws Exception;

	Map<String, Object> insertHistory(History history, HistoryRequest historyRequest) throws ParseException;

	Map<String, Object> updateHistory(History history, HistoryRequest historyRequest) throws ParseException;

	Map<String, Object> deleteHistory(List<String> historyNoList);

}