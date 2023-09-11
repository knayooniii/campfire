<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<c:choose>
    <c:when test="${trading.sold eq false}">
        <c:if test="${(sessionMemberIdx != null)}">
            <c:choose>
                <c:when test="${(sessionMemberIdx != trading.memberIdx) && (chatRoomIdx != null)}">
                    <div class="chatIconChatDivider forBuyer">
                        <div id="chatIcon" onclick="toggleChat(this)">
                            <h3 id="unreadCountAll"></h3>
                            <img src="${pageContext.request.contextPath}/resources/images/tw/keyboard_arrow_up.png"></img>
                        </div>
                        <div id="chat" data-leave_clicked = "false">
                            <div class="container" id="chatRoomContainer">
                                <div class="informationMessageListMessagingDivider">
                                    <div class="container" id="informationContainer">
                                        <div class="buttonInChat leaveChatButton" onclick="leaveChatBtn('${sessionMemberIdx}', '${trading.idx}', '${trading.memberIdx}')">
                                            <img src="${pageContext.request.contextPath}/resources/images/tw/close.png" alt="">
                                        </div>
                                        <div id="chatTitle">
                                            <h3>${member.name}님과의 대화</h3>
                                        </div>
                                        <div class="buttonInChat leaveChatButton" style="visibility: hidden;">
                                            <img src="${pageContext.request.contextPath}/resources/images/tw/close.png" alt="">
                                        </div>
                                    </div>
                                    <div class="container" id="messageListContainer" data-chat_room_idx = "0" data-last_msg_idx = "0" data-leave_requested_flag = "0" data-last_read_my_message_idx="0">
                                        
                                    </div>
                                    <div class="container" id="messagingContainer">
                                        <input type="text" name="demo-name" id="demo-name" value="" placeholder="">
                                        <div class="button primary sendButtonBlock" onclick="sendMessage('${sessionMemberIdx}')">
                                            <img onclick="" src="${pageContext.request.contextPath}/resources/images/tw/send.svg" alt="send_inputted_message">
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:when>
                <c:when test="${(sessionMemberIdx != trading.memberIdx) && (chatRoomIdx == null)}">

                </c:when>
                <c:otherwise> <!-- sessionMemberIdx == trading.memberIdx -->
                    <div class="chatIconChatDivider forSeller">
                        <div id="chatIcon" onclick="toggleChat(this)">
                            <h3 id="unreadCountAll"></h3>
                            <img src="${pageContext.request.contextPath}/resources/images/tw/keyboard_arrow_up.png" onclick="toggleChat(this)"></img>
                        </div>
                        <div id="chat" data-leave_clicked = "false">
                            <div class="container" id="chatRoomListContainer">
                                <!-- <div class="chatRoomDoor">
                                    <div class="rowDivider">
                                        <div class="firstRow">
                                            <div class="personUnreadCountDivider">
                                                <div class="person">
                                                    <h3 class="buyer">구매자</h3>
                                                    <h3>모래표범</h3>
                                                </div>
                                                <h3 class="unreadCount">2123 </h3>
                                            </div>
                                        </div>
                                        <div class="secondRow">
                                            <div class="messageTimeDivider">
                                                <div class="message">
                                                    <p>어느 회사의 제품인가요?</p>
                                                </div>
                                                <div class="time">
                                                    <p>21:07</p>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div> -->
                            </div>
                            <div class="container" id="chatRoomContainer">
                                <div class="informationMessageListMessagingDivider">
                                    <div class="container" id="informationContainer">
                                        <div class="buttonInChat hideChatButton visible" onclick="hideChat()">
                                            <img src="${pageContext.request.contextPath}/resources/images/tw/arrow_back_ios.png" alt="">
                                        </div>
                                        <div class="buttonInChat leaveChatButton" onclick="leaveChatBtn('${sessionMemberIdx}', '${trading.idx}', '${trading.memberIdx}')">
                                            <img src="${pageContext.request.contextPath}/resources/images/tw/close.png" alt="">
                                        </div>
                                        <div id="chatTitle">
                                            <h3>${trading.title}</h3>
                                        </div>
                                        <div class="buttonInChat hideChatButton" style="width: 6vw; visibility: hidden;">
                                        </div>
                                    </div>
                                    <div class="container" id="messageListContainer" data-chat_room_idx = "0" data-last_msg_idx = "0" data-leave_requested_flag = "0" data-last_read_my_message_idx="0">
                                        
                                    </div>
                                    <div class="container" id="messagingContainer">
                                        <input type="text" name="demo-name" id="demo-name" value="" placeholder="">
                                        <div class="button primary sendButtonBlock" onclick="sendMessage('${sessionMemberIdx}')">
                                            <img onclick="" src="${pageContext.request.contextPath}/resources/images/tw/send.svg" alt="send_inputted_message">
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:otherwise>
            </c:choose>
        </c:if>
    </c:when>
    <c:otherwise>
    </c:otherwise>
