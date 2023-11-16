package com.naedam.admin.menu.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import com.naedam.admin.menu.model.vo.Bottom;
import com.naedam.admin.menu.model.vo.Head;
import com.naedam.admin.menu.model.vo.Menu;

import lombok.Data;

@Data
public class MenuRequest {
	
	private Menu menu;
	private List<String> menuArr;
	private Head head;
	private Bottom bottm;
	private String mode;
	private String part;
	private MultipartFile headImage;
	private String filePath;
	private String imageStatus;
	
	// 임시
	private HttpServletRequest request;
	

}
