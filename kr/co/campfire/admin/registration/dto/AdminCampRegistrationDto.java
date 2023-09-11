package kr.co.campfire.admin.registration.dto;

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
public class AdminCampRegistrationDto {
	private int campNum;
	private int memberNum;
	private String campName;
	private int campPostCode;
	private String campAddr;
	private String campPhoneNum;
	private String campType;
	private String campLink;
	private String campAPlace;
	private double campX;
	private double campY;
	private String campCheck;
	private String campIntro;
	private int campOffsdPrice;
	private int campOffswPrice;
	private int campSdPrice;
	private int campSwPrice;
	private int campViews;
	private Date campCreateDate;
	private String newCampCreateDate;
	private List<AdminCampRegistrationPhotoDto> campPhotoList;
	private String campReason;
}
