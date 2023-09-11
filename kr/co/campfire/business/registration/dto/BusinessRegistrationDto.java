package kr.co.campfire.business.registration.dto;

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
public class BusinessRegistrationDto {
	private String brNum;
	private int memberNum;
	private String brCompany;
	private String brSttCd;
	private String brArSttCd; 
	private String brRepname;
	private String brCreateDate;
	private String brReason;
	
}
