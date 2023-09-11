package kr.co.campfire.member.controller;

import java.util.HashMap;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.co.campfire.member.dto.*;
import kr.co.campfire.member.service.*;

@Controller
@RequestMapping("/member")
public class MemberController {
	@Autowired
	MemberServiceImpl memberService;

	@RequestMapping("/login.do")
	public String loginIndex(MemberDto m, HttpSession session, Model model) {
		MemberDto loginUser = memberService.loginMember(m);
		if (!Objects.isNull(loginUser)) {
			session.setAttribute("memberNum", loginUser.getMemberNum());
			String sessionMemberIdx = String.valueOf(loginUser.getMemberNum());
			session.setAttribute("sessionMemberIdx", sessionMemberIdx);
			session.setAttribute("memberName", loginUser.getMemberName());
			session.setAttribute("memberDivision", loginUser.getMemberDivision());
			session.setAttribute("memberUserId", loginUser.getMemberUserId());
			System.out.println("memberNum : " + session.getAttribute("memberName"));
			return "redirect:/campSearch/camping.do";
		} else {
//			model.addAttribute("msg", "아이디 비밀번호를 확인해 주세요!");
//			model.addAttribute("status", "error");
			return "/member/login";
		}
	}

	@GetMapping("/go.do")
	public String godo() {

		return "redirect:/campSearch/camping.do";
	}

	@RequestMapping(value = "/kakaoLogin", method = RequestMethod.GET)
	public String kakaoLogin(@RequestParam(value = "code", required = false) String code, HttpSession session,
			Model model) throws Throwable {

		String access_Token = memberService.getAccessTokenKakao(code);
		// 위의 access_Token 받는 걸 확인한 후에 밑에 진행

		HashMap<String, Object> userInfo = memberService.getUserInfoKakao(access_Token);
		System.out.println("###id#### : " + userInfo.get("id"));
		System.out.println("###email#### : " + userInfo.get("email"));
		System.out.println("###nickname#### : " + userInfo.get("nickname"));
		System.out.println("###gender#### : " + userInfo.get("gender"));

		String id = (String) userInfo.get("id");
		int checkId = memberService.checkId(id);
		if (checkId > 0) {
			MemberDto m = new MemberDto();
			m.setMemberUserId(id);
			MemberDto loginUser = memberService.loginMember(m);
			System.out.println(loginUser.toString());
			if (!Objects.isNull(loginUser)) {
				session.setAttribute("memberNum", loginUser.getMemberNum());
				String sessionMemberIdx = String.valueOf(loginUser.getMemberNum());
				session.setAttribute("sessionMemberIdx", sessionMemberIdx);
				session.setAttribute("memberName", loginUser.getMemberName());
				session.setAttribute("memberDivision", loginUser.getMemberDivision());
				return "redirect:/campSearch/camping.do";
			} else {
//				model.addAttribute("msg", "아이디 비밀번호를 확인해 주세요!");
//				model.addAttribute("status", "error");
				return "/member/login";
			}
		} else {
			MemberDto md = new MemberDto();
			md.setMemberUserId(id);
			md.setMemberPw(id);
			md.setMemberName((String) userInfo.get("nickname"));
			md.setMemberGender((String) userInfo.get("gender"));
			md.setMemberKakao((String) userInfo.get("email"));

			int result = memberService.kakaoSingup(md);
			if (result > 0) {
				MemberDto m = new MemberDto();
				m.setMemberUserId(id);
				MemberDto loginUser = memberService.loginMember(m);
				System.out.println(loginUser.toString());
				if (!Objects.isNull(loginUser)) {
					session.setAttribute("memberNum", loginUser.getMemberNum());
					String sessionMemberIdx = String.valueOf(loginUser.getMemberNum());
					session.setAttribute("sessionMemberIdx", sessionMemberIdx);
					session.setAttribute("memberName", loginUser.getMemberName());
					session.setAttribute("memberDivision", loginUser.getMemberDivision());
					return "redirect:/campSearch/camping.do";
				} else {
//					model.addAttribute("msg", "아이디 비밀번호를 확인해 주세요!");
//					model.addAttribute("status", "error");
					return "/member/login";
				}
			}
			return "/member/login";
		}
		// return에 페이지를 해도 되고, 여기서는 코드가 넘어오는지만 확인할거기 때문에 따로 return 값을 두지는 않았음

	}

