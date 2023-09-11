package kr.co.campfire.admin.inquiry.controller;

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

import kr.co.campfire.admin.inquiry.dto.AdminInquiryDto;
import kr.co.campfire.admin.inquiry.service.AdminInquiryServiceImpl;
import kr.co.campfire.common.controller.DataValidationController;
import kr.co.campfire.common.controller.LoginCheckController;
import kr.co.campfire.common.controller.Pagination;
import kr.co.campfire.common.controller.SessionManageController;
import kr.co.campfire.common.dto.PageInfo;
import kr.co.campfire.user.userInquiry.dto.UserInquiryDto;
import kr.co.campfire.user.userInquiry.service.UserInquiryServiceImpl;

@Controller
@RequestMapping("/admin")
public class AdminInquiryController {
	@Autowired
	private AdminInquiryServiceImpl adminInquiryService;

	@Autowired
	private DataValidationController dataValidation;

	@Autowired
	private LoginCheckController loginCheck;

	@Autowired
	private SessionManageController sessionManage;

	@RequestMapping("/showAdminInquiry.do")
	public String showAdminInquiry(@RequestParam(value = "cpage", defaultValue = "1") int currentPage,
			@RequestParam(value = "allList", defaultValue = "N") String allList, HttpSession session, Model model) {
		if (loginCheck.loginCheck(session)) {
			if (((String) session.getAttribute("memberDivision")).equals("admin")) {
				// 전체 게시글 수 구하기
				int listCount = 0;
				if (allList.equals("Y")) {
					listCount = adminInquiryService.selectListAllCount();
				} else {
					listCount = adminInquiryService.selectListNoAnswerCount();
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

				List<AdminInquiryDto> list = null;
				if (allList.equals("Y")) {
					list = adminInquiryService.selectListAll(pi);
				} else {
					list = adminInquiryService.selectListNoAnswer(pi);
				}

				for (AdminInquiryDto item : list) {
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
				model.addAttribute("allList", allList);
				model.addAttribute("msg", (String) session.getAttribute("msg"));
				model.addAttribute("status", (String) session.getAttribute("status"));

				session.removeAttribute("msg");
				session.removeAttribute("status");

				return "admin/adminInquiry";

			} else {
				sessionManage.setSessionMessage("관리자 로그인 후 이용할 수 있습니다.", "error", session);

				return "redirect:/campSearch/camping.do";
			}
		} else {
			sessionManage.setSessionMessage("로그인 후 이용할 수 있습니다.", "error", session);

			return "redirect:/";
		}
	}

	@RequestMapping("/detailAdminInquiry.do")
	public String detailAdminInquiry(@RequestParam(value = "inNum") int inNum, Model model, HttpSession session) {

		if (loginCheck.loginCheck(session)) {
			if (((String) session.getAttribute("memberDivision")).equals("admin")) {

				AdminInquiryDto result = adminInquiryService.detailInquiry(inNum);
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
					model.addAttribute("detail", result);
					return "admin/adminInquiryDetail";
				} else {
					return "common/errorPage";
				}
			} else {
				sessionManage.setSessionMessage("관리자 로그인 후 이용할 수 있습니다.", "error", session);

				return "redirect:/campSearch/camping.do";
			}
		} else {
			sessionManage.setSessionMessage("로그인 후 이용할 수 있습니다.", "error", session);

			return "redirect:/";
		}
	}

	@RequestMapping("/answerForm.do")
	public String enrollForm(@RequestParam(value = "inNum") int inNum, Model model, HttpSession session,
			HttpServletRequest request) {

		if (loginCheck.loginCheck(session)) {
			if (((String) session.getAttribute("memberDivision")).equals("admin")) {

				AdminInquiryDto result = adminInquiryService.detailInquiry(inNum);
				if (!Objects.isNull(result)) {
					// 추가
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

					Timestamp inquiryTimestamp = result.getInquiryDateTimeCreated(); //
					Date inquiryDate = new Date(inquiryTimestamp.getTime());
					String formattedinquiryDate = sdf.format(inquiryDate);
					result.setInquiryNewDate(formattedinquiryDate);
					model.addAttribute("detail", result);
					return "admin/adminInquiryEnroll";
				} else {
					return "common/errorPage";
				}
			} else {
				sessionManage.setSessionMessage("관리자 로그인 후 이용할 수 있습니다.", "error", session);

				return "redirect:/campSearch/camping.do";
			}
		} else {
			sessionManage.setSessionMessage("로그인 후 이용할 수 있습니다.", "error", session);

			return "redirect:/";
		}
	}

	@RequestMapping("/insertAdminAnswer.do")
	public String insertAdminEnroll(AdminInquiryDto aid, Model model, HttpSession session, HttpServletRequest request) {

		if (loginCheck.loginCheck(session)) {
			if (((String) session.getAttribute("memberDivision")).equals("admin")) {
				aid.setAnswerAdmin((String) session.getAttribute("memberName"));
				System.out.println(aid.toString());
				int result1 = adminInquiryService.insertAnswer(aid);
				int result2 = adminInquiryService.updateInquiryIA(aid.getInquiryNum());

				if (result1 > 0 && result2 > 0) {
					sessionManage.setSessionMessage("답변 등록 완료 되었습니다.", "success", session);
				} else {
					sessionManage.setSessionMessage("답변 등록 실패...", "error", session);
				}
				return "redirect:/admin/showAdminInquiry.do";
			} else {
				sessionManage.setSessionMessage("관리자 로그인 후 이용할 수 있습니다.", "error", session);

				return "redirect:/campSearch/camping.do";
			}
		} else {
			sessionManage.setSessionMessage("로그인 후 이용할 수 있습니다.", "error", session);

			return "redirect:/";
		}
	}

	@GetMapping("/deleteAdminInquiry.do")
	public String deleteAdminInquiry(@RequestParam(value = "inNum") int inNum, Model model, HttpSession session) {

		if (loginCheck.loginCheck(session)) {
			if (((String) session.getAttribute("memberDivision")).equals("admin")) {
				adminInquiryService.deleteAnswer(inNum);
				int result = adminInquiryService.deleteInquiry(inNum);

				if (result > 0) {
					return "redirect:/admin/showAdminInquiry.do";
				} else {
					sessionManage.setSessionMessage("삭제 불가능 합니다.", "error", session);
					return "redirect:/admin/showAdminInquiry.do";
				}
			} else {
				sessionManage.setSessionMessage("관리자 로그인 후 이용할 수 있습니다.", "error", session);

				return "redirect:/campSearch/camping.do";
			}
		} else {
			sessionManage.setSessionMessage("로그인 후 이용할 수 있습니다.", "error", session);

			return "redirect:/";
		}
	}
	@GetMapping("/deleteAdminAnswer.do")
	public String deleteAdminAnswer(@RequestParam(value = "inNum") int inNum, Model model, HttpSession session) {

		if (loginCheck.loginCheck(session)) {
			if (((String) session.getAttribute("memberDivision")).equals("admin")) {
				int result = adminInquiryService.deleteAnswer(inNum);
				adminInquiryService.updateInquiryDA(inNum);
				if (result > 0) {
					return "redirect:/admin/showAdminInquiry.do";
				} else {
					sessionManage.setSessionMessage("삭제 불가능 합니다.", "error", session);
					return "redirect:/admin/showAdminInquiry.do";
				}
			} else {
				sessionManage.setSessionMessage("관리자 로그인 후 이용할 수 있습니다.", "error", session);

				return "redirect:/campSearch/camping.do";
			}
		} else {
			sessionManage.setSessionMessage("로그인 후 이용할 수 있습니다.", "error", session);

			return "redirect:/";
		}
	}
}