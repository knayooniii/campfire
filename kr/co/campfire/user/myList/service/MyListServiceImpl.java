package kr.co.campfire.user.myList.service;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.campfire.common.dto.PageInfo;
import kr.co.campfire.user.myList.dao.MyListDao;
import kr.co.campfire.user.myList.dto.MyBoardListDto;
import kr.co.campfire.user.myList.dto.MyListDto;

@Service
public class MyListServiceImpl implements MyListService {
	@Autowired
	private SqlSessionTemplate sqlSession;
	@Autowired
	private MyListDao myListDao;

	@Override
	public List<MyListDto> selectLikeList(PageInfo pi, int memberNum) {

		return myListDao.selectLikeList(sqlSession, pi, memberNum);
	}

	@Override
	public List<MyBoardListDto> selectLikeBoardList(PageInfo pi, int memberNum) {

		return myListDao.selectLikeBoardList(sqlSession, pi, memberNum);
	}

	@Override
	public List<MyListDto> selectWishList(PageInfo pi, int memberNum) {
		return myListDao.selectWishList(sqlSession, pi, memberNum);
	}

	@Override
	public int selectLikeListCount(int memberNum) {

		return myListDao.selectLikeListCount(sqlSession, memberNum);
	}

	@Override
	public int selectLikeBoardListCount(int memberNum) {

		return myListDao.selectLikeBoardListCount(sqlSession, memberNum);
	}

	@Override
	public int selectWishListCount(int memberNum) {
		return myListDao.selectWishListCount(sqlSession, memberNum);
	}

}
