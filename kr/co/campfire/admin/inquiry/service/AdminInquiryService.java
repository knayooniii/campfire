package kr.co.campfire.admin.inquiry.service;

import java.util.List;

import kr.co.campfire.admin.inquiry.dto.AdminInquiryDto;
import kr.co.campfire.common.dto.PageInfo;

public interface AdminInquiryService {
	int selectListAllCount();
	
	List<AdminInquiryDto> selectListAll(PageInfo pi);
	
	int selectListNoAnswerCount();
	
	List<AdminInquiryDto> selectListNoAnswer(PageInfo pi);
	
	AdminInquiryDto detailInquiry(int inNum);
	
	int insertAnswer(AdminInquiryDto aid);
	
	int deleteInquiry(int inNum);
	
	int deleteAnswer(int inNum);
	
	int updateInquiryIA(int inNum);
	
	int updateInquiryDA(int inNum);
}
