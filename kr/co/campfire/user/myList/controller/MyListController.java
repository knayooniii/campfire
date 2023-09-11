package kr.co.campfire.user.myList.controller;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.campfire.common.controller.LoginCheckController;
import kr.co.campfire.common.controller.Pagination;
import kr.co.campfire.common.controller.SessionManageController;
import kr.co.campfire.common.dto.PageInfo;
import kr.co.campfire.user.campSearch.dto.CampSearchDto;
import kr.co.campfire.user.campSearch.service.CampSearchService;
import kr.co.campfire.user.campSearch.service.CampSearchServiceImpl;
import kr.co.campfire.user.myList.dto.MyListDto;
import kr.co.campfire.user.myList.dto.MyBoardListDto;
import kr.co.campfire.user.myList.service.MyListService;
import kr.co.campfire.user.myList.service.MyListServiceImpl;
import kr.co.campfire.user.userBoard.dto.UserBoardDto;
import kr.co.campfire.user.userBoard.service.UserBoardServiceImpl;

@RequestMapping("/myList")
@Controller
public class MyListController {
	@Autowired
	private MyListServiceImpl myListService;
	@Autowired
	private UserBoardServiceImpl userBoardService;
	@Autowired
	private CampSearchServiceImpl campSearchService;
	
	@Autowired
	private LoginCheckController loginCheck;

	@Autowired
	private SessionManageController sessionManage;
	
	@GetMapping("/likeList.do")
	
	public String likeList(
			@RequestParam(value = "cpage", defaultValue = "1") int currentPage,
			HttpSession session, Model model) {
		if (!loginCheck.loginCheck(session)) {
			sessionManage.setSessionMessage("로그인 후 이용할 수 있습니다.", "error", session);

			return "redirect:/";
		}
		int memberNum = (int)session.getAttribute("memberNum");
		int listCount = 0;
		listCount = myListService.selectLikeListCount(memberNum);
		
		// 보여질 페이지수
				int pageLimit = 10;
				// 한페이지에 보여질 게시글 수
				int boardLimit = 4;

				int row = listCount - (currentPage - 1) * boardLimit;

				// 페이징 로직 처리
				PageInfo pi = Pagination.getPageInfo(listCount, currentPage, pageLimit, boardLimit);
		
		
		List<MyListDto> list=myListService.selectLikeList(pi, memberNum);

		for (MyListDto mld : list) {
			int likeCount = campSearchService.selectLikeCount(mld.getCampNum());
			mld.setLikeCamp(likeCount);
			
			List<CampSearchDto> campPhoto = campSearchService.selectCampPhoto(mld.getCampNum());
			CampSearchDto firstCampPhoto = new CampSearchDto();
			if(!campPhoto.isEmpty()) {
				firstCampPhoto = campPhoto.get(0);
				mld.setCampPhotoName(firstCampPhoto.getCampPhotoName());
				mld.setCampPhotoURL(firstCampPhoto.getCampPhotoURL());
			}
		}
		
		model.addAttribute("likeList", list);
		model.addAttribute("pi", pi);
		return "/user/likeList";
	}
	
@GetMapping("/likeBoardList.do")
	
	public String likeBoardList(
			@RequestParam(value = "cpage", defaultValue = "1") int currentPage,
			HttpSession session, Model model) {
	if (!loginCheck.loginCheck(session)) {
		sessionManage.setSessionMessage("로그인 후 이용할 수 있습니다.", "error", session);

		return "redirect:/";
	}
		int memberNum = (int)session.getAttribute("memberNum");
		int listCount = 0;
		listCount = myListService.selectLikeBoardListCount(memberNum);
		
		// 보여질 페이지수
				int pageLimit = 10;
				// 한페이지에 보여질 게시글 수
				int boardLimit = 6;

				int row = listCount - (currentPage - 1) * boardLimit;

				// 페이징 로직 처리
				PageInfo pi = Pagination.getPageInfo(listCount, currentPage, pageLimit, boardLimit);
		
		
		List<MyBoardListDto> list=myListService.selectLikeBoardList(pi, memberNum);

		for (MyBoardListDto item : list) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

			Timestamp postTimestamp = item.getPostCreateDate(); //
			Date postDate = new Date(postTimestamp.getTime());
			String formattedinquiryDate = sdf.format(postDate);
			item.setNewCreateDate(formattedinquiryDate);

			item.setLikeCount(userBoardService.selectLikeCount(item.getPostNum()));

			String postContent = item.getPostContent();

			String imgTag = "<img src=\"";
			int imgTagStart = postContent.indexOf(imgTag);
			if (imgTagStart != -1) {
				int srcStart = imgTagStart + imgTag.length();
				int srcEnd = postContent.indexOf("\"", srcStart);
				if (srcEnd != -1) {
					String src = postContent.substring(srcStart, srcEnd);
					System.out.println("Image Source: " + src);

					String uploadPath = "/resources/upload";
					if (src.startsWith(uploadPath)) {
						String imageName = src.substring(uploadPath.length() + 1);
						System.out.println("Image Name: " + imageName);
						item.setImageName(imageName);
					}
				}
			}

			String input = item.getPostContent();
			StringBuilder result = new StringBuilder();
			boolean withinTag = false;

			for (char c : input.toCharArray()) {
				if (c == '<') {
					withinTag = true;
					continue;
				}
				if (c == '>') {
					withinTag = false;
					continue;
				}
				if (!withinTag) {
					result.append(c);
				}
			}

			String output = result.toString();
			item.setPostContent(output);
			
			item.setReplyCount(userBoardService.selectReplyCount(item.getPostNum()));
		}
		
		model.addAttribute("boardList", list);
		model.addAttribute("pi", pi);
		return "/user/likeBoardList";
	}
	
	@GetMapping("/wishList.do")
	
	public String wishList(
			@RequestParam(value = "cpage", defaultValue = "1") int currentPage,
			HttpSession session, Model model) {
		
		if (!loginCheck.loginCheck(session)) {
			sessionManage.setSessionMessage("로그인 후 이용할 수 있습니다.", "error", session);

			return "redirect:/";
		}
		int memberNum = (int)session.getAttribute("memberNum");
		int listCount = 0;
		listCount = myListService.selectWishListCount(memberNum);
		
		// 보여질 페이지수
				int pageLimit = 10;
				// 한페이지에 보여질 게시글 수
				int boardLimit = 4;

				int row = listCount - (currentPage - 1) * boardLimit;

				// 페이징 로직 처리
				PageInfo pi = Pagination.getPageInfo(listCount, currentPage, pageLimit, boardLimit);
		
		
		List<MyListDto> list=myListService.selectWishList(pi, memberNum);

		for (MyListDto mld : list) {
			int likeCount = campSearchService.selectLikeCount(mld.getCampNum());
			mld.setLikeCamp(likeCount);
			
			List<CampSearchDto> campPhoto = campSearchService.selectCampPhoto(mld.getCampNum());
			CampSearchDto firstCampPhoto = new CampSearchDto();
			if(!campPhoto.isEmpty()) {
				firstCampPhoto = campPhoto.get(0);
				mld.setCampPhotoName(firstCampPhoto.getCampPhotoName());
				mld.setCampPhotoURL(firstCampPhoto.getCampPhotoURL());
			}
		}
		model.addAttribute("pi",pi);
		model.addAttribute("wishList", list);
		return "/user/wishList";
	}
}
