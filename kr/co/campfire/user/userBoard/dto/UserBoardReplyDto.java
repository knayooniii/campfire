package kr.co.campfire.user.userBoard.dto;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserBoardReplyDto {
	private int postReplyNum;
	private int postNum;
	private int memberNum;
	private String memberId;
	private String postReplyContent;
	private Timestamp postReplyCreateDate;
	private String newDate; 
	private int replyLikeCount;
}
