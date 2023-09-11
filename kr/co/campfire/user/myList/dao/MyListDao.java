package kr.co.campfire.user.myList.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import kr.co.campfire.common.dto.PageInfo;
import kr.co.campfire.user.myList.dto.MyListDto;
import kr.co.campfire.user.myList.dto.MyBoardListDto;

@Repository
public class MyListDao {
	public List<MyListDto> selectLikeList(SqlSessionTemplate sqlSession,PageInfo pi, int memberNum) {
		int offset = (pi.getCurrentPage()-1)*pi.getBoardLimit();
		RowBounds rowBounds = new RowBounds(offset, pi.getBoardLimit());
		return sqlSession.selectList("myListMapper.selectMyLikeList", memberNum, rowBounds);
	}
	
	public List<MyBoardListDto> selectLikeBoardList(SqlSessionTemplate sqlSession,PageInfo pi, int memberNum) {
		int offset = (pi.getCurrentPage()-1)*pi.getBoardLimit();
		RowBounds rowBounds = new RowBounds(offset, pi.getBoardLimit());
		return sqlSession.selectList("myListMapper.selectMyLikeBoardList", memberNum, rowBounds);
	}

	public List<MyListDto> selectWishList(SqlSessionTemplate sqlSession,PageInfo pi, int memberNum) {
		int offset = (pi.getCurrentPage()-1)*pi.getBoardLimit();
		RowBounds rowBounds = new RowBounds(offset, pi.getBoardLimit());
		return sqlSession.selectList("myListMapper.selectWishList", memberNum , rowBounds);
	}
	
	public int selectLikeListCount(SqlSessionTemplate sqlSession, int memberNum) {
		return sqlSession.selectOne("myListMapper.selectMyLikeListCount", memberNum);
	}
	
	public int selectLikeBoardListCount(SqlSessionTemplate sqlSession, int memberNum) {
		return sqlSession.selectOne("myListMapper.selectMyLikeBoardListCount", memberNum);
	}
	
	public int selectWishListCount(SqlSessionTemplate sqlSession, int memberNum) {
		return sqlSession.selectOne("myListMapper.selectMyWishListCount", memberNum);
	}

}
