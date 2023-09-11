package kr.co.campfire.chat.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChatRoomThumb {
	private int chatRoomIdx;
	private String name;
	private int unreadCount;
	private String content;
	private String time;
	
}