</c:choose>

<script>


document.addEventListener("DOMContentLoaded", function () {
    if('${chatDrawerOn}' == 'true') {
        document.querySelector('#chatIcon').click();
        
    }

    const messagingBox = document.querySelector('#messagingContainer input');
    const sendBtn = document.querySelector('.sendButtonBlock');
    if( messagingBox != null) {
        messagingBox.addEventListener("keydown", function(event) {
            if (event.key === "Enter") {
                sendBtn.click();
            }
        });
    }

    tradingChatPolling();
});

function toggleChat(chatToggleButton) {
	const chatIconImg = document.querySelector("#chatIcon img");
    const unreadCountAll = document.querySelector("#unreadCountAll");
	const chatIconChatDivider = document.querySelector('.chatIconChatDivider');
    const messageListContainer = document.querySelector('#messageListContainer'); 
    const chatRoomListContainer = document.querySelector('#chatRoomListContainer');

	if (chatToggleButton.classList.contains('on')) {
		chatToggleButton.classList.remove('on');

		chatIconChatDivider.style.bottom = '-35vw';
		chatIconImg.src = '../resources/images/tw/keyboard_arrow_up.png';
		chatIconImg.style.top = 'calc(-4vw - 4vw)';
		
        messageListContainer.setAttribute("data-last_msg_idx", 0);
        messageListContainer.setAttribute('data-chat_room_idx', 0);
        messageListContainer.setAttribute("data-leave_requested_flag", 0);
        messageListContainer.setAttribute('data-last_read_my_message_idx', 0);
        messageListContainer.innerHTML = "";

	} else {
		chatToggleButton.classList.add('on')

		chatIconChatDivider.style.bottom = '0';
		chatIconImg.src = '../resources/images/tw/keyboard_arrow_down.png';
		chatIconImg.style.top = 'calc(-4vw - 1vw)';
        unreadCountAll.style.display = "none";

        if('${sessionMemberIdx}' == '${trading.memberIdx}') { // 판매자면
            hideChat();
            chatRoomListContainer.innerHTML = "";
        } 

        if('${sessionMemberIdx}' != '${trading.memberIdx}') { // 구매자면
            messageListContainer.setAttribute("data-chat_room_idx", '${chatRoomIdx}');
        }

        messageListContainer.innerHTML = "";
	}
}

