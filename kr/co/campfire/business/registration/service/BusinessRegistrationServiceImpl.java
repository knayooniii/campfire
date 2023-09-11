package kr.co.campfire.business.registration.service;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.campfire.admin.registration.dto.AdminCampRegistrationAmenityDto;
import kr.co.campfire.admin.registration.dto.AdminCampRegistrationPhotoDto;
import kr.co.campfire.admin.registration.dto.AdminCampRegistrationTagDto;
import kr.co.campfire.business.registration.dao.BusinessRegistrationDao;
import kr.co.campfire.business.registration.dto.BusinessRegistrationDto;
import kr.co.campfire.business.registration.dto.CampRegistrationAmenityDto;
import kr.co.campfire.business.registration.dto.CampRegistrationDto;
import kr.co.campfire.business.registration.dto.CampRegistrationTagDto;
import kr.co.campfire.common.dto.PageInfo;
import kr.co.campfire.business.registration.dto.CampRegistrationPhotoDto;

@Service
public class BusinessRegistrationServiceImpl implements BusinessRegistrationService{
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	@Autowired
	private BusinessRegistrationDao businessRegistrationDao;
	
	//사업자 등록증
	@Override
	public BusinessRegistrationDto selectBusinessRegistration(int memberNum) {
		return businessRegistrationDao.selectBusinessRegistration(sqlSession, memberNum);
	}
	
	@Override
	public int checkBusinessRegistration(int memberNum) {
		return businessRegistrationDao.checkBusinessRegistration(sqlSession, memberNum);
	}
	
	@Override
	public int checkBusinessNum(String brNum) {
		return businessRegistrationDao.checkBusinessNum(sqlSession, brNum);
	}
	
	@Override
	public int insertBusiness(BusinessRegistrationDto brd) {
		return businessRegistrationDao.insertBusiness(sqlSession, brd);
	}

	@Override
	public int deleteBusiness(int memberNum) {
		return businessRegistrationDao.deleteBusiness(sqlSession, memberNum);
	}
	//캠핑장 등록
	@Override
	public int insertCampInfo(CampRegistrationDto crd) {
		return businessRegistrationDao.insertCampInfo(sqlSession, crd);
	}

	@Override
	public int insertCampAmenity(CampRegistrationAmenityDto cad) {
		return businessRegistrationDao.insertCampAmenity(sqlSession, cad);
	}

	@Override
	public int insertCampTag(CampRegistrationTagDto ctd) {
		return businessRegistrationDao.insertCampTag(sqlSession, ctd);
	}
	
	@Override
	public int selectCampNum(CampRegistrationDto crd) {
		return businessRegistrationDao.selectCampNum(sqlSession, crd);
	}

	@Override
	public int insertCampPhoto(CampRegistrationPhotoDto cpd) {
		return businessRegistrationDao.insertCampPhoto(sqlSession, cpd);
	}

	@Override
	public int selectMyCampRegistrationListCount(int memberNum) {
		return businessRegistrationDao.selectMyCampRegistrationListCount(sqlSession, memberNum);
	}

	@Override
	public List<CampRegistrationDto> selectMyCampRegistrationList(PageInfo pi, int memberNum) {
		return businessRegistrationDao.selectMyCampRegistrationList(sqlSession, pi, memberNum);
	}
	@Override
	public List<CampRegistrationPhotoDto> selectCampRegistrationPhotoList(int campNum) {
		return businessRegistrationDao.selectCampRegistrationPhotoList(sqlSession, campNum);
	}

	@Override
	public List<CampRegistrationAmenityDto> selectCampRegistrationAmenityList(int campNum) {
		return businessRegistrationDao.selectCampRegistrationAmenityList(sqlSession, campNum);
	}

	@Override
	public List<CampRegistrationTagDto> selectCampRegistrationTagList(int campNum) {
		return businessRegistrationDao.selectCampRegistrationTagList(sqlSession, campNum);
	}

	@Override
	public CampRegistrationDto selectCampRegistration(int campNum) {
		return businessRegistrationDao.selectCampRegistration(sqlSession, campNum);
	}

	@Override
	public int deleteCampRegistration(int campNum) {
		return businessRegistrationDao.deleteCampRegistration(sqlSession, campNum);
	}

	@Override
	public int deleteCampRegistrationAmenityList(int campNum) {
		// TODO Auto-generated method stub
		return businessRegistrationDao.deleteCampRegistrationAmenityList(sqlSession, campNum);
	}

	@Override
	public int deleteCampRegistrationTagList(int campNum) {
		// TODO Auto-generated method stub
		return businessRegistrationDao.deleteCampRegistrationTagList(sqlSession, campNum);
	}

	@Override
	public int deleteCampRegistrationPhotoList(int campNum) {
		// TODO Auto-generated method stub
		return businessRegistrationDao.deleteCampRegistrationPhotoList(sqlSession, campNum);
	}

}
