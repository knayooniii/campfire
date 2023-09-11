package kr.co.campfire.chat.model.dao;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import kr.co.campfire.chat.model.dto.ChatMessage;
import kr.co.campfire.chat.model.dto.ChatMessage2;

@Repository
public class ChatDao {

	public List<ChatMessage> loadMessages(SqlSessionTemplate sqlSession, int chatRoomIdx, int lastMessageIdx) {
		
		HashMap<String, Integer> paramMap = new HashMap<>();
		paramMap.put("chatRoomIdx", chatRoomIdx);
		paramMap.put("lastMessageIdx", lastMessageIdx);
		
		return sqlSession.selectList("chatMapper.loadMessages", paramMap);
	}

	public int selectChatRoomIdx(SqlSessionTemplate sqlSession, int tradingIdx, int sessionMemberIdx) {
		HashMap<String, Integer> paramMap = new HashMap<>();
		paramMap.put("tradingIdx", tradingIdx);
		paramMap.put("sessionMemberIdx", sessionMemberIdx);
		
		return sqlSession.selectOne("chatMapper.selectChatRoomIdx", paramMap);
	}

	public int selectChatRoomIdxCount(SqlSessionTemplate sqlSession, int tradingIdx, int sessionMemberIdx) {
		HashMap<String, Integer> paramMap = new HashMap<>();
		paramMap.put("tradingIdx", tradingIdx);
		paramMap.put("sessionMemberIdx", sessionMemberIdx);
		
		return sqlSession.selectOne("chatMapper.selectChatRoomIdxCount", paramMap);
	}

	public int deleteChatMessages(SqlSessionTemplate sqlSession, int chatRoomIdx) {
		return sqlSession.delete("chatMapper.deleteChatMessages", chatRoomIdx);
	}

	public int deleteChatRoom(SqlSessionTemplate sqlSession, int chatRoomIdx) {
		return sqlSession.delete("chatMapper.deleteChatRoom", chatRoomIdx);
	}

	public int insertMessage(SqlSessionTemplate sqlSession, int chatRoomIdx, int senderIdx, String content) {
		HashMap<String, Object> paramMap = new HashMap<>();
		paramMap.put("chatRoomIdx", chatRoomIdx);
		paramMap.put("senderIdx", senderIdx);
		paramMap.put("content", content);
		
		return sqlSession.insert("chatMapper.insertMessage", paramMap);
	}

	public int makeChatRoom(SqlSessionTemplate sqlSession, int tradingIdx, int sessionMemberIdx) {
		HashMap<String, Object> paramMap = new HashMap<>();
		paramMap.put("tradingIdx", tradingIdx);
		paramMap.put("sessionMemberIdx", sessionMemberIdx);
		
		return sqlSession.insert("chatMapper.makeChatRoom", paramMap);
	}

	public List<Integer> listOfChatRoomIdx(SqlSessionTemplate sqlSession, int tradingIdx) {
		return sqlSession.selectList("chatMapper.listOfChatRoomIdx", tradingIdx);
	}

	public List<ChatMessage2> loadMessages2(SqlSessionTemplate sqlSession, int chatRoomIdx, int lastMessageIdx) {
		
		HashMap<String, Integer> paramMap = new HashMap<>();
		paramMap.put("chatRoomIdx", chatRoomIdx);
		paramMap.put("lastMessageIdx", lastMessageIdx);
		
		return sqlSession.selectList("chatMapper.loadMessages2", paramMap);
	}

	public String selectBuyerName(SqlSessionTemplate sqlSession, int chatRoomIdx) {
		return sqlSession.selectOne("chatMapper.selectBuyerName", chatRoomIdx);
	}


	public ChatMessage2 selectLastMessage(SqlSessionTemplate sqlSession, int chatRoomIdx) {
		return sqlSession.selectOne("chatMapper.selectLastMessage", chatRoomIdx);
	}

	public int selectChatRoom(SqlSessionTemplate sqlSession, int chatRoomIdx) {
		return sqlSession.selectOne("chatMapper.selectChatRoom", chatRoomIdx);
	}

	public int isChatRoomExist(SqlSessionTemplate sqlSession, int chatRoomIdx) {
		return sqlSession.selectOne("chatMapper.isChatRoomExist", chatRoomIdx);
	}

	public int selectUnreadCount(SqlSessionTemplate sqlSession, int chatRoomIdx, int sessionMemberIdx) {
		HashMap<String, Object> paramMap = new HashMap<>();
		paramMap.put("chatRoomIdx", chatRoomIdx);
		paramMap.put("sessionMemberIdx", sessionMemberIdx);
		
		return sqlSession.selectOne("chatMapper.selectUnreadCount", paramMap);
	}

	public void setReadMessage(SqlSessionTemplate sqlSession, int chatRoomIdx, int lastMessageIdx,
			int sessionMemberIdx) {
		HashMap<String, Object> paramMap = new HashMap<>();
		paramMap.put("chatRoomIdx", chatRoomIdx);
		paramMap.put("lastMessageIdx", lastMessageIdx);
		paramMap.put("sessionMemberIdx", sessionMemberIdx);
		
		sqlSession.update("chatMapper.setReadMessage", paramMap);
	}

	public int selectUnreadCountForBuyer(SqlSessionTemplate sqlSession, int tradingIdx, int buyerIdx) {
		HashMap<String, Object> paramMap = new HashMap<>();
		paramMap.put("tradingIdx", tradingIdx);
		paramMap.put("buyerIdx", buyerIdx);
		
		return sqlSession.selectOne("chatMapper.selectUnreadCountForBuyer", paramMap);
	}

	public int messageReadCheck(SqlSessionTemplate sqlSession, int messageIdx) {

		return sqlSession.selectOne("chatMapper.messageReadCheck", messageIdx);
	}




}
