package kr.co.campfire.business.registration.controller;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import kr.co.campfire.admin.registration.dto.AdminCampRegistrationAmenityDto;
import kr.co.campfire.admin.registration.dto.AdminCampRegistrationDto;
import kr.co.campfire.admin.registration.dto.AdminCampRegistrationPhotoDto;
import kr.co.campfire.admin.registration.dto.AdminCampRegistrationTagDto;
import kr.co.campfire.business.registration.dto.BusinessRegistrationDto;
import kr.co.campfire.business.registration.dto.CampRegistrationAmenityDto;
import kr.co.campfire.business.registration.dto.CampRegistrationDto;
import kr.co.campfire.business.registration.dto.CampRegistrationPhotoDto;
import kr.co.campfire.business.registration.dto.CampRegistrationTagDto;
import kr.co.campfire.business.registration.service.BusinessRegistrationServiceImpl;
import kr.co.campfire.common.controller.DataValidationController;
import kr.co.campfire.common.controller.LoginCheckController;
import kr.co.campfire.common.controller.Pagination;
import kr.co.campfire.common.controller.SessionManageController;
import kr.co.campfire.common.dto.PageInfo;

@Controller
@RequestMapping("/business")
public class BusinessRegistrationController {
	@Autowired
	private BusinessRegistrationServiceImpl businessRegistrationService;

	@Autowired
	private SessionManageController sessionManage;

	@Autowired
	private DataValidationController dataValidation;

	@Autowired
	private LoginCheckController loginCheck;

	@GetMapping("/showMyCampList.do")
	public String showMyCampList(@RequestParam(value = "cpage", defaultValue = "1") int currentPage,
			HttpSession session, Model model) {
		if (!loginCheck.loginCheck(session)) {
			sessionManage.setSessionMessage("로그인 후 이용할 수 있습니다.", "error", session);

			return "redirect:/"; // 캠핑장 서치
		} else if (!((String) session.getAttribute("memberDivision")).equals("business")) {
			sessionManage.setSessionMessage("사업자 로그인 후 이용할 수 있습니다.", "error", session);

			return "redirect:/campSearch/camping.do"; // 캠핑장 서치
		} else {
			int memberNum = (int) session.getAttribute("memberNum");
			if (businessRegistrationService.checkBusinessRegistration(memberNum) > 0) {

				BusinessRegistrationDto brd = businessRegistrationService.selectBusinessRegistration(memberNum);

				if (brd.getBrArSttCd().equals("Y")) {
					int listCount = businessRegistrationService.selectMyCampRegistrationListCount(memberNum);
					// 보여질 페이지 수
					int pageLimit = 10;

					// 한 페이지에 보여질 게시글 수
					int boardLimit = 5;

					// 글 번호 뒤에서부터 출력해주는 변수
					int row = listCount - (currentPage - 1) * boardLimit;

					// 페이징 로직 처리
					PageInfo pi = Pagination.getPageInfo(listCount, currentPage, pageLimit, boardLimit);

					List<CampRegistrationDto> campList = null;

					campList = businessRegistrationService.selectMyCampRegistrationList(pi, memberNum);

					for (CampRegistrationDto crd : campList) {
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

						Date createDate = crd.getCampCreateDate();
						String formattedinquiryDate = sdf.format(createDate);
						crd.setNewCampCreateDate(formattedinquiryDate);

						int campNum = crd.getCampNum();
						List<CampRegistrationPhotoDto> photos = businessRegistrationService
								.selectCampRegistrationPhotoList(campNum); // 새로운 CampPhotoItemDto 리스트 생성

						if (!photos.isEmpty()) {
							crd.setCampPhotoList(photos);
						}
					}
					model.addAttribute("campList", campList); // 객체 바인딩
					model.addAttribute("pi", pi);
					model.addAttribute("row", row);

					model.addAttribute("msg", (String) session.getAttribute("msg"));
					model.addAttribute("status", (String) session.getAttribute("status"));

					session.removeAttribute("msg");
					session.removeAttribute("status");

					return "business/campRegistrationList";

				} else if (!brd.getBrArSttCd().equals("Y")) {
					sessionManage.setSessionMessage("사업자 승인이 되지 않은 사용자 입니다.", "error", session);
					return "redirect:/business/showBusinessRegistration.do";
				} else {
					sessionManage.setSessionMessage("잘못된 접근 입니다.", "error", session);
					return "redirect:/campSearch/camping.do";
				}
			} else {
				sessionManage.setSessionMessage("사업자 승인이 되지 않은 사용자 입니다.", "error", session);
				return "redirect:/business/showBusinessRegistration.do";
			}
		}
	}

