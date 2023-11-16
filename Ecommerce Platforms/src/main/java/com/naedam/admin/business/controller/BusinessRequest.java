package com.naedam.admin.business.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import com.naedam.admin.board.model.vo.BoardOption;
import com.naedam.admin.business.model.vo.Business;
import com.naedam.admin.business.model.vo.BusinessContents;
import com.naedam.admin.business.model.vo.BusinessPost;

import lombok.Data;

@Data
public class BusinessRequest {
	
	private String mode;
	private Business business;
	private BoardOption boardOption;
	private MultipartFile icon;
	private List<String> businessArr;
	private BusinessPost businessPost;
	private MultipartFile file;
	private HttpServletRequest request;
	private String secNo;
	private List<String> businessPostArr;
	private BusinessContents businessContents;
	private List<String> businessContentsArr;

}
