package kr.co.campfire.common.controller;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;

import kr.co.campfire.user.trading.dto.File;
import kr.co.campfire.user.trading.service.TradingServiceImpl;

@Controller
public class UploadFileController {
	String FILE_UPLOAD_PATH = "C:\\Users\\Administrator\\git\\campfire\\Campfire\\src\\main\\webapp\\resources\\upload\\";

	@Autowired
	private TradingServiceImpl tradingService;

	public List<File> setUploadInfo(int tradingIdx, List<MultipartFile> filesArr) {
		
		List<File> listOfFile = new ArrayList<File>();
		for(MultipartFile mFile : filesArr) {
			File file = new File();
			
			// 원본 파일명
			String originalName = mFile.getOriginalFilename();

			// 확장자 구하기
			String extension = originalName.substring(originalName.lastIndexOf("."));

			// 현재 시각
			LocalDateTime now = LocalDateTime.now();

			// 년 월 일 시 분 초
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyMMddHHmmss");
			String output = now.format(formatter);

			// 랜덤 문자열 생성
			int length = 8;
			String characters = "ABCDEFHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz"; // *는 윈도우에서 파일이름으로 쓸 수 없다
			Random random = new Random();
			String randomString = random.ints(length, 0, characters.length()).mapToObj(characters::charAt)
					.map(Object::toString).collect(Collectors.joining());

			// 날짜 + "_" + 랜덤문자열 + 확장자
			String fileName = (output + "_" + randomString + extension);

			// 경로 + 변경된 파일명
			String fullFilePath = FILE_UPLOAD_PATH + fileName;

			// 객체 만들기
			file.setUploadPath(FILE_UPLOAD_PATH);
			file.setUploadName(fileName);
			file.setUploadOriginName(originalName);
			file.setTradingIdx(tradingIdx);
			 
			// 리스트에 넣기
			listOfFile.add(file);
		}
		
		return listOfFile;
	}

	public void uploadFile(List<File> listOfFile, List<MultipartFile> filesArr) throws IllegalStateException, IOException {

		
		
		// 서버에 파일 저장 (nio.file.Path 임포트)
		for(int i=0; i<listOfFile.size(); i++) {
		
			Path filePath = Paths.get(listOfFile.get(i).getUploadPath() + listOfFile.get(i).getUploadName());	
			filesArr.get(i).transferTo(filePath);
			
			
		}
	}

//	public void deleteFile(String fileName) {
//
//		/* log */ System.out.printf("%s: %s\n", "fileName", fileName);
//		File file = new File(fileName);
//		if (file.exists()) {
//			file.delete();
//		}
//	}
}