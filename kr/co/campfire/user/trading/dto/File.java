package kr.co.campfire.user.trading.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class File {
	private int idx;
	private int tradingIdx;
	private String uploadPath; // 경로
	private String uploadName; // 변경된 파일명
	private String uploadOriginName; // 원본 파일명
}
