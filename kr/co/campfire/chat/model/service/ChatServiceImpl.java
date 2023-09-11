package kr.co.campfire.chat.model.service;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.campfire.chat.model.dao.ChatDao;
import kr.co.campfire.chat.model.dto.ChatMessage;
import kr.co.campfire.chat.model.dto.ChatMessage2;
import kr.co.campfire.user.trading.dao.TradingDao;

@Service
public class ChatServiceImpl implements ChatService{

	@Autowired
	private ChatDao chatDao;

	@Autowired
	private SqlSessionTemplate sqlSession;
	
	@Override
	public List<ChatMessage> loadMessages(int chatRoomIdx, int lastMessageIdx) {
		return chatDao.loadMessages(sqlSession, chatRoomIdx, lastMessageIdx);
	}

	@Override
	public int selectChatRoomIdx(int tradingIdx, int sessionMemberIdx) {
		return chatDao.selectChatRoomIdx(sqlSession, tradingIdx, sessionMemberIdx);
	}

	@Override
	public int selectChatRoomIdxCount(int tradingIdx, int sessionMemberIdx) {
		return chatDao.selectChatRoomIdxCount(sqlSession, tradingIdx, sessionMemberIdx);
	}

	@Override
	public int deleteChatMessages(int chatRoomIdx) {
		return chatDao.deleteChatMessages(sqlSession, chatRoomIdx);
	}

	@Override
	public int deleteChatRoom(int chatRoomIdx) {
		return chatDao.deleteChatRoom(sqlSession, chatRoomIdx);
	}

	
	@Override
	public int insertMessage(int chatRoomIdx, int senderIdx, String content) {
		return chatDao.insertMessage(sqlSession, chatRoomIdx, senderIdx, content);
	}

	@Override
	public int makeChatRoom(int tradingIdx, int sessionMemberIdx) {
		return chatDao.makeChatRoom(sqlSession, tradingIdx, sessionMemberIdx);
	}

	@Override
	public List<Integer> listOfChatRoomIdx(int tradingIdx) {
		return chatDao.listOfChatRoomIdx(sqlSession, tradingIdx);
	}

	@Override
	public List<ChatMessage2> loadMessages2(int chatRoomIdx, int lastMessageIdx) {
		return chatDao.loadMessages2(sqlSession, chatRoomIdx, lastMessageIdx);
	}

	@Override
	public String selectBuyerName(int chatRoomIdx) {
		return chatDao.selectBuyerName(sqlSession, chatRoomIdx);
	}
	
	@Override
	public int selectUnreadCount(int chatRoomIdx, int sessionMemberIdx) {
		return chatDao.selectUnreadCount(sqlSession, chatRoomIdx, sessionMemberIdx);
	}
	
	@Override
	public ChatMessage2 selectLastMessage(int chatRoomIdx) {
		return chatDao.selectLastMessage(sqlSession, chatRoomIdx);
	}

	@Override
	public int isChatRoomExist(int chatRoomIdx) {
		return chatDao.isChatRoomExist(sqlSession, chatRoomIdx);
	}

	@Override
	public void setReadMessage(int chatRoomIdx, int lastMessageIdx, int sessionMemberIdx) {
		chatDao.setReadMessage(sqlSession, chatRoomIdx, lastMessageIdx, sessionMemberIdx);
	}

	@Override
	public int selectUnreadCountForBuyer(int tradingIdx, int buyerIdx) {
		return chatDao.selectUnreadCountForBuyer(sqlSession, tradingIdx, buyerIdx);
	}

	@Override
	public int messageReadCheck(int messageIdx) {
		return chatDao.messageReadCheck(sqlSession, messageIdx);
	}
	



}
