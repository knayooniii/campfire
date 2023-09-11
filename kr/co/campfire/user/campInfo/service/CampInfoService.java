package kr.co.campfire.user.campInfo.service;

import java.util.List;

import kr.co.campfire.user.campInfo.dto.CampInfoDto;
import kr.co.campfire.user.campInfo.dto.CampInfoFunctionDto;

public interface CampInfoService {
	CampInfoDto campInfo(int campNum);
	
	int countCampInfo (CampInfoDto campView);
	List<CampInfoDto> campTag(int campNum);
	
	List<CampInfoDto> campAmenity(int campNum);
	
	List<CampInfoDto> campPhoto(int campNum);
//
//	List<String> campAPlace(int campNum);

	
	   int selectLikeCount(int campNum);
	   
	   int selectCheckLike(CampInfoFunctionDto cifd);
	   
	   int likeCamp(CampInfoFunctionDto cifd);
	   
	   int unlikeCamp(CampInfoFunctionDto cifd);
	   
		
	   int selectWishlistCount(int campNum);
	   
	   int selectCheckWishlist(CampInfoFunctionDto cifd);
	   
	   int wishlist(CampInfoFunctionDto cifd);
	   
	   int unwishlist(CampInfoFunctionDto cifd);
	   
	   //댓글
	   List<CampInfoFunctionDto> selectPostReply(int postNum);

	   int insertReply(CampInfoFunctionDto cifd);

	   List<CampInfoFunctionDto> selectReplyOne(String postReplyContent);

	   int selectReplyLikeCount(int postReplyNum);

	   int selectReplyCheckLike(CampInfoFunctionDto cifd);
	   
	   int replyLikePost(CampInfoFunctionDto cifd);

	   int replyUnlikePost(CampInfoFunctionDto cifd);
	   
	   int deleteReply(int postReplyNum);

}
