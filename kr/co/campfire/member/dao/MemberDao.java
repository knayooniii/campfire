package kr.co.campfire.member.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import kr.co.campfire.member.dto.MemberDto;

@Repository
public class MemberDao {
	public MemberDto loginMember(SqlSessionTemplate sqlSession, MemberDto m) {
		return sqlSession.selectOne("memberMapper.loginMember", m);
	}

//	public int checkEmail(SqlSessionTemplate sqlSession, String email) {
//		return sqlSession.selectOne("memberMapper.checkEmail", email);
//	}

//	public int singupMember(SqlSessionTemplate sqlSession, MemberDto m) {
//		return sqlSession.insert("memberMapper.singupMember", m);
//	}

	public int checkId(SqlSessionTemplate sqlSession, String id) {
		return sqlSession.selectOne("memberMapper.checkId", id);
	}

	public int kakaoSingup(SqlSessionTemplate sqlSession, MemberDto md) {
		return sqlSession.insert("memberMapper.kakaoSingup", md);
	}

	public int naverSingup(SqlSessionTemplate sqlSession, MemberDto md) {
		return sqlSession.insert("memberMapper.naverSingup", md);
	}

	public int googleSingup(SqlSessionTemplate sqlSession, MemberDto md) {
		return sqlSession.insert("memberMapper.googleSingup", md);
	}

	// 은연님 로그인

	public int signupMember(SqlSessionTemplate sqlSession, MemberDto memberjojn) {

		return sqlSession.insert("memberMapper.signupMember", memberjojn);
	}

	public MemberDto readMember(SqlSessionTemplate sqlSession, int mnum) {
		return sqlSession.selectOne("memberMapper.readMember", mnum);
	}

	public int pwup(SqlSessionTemplate sqlSession, int mnum, String memberPw) {
		MemberDto md = new MemberDto();
		md.setMemberNum(mnum);
		md.setMemberPw(memberPw);
		return sqlSession.update("memberMapper.pwup", md);
	}

	public int mypageup(SqlSessionTemplate sqlSession, int mnum, String memberName, String memberPostalcode,
			String memberAdd1, String memberAdd2, String memberGender, String memberDateBirth) {
		MemberDto mp = new MemberDto();
		mp.setMemberNum(mnum);
		mp.setMemberName(memberName);
		mp.setMemberPostalcode(memberPostalcode);
		mp.setMemberAdd1(memberAdd1);
		mp.setMemberAdd2(memberAdd2);
		mp.setMemberGender(memberGender);
		mp.setMemberDateBirth(memberDateBirth);
		return sqlSession.update("memberMapper.mypageup", mp);
	}

	// 비밀번호 변경정보 보기
	public MemberDto pwupread(SqlSessionTemplate sqlSession, int mnum) {
		return sqlSession.selectOne("memberMapper.pwupread", mnum);
	}

	// 회원수정정보 보기
	public MemberDto mypageupread(SqlSessionTemplate sqlSession, int mnum) {
		return sqlSession.selectOne("memberMapper.mypageupread", mnum);
	}

	// 아이디 중복체크
	public MemberDto idCheck(SqlSessionTemplate sqlSession, String memberUserId) {
		return sqlSession.selectOne("memberMapper.idCheck", memberUserId);
	}

	// 아이디 찾기
	public String find_id(SqlSessionTemplate sqlSession, String memberName, String memberDateBirth) {
		MemberDto md = new MemberDto();
		md.setMemberName(memberName);
		md.setMemberDateBirth(memberDateBirth);
		return sqlSession.selectOne("memberMapper.find_id", md);
	}

	// 비밀번호 찾기
	public String find_pw(SqlSessionTemplate sqlSession, String memberUserId, String memberName) {
		MemberDto md = new MemberDto();
		md.setMemberUserId(memberUserId);
		md.setMemberName(memberName);
		return sqlSession.selectOne("memberMapper.find_pw", md);
	}

	// 회원 탈퇴
	public int memberDelete(SqlSessionTemplate sqlSession, MemberDto vo) {
		return sqlSession.delete("memberMapper.memberDelete", vo);
	}

	// 
	public String selectMemberPw(SqlSessionTemplate sqlSession, int memberNum) {
		return sqlSession.selectOne("memberMapper.selectMemberPw", memberNum);
	}
}