	// 네이버 로그인
	@RequestMapping(value = "/naverLogin", method = RequestMethod.GET)
	public String naverLogin(@RequestParam(value = "code", required = false) String code, HttpSession session,
			Model model) throws Throwable {

		String access_Token = memberService.getAccessTokenNaver(code);
		// 위의 access_Token 받는 걸 확인한 후에 밑에 진행
		System.out.println("access_Token" + access_Token);

		HashMap<String, Object> userInfo = memberService.getUserInfoNaver(access_Token);
		System.out.println("###id#### : " + userInfo.get("id"));
		System.out.println("###pw#### : " + userInfo.get("pw"));
		System.out.println("###email#### : " + userInfo.get("email"));
		System.out.println("###nickname#### : " + userInfo.get("nickname"));
		System.out.println("###gender#### : " + userInfo.get("gender"));
		System.out.println("###birthyear#### : " + userInfo.get("birthyear"));
		System.out.println("###birthday#### : " + userInfo.get("birthday"));
		String id = (String) userInfo.get("id");
		int checkId = memberService.checkId(id);
		if (checkId > 0) {
			MemberDto m = new MemberDto();
			m.setMemberUserId(id);
			MemberDto loginUser = memberService.loginMember(m);
			System.out.println(loginUser.toString());
			if (!Objects.isNull(loginUser)) {
				session.setAttribute("memberNum", loginUser.getMemberNum());
				String sessionMemberIdx = String.valueOf(loginUser.getMemberNum());
				session.setAttribute("sessionMemberIdx", sessionMemberIdx);
				session.setAttribute("memberName", loginUser.getMemberName());
				session.setAttribute("memberDivision", loginUser.getMemberDivision());
				return "redirect:/campSearch/camping.do";
			} else {
//				model.addAttribute("msg", "아이디 비밀번호를 확인해 주세요!");
//				model.addAttribute("status", "error");
				return "/member/login";
			}
		} else {
			MemberDto md = new MemberDto();
			md.setMemberUserId(id);
			md.setMemberPw(id);
			md.setMemberName((String) userInfo.get("nickname"));
			md.setMemberGender((String) userInfo.get("gender"));
			md.setMemberKakao((String) userInfo.get("email"));

			int result = memberService.naverSingup(md);
			if (result > 0) {
				MemberDto m = new MemberDto();
				m.setMemberUserId(id);
				MemberDto loginUser = memberService.loginMember(m);
				System.out.println(loginUser.toString());
				if (!Objects.isNull(loginUser)) {
					session.setAttribute("memberNum", loginUser.getMemberNum());
					String sessionMemberIdx = String.valueOf(loginUser.getMemberNum());
					session.setAttribute("sessionMemberIdx", sessionMemberIdx);
					session.setAttribute("memberName", loginUser.getMemberName());
					session.setAttribute("memberDivision", loginUser.getMemberDivision());
					return "redirect:/campSearch/camping.do";
				} else {
//					model.addAttribute("msg", "아이디 비밀번호를 확인해 주세요!");
//					model.addAttribute("status", "error");
					return "/member/login";
				}
			}
			return "/member/login";
		}
	}

