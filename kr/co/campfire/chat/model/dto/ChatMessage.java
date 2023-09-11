package kr.co.campfire.chat.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChatMessage {
	private int idx;
	private int chatRoomIdx;
	private int memberIdx;
	private String content;
	private String createdDate;
	private boolean isRead;
}
