package kr.co.campfire.admin.registration.service;

import java.util.List;

import kr.co.campfire.admin.registration.dto.AdminBusinessRegistrationDto;
import kr.co.campfire.admin.registration.dto.AdminCampRegistrationAmenityDto;
import kr.co.campfire.admin.registration.dto.AdminCampRegistrationDto;
import kr.co.campfire.admin.registration.dto.AdminCampRegistrationPhotoDto;
import kr.co.campfire.admin.registration.dto.AdminCampRegistrationTagDto;
import kr.co.campfire.common.dto.PageInfo;

public interface AdminRegistrationService {
	int selectBusinessRegistrationListCount();
	
	List<AdminBusinessRegistrationDto> selectBusinessRegistrationList(PageInfo pi);
	
	int approvalBusinessRegistration(String brNum);
	
	int refuseBusinessRegistration(AdminBusinessRegistrationDto abrd);
	
	//캠핑장 등록 관리
	int selectCampRegistrationListCount();
	
	List<AdminCampRegistrationDto> selectCampRegistrationList(PageInfo pi);
	
	AdminCampRegistrationDto selectCampRegistration(int campNum);
	
	List<AdminCampRegistrationPhotoDto> selectCampRegistrationPhotoList(int campNum);
	
	List<AdminCampRegistrationAmenityDto> selectCampRegistrationAmenityList(int campNum);
	
	List<AdminCampRegistrationTagDto> selectCampRegistrationTagList(int campNum);
	
	int approvalCampRegistration(int campNum);
	
	int refuseCampRegistration(AdminCampRegistrationDto acrd);
}
