package kr.co.campfire.user.userInquiry.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import kr.co.campfire.common.dto.PageInfo;
import kr.co.campfire.user.userInquiry.dto.UserInquiryDto;

@Repository
public class UserInquiryDao {
	public int selectListAllCount(SqlSessionTemplate sqlSession) {
		return sqlSession.selectOne("inquiryMapper.selectListAllCount");
	}

	public int selectListTitleCount(SqlSessionTemplate sqlSession, String searchTxt) {
		return sqlSession.selectOne("inquiryMapper.selectListTitleCount", searchTxt);
	}

	public int selectListContextCount(SqlSessionTemplate sqlSession, String searchTxt) {
		return sqlSession.selectOne("inquiryMapper.selectListContextCount", searchTxt);
	}

	public int selectListWriterCount(SqlSessionTemplate sqlSession, String searchTxt) {
		return sqlSession.selectOne("inquiryMapper.selectListWriterCount", searchTxt);
	}

	public List<UserInquiryDto> selectListAll(SqlSessionTemplate sqlSession, PageInfo pi) {
		int offset = (pi.getCurrentPage() - 1) * pi.getBoardLimit();
		int limit = pi.getBoardLimit();
		RowBounds rowBounds = new RowBounds(offset, limit);
		return sqlSession.selectList("inquiryMapper.selectListAll",null,  rowBounds);
	}

	public List<UserInquiryDto> selectListTitle(SqlSessionTemplate sqlSession, PageInfo pi, String searchTxt) {
		int offset = (pi.getCurrentPage() - 1) * pi.getBoardLimit();
		int limit = pi.getBoardLimit();
		RowBounds rowBounds = new RowBounds(offset, limit);
		return sqlSession.selectList("inquiryMapper.selectListTitle", searchTxt, rowBounds);
	}

	public List<UserInquiryDto> selectListContext(SqlSessionTemplate sqlSession, PageInfo pi, String searchTxt) {
		int offset = (pi.getCurrentPage() - 1) * pi.getBoardLimit();
		int limit = pi.getBoardLimit();
		RowBounds rowBounds = new RowBounds(offset, limit);
		return sqlSession.selectList("inquiryMapper.selectListContext", searchTxt, rowBounds);
	}

	public List<UserInquiryDto> selectListWriter(SqlSessionTemplate sqlSession, PageInfo pi, String searchTxt) {
		int offset = (pi.getCurrentPage() - 1) * pi.getBoardLimit();
		int limit = pi.getBoardLimit();
		RowBounds rowBounds = new RowBounds(offset, limit);
		return sqlSession.selectList("inquiryMapper.selectListWriter", searchTxt, rowBounds);
	}

	public UserInquiryDto detailInquiry(SqlSessionTemplate sqlSession, int inNum) {
		return sqlSession.selectOne("inquiryMapper.detailInquiry", inNum);
	}
	
	public int insertInquiry(SqlSessionTemplate sqlSession, UserInquiryDto uid) {
	      return sqlSession.insert("inquiryMapper.insertInquiry", uid);
	   }

	public int deleteInquiry(SqlSessionTemplate sqlSession, int inNum) {
	      return sqlSession.delete("inquiryMapper.deleteInquiry", inNum);
	   }
	
	public int updateInquiry(SqlSessionTemplate sqlSession, UserInquiryDto uid) {
	      return sqlSession.update("inquiryMapper.updateInquiry", uid);
	   }
}
