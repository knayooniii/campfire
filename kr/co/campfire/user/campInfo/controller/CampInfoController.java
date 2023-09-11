package kr.co.campfire.user.campInfo.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.campfire.common.controller.LoginCheckController;
import kr.co.campfire.common.controller.SessionManageController;
import kr.co.campfire.user.campInfo.dto.CampInfoDto;
import kr.co.campfire.user.campInfo.dto.CampInfoFunctionDto;
import kr.co.campfire.user.campInfo.service.CampInfoServiceImpl;

@Controller
@RequestMapping("/campInfo")
public class CampInfoController {

	@Autowired
	private CampInfoServiceImpl CampInfoService;

	@Autowired
	private SessionManageController sessionManage;

	@Autowired
	private LoginCheckController loginCheck;

	@RequestMapping("/campInfo.do")
	public String campInfo(Model model, HttpSession session, @RequestParam(value = "campNum") int campNum) {
		if (!loginCheck.loginCheck(session)) {
			sessionManage.setSessionMessage("로그인 후 이용할 수 있습니다.", "error", session);
			return "redirect:/campSearch/camping.do";
		} else {

			CampInfoDto result = CampInfoService.campInfo(campNum);

			int count = result.getCampView() + 1;
			result.setCampView(count);
			result.setCampNum(campNum);

			int countLike = CampInfoService.selectLikeCount(campNum);
			result.setLikeCamp(countLike);

			int countWishlist = CampInfoService.selectWishlistCount(campNum);
			result.setWishlist(countWishlist);

			int resultView = CampInfoService.countCampInfo(result);

			// 태그 정보 불러오기
			List<CampInfoDto> campTagList = CampInfoService.campTag(campNum);
			for (CampInfoDto item : campTagList) {

			}
			// 편의 시설 불러오기
			List<CampInfoDto> campAmenityList = CampInfoService.campAmenity(campNum);
			for (CampInfoDto item : campAmenityList) {

			}

			// 사진 불러오기
			List<CampInfoDto> campPhotoList = CampInfoService.campPhoto(campNum);
			for (CampInfoDto item : campPhotoList) {

			}

			CampInfoFunctionDto cifd = new CampInfoFunctionDto();
			int memberNum = (int) session.getAttribute("memberNum");
			cifd.setMemberNum(memberNum);
			cifd.setCampNum(campNum);

			int checkLike = CampInfoService.selectCheckLike(cifd);
			int checkWishlist = CampInfoService.selectCheckWishlist(cifd);

			List<CampInfoFunctionDto> replyList = CampInfoService.selectPostReply(campNum);

			for (CampInfoFunctionDto item : replyList) {
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

				int replyLikeCount = CampInfoService.selectReplyLikeCount(item.getPostReplyNum());
				item.setReplyLikeCount(replyLikeCount);
			}

			model.addAttribute("replyList", replyList);
			model.addAttribute("campPhotoList", campPhotoList);
			model.addAttribute("campAmenityList", campAmenityList);
			model.addAttribute("campTagList", campTagList);
			model.addAttribute("result", result);
			model.addAttribute("checkLike", checkLike);
			model.addAttribute("checkWishlist", checkWishlist);
			
			return "user/campInfo";
		}
	}

	// 좋아요카운트ㅡ
	@ResponseBody
	@RequestMapping("/likeCamp.do")
	public int likeCamp(@RequestParam(value = "campNum", defaultValue = "-1") int campNum, HttpSession session,
			Model model) {
		if (!loginCheck.loginCheck(session)) {
			sessionManage.setSessionMessage("로그인 후 이용할 수 있습니다.", "error", session);
			return -2;
		} else {

			int memberNum = (int) session.getAttribute("memberNum");

			CampInfoFunctionDto cifd = new CampInfoFunctionDto();
			cifd.setMemberNum(memberNum);
			cifd.setCampNum(campNum);

			int checkLike = CampInfoService.selectCheckLike(cifd);

			if (checkLike > 0) {
				return -1;
			} else {
				CampInfoService.likeCamp(cifd);

				int newLikeCount = CampInfoService.selectLikeCount(campNum);
				System.out.println(newLikeCount);
				return newLikeCount;
			}
		}
	}