	@GetMapping("/detailCampRegistration.do")
	public String detailCampRegistration(@RequestParam(value = "campNum", defaultValue = "-1") int campNum,
			HttpSession session, Model model) {
		if (!loginCheck.loginCheck(session)) {
			sessionManage.setSessionMessage("로그인 후 이용할 수 있습니다.", "error", session);

			return "redirect:/"; 
		} else if (!((String) session.getAttribute("memberDivision")).equals("business")) {
			sessionManage.setSessionMessage("사업자 로그인 후 이용할 수 있습니다.", "error", session);

			return "redirect:/campSearch/camping.do"; // 캠핑장 서치
		} else {
			int loginMemberNum = (int) session.getAttribute("memberNum");
			if (businessRegistrationService.checkBusinessRegistration(loginMemberNum) > 0) {

				BusinessRegistrationDto brd = businessRegistrationService.selectBusinessRegistration(loginMemberNum);

				if (brd.getBrArSttCd().equals("Y")) {
					if (campNum == -1) {
						sessionManage.setSessionMessage("오류 발생 다시한번 확인해 주세요.", "error", session);
					} else {
						CampRegistrationDto campInfo = businessRegistrationService.selectCampRegistration(campNum);

						// 태그 정보 불러오기
						List<CampRegistrationTagDto> campTagList = businessRegistrationService
								.selectCampRegistrationTagList(campNum);

						// 편의 시설 불러오기
						List<CampRegistrationAmenityDto> campAmenityList = businessRegistrationService
								.selectCampRegistrationAmenityList(campNum);

						// 사진 불러오기
						List<CampRegistrationPhotoDto> campPhotoList = businessRegistrationService
								.selectCampRegistrationPhotoList(campNum);

						System.out.println(campAmenityList.toString());

						model.addAttribute("campInfo", campInfo); // 객체 바인딩
						model.addAttribute("campTagList", campTagList); // 객체 바인딩
						model.addAttribute("campAmenityList", campAmenityList); // 객체 바인딩
						model.addAttribute("campPhotoList", campPhotoList); // 객체 바인딩
					}

					model.addAttribute("msg", (String) session.getAttribute("msg"));
					model.addAttribute("status", (String) session.getAttribute("status"));

					session.removeAttribute("msg");
					session.removeAttribute("status");

					return "business/detailCampRegistration";

				} else if (!brd.getBrArSttCd().equals("Y")) {
					sessionManage.setSessionMessage("사업자 승인이 되지 않은 사용자 입니다.", "error", session);
					return "redirect:/business/showBusinessRegistration.do";
				} else {
					sessionManage.setSessionMessage("잘못된 접근 입니다.", "error", session);
					return "redirect:/campSearch/camping.do";
				}
			} else {
				sessionManage.setSessionMessage("사업자 승인이 되지 않은 사용자 입니다.", "error", session);
				return "redirect:/business/showBusinessRegistration.do";
			}
		}
	}

