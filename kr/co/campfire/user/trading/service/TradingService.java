package kr.co.campfire.user.trading.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kr.co.campfire.user.trading.dto.*;
import kr.co.campfire.*;

public interface TradingService {
	
	// 전체 게시글 수 구하기
	int selectListCount(HashMap<String, Object> paramMap);
	int selectListCountInMember(HashMap<String, Object> paramMap);
	
	// 목록 불러오기
	List<Trading> selectListAll(HashMap<String, Object> paramMap);
	List<Trading> selectListAllInMember(HashMap<String, Object> paramMap);
	
	// 사진 목록 불러오기, 카테고리, 지역도
	List<File> selectFileAll(int tradingIdx);
	Category selectCategory(int categoryIdx);
	Region selectRegion(int regionIdx);
	
	// 카테고리 지역 idx
	int selectCategoryIdx(HashMap<String, Object> paramMap);
	int selectRegionIdx(HashMap<String, Object> paramMap);
	
	// detail
	Trading selectTrading(int tradingIdx);
	Member selectMember(int memberIdx);
	// 게시글 삽입
	int insertTrading(Trading tr);
	int insertFile(File fi);
	
	// 게시글 상세보기 (내용 보기)
	// Trading detailTrading(int idx);
	// Category detailCategory(int tradingIdx);
	// Region detailRegion(int tradingIdx);
	// Files detaileFile(int tradingIdx);
	
	// 조회수 +1
	// 일단 봉인 // int incrementCount(int idx);
	
	// 게시글 수정
	int updateTrading(Trading tr);
	int updateFiles(File fi);
	
	// 게시글 삭제
	int deleteTrading(int idx);
	int deleteFiles(int tradingIdx);
	int deleteFile(int fileIdx);
	List<Integer> selectFileIndexes(int tradingIdx);
	int selectDescPositionOnPost(int tradingIdx);
	int soldToggle(int tradingIdx, int tradingSold);
	String checkSold(int tradingIdx);
	

}
