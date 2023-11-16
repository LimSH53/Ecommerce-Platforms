package com.naedam.admin.security.model.dao;

import java.util.Map;

import org.springframework.security.core.userdetails.UserDetails;

public interface SecurityDao {

	UserDetails loadUserByUsername(String username);

	int insertLoginDate(Map<String, Object> param);

	int insertAccessHistoryByLogin(Map<String, Object> paramHistory);

	int insertAccessHistoryByLogout(Map<String, Object> paramHistory);


}
