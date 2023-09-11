package kr.co.campfire.user.campInfo.dto;

import java.security.Timestamp;

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
public class CampInfoDto {
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
	   private int campView;
	   private int likeCamp;
	   private String campCreateDate;
	   private String campAmenity;
	   private String campAmenityName;
	   private String campTag;
	   
    
    private String campReviewContent;
    private Timestamp campReviewCommentDate;
    
    
    private int wishlist;
	
	
    private String campPhotoName;
    private String campPhotoURL;
     
    
    
    
}
