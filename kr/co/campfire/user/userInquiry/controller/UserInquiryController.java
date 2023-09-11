package kr.co.campfire.user.userInquiry.controller;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.campfire.common.controller.DataValidationController;
import kr.co.campfire.common.controller.LoginCheckController;
import kr.co.campfire.common.controller.Pagination;
import kr.co.campfire.common.controller.SessionManageController;
import kr.co.campfire.common.dto.PageInfo;
import kr.co.campfire.user.userInquiry.dto.UserInquiryDto;
import kr.co.campfire.user.userInquiry.service.UserInquiryServiceImpl;

@Controller
@RequestMapping("/user")
public class UserInquiryController {

	@Autowired
	private UserInquiryServiceImpl userInquiryService;

	@Autowired
	private DataValidationController dataValidation;

	@Autowired
	private LoginCheckController loginCheck;

	@Autowired
	private SessionManageController sessionManage;

	@RequestMapping("/showUserInquiry.do")
	public String showUserInquiry(@RequestParam(value = "searchCtg", defaultValue = "") String searchCtg,
			@RequestParam(value = "searchTxt", defaultValue = "") String searchTxt,
			@RequestParam(value = "cpage", defaultValue = "1") int currentPage, HttpSession session, Model model) {
		// 전체 게시글 수 구하기
		int listCount = 0;
		if (searchCtg.equals("")) {
			listCount = userInquiryService.selectListAllCount();
		} else if (searchCtg.equals("title")) {
			listCount = userInquiryService.selectListTitleCount(searchTxt);
		} else if (searchCtg.equals("context")) {
			listCount = userInquiryService.selectListContextCount(searchTxt);
		} else if (searchCtg.equals("writer")) {
			listCount = userInquiryService.selectListWriterCount(searchTxt);
		}
		// 보여질 페이지 수
		int pageLimit = 10;

		// 한 페이지에 보여질 게시글 수
		int boardLimit = 15;

		// 글 번호 뒤에서부터 출력해주는 변수
		int row = listCount - (currentPage - 1) * boardLimit;

		// 페이징 로직 처리
		PageInfo pi = Pagination.getPageInfo(listCount, currentPage, pageLimit, boardLimit);
		// 목록 불러오기

		List<UserInquiryDto> list = null;
		if (searchCtg.equals("")) {
			list = userInquiryService.selectListAll(pi);
		} else if (searchCtg.equals("title")) {
			list = userInquiryService.selectListTitle(pi, searchTxt);
		} else if (searchCtg.equals("context")) {
			list = userInquiryService.selectListContext(pi, searchTxt);
		} else if (searchCtg.equals("writer")) {
			list = userInquiryService.selectListWriter(pi, searchTxt);
		}
		for (UserInquiryDto item : list) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

			Timestamp inquiryTimestamp = item.getInquiryDateTimeCreated(); //
			Date inquiryDate = new Date(inquiryTimestamp.getTime());
			String formattedinquiryDate = sdf.format(inquiryDate);
			item.setInquiryNewDate(formattedinquiryDate);

			if (item.getInquiryAnswerFL().equals("Y")) {
				Timestamp answerTimestamp = item.getAnswerDateTimeCreated(); //
				Date answerDate = new Date(answerTimestamp.getTime());
				String formattedanswerDate = sdf.format(answerDate);
				item.setAnswerNewDate(formattedanswerDate);
			}
		}

		// 로그인 메시지

		model.addAttribute("list", list); // 객체 바인딩
		model.addAttribute("pi", pi);
		model.addAttribute("row", row);
		model.addAttribute("searchCtg", searchCtg);
		model.addAttribute("searchTxt", searchTxt);

		model.addAttribute("msg", (String) session.getAttribute("msg"));
		model.addAttribute("status", (String) session.getAttribute("status"));

		session.removeAttribute("msg");
		session.removeAttribute("status");

