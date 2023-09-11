package kr.co.campfire.user.campSearch.dao;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;


import kr.co.campfire.common.dto.PageInfo;
import kr.co.campfire.user.campSearch.dto.CampSearchDto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class CampSearchDao {

    // 모든 캠핑장 데이터를 가져오는 메서드	
	public List<CampSearchDto> selectCampSearch(SqlSessionTemplate sqlSession, PageInfo pi) {
		int offset = (pi.getCurrentPage()-1)*pi.getBoardLimit();
		RowBounds rowBounds = new RowBounds(offset, pi.getBoardLimit());
		return sqlSession.selectList("campSearchMapper.selectCampSearch", null ,rowBounds);
	}

	public int selectCampSearchCount(SqlSessionTemplate sqlSession) {
		return sqlSession.selectOne("campSearchMapper.selectCampSearchCount");
	}

	public int selectLikeCount(SqlSessionTemplate sqlSession ,int campNum) {
	
		return sqlSession.selectOne("campSearchMapper.selectCampLikeCount",campNum);
	}

//	public List<CampSearchDto> selectCampSearch(SqlSessionTemplate sqlSession, String campAddr) {
//		return sqlSession.selectList(campAddr);
//	}

	public List<CampSearchDto> selectCampAddr(SqlSessionTemplate sqlSession, String campAddr) {
		return sqlSession.selectList(campAddr);
	}

	public List<CampSearchDto> selectCampSearch(SqlSessionTemplate sqlSession, PageInfo pi, CampSearchDto campSearchDto) {
		
		
		int offset = (pi.getCurrentPage()-1)*pi.getBoardLimit();
		RowBounds rowBounds = new RowBounds(offset, pi.getBoardLimit());
		return sqlSession.selectList("campSearchMapper.selectCampSearchAddrType", campSearchDto, rowBounds);
	}

	public List<CampSearchDto> selectCampSearch(SqlSessionTemplate sqlSession, PageInfo pi, String campGroup,
			String status) { 
		CampSearchDto campSearchDto= new CampSearchDto() ;
		campSearchDto.setStatus(status);
		campSearchDto.setCampGroup(campGroup);
		
		int offset = (pi.getCurrentPage()-1)*pi.getBoardLimit();
		RowBounds rowBounds = new RowBounds(offset, pi.getBoardLimit());
		
		if(status.equals("CT_TAG")) {
			return sqlSession.selectList("campSearchMapper.selectCampTag", campSearchDto, rowBounds);
			
		}else if(status.equals("C_KEYWORD")){
			return sqlSession.selectList("campSearchMapper.selectCampKeyword", campGroup, rowBounds);
		} else if(status.equals("CA_AMENITY")){
			return sqlSession.selectList("campSearchMapper.selectCampAmenity", campSearchDto, rowBounds);
		} else { 
			return sqlSession.selectList("campSearchMapper.selectCampGroup", campSearchDto, rowBounds);
		}
		
	}
	//리스트 갯수 반환 
	public int selectCampSearchCount(SqlSessionTemplate sqlSession, String campGroup,
			String status) { 
		CampSearchDto campSearchDto= new CampSearchDto() ;
		campSearchDto.setStatus(status);
		campSearchDto.setCampGroup(campGroup);
		
		if(status.equals("CT_TAG")) {
			return sqlSession.selectOne("campSearchMapper.selectCampTagCount", campSearchDto);
			
		}else if(status.equals("C_KEYWORD")){
			return sqlSession.selectOne("campSearchMapper.selectCampKeywordCount", campGroup);
		
		
		}else if(status.equals("CA_AMENITY")){ 
			System.out.println(campSearchDto.getCampGroup());
			return sqlSession.selectOne("campSearchMapper.selectCampAmenityCount", campSearchDto);
		} else { 
			System.out.println(campSearchDto.getCampGroup());
			return sqlSession.selectOne("campSearchMapper.selectCampGroupCount", campSearchDto);
		}
		
	}

	public List<CampSearchDto> selectCampPhoto(SqlSessionTemplate sqlSession,int campNum) {

		return sqlSession.selectList("campSearchMapper.selectCampPhoto", campNum );
	}

	public int selectCampSearchCount(SqlSessionTemplate sqlSession, CampSearchDto campSearchDto) {
		return sqlSession.selectOne("campSearchMapper.selectCampSearchAddrTypeCount", campSearchDto);
	}

	public List<CampSearchDto> selectCampSearch(SqlSessionTemplate sqlSession, PageInfo pi, String category) {
	    Map<String, Object> paramMap = new HashMap<>();
	    paramMap.put("category", category);
	    
		int offset = (pi.getCurrentPage()-1)*pi.getBoardLimit();
		RowBounds rowBounds = new RowBounds(offset, pi.getBoardLimit());
		return sqlSession.selectList("campSearchMapper.selectCampSearchCategory",paramMap, rowBounds);
	}





}