	@ResponseBody
	@RequestMapping("/unlikeCamp.do")
	public int unlikeCamp(@RequestParam(value = "campNum", defaultValue = "-1") int campNum, HttpSession session,
			Model model) {
		if (!loginCheck.loginCheck(session)) {
			sessionManage.setSessionMessage("로그인 후 이용할 수 있습니다.", "error", session);
			return -2;
		}
		int memberNum = (int) session.getAttribute("memberNum");
		System.out.println(campNum);

		CampInfoFunctionDto cifd = new CampInfoFunctionDto();
		cifd.setMemberNum(memberNum);
		cifd.setCampNum(campNum);

		int checkLike = CampInfoService.selectCheckLike(cifd);

		if (checkLike > 0) {
			CampInfoService.unlikeCamp(cifd);
			int newLikeCount = CampInfoService.selectLikeCount(campNum);
			System.out.println(newLikeCount);
			return newLikeCount;

		} else {

			return -1;
		}
	}

	// 즐찾카운트
	@ResponseBody
	@RequestMapping("/wishlist.do")
	public int wishlist(@RequestParam(value = "campNum", defaultValue = "-1") int campNum, HttpSession session,
			Model model) {
		if (!loginCheck.loginCheck(session)) {
			sessionManage.setSessionMessage("로그인 후 이용할 수 있습니다.", "error", session);
			return -2;
		}
		int memberNum = (int) session.getAttribute("memberNum");

		CampInfoFunctionDto cifd = new CampInfoFunctionDto();
		cifd.setMemberNum(memberNum);
		cifd.setCampNum(campNum);

		int checkWishlist = CampInfoService.selectCheckWishlist(cifd);

		if (checkWishlist > 0) {
			return -1;
		} else {
			int result = CampInfoService.wishlist(cifd);
			System.out.println(result);
			int newWishlist = CampInfoService.selectWishlistCount(campNum);
			return newWishlist;
		}
	}

	@ResponseBody
	@RequestMapping("/unwishlist.do")
	public int unwishliat(@RequestParam(value = "campNum", defaultValue = "-1") int campNum, HttpSession session,
			Model model) {
		if (!loginCheck.loginCheck(session)) {
			sessionManage.setSessionMessage("로그인 후 이용할 수 있습니다.", "error", session);
			return -2;
		}
		int memberNum = (int) session.getAttribute("memberNum");

		CampInfoFunctionDto cifd = new CampInfoFunctionDto();
		cifd.setMemberNum(memberNum);
		cifd.setCampNum(campNum);

		int checkWishlist = CampInfoService.selectCheckWishlist(cifd);

		if (checkWishlist > 0) {
			int result = CampInfoService.unwishlist(cifd);
			System.out.println(result);
			int newWishlistCount = CampInfoService.selectWishlistCount(campNum);
			return newWishlistCount;

		} else {

			return -1;
		}
	}

	// 댓글ㄹ
	@ResponseBody
	@RequestMapping("/insertReply.do")
	public List<CampInfoFunctionDto> insertReply(CampInfoFunctionDto cifd, HttpSession session, Model model) {
		if (!loginCheck.loginCheck(session)) {
			sessionManage.setSessionMessage("로그인 후 이용할 수 있습니다.", "error", session);
			List<CampInfoFunctionDto> replyList = null;
			return replyList;
		}

		int memberNum = (int) session.getAttribute("memberNum");

		cifd.setMemberNum(memberNum);
		int result = CampInfoService.insertReply(cifd);

		if (result > 0) {
			List<CampInfoFunctionDto> replyList = CampInfoService.selectReplyOne(cifd.getPostReplyContent());
			for (CampInfoFunctionDto item : replyList) {
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
			List<CampInfoFunctionDto> replyList = null;
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

		CampInfoFunctionDto cifd = new CampInfoFunctionDto();
		cifd.setMemberNum(memberNum);
		cifd.setPostReplyNum(postReplyNum);
		System.out.println(cifd.toString());
		int checkReplyLike = CampInfoService.selectReplyCheckLike(cifd);

		if (checkReplyLike > 0) {
			CampInfoService.replyUnlikePost(cifd);
			int newLikeCount = CampInfoService.selectReplyLikeCount(postReplyNum);
			return newLikeCount;

		} else {
			CampInfoService.replyLikePost(cifd);
			int newLikeCount = CampInfoService.selectReplyLikeCount(postReplyNum);
			return newLikeCount;
		}
	}

	@ResponseBody
	@RequestMapping("/deleteReply.do")
	public boolean deleteReply(@RequestParam(value = "postReplyNum", defaultValue = "-1") int postReplyNum,
			HttpSession session, Model model) {

		int deleteResult = CampInfoService.deleteReply(postReplyNum);

		if (deleteResult > 0) {
			return true;

		} else {
			return false;
		}
	}
}
