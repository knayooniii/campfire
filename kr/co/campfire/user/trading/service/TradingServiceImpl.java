package kr.co.campfire.user.trading.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.campfire.user.trading.dao.TradingDao;
import kr.co.campfire.user.trading.dto.*;

@Service
public class TradingServiceImpl implements TradingService {
	
	@Autowired
	private TradingDao tradingDao;

	@Autowired
	private SqlSessionTemplate sqlSession;

	// 전체 게시글 수 구하기
	@Override
	public int selectListCount(HashMap<String, Object> paramMap) {
		return tradingDao.selectListCount(sqlSession, paramMap);
	}
	@Override
	public int selectListCountInMember(HashMap<String, Object> paramMap) {
		return tradingDao.selectListCountInMember(sqlSession, paramMap);
	}

	// 목록 불러오기
	@Override
	public List<Trading> selectListAll(HashMap<String, Object> paramMap) {
		return tradingDao.selectListAll(sqlSession, paramMap);
	}
	@Override
	public List<Trading> selectListAllInMember(HashMap<String, Object> paramMap) {
		return tradingDao.selectListAllInMember(sqlSession, paramMap);
	}

	// 사진 목록 불러오기, 카테고리, 지역도
	@Override
	public List<File> selectFileAll(int tradingIdx) {
		return tradingDao.selectFileAll(sqlSession, tradingIdx);
	}
	
	@Override
	public Category selectCategory(int categoryIdx) {
		return tradingDao.selectCategory(sqlSession, categoryIdx);
	}
	@Override
	public Region selectRegion(int regionIdx) {
		return tradingDao.selectRegion(sqlSession, regionIdx);
	}
	
	// 카테고리, 지역 idx
	@Override
	public int selectCategoryIdx(HashMap<String, Object> paramMap) {
		return tradingDao.selectCategoryIdx(sqlSession, paramMap);
	}
	@Override
	public int selectRegionIdx(HashMap<String, Object> paramMap) {
		return tradingDao.selectRegionIdx(sqlSession, paramMap);
	}
	
	// detail
	@Override
	public Trading selectTrading(int tradingIdx) {
		return tradingDao.selectTrading(sqlSession, tradingIdx);
	}
	@Override
	public Member selectMember(int memberIdx) {
		return tradingDao.selectMember(sqlSession, memberIdx);
	}
	
	// 게시글 삽입
	@Override
	public int insertTrading(Trading tr) {
		return tradingDao.insertTrading(sqlSession, tr);
	}

	@Override
	public int insertFile(File fi) {
		return tradingDao.insertFile(sqlSession, fi);
	}

	// 게시글 수정
	@Override
	public int updateTrading(Trading tr) {
		return tradingDao.updateTrading(sqlSession, tr);
	}
	
	@Override
	public int updateFiles(File fi) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteTrading(int idx) {
		return tradingDao.deleteTrading(sqlSession, idx);
	}

	@Override
	public int deleteFiles(int tradingIdx) {
		return tradingDao.deleteFiles(sqlSession, tradingIdx);
	}
	
	@Override
	public int deleteFile(int fileIdx) {
		return tradingDao.deleteFile(sqlSession, fileIdx);
		
	}
	
	@Override
	public List<Integer> selectFileIndexes(int tradingIdx) {
		return tradingDao.selectFileIndexes(sqlSession, tradingIdx);
	}
	
	@Override
	public int selectDescPositionOnPost(int tradingIdx) {
		return tradingDao.selectDescPositionOnPost(sqlSession, tradingIdx);
	}
	
	@Override
	public int soldToggle(int tradingIdx, int tradingSold) {
		return tradingDao.soldToggle(sqlSession, tradingIdx, tradingSold);
		
	}
	
	@Override
	public String checkSold(int tradingIdx) {
		return tradingDao.checkSold(sqlSession, tradingIdx);
	}






}