async function tradingChatPolling() {
    console.log("tradingChatPolling() is Started");

    /* [1-1]: [1-2]에서 모을 정보를 위한 HTML요소 준비 */
    let chatToggleButton = document.querySelector('#chatIcon'); // 채팅 수납 on/off 버튼
    let messageListContainer = document.querySelector('#messageListContainer'); // 이하 msg
    let chatRoomListContainer = document.querySelector('#chatRoomListContainer'); // 이하 cr, seller가 아닌 이상 null이 담길 것

    /* [1-2]: 기초적인 정보를 포괄적으로 모음 */
    /*(1)*/ let tradingIdx = '${trading.idx}'; // 먼저 제일 기본이 될 현재 게시글의 idx
    /*(2)*/ let tradingMemberIdx = '${trading.memberIdx}'; // 게시글 작성자 (판매자) idx
    /*(3)*/ let tradingSold = '${trading.sold}'; // true or false 가 담김
    /*(4)*/ let sessionMemberIdx; if(('${sessionMemberIdx}' == null) || ('${sessionMemberIdx}' == "")){sessionMemberIdx = 0} else {sessionMemberIdx = '${sessionMemberIdx}'} ; // 세션에 접속한 회원의 idx, (로그아웃 상태면 sessionMemberIdx는 0으로 둠)
    /*(5)*/ let chatDrawerOpenedFlag; if(chatToggleButton == null){chatDrawerOpenedFlag = 0} else {if(chatToggleButton.classList.contains('on')) {chatDrawerOpenedFlag = 1} else {chatDrawerOpenedFlag = 0}}; // 채팅창 올림(염) 여부 
    /*(6)*/ let msgChatRoomIdx; if(messageListContainer == null ){msgChatRoomIdx = 0} else {msgChatRoomIdx = messageListContainer.getAttribute('data-chat_room_idx');}  // 기본값 0
    /*(7)*/ let msgLastMsgIdx; if(messageListContainer == null){msgLastMsgIdx = 0} else {msgLastMsgIdx = messageListContainer.getAttribute('data-last_msg_idx')} ; // 기본값 0
    /*(8)*/ let msgLeaveRequestedFlag; if(messageListContainer == null){msgLeaveRequestedFlag = 0} else {msgLeaveRequestedFlag = messageListContainer.getAttribute('data-leave_requested_flag')}; // 기본값 0
    /*(9-1)*/ let myMessageElements = document.querySelectorAll(".myMessage");
    /*(9-2)*/ let lastMyMessageIdx;
    /*(9-3)*/ if(myMessageElements.length == 0){lastMyMessageIdx = 0 } else {lastMyMessageIdx = myMessageElements[myMessageElements.length - 1].getAttribute("data-msg_idx")};

	/* [1-4]: [1-2]의 정보들 FormData에 담음 */
	var formData = new FormData();
    /*(1)*/ formData.append("tradingIdx", tradingIdx);
    /*(2)*/ formData.append("tradingMemberIdx", tradingMemberIdx);
    /*(3)*/ formData.append("tradingSold", tradingSold);
    /*(4)*/ formData.append("sessionMemberIdx", sessionMemberIdx);
    /*(5)*/ formData.append("chatDrawerOpenedFlag", chatDrawerOpenedFlag);
    /*(6)*/ formData.append("msgChatRoomIdx", msgChatRoomIdx);
    /*(7)*/ formData.append("msgLastMsgIdx", msgLastMsgIdx);
    /*(8)*/ formData.append("msgLeaveRequestedFlag", msgLeaveRequestedFlag);
    /*(9)*/ formData.append("lastMyMessageIdx", lastMyMessageIdx)

	/* [1-5]: [1-3]의 데이터 컨트롤러의 @ResponseBody에 보냄 */
	fetch('http://localhost/chat/tradingChatPolling.do', {
		method: 'POST',
		body: formData,
		redirect: 'manual',
	})
	.then(response => {        
		return response.json();
	})
	.then(result => {
        /* [2-1]: 데이터 확인용 로그 */
        let resultLogString = "tradingChatPolling.do's result arrived on tradingPolling()" + "\n";
        /*(1)*/ if(result[0] != null){resultLogString += "tradingIsDeletedFlag: " + result[0].toString() + "\n";}
        /*(2)*/ if(result[1] != null){resultLogString += "tradingSwitchToSoldFlag: " + result[1].toString() + "\n";}
        /*(3)*/ if(result[2] != null){resultLogString += "chatRoomRemovedByMeFlag: " + result[2].toString() + "\n";}
        /*(4)*/ if(result[3] != null){resultLogString += "chatRoomRemovedByCounterPartFlag: " + result[3].toString() + "\n";}
        /*(5)*/ if(result[4] != null){resultLogString += "chatAndRoomLoadActivationFlag: " + result[4].toString() + "\n";}
        /*(6)*/ if(result[5] != null){resultLogString += "msgList.length: " + result[5].length.toString() + "\n";}
        /*(7)*/ if(result[6] != null){resultLogString += "listOfChatRoomThumb.length: " + result[6].length.toString() + "\n";}
        /*(7)*/ if(result[7] != null){resultLogString += "lastMyMessageChangeToReadFlag: " + result[7].toString() + "\n";}
        /*(8)*/ if(result[8] != null){resultLogString += "unreadCountAll: " + result[8].toString() + "\n";}
        console.log(resultLogString);

        /* [2-2]: result[0] (int tradingIsDeletedFlag) */
        if(result[0] == 1) {
            window.location.href = "/trading/list.do";
            return;
        }

        /* [2-3]: result[1] (int tradingSwitchToSoldFlag) */
        if(result[1] == 1) {
            window.location.href = "/trading/detail.do" + "?tradingIdx=" + tradingIdx;
            return;
        }

        /* [2-4]: result[2] (int chatRoomRemovedByMeFlag) */
        if(result[2] == 1) {
            if(tradingMemberIdx == sessionMemberIdx){ // 'Me'가 판매자라면
                window.location.href = "/trading/detail.do" + "?tradingIdx="+ tradingIdx + "&chatDrawerOn=true";
            } else { // 'Me'가 구매자라면
                window.location.href = "/trading/detail.do" + "?tradingIdx=" + tradingIdx;
            }
            return;
        }

        /* [2-5]: result[3] (int chatRoomRemovedByCounterPartFlag) */
        if(result[3] == 1) {
            if(tradingMemberIdx == sessionMemberIdx){ // 'Me'가 판매자라면
                window.location.href = "/trading/detail.do" + "?tradingIdx="+ tradingIdx + "&chatDrawerOn=true";
            } else { // 'Me'가 구매자라면
                window.location.href = "/trading/detail.do" + "?tradingIdx=" + tradingIdx;
            }
            return;
        }

        /* [2-6]: result[4] (int chatAndRoomLoadActivationFlag) */
        let chatAndRoomLoadActivationFlag = result[4];

        /* [2-7]: result[5] ((List<ChatMessage2> msgList) or (null)) */
        if(result[5] != null) {
            let renewedlastReadMyMessageIdx = 0;
            let htmlDataAll = '';
            let isMyMessageFlag;

            result[5].forEach(item => {
                let htmlData = '';

                if(item.memberIdx == '${sessionMemberIdx}') { // 메시지가 내꺼면
                    htmlData += '<div class="message myMessage" data-msg_idx="' + item.idx + '">';
                    isMyMessageFlag = 1;
                } else {
                    htmlData += '<div class="message otherPersonMessage">';
                    isMyMessageFlag = 0;
                }
                htmlData += '    <div class="personContentDivider">';
                htmlData += '        <div class="person">';
                htmlData += '            <div class="tag">';

                if(item.memberIdx == '${trading.memberIdx}') { // 메시지의 회원idx가 trading 페이지의 회원 idx랑 같다면 (== 판매자라면)
                    htmlData += '                <h3 class="seller">판매자</h3>';
                } else {
                    htmlData += '                <h3 class="buyer">구매자</h3>';
                }
                htmlData +=             '</div>';
                htmlData +=             '<div class="name">';
                htmlData +=                 '<h3>' + item.name +'</h3>';
                htmlData +=             '</div>';
                htmlData +=         '</div>';
                htmlData +=         '<div class="content">';
                htmlData +=             '<div class="textIsReadDivider">';
                htmlData +=                 '<div class="text">';
                htmlData +=                     '<p>' + item.content + '</p>';
                htmlData +=                     '<div class="time">';
                htmlData +=                        '<p>' + extractTimeFromDateString(item.createdDate) + '</p>';
                htmlData +=                     '</div>';
                htmlData +=                 '</div>';
                htmlData +=                 '<div class="isRead off">';
                htmlData +=                     '<p>' + "읽음" +'</p>';
                htmlData +=                 '</div>';
                htmlData +=             '</div>';
                htmlData +=         '</div>';
                htmlData +=     '</div>';
                htmlData += '</div>';

                if((isMyMessageFlag == 1) && (item.read.toString() == 'true')) {
                    renewedlastReadMyMessageIdx = item.idx;
                }

                htmlDataAll += htmlData;
            });

            if(htmlDataAll != "") {
                messageListContainer.innerHTML += htmlDataAll;
                renewReadSign(renewedlastReadMyMessageIdx);
                
                messageListContainer.scrollTop = messageListContainer.scrollHeight;
                messageListContainer.setAttribute("data-last_msg_idx", result[5][result[5].length - 1].idx);
            }
        }

        /* [2-8]: result[6] ((List<ChatRoomThumb> listOfChatRoomThumb) or (null)) */
        if(result[6] != null) {
            let oldChatRoomList = chatRoomListContainer.querySelectorAll(".chatRoomDoor");
            let oldChatRoomIdxList = Array.from(oldChatRoomList).map(element => element.getAttribute('data-chat_room_idx'));

            result[6].forEach(item => {
                const index = oldChatRoomIdxList.indexOf(String(item.chatRoomIdx));

                if (index == -1) { // oldChatRoomIdxList 어디에도 없다면, 새로이 추가
                    let htmlData = '';
                    htmlData += '<div class="chatRoomDoor" data-chat_room_idx = "' + item.chatRoomIdx + '" ' + 'onclick="setMessageListContainerChatRoomIdx(this)">'
                    htmlData +=     '<div class="rowDivider">'
                    htmlData +=         '<div class="firstRow">'
                    htmlData +=             '<div class="personUnreadCountDivider">'
                    htmlData +=                 '<div class="person">'
                    htmlData +=                     '<h3 class="buyer">구매자</h3>'
                    htmlData +=                     '<h3>' + item.name+ '</h3>'
                    htmlData +=                 '</div>'
                    if(item.unreadCount == 0) {
                        htmlData +=                 '<h3 class="unreadCount" style="display: none;">' + item.unreadCount + '</h3>'
                    } else {
                        htmlData +=                 '<h3 class="unreadCount" style="display: unset;">' + item.unreadCount + '</h3>'
                    }
                    htmlData +=             '</div>'
                    htmlData +=         '</div>'
                    htmlData +=         '<div class="secondRow">'
                    htmlData +=             '<div class="messageTimeDivider">'
                    htmlData +=                 '<div class="message">'
                    htmlData +=                     '<p>' + item.content + '</p>'
                    htmlData +=                 '</div>'
                    htmlData +=                 '<div class="time">'
                    if(item.time == -1) {
                        htmlData +=                     '<p style="display: none;">' + '00:00' + '</p>'
                    } else {
                        htmlData +=                     '<p style="display: unset;">' + extractTimeFromDateString(item.time) + '</p>'
                    }
                    htmlData +=                 '</div>'
                    htmlData +=             '</div>'
                    htmlData +=         '</div>'
                    htmlData +=     '</div>'
                    htmlData += '</div>'

                    chatRoomListContainer.innerHTML += htmlData;

                } else { // oldChatRoomIdxList 어딘가에 있다면, 해당 ChatRoom을 업데이트
                    let selector = '.chatRoomDoor[data-chat_room_idx="' + item.chatRoomIdx + '"]';
                    let chatRoomToUpdate = document.querySelector(selector);
                    if(item.unreadCount == 0) {
                        chatRoomToUpdate.querySelector('.unreadCount').style.display = "none";
                        chatRoomToUpdate.querySelector('.unreadCount').textContent = item.unreadCount;
                    } else {
                        chatRoomToUpdate.querySelector('.unreadCount').style.display = "unset";
                        chatRoomToUpdate.querySelector('.unreadCount').textContent = item.unreadCount;
                    }
                    chatRoomToUpdate.querySelector('.message').children[0].textContent = item.content;
                    if(item.time == -1) {
                        chatRoomToUpdate.querySelector('.time').style.display = "none";
                        chatRoomToUpdate.querySelector('.time').children[0].textContent = extractTimeFromDateString(item.time);
                    } else {
                        chatRoomToUpdate.querySelector('.time').style.display = "unset";
                        chatRoomToUpdate.querySelector('.time').children[0].textContent = extractTimeFromDateString(item.time);
                    }
                    

                    delete oldChatRoomIdxList[index]; // 이용가치 없어진 해당 요소 삭제 (delete는 배열 길이에 영향을 주지 않는다. 해당 공간을 'empty'로 변경함)
                }
            });

            // 3. 삭제
            oldChatRoomIdxList.forEach(oldChatRoomIdx => {
                if (oldChatRoomIdx !== undefined) {
                    document.querySelector('.chatRoomDoor[data-chat_room_idx="' + oldChatRoomIdx + '"]').remove();
                }
            });
        }

        /* [2-9]: result[7] ((int lastMyMessageChangeToReadFlag) or (null)) */
        if((result[7] != null) && (result[7] == 1)&&(lastMyMessageIdx != 0)) {
            renewReadSign(lastMyMessageIdx);
        }

        /* [2-10]: result[8] (int unreadCount) */
        if(result[8] != null) {
            let unreadCountAll = document.querySelector("#unreadCountAll")
            if(result[8] == 0) {
                unreadCountAll.style.display = "none";
            } else {
                unreadCountAll.style.display = "unset";
            }
            unreadCountAll.textContent = result[8];
        }
	})
	.catch(error => {
        console.error(error);
	});

    await new Promise(resolve => setTimeout(resolve, 500)); // 0.5초 대기
    await tradingChatPolling(); // 재귀 호출

} // End of tradingChatPolling()

