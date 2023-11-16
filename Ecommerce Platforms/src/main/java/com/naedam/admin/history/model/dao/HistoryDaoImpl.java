package com.naedam.admin.history.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.naedam.admin.history.model.vo.History;

@Repository
public class HistoryDaoImpl implements HistoryDao {
	@Autowired
	private SqlSession session;


	@Override
	public void insertHistory(History history) {
		// TODO Auto-generated method stub
		session.insert("history.insertHistroy", history);
	}

	@Override
	public void updateHistory(History history) {
		// TODO Auto-generated method stub
		session.update("history.updateHistory", history);
	}

	@Override
	public void deleteHistory(int historyNo) {
		// TODO Auto-generated method stub
		session.delete("history.deleteHistory", historyNo);
	}

	@Override
	public List<History> selectHistoryList(Map<String,Object> map) {
		// TODO Auto-generated method stub
		return session.selectList("history.selectHistoryList", map);
	}

	@Override
	public List<History> selectYearList(Map<String,Object> map) {
		// TODO Auto-generated method stub
		return session.selectList("history.selectYearList", map);
	}

	@Override
	public List<History> selectMonthList(Map<String,Object> map) {
		// TODO Auto-generated method stub
		return session.selectList("history.selectMonthList", map);
	}
	
	@Override
	public History selectOneHistoryByHisNo(int historyNo) {
		// TODO Auto-generated method stub
		return session.selectOne("history.selectOneHistoryByHisNo", historyNo);
	}
	
}