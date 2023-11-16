package com.naedam.admin.award.model.vo;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class AwardRequest {
	private Award award;
	private String mode;
	private HttpServletRequest request;
	private MultipartFile awardImage;
	private String locale;
	private String fullDate;
}
