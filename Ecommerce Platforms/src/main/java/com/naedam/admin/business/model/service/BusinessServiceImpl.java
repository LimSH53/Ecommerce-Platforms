package com.naedam.admin.business.model.service;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.naedam.admin.board.model.dao.BoardDao;
import com.naedam.admin.business.controller.BusinessRequest;
import com.naedam.admin.business.model.dao.BusinessDao;
import com.naedam.admin.business.model.vo.Business;
import com.naedam.admin.business.model.vo.BusinessContents;
import com.naedam.admin.business.model.vo.BusinessPost;
import com.naedam.admin.member.model.vo.Member;


@Service
public class BusinessServiceImpl implements BusinessService {

	@Autowired
	private BusinessDao businessDao;
	@Autowired
	private BoardDao boardDao;
	
	final static String businessFilePath = "resources/user/images/introduction/icon/";
	final static String businessFilePath2 = "resources/user/images/main/";
	final static String contentsFilePath = "resources/user/images/introduction/";
	
	public void businessProcess(BusinessRequest businessRequest) throws Exception{
		Business business = businessRequest.getBusiness();
		String mode = businessRequest.getMode();
		
		if("insert".equals(mode)) {
			businessDao.addBusiness(business);
		}
		if("update".equals(mode)) {
			businessDao.updateBusiness(business);
		}
		if("delete".equals(mode)) {
			List<Integer> businessArr = businessRequest.getBusinessArr().stream()
										.map(s -> Integer.parseInt(s))
										.collect(Collectors.toList());
			businessDao.deleteChoiceBusiness(businessArr);
		}
	}
	
	public boolean businessPostProcess(BusinessRequest businessRequest) throws Exception{
		BusinessPost businessPost = businessRequest.getBusinessPost();
		
		String mode = businessRequest.getMode();

		if("insert".equals(mode)) {
			return insertBusiness(businessPost, businessRequest);
		}
		if("update".equals(mode)) {
			return updateBusiness(businessPost, businessRequest);
		}
		
		if("delete".equals(mode)) {
			List<Integer> businessPostArr = businessRequest.getBusinessPostArr().stream()
						.map(s -> Integer.parseInt(s))
						.collect(Collectors.toList());
			int result = businessDao.deleteChoiceBusinessPost(businessPostArr);
			return result > 0 ? true : false;
		}
		
		return false;
	}
	
	private boolean updateBusiness(BusinessPost businessPost, BusinessRequest businessRequest) throws Exception {
		businessPost.setBusiness(businessRequest.getBusiness());
		MultipartFile iconName = businessRequest.getIcon();
		MultipartFile imgName = businessRequest.getFile();
		String filePath = businessRequest.getRequest().getServletContext().getRealPath(businessFilePath);
		String filePath2 = businessRequest.getRequest().getServletContext().getRealPath(businessFilePath2);
		
		//iconName not null
		if(!iconName.isEmpty()) {
			File file = new File(filePath + iconName.getOriginalFilename());
			businessPost.setBusinessPostIcon(iconName.getOriginalFilename());
			iconName.transferTo(file);
		}
		//imgName not null
		if(!imgName.isEmpty()) {
			File file = new File(filePath2 + imgName.getOriginalFilename());
			businessPost.setBusinessPostImg(imgName.getOriginalFilename());
			imgName.transferTo(file);
		}
		int result = businessDao.updateBusinessPost(businessPost);
		return result > 0 ? true : false;
	}

