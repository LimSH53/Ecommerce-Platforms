package com.naedam.admin.board.model.vo;



import java.util.List;

import javax.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;
import lombok.Data;

@Data
public class PostRequest {
	
	private Board board;
	private Post post;
	private String mode;
	private HttpServletRequest request;
	private MultipartFile[] postImage;
	private String locale;
	private String secNo;
	private String filePath;
	private int boardNo;
	private List<String> postArr;
	

}
