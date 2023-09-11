package kr.co.campfire.user.userBoard.service;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.campfire.common.dto.PageInfo;
import kr.co.campfire.user.userBoard.dao.UserBoardDao;
import kr.co.campfire.user.userBoard.dto.UserBoardDto;
import kr.co.campfire.user.userBoard.dto.UserBoardLikeDto;
import kr.co.campfire.user.userBoard.dto.UserBoardReplyDto;

@Service
public class UserBoardServiceImpl implements UserBoardService{

	@Autowired
	private UserBoardDao userBoardDao;
	
	@Autowired
	private SqlSessionTemplate sqlSession;

	@Override
	public int insertBoard(UserBoardDto ubd) {
		return userBoardDao.insertBoard(sqlSession, ubd);
	}
	
	@Override
	public int selectListAllCount(UserBoardDto ubd) {
		return userBoardDao.selectListAllCount(sqlSession, ubd);
	}

	@Override
	public int selectListTitleCount(UserBoardDto ubd) {
		return userBoardDao.selectListTitleCount(sqlSession, ubd);
	}

	@Override
	public int selectListContextCount(UserBoardDto ubd) {
		return userBoardDao.selectListContextCount(sqlSession, ubd);
	}

	@Override
	public int selectListWriterCount(UserBoardDto ubd) {
		return userBoardDao.selectListWriterCount(sqlSession, ubd);
	}

	@Override
	public List<UserBoardDto> selectListPopular(UserBoardDto ubd) {
		return userBoardDao.selectListPopular(sqlSession, ubd);
	}
	
	@Override
	public List<UserBoardDto> selectListAll(PageInfo pi, UserBoardDto ubd) {
		return userBoardDao.selectListAll(sqlSession, pi, ubd);
	}

	@Override
	public List<UserBoardDto> selectListTitle(PageInfo pi, UserBoardDto ubd) {
		return userBoardDao.selectListTitle(sqlSession, pi, ubd);
	}

	@Override
	public List<UserBoardDto> selectListContext(PageInfo pi, UserBoardDto ubd) {
		return userBoardDao.selectListContext(sqlSession, pi, ubd);
	}

	@Override
	public List<UserBoardDto> selectListWriter(PageInfo pi, UserBoardDto ubd) {
		return userBoardDao.selectListWriter(sqlSession, pi, ubd);
	}

	@Override
	public UserBoardDto selectPost(int postNum) {
		return userBoardDao.selectPost(sqlSession, postNum);
	}

	@Override
	public int updateViewCount(UserBoardDto ubd) {
		return userBoardDao.updateViewCount(sqlSession, ubd);
	}
	
	@Override
	public int deleteBoard(int postNum) {
		return userBoardDao.deleteBoard(sqlSession, postNum);
	}
	
	@Override
	public int updateBoard(UserBoardDto ubd) {
		return userBoardDao.updateBoard(sqlSession, ubd);
	}

	@Override
	public int selectLikeCount(int postNum) {
		return userBoardDao.selectLikeCount(sqlSession, postNum);
	}

	@Override
	public int selectCheckLike(UserBoardLikeDto ubld) {
		return userBoardDao.selectCheckLike(sqlSession, ubld);
	}

	@Override
	public int likePost(UserBoardLikeDto ubld) {
		return userBoardDao.likePost(sqlSession, ubld);
	}
	
	@Override
	public int unlikePost(UserBoardLikeDto ubld) {
		return userBoardDao.unlikePost(sqlSession, ubld);
	}

	@Override
	public int selectReplyCount(int postNum) {
		return userBoardDao.selectReplyCount(sqlSession, postNum);
	}
	
	@Override
	public List<UserBoardReplyDto> selectPostReply(int postNum){
		return userBoardDao.selectPostReply(sqlSession, postNum);
	}

	
	@Override
	public int insertReply(UserBoardReplyDto ubrd) {
		return userBoardDao.insertReply(sqlSession, ubrd);
	}	

	@Override
	public  List<UserBoardReplyDto> selectReplyOne(String postReplyContent) {
		return userBoardDao.selectReplyOne(sqlSession, postReplyContent);
	}
	
	@Override
	public int selectReplyLikeCount(int postReplyNum) {
		return userBoardDao.selectReplyLikeCount(sqlSession, postReplyNum);
	}
	
	@Override
	public int selectReplyCheckLike(UserBoardReplyDto ubrd) {
		return userBoardDao.selectReplyCheckLike(sqlSession, ubrd);
	}
	
	@Override
	public int replyLikePost(UserBoardReplyDto ubrd) {
		return userBoardDao.replyLikePost(sqlSession, ubrd);
	}
	
	@Override
	public int replyUnlikePost(UserBoardReplyDto ubrd) {
		return userBoardDao.replyUnlikePost(sqlSession, ubrd);
	}

	@Override
	public int deleteReply(int postReplyNum) {
		return userBoardDao.deleteReply(sqlSession, postReplyNum);
	}
}
