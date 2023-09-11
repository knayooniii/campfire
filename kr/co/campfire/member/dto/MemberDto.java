package kr.co.campfire.member.dto;

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
public class MemberDto {
	
	
	private int memberNum;
	private String memberUserId;
	private String memberPw;
	private String memberName;
	private String memberGender;
	private String memberPostalcode;
	private String memberAdd1;
	private String memberAdd2;
	private String memberDateBirth;
	private String memberDatetimeCreated;
	private String memberGoogle;
	private String memberKakao;
	private String memberNaver;
	private String memberDivision;
}

