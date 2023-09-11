package kr.co.campfire.user.trading.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Trading {
	private int idx;
	private int memberIdx;
	private int categoryIdx;
	private int regionIdx;
	private String title;
	private String content;
	private int price;
	private String createdDate;
	private boolean isSold;

}
