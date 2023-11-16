package com.naedam.admin.menu.model.service;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.naedam.admin.board.model.dao.BoardDao;
import com.naedam.admin.menu.controller.MenuRequest;
import com.naedam.admin.menu.model.dao.MenuDao;
import com.naedam.admin.menu.model.vo.Bottom;
import com.naedam.admin.menu.model.vo.Head;
import com.naedam.admin.menu.model.vo.Menu;

@Service
public class MenuServiceImpl implements MenuService {
	
	@Autowired
	private MenuDao menuDao;
	@Autowired
	private BoardDao boardDao;
	
	//메뉴관리 프로세서
	public String menuProcess(MenuRequest menuRequest) throws Exception{
		String part = menuRequest.getPart();
		String mode = menuRequest.getMode();
		Menu menu = menuRequest.getMenu();
		Head head = menuRequest.getHead();
		Bottom bottom = menuRequest.getBottm();
		List<String> menuArr = menuRequest.getMenuArr();
		
		//part값에 따라 if (menu)
		if("menu".equals(part)) {
			menuPart(mode, menu, menuArr);
			return "redirect:/admin/menu/menu";
		}
		//part값에 따라 if (head)	
		if("head".equals(part)) {
			headPart(mode, head, menuArr);
			return "redirect:/admin/menu/headList";
		}
		//part값에 따라 if (bottom)		
		if("bottom".equals(part)) {
			bottomPart(mode, bottom);
			return "redirect:/admin/menu/bottomList";
		}
		//part값에 따라 if (revision)	
		if("revision".equals(part)) {
			revisionPart(mode, menuArr);
		}
		return null;
	}

	//헤더 프로세스
	public String headProcess(MenuRequest menuRequest) throws Exception{
		String mode = menuRequest.getMode();
		Head head = menuRequest.getHead();
		MultipartFile headImage = menuRequest.getHeadImage();
		File file = new File(menuRequest.getFilePath() + headImage.getOriginalFilename());

		if("insert".equals(mode)) {
			head.setHeadImage(headImage.getOriginalFilename());
			headImage.transferTo(file);
			menuDao.addHead(head);
		}
		
		if("update".equals(mode)) {
			head.setHeadImage(headImage.getOriginalFilename());
			
			if(!headImage.isEmpty()) {
				headImage.transferTo(file);
			}
			menuDao.updateHead(head);
		}
		
		if("delete".equals(mode)) {
			List<Integer> headArr = menuRequest.getMenuArr().stream()
					.map(s -> Integer.parseInt(s))
					.collect(Collectors.toList());
			menuDao.deleteChoiceHead(headArr);
		}
		return "redirect:/admin/menu/headList?locale="+head.getLocale();
	}
	
	//메뉴관리 part == revision일때
		private void revisionPart(String mode, List<String> menuArr) throws NumberFormatException, Exception {
			if("delete".equals(mode)) {
				for(String i : menuArr) {
					menuDao.deleteMenu(Integer.parseInt(i));
				}				
			}
			
			if("update".equals(mode)) {
				for(String i : menuArr) {
					menuDao.updateRevision(Integer.parseInt(i));
				}
			}
			
		}

		//메뉴관리 part == bottom일때
		private void bottomPart(String mode, Bottom bottom) throws Exception {
			if("update".equals(mode)) {
				menuDao.updateBottom(bottom);
			}
			
		}
		//메뉴관리 part == head일때
		private void headPart(String mode, Head head, List<String> menuArr) throws Exception {
			if("insert".equals(mode)) {
				menuDao.addHead(head);
			}
			if("update".equals(mode)) {
				menuDao.updateHead(head);
			}
			
			if("delete".equals(mode)) {
				List<Integer> headArr = menuArr.stream()
								.map(s -> Integer.parseInt(s))
									.collect(Collectors.toList());
				menuDao.deleteChoiceHead(headArr);
			}
			
		}

		//메뉴관리 part == menu일때
		private void menuPart(String mode, Menu menu, List<String> menuArr) throws Exception {
			/* Menu menu = (Menu) map.get("menu"); */
			if("insert".equals(mode)) {
				menuDao.addMenu(menu);
			}
			if("update".equals(mode)) {
				Menu revisionMenu = new Menu();
				revisionMenu = menuDao.getRevision(menu);
				revisionMenu.setOriginNo(menu.getCode());
				menuDao.addRevision(revisionMenu);
				menuDao.updateMenu(menu);
			}
			if("delete".equals(mode)) {
				List<Integer> deletMenuArr = menuArr.stream().map(s -> Integer.parseInt(s)).collect(Collectors.toList());
				menuDao.updateChoiceMenu(deletMenuArr);
			}
			
		}
	
	
	//헤더관리 등록
	@Override
	public int addRevision(Menu menu) throws Exception {
		return menuDao.addRevision(menu);
	}
	