	@GetMapping("/deleteCampRegistration.do")
	public String deleteCampRegistration(@RequestParam(value = "campNum", defaultValue = "-1") int campNum,
			@RequestParam(value = "memberNum", defaultValue = "-1") int memberNum, HttpSession session, Model model) {
		if (!loginCheck.loginCheck(session)) {
			sessionManage.setSessionMessage("로그인 후 이용할 수 있습니다.", "error", session);

			return "redirect:/";
		} else if (!((String) session.getAttribute("memberDivision")).equals("business")) {
			sessionManage.setSessionMessage("사업자 로그인 후 이용할 수 있습니다.", "error", session);

			return "redirect:/campSearch/camping.do"; // 캠핑장 서치
		} else {
			int loginMemberNum = (int) session.getAttribute("memberNum");
			if (businessRegistrationService.checkBusinessRegistration(memberNum) > 0) {

				BusinessRegistrationDto brd = businessRegistrationService.selectBusinessRegistration(loginMemberNum);

				if (brd.getBrArSttCd().equals("Y")) {
					if (campNum == -1 || memberNum == -1) {
						sessionManage.setSessionMessage("오류 발생 다시한번 확인해 주세요.", "error", session);
					} else {
						if (memberNum == loginMemberNum) {

							businessRegistrationService.deleteCampRegistrationAmenityList(campNum);

							businessRegistrationService.deleteCampRegistrationTagList(campNum);

							businessRegistrationService.deleteCampRegistrationPhotoList(campNum);

							int result = businessRegistrationService.deleteCampRegistration(campNum);
							if (result > 0) {
								sessionManage.setSessionMessage("삭제 완료 되었습니다.", "success", session);
							} else {
								sessionManage.setSessionMessage("삭제처리중 오류 발생", "error", session);
							}

						} else {
							sessionManage.setSessionMessage("삭제 권한이 없습니다.", "error", session);
						}
					}
					return "redirect:/business/showMyCampList.do";

				} else if (!brd.getBrArSttCd().equals("Y")) {
					sessionManage.setSessionMessage("사업자 승인이 되지 않은 사용자 입니다.", "error", session);
					return "redirect:/business/showBusinessRegistration.do";
				} else {
					sessionManage.setSessionMessage("잘못된 접근 입니다.", "error", session);
					return "redirect:/campSearch/camping.do";
				}
			} else {
				sessionManage.setSessionMessage("사업자 승인이 되지 않은 사용자 입니다.", "error", session);
				return "redirect:/business/showBusinessRegistration.do";
			}
		}
	}

	// 캠핑장 등록 컨트롤러
	@GetMapping("/showCampRegistration.do")
	public String showCampRegistration(HttpSession session, Model model) {
		if (!loginCheck.loginCheck(session)) {
			sessionManage.setSessionMessage("로그인 후 이용할 수 있습니다.", "error", session);

			return "redirect:/";
		} else if (!((String) session.getAttribute("memberDivision")).equals("business")) {
			sessionManage.setSessionMessage("사업자 로그인 후 이용할 수 있습니다.", "error", session);

			return "redirect:/campSearch/camping.do"; // 캠핑장 서치
		} else {
			int memberNum = (int) session.getAttribute("memberNum");
			if (businessRegistrationService.checkBusinessRegistration(memberNum) > 0) {

				BusinessRegistrationDto brd = businessRegistrationService.selectBusinessRegistration(memberNum);

				if (brd.getBrArSttCd().equals("Y")) {
					model.addAttribute("msg", (String) session.getAttribute("msg"));
					model.addAttribute("status", (String) session.getAttribute("status"));

					session.removeAttribute("msg");
					session.removeAttribute("status");

					return "business/campRegistration";

				} else if (!brd.getBrArSttCd().equals("Y")) {
					sessionManage.setSessionMessage("사업자 승인이 되지 않은 사용자 입니다.", "error", session);
					return "redirect:/business/showBusinessRegistration.do";
				} else {
					sessionManage.setSessionMessage("잘못된 접근 입니다.", "error", session);
					return "redirect:/campSearch/camping.do";
				}
			} else {
				sessionManage.setSessionMessage("사업자 승인이 되지 않은 사용자 입니다.", "error", session);
				return "redirect:/business/showBusinessRegistration.do";
			}
		}
	}

