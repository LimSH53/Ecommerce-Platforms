package com.naedam.admin.board.model.vo;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;
import lombok.Data;

@Data
public class BoardRequest {

	private Board board;
	private BoardOption boardOption;
	private String mode;
	private HttpServletRequest request;
	private MultipartFile boardImage;
	private String locale;
	private String fullDate;
	
}
