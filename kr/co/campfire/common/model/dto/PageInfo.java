package kr.co.campfire.common.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor // 기본 생성자 주입
@AllArgsConstructor // 매개변수 있는 생성자 주입
public class PageInfo {
	private int listCount;
	private int boardLimit;
	private int pageCount;
	private int pageBlockRangeLimit;
	private int currentPage;
	private int pageBlockCount;
	private int currentPageBlock;
	private int startPage;
	private int endPage;
	private int startRow;
	private int endRow;
	private int firstPageOfPreviousBlock;
	private int firstPageOfNextBlock;
}