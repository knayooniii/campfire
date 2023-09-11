package kr.co.campfire.chat.model.service;

import java.util.List;

import kr.co.campfire.chat.model.dto.ChatMessage;
import kr.co.campfire.chat.model.dto.ChatMessage2;

public interface ChatService {

	int selectChatRoomIdx(int tradingIdx, int sessionMemberIdx);

	int selectChatRoomIdxCount(int tradingIdx, int sessionMemberIdx);

	List<ChatMessage> loadMessages(int chatRoomIdx, int lastMessageIdx);
	
	int deleteChatMessages(int chatRoomIdx);
	
	int deleteChatRoom(int chatRoomIdx);

	int insertMessage(int chatRoomIdx, int senderIdx, String content);

	int makeChatRoom(int tradingIdx, int sessionMemberIdx);

	List<Integer> listOfChatRoomIdx(int tradingIdx);

	List<ChatMessage2> loadMessages2(int chatRoomIdx, int lastMessageIdx);

	String selectBuyerName(int chatRoomIdx);

	ChatMessage2 selectLastMessage(int chatRoomIdx);

	int isChatRoomExist(int chatRoomIdx);

	int selectUnreadCount(int chatRoomIdx, int sessionMemberIdx);

	void setReadMessage(int chatRoomIdx, int lastMessageIdx, int sessionMemberIdx);

	int selectUnreadCountForBuyer(int tradingIdx, int buyerIdx);

	int messageReadCheck(int messageIdx);





}
