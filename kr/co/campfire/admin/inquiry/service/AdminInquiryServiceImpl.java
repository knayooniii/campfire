package kr.co.campfire.admin.inquiry.service;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.campfire.admin.inquiry.dao.AdminInquiryDao;
import kr.co.campfire.admin.inquiry.dto.AdminInquiryDto;
import kr.co.campfire.common.dto.PageInfo;

@Service
public class AdminInquiryServiceImpl implements AdminInquiryService{

	@Autowired
	private AdminInquiryDao adminInquiryDao;
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	@Override
	public int selectListNoAnswerCount() {
		return adminInquiryDao.selectListNoAnswerCount(sqlSession);
	}

	@Override
	public List<AdminInquiryDto> selectListNoAnswer(PageInfo pi) {
		return adminInquiryDao.selectListNoAnswer(sqlSession, pi);
	}
	
	@Override
	public int selectListAllCount() {
		return adminInquiryDao.selectListAllCount(sqlSession);
	}

	@Override
	public List<AdminInquiryDto> selectListAll(PageInfo pi) {
		return adminInquiryDao.selectListAll(sqlSession, pi);
	}

	@Override
	public AdminInquiryDto detailInquiry(int inNum) {
		return adminInquiryDao.detailInquiry(sqlSession, inNum);
	}

	@Override
	public int insertAnswer(AdminInquiryDto aid) {
		return adminInquiryDao.insertAnswer(sqlSession, aid);
	}

	@Override
	public int deleteInquiry(int inNum) {
		return adminInquiryDao.deleteInquiry(sqlSession, inNum);
	}
	@Override
	public int deleteAnswer(int inNum) {
		return adminInquiryDao.deleteAnswer(sqlSession, inNum);
	}

	@Override
	public int updateInquiryIA(int inNum) {
		// TODO Auto-generated method stub
		return adminInquiryDao.updateInquiryIA(sqlSession, inNum);
	}
	
	@Override
	public int updateInquiryDA(int inNum) {
		// TODO Auto-generated method stub
		return adminInquiryDao.updateInquiryDA(sqlSession, inNum);
	}

}
