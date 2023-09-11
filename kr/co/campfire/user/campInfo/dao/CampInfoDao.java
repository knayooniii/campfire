package kr.co.campfire.user.campInfo.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.campfire.user.campInfo.dto.CampInfoDto;
import kr.co.campfire.user.campInfo.dto.CampInfoFunctionDto;

@Repository
public class CampInfoDao {

	    public CampInfoDto campInfo(SqlSessionTemplate sqlSession, int campNum) {
	        return sqlSession.selectOne("campInfoMapper.CampInfo", campNum);
	    }
	    
	    public int countCampInfo(SqlSessionTemplate sqlSession, CampInfoDto campView) {
	    	return sqlSession.update("campInfoMapper.countCampInfo", campView);
	    }
	    
	    public List<CampInfoDto> campTag(SqlSessionTemplate sqlSession,int campNum) {
	        return sqlSession.selectList("campInfoMapper.CampTag", campNum);
	    }

	    public List<CampInfoDto> campAmenity(SqlSessionTemplate sqlSession,int campNum) {
	        return sqlSession.selectList("campInfoMapper.CampAmenity", campNum);
	    }
//
//	    public List<String> campAPlace(SqlSessionTemplate sqlSession,int campNum) {
//	        return sqlSession.selectList("campInfo.campAPlace", campNum);
//	    }

		public List<CampInfoDto> campPhoto(SqlSessionTemplate sqlSession, int campNum) {
			return sqlSession.selectList("campInfoMapper.campPhoto", campNum);
		}


		 //좋아요 수 반환
		   public int selectLikeCount(SqlSessionTemplate sqlSession, int campNum) {
		      return sqlSession.selectOne("campInfoMapper.selectLikeCount", campNum);
		   }
		   // 게시물 좋아요 했는지 체크
		   public int selectCheckLike(SqlSessionTemplate sqlSession, CampInfoFunctionDto cifd) {
		        return sqlSession.selectOne("campInfoMapper.selectCheckLike", cifd);
		   }
		   //좋아요 추가
		   public int likeCamp(SqlSessionTemplate sqlSession, CampInfoFunctionDto cifd) {
		        return sqlSession.insert("campInfoMapper.likeCamp", cifd);
		   }
		   // 좋아요 삭제
		   public int unlikeCamp(SqlSessionTemplate sqlSession, CampInfoFunctionDto cifd) {
		        return sqlSession.delete("campInfoMapper.unlikeCamp", cifd);
		   }
		   //
		   
		   //즐찾 수 반환
		   public int selectWishlistCount(SqlSessionTemplate sqlSession, int campNum) {
		      return sqlSession.selectOne("campInfoMapper.selectWishlistCount", campNum);
		   }
		   //즐찾 했는지 체크
		   public int selectCheckWishlist(SqlSessionTemplate sqlSession, CampInfoFunctionDto cifd) {
		        return sqlSession.selectOne("campInfoMapper.selectCheckWishlist", cifd);
		   }
		   //즐찾 추가
		   public int wishlist(SqlSessionTemplate sqlSession, CampInfoFunctionDto cifd) {
		        return sqlSession.insert("campInfoMapper.wishlist", cifd);
		   }
		   // 즐찾 삭제
		   public int unwishlist(SqlSessionTemplate sqlSession, CampInfoFunctionDto cifd) {
		        return sqlSession.delete("campInfoMapper.unwishlist", cifd);
		   }

		   //댓글 리스트
		   public List<CampInfoFunctionDto> selectPostReply(SqlSessionTemplate sqlSession, int postNum){
		      return sqlSession.selectList("campInfoMapper.selectPostReply", postNum);
		   }
		   
		   //댓글 추가
		   public int insertReply(SqlSessionTemplate sqlSession, CampInfoFunctionDto cifd) {
		        return sqlSession.insert("campInfoMapper.insertReply", cifd);
		   }
		   public List<CampInfoFunctionDto> selectReplyOne(SqlSessionTemplate sqlSession, String postReplyContent) {
		      return sqlSession.selectList("campInfoMapper.selectReplyOne", postReplyContent);
		   }
		   // 게시물 좋아요 했는지 체크
		   public int selectReplyCheckLike(SqlSessionTemplate sqlSession, CampInfoFunctionDto cifd) {
		        return sqlSession.selectOne("campInfoMapper.selectReplyCheckLike", cifd);
		   }
		   //댓글 좋아요 수 반환
		   public int selectReplyLikeCount(SqlSessionTemplate sqlSession, int postReplyNum) {
		      return sqlSession.selectOne("campInfoMapper.selectReplyLikeCount", postReplyNum);
		   }
		   
		   //댓글 좋아요 추가
		   public int replyLikePost(SqlSessionTemplate sqlSession, CampInfoFunctionDto cifd) {
		        return sqlSession.insert("campInfoMapper.replyLikePost", cifd);
		   }
		   //댓글 좋아요 삭제
		   public int replyUnlikePost(SqlSessionTemplate sqlSession, CampInfoFunctionDto cifd) {
		        return sqlSession.delete("campInfoMapper.replyUnlikePost", cifd);
		   }

		   public int deleteReply(SqlSessionTemplate sqlSession, int postReplyNum) {
			      return sqlSession.delete("campInfoMapper.deleteReply", postReplyNum);
			   }
}