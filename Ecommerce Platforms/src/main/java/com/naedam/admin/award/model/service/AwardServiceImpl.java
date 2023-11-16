package com.naedam.admin.award.model.service;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.naedam.admin.award.model.dao.AwardDao;
import com.naedam.admin.award.model.vo.Award;
import com.naedam.admin.award.model.vo.AwardRequest;

@Service
public class AwardServiceImpl implements AwardService {

	@Autowired
	private AwardDao awardDao;
	
	final static String awardImgPath = "resources/user/images/company/award/";
	
	@Override
	public Map<String, Object> awardProcess(AwardRequest awardRequest) throws Exception {
		Map<String, Object> resultMap = new HashMap<>();
		Award award = awardRequest.getAward();
		HttpServletRequest request = awardRequest.getRequest();
		String mode = awardRequest.getMode();
		String filePath = request.getServletContext().getRealPath(awardImgPath);
		
		if("insert".equals(mode)) {
			resultMap = insertAward(award, awardRequest, filePath);
		}
		
		if("update".equals(mode)) {
			resultMap = updateAward(award, awardRequest, filePath);
		}
		
		if("delete".equals(mode)) {
			List<String> historyNoList = Arrays.asList(request.getParameterValues("list[]"));
			resultMap = deleteAward(historyNoList);
		}
		
		return resultMap;
	}

	@Override
	public Map<String, Object> insertAward(Award award, AwardRequest awardRequest, String filePath) throws ParseException, IllegalStateException, IOException {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
		Date date = formatter.parse(awardRequest.getFullDate());
		award.setAwardDate(date);
		award.setLocale(awardRequest.getLocale());
		
		MultipartFile awardImage = awardRequest.getAwardImage();
		File file = new File(filePath+awardImage.getOriginalFilename());
		award.setImgUrl(awardImage.getOriginalFilename());
		awardImage.transferTo(file);
		awardDao.insertAward(award);
		resultMap.put("msg", "수상 정보가 등록되었습니다.");
		return resultMap;
	}

	@Override
	public Map<String, Object> updateAward(Award award, AwardRequest awardRequest, String filePath) throws ParseException, IllegalStateException, IOException {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
		Date date = formatter.parse(awardRequest.getFullDate());
		award.setAwardDate(date);
		award.setLocale(awardRequest.getLocale());
		
		MultipartFile awardImage = awardRequest.getAwardImage();
		if(! awardImage.isEmpty()) {
			File file = new File(filePath+awardImage.getOriginalFilename());
			award.setImgUrl(awardImage.getOriginalFilename());
			awardImage.transferTo(file);
		}
		
		awardDao.updateAward(award);
		resultMap.put("msg", "수상 정보가 수정되었습니다.");
		
		return resultMap;
	}

	@Override
	public Map<String, Object> deleteAward(List<String> historyNoList)throws NumberFormatException, Exception {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		for(String no : historyNoList) {
			awardDao.deleteAward(Integer.parseInt(no));
		}
		resultMap.put("msg", "수상 정보가 삭제되었습니다.");
		return resultMap;
	}


	@Override
	public Award selectDetailByNo(int awardNo) {

		return awardDao.selectDetailByNo(awardNo);
	}

	@Override
	public Map<String, Object> selectAwardList(Map<String, Object> map) throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> awardmap = new HashMap<String, Object>();
		awardmap.put("award", awardDao.selectAwardList(map));
		awardmap.put("awardYears", awardDao.SelectYear(map));
		awardmap.put("awardMonths", awardDao.SelectMonth(map));
		
		return awardmap;
	}



	
}
