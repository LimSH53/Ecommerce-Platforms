package com.naedam.admin.history.model.vo;


import javax.servlet.http.HttpServletRequest;
import lombok.Data;

@Data
public class HistoryRequest {
	
	private History history;
	private String mode;
	private HttpServletRequest request;
	private String locale;
	private String fullDate;

}
/**
		map.put("history", history);
		map.put("mode", request.getParameter("mode"));
		map.put("request", request);
		map.put("locale", locale);
*/