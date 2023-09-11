package kr.co.campfire.member.service;

import java.util.HashMap;

import javax.servlet.http.HttpServletResponse;

import kr.co.campfire.member.dto.*;

public interface MemberService {
	MemberDto loginMember(MemberDto m);
	
//	int checkEmail(String email);
	
//	int singupMember(MemberDto m);
	
	//카카오 로그인
	String getAccessTokenKakao(String authorize_code) throws Throwable;
	
	public HashMap<String, Object> getUserInfoKakao(String access_Token) throws Throwable;
	
	int checkId(String id);
	
	int kakaoSingup(MemberDto md);
	
	int naverSingup(MemberDto md);
	
	int googleSingup(MemberDto md);
	
	String getAccessTokenNaver(String authorize_code) throws Throwable;
	
	public HashMap<String, Object> getUserInfoNaver(String access_Token) throws Throwable;
	
	String getAccessTokenGoogle(String authorize_code) throws Throwable;
	
	public HashMap<String, Object> getUserInfoGoogle(String access_Token) throws Throwable;
	
	//은연님 로그인
	
	int signupMember(MemberDto memberjojn);
	MemberDto readMember(int mnum);
	MemberDto pwupread(int mnum);
	MemberDto mypageupread(int mnum);
	int pwup(int mnum, String memberPw);
	int mypageup(int mnum, String memberName, String memberPostalcode,
			String memberAdd1, String memberAdd2, String memberGender, 
			String memberDateBirth);
	MemberDto idCheck(String memberUserId);
	
	String find_id(HttpServletResponse response, String memberName, String memberDateBirth);
	String find_pw(HttpServletResponse response, String  memberUserId, String memberName);
	
	int memberDelete(MemberDto vo);
	
	String selectMemberPw(int memberNum);
}