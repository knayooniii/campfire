package kr.co.campfire.user.trading.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import kr.co.campfire.common.controller.UploadFileController;
import kr.co.campfire.common.model.dto.PageInfo;
import kr.co.campfire.common.template.Constants;
import kr.co.campfire.common.template.Pagination;
import kr.co.campfire.user.trading.*;
import kr.co.campfire.user.trading.dto.Category;
import kr.co.campfire.user.trading.dto.File;
import kr.co.campfire.user.trading.dto.Member;
import kr.co.campfire.user.trading.dto.Region;
import kr.co.campfire.user.trading.dto.Trading;
import kr.co.campfire.user.trading.service.TradingServiceImpl;
import kr.co.campfire.chat.model.service.ChatServiceImpl;


@Controller
@RequestMapping("/trading")
public class TradingController {
	
	@Autowired
	private TradingServiceImpl tradingService;
	
	@Autowired
	private ChatServiceImpl chatService;
	
	@Autowired
	private UploadFileController uploadFileController;

	@GetMapping("/list.do")
	public String tradingList(
			@RequestParam(value="currentPage", defaultValue = "1") String currentPage,
			@RequestParam(value="filterCategory1", defaultValue="전체") String filterCategory1,
			@RequestParam(value="filterCategory2", defaultValue="전체") String filterCategory2,
			@RequestParam(value="filterKeyword", defaultValue="") String filterKeyword,
			@RequestParam(value="filterRegion1", defaultValue="전국") String filterRegion1,
			@RequestParam(value="filterRegion2", defaultValue="전 지역") String filterRegion2,
			@RequestParam(value="filterMinPrice", defaultValue="") String filterMinPrice,
			@RequestParam(value="filterMaxPrice", defaultValue="") String filterMaxPrice,
			@RequestParam(value="filterResultMaxNumber", defaultValue="6") int filterResultMaxNumber,
			@RequestParam(value="filterSearchResultsType", defaultValue="grid") String filterSearchResultsType,
			@RequestParam(value="filterShowOnlyMine", defaultValue="false") String filterShowOnlyMine,
			@RequestParam(value="isOpenFilterDrawer", defaultValue="false") String isOpenFilterDrawer,
			@RequestParam(value="scrollX", defaultValue="0") String scrollX,
			@RequestParam(value="scrollY", defaultValue="0") String scrollY,
			Model model, 
			HttpSession session
			) {
		
		System.out.println("currentPage: " + currentPage);
		System.out.println("filterCategory1: " + filterCategory1);
		System.out.println("filterCategory2: " + filterCategory2);
		System.out.println("filterKeyword: " + filterKeyword);
		System.out.println("filterRegion1: " + filterRegion1);
		System.out.println("filterRegion2: " + filterRegion2);
		System.out.println("filterMinPrice: " + filterMinPrice);
		System.out.println("filterMaxPrice: " + filterMaxPrice);
		System.out.println("filterResultMaxNumber: " + filterResultMaxNumber);
		System.out.println("filterSearchResultsType: " + filterSearchResultsType);
		System.out.println("filterShowOnlyMine: " + filterShowOnlyMine);
		System.out.println("isOpenFilterDrawer: " + isOpenFilterDrawer);
		System.out.println("scrollX: " + scrollX);
		System.out.println("scrollY: " + scrollY);
		
		// 로그인 정보
		String sessionMemberIdx = (String) session.getAttribute("sessionMemberIdx");
		System.out.println("sessionMemberIdx: " + sessionMemberIdx);
		
		// 임시 변경
		if(filterMinPrice.equals("")) {
			filterMinPrice = "0";
		}
		if(filterMaxPrice.equals("")) {
			filterMaxPrice = "999999999999";
		}
		if(filterCategory1.equals("전체")) {
			filterCategory1 = "%";
		}
		if(filterCategory2.equals("전체")) {
			filterCategory2 = "%";
		}
		if(filterRegion1.equals("전국")) {
			filterRegion1 = "%";
		}
		if(filterRegion2.equals("전 지역")) {
			filterRegion2 = "%";
		}
		
		// 보여질 페이지 수
		int pageBlockRangeLimit = 5;

		// 한 페이지에 들어갈 게시글 수
		int boardLimit = filterResultMaxNumber;

		// 필터, 회원정보 Map에 모아담기
		HashMap<String, Object> paramMap = new HashMap<>();
		paramMap.put("filterCategory1", filterCategory1);
		paramMap.put("filterCategory2", filterCategory2);
		paramMap.put("filterKeyword", filterKeyword);
		paramMap.put("filterRegion1", filterRegion1);
		paramMap.put("filterRegion2", filterRegion2);
		paramMap.put("filterMinPrice", filterMinPrice);
		paramMap.put("filterMaxPrice", filterMaxPrice);
		paramMap.put("memberIdx", sessionMemberIdx);
		
		System.out.println("(HashMap) filterRegion1: " + paramMap.get("filterRegion1"));
		System.out.println("(HashMap) memberIdx: " + paramMap.get("memberIdx"));
		
		// 전체 게시글 수 구하기
		int listCount;
		if((sessionMemberIdx != null) && (filterShowOnlyMine.equals("true"))) {
			System.out.println("selectListCountInMember(paramMap)");
			listCount = tradingService.selectListCountInMember(paramMap);
		} else {
			listCount = tradingService.selectListCount(paramMap);
		}

		// 페이징 로직 처리
		PageInfo pi = Pagination.getPageInfo(listCount, Integer.parseInt(currentPage), pageBlockRangeLimit, boardLimit);
		System.out.println("(paging) listCount: " + pi.getListCount());
		System.out.println("(paging) boardLimit: " + pi.getBoardLimit());
		System.out.println("(paging) pageCount: " + pi.getPageCount());
		System.out.println("(paging) pageBlockRangeLimit: " + pi.getPageBlockRangeLimit());
		System.out.println("(paging) currentPage: " + pi.getCurrentPage());
		System.out.println("(paging) pageBlockCount: " + pi.getPageBlockCount());
		System.out.println("(paging) currentPageBlock: " + pi.getCurrentPageBlock());
		System.out.println("(paging) startPage: " + pi.getStartPage());
		System.out.println("(paging) endPage: " + pi.getEndPage());
		System.out.println("(paging) startRow: " + pi.getStartRow());
		System.out.println("(paging) endRow: " + pi.getEndRow());
		System.out.println("(paging) firstPageOfPreviousBlock: " + pi.getFirstPageOfPreviousBlock());
		System.out.println("(paging) firstPageOfNextBlock: " + pi.getFirstPageOfNextBlock());
		
		// 가져올 레코드의 범위
		paramMap.put("startRow", pi.getStartRow());
		paramMap.put("endRow", pi.getEndRow());
			
		// '리스트로 가져오기'
		List<Trading> list;
		if((sessionMemberIdx != null) && (filterShowOnlyMine.equals("true"))) {
			System.out.println("selectListAllInMember(paramMap)");
			list = tradingService.selectListAllInMember(paramMap);
		} else {
			list = tradingService.selectListAll(paramMap);
		}
		System.out.println("(list) --- logging start ---");
		for(Trading element : list) {
			System.out.println("(list) idx: " + element.getIdx());
			System.out.println("(list) memberIdx: " + element.getMemberIdx());
			System.out.println("(list) categoryIdx: " + element.getCategoryIdx());
			System.out.println("(list) regionIdx: " + element.getRegionIdx());
			System.out.println("(list) title: " + element.getTitle());
			System.out.println("(list) content: " + element.getContent());
			System.out.println("(list) price: " + element.getPrice());
			System.out.println("(list) createdDate: " + element.getCreatedDate());
			//System.out.println("(list) isSold: " + element.isIsSold());
		}
		System.out.println("(list) --- logging end ---");

		// 게시글에에 하나씩 대응되는 사진 리스트, 카테고리, 지역
		List<List<File>> listOfFileList = new ArrayList<List<File>>();
		List<Category> listOfCategory = new ArrayList<Category>();
		List<Region> listOfRegion = new ArrayList<Region>();
		for(Trading element : list) {	
			List<File> tmpFileList = tradingService.selectFileAll(element.getIdx());
			listOfFileList.add(tmpFileList);
			
			Category tmpCategory = tradingService.selectCategory(element.getCategoryIdx());
			listOfCategory.add(tmpCategory);
			
			Region tmpRegion = tradingService.selectRegion(element.getRegionIdx());
			listOfRegion.add(tmpRegion);
		}
		
		// 년월일까지만 출력될 수 있도록 문자열 자르기
		// for (Board item : list) {
		// 	item.setIndate(item.getIndate().substring(0, 10));
		// }

		model.addAttribute("currentPage", currentPage);
		
		if(filterCategory1.equals("%")) {
			filterCategory1 = "전체";
		}
		if(filterCategory2.equals("%")) {
			filterCategory2 = "전체";
		}
		if(filterRegion1.equals("%")) {
			filterRegion1 = "전국";
		}
		if(filterRegion2.equals("%")) {
			filterRegion2 = "전 지역";
		}
		model.addAttribute("filterCategory1", filterCategory1);
		model.addAttribute("filterCategory2", filterCategory2);
		model.addAttribute("filterRegion1", filterRegion1);
		model.addAttribute("filterRegion2", filterRegion2);
		
		model.addAttribute("filterKeyword", filterKeyword);
		
		if(filterMinPrice.equals("0")) {
			filterMinPrice = "";
		}
		if(filterMaxPrice.equals("999999999999")) {
			filterMaxPrice = "";
		}
		model.addAttribute("filterMinPrice", filterMinPrice);
		model.addAttribute("filterMaxPrice", filterMaxPrice);
		
		model.addAttribute("filterResultMaxNumber", filterResultMaxNumber);
		model.addAttribute("filterSearchResultsType", filterSearchResultsType);
		model.addAttribute("filterShowOnlyMine", filterShowOnlyMine);
		model.addAttribute("isOpenFilterDrawer", isOpenFilterDrawer);
		model.addAttribute("sessionMemberIdx", sessionMemberIdx);
		model.addAttribute("listCount", listCount);
		model.addAttribute("listOfCategory", listOfCategory);
		model.addAttribute("listOfRegion", listOfRegion);
		model.addAttribute("list", list);
		model.addAttribute("listOfFileList", listOfFileList);
		for(int i=0; i<listOfFileList.size(); i++) {
			System.out.println("category: " + listOfCategory.get(i).getCategoryName1() + " " + listOfCategory.get(i).getCategoryName2());
			System.out.println("region: " + listOfRegion.get(i).getRegionName1() + " " + listOfRegion.get(i).getRegionName2());
			for(File secondDepth : listOfFileList.get(i)) {
				/* !!! */ secondDepth.setUploadPath("resources/upload/");
				
				System.out.println("(file) idx: " + secondDepth.getIdx());
				System.out.println("(file) tradingIdx: " + secondDepth.getTradingIdx());
				System.out.println("(file) uploadPath: " + secondDepth.getUploadPath());
				System.out.println("(file) uploadName: " + secondDepth.getUploadName());
				System.out.println("(file) uploadOriginName: " + secondDepth.getUploadOriginName());
			}
		}

		model.addAttribute("scrollX", Integer.parseInt(scrollX));
		model.addAttribute("scrollY", Integer.parseInt(scrollY));
		model.addAttribute("pi", pi); //PageInfo
		
		// 상품 필터 문자열 만들기
        NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.US);
		String filterInfoText = "";
		if(!filterRegion1.equals("전국")) {
			filterInfoText += filterRegion1;
			
			if(!filterRegion2.equals("전 지역")) {
				filterInfoText += " " + filterRegion2;
			}
			filterInfoText += "의 ";
		}
		
