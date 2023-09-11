package kr.co.campfire.user.trading.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import kr.co.campfire.user.trading.dto.Category;
import kr.co.campfire.user.trading.dto.File;
import kr.co.campfire.user.trading.dto.Member;
import kr.co.campfire.user.trading.dto.Region;
import kr.co.campfire.user.trading.dto.Trading;

@Repository
public class TradingDao {

	public int selectListCount(SqlSessionTemplate sqlSession, HashMap<String, Object> paramMap) {
		return sqlSession.selectOne("tradingMapper.selectListCount", paramMap);
	}
	
	public int selectListCountInMember(SqlSessionTemplate sqlSession, HashMap<String, Object> paramMap) {
		System.out.println("(Dao) memberIdx: " + paramMap.get("memberIdx"));
		return sqlSession.selectOne("tradingMapper.selectListCountInMember", paramMap);
	}
	
	public List<Trading> selectListAll(SqlSessionTemplate sqlSession, HashMap<String, Object> paramMap) {
		return sqlSession.selectList("tradingMapper.selectListAll", paramMap);
	}
	
	public List<Trading> selectListAllInMember(SqlSessionTemplate sqlSession, HashMap<String, Object> paramMap) {
		System.out.println("(Dao) memberIdx: " + paramMap.get("memberIdx"));
		return sqlSession.selectList("tradingMapper.selectListAllInMember", paramMap);
	}

	public List<File> selectFileAll(SqlSessionTemplate sqlSession, int tradingIdx) {
		return sqlSession.selectList("tradingMapper.selectFileAll", tradingIdx);
	}
	
	public Category selectCategory(SqlSessionTemplate sqlSession, int categoryIdx) {
		return sqlSession.selectOne("tradingMapper.selectCategory", categoryIdx);
	}

	public Region selectRegion(SqlSessionTemplate sqlSession, int regionIdx) {
		return sqlSession.selectOne("tradingMapper.selectRegion", regionIdx);
	}

	
/*
	public int selectListCount(SqlSessionTemplate sqlSession, String searchText) {
		return sqlSession.selectOne("tradingMapper.selectListCount", searchText);
	}

	public List<Trading> selectListAll(SqlSessionTemplate sqlSession, PageInfo pi, String searchText) {
		int offset = (pi.getCurrentPage() - 1) * (pi.getBoardLimit());

		RowBounds rowBounds = new RowBounds(offset, pi.getBoardLimit());

		return sqlSession.selectList("tradingMapper.selectListAll", searchText, rowBounds);
	}
*/

	public int insertTrading(SqlSessionTemplate sqlSession, Trading tr) {
		return sqlSession.insert("tradingMapper.insertTrading", tr);
	}

	public int insertFile(SqlSessionTemplate sqlSession, File fi) {
		return sqlSession.insert("tradingMapper.insertFile", fi);
	}

	public Trading detailTrading(SqlSessionTemplate sqlSession, int idx) {
		return sqlSession.selectOne("tradingMapper.detailTrading", idx);
	}

	public Category detailCategory(SqlSessionTemplate sqlSession, int tradingIdx) {
		return sqlSession.selectOne("tradingMapper.detailCategory", tradingIdx);
	}
	
	public Region detailRegion(SqlSessionTemplate sqlSession, int tradingIdx) {
		return sqlSession.selectOne("tradingMapper.detailRegion", tradingIdx);
	}
	
	public File detailFile(SqlSessionTemplate sqlSession, int tradingIdx) {
		return sqlSession.selectOne("tradingMapper.detailFile", tradingIdx);
	}

	public int updateTrading(SqlSessionTemplate sqlSession, Trading tr) {
		return sqlSession.update("tradingMapper.updateTrading", tr);
	}
	
	/*
	 * public int updateFile(SqlSessionTemplate sqlSession, File fi) { return
	 * sqlSession.update("tradingMapper.updateFile", fi); }
	 */

	public int deleteTrading(SqlSessionTemplate sqlSession, int idx) {
		return sqlSession.delete("tradingMapper.deleteTrading", idx);
	}

	public int deleteFiles(SqlSessionTemplate sqlSession, int tradingIdx) {
		return sqlSession.delete("tradingMapper.deleteFiles", tradingIdx);
	}
	
	public int deleteFile(SqlSessionTemplate sqlSession, int fileIdx) {
		return sqlSession.delete("tradingMapper.deleteFile", fileIdx);
	}

	// detail
	public Trading selectTrading(SqlSessionTemplate sqlSession, int tradingIdx) {
		return sqlSession.selectOne("tradingMapper.selectTrading", tradingIdx);
	}

	public Member selectMember(SqlSessionTemplate sqlSession, int memberIdx) {
		return sqlSession.selectOne("tradingMapper.selectMember", memberIdx);
	}

	// 카테고리 지역 idx
	public int selectCategoryIdx(SqlSessionTemplate sqlSession, HashMap<String, Object> paramMap) {
		return sqlSession.selectOne("tradingMapper.selectCategoryIdx", paramMap);
	}

	public int selectRegionIdx(SqlSessionTemplate sqlSession, HashMap<String, Object> paramMap) {
		return sqlSession.selectOne("tradingMapper.selectRegionIdx", paramMap);
	}

	public List<Integer> selectFileIndexes(SqlSessionTemplate sqlSession, int tradingIdx) {
		return sqlSession.selectList("tradingMapper.selectFileIndexes", tradingIdx);
	}

	public int selectDescPositionOnPost(SqlSessionTemplate sqlSession, int tradingIdx) {
		return sqlSession.selectOne("tradingMapper.selectDescPositionOnPost", tradingIdx);
	}

	public int soldToggle(SqlSessionTemplate sqlSession, int tradingIdx, int tradingSold) {
		
		HashMap<String, Integer> paramMap = new HashMap<>();
		paramMap.put("tradingIdx", tradingIdx);
		paramMap.put("tradingSold", tradingSold);
		
		return sqlSession.update("tradingMapper.soldToggle", paramMap);
	}

	public String checkSold(SqlSessionTemplate sqlSession, int tradingIdx) {
		return sqlSession.selectOne("tradingMapper.checkSold", tradingIdx);
	}






}
