package kr.co.campfire.chat.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import kr.co.campfire.chat.model.dto.ChatMessage;
import kr.co.campfire.chat.model.dto.ChatMessage2;
import kr.co.campfire.chat.model.dto.ChatRoomThumb;
import kr.co.campfire.chat.model.service.ChatServiceImpl;
import kr.co.campfire.user.trading.service.TradingServiceImpl;

@Controller
@RequestMapping("/chat")
public class ChatController {

	@Autowired
	private ChatServiceImpl chatService;

	@Autowired
	private TradingServiceImpl tradingService;

//	@ResponseBody
//	@PostMapping(value = "/loadMessage.do" , produces = "application/json;charset=UTF-8")
//	public List<Object> loadMessage(
//			HttpServletRequest request,
//			HttpSession session
//			) {
//		String viewerIdx = (String) request.getParameter("viewerIdx");
//		String leave_clicked = (String) request.getParameter("leave_clicked");
//	
//		
//		
//		// 메시지 로드
//		String chatRoomIdx = (String) request.getParameter("chatRoomIdx");
//		
//		//삭제 관련
//		if(leave_clicked.equals("true")) {
//			int result1 = chatService.deleteChatMessages(Integer.parseInt(chatRoomIdx));
//			int result2 = chatService.deleteChatRoom(Integer.parseInt(chatRoomIdx));
//		}
//		
//		//메시지 로드
//		String lastMessageIdx = (String) request.getParameter("lastMessageIdx");
//		System.out.println("chatRoomIdx: " + chatRoomIdx + ", viewerIdx: " + viewerIdx + ", lastMessageIdx: " + lastMessageIdx);
//		
//		List<ChatMessage2> msgList = chatService.loadMessages2(Integer.parseInt(chatRoomIdx), Integer.parseInt(lastMessageIdx));
//
//		// 읽음(is_read) 처리
//		
//		// 채팅방 존재 여부
//		int chatRoomCount = chatService.isChatRoomExist(Integer.parseInt(chatRoomIdx));
//		// 로드한 메시지 전송
//		List<Object> result = new ArrayList<>();
//		result.add(msgList);
//		result.add(chatRoomCount);
//		
//		if(chatRoomCount == 0) {
//			if(leave_clicked.equals("true")) {
//				// 누른사람은 자신 행동을 잘 아니까 알릴필요x
//			} else { // false
//				session.setAttribute("tw_msg", "대화 상대가 채팅방을 떠났어요.");
//				session.setAttribute("tw_status", "info");
//			}
//		}
//	
//
//		
//		return result;
//	}

//	@ResponseBody 
//	@PostMapping(value = "/leaveChat.do", produces = "text/plain;charset=UTF-8")
//	public String leaveChat(
//			HttpServletRequest request,
//			HttpSession session
//			) {
//		
//		String chatRoomIdx = (String) request.getParameter("chatRoomIdx");
//		System.out.println("(leaveChat.do) chatRoomIdx: " + chatRoomIdx);
//		
//		int result1 = chatService.deleteChatMessages(Integer.parseInt(chatRoomIdx));
//		int result2 = chatService.deleteChatRoom(Integer.parseInt(chatRoomIdx));
//		
//        if( result2 == 0) { // 삭제할 메시지가 없는 경우는 에러가 아님에도 0을 반환한다. 따라서 result1은 제외
//        	System.out.println("(leaveChat.do) result1: " + result1 + ", result2: " + result2);
//        	return "데이터베이스 에러";
//        } 
//        
//		return "";
//	}

	@ResponseBody
	@PostMapping(value = "/insertMessage.do", produces = "text/plain;charset=UTF-8")
	public String insertMessage(HttpServletRequest request) {
		String chatRoomIdx = (String) request.getParameter("chatRoomIdx");
		if (chatRoomIdx == null || chatRoomIdx.equals("0")) {
			return "";
		}
		String senderIdx = (String) request.getParameter("senderIdx");
		String content = (String) request.getParameter("content");

		int result = chatService.insertMessage(Integer.parseInt(chatRoomIdx), Integer.parseInt(senderIdx), content);

		if (result == 0) {
			return "데이터베이스 에러";
		}

		return "";
	}