function setMessageListContainerChatRoomIdx(chatRoomDoorObject) {
    showChat();

    let chatRoomIdx = chatRoomDoorObject.getAttribute("data-chat_room_idx");
    let counterPartName = chatRoomDoorObject.querySelector(".person > *:nth-child(2)").textContent;
    var messageListContainer = document.querySelector('#messageListContainer');

    messageListContainer.innerHTML = "";
    messageListContainer.setAttribute('data-chat_room_idx', chatRoomIdx);
    messageListContainer.setAttribute('data-last_msg_idx', 0);
    messageListContainer.setAttribute('data-leave_requested_flag', 0);
    messageListContainer.setAttribute('data-last_read_my_message_idx', 0);

    var chatTitleText = document.querySelector('#chatTitle h3');
    chatTitleText.textContent = counterPartName + '님과의 대화';
}

function hideChat() {
	let messageListContainer = document.querySelector("#messageListContainer");
	messageListContainer.setAttribute("data-chat_room_idx", "0");
	messageListContainer.setAttribute("data-last_msg_idx", "0");
	messageListContainer.setAttribute("data-leave_requested_flag", "0");
	messageListContainer.innerHTML = "";

	let chatRoomContainer = document.querySelector("#chatRoomContainer");
    chatRoomContainer.style.display = "none";
	//chatRoomContainer.classList.remove("visible");
	//chatRoomContainer.classList.add("hidden");
}

