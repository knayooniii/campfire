package kr.co.campfire.admin.registration.dto;

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
public class AdminCampRegistrationAmenityDto {

	private String campAmenityPhoto;
	private String campAmenityItem;
	private int campNum;
}
