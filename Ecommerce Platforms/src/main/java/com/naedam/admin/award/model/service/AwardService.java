package com.naedam.admin.award.model.service;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

import com.naedam.admin.award.model.vo.Award;
import com.naedam.admin.award.model.vo.AwardRequest;

public interface AwardService {

	Map<String, Object> awardProcess(AwardRequest awardRequest) throws Exception;

	Map<String, Object> insertAward(Award award, AwardRequest awardRequest, String filePath) throws ParseException, IllegalStateException, IOException;

	Map<String, Object> updateAward(Award award, AwardRequest awardRequest, String filePath) throws ParseException, IllegalStateException, IOException;

	Map<String, Object> deleteAward(List<String> boardNoList) throws NumberFormatException, Exception;

	public Map<String,Object> selectAwardList(Map<String, Object> map) throws Exception;

	public Award selectDetailByNo(int awardNo);

}
