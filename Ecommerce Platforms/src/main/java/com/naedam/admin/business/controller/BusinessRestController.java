package com.naedam.admin.business.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.naedam.admin.business.model.service.BusinessService;
import com.naedam.admin.business.model.vo.Business;
import com.naedam.admin.business.model.vo.BusinessContents;
import com.naedam.admin.business.model.vo.BusinessPost;

@RestController
@RequestMapping("/admin/business/*")
public class BusinessRestController {
	
	@Autowired
	private BusinessService businessService;
	
	@PostMapping("json/businessProcess")
	public Boolean BusinessProcess(@RequestParam(value = "businessArr[]") List<String> businessArr, 
		   @RequestParam("mode") String mode) throws Exception{
		Boolean result = false;
		
		BusinessRequest businessRequest = new BusinessRequest();
		businessRequest.setMode(mode);
		businessRequest.setBusinessArr(businessArr);
		
		businessService.businessProcess(businessRequest);
		result = true;
		return result;
	}
	
	@PostMapping("json/businessPostProcess")
	public Boolean BusinessPostProcess(@RequestParam(value = "businessPostArr[]") List<String> businessPostArr,
									   @RequestParam("mode") String mode) throws Exception{
		BusinessRequest businessRequest = new BusinessRequest();
		businessRequest.setMode(mode);
		businessRequest.setBusinessPostArr(businessPostArr);
		Boolean result = businessService.businessPostProcess(businessRequest);
		
		return result;
	}
	
	@PostMapping("json/businessContentsProcess")
	public Boolean BusinessContentsProcess(@RequestParam(value = "businessContentsArr[]") List<String> businessContentsArr,
										   @RequestParam("mode") String mode) throws Exception{
		BusinessRequest businessRequest = new BusinessRequest();
		businessRequest.setMode(mode);
		businessRequest.setBusinessContentsArr(businessContentsArr);
		
		Boolean result = businessService.businessContentsProcess(businessRequest);
		
		return result;
	}
	
	@GetMapping("json/getBusiness/{businessNo}")
	public Business getBusiness(@PathVariable("businessNo") int businessNo) throws Exception{ 
		return businessService.getBusiness(businessNo);
	}
	
	@GetMapping("json/getBusinessList")
	public List<Business> getBusinessList(@RequestParam(value = "locale", defaultValue = "ko")String locale) throws Exception{
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("locale", locale);
		Map<String, Object> resultMap = businessService.getBusinessList(map);
		List<Business> businessList = (List<Business>) resultMap.get("list");
		return businessList;
	}
	
	@GetMapping(value="json/getBusinessPost/{businessPostNo}")
	public BusinessPost getBusinessPost(@PathVariable("businessPostNo") int businessPostNo)throws Exception{
		return businessService.getBusinessPost(businessPostNo);
	}
	
	@GetMapping(value="json/getBusinessContents/{businessContentsNo}")
	public BusinessContents getBusinessContents(@PathVariable("businessContentsNo") int businessContentsNo) throws Exception{
		return businessService.getBusinessContents(businessContentsNo);
	}
}
