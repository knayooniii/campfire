package kr.co.campfire.user.campSearch.service;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.campfire.common.dto.PageInfo;
import kr.co.campfire.user.campSearch.dao.CampSearchDao;
import kr.co.campfire.user.campSearch.dto.CampSearchDto;

import java.util.List;

@Service
public class CampSearchServiceImpl implements CampSearchService {

	@Autowired
	private SqlSessionTemplate sqlSession;
	
	@Autowired
	private CampSearchDao campSearchDao;

	@Override
	public int selectCampSearchCount () {
		return campSearchDao.selectCampSearchCount(sqlSession);
	}
	

	@Override
	public int selectLikeCount(int campNum) {
		return campSearchDao.selectLikeCount(sqlSession, campNum);
	}

	@Override
	public List<CampSearchDto>  getCampAddr(String campAddr) {
		return campSearchDao.selectCampAddr(sqlSession, campAddr);
	}


	@Override
	public List<CampSearchDto> selectCampSearch(PageInfo pi) {
		// TODO Auto-generated method stub
		return campSearchDao.selectCampSearch(sqlSession, pi);
	}
	


	@Override
	public List<CampSearchDto> selectCampSearch(PageInfo pi, CampSearchDto campSearchDto) {
		// TODO Auto-generated method stub
		return campSearchDao.selectCampSearch(sqlSession, pi, campSearchDto);
	}


	@Override
	public List<CampSearchDto> selectCampSearch(PageInfo pi, String campGroup, String status) {
		return campSearchDao.selectCampSearch(sqlSession, pi, campGroup, status);
	}


	@Override
	public List<CampSearchDto> selectCampPhoto(int campNum) {
		return campSearchDao.selectCampPhoto(sqlSession,campNum);
	}


	@Override
	public int selectCampSearchCount(CampSearchDto campSearchDto) {

		return campSearchDao.selectCampSearchCount(sqlSession, campSearchDto);
	}


	@Override
	public int selectCampSearchCount(String campGroup, String status) {

		return campSearchDao.selectCampSearchCount(sqlSession, campGroup, status);
	}


	@Override
	public List<CampSearchDto> selectCampSearch(PageInfo pi, String category) {

		return campSearchDao.selectCampSearch(sqlSession, pi, category);
	}


	
	
	
	

    // 기타 필요한 메서드 구현s
}
