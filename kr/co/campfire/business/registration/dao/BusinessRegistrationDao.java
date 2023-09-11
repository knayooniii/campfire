package kr.co.campfire.business.registration.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import kr.co.campfire.admin.registration.dto.AdminCampRegistrationAmenityDto;
import kr.co.campfire.admin.registration.dto.AdminCampRegistrationDto;
import kr.co.campfire.admin.registration.dto.AdminCampRegistrationPhotoDto;
import kr.co.campfire.admin.registration.dto.AdminCampRegistrationTagDto;
import kr.co.campfire.business.registration.dto.BusinessRegistrationDto;
import kr.co.campfire.business.registration.dto.CampRegistrationAmenityDto;
import kr.co.campfire.business.registration.dto.CampRegistrationDto;
import kr.co.campfire.business.registration.dto.CampRegistrationPhotoDto;
import kr.co.campfire.business.registration.dto.CampRegistrationTagDto;
import kr.co.campfire.common.dto.PageInfo;

@Repository
public class BusinessRegistrationDao {
	public BusinessRegistrationDto selectBusinessRegistration(SqlSessionTemplate sqlSession, int memberNum) {
		return sqlSession.selectOne("registrationMapper.selectBusinessRegistration", memberNum);
	}

	public int checkBusinessRegistration(SqlSessionTemplate sqlSession, int memberNum) {
		return sqlSession.selectOne("registrationMapper.checkBusinessRegistration", memberNum);
	}
	
	public int checkBusinessNum(SqlSessionTemplate sqlSession, String brNum) {
		return sqlSession.selectOne("registrationMapper.checkBusinessNum", brNum);
	}

	public int insertBusiness(SqlSessionTemplate sqlSession, BusinessRegistrationDto brd) {
		return sqlSession.insert("registrationMapper.insertBusiness", brd);
	}
	
	public int deleteBusiness(SqlSessionTemplate sqlSession, int memberNum) {
		return sqlSession.insert("registrationMapper.deleteBusiness", memberNum);
	}

	public int insertCampInfo(SqlSessionTemplate sqlSession, CampRegistrationDto crd) {
		return sqlSession.insert("registrationMapper.insertCampInfo", crd);
	}

	public int insertCampAmenity(SqlSessionTemplate sqlSession, CampRegistrationAmenityDto cad) {
		return sqlSession.insert("registrationMapper.insertCampAmenity", cad);
	}

	public int insertCampTag(SqlSessionTemplate sqlSession, CampRegistrationTagDto ctd) {
		return sqlSession.insert("registrationMapper.insertCampTag", ctd);
	}

	public int insertCampPhoto(SqlSessionTemplate sqlSession, CampRegistrationPhotoDto cpd) {
		return sqlSession.insert("registrationMapper.insertCampPhoto", cpd);
	}

	public int selectCampNum(SqlSessionTemplate sqlSession, CampRegistrationDto crd) {
		return sqlSession.selectOne("registrationMapper.selectCampNum", crd);
	}
	
	public int selectMyCampRegistrationListCount(SqlSessionTemplate sqlSession, int memberNum) {
		return sqlSession.selectOne("registrationMapper.selectMyCampRegistrationListCount", memberNum);
	}
	
	public List<CampRegistrationDto> selectMyCampRegistrationList(SqlSessionTemplate sqlSession, PageInfo pi, int memberNum) {
		int offset = (pi.getCurrentPage() - 1) * pi.getBoardLimit();
		int limit = pi.getBoardLimit();
		RowBounds rowBounds = new RowBounds(offset, limit);
		return sqlSession.selectList("registrationMapper.selectMyCampRegistrationList", memberNum,  rowBounds);
	}
	
	public CampRegistrationDto selectCampRegistration(SqlSessionTemplate sqlSession, int campNum) {
		return sqlSession.selectOne("registrationMapper.selectCampRegistration", campNum);
	}
	
	public List<CampRegistrationPhotoDto> selectCampRegistrationPhotoList(SqlSessionTemplate sqlSession, int campNum) {
		return sqlSession.selectList("registrationMapper.selectCampRegistrationPhotoList", campNum);
	}
	
	public List<CampRegistrationAmenityDto> selectCampRegistrationAmenityList(SqlSessionTemplate sqlSession, int campNum) {
		return sqlSession.selectList("registrationMapper.selectCampRegistrationAmenityList", campNum);
	}
	
	public List<CampRegistrationTagDto> selectCampRegistrationTagList(SqlSessionTemplate sqlSession, int campNum) {
		return sqlSession.selectList("registrationMapper.selectCampRegistrationTagList", campNum);
	}
	
	public int deleteCampRegistration(SqlSessionTemplate sqlSession, int campNum) {
		return sqlSession.delete("registrationMapper.deleteCampRegistration", campNum);
	}
	
	public int deleteCampRegistrationAmenityList(SqlSessionTemplate sqlSession, int campNum) {
		return sqlSession.delete("registrationMapper.deleteCampRegistrationAmenityList", campNum);
	}
	
	public int deleteCampRegistrationTagList(SqlSessionTemplate sqlSession, int campNum) {
		return sqlSession.delete("registrationMapper.deleteCampRegistrationTagList", campNum);
	}
	
	public int deleteCampRegistrationPhotoList(SqlSessionTemplate sqlSession, int campNum) {
		return sqlSession.delete("registrationMapper.deleteCampRegistrationPhotoList", campNum);
	}
}