	// 구글 로그인
	@RequestMapping(value = "googleLogin", method = RequestMethod.GET)
	public String googleLogin(@RequestParam(value = "code", required = false) String code, HttpSession session,
			Model model) throws Throwable {

		String access_Token = memberService.getAccessTokenGoogle(code);
		// 위의 access_Token 받는 걸 확인한 후에 밑에 진행
		System.out.println("access_Token" + access_Token);
		HashMap<String, Object> userInfo = memberService.getUserInfoGoogle(access_Token);
		System.out.println("###id#### : " + userInfo.get("id"));
		System.out.println("###pw#### : " + userInfo.get("pw"));
		System.out.println("###email#### : " + userInfo.get("email"));
		System.out.println("###nickname#### : " + userInfo.get("nickname"));
		String id = (String) userInfo.get("id");
		int checkId = memberService.checkId(id);
		if (checkId > 0) {
			MemberDto m = new MemberDto();
			m.setMemberUserId(id);
			MemberDto loginUser = memberService.loginMember(m);
			System.out.println(loginUser.toString());
			if (!Objects.isNull(loginUser)) {
				session.setAttribute("memberNum", loginUser.getMemberNum());
				String sessionMemberIdx = String.valueOf(loginUser.getMemberNum());
				session.setAttribute("sessionMemberIdx", sessionMemberIdx);
				session.setAttribute("memberName", loginUser.getMemberName());
				session.setAttribute("memberDivision", loginUser.getMemberDivision());
				return "redirect:/campSearch/camping.do";
			} else {
//						model.addAttribute("msg", "아이디 비밀번호를 확인해 주세요!");
//						model.addAttribute("status", "error");
				return "/member/login";
			}
		} else {
			MemberDto md = new MemberDto();
			md.setMemberUserId(id);
			md.setMemberPw(id);
			md.setMemberName((String) userInfo.get("nickname"));
			md.setMemberGender((String) userInfo.get("gender"));
			md.setMemberGoogle((String) userInfo.get("email"));

			int result = memberService.googleSingup(md);
			if (result > 0) {
				MemberDto m = new MemberDto();
				m.setMemberUserId(id);
				MemberDto loginUser = memberService.loginMember(m);
				System.out.println(loginUser.toString());
				if (!Objects.isNull(loginUser)) {
					session.setAttribute("memberNum", loginUser.getMemberNum());
					String sessionMemberIdx = String.valueOf(loginUser.getMemberNum());
					session.setAttribute("sessionMemberIdx", sessionMemberIdx);
					session.setAttribute("memberName", loginUser.getMemberName());
					session.setAttribute("memberDivision", loginUser.getMemberDivision());
					return "redirect:/campSearch/camping.do";
				} else {
//							model.addAttribute("msg", "아이디 비밀번호를 확인해 주세요!");
//							model.addAttribute("status", "error");
					return "/member/login";
				}
			}
			return "/member/login";
		}
	}

	// return에 페이지를 해도 되고, 여기서는 코드가 넘어오는지만 확인할거기 때문에 따로 return 값을 두지는 않았음
	


	
	//비밀번호 변경  DB출력
	@GetMapping("/pwup.do")
	public String pwupread(HttpSession session,Model model) { 
		int mnum = (int) session.getAttribute("memberNum");
		MemberDto au2 = memberService.pwupread(mnum);
		model.addAttribute("member",au2);
		return "member/pwup"; 
	}
	
	//비밀번호 변경
	@PostMapping("/pwup.do")
	public String pwup(HttpSession session, MemberDto pwup) {
		int mnum = (int) session.getAttribute("memberNum");
	  int result = memberService.pwup(mnum, pwup.getMemberPw());
	    
	  return "redirect:/member/mypage.do"; 
	}
	
	//회원수정정보 보기
	@GetMapping("/mypageup.do")
	public String mypageupread(HttpSession session,Model model) { 
		int mnum = (int) session.getAttribute("memberNum");
		MemberDto au1 = memberService.mypageupread(mnum);
		model.addAttribute("member",au1);
		
		  // 생일데이터 분리
		if (au1.getMemberDateBirth() != null) {
		    String[] birthParts = au1.getMemberDateBirth().split("-");
		    model.addAttribute("birthYear", birthParts[0]);
		    model.addAttribute("birthMonth", birthParts[1]);
		    model.addAttribute("birthDay", birthParts[2]);	
		    // 여기서 birthParts 배열을 사용한 원하는 작업을 수행
		}
		return "member/mypageup"; 
	}
	
