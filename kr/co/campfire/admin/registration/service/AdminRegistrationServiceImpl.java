package kr.co.campfire.admin.registration.service;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.campfire.admin.registration.dao.AdminRegistrationDao;
import kr.co.campfire.admin.registration.dto.AdminBusinessRegistrationDto;
import kr.co.campfire.admin.registration.dto.AdminCampRegistrationAmenityDto;
import kr.co.campfire.admin.registration.dto.AdminCampRegistrationDto;
import kr.co.campfire.admin.registration.dto.AdminCampRegistrationPhotoDto;
import kr.co.campfire.admin.registration.dto.AdminCampRegistrationTagDto;
import kr.co.campfire.common.dto.PageInfo;

@Service
public class AdminRegistrationServiceImpl implements AdminRegistrationService {
	@Autowired
	private AdminRegistrationDao adminRegistrationDao;

	@Autowired
	private SqlSessionTemplate sqlSession;

	//사업자 등록 관리
	@Override
	public int selectBusinessRegistrationListCount() {
		return adminRegistrationDao.selectBusinessRegistrationListCount(sqlSession);
	}

	@Override
	public List<AdminBusinessRegistrationDto> selectBusinessRegistrationList(PageInfo pi) {
		return adminRegistrationDao.selectBusinessRegistrationList(sqlSession, pi);
	}

	@Override
	public int approvalBusinessRegistration(String brNum) {
		return adminRegistrationDao.approvalBusinessRegistration(sqlSession, brNum);
	}

	@Override
	public int refuseBusinessRegistration(AdminBusinessRegistrationDto abrd) {
		return adminRegistrationDao.refuseBusinessRegistration(sqlSession, abrd);
	}
	
	//캠핑장 등록 관리
	@Override
	public int selectCampRegistrationListCount() {
		return adminRegistrationDao.selectCampRegistrationListCount(sqlSession);
	}

	@Override
	public List<AdminCampRegistrationDto> selectCampRegistrationList(PageInfo pi) {
		return adminRegistrationDao.selectCampRegistrationList(sqlSession, pi);
	}
	
	@Override
	public AdminCampRegistrationDto selectCampRegistration(int campNum) {
		return adminRegistrationDao.selectCampRegistration(sqlSession, campNum);
	}

	@Override
	public List<AdminCampRegistrationPhotoDto> selectCampRegistrationPhotoList(int campNum) {
		return adminRegistrationDao.selectCampRegistrationPhotoList(sqlSession, campNum);
	}

	@Override
	public List<AdminCampRegistrationAmenityDto> selectCampRegistrationAmenityList(int campNum) {
		return adminRegistrationDao.selectCampRegistrationAmenityList(sqlSession, campNum);
	}

	@Override
	public List<AdminCampRegistrationTagDto> selectCampRegistrationTagList(int campNum) {
		return adminRegistrationDao.selectCampRegistrationTagList(sqlSession, campNum);
	}
	
	@Override
	public int approvalCampRegistration(int campNum) {
		return adminRegistrationDao.approvalCampRegistration(sqlSession, campNum);
	}

	@Override
	public int refuseCampRegistration(AdminCampRegistrationDto acrd) {
		return adminRegistrationDao.refuseCampRegistration(sqlSession, acrd);
	}

}
