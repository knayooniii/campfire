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
public class CampRegistrationPhotoDto {

	private int campNum;
	private String campPhotoName;
	private String campPhotoURL;

}