	@ResponseBody
	@PostMapping(value = "/makeChatRoom.do", produces = "text/plain;charset=UTF-8")
	public String makeChatRoom(HttpServletRequest request) {
		String tradingIdx = (String) request.getParameter("tradingIdx");
		String sessionMemberIdx = (String) request.getParameter("sessionMemberIdx");

		// 가끔 버튼 여러번 누르면 같은 tradingIdx와 m_num을 공유하는 채팅방이 여러개 생기는데,
		// 이 if문을 통해서 그런 현상을 방지할 수 있다.
		if (chatService.selectChatRoomIdxCount(Integer.parseInt(tradingIdx), Integer.parseInt(sessionMemberIdx)) == 0) {

			int result = chatService.makeChatRoom(Integer.parseInt(tradingIdx), Integer.parseInt(sessionMemberIdx));
			if (result == 0) {
				return "데이터베이스 에러";
			}

		}

		return "";
	}

//	//chatRoomIdx list
//	@ResponseBody
//	@PostMapping(value = "/listOfChatRoomThumb.do" , produces = "application/json;charset=UTF-8")
//	public List<ChatRoomThumb> listOfChatRoomThumb(
//			HttpServletRequest request
//			) {
//		String tradingIdx = (String) request.getParameter("tradingIdx");
//		String sessionMemberIdx = (String) request.getParameter("sessionMemberIdx");
//		
//		List<Integer> listOfChatRoomIdx = chatService.listOfChatRoomIdx(Integer.parseInt(tradingIdx));
//		
//		String logString = "";
//		for(int i : listOfChatRoomIdx) {
//			logString += Integer.toString(i) + ", ";
//		}
//		System.out.println("(listOfChatRoomIdx.do) chatRoomIdx 목록: " + logString);
//		
//		
//		
//		List<ChatRoomThumb> listOfChatRoomThumb = new ArrayList<ChatRoomThumb>();
//		for(int chatRoomIdx : listOfChatRoomIdx) {
//			
//			String name = chatService.selectBuyerName(chatRoomIdx); // chatRoomIdx의 memberIdx의 name
//			int unreadCount = chatService.selectUnreadCount(chatRoomIdx, sessionMemberIdx); // <<<(chatMessage where chatRoomIdx=...)에서, '내 sessionMemberIdx가 아닌 레코드' >>> -> is_read가 0인거
//			ChatMessage2 chatMessage2 = chatService.selectLastMessage(chatRoomIdx);
//			
//			if(chatMessage2 != null) {
//				
//				String content = chatMessage2.getContent(); // (chatMessage where chatRoomIdx=...)에서, 마지막 꺼 하나
//				String time = chatMessage2.getCreatedDate(); // (chatMessage where chatRoomIdx=...)에서, 마지막 꺼 하나
//				
//				ChatRoomThumb chatRoomThumb = new ChatRoomThumb();
//				chatRoomThumb.setChatRoomIdx(chatRoomIdx);
//				chatRoomThumb.setName(name);
//				chatRoomThumb.setUnreadCount(unreadCount);
//				chatRoomThumb.setContent(content);
//				chatRoomThumb.setTime(time);
//				
//				listOfChatRoomThumb.add(chatRoomThumb);
//			} else {
//				ChatRoomThumb chatRoomThumb = new ChatRoomThumb();
//				chatRoomThumb.setChatRoomIdx(chatRoomIdx);
//				chatRoomThumb.setName(name);
//				chatRoomThumb.setUnreadCount(unreadCount);
//				chatRoomThumb.setContent(" ");
//				chatRoomThumb.setTime(" ");
//				
//				listOfChatRoomThumb.add(chatRoomThumb);
//				
//			}
//			
//
//		}
//
//		return listOfChatRoomThumb;
//	}