	@RequestMapping("/insertCampRegistration.do")
	public String insertRegistration(@ModelAttribute CampRegistrationDto crd, HttpSession session, Model model) {
		System.out.println(crd.toString());
		if (loginCheck.loginCheck(session)) {
			if (((String) session.getAttribute("memberDivision")).equals("business")) {

				if (dataValidation.nullCheck(crd.getCampName()) && dataValidation.nullCheck(crd.getCampAddr())
						&& dataValidation.nullCheck(crd.getCampPhoneNum())
						&& dataValidation.nullCheck(crd.getCampType())) {
					crd.setMemberNum((int) session.getAttribute("memberNum"));
					int resultCampInfo = businessRegistrationService.insertCampInfo(crd);

					if (resultCampInfo > 0) {
						// 편의시설 및 tag, 사진 정보넣을 캠핑장 번호 반환
						int campNum = businessRegistrationService.selectCampNum(crd);

						CampRegistrationAmenityDto cad = new CampRegistrationAmenityDto();
						CampRegistrationTagDto ctd = new CampRegistrationTagDto();
						CampRegistrationPhotoDto cpd = new CampRegistrationPhotoDto();

						// 편의시설 db저장
						if (crd.getCampAmenity() != null) {
							for (String campAmenityItem : crd.getCampAmenity()) {
								cad.setCampNum(campNum);
								cad.setCampAmenityItem(campAmenityItem);
								cad.setCampAmenityPhoto("/resources/images/" + campAmenityItem + ".png");
								businessRegistrationService.insertCampAmenity(cad);
							}
						}
						// Tag db저장
						if (crd.getCampTag() != null) {
							for (String campTagItem : crd.getCampTag()) {
								ctd.setCampNum(campNum);
								ctd.setCampTagItem(campTagItem);

								businessRegistrationService.insertCampTag(ctd);
							}
						}

						// 사진 저장 및 db 저장
						if (crd.getFiles() != null) {
							String uploadFolder = "C:\\Users\\Administrator\\git\\campfire\\Campfire\\src\\main\\webapp\\resources\\uploads\\";
							int i = 0;

							for (MultipartFile file : crd.getFiles()) {
								// 사진 로컬저장
								// 원본 파일이름
								String fileRealName = file.getOriginalFilename();
								// 확장자
								String extension = fileRealName.substring(fileRealName.lastIndexOf("."));

								// 랜덤문자열 생성
								int length = 8;
								String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
								Random random = new Random();
								String randomString = random.ints(length, 0, characters.length())
										.mapToObj(characters::charAt).map(Object::toString)
										.collect(Collectors.joining());

								// 새로운 파일이름
								int memberNum = (int) session.getAttribute("memberNum");
								String campPhotoName = memberNum + "camping" + i + 1 + "_" + randomString + extension;
								String filePathName = uploadFolder + campPhotoName;
								Path filePath = Paths.get(filePathName);
								long size = file.getSize();

								System.out.println("파일명: " + fileRealName);
								System.out.println("사이즈: " + size);
								// 파일을 저장하거나 처리하는 로직을 추가하세요
								i++;
								try {
									file.transferTo(filePath);
								} catch (Exception e) {
								}

								// 사진 정보 db저장
								cpd.setCampNum(campNum);
								cpd.setCampPhotoName(campPhotoName);
								cpd.setCampPhotoURL("\\resources\\uploads\\");

								businessRegistrationService.insertCampPhoto(cpd);
							}
						}
						model.addAttribute("msg", (String) session.getAttribute("msg"));
						model.addAttribute("status", (String) session.getAttribute("status"));

						session.removeAttribute("msg");
						session.removeAttribute("status");
						return "campSearch/camping";
					} else {
						return "common/errorPage";
					}
				} else {

					sessionManage.setSessionMessage("필수 항목을 꼭 적어주세요", "error", session);

//					return "redirect:/business/registration/insertRegistration.do";
					return "";
				}
			} else {
				sessionManage.setSessionMessage("사업자 로그인 후 이용할 수 있습니다.", "error", session);

				// return "redirect:/user/showUserInquiry.do";
				return "";
			}
		} else {
			sessionManage.setSessionMessage("로그인 후 이용할 수 있습니다.", "error", session);

			// return "redirect:/user/showUserInquiry.do";
			return "";
		}
	}

