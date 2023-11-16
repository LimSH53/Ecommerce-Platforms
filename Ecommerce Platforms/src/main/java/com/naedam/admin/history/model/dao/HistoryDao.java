package com.naedam.admin.history.model.dao;

import java.util.List;
import java.util.Map;

import com.naedam.admin.history.model.vo.History;

public interface HistoryDao {

	History selectOneHistoryByHisNo(int historyNo);

	public List<History> selectHistoryList(Map<String,Object> map);
	
	public List<History> selectYearList(Map<String,Object> map);

	public List<History> selectMonthList(Map<String,Object> map);
	
	void insertHistory(History history);

	void updateHistory(History history);

	void deleteHistory(int historyNo);


}