package kr.co.campfire.user.userInquiry.service;

import java.util.List;

import kr.co.campfire.user.userInquiry.dto.UserInquiryDto;
import kr.co.campfire.common.dto.PageInfo;

public interface UserInquiryService {
	int selectListAllCount();
	
	int selectListTitleCount(String searchTxt);
	
	int selectListContextCount(String searchTxt);
	
	int selectListWriterCount(String searchTxt);
	
	List<UserInquiryDto> selectListAll(PageInfo pi);
	
	List<UserInquiryDto> selectListTitle(PageInfo pi, String searchTxt);
	
	List<UserInquiryDto> selectListContext(PageInfo pi, String searchTxt);
	
	List<UserInquiryDto> selectListWriter(PageInfo pi, String searchTxt);
	
	UserInquiryDto detailInquiry(int inNum);
	
	int insertInquiry(UserInquiryDto uid);
	
	int deleteInquiry(int inNum);
	
	int updateInquiry(UserInquiryDto uid);
	
}