	//회원수정 
	 @PostMapping("/mypageup.do")
	    public String mypageup(@RequestParam(value = "birth-year") String year,
				@RequestParam(value = "birth-month") String month, 
				@RequestParam(value = "birth-day") String day,HttpSession session, MemberDto mypageup
	    		){ 
	        int mnum = (int) session.getAttribute("memberNum");

	        String date = year + "-" + month + "-" + day;
	        mypageup.setMemberDateBirth(date);

	        int result = memberService.mypageup(mnum, mypageup.getMemberName(),
	                                            mypageup.getMemberPostalcode(),
	                                            mypageup.getMemberAdd1(),
	                                            mypageup.getMemberAdd2(),
	                                            mypageup.getMemberGender(),
	                                            mypageup.getMemberDateBirth());
		return "redirect:/member/mypage.do"; 
	}
	
	
	

	
	//회원정보 보기
	@GetMapping("/mypage.do")
	public String mypage(HttpSession session,Model model) { 
		int mnum = (int) session.getAttribute("memberNum");
		MemberDto au = memberService.readMember(mnum);
		model.addAttribute("member",au);
		
	    // 생일데이터 분리
		if (au.getMemberDateBirth() != null) {
		    String[] birthParts = au.getMemberDateBirth().split("-");
		    model.addAttribute("birthYear", birthParts[0]);
		    model.addAttribute("birthMonth", birthParts[1]);
		    model.addAttribute("birthDay", birthParts[2]);	
		    // 여기서 birthParts 배열을 사용한 원하는 작업을 수행
		}

		return "member/mypage"; }
	 
	
	//회원가입
	@PostMapping("/signup.do")
	public String signup(@RequestParam(value = "birth-year") String year,
			@RequestParam(value = "birth-month") String month, @RequestParam(value = "birth-day") String day,
			MemberDto memberjojn, HttpSession session) {

		String date = year + "-" + month + "-" + day;

		memberjojn.setMemberDateBirth(date);

		System.out.println(date);

		int result = memberService.signupMember(memberjojn);

		if (result > 0) {
			return "redirect:/member/login.do"; 

		} else {
			return "blank/fail";
		}

	}

	
	//아이디 중복체크
	@ResponseBody
	@RequestMapping(value = "/idCheck", method = RequestMethod.POST)
	public int postIdCheck(HttpServletRequest req) throws Exception {
	   String memberUserId = req.getParameter("memberUserId");
	   MemberDto idCheck =  memberService.idCheck(memberUserId);
	   
	   int result = 0;
	   
	   if(idCheck != null) {
	    result = 1;
	   }   
	   
	   return result;
	}

	

		

	    
		//아이디 비빌번호 찾기 페이지
		@GetMapping("/idpw.do")
		public String idpw() { 
			
			return "member/idpw"; 
		}  
		
		//아이디 찾기 페이지
		@GetMapping("/search_id.do")
		public String search_id() { 
			
			return "/member/search_id"; 
		}
		
		// 아이디 찾기
		@RequestMapping(value = "/find_id.do", method = RequestMethod.POST)
		public String find_id(HttpServletResponse response, @RequestParam("memberName") String memberName,@RequestParam("memberDateBirth") String memberDateBirth, Model md) throws Exception{
			md.addAttribute("id", memberService.find_id(response, memberName,memberDateBirth));
			return "/member/find_id";
		}

	
		//비밀번호 찾기 페이지
		@GetMapping("/search_pwd.do")
		public String search_pwd() { 
			
			return "/member/search_pwd"; 
		}
		// 아비밀번호 찾기
		@RequestMapping(value = "/find_pw.do", method = RequestMethod.POST)
		public String find_pw(HttpServletResponse response, @RequestParam("memberUserId") String memberUserId,@RequestParam("memberName") String memberName, Model md) throws Exception{
			md.addAttribute("pw", memberService.find_pw(response,memberUserId,memberName));
			return "/member/find_pw";
		}
		
		@RequestMapping("/logout.do")
	    public String logout(Model model, HttpSession session) {
	        // 세션 무효화를 통해 모든 세션 정보 삭제
	        session.invalidate();
	        
	        return "member/login"; // 로그아웃 후 이동할 페이지
	    }
		
		
		// 회원 탈퇴 get
		@RequestMapping(value="/memberDeleteView.do", method = RequestMethod.GET)
		public String memberDeleteView(HttpSession session,Model model) throws Exception{
			model.addAttribute("memberUserId",session.getAttribute("memberUserId") );
			return "member/memberDeleteView";
		}
		
		// 회원 탈퇴 post
		@RequestMapping(value="/memberDelete.do", method = RequestMethod.POST)
		public String memberDelete(MemberDto vo, HttpSession session, RedirectAttributes rttr) throws Exception{
		
			// 세션에 있는 member를 가져와 member변수에 넣어줍니다.
			int memberNum = (int)session.getAttribute("memberNum");
			// 세션에있는 비밀번호
			String sessionPass = memberService.selectMemberPw(memberNum);
			// vo로 들어오는 비밀번호
			String voPass = vo.getMemberPw();
			
			if(!(sessionPass.equals(voPass))) {
				rttr.addFlashAttribute("msg", false);
				return "redirect:/member/memberDeleteView.do";
			}
			memberService.memberDelete(vo);
			session.invalidate();
			return "redirect:/";
		}

}
