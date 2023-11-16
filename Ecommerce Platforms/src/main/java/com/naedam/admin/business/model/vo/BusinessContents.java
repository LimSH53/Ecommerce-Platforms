package com.naedam.admin.business.model.vo;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BusinessContents implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int businessContentsNo;			
	private BusinessPost businessPost;
	private String businessContentsTitle; 	
	private String businessContentsContent;
	private String businessContentsType;
	private String image;
	private String inDtm;
	private String locale;
	
	
	
	
}
