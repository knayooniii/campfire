package kr.co.campfire.admin.registration.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import kr.co.campfire.admin.registration.dto.AdminBusinessRegistrationDto;
import kr.co.campfire.admin.registration.dto.AdminCampRegistrationAmenityDto;
import kr.co.campfire.admin.registration.dto.AdminCampRegistrationDto;
import kr.co.campfire.admin.registration.dto.AdminCampRegistrationPhotoDto;
import kr.co.campfire.admin.registration.dto.AdminCampRegistrationTagDto;
import kr.co.campfire.common.dto.PageInfo;
import kr.co.campfire.user.userInquiry.dto.UserInquiryDto;

@Repository
public class AdminRegistrationDao {
	//사업자 등록 관리
	public int selectBusinessRegistrationListCount(SqlSessionTemplate sqlSession) {
		return sqlSession.selectOne("adminRegistrationMapper.selectBusinessRegistrationListCount");
	}
	
	public List<AdminBusinessRegistrationDto> selectBusinessRegistrationList(SqlSessionTemplate sqlSession, PageInfo pi) {
		int offset = (pi.getCurrentPage() - 1) * pi.getBoardLimit();
		int limit = pi.getBoardLimit();
		RowBounds rowBounds = new RowBounds(offset, limit);
		return sqlSession.selectList("adminRegistrationMapper.selectBusinessRegistrationList",null,  rowBounds);
	}
	
	public int approvalBusinessRegistration(SqlSessionTemplate sqlSession, String brNum) {
		return sqlSession.update("adminRegistrationMapper.approvalBusinessRegistration", brNum);
	}
	
	public int refuseBusinessRegistration(SqlSessionTemplate sqlSession, AdminBusinessRegistrationDto abrd) {
		return sqlSession.update("adminRegistrationMapper.refuseBusinessRegistration", abrd);
	}
	
	//캠핑장 등록 관리
	public int selectCampRegistrationListCount(SqlSessionTemplate sqlSession) {
		return sqlSession.selectOne("adminRegistrationMapper.selectCampRegistrationListCount");
	}
	
	public List<AdminCampRegistrationDto> selectCampRegistrationList(SqlSessionTemplate sqlSession, PageInfo pi) {
		int offset = (pi.getCurrentPage() - 1) * pi.getBoardLimit();
		int limit = pi.getBoardLimit();
		RowBounds rowBounds = new RowBounds(offset, limit);
		return sqlSession.selectList("adminRegistrationMapper.selectCampRegistrationList", null,  rowBounds);
	}
	
	public AdminCampRegistrationDto selectCampRegistration(SqlSessionTemplate sqlSession, int campNum) {
		return sqlSession.selectOne("adminRegistrationMapper.selectCampRegistration", campNum);
	}
	
	public List<AdminCampRegistrationPhotoDto> selectCampRegistrationPhotoList(SqlSessionTemplate sqlSession, int campNum) {
		return sqlSession.selectList("adminRegistrationMapper.selectCampRegistrationPhotoList", campNum);
	}
	
	public List<AdminCampRegistrationAmenityDto> selectCampRegistrationAmenityList(SqlSessionTemplate sqlSession, int campNum) {
		return sqlSession.selectList("adminRegistrationMapper.selectCampRegistrationAmenityList", campNum);
	}
	
	public List<AdminCampRegistrationTagDto> selectCampRegistrationTagList(SqlSessionTemplate sqlSession, int campNum) {
		return sqlSession.selectList("adminRegistrationMapper.selectCampRegistrationTagList", campNum);
	}
	
	public int approvalCampRegistration(SqlSessionTemplate sqlSession, int campNum) {
		return sqlSession.update("adminRegistrationMapper.approvalCampRegistration", campNum);
	}
	
	public int refuseCampRegistration(SqlSessionTemplate sqlSession, AdminCampRegistrationDto acrd) {
		return sqlSession.update("adminRegistrationMapper.refuseCampRegistration", acrd);
	}
}
