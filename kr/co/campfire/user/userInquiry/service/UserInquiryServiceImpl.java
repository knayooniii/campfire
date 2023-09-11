package kr.co.campfire.user.userInquiry.service;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.campfire.common.dto.PageInfo;
import kr.co.campfire.user.userInquiry.dao.UserInquiryDao;
import kr.co.campfire.user.userInquiry.dto.UserInquiryDto;

@Service
public class UserInquiryServiceImpl implements UserInquiryService{

	@Autowired
	private UserInquiryDao userInquiryDao;
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	@Override
	public int selectListAllCount() {
		return userInquiryDao.selectListAllCount(sqlSession);
	}

	@Override
	public int selectListTitleCount(String searchTxt) {
		return userInquiryDao.selectListTitleCount(sqlSession, searchTxt);
	}

	@Override
	public int selectListContextCount(String searchTxt) {
		return userInquiryDao.selectListContextCount(sqlSession, searchTxt);
	}

	@Override
	public int selectListWriterCount(String searchTxt) {
		return userInquiryDao.selectListWriterCount(sqlSession, searchTxt);
	}

	@Override
	public List<UserInquiryDto> selectListAll(PageInfo pi) {
		return userInquiryDao.selectListAll(sqlSession, pi);
	}

	@Override
	public List<UserInquiryDto> selectListTitle(PageInfo pi, String searchTxt) {
		return userInquiryDao.selectListTitle(sqlSession, pi, searchTxt);
	}

	@Override
	public List<UserInquiryDto> selectListContext(PageInfo pi, String searchTxt) {
		return userInquiryDao.selectListContext(sqlSession, pi, searchTxt);
	}

	@Override
	public List<UserInquiryDto> selectListWriter(PageInfo pi, String searchTxt) {
		return userInquiryDao.selectListWriter(sqlSession, pi, searchTxt);
	}

	@Override
	public UserInquiryDto detailInquiry(int inNum) {
		return userInquiryDao.detailInquiry(sqlSession, inNum);
	}

	@Override
	public int insertInquiry(UserInquiryDto uid) {
		return userInquiryDao.insertInquiry(sqlSession, uid);
	}

	@Override
	public int deleteInquiry(int inNum) {
		return userInquiryDao.deleteInquiry(sqlSession, inNum);
	}

	@Override
	public int updateInquiry(UserInquiryDto uid) {
		// TODO Auto-generated method stub
		return userInquiryDao.updateInquiry(sqlSession, uid);
	}

}
