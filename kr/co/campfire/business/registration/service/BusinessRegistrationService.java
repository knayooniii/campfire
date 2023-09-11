package kr.co.campfire.business.registration.service;

import java.util.List;

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

public interface BusinessRegistrationService {
	BusinessRegistrationDto selectBusinessRegistration(int memberNum);
	
	int checkBusinessRegistration(int memberNum);
	
	int checkBusinessNum(String brNum);
	
	int insertBusiness(BusinessRegistrationDto brd);
	
	int deleteBusiness(int memberNum);
	
	int insertCampInfo(CampRegistrationDto crd);
	
	int insertCampAmenity(CampRegistrationAmenityDto cad);
	
	int insertCampTag(CampRegistrationTagDto ctd);
	
	int insertCampPhoto(CampRegistrationPhotoDto cpd);
	
	int selectCampNum(CampRegistrationDto crd);
	
	int selectMyCampRegistrationListCount(int memberNum);
	
	List<CampRegistrationDto> selectMyCampRegistrationList(PageInfo pi, int memberNum);
	
	CampRegistrationDto selectCampRegistration(int campNum);
	
	List<CampRegistrationPhotoDto> selectCampRegistrationPhotoList(int campNum);
	
	List<CampRegistrationAmenityDto> selectCampRegistrationAmenityList(int campNum);
	
	List<CampRegistrationTagDto> selectCampRegistrationTagList(int campNum);
	
	int deleteCampRegistration(int campNum);
	
	int deleteCampRegistrationAmenityList(int campNum);
	
	int deleteCampRegistrationTagList(int campNum);
	
	int deleteCampRegistrationPhotoList(int campNum);
}
