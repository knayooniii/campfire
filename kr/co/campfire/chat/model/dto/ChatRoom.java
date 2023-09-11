package kr.co.campfire.chat.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChatRoom {
	private int idx;
	private int tradingIdx;
	private int memberIdx;
}
