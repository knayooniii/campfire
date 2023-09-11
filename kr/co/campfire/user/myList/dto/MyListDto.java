package kr.co.campfire.user.myList.dto;


import java.sql.Date;
import java.util.List;

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
public class MyListDto {

		private int memberNum;
		private int campX;
		private int campY;
		private int campPhoneNum;
		private int campPostCode;
		private String campName;
		private String campCheck;

		private Date campCreateDate;
		private int campNum;
		private String campAPlace;
		private String campType;
		private String campAmenity;
		private String campAddress;
		private String campKeyword;
		private String campTag;
		private String campGroup;
		private String status;

		private String campPhotoName;
		private String campPhotoURL;

		private int campView;
		private int likeCamp;
		private int wishList;

	}



