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
public class CampRegistrationAmenityDto {

	private String campAmenityPhoto;
	private String campAmenityItem;
	private int campNum;
}
