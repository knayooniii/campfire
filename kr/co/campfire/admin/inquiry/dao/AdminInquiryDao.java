package kr.co.campfire.admin.inquiry.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import kr.co.campfire.admin.inquiry.dto.AdminInquiryDto;
import kr.co.campfire.common.dto.PageInfo;

@Repository
public class AdminInquiryDao {
	public int selectListAllCount(SqlSessionTemplate sqlSession) {
		return sqlSession.selectOne("answerMapper.selectAllAnswerListCount");
	}


	public List<AdminInquiryDto> selectListAll(SqlSessionTemplate sqlSession, PageInfo pi) {
		int offset = (pi.getCurrentPage() - 1) * pi.getBoardLimit();
		int limit = pi.getBoardLimit();
		RowBounds rowBounds = new RowBounds(offset, limit);
		return sqlSession.selectList("answerMapper.selectAllAnswerList", null,  rowBounds);
	}
	
	public int selectListNoAnswerCount(SqlSessionTemplate sqlSession) {
		return sqlSession.selectOne("answerMapper.selectNoAnswerListCount");
	}


	public List<AdminInquiryDto> selectListNoAnswer(SqlSessionTemplate sqlSession, PageInfo pi) {
		int offset = (pi.getCurrentPage() - 1) * pi.getBoardLimit();
		int limit = pi.getBoardLimit();
		RowBounds rowBounds = new RowBounds(offset, limit);
		return sqlSession.selectList("answerMapper.selectNoAnswerList", null,  rowBounds);
	}


	public AdminInquiryDto detailInquiry(SqlSessionTemplate sqlSession, int inNum) {
		return sqlSession.selectOne("answerMapper.detailInquiry", inNum);
	}
	
	public int insertAnswer(SqlSessionTemplate sqlSession, AdminInquiryDto aid) {
	      return sqlSession.insert("answerMapper.insertAnswer", aid);
	   }

	public int deleteInquiry(SqlSessionTemplate sqlSession, int inNum) {
	      return sqlSession.delete("answerMapper.deleteInquiry", inNum);
	   }
	
	public int deleteAnswer(SqlSessionTemplate sqlSession, int inNum) {
	      return sqlSession.delete("answerMapper.deleteAnswer", inNum);
	   }
	
	public int updateInquiryIA(SqlSessionTemplate sqlSession, int inNum) {
	      return sqlSession.update("answerMapper.updateInquiryIA", inNum);
	   }
	
	public int updateInquiryDA(SqlSessionTemplate sqlSession, int inNum) {
	      return sqlSession.update("answerMapper.updateInquiryDA", inNum);
	   }
}
