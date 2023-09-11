package kr.co.campfire.user.myList.service;

import java.util.List;

import kr.co.campfire.common.dto.PageInfo;
import kr.co.campfire.user.myList.dto.MyBoardListDto;
import kr.co.campfire.user.myList.dto.MyListDto;

public interface MyListService {
	List<MyListDto> selectLikeList(PageInfo pi, int memberNum);
	List<MyBoardListDto> selectLikeBoardList(PageInfo pi, int memberNum);
	List<MyListDto> selectWishList(PageInfo pi, int memberNum);
	int selectLikeListCount(int memberNum);
	int selectLikeBoardListCount(int memberNum);
	int selectWishListCount(int memberNum);

}