		return "user/userInquiry";
	}

	@RequestMapping("/detailUserInquiry.do")
	public String detailUserInquiry(@RequestParam(value = "inNum") int inNum, Model model, HttpSession session) {

		UserInquiryDto result = userInquiryService.detailInquiry(inNum);

		if (!Objects.isNull(result)) {
			// 추가
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

			Timestamp inquiryTimestamp = result.getInquiryDateTimeCreated(); //
			Date inquiryDate = new Date(inquiryTimestamp.getTime());
			String formattedinquiryDate = sdf.format(inquiryDate);
			result.setInquiryNewDate(formattedinquiryDate);

			if (result.getInquiryAnswerFL().equals("Y")) {
				Timestamp answerTimestamp = result.getAnswerDateTimeCreated(); //
				Date answerDate = new Date(answerTimestamp.getTime());
				String formattedanswerDate = sdf.format(answerDate);
				result.setAnswerNewDate(formattedanswerDate);
			}

			if (!loginCheck.loginCheck(session)) {
				sessionManage.setSessionMessage("로그인 후 이용할 수 있습니다.", "error", session);

				return "redirect:/user/showUserInquiry.do";
			} else {
				if (result.getInquiryPublicFL().equals("N")) {
					if ((int) session.getAttribute("memberNum") == result.getMemberNum()
							|| ((String) session.getAttribute("memberDivision")).equals("admin")) {
						model.addAttribute("detail", result);

						return "user/userInquiryDetail";
					} else {
						sessionManage.setSessionMessage("비공개 문의사항 입니다.", "error", session);

						return "redirect:/user/showUserInquiry.do";
					}
				}
				model.addAttribute("detail", result);
				return "user/userInquiryDetail";
			}
		} else {
			return "common/errorPage";
		}
	}

	@RequestMapping("/enrollUserInquiry.do")
	public String enrollUserInquiry(UserInquiryDto uid, HttpSession session, Model model) {
		boolean titleLength = dataValidation.titleLanguageCheck(uid.getInquiryTitle(), 100);
		boolean contentLength = dataValidation.contentLanguageCheck(uid.getInquiryContext(), 4000);
		boolean titleNullCheck = dataValidation.nullCheck(uid.getInquiryTitle());

		if (titleLength && contentLength && titleNullCheck) {
			uid.setMemberNum((int) session.getAttribute("memberNum"));

			int result = userInquiryService.insertInquiry(uid);

			if (result > 0) {
				return "redirect:/user/showUserInquiry.do";
			} else {
				return "common/errorPage";
			}
		} else if (!titleLength) {
			sessionManage.setSessionMessage("제목이 너무 길어요", "error", session);

			return "redirect:/user/enrollForm.do";
		} else if (!contentLength) {
			sessionManage.setSessionMessage("내용이 너무 길어요", "error", session);

			return "redirect:/user/enrollForm.do";
		} else if (!titleNullCheck) {
			sessionManage.setSessionMessage("제목을 입력해 주세요", "error", session);

			return "redirect:/user/enrollForm.do";
		} else {
			return "common/errorPage";
		}
	}

	@RequestMapping("/enrollForm.do")
	public String enrollForm(Model model, HttpSession session, HttpServletRequest request) {

		// String referer = request.getHeader("referer");
		// if(referer == null || ! referer.startsWith("http://localhost/free/list.do"))
		// {
		// return "common/errorPage";
		// }
		if (!loginCheck.loginCheck(session)) {
			model.addAttribute("msg", "로그인 후 이용할 수 있습니다.");
			model.addAttribute("status", "error");

			return "user/userInquiry";
		} else {
			model.addAttribute("msg", (String) session.getAttribute("msg"));
			model.addAttribute("status", (String) session.getAttribute("status"));

			session.removeAttribute("msg");
			session.removeAttribute("status");

			return "user/userInquiryEnroll";
		}

	}

	@GetMapping("/deleteUserInquiry.do")
	public String deleteUserInquiry(@RequestParam(value = "inNum") int inNum,@RequestParam(value = "memberNum") int memberNum, Model model, HttpSession session) {
		if ((int) session.getAttribute("memberNum") == memberNum
				|| ((String) session.getAttribute("memberDivision")).equals("admin")) {
			int result = userInquiryService.deleteInquiry(inNum);

			if (result > 0) {
				return "redirect:/user/showUserInquiry.do";
			} else {
				sessionManage.setSessionMessage("삭제 불가능 합니다.", "error", session);
				return "redirect:/user/showUserInquiry.do";
			}
		} else {
			sessionManage.setSessionMessage("잘못된 접근 입니다.", "error", session);
			return "redirect:/user/showUserInquiry.do";
		}

	}

	@RequestMapping("/modifyUserInquiry.do")
	public String modifyUserInquiry(UserInquiryDto uid, HttpSession session, Model model) {
		if((int) session.getAttribute("memberNum") == uid.getMemberNum()) {
//			int result = userInquiryService.update(free);
			boolean titleLength = dataValidation.titleLanguageCheck(uid.getInquiryTitle(), 100);
			boolean contentLength = dataValidation.contentLanguageCheck(uid.getInquiryContext(), 4000);
			boolean titleNullCheck = dataValidation.nullCheck(uid.getInquiryTitle());
			
			if (titleLength && contentLength && titleNullCheck) {
				uid.setMemberNum((int) session.getAttribute("memberNum"));
				
				int result = userInquiryService.updateInquiry(uid);
				
				if (result > 0) {
					return "redirect:/user/showUserInquiry.do";
				} else {
					return "common/errorPage";
				}
			} else if (!titleLength) {
				sessionManage.setSessionMessage("제목이 너무 길어요", "error", session);
				
				return "redirect:/user/showUserInquiry.do";
			} else if (!contentLength) {
				sessionManage.setSessionMessage("내용이 너무 길어요", "error", session);
				
				return "redirect:/user/showUserInquiry.do";
			} else if (!titleNullCheck) {
				sessionManage.setSessionMessage("제목을 입력해 주세요", "error", session);
				
				return "redirect:/user/showUserInquiry.do";
			} else {
				return "common/errorPage";
			}
		} else {
			sessionManage.setSessionMessage("잘못된 접근 입니다.", "error", session);
			return "redirect:/user/showUserInquiry.do";
		}
		
	}
	
	@RequestMapping("/modifyForm.do")
	public String modifyForm(UserInquiryDto uid, HttpSession session, Model model) {
		if((int) session.getAttribute("memberNum") == uid.getMemberNum()) {
			model.addAttribute("detail", uid);
			
			return "user/userInquiryModify";
		} else {
			sessionManage.setSessionMessage("잘못된 접근 입니다.", "error", session);
			return "redirect:/user/showUserInquiry.do";
		}
	}

}
