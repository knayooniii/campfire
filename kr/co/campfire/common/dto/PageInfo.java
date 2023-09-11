package kr.co.campfire.common.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PageInfo {
	private int listCount; //전체 게시글 수
	private int currentPage; //현재 페이지
	private int pageLimit; //보여질 페이지 수
	private int boardLimit; //보여질 게시글 수
	
	//전체 페이지, 시작 페이지, 끝 페이지
	private int maxPage; 
	private int startPage;
	private int endPage;

	
}
