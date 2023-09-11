package kr.co.campfire.user.campSearch.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.campfire.common.dto.PageInfo;
import kr.co.campfire.user.campSearch.dto.CampSearchDto;

import java.util.List;

public interface CampSearchService {


	int selectCampSearchCount();

	int selectLikeCount(int campNum);

	List<CampSearchDto> selectCampSearch(PageInfo pi, CampSearchDto campSearchDto);

	List<CampSearchDto> selectCampSearch(PageInfo pi, String campGroup , String status);
	
	List<CampSearchDto> getCampAddr(String campAddr);

	List<CampSearchDto> selectCampSearch(PageInfo pi);

	List<CampSearchDto> selectCampPhoto(int campNum);


	int selectCampSearchCount( CampSearchDto campSearchDto);

	int selectCampSearchCount( String campGroup , String status);

	List<CampSearchDto> selectCampSearch(PageInfo pi, String category);





}
