package kr.co.campfire.user.campInfo.service;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.campfire.user.campInfo.dto.CampInfoDto;
import kr.co.campfire.user.campInfo.dto.CampInfoFunctionDto;
import kr.co.campfire.user.campInfo.dao.CampInfoDao;

@Service
public class CampInfoServiceImpl implements CampInfoService {

	@Autowired
	private SqlSessionTemplate sqlSession;

	@Autowired
	private CampInfoDao campInfoDao;

	@Override
	public CampInfoDto campInfo(int campNum) {
		return campInfoDao.campInfo(sqlSession, campNum);
	}

	@Override
	public int countCampInfo(CampInfoDto campView) {
		return campInfoDao.countCampInfo(sqlSession, campView);

	}

	@Override
	public List<CampInfoDto> campTag(int campNum) {
		return campInfoDao.campTag(sqlSession, campNum);
	}

	@Override
	public List<CampInfoDto> campAmenity(int campNum) {
		return campInfoDao.campAmenity(sqlSession, campNum);
	}

	@Override
	public List<CampInfoDto> campPhoto(int campNum) {
		return campInfoDao.campPhoto(sqlSession, campNum);
	}

	// 좋아요
	@Override
	public int selectLikeCount(int campNum) {
		return campInfoDao.selectLikeCount(sqlSession, campNum);
	}

	@Override
	public int selectCheckLike(CampInfoFunctionDto cifd) {
		return campInfoDao.selectCheckLike(sqlSession, cifd);
	}

	@Override
	public int likeCamp(CampInfoFunctionDto cifd) {
		return campInfoDao.likeCamp(sqlSession, cifd);
	}

	@Override
	public int unlikeCamp(CampInfoFunctionDto cifd) {
		return campInfoDao.unlikeCamp(sqlSession, cifd);
	}

	// 즐찾
	@Override
	public int selectWishlistCount(int campNum) {
		return campInfoDao.selectWishlistCount(sqlSession, campNum);
	}

	@Override
	public int selectCheckWishlist(CampInfoFunctionDto cifd) {
		return campInfoDao.selectCheckWishlist(sqlSession, cifd);
	}

	@Override
	public int wishlist(CampInfoFunctionDto cifd) {
		return campInfoDao.wishlist(sqlSession, cifd);
	}

	@Override
	public int unwishlist(CampInfoFunctionDto cifd) {
		return campInfoDao.unwishlist(sqlSession, cifd);
	}

//댓글

	@Override
	public List<CampInfoFunctionDto> selectPostReply(int postNum) {
		return campInfoDao.selectPostReply(sqlSession, postNum);
	}

	@Override
	public int insertReply(CampInfoFunctionDto cifd) {
		return campInfoDao.insertReply(sqlSession, cifd);
	}

	@Override
	public List<CampInfoFunctionDto> selectReplyOne(String postReplyContent) {
		return campInfoDao.selectReplyOne(sqlSession, postReplyContent);
	}

	@Override
	public int selectReplyLikeCount(int postReplyNum) {
		return campInfoDao.selectReplyLikeCount(sqlSession, postReplyNum);
	}

	@Override
	public int selectReplyCheckLike(CampInfoFunctionDto cifd) {
		return campInfoDao.selectReplyCheckLike(sqlSession, cifd);
	}

	@Override
	public int replyLikePost(CampInfoFunctionDto cifd) {
		return campInfoDao.replyLikePost(sqlSession, cifd);
	}

	@Override
	public int replyUnlikePost(CampInfoFunctionDto cifd) {
		return campInfoDao.replyUnlikePost(sqlSession, cifd);
	}

	@Override
	   public int deleteReply(int postReplyNum) {
	      return campInfoDao.deleteReply(sqlSession, postReplyNum);
	   }
}
