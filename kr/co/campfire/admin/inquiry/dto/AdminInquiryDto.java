package kr.co.campfire.admin.inquiry.dto;

import java.sql.Timestamp;

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
public class AdminInquiryDto {
	private int inquiryNum;
	private int memberNum;
	private String memberId;
	private String inquiryTitle;
	private String inquiryContext;
	private Timestamp inquiryDateTimeCreated;
	private String inquiryAnswerFL;
	private String inquiryPublicFL;
	private String answerAdmin;
	private String answerTitle;
	private String answerContext;
	private Timestamp answerDateTimeCreated;
	private String inquiryNewDate;
	private String answerNewDate;
}
