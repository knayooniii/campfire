package kr.co.campfire.common.template;

import kr.co.campfire.common.model.dto.PageInfo;

public class Pagination {

	public static PageInfo getPageInfo(int listCount, int currentPage, int pageBlockRangeLimit, int boardLimit) {
		
		int pageCount = (int) Math.ceil(((double) listCount/boardLimit));
		
		int pageBlockCount = (int) Math.ceil((double)pageCount / pageBlockRangeLimit);
		
		int currentPageBlock = (int) Math.ceil((double)currentPage / pageBlockRangeLimit);
		
		int startPage = (currentPageBlock - 1) * pageBlockRangeLimit + 1;
		
		int endPage = Math.min(pageCount, currentPageBlock * pageBlockRangeLimit);
		
		/*
		// log for test
		System.out.printf("---\n");
		System.out.printf("%s: %s\n", "listCount", listCount);
		System.out.printf("%s: %s\n", "boardLimit", boardLimit);
		System.out.printf("%s: %s\n", "pageCount", pageCount);
		System.out.printf("%s: %s\n", "pageBlockRangeLimit", pageBlockRangeLimit);
		System.out.printf("%s: %s\n", "currentPage", currentPage);
		System.out.printf("%s: %s\n", "pageBlockCount", pageBlockCount);
		System.out.printf("%s: %s\n", "currentPageBlock", currentPageBlock);
		System.out.printf("%s: %s\n", "startPage", startPage);
		System.out.printf("%s: %s\n", "endPage", endPage);
		System.out.printf("%s: %s\n", "((currentPageBlock - 1) - 1) * (pageBlockRangeLimit) + 1", ((currentPageBlock - 1) - 1) * (pageBlockRangeLimit) + 1);
		System.out.printf("%s: %s\n", "", (currentPageBlock +1)*pageBlockRangeLimit);
		*/
		
		/* (start) 2023-08-17 */
		// 레코드가 1부터 시작하는 경우 (페이지는 1부터 시작)
		int startRow = boardLimit * (currentPage - 1) + 1;
		int endRow = ((boardLimit * currentPage) < (listCount)) ?  (boardLimit * currentPage) : (listCount); // (a < b) ? a : b;
		// 블럭 이동 시...
		int firstPageOfPreviousBlock;
		if(currentPageBlock == 1) {
			firstPageOfPreviousBlock = 0;
		} else {
			firstPageOfPreviousBlock = ((currentPageBlock - 2) * pageBlockRangeLimit) + 1;
		}
		
		int firstPageOfNextBlock;
		if(currentPageBlock == pageBlockCount) {
			firstPageOfNextBlock = 0;
		} else {
			firstPageOfNextBlock = (currentPageBlock * pageBlockRangeLimit) + 1;
		}
		
		/* (end) 2023-08-17 */

		return new PageInfo(
				listCount, // 전체 게시글 수
				boardLimit, // 페이지 당 게시글 갯수 상한
				pageCount, // 전체 페이지 수
				pageBlockRangeLimit, // 페이지 선택바 한 블럭의 페이지 갯수 상한
				currentPage, // 현재 페이지
				pageBlockCount, // 페이지 선택바 블럭의 총 갯수
				currentPageBlock, // 페이지 선택바 블럭의 현재 순서 (몇 번째 블럭인가?) (1부터 시작)
				startPage, // 페이지바의 첫번째 페이지
				endPage, // 페이지바의 끝 페이지
				startRow, // 가져올 첫번째 레코드의 번호
				endRow, // 가져올 마지막 레코드의 번호
				firstPageOfPreviousBlock, // 이전 블럭의 첫번째 페이지 (없으면 0)
				firstPageOfNextBlock // 다음 블럭의 첫번째 페이지 (없으면 0)
				);
	}

	public static int getCurrentPage(int postNumber, int boardLimit) {
		
		int currentPage = (int) Math.ceil((double) postNumber / boardLimit);
		
		return currentPage;
	}
}


/*
// currentPage = 39
// pageBlockRangeLimit = 10
// currerntPage - 1: 38
// (currentPage-1) / pageLimit: 38/10: 3 (double형이 아니라 int형에서의 연산이라서 몫만 구해지고 나머지는 '버림'. 반올림이 아니다)
// (currentPage-1) / pageLimit * pageLimit: 38/10*10+1: 3*10+1: 31
// int startPage = ((currentPage-1) / pageBlockRangeLimit * pageBlockRangeLimit) + 1;
// int endPage = Math.min((startPage + pageBlockRangeLimit - 1), pageCount);
*/
		