	private boolean insertBusiness(BusinessPost businessPost, BusinessRequest businessRequest) throws Exception {
		businessPost.setBusiness(businessRequest.getBusiness());
		MultipartFile iconName = businessRequest.getIcon();
		MultipartFile imgName = businessRequest.getFile();
		String filePath = businessRequest.getRequest().getServletContext().getRealPath(businessFilePath);
		String filePath2 = businessRequest.getRequest().getServletContext().getRealPath(businessFilePath2);
		
		//작성자 등록
		Member member = boardDao.getMemberData(Integer.parseInt(businessRequest.getSecNo().toString()));
		businessPost.setBusinessMember(member);
		
		if(!iconName.isEmpty()){
			businessPost.setBusinessPostIcon(iconName.getOriginalFilename());
			File file = new File(filePath + iconName.getOriginalFilename());
			iconName.transferTo(file);	
		}
		if(!imgName.isEmpty()){
			businessPost.setBusinessPostImg(imgName.getOriginalFilename());
			File file = new File(filePath2 + imgName.getOriginalFilename());
			imgName.transferTo(file);	
		}
		int result = businessDao.addBusinessPost(businessPost);
	
		return result > 0 ? true : false;
	}


	public boolean businessContentsProcess(BusinessRequest businessRequest) throws Exception{
		BusinessContents businessContents = businessRequest.getBusinessContents();
		String mode = businessRequest.getMode();
			
		if ("insert".equals(mode)) {
			return insertContents(businessContents, businessRequest);
		}
		if ("update".equals(mode)) {
			return updateContents(businessContents, businessRequest);
		}
		if("delete".equals(mode)) {
			List<Integer> businessContentsArr = businessRequest.getBusinessContentsArr().stream()
									.map(s -> Integer.parseInt(s))
									.collect(Collectors.toList());
			int result = businessDao.deleteChoiceBusinessContents(businessContentsArr);
			return result > 0 ? true : false;
		}
		
		return false;
	}
	
	private boolean updateContents(BusinessContents businessContents, BusinessRequest businessRequest) throws Exception {
		businessContents.setBusinessPost(businessRequest.getBusinessPost());
		MultipartFile imageName = businessRequest.getFile();
		String filePath = businessRequest.getRequest().getServletContext().getRealPath(contentsFilePath);
		
		if (!imageName.isEmpty()) {
			File file = new File(filePath + imageName.getOriginalFilename());
			businessContents.setImage(imageName.getOriginalFilename());
			imageName.transferTo(file);
		} 
		
		int result = businessDao.updateBusinessContents(businessContents);
		
		return result > 0 ? true : false;
	}

	private boolean insertContents(BusinessContents businessContents, BusinessRequest businessRequest) throws Exception {
		businessContents.setBusinessPost(businessRequest.getBusinessPost());
		
		MultipartFile imageName = businessRequest.getFile();
		String filePath = businessRequest.getRequest().getServletContext().getRealPath(contentsFilePath);
		
		if (imageName.isEmpty() == false) {
			File file = new File(filePath + imageName.getOriginalFilename());
			imageName.transferTo(file);
			businessContents.setImage(imageName.getOriginalFilename());
		}
		int result = businessDao.addBusinessContents(businessContents);
		return result > 0 ? true : false;
	}

	@Override
	public Business getBusiness(int businessNo) throws Exception {
		return businessDao.getBusiness(businessNo);
	}
	
	@Override
	public BusinessPost getBusinessPost(int businessPostNo) throws Exception {
		return businessDao.getBusinessPost(businessPostNo);
	}
	
	@Override
	public Map<String, Object> getBusinessList(Map<String, Object> map) throws Exception {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("list", businessDao.getBusinessList(map));
		return resultMap;
	}

	@Override
	public List<BusinessPost> getBusinessPostList(Map<String, Object> map) throws Exception {
		return businessDao.getBusinessPostList(map);
	}

	@Override
	public List<BusinessPost> getBusinessPostAllList(Map<String, Object> map) throws Exception {
		return businessDao.getBusinessPostAllList(map);
	}
	
	@Override
	public List<BusinessContents> getBusinessContentsList(Map<String, Object> map) throws Exception{
		return businessDao.getBusinessContentsList(map);
	}

	@Override
	public BusinessContents getBusinessContents(int businessPostNo) throws Exception {
		return businessDao.getBusinessContents(businessPostNo);
	}

	@Override
	public int TotalBusinessPost(int businessNo) {
		return businessDao.TotalBusinessPost(businessNo);
	}

}