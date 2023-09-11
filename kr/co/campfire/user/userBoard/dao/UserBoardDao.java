package kr.co.campfire.user.userBoard.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import kr.co.campfire.common.dto.PageInfo;
import kr.co.campfire.user.userBoard.dto.UserBoardDto;
import kr.co.campfire.user.userBoard.dto.UserBoardLikeDto;
import kr.co.campfire.user.userBoard.dto.UserBoardReplyDto;

@Repository
public class UserBoardDao {
	public int insertBoard(SqlSessionTemplate sqlSession, UserBoardDto ubd) {
		  return sqlSession.insert("boardMapper.insertBoard", ubd);
	}
	
	public int selectListAllCount(SqlSessionTemplate sqlSession, UserBoardDto ubd) {
		return sqlSession.selectOne("boardMapper.selectListAllCount",ubd);
	}

	public int selectListTitleCount(SqlSessionTemplate sqlSession, UserBoardDto ubd) {
		return sqlSession.selectOne("boardMapper.selectListTitleCount", ubd);
	}

	public int selectListContextCount(SqlSessionTemplate sqlSession, UserBoardDto ubd) {
		return sqlSession.selectOne("boardMapper.selectListContextCount", ubd);
	}

	public int selectListWriterCount(SqlSessionTemplate sqlSession, UserBoardDto ubd) {
		return sqlSession.selectOne("boardMapper.selectListWriterCount", ubd);
	}

	public List<UserBoardDto> selectListPopular(SqlSessionTemplate sqlSession, UserBoardDto ubd) {
		return sqlSession.selectList("boardMapper.selectListPopular",ubd);
	}
	
	public List<UserBoardDto> selectListAll(SqlSessionTemplate sqlSession, PageInfo pi, UserBoardDto ubd) {
		int offset = (pi.getCurrentPage() - 1) * pi.getBoardLimit();
		int limit = pi.getBoardLimit();
		RowBounds rowBounds = new RowBounds(offset, limit);
		return sqlSession.selectList("boardMapper.selectListAll",ubd,  rowBounds);
	}

	public List<UserBoardDto> selectListTitle(SqlSessionTemplate sqlSession, PageInfo pi, UserBoardDto ubd) {
		int offset = (pi.getCurrentPage() - 1) * pi.getBoardLimit();
		int limit = pi.getBoardLimit();
		RowBounds rowBounds = new RowBounds(offset, limit);
		return sqlSession.selectList("boardMapper.selectListTitle", ubd, rowBounds);
	}

	public List<UserBoardDto> selectListContext(SqlSessionTemplate sqlSession, PageInfo pi, UserBoardDto ubd) {
		int offset = (pi.getCurrentPage() - 1) * pi.getBoardLimit();
		int limit = pi.getBoardLimit();
		RowBounds rowBounds = new RowBounds(offset, limit);
		return sqlSession.selectList("boardMapper.selectListContext", ubd, rowBounds);
	}

	public List<UserBoardDto> selectListWriter(SqlSessionTemplate sqlSession, PageInfo pi, UserBoardDto ubd) {
		int offset = (pi.getCurrentPage() - 1) * pi.getBoardLimit();
		int limit = pi.getBoardLimit();
		RowBounds rowBounds = new RowBounds(offset, limit);
		return sqlSession.selectList("boardMapper.selectListWriter", ubd, rowBounds);
	}

	public UserBoardDto selectPost(SqlSessionTemplate sqlSession, int postNum) {
		return sqlSession.selectOne("boardMapper.selectPost", postNum);
	}
	
	public int updateViewCount(SqlSessionTemplate sqlSession, UserBoardDto ubd) {
		return sqlSession.update("boardMapper.updateViewCount", ubd);
	}
	
	public int deleteBoard(SqlSessionTemplate sqlSession, int postNum) {
		return sqlSession.delete("boardMapper.deleteBoard", postNum);
	}
	
	public int updateBoard(SqlSessionTemplate sqlSession, UserBoardDto ubd) {
		  return sqlSession.update("boardMapper.updateBoard", ubd);
	}
	
	//좋아요 수 반환
	public int selectLikeCount(SqlSessionTemplate sqlSession, int postNum) {
		return sqlSession.selectOne("boardMapper.selectLikeCount", postNum);
	}
	// 게시물 좋아요 했는지 체크
	public int selectCheckLike(SqlSessionTemplate sqlSession, UserBoardLikeDto ubld) {
		  return sqlSession.selectOne("boardMapper.selectCheckLike", ubld);
	}
	//좋아요 추가
	public int likePost(SqlSessionTemplate sqlSession, UserBoardLikeDto ubld) {
		  return sqlSession.insert("boardMapper.likePost", ubld);
	}
	// 좋아요 삭제
	public int unlikePost(SqlSessionTemplate sqlSession, UserBoardLikeDto ubld) {
		  return sqlSession.delete("boardMapper.unlikePost", ubld);
	}
	//댓글 수 구하기
	public int selectReplyCount(SqlSessionTemplate sqlSession, int postNum) {
		return sqlSession.selectOne("boardMapper.selectReplyCount", postNum);
	}
	//댓글 리스트
	public List<UserBoardReplyDto> selectPostReply(SqlSessionTemplate sqlSession, int postNum){
		return sqlSession.selectList("boardMapper.selectPostReply", postNum);
	}
	
	//댓글 추가
	public int insertReply(SqlSessionTemplate sqlSession, UserBoardReplyDto ubrd) {
		  return sqlSession.insert("boardMapper.insertReply", ubrd);
	}
	public List<UserBoardReplyDto> selectReplyOne(SqlSessionTemplate sqlSession, String postReplyContent) {
		return sqlSession.selectList("boardMapper.selectReplyOne", postReplyContent);
	}
	// 게시물 좋아요 했는지 체크
	public int selectReplyCheckLike(SqlSessionTemplate sqlSession, UserBoardReplyDto ubrd) {
		  return sqlSession.selectOne("boardMapper.selectReplyCheckLike", ubrd);
	}
	//댓글 좋아요 수 반환
	public int selectReplyLikeCount(SqlSessionTemplate sqlSession, int postReplyNum) {
		return sqlSession.selectOne("boardMapper.selectReplyLikeCount", postReplyNum);
	}
	
	//댓글 좋아요 추가
	public int replyLikePost(SqlSessionTemplate sqlSession, UserBoardReplyDto ubrd) {
		  return sqlSession.insert("boardMapper.replyLikePost", ubrd);
	}
	//댓글 좋아요 삭제
	public int replyUnlikePost(SqlSessionTemplate sqlSession, UserBoardReplyDto ubrd) {
		  return sqlSession.delete("boardMapper.replyUnlikePost", ubrd);
	}
	
	public int deleteReply(SqlSessionTemplate sqlSession, int postReplyNum) {
		return sqlSession.delete("boardMapper.deleteReply", postReplyNum);
	}
}
