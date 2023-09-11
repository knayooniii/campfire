package kr.co.campfire.user.campInfo.dto;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CampInfoFunctionDto {
	private int campNum;
	private int memberNum;
	private int postReplyNum;
	private String memberId;
	private String postReplyContent;
	private Timestamp postReplyCreateDate;
	private String newDate;
	private int replyLikeCount;

}