	@ResponseBody
	@PostMapping(value = "/tradingChatPolling.do", produces = "application/json;charset=UTF-8")
	public List<Object> tradingChatPolling(HttpServletRequest request, HttpSession session) {
		/* [1]: return용 List<Object> 객체 */
		List<Object> result = new ArrayList<>();
		/* [2]: request에서 변수 뽑아두기 */
		/* (1) */ int v_tradingIdx = Integer.parseInt((String) request.getParameter("tradingIdx"));
		/* log */ System.out.println("v_tradingIdx: " + Integer.toString(v_tradingIdx));
		/* (2) */ int v_tradingMemberIdx = Integer.parseInt((String) request.getParameter("tradingMemberIdx"));
		/* log */ System.out.println("v_tradingMemberIdx: " + Integer.toString(v_tradingMemberIdx));
		/* (3) */ String v_tradingSold = (String) request.getParameter("tradingSold");
		/* log */ System.out.println("v_tradingSold: " + v_tradingSold);
		/* (4) */ int v_sessionMemberIdx = Integer.parseInt((String) request.getParameter("sessionMemberIdx"));
		/* log */ System.out.println("v_sessionMemberId: " + Integer.toString(v_sessionMemberIdx));
		// 값이 0이면 로그아웃 상태라는 뜻
		/* (5) */ int v_chatDrawerOpenedFlag = Integer.parseInt((String) request.getParameter("chatDrawerOpenedFlag"));
		/* log */ System.out.println("v_chatDrawerOpenedFlag: " + Integer.toString(v_chatDrawerOpenedFlag));
		/* (6) */ int v_msgChatRoomIdx = Integer.parseInt((String) request.getParameter("msgChatRoomIdx"));
		/* log */ System.out.println("v_msgChatRoomIdx: " + Integer.toString(v_msgChatRoomIdx));
		/* (7) */ int v_msgLastMsgIdx = Integer.parseInt((String) request.getParameter("msgLastMsgIdx"));
		/* log */ System.out.println("v_msgLastMsgIdx: " + Integer.toString(v_msgLastMsgIdx));
		/* (8) */ int v_msgLeaveRequestedFlag = Integer
				.parseInt((String) request.getParameter("msgLeaveRequestedFlag"));
		/* log */ System.out.println("v_msgLeaveRequestedFlag: " + Integer.toString(v_msgLeaveRequestedFlag));
		/* (9) */ int v_lastMyMessageIdx = Integer.parseInt((String) request.getParameter("lastMyMessageIdx"));
		/* log */ System.out.println("v_lastMyMessageIdx: " + Integer.toString(v_lastMyMessageIdx));

		/* [3]: 사용자가 보고있던 페이지가 삭제되었는지, tradingIdx로 존재 여부로 확인 */
		int tradingIsDeletedFlag;
		if (tradingService.selectTrading(v_tradingIdx) == null) {
			tradingIsDeletedFlag = 1;
			result.add(tradingIsDeletedFlag); // result[0];

			session.setAttribute("tw_msg", "보고있던 게시글이 삭제되었어요.");
			session.setAttribute("tw_status", "info");
			return result;
		} else {
			tradingIsDeletedFlag = 0;
			result.add(tradingIsDeletedFlag); // result[0];
		}

		/*
		 * [4]: 사용자가 보고있던 페이지의 상품의 판매완료가, (미완료 -> 완료)로 변한 것인지 확인, 원래부터 판매완료인 경우가 아니라 판매
		 * 미완료였는데, 실시간으로 판매완료로 전환된 경우를 검출
		 */
		int tradingSwitchToSoldFlag;
		if ((v_tradingSold.equals("false")) && (tradingService.checkSold(v_tradingIdx).equals("1"))) {
			tradingIsDeletedFlag = 1;
			result.add(tradingIsDeletedFlag); // result[1];

			session.setAttribute("tw_msg", "방금 해당 상품이 판매완료 되었어요.");
			session.setAttribute("tw_status", "info");
			return result;
		} else {
			tradingIsDeletedFlag = 0;
			result.add(tradingIsDeletedFlag); // result[1];
		}

		/*
		 * [5]: 나의 채팅방 나가기 요청 (전제: 채팅방 나가기 버튼은 '반드시' 채팅방에 들어와야 클릭 가능. 따라서,
		 * v_msgLeaveRequestedFlag == 1 라면, v_msgChatRoomIdx는 반드시 0이 '아니다')
		 */
		int chatRoomRemovedByMeFlag;
		if (v_msgLeaveRequestedFlag == 1) {
			chatRoomRemovedByMeFlag = 1;
			result.add(chatRoomRemovedByMeFlag); // result[2];

			chatService.deleteChatMessages(v_msgChatRoomIdx);
			chatService.deleteChatRoom(v_msgChatRoomIdx);

			return result;
		} else {
			chatRoomRemovedByMeFlag = 0;
			result.add(chatRoomRemovedByMeFlag); // result[2];
		}

		/*
		 * [6]: 상대방의 채팅방 나감 감지. 상대방이 실시간으로 채팅방을 나간 경우 메시지 표시. 채팅방 존재 여부 확인 후,
		 * v_msgLeaveRequestedFlag가 0인 사람에게만 '상대방이 채팅방을 나갔다'라는 메시지 표시. '나가기 버튼'을 누른 사람은
		 * 자기가 아니가 알릴 필요 없음
		 */
		int chatRoomRemovedByCounterPartFlag;
		if ((v_msgChatRoomIdx != 0) && (v_msgLeaveRequestedFlag == 0)
				&& (chatService.isChatRoomExist(v_msgChatRoomIdx) == 0)) { // 메시지가 비활성화 상태(0)가 아니고 && '내'가 채팅방 나간다는 요청
																			// 없었는데, && 내가 요청한 채팅방이 없음
			chatRoomRemovedByCounterPartFlag = 1;
			result.add(chatRoomRemovedByCounterPartFlag); // result[3];

			session.setAttribute("tw_msg", "진행 중인 채팅에서 상대방이 나갔어요.");
			session.setAttribute("tw_status", "info");
			return result;
		} else {
			chatRoomRemovedByCounterPartFlag = 0;
			result.add(chatRoomRemovedByCounterPartFlag); // result[3];
		}

		/*
		 * [7]: 채팅서랍이 활성화('on' 클래스 보유) 인지 확인. 아니라면, [8]과 [9]와 [10]에 null이 들어감, 맞다면
		 * [11]에는 null이 들어감
		 */
		int chatAndRoomLoadActivationFlag;
		if (v_chatDrawerOpenedFlag == 1) {
			chatAndRoomLoadActivationFlag = 1;
			result.add(chatAndRoomLoadActivationFlag); // result[4];
		} else {
			chatAndRoomLoadActivationFlag = 0;
			result.add(chatAndRoomLoadActivationFlag); // result[4];
		}

		/* [8]: 메시지 로드 */
		if ((chatAndRoomLoadActivationFlag == 1) && (v_msgChatRoomIdx != 0)) {
			chatService.setReadMessage(v_msgChatRoomIdx, v_msgLastMsgIdx, v_sessionMemberIdx);
			List<ChatMessage2> msgList = chatService.loadMessages2(v_msgChatRoomIdx, v_msgLastMsgIdx);
			result.add(msgList); // result[5];
		} else {
			result.add(null); // result[5];
		}

		/* [9]: 채팅방리스트 로드. 판매자인 경우에만 로드한다. */
		if ((chatAndRoomLoadActivationFlag == 1) && (v_tradingMemberIdx == v_sessionMemberIdx)) {
			List<Integer> listOfChatRoomIdx = chatService.listOfChatRoomIdx(v_tradingIdx);
			List<ChatRoomThumb> listOfChatRoomThumb = new ArrayList<ChatRoomThumb>();

			for (int chatRoomIdx : listOfChatRoomIdx) {
				String name = chatService.selectBuyerName(chatRoomIdx); // chatRoomIdx의 memberIdx의 name
				int unreadCount = chatService.selectUnreadCount(chatRoomIdx, v_sessionMemberIdx); // <<<(chatMessage
																									// where
																									// chatRoomIdx=...)에서,
																									// '내
																									// sessionMemberIdx가
																									// 아닌 레코드' >>> ->
																									// is_read가 0인거
				ChatMessage2 chatMessage2 = chatService.selectLastMessage(chatRoomIdx);

				if (chatMessage2 != null) {
					String content = chatMessage2.getContent(); // (chatMessage where chatRoomIdx=...)에서, 마지막 꺼 하나
					String time = chatMessage2.getCreatedDate(); // (chatMessage where chatRoomIdx=...)에서, 마지막 꺼 하나

					ChatRoomThumb chatRoomThumb = new ChatRoomThumb();
					chatRoomThumb.setChatRoomIdx(chatRoomIdx);
					chatRoomThumb.setName(name);
					chatRoomThumb.setUnreadCount(unreadCount);
					chatRoomThumb.setContent(content);
					chatRoomThumb.setTime(time);

					listOfChatRoomThumb.add(chatRoomThumb);
				} else {
					ChatRoomThumb chatRoomThumb = new ChatRoomThumb();
					chatRoomThumb.setChatRoomIdx(chatRoomIdx);
					chatRoomThumb.setName(name);
					chatRoomThumb.setUnreadCount(unreadCount);
					chatRoomThumb.setContent("채팅이 시작됐어요");
					chatRoomThumb.setTime("-1");

					listOfChatRoomThumb.add(chatRoomThumb);
				}
			}

			result.add(listOfChatRoomThumb); // result[6];
		} else {
			result.add(null); // result[6];
		}

		/* [10]: v_lastMyMessageIdx가 read되었는지 확인 후, 맞다면 1, 아니면 0 반환 */
		int lastMyMessageChangeToReadFlag;
		if ((chatAndRoomLoadActivationFlag == 1) && (v_msgChatRoomIdx != 0) && (v_lastMyMessageIdx != 0)) {
			lastMyMessageChangeToReadFlag = chatService.messageReadCheck(v_lastMyMessageIdx); // 읽은 상태면 1, 안 읽은 상태면 0 반환
			result.add(lastMyMessageChangeToReadFlag); // result[7];
		} else {
			result.add(null); // result[7];
		}

		/* [11]: 채팅Drawer가 내려가 있을 경우에만 활성화됨. 채팅Drawer 토글 아이콘에 합산 UnreadCount 표시 */
		int unreadCount = 0;
		if ((v_chatDrawerOpenedFlag == 0) && (v_sessionMemberIdx != 0)) {

			if (v_tradingMemberIdx == v_sessionMemberIdx) { // 판매자인 경우
				List<Integer> listOfChatRoomIdx = chatService.listOfChatRoomIdx(v_tradingIdx);
				for (int chatRoomIdx : listOfChatRoomIdx) {
					unreadCount += chatService.selectUnreadCount(chatRoomIdx, v_sessionMemberIdx);
				}
			} else { // 구매자인 경우
				unreadCount += chatService.selectUnreadCountForBuyer(v_tradingIdx, v_sessionMemberIdx);
			}

			result.add(unreadCount); // result[8];
		} else {
			result.add(null); // result[8];
		}

		return result;
	}
}