function showChat() {
	let hchatRoomContainer = document.querySelector("#chatRoomContainer");
    chatRoomContainer.style.display = "flex";
	//chatRoomContainer.classList.remove("hidden");
	//chatRoomContainer.classList.add("visible");
}

function renewReadSign(newlastReadMyMessageIdx) {
    if(newlastReadMyMessageIdx != 0) {
        let messageListContainer = document.querySelector('#messageListContainer');
        let oldLastReadMyMessageIdx = messageListContainer.getAttribute("data-last_read_my_message_idx");

        if(oldLastReadMyMessageIdx != 0) {
            let secondLastReadMyMessage = messageListContainer.querySelector('.myMessage[data-msg_idx="' + oldLastReadMyMessageIdx + '"]');
            let isReadContainer = secondLastReadMyMessage.querySelector(".isRead");
            isReadContainer.classList.remove("on");
            isReadContainer.classList.add("off");
        }

        let firstLastReadMyMessage = messageListContainer.querySelector('.myMessage[data-msg_idx="' + newlastReadMyMessageIdx + '"]');
        let isReadContainer = firstLastReadMyMessage.querySelector(".isRead");
        isReadContainer.classList.remove("off");
        isReadContainer.classList.add("on");
        messageListContainer.setAttribute("data-last_read_my_message_idx", newlastReadMyMessageIdx);
    }
}

</script>