	//메뉴 리스트 출력
	@Override
	public Map<String, Object> getMenuList(Map<String,Object> map) throws Exception{
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("list", menuDao.getMenuList(map));
		resultMap.put("list2", menuDao.getHeadList(map));
		resultMap.put("board", boardDao.getBoardTitle(map));
		return resultMap;
	}
	
	//조건식 메뉴 리스트 출력
	@Override
	public Map<String, Object> getMenuList2(Map<String,Object> map) throws Exception{
		Menu menu = (Menu) map.get("menu");
		Map<String, Object> resultMap = new HashMap<String, Object>();
		if(menu.getOrd() == 1) {
			resultMap.put("list", menuDao.getMenuList2(map));
			resultMap.put("list2", menuDao.getHeadList(map));
		}else if(menu.getOrd() == 2) {
			resultMap.put("list", menuDao.getMenuList3(map));
			resultMap.put("list2", menuDao.getHeadList(map));
		}
		return resultMap;
	}	
	
	//리비젼 리스트
	@Override
	public Map<String, Object> getRevisionList(Map<String, Object> map) throws Exception {
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("list", menuDao.getRevisionList(map));
		
		return resultMap;
	}

	//삭제된 리비젼 리스트
	@Override
	public Map<String, Object> deleteRevisionList(Map<String, Object> map) throws Exception {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("list", menuDao.deleteRevisionList(map));
		
		return resultMap;
	}
	
	//헤더관리 리스트
	@Override
	public Map<String, Object> getHeadList(Map<String, Object> map) throws Exception {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("list", menuDao.getHeadList(map));
		return resultMap;
	}
	
	//헤더관리 리스트
	@Override
	public Map<String, Object> getUserHeadList(Map<String, Object> map) throws Exception {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("list", menuDao.getUserHeadList(map));
		return resultMap;
	}	
	
	//메뉴카테고리 리스트
	@Override
	public Map<String, Object> getMenuCategoryList(Map<String, Object> map) throws Exception {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("list", menuDao.getMenuCategoryList(map));
		resultMap.put("list2", menuDao.getMenuCategoryList2(map));
		return resultMap;
	}
	
	//메뉴카테고리 리스트2
	@Override
	public Map<String, Object> getMenuCategoryList2(Map<String, Object> map) throws Exception {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("list", menuDao.getMenuCategoryList2(map));
		
		return resultMap;
	}	
	
	//메뉴 정보
	@Override
	public Menu getMenu(int code) throws Exception {
		return menuDao.getMenu(code);
	}
	
	//헤더 정보
	@Override
	public Head getHead(int headNo) throws Exception {
		return menuDao.getHead(headNo);
	}
	
	//하단 정보
	@Override
	public Bottom getBottom() throws Exception {
		return menuDao.getBottom();
	}
	
	//리비젼 가져오기
	@Override
	public Menu getRevision(Menu menu) throws Exception {
		return menuDao.getRevision(menu);
	}
	
	//삭제 리비전 복구
	@Override
	public void updateRevision(int code) throws Exception {
		menuDao.updateRevision(code);
	}

	//리비젼의 메뉴 삭제
	@Override
	public void deleteMenu(int code) throws Exception {
		menuDao.deleteMenu(code);
	}

	//메뉴 down순서변경
	@Override
	public void updateDownAsc(Map<String, Object> map) throws Exception {
		menuDao.updateDownAsc(map);
	}

	//메뉴 up순서변경
	@Override
	public void updateUpAsc(Map<String, Object> map) throws Exception {
		menuDao.updateUpAsc(map);
	}
	
	//헤드 down순서변경
	@Override
	public void updateHeadDownAsc(Map<String, Object> map) throws Exception {
		menuDao.updateHeadDownAsc(map);
	}

	//헤드 up순서변경
	@Override
	public void updateHeadUpAsc(Map<String, Object> map) throws Exception {
		menuDao.updateHeadUpAsc(map);
	}	
	
}
