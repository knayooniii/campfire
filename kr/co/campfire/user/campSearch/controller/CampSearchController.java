package kr.co.campfire.user.campSearch.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.campfire.common.controller.Pagination;
import kr.co.campfire.common.dto.PageInfo;
import kr.co.campfire.user.campInfo.dto.CampInfoDto;
import kr.co.campfire.user.campInfo.service.CampInfoService;
import kr.co.campfire.user.campSearch.dto.CampSearchDto;
import kr.co.campfire.user.campSearch.service.CampSearchService;

@Controller
@RequestMapping("/campSearch")
public class CampSearchController {
	@Autowired
	private CampSearchService campSearchService;

	@RequestMapping("/camping.do")
	public String campSearch(CampSearchDto campSearchDto, Model model, HttpSession session, @RequestParam(name ="tagItem", defaultValue = "none") String[] tagItem,
			@RequestParam(name ="campAmenity", defaultValue = "none") String[] campAmenity,
			@RequestParam(value = "cpage", defaultValue = "1") int currentPage ,
		  @RequestParam(value = "searchTxt", defaultValue = "") String searchTxt,
		  @RequestParam(value = "selectCategory", defaultValue = "campLastUpdate") String category) {
		String campAddress = campSearchDto.getCampAddress();
		String campType = campSearchDto.getCampType();
		String campKeyword = campSearchDto.getCampKeyword();
		
		// 전체 게시글 수
		int listCount = 0;
		
		if (campAddress != null && campType != null && !campAddress.equals("") && !campType.equals("")) {
			listCount = campSearchService.selectCampSearchCount( campSearchDto);
		} else if (campAddress != null && !campAddress.equals("")) {
			listCount = campSearchService.selectCampSearchCount( campAddress, "C_ADDR");
		} else if (campType != null && !campType.equals("")) {
			listCount = campSearchService.selectCampSearchCount( campType, "C_TYPE");
		}else if (!tagItem[0].equals("none") && !campAmenity[0].equals("none")) {

			for (String tag : tagItem) {
				int newCampList = campSearchService.selectCampSearchCount( tag, "CT_TAG");
				listCount += newCampList;
			}
			
			for (String amenity : campAmenity) {
				System.out.println(amenity);

				int newCampList = campSearchService.selectCampSearchCount( amenity, "CA_AMENITY");
				listCount += newCampList;
			}

		} else if (!tagItem[0].equals("none")) {
			for (String tag : tagItem) {
				int newCampList = campSearchService.selectCampSearchCount( tag, "CT_TAG");
				listCount += newCampList;
			}			
		} else if (!campAmenity[0].equals("none")) {
			for (String amenity : campAmenity) {
				int newCampList = campSearchService.selectCampSearchCount( amenity, "CA_AMENITY");
				listCount += newCampList;
			}		
		} else if (campKeyword != null && !campKeyword.equals("")) {
			listCount = campSearchService.selectCampSearchCount( campKeyword, "C_KEYWORD");
		} else{ 
				listCount = campSearchService.selectCampSearchCount();
		} 
		
		System.out.println(listCount);
		
		
		// 보여질 페이지수
		int pageLimit = 10;
		// 한페이지에 보여질 게시글 수
		int boardLimit = 4;

		int row = listCount - (currentPage - 1) * boardLimit;

		// 페이징 로직 처리
		PageInfo pi = Pagination.getPageInfo(listCount, currentPage, pageLimit, boardLimit);

		List<CampSearchDto> campList = null;
		if (campSearchDto.equals("")) {
			campList = campSearchService.selectCampSearch(pi);
		} else if (campAddress != null && campType != null && !campAddress.equals("") && !campType.equals("")) {
			campList = campSearchService.selectCampSearch(pi, campSearchDto);
		} else if (campAddress != null && !campAddress.equals("")) {
			campList = campSearchService.selectCampSearch(pi, campAddress, "C_ADDR");
		} else if (campType != null && !campType.equals("")) {
			campList = campSearchService.selectCampSearch(pi, campType, "C_TYPE");
		} else if (!tagItem[0].equals("none") && !campAmenity[0].equals("none")) {

			campList = new ArrayList<>();

			for (String tag : tagItem) {
				List<CampSearchDto> newCampList = campSearchService.selectCampSearch(pi, tag, "CT_TAG");
				campList.addAll(newCampList);
			}
			for (String amenity : campAmenity) {
				List<CampSearchDto> newCampList = campSearchService.selectCampSearch(pi, amenity, "CA_AMENITY");
				campList.addAll(newCampList);
			}
			
			// Set을 사용하여 중복 제거
		    Set<CampSearchDto> campSet = new HashSet<CampSearchDto>(campList);
		    for(CampSearchDto item : campSet) {
		    	    System.out.println(item);

		    }
			campList.clear();
			campList.addAll(campSet);		

		} else if (!tagItem[0].equals("none")) {
			 campList = new ArrayList<>();

				for (String tag : tagItem) {
					List<CampSearchDto> newCampList = campSearchService.selectCampSearch(pi, tag, "CT_TAG");
					campList.addAll(newCampList);
				}
				
				// Set을 사용하여 중복 제거
			    Set<CampSearchDto> campSet = new HashSet<CampSearchDto>(campList);
			    for(CampSearchDto item : campSet) {
			    	    System.out.println(item);

			    }
				campList.clear();
				campList.addAll(campSet);		
		} else if (!campAmenity[0].equals("none")) {
			 campList = new ArrayList<>();

				for (String amenity : campAmenity) {
					System.out.println(amenity);
					List<CampSearchDto> newCampList = campSearchService.selectCampSearch(pi, amenity, "CA_AMENITY");
					campList.addAll(newCampList);
				}
				
				// Set을 사용하여 중복 제거
			    Set<CampSearchDto> campSet = new HashSet<CampSearchDto>(campList);
			    for(CampSearchDto item : campSet) {
			    	    System.out.println(item);

			    }
				campList.clear();
				campList.addAll(campSet);
		} else if (campKeyword != null && !campKeyword.equals("")) {
			campList = campSearchService.selectCampSearch(pi, campKeyword, "C_KEYWORD");


		} else {
			
			campList = campSearchService.selectCampSearch(pi, category);

		}

		for (CampSearchDto csd : campList) {
			

			int likeCount = campSearchService.selectLikeCount(csd.getCampNum());
			System.out.println("ijijujji"+csd.getCampPhotoURL());
			csd.setLikeCamp(likeCount);
			
			System.out.println(csd.getCampNum());
			List<CampSearchDto> campPhoto = campSearchService.selectCampPhoto(csd.getCampNum());
			CampSearchDto firstCampPhoto = new CampSearchDto();
			if(!campPhoto.isEmpty()) {
				firstCampPhoto = campPhoto.get(0);
				csd.setCampPhotoName(firstCampPhoto.getCampPhotoName());
				csd.setCampPhotoURL(firstCampPhoto.getCampPhotoURL());
			}
		}
		
		
		// 받아온값 넘겨주기
		model.addAttribute("campAmenity", campAmenity);
		model.addAttribute("searchTxt", searchTxt);
		model.addAttribute("campAddress",campAddress);
		model.addAttribute("campType",campType);
		model.addAttribute("category", category);
		model.addAttribute("campList", campList);
		model.addAttribute("pi", pi);
		
		model.addAttribute("msg", (String) session.getAttribute("msg"));
		model.addAttribute("status", (String) session.getAttribute("status"));

		session.removeAttribute("msg");
		session.removeAttribute("status");
		
		return "user/campSearch";
		

	}
}