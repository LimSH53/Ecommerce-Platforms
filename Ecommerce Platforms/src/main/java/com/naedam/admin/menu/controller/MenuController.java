package com.naedam.admin.menu.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.naedam.admin.menu.model.service.MenuService;
import com.naedam.admin.menu.model.vo.Bottom;
import com.naedam.admin.menu.model.vo.Head;
import com.naedam.admin.menu.model.vo.Menu;
import com.naedam.admin.menu.model.vo.MenuCategory;



@Controller
@RequestMapping("/admin/menu/*")
public class MenuController {

	@Autowired	
	private MenuService menuService;
	
	/**
	 * 메뉴 관리 => 메뉴 관리 DML 프로세스
	 * @param menu
	 * @param head
	 * @param bottom
	 * @param mode
	 * @param part
	 * @return
	 * @throws Exception
	 */
	@PostMapping("menuProcess")
	public String menuProcess(@ModelAttribute("menu") Menu menu, 
							  @ModelAttribute("head") Head head, 
							  @ModelAttribute("bottom") Bottom bottom,
							  @RequestParam("mode") String mode, 
							  @RequestParam("part") String part) throws Exception{
		
		MenuRequest menuRequest = new MenuRequest();
		menuRequest.setMenu(menu);
		menuRequest.setHead(head);
		menuRequest.setBottm(bottom);
		menuRequest.setMode(mode);
		menuRequest.setPart(part);
		
		return menuService.menuProcess(menuRequest);
	}
	
	@PostMapping("headProcess")
	public String headProcess(@ModelAttribute("head") Head head,
							  @RequestParam("mode") String mode,
							  @RequestParam(value="image", required = false) MultipartFile headImage,
							  @RequestParam(value="imageStatus", required = false) String imageStatus,
							  HttpServletRequest request) throws Exception{
		String filePath = request.getServletContext().getRealPath("resources/user/images/main/");
		
		MenuRequest menuRequest = new MenuRequest();
		
		menuRequest.setHead(head);
		menuRequest.setMode(mode);
		menuRequest.setHeadImage(headImage);
		menuRequest.setFilePath(filePath);
		menuRequest.setImageStatus(imageStatus);
		
		return menuService.headProcess(menuRequest);
	}
	
	/**
	 * 메뉴 관리 => 메뉴 괸리 리스트 조회
	 * @param menu 리스트 조회
	 * list는 ord = 1, list2 = code != 1
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="menu")
	public String listMenu(Menu menu, Model model,
				@RequestParam(value="revision_code", defaultValue = "0") int revision_code,
			    @RequestParam(value="ord", defaultValue = "0") int ord,
			    @RequestParam(value = "locale", defaultValue = "ko") String locale) throws Exception{
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("locale", locale);
		Map<String, Object> resultMap = menuService.getMenuList(map);
		model.addAttribute("list", resultMap.get("list"));
		model.addAttribute("list2", resultMap.get("list2"));
		model.addAttribute("board", resultMap.get("board"));
		model.addAttribute("locale", locale);
		return "admin/menu/menu";
	}
	
	/**
	 * 메뉴 관리 => 메뉴 괸리 리스트 조회
	 * list는 ord = 1, list2 = code != 1 and ord = 3
	 * @param menu
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="menu2")
	public String listMenu2(@ModelAttribute("menu") Menu menu, Model model,
			@RequestParam(value="ord", defaultValue = "0") int ord,
		    @RequestParam(value = "locale", defaultValue = "ko") String locale) throws Exception{
		menu.setOrd(menu.getOrd()+1);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("menu", menu);
		map.put("locale", locale);
		Map<String, Object> resultMap = menuService.getMenuList2(map);
		model.addAttribute("menuData", menuService.getMenu(menu.getCode()));
		model.addAttribute("list", resultMap.get("list"));
		model.addAttribute("list2", resultMap.get("list2"));	
		model.addAttribute("locale", locale);
		return "admin/menu/menuList";
	}	
	
	/**
	 * 메뉴관리 => 메뉴 관리 리스트 조회
	 * @param menu
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="menuList")
	public String menuList(Menu menu, Model model, HttpServletRequest request,
			@RequestParam(value="ord", defaultValue = "0") int ord,
			  @RequestParam(value = "locale", defaultValue = "ko") String locale) throws Exception{
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("locale", locale);
		Map<String, Object> resultMap = menuService.getMenuList(map);
		model.addAttribute("menuData", menuService.getMenu(menu.getCode()));
		model.addAttribute("list", resultMap.get("list"));
		model.addAttribute("list2", resultMap.get("list2"));
		model.addAttribute("locale", locale);
		return "admin/menu/menuList";
	}
	
	/**
	 * iframe tree 리스트 조회
	 * @param model
	 * @param menuCategory
	 * @return
	 * @throws Exception
	 */
	@GetMapping("tree")
	public String tree(Model model, MenuCategory menuCategory,
			@RequestParam(value = "locale", defaultValue = "ko") String locale) throws Exception{
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("locale", locale);
		Map<String, Object> resultMap = menuService.getMenuCategoryList(map);
		model.addAttribute("list", resultMap.get("list"));
		model.addAttribute("list2", resultMap.get("list2"));
		model.addAttribute("list3", resultMap.get("list3"));
		model.addAttribute("locale", locale);
		return "admin/menu/tree";
	}
	
	/**
	 * 메뉴 관리 => 헤드 관리 리스트 조회
	 * @param head
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="headList")
	public String headList(Head head, Model model,
			   @RequestParam(value = "locale", defaultValue = "ko") String locale) throws Exception{
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("locale", locale);
		Map<String, Object> resultMap = menuService.getHeadList(map);
		model.addAttribute("list", resultMap.get("list"));
		model.addAttribute("locale", locale);
		return "admin/menu/head";
	}	
	
	/**
	 * 메뉴 관리 => 하단 관리 리스트 조회
	 * @param model
	 * @param bottom
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="bottomList")
	public String bottomList(Model model, Bottom bottom) throws Exception{
		bottom = menuService.getBottom();
		model.addAttribute("bottom", bottom);
		return "admin/menu/bottom";
	}

}

