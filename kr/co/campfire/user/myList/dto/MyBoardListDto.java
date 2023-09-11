package kr.co.campfire.user.myList.dto;

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
public class MyBoardListDto {
	private int postNum;
	private int memberNum;
	private String memberId;
	private String postCategory;
	private Timestamp postCreateDate;
	private String postTitle;
	private String postContent;
	private int postViewCount;
	private int likeCount;
	private int replyCount;
	private List<MultipartFile> files;
	
	private String imageName;
	
	private String searchCtg;
	private String searchTxt;
	
	private String newCreateDate;
}