	// 사업자 등록 컨트롤러
	@RequestMapping("/showBusinessRegistration.do")
	public String showBusinessRegistration(HttpSession session, Model model) {
		if (!loginCheck.loginCheck(session)) {
			sessionManage.setSessionMessage("로그인 후 이용할 수 있습니다.", "error", session);
			return "redirect:/";
		} else if (!((String) session.getAttribute("memberDivision")).equals("business")) {
			sessionManage.setSessionMessage("사업자 로그인 후 이용할 수 있습니다.", "error", session);
			return "redirect:/campSearch/camping.do"; // 캠프서치로
		} else {
			int memberNum = (int) session.getAttribute("memberNum");
			int result = businessRegistrationService.checkBusinessRegistration(memberNum);

			// 등록된 사업자등록증이 있으면 보여주기
			if (result > 0) {
				BusinessRegistrationDto brd = businessRegistrationService.selectBusinessRegistration(memberNum);
				model.addAttribute("businessList", brd);
				
				model.addAttribute("msg", (String) session.getAttribute("msg"));
				model.addAttribute("status", (String) session.getAttribute("status"));

				session.removeAttribute("msg");
				session.removeAttribute("status");
				return "business/businessList";
			} else {
				// 없으면 등록화면으로 이동
				model.addAttribute("msg", (String) session.getAttribute("msg"));
				model.addAttribute("status", (String) session.getAttribute("status"));

				session.removeAttribute("msg");
				session.removeAttribute("status");

				return "business/businessRegistration";
			}
		}
	}

	@RequestMapping("/insertBusinessRegistration.do")
	public String insertBusinessRegistration(BusinessRegistrationDto brd, HttpSession session, Model model) {
		if (!loginCheck.loginCheck(session)) {
			sessionManage.setSessionMessage("로그인 후 이용할 수 있습니다.", "error", session);
			return "redirect:/";
		} else if (!((String) session.getAttribute("memberDivision")).equals("business")) {
			sessionManage.setSessionMessage("사업자 로그인 후 이용할 수 있습니다.", "error", session);
			return "redirect:/campSearch/camping.do"; // 캠프서치 컨트롤러
		} else {
			// 입력 정보 확인
			if (brd.getBrNum() == null || brd.getBrCompany() == null || brd.getBrSttCd() == null
					|| brd.getBrNum().isEmpty() || brd.getBrCompany().isEmpty() || brd.getBrSttCd().isEmpty()) {
				sessionManage.setSessionMessage("입력 정보를 다시 확인해 주세요.", "error", session);
			} else {
				// 사업자 등록증 번호 중복 체크
				int resultCheckBusinessNum = businessRegistrationService.checkBusinessNum(brd.getBrNum());
				if (resultCheckBusinessNum > 0) {
					sessionManage.setSessionMessage("이미 등록된 사업자 번호 입니다.", "error", session);
				} else {
					brd.setMemberNum((int) session.getAttribute("memberNum"));
					int result = businessRegistrationService.insertBusiness(brd);

					if (result > 0) {
						sessionManage.setSessionMessage("등록이 완료 되었습니다.", "success", session);
					} else {
						sessionManage.setSessionMessage("다시한번 확인해 주세요.", "error", session);
					}
				}
			}
			return "redirect:/business/showBusinessRegistration.do";
		}
	}

	@RequestMapping("/deleteBusinessRegistration.do")
	public String deleteBusinessRegistration(HttpSession session, Model model) {
		if (!loginCheck.loginCheck(session)) {
			sessionManage.setSessionMessage("로그인 후 이용할 수 있습니다.", "error", session);
			return "redirect:/";
		} else if (!((String) session.getAttribute("memberDivision")).equals("business")) {
			sessionManage.setSessionMessage("사업자 로그인 후 이용할 수 있습니다.", "error", session);
			return "redirect:/campSearch/camping.do"; // 캠프서치 컨트롤러
		} else {
			int memberNum = (int) session.getAttribute("memberNum");

			int result = businessRegistrationService.deleteBusiness(memberNum);
			if (result > 0) {
				sessionManage.setSessionMessage("삭제가 완료 되었습니다.", "success", session);
			}
			return "redirect:/business/showBusinessRegistration.do";
		}
	}

}
