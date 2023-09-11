package kr.co.campfire.user.userBoard.service;

import java.util.List;

import kr.co.campfire.common.dto.PageInfo;
import kr.co.campfire.user.userBoard.dto.UserBoardDto;
import kr.co.campfire.user.userBoard.dto.UserBoardLikeDto;
import kr.co.campfire.user.userBoard.dto.UserBoardReplyDto;

public interface UserBoardService {
	int insertBoard(UserBoardDto ubd);

	int selectListAllCount(UserBoardDto ubd);

	int selectListTitleCount(UserBoardDto ubd);

	int selectListContextCount(UserBoardDto ubd);

	int selectListWriterCount(UserBoardDto ubd);

	List<UserBoardDto> selectListPopular(UserBoardDto ubd);

	List<UserBoardDto> selectListAll(PageInfo pi, UserBoardDto ubd);

	List<UserBoardDto> selectListTitle(PageInfo pi, UserBoardDto ubd);

	List<UserBoardDto> selectListContext(PageInfo pi, UserBoardDto ubd);

	List<UserBoardDto> selectListWriter(PageInfo pi, UserBoardDto ubd);

	UserBoardDto selectPost(int postNum);

	int updateViewCount(UserBoardDto ubd);

	int deleteBoard(int postNum);

	int updateBoard(UserBoardDto ubd);

	int selectLikeCount(int postNum);

	int selectCheckLike(UserBoardLikeDto ubld);

	int likePost(UserBoardLikeDto ubld);

	int unlikePost(UserBoardLikeDto ubld);
	
	int selectReplyCount(int postNum);

	List<UserBoardReplyDto> selectPostReply(int postNum);

	int insertReply(UserBoardReplyDto ubrd);

	List<UserBoardReplyDto> selectReplyOne(String postReplyContent);

	int selectReplyLikeCount(int postReplyNum);

	int selectReplyCheckLike(UserBoardReplyDto ubrd);
	
	int replyLikePost(UserBoardReplyDto ubrd);

	int replyUnlikePost(UserBoardReplyDto ubrd);
	
	int deleteReply(int postReplyNum);
}