		if(filterCategory1.equals("전체")) {
			filterInfoText += "모든 상품";
		} else {
			filterInfoText += filterCategory1;
			
			if(!filterCategory2.equals("전체")) {
				filterInfoText += " > " + filterCategory2;
			}
		}
		if(!(filterKeyword.equals("")&& filterMinPrice.equals("") && filterMaxPrice.equals(""))) {
			filterInfoText += " (";
			
			if(!filterKeyword.equals("")) {
				filterInfoText += "키워드: '" + filterKeyword + "'";
			}
			if(!(filterMinPrice.equals("") && filterMaxPrice.equals(""))) {
				if(!filterKeyword.equals("")) {
					filterInfoText += ", ";
				}
				
				filterInfoText += "가격: ";
				
				if(!filterMinPrice.equals("")) {
					filterInfoText += numberFormat.format(Integer.parseInt(filterMinPrice)) + "₩ 이상";
				}
				if(!filterMaxPrice.equals("")) {
					filterInfoText += numberFormat.format(Integer.parseInt(filterMaxPrice)) + "₩ 이하";
				}
			}

			
			filterInfoText += ")";
		}


		model.addAttribute("filterInfoText", filterInfoText);

		return "user/trade_board";
	}

	@ResponseBody
	@PostMapping(value = "/filterVerify.do", produces = "text/plain;charset=UTF-8")
	public String filterVerify(
			HttpServletRequest request
			) {
		System.out.println("(filterVerify.do) minPrice: " + request.getParameter("minPrice"));
		System.out.println("(filterVerify.do) maxPrice: " + request.getParameter("maxPrice"));
		
		List<String> listOfPrice = new ArrayList<String>();
		String minPrice = request.getParameter("minPrice");
		String maxPrice = request.getParameter("maxPrice");
		
		listOfPrice.add(minPrice);
		listOfPrice.add(maxPrice);
		
		int[] isNumber = {0, 0};
		
		for(int i = 0; i < listOfPrice.size(); i++) {
			if((listOfPrice.get(i) != null) && (!listOfPrice.get(i).equals(""))) {		
				try {
					int number = Integer.parseInt(listOfPrice.get(i));
					if (number >= 0) {
						isNumber[i] = 1;
					} else {
					    return "가격은 0이상의 정수로만 설정할 수 있어요.";
					    }
					} catch (NumberFormatException e) {
					    return "가격은 0이상의 정수로만 설정할 수 있어요.";
					}
			}
		}
		
		if((isNumber[0] == 1)&&(isNumber[1] == 1)&&(!minPrice.equals(""))&&(!maxPrice.equals(""))) {
			if(Integer.parseInt(maxPrice) < Integer.parseInt(minPrice)) {
				return "최소 가격을 최대 가격보다 낮게 설정해주세요.";
			}
		}
		
		
		return "";
	}
	
	@GetMapping("/enrollForm.do")
	public String enrollForm(
			Model model, 
			HttpSession session
			) {
		model.addAttribute("filterCategory1", "전체");
		model.addAttribute("filterCategory2", "전체");
		model.addAttribute("filterRegion1", "전국");
		model.addAttribute("filterRegion2", "전 지역");
		
		return "user/enroll_product";
	}

	@ResponseBody // 이 어노테이션이 있으면, return값을 view resolver를 거쳐 jsp를 가는게 아니라, 그냥 클라이언트에게 직통으로 문자열을 쏴준다.
	// 게시글 게시 (삽입)
	@PostMapping(value = "/insert.do", produces = "text/plain;charset=UTF-8")
	public String insert(
			HttpServletRequest request,
			@RequestParam(value = "filesArr", required = false) List<MultipartFile> filesArr,
			Model model, 
			HttpSession session
			) throws UnsupportedEncodingException {

		String sessionMemberIdx = (String) session.getAttribute("sessionMemberIdx");
		if(sessionMemberIdx == null) {
			return "세션이 만료되었어요. 다시 로그인해 주세요";
		}
		
		request.setCharacterEncoding("UTF-8");
        String category1 = request.getParameter("category1");
        String category2 = request.getParameter("category2");
        String region1 = request.getParameter("region1");
        String region2 = request.getParameter("region2");
        String price = request.getParameter("price");
        String title = request.getParameter("title");
        String content = request.getParameter("content");
        
		System.out.println("category1: " + category1);
		System.out.println("category2: " + category2);
		System.out.println("region1: " + region1);
		System.out.println("region2: " + region2);
		System.out.println("price: " + price);
		System.out.println("title: " + title);
		System.out.println("content: " + content);
		if ((filesArr != null) && (filesArr.size() != 0)) {
	        for (MultipartFile file : filesArr) {
	            String fileName = file.getOriginalFilename();
	            String contentType = file.getContentType();
	            long fileSize = file.getSize();
	            
	            System.out.println("File Name: " + fileName);
	            System.out.println("Content Type: " + contentType);
	            System.out.println("File Size: " + fileSize);
	        }
	        
		} else {
			return "사진을 1장 이상 올려야 해요. (최대 5장)";
		}
		
		if(price != null) {		
			if(price.equals("")) {
				return "가격란이 비어있어요.";
			}
			try {
				int number = Integer.parseInt(price);
				if (number >= 0) {
					// 아무동작없음
				} else {
				    return "가격은 0이상의 정수로만 설정할 수 있어요.";
				    }
				} catch (NumberFormatException e) {
				    return "가격은 0이상의 정수로만 설정할 수 있어요.";
				}
		}
		
		if(title == null || title.equals("")) {
			return "제목란이 비어있어요.";
		}
		
		if(content == null || content.equals("")) {
			return "본문란이 비어있어요.";
		}
		
		Trading trading = new Trading();
		trading.setMemberIdx(Integer.parseInt(sessionMemberIdx));
		HashMap<String, Object> paramMap = new HashMap<>();
		paramMap.put("category1", category1);
		paramMap.put("category2", category2);
		paramMap.put("region1", region1);
		paramMap.put("region2", region2);
		trading.setCategoryIdx(tradingService.selectCategoryIdx(paramMap));
		trading.setRegionIdx(tradingService.selectRegionIdx(paramMap));
		trading.setTitle(title);
		trading.setContent(content);
		trading.setPrice(Integer.parseInt(price));
		
		tradingService.insertTrading(trading);
		List<File> listOfFile = uploadFileController.setUploadInfo(trading.getIdx(), filesArr);
		for(File file : listOfFile) {
			
			System.out.println("(insert file) trading idx: " + file.getIdx());
			
			tradingService.insertFile(file);
			
			System.out.println("(insert file) origin name: " + file.getUploadOriginName());
			System.out.println("(insert file) upload name: " + file.getUploadName());
		}
		try {
			uploadFileController.uploadFile(listOfFile, filesArr);
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
		}
		
		session.setAttribute("tw_msg", "게시글이 등록되었어요.");
		session.setAttribute("tw_status", "success");
		return "";
	}
	
	@ResponseBody 
	@PostMapping("/alert.do")
	public String alert(
			HttpSession session
			) {
		String msg = (String) session.getAttribute("tw_msg");
		String status = (String) session.getAttribute("tw_status");
		
        if (msg == null || msg.equals("")) {
        	System.out.println("(alert) false");
            return "false";
        }
        
        session.removeAttribute("tw_msg");
        session.removeAttribute("tw_status");
        System.out.println("(alert) true");
		return "true";
	}
	
	@GetMapping("/detail.do")
	public String detail(
			@RequestParam(value="tradingIdx") String tradingIdx,
			@RequestParam(value="chatDrawerOn", defaultValue="false") String chatDrawerOn,
			Model model, 
			HttpSession session
			) {
		
		// 로그인 정보
		String sessionMemberIdx = (String) session.getAttribute("sessionMemberIdx");
		model.addAttribute("sessionMemberIdx", sessionMemberIdx); // 로그인 정보
		System.out.println("(detail.do) sessionMemberIdx: " + sessionMemberIdx);
		
		Trading trading = tradingService.selectTrading(Integer.parseInt(tradingIdx));
		List<File> listOfFile = tradingService.selectFileAll(Integer.parseInt(tradingIdx));
		Category category = tradingService.selectCategory(trading.getCategoryIdx());
		Region region = tradingService.selectRegion(trading.getRegionIdx());
		Member member = tradingService.selectMember(trading.getMemberIdx());
		
		model.addAttribute("trading", trading); // 거래 idx
		System.out.println("(list) idx: " + trading.getIdx());
		System.out.println("(list) memberIdx: " + trading.getMemberIdx());
		System.out.println("(list) categoryIdx: " + trading.getCategoryIdx());
		System.out.println("(list) regionIdx: " + trading.getRegionIdx());
		System.out.println("(list) title: " + trading.getTitle());
		System.out.println("(list) content: " + trading.getContent());
		System.out.println("(list) price: " + trading.getPrice());
		System.out.println("(list) createdDate: " + trading.getCreatedDate());
		for (File element : listOfFile) {
			/* !!! */element.setUploadPath("resources/upload/");
			
			System.out.println("(file) idx: " + element.getIdx());
			System.out.println("(file) tradingIdx: " + element.getTradingIdx());
			System.out.println("(file) uploadPath: " + element.getUploadPath());
			System.out.println("(file) uploadName: " + element.getUploadName());
			System.out.println("(file) uploadOriginName: " + element.getUploadOriginName());
		}
		model.addAttribute("category", category); // 카테고리
		model.addAttribute("region", region); // 지역
		System.out.println("category: " + category.getCategoryName1() + " " + category.getCategoryName2());
		System.out.println("region: " + region.getRegionName1() + " " + region.getRegionName2());
		
		model.addAttribute("member", member);
		System.out.println("member name: " + member.getName());
		
		model.addAttribute("listOfFile", listOfFile); // 사진 리스트
		
		int descPositionOnPost = tradingService.selectDescPositionOnPost(Integer.parseInt(tradingIdx));
		int backPageNumber = Pagination.getCurrentPage(descPositionOnPost, 6);
				
		model.addAttribute("backPageNumber", backPageNumber);
		
		if(sessionMemberIdx != null ) {
			System.out.println("tradingIdx: " + tradingIdx);
			System.out.println("sessionMemberIdx: " + sessionMemberIdx);
			
			if(chatService.selectChatRoomIdxCount(Integer.parseInt(tradingIdx), Integer.parseInt(sessionMemberIdx)) != 0) {
				int cidx = chatService.selectChatRoomIdx(Integer.parseInt(tradingIdx), Integer.parseInt(sessionMemberIdx));
				model.addAttribute("chatRoomIdx", cidx);
				System.out.println("chatRoomIdx: " + Integer.toString(cidx));
			} else {
				System.out.println("chatRoom doesnt exist");
			}
			
		}
		
		if(chatDrawerOn.equals("true")) {
			model.addAttribute("chatDrawerOn", chatDrawerOn);
		}
		
		
		return "user/product_detail";
	}
	
	@ResponseBody
	@PostMapping(value = "/checkSold.do", produces = "text/plain;charset=UTF-8")
	public String checkChatAndSold(
			HttpServletRequest request,
			HttpSession session
			) {

        String tradingIdx = request.getParameter("tradingIdx");
        
        String isSold = tradingService.checkSold(Integer.parseInt(tradingIdx));
        System.out.println("(checkSold) isSold: " + isSold);
        
        if(isSold.equals("1")) {
    		session.setAttribute("tw_msg", "방금 해당 상품이 판매완료 되었어요.");
    		session.setAttribute("tw_status", "info");
    		return "sold";
        }

		
		return "";
	}
	
	// 게시글 삭제
	@ResponseBody
	@PostMapping(value = "/delete.do", produces = "text/plain;charset=UTF-8")
	public String delete(
			HttpServletRequest request,
			HttpSession session
			) {

        String tradingIdx = request.getParameter("tradingIdx");
        
        Trading trading = tradingService.selectTrading(Integer.parseInt(tradingIdx));
        
        String memberIdx = Integer.toString(trading.getMemberIdx()); 
        System.out.println("(delete) memberIdx of trading: " + memberIdx);
        
		String sessionMemberIdx = (String) session.getAttribute("sessionMemberIdx");
		System.out.println("(delete) sessionMemberIdx: " + sessionMemberIdx);
		
		if(sessionMemberIdx == null) {
			return "세션이 만료되었어요. 다시 로그인해 주세요";
		}
		
		if(!sessionMemberIdx.equals(memberIdx)) {
			return "작성자만 글을 삭제할 수 있어요.";
		}
		
		//채팅 관련 삭제
		List<Integer> chatRoomIdxList = chatService.listOfChatRoomIdx(Integer.parseInt(tradingIdx));
		for(int chatRoomIdx : chatRoomIdxList) {
			chatService.deleteChatMessages(chatRoomIdx);
			chatService.deleteChatRoom(chatRoomIdx);
		}
		
		tradingService.deleteFiles(Integer.parseInt(tradingIdx));
		tradingService.deleteTrading(Integer.parseInt(tradingIdx));
		
		session.setAttribute("tw_msg", "게시글을 삭제했어요.");
		session.setAttribute("tw_status", "success");
		
		return "";
		
	}
	
	// 게시글 수정
	@GetMapping("modifyForm.do")
	public String modifyForm(
			@RequestParam(value="tradingIdx") String tradingIdx,
			@RequestParam(value = "filesArr", required = false) List<MultipartFile> filesArr,
			Model model
			) {
		Trading trading = tradingService.selectTrading(Integer.parseInt(tradingIdx));
		List<File> listOfFile = tradingService.selectFileAll(Integer.parseInt(tradingIdx));
		Category category = tradingService.selectCategory(trading.getCategoryIdx());
		Region region = tradingService.selectRegion(trading.getRegionIdx());
		Member member = tradingService.selectMember(trading.getMemberIdx());
		
		model.addAttribute("trading", trading); // 거래 idx
		model.addAttribute("category", category); // 카테고리
		model.addAttribute("region", region); // 지역
		model.addAttribute("member", member);
		model.addAttribute("listOfFile", listOfFile); // 사진 리스트
		
		for(File item : listOfFile) {
			System.out.println("(listOfFile) uploadName: " + item.getUploadName());
			System.out.println("(listOfFile) uploadOriginName: " + item.getUploadOriginName());
		}
		
		model.addAttribute("filterCategory1", category.getCategoryName1());
		model.addAttribute("filterCategory2", category.getCategoryName2());
		model.addAttribute("filterRegion1", region.getRegionName1());
		model.addAttribute("filterRegion2", region.getRegionName2());
		
		return "user/modify_product";
	}
	
	
	@ResponseBody
	@PostMapping(value = "/modify.do", produces = "text/plain;charset=UTF-8")
	public String modify(
			HttpServletRequest request,
			@RequestParam(value = "filesArr", required = false) List<MultipartFile> filesArr,
			@RequestParam(value = "oldFileIdxArr", required = false) List<Integer> oldFileIdxArr,
			Model model, 
			HttpSession session
			) throws UnsupportedEncodingException {

        String tradingIdx = request.getParameter("tradingIdx");
        Trading trading = tradingService.selectTrading(Integer.parseInt(tradingIdx));
        trading.setIdx(Integer.parseInt(tradingIdx));
        
		String sessionMemberIdx = (String) session.getAttribute("sessionMemberIdx");
		if(sessionMemberIdx == null) {
			return "세션이 만료되었어요. 다시 로그인해 주세요";
		}
		if(!sessionMemberIdx.equals(Integer.toString(trading.getMemberIdx()))) {
			return "작성자만 글을 수정할 수 있어요.";
		}
		
		request.setCharacterEncoding("UTF-8");
		
        String category1 = request.getParameter("category1");
        String category2 = request.getParameter("category2");
        String region1 = request.getParameter("region1");
        String region2 = request.getParameter("region2");
        String price = request.getParameter("price");
        String title = request.getParameter("title");
        String content = request.getParameter("content");

        
		System.out.println("category1: " + category1);
		System.out.println("category2: " + category2);
		System.out.println("region1: " + region1);
		System.out.println("region2: " + region2);
		System.out.println("price: " + price);
		System.out.println("title: " + title);
		System.out.println("content: " + content);
		
		// 파일 갯수
		int oldFileCount;
		int newFileCount;
		
		if((oldFileIdxArr == null) || (oldFileIdxArr.size() == 0)) {
			oldFileCount = 0;
		} else {
			oldFileCount = oldFileIdxArr.size();
		}
		if((filesArr == null) || (filesArr.size() == 0)) {
			newFileCount = 0;
		} else {
			newFileCount = filesArr.size();
		}
		
		if(oldFileCount + newFileCount < 1) {
			return "사진을 1장 이상 올려야 해요. (최대 5장)";
		}
		System.out.println("oldFileCount: " + String.valueOf(oldFileCount));
		System.out.println("newFileCount: " + String.valueOf(newFileCount));
		
		if ((filesArr != null) && (filesArr.size() != 0)) {
	        for (MultipartFile file : filesArr) {
	            String fileName = file.getOriginalFilename();
	            String contentType = file.getContentType();
	            long fileSize = file.getSize();
	            
	            System.out.println("File Name: " + fileName);
	            System.out.println("Content Type: " + contentType);
	            System.out.println("File Size: " + fileSize);
	        }
		}
		
		if(price != null) {		
			if(price.equals("")) {
				return "가격란이 비어있어요.";
			}
			try {
				int number = Integer.parseInt(price);
				if (number >= 0) {
					// 아무동작없음
				} else {
				    return "가격은 0이상의 정수로만 설정할 수 있어요.";
				    }
				} catch (NumberFormatException e) {
				    return "가격은 0이상의 정수로만 설정할 수 있어요.";
				}
		}
		
		if(title == null || title.equals("")) {
			return "제목란이 비어있어요.";
		}
		
		if(content == null || content.equals("")) {
			return "본문란이 비어있어요.";
		}
		
		//Trading trading = new Trading();
		//trading.setMemberIdx(Integer.parseInt(sessionMemberIdx));
		HashMap<String, Object> paramMap = new HashMap<>();
		paramMap.put("category1", category1);
		paramMap.put("category2", category2);
		paramMap.put("region1", region1);
		paramMap.put("region2", region2);
		trading.setCategoryIdx(tradingService.selectCategoryIdx(paramMap));
		trading.setRegionIdx(tradingService.selectRegionIdx(paramMap));
		trading.setTitle(title);
		trading.setContent(content);
		trading.setPrice(Integer.parseInt(price));
		
		tradingService.updateTrading(trading);
		
		// 살아남지 못한 기존 파일 삭제
		if(!(oldFileIdxArr == null || oldFileIdxArr.size() == 0)) {
			List<Integer> dbFileIdxArr = tradingService.selectFileIndexes(Integer.parseInt(tradingIdx));
			
			for(int dbFileIdx : dbFileIdxArr) {
				boolean survival = false;
				
				for(int oldFileIdx : oldFileIdxArr) {
					if(dbFileIdx == oldFileIdx) {
						survival = true;
					}
				}
				if(survival == false) {
					tradingService.deleteFile(dbFileIdx);
					System.out.println("(delete db file) idx: " + Integer.toString(dbFileIdx));
				}	
			}
		} else {
			List<Integer> dbFileIdxArr = tradingService.selectFileIndexes(Integer.parseInt(tradingIdx));
			for(int dbFileIdx : dbFileIdxArr) {
				tradingService.deleteFile(dbFileIdx);
				System.out.println("(delete db file) idx: " + Integer.toString(dbFileIdx));
			}
		}

		// 새 파일 업로드
		if(!(filesArr == null || filesArr.size() == 0)) {
			List<File> listOfFile = uploadFileController.setUploadInfo(trading.getIdx(), filesArr);
			for(File file : listOfFile) {
				
				System.out.println("(insert file) trading idx: " + file.getTradingIdx());
				
				tradingService.insertFile(file);
				
				System.out.println("(insert file) origin name: " + file.getUploadOriginName());
				System.out.println("(insert file) upload name: " + file.getUploadName());
			}
			try {
				uploadFileController.uploadFile(listOfFile, filesArr);
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
			}
		}

		
		session.setAttribute("tw_msg", "게시글을 수정했어요.");
		session.setAttribute("tw_status", "success");
		
		return "";
		
	}
	
	// 게시글 판매완료 <-> 판매미완료로 전환
	@ResponseBody
	@PostMapping(value = "/soldToggle.do", produces = "text/plain;charset=UTF-8")
	public String soldToggle(
			HttpServletRequest request,
			HttpSession session
			) {

        String tradingIdx = request.getParameter("tradingIdx");
        String toggleStatus = request.getParameter("toggleStatus");

        tradingService.soldToggle(Integer.parseInt(tradingIdx), Integer.parseInt(toggleStatus));
        
        if(toggleStatus.equals("1")) {
    		session.setAttribute("tw_msg", "거래를 완료했어요.");
    		session.setAttribute("tw_status", "success");
            
        } else {
    		session.setAttribute("tw_msg", "거래 완료를 해제했어요.");
    		session.setAttribute("tw_status", "success");
        }
        return "";
        
//        int isToggleComplete = tradingService.soldToggle(Integer.parseInt(tradingIdx));
//
//        if(isToggleComplete == 1) {
//        	return "";
//        } else {
//        	return "데이터베이스 에러";
//        }
	}
}
