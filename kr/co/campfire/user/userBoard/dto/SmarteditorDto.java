package kr.co.campfire.user.userBoard.dto;

import java.sql.Date;
import java.sql.Timestamp;

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
public class SmarteditorDto {
	private MultipartFile filedata;
	private String callback;
	private String callback_func;
}
