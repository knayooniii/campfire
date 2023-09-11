package kr.co.campfire.user.userBoard.controller;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import kr.co.campfire.common.controller.DataValidationController;
import kr.co.campfire.common.controller.LoginCheckController;
import kr.co.campfire.common.controller.Pagination;
import kr.co.campfire.common.controller.SessionManageController;
import kr.co.campfire.common.dto.PageInfo;
import kr.co.campfire.user.userBoard.dto.SmarteditorDto;
import kr.co.campfire.user.userBoard.dto.UserBoardDto;
import kr.co.campfire.user.userBoard.dto.UserBoardLikeDto;
import kr.co.campfire.user.userBoard.dto.UserBoardReplyDto;
import kr.co.campfire.user.userBoard.service.UserBoardServiceImpl;

@Controller
@RequestMapping("/user/board")
public class UserBoardController {

	@Autowired
	private UserBoardServiceImpl userBoardService;

	@Autowired
	private DataValidationController dataValidation;

	@Autowired
	private LoginCheckController loginCheck;

	@Autowired
	private SessionManageController sessionManage;

	@RequestMapping("/showBoardList.do")
	public String showRecommendBoard(@RequestParam(value = "postCategory", defaultValue = "") String postCategory,
			@RequestParam(value = "searchCtg", defaultValue = "") String searchCtg,
			@RequestParam(value = "searchTxt", defaultValue = "") String searchTxt,
			@RequestParam(value = "cpage", defaultValue = "1") int currentPage, HttpSession session, Model model) {

		UserBoardDto ubd = new UserBoardDto();
		ubd.setPostCategory(postCategory);
		ubd.setSearchCtg(searchCtg);
		ubd.setSearchTxt(searchTxt);

		// 전체 게시글 수 구하기
		int listCount = 0;
		if (searchCtg.equals("")) {
			listCount = userBoardService.selectListAllCount(ubd);
		} else if (searchCtg.equals("title")) {
			listCount = userBoardService.selectListTitleCount(ubd);
		} else if (searchCtg.equals("context")) {
			listCount = userBoardService.selectListContextCount(ubd);
		} else if (searchCtg.equals("writer")) {
			listCount = userBoardService.selectListWriterCount(ubd);
		}
		// 보여질 페이지 수
		int pageLimit = 10;
		System.out.println(listCount);
		// 한 페이지에 보여질 게시글 수
		int boardLimit = 6;

		// 페이징 로직 처리
		PageInfo pi = Pagination.getPageInfo(listCount, currentPage, pageLimit, boardLimit);
		// 목록 불러오기

		List<UserBoardDto> list = null;
		if (searchCtg.equals("")) {
			list = userBoardService.selectListAll(pi, ubd);
		} else if (searchCtg.equals("title")) {
			list = userBoardService.selectListTitle(pi, ubd);
		} else if (searchCtg.equals("context")) {
			list = userBoardService.selectListContext(pi, ubd);
		} else if (searchCtg.equals("writer")) {
			list = userBoardService.selectListWriter(pi, ubd);
		}
		for (UserBoardDto item : list) {
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

		List<UserBoardDto> popularList = userBoardService.selectListPopular(ubd);

		for (UserBoardDto item : popularList) {
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
		}

		model.addAttribute("boardList", list);
		model.addAttribute("boardPopularList", popularList);
		model.addAttribute("postCategory", postCategory);
		model.addAttribute("pi", pi);

		model.addAttribute("msg", (String) session.getAttribute("msg"));
		model.addAttribute("status", (String) session.getAttribute("status"));

		session.removeAttribute("msg");
		session.removeAttribute("status");

		return "user/userBoard";
	}

	@RequestMapping("/showBoardDetail.do")
	public String showBoardDetail(@RequestParam(value = "postNum", defaultValue = "-1") int postNum,
			HttpSession session, Model model) {
		if (!loginCheck.loginCheck(session)) {
			sessionManage.setSessionMessage("로그인 후 이용할 수 있습니다.", "error", session);

			return "redirect:/user/board/showBoardList.do";
		} else {
			UserBoardDto ubd = userBoardService.selectPost(postNum);

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

			Timestamp postTimestamp = ubd.getPostCreateDate(); //
			Date postDate = new Date(postTimestamp.getTime());
			String formattedinquiryDate = sdf.format(postDate);
			ubd.setNewCreateDate(formattedinquiryDate);

			// 좋아요 카운트 가져오기
			int likeCount = userBoardService.selectLikeCount(postNum);
			// 현재 로그인한 사용자가 좋아요 했는지 구별
			UserBoardLikeDto ubld = new UserBoardLikeDto();

			ubld.setPostNum(postNum);
			int memberNum = (int) session.getAttribute("memberNum");
			ubld.setMemberNum(memberNum);

			int ckeckLike = userBoardService.selectCheckLike(ubld);

			int newViewCount = ubd.getPostViewCount() + 1;
			ubd.setPostViewCount(newViewCount);

			userBoardService.updateViewCount(ubd);

			List<UserBoardReplyDto> replyList = userBoardService.selectPostReply(postNum);

			for (UserBoardReplyDto item : replyList) {
				Date replyDate = item.getPostReplyCreateDate();
				Date currentTime = new Date();
				long timeDiff = currentTime.getTime() - replyDate.getTime();
				long seconds = timeDiff / 1000;

				String newDate;
				if (seconds < 60) {
					newDate = seconds + "초 전";
				} else if (seconds < 3600) {
					long minutes = seconds / 60;
					newDate = minutes + "분 전";
				} else if (seconds < 86400) {
					long hours = seconds / 3600;
					newDate = hours + "시간 전";
				} else {
					long days = seconds / 86400;
					newDate = days + "일 전";
				}
				item.setNewDate(newDate);

				int replyLikeCount = userBoardService.selectReplyLikeCount(item.getPostReplyNum());
				item.setReplyLikeCount(replyLikeCount);
			}

			model.addAttribute("board", ubd);
			// 좋아요 수 바인딩
			model.addAttribute("likeCount", likeCount);
			// 좋아요 체크 바인딩(1 또는 0)
			model.addAttribute("ckeckLike", ckeckLike);

			model.addAttribute("replyList", replyList);

			model.addAttribute("msg", (String) session.getAttribute("msg"));
			model.addAttribute("status", (String) session.getAttribute("status"));

			session.removeAttribute("msg");
			session.removeAttribute("status");

			return "user/userBoardDetail";
		}
	}

	@RequestMapping("/simpleImageUploader.do")
	public String simpleImageUploader(HttpServletRequest req, SmarteditorDto smarteditorDto)
			throws UnsupportedEncodingException {

		String callback = smarteditorDto.getCallback();
		String callback_func = smarteditorDto.getCallback_func();
		String file_result = "";
		String result = "";

		MultipartFile multiFile = smarteditorDto.getFiledata();

		try {
			if (multiFile != null && !multiFile.isEmpty()
					&& multiFile.getContentType().toLowerCase().startsWith("image/")) {
				String oriName = multiFile.getOriginalFilename(); // Changed from getName()
				String uploadPath = req.getSession().getServletContext().getRealPath("/img");
				String path = uploadPath + "/smarteditor/";
				File file = new File(path);
				if (!file.exists()) {
					file.mkdirs();
				}
				String fileName = UUID.randomUUID().toString() + ".jpg"; // Added file extension
				File uploadedFile = new File(path + fileName);
				multiFile.transferTo(uploadedFile);
				file_result += "&bNewLine=true&sFileName=" + URLEncoder.encode(oriName, "UTF-8")
						+ "&sFileURL=/img/smarteditor/" + fileName;
			} else {
				file_result += "&errstr=error";
			}
		} catch (IOException e) {
			e.printStackTrace();
			file_result += "&errstr=error";
		}

		result = "redirect:" + callback + "?callback_func=" + URLEncoder.encode(callback_func, "UTF-8") + file_result;
		return result;

	}

	@RequestMapping("/showEnrollBoard.do")
	public String showEnrollBoard(HttpSession session, Model model) {
		if (!loginCheck.loginCheck(session)) {
			sessionManage.setSessionMessage("로그인 후 이용할 수 있습니다.", "error", session);

			return "redirect:/user/board/showBoardList.do";
		} else {
			model.addAttribute("msg", (String) session.getAttribute("msg"));
			model.addAttribute("status", (String) session.getAttribute("status"));

			session.removeAttribute("msg");
			session.removeAttribute("status");

			return "user/userBoardEnroll";
		}
	}

	@RequestMapping("/insertBoard.do")
	public String insertBoard(UserBoardDto ubd, HttpSession session, Model model) {
		if (!loginCheck.loginCheck(session)) {
			sessionManage.setSessionMessage("로그인 후 이용할 수 있습니다.", "error", session);

			return "redirect:/user/board/showBoardList.do?postCategory=" + ubd.getPostCategory();
		} else {

			int memberNum = (int) session.getAttribute("memberNum");

			ubd.setMemberNum(memberNum);

			int result = userBoardService.insertBoard(ubd);

			return "redirect:/user/board/showBoardList.do?postCategory=" + ubd.getPostCategory();
		}
	}

	@RequestMapping("/deleteBoard.do")
	public String deleteBoard(@RequestParam(value = "postCategory", defaultValue = "-1") String postCategory,
			@RequestParam(value = "postNum", defaultValue = "-1") int postNum,
			@RequestParam(value = "memberNum", defaultValue = "-1") int memberNum, HttpSession session, Model model) {
		if (!loginCheck.loginCheck(session)) {
			sessionManage.setSessionMessage("로그인 후 이용할 수 있습니다.", "error", session);

			return "redirect:/user/board/showBoardList.do?postCategory=" + postCategory;
		} else {
			int sessionMemberNum = (int) session.getAttribute("memberNum");

			if (memberNum == sessionMemberNum || ((String) session.getAttribute("memberDivision")).equals("admin")) {

				int result = userBoardService.deleteBoard(postNum);

				if (result > 0) {
					sessionManage.setSessionMessage("정상 처리 되었습니다.", "success", session);
				} else {
					sessionManage.setSessionMessage("삭제 처리중 오류 발생.", "error", session);
				}

				return "redirect:/user/board/showBoardList.do?postCategory=" + postCategory;
			} else {
				return "redirect:/user/board/showBoardList.do?postCategory=" + postCategory;
			}
		}
	}

	@RequestMapping("/showModifyBoard.do")
	public String showModifyBoard(@RequestParam(value = "postCategory", defaultValue = "-1") String postCategory,
			@RequestParam(value = "postNum", defaultValue = "-1") int postNum,
			@RequestParam(value = "memberNum", defaultValue = "-1") int memberNum, HttpSession session, Model model) {
		if (!loginCheck.loginCheck(session)) {
			sessionManage.setSessionMessage("로그인 후 이용할 수 있습니다.", "error", session);

			return "redirect:/user/board/showBoardList.do?postCategory=" + postCategory;
		} else {
			int sessionMemberNum = (int) session.getAttribute("memberNum");

			if (memberNum == sessionMemberNum || ((String) session.getAttribute("memberDivision")).equals("admin")) {

				UserBoardDto ubd = userBoardService.selectPost(postNum);

				if (ubd != null) {

					model.addAttribute("board", ubd);
					return "user/userBoardModify";
				} else {
					sessionManage.setSessionMessage("수정 가능한 게시물이 없습니다.", "error", session);
				}

				return "redirect:/user/board/showBoardList.do?postCategory=" + postCategory;
			} else {
				return "redirect:/user/board/showBoardList.do?postCategory=" + postCategory;
			}
		}
	}

	@RequestMapping("/updateBoard.do")
	public String updateBoard(UserBoardDto ubd, HttpSession session, Model model) {
		if (!loginCheck.loginCheck(session)) {
			sessionManage.setSessionMessage("로그인 후 이용할 수 있습니다.", "error", session);

			return "redirect:/";
		} else {

			int result = userBoardService.updateBoard(ubd);

			return "redirect:/user/board/showBoardList.do?postCategory=" + ubd.getPostCategory();
		}
	}

	@ResponseBody
	@RequestMapping("/likePost.do")
	public int likePost(@RequestParam(value = "postNum", defaultValue = "-1") int postNum, HttpSession session,
			Model model) {

		int memberNum = (int) session.getAttribute("memberNum");

		UserBoardLikeDto ubld = new UserBoardLikeDto();
		ubld.setMemberNum(memberNum);
		ubld.setPostNum(postNum);

		int checkLike = userBoardService.selectCheckLike(ubld);

		if (checkLike > 0) {
			return -1;
		} else {
			userBoardService.likePost(ubld);

			int newLikeCount = userBoardService.selectLikeCount(postNum);
			return newLikeCount;
		}
	}

	@ResponseBody
	@RequestMapping("/unlikePost.do")
	public int unikePost(@RequestParam(value = "postNum", defaultValue = "-1") int postNum, HttpSession session,
			Model model) {
		if (!loginCheck.loginCheck(session)) {
			sessionManage.setSessionMessage("로그인 후 이용할 수 있습니다.", "error", session);
			return -2;
		}
		int memberNum = (int) session.getAttribute("memberNum");

		UserBoardLikeDto ubld = new UserBoardLikeDto();
		ubld.setMemberNum(memberNum);
		ubld.setPostNum(postNum);

		int checkLike = userBoardService.selectCheckLike(ubld);

		if (checkLike > 0) {
			userBoardService.unlikePost(ubld);
			int newLikeCount = userBoardService.selectLikeCount(postNum);
			return newLikeCount;

		} else {

			return -1;
		}
	}

	@ResponseBody
	@RequestMapping("/insertReply.do")
	public List<UserBoardReplyDto> insertReply(UserBoardReplyDto ubrd, HttpSession session, Model model) {
		if (!loginCheck.loginCheck(session)) {
			sessionManage.setSessionMessage("로그인 후 이용할 수 있습니다.", "error", session);
			List<UserBoardReplyDto> list = null;
			return list;
		}
		int memberNum = (int) session.getAttribute("memberNum");

		ubrd.setMemberNum(memberNum);
		int result = userBoardService.insertReply(ubrd);

		if (result > 0) {
			List<UserBoardReplyDto> replyList = userBoardService.selectReplyOne(ubrd.getPostReplyContent());
			for (UserBoardReplyDto item : replyList) {
				Date replyDate = item.getPostReplyCreateDate();
				Date currentTime = new Date();
				long timeDiff = currentTime.getTime() - replyDate.getTime();
				long seconds = timeDiff / 1000;

				String newDate;
				if (seconds < 60) {
					newDate = seconds + "초 전";
				} else if (seconds < 3600) {
					long minutes = seconds / 60;
					newDate = minutes + "분 전";
				} else if (seconds < 86400) {
					long hours = seconds / 3600;
					newDate = hours + "시간 전";
				} else {
					long days = seconds / 86400;
					newDate = days + "일 전";
				}
				item.setNewDate(newDate);
			}
			return replyList;

		} else {
			List<UserBoardReplyDto> replyList = null;
			return replyList;
		}

	}

	@ResponseBody
	@RequestMapping("/replyLikePost.do")
	public int replyLikePost(@RequestParam(value = "postReplyNum", defaultValue = "-1") int postReplyNum,
			HttpSession session, Model model) {
		if (!loginCheck.loginCheck(session)) {
			sessionManage.setSessionMessage("로그인 후 이용할 수 있습니다.", "error", session);
			return -2;
		}
		int memberNum = (int) session.getAttribute("memberNum");

		UserBoardReplyDto ubrd = new UserBoardReplyDto();
		ubrd.setMemberNum(memberNum);
		ubrd.setPostReplyNum(postReplyNum);
		System.out.println(ubrd.toString());
		int checkReplyLike = userBoardService.selectReplyCheckLike(ubrd);

		if (checkReplyLike > 0) {
			userBoardService.replyUnlikePost(ubrd);
			int newLikeCount = userBoardService.selectReplyLikeCount(postReplyNum);
			return newLikeCount;

		} else {
			userBoardService.replyLikePost(ubrd);
			int newLikeCount = userBoardService.selectReplyLikeCount(postReplyNum);
			return newLikeCount;
		}
	}

	@ResponseBody
	@RequestMapping("/deleteReply.do")
	public boolean deleteReply(@RequestParam(value = "postReplyNum", defaultValue = "-1") int postReplyNum,
			HttpSession session, Model model) {

		int deleteResult = userBoardService.deleteReply(postReplyNum);

		if (deleteResult > 0) {
			return true;

		} else {
			return false;
		}
	}
}
