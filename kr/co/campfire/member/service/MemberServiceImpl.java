package kr.co.campfire.member.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletResponse;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import kr.co.campfire.member.dao.MemberDao;
import kr.co.campfire.member.dto.MemberDto;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	private SqlSessionTemplate sqlSession;

	@Autowired
	private MemberDao memberDao;

	@Override
	public MemberDto loginMember(MemberDto m) {
		return memberDao.loginMember(sqlSession, m);
	}

//	@Override
//	public int checkEmail(String email) {
//		return memberDao.checkEmail(sqlSession, email);
//	}
//	@Override
//	public int singupMember(MemberDto m) {
//		return memberDao.singupMember(sqlSession, m);
//	}
	// 카카오 로그인
	@Override
	public String getAccessTokenKakao(String authorize_code) throws Exception {
		String access_Token = "";
		String refresh_Token = "";
		String reqURL = "https://kauth.kakao.com/oauth/token";

		try {
			URL url = new URL(reqURL);

			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			// POST 요청을 위해 기본값이 false인 setDoOutput을 true로

			conn.setRequestMethod("POST");
			conn.setDoOutput(true);
			// POST 요청에 필요로 요구하는 파라미터 스트림을 통해 전송

			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
			StringBuilder sb = new StringBuilder();
			sb.append("grant_type=authorization_code");

			sb.append("&client_id=f56f8461b6c1bd3d445e592479cd7a79"); // REST_API키 본인이 발급받은 key 넣어주기
			sb.append("&redirect_uri=http://campfire.com/member/kakaoLogin"); // REDIRECT_URI 본인이 설정한 주소 넣어주기

			sb.append("&code=" + authorize_code);
			bw.write(sb.toString());
			bw.flush();

			// 결과 코드가 200이라면 성공
			int responseCode = conn.getResponseCode();
			System.out.println("responseCode : " + responseCode);

			// 요청을 통해 얻은 JSON타입의 Response 메세지 읽어오기
			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line = "";
			String result = "";

			while ((line = br.readLine()) != null) {
				result += line;
			}
			System.out.println("response body : " + result);

			// jackson objectmapper 객체 생성
			ObjectMapper objectMapper = new ObjectMapper();
			// JSON String -> Map
			Map<String, Object> jsonMap = objectMapper.readValue(result, new TypeReference<Map<String, Object>>() {
			});

			access_Token = jsonMap.get("access_token").toString();
			refresh_Token = jsonMap.get("refresh_token").toString();

			System.out.println("access_token : " + access_Token);
			System.out.println("refresh_token : " + refresh_Token);

			br.close();
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return access_Token;
	}

	@Override
	public HashMap<String, Object> getUserInfoKakao(String access_Token) throws Throwable {
		// 요청하는 클라이언트마다 가진 정보가 다를 수 있기에 HashMap타입으로 선언
		HashMap<String, Object> userInfo = new HashMap<String, Object>();
		String reqURL = "https://kapi.kakao.com/v2/user/me";

		try {
			URL url = new URL(reqURL);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");

			// 요청에 필요한 Header에 포함될 내용
			conn.setRequestProperty("Authorization", "Bearer " + access_Token);


			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

			String line = "";
			String result = "";

			while ((line = br.readLine()) != null) {
				result += line;
			}

			try {
				// jackson objectmapper 객체 생성
				ObjectMapper objectMapper = new ObjectMapper();
				// JSON String -> Map
				Map<String, Object> jsonMap = objectMapper.readValue(result, new TypeReference<Map<String, Object>>() {
				});

				System.out.println(jsonMap.get("properties"));

				Map<String, Object> properties = (Map<String, Object>) jsonMap.get("properties");
				Map<String, Object> kakao_account = (Map<String, Object>) jsonMap.get("kakao_account");

				// System.out.println(properties.get("nickname"));
				// System.out.println(kakao_account.get("email"));
				String pw = jsonMap.get("id").toString();
				String nickname = properties.get("nickname").toString();
				String email = kakao_account.get("email").toString();
				String gender = kakao_account.get("gender").toString();


				int atIndex = email.indexOf('@'); // '@'의 인덱스를 찾습니다

				if (atIndex != -1) {
				    String id = email.substring(0, atIndex); // '@' 앞의 부분을 추출합니다
				    userInfo.put("id", id);	    
				    // 이제 'id' 변수를 사용할 수 있습니다
				    System.out.println("추출된 ID: " + id);
				} else {
				    System.out.println("유효한 이메일 형식이 아닙니다.");
				    userInfo.put("id", pw);	 
				}
				
				userInfo.put("pw", pw);
				userInfo.put("nickname", nickname);
				userInfo.put("email", email);
				userInfo.put("gender", gender);

			} catch (Exception e) {
				e.printStackTrace();
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		return userInfo;
	}

	@Override
	public int checkId(String id) {
		return memberDao.checkId(sqlSession, id);
	}

	@Override
	public int kakaoSingup(MemberDto md) {
		return memberDao.kakaoSingup(sqlSession, md);
	}
	
	@Override
	public int naverSingup(MemberDto md) {
		return memberDao.naverSingup(sqlSession, md);
	}
	
	@Override
	public int googleSingup(MemberDto md) {
		return memberDao.googleSingup(sqlSession, md);
	}
	
	//네이버 로그인
	@Override
	public String getAccessTokenNaver(String authorize_code) throws Exception {
		String access_Token = "";
		String refresh_Token = "";
		String reqURL = "https://nid.naver.com/oauth2.0/token";

		try {
			URL url = new URL(reqURL);

			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			// POST 요청을 위해 기본값이 false인 setDoOutput을 true로

			conn.setRequestMethod("POST");
			conn.setDoOutput(true);
			// POST 요청에 필요로 요구하는 파라미터 스트림을 통해 전송

			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
			StringBuilder sb = new StringBuilder();
			sb.append("grant_type=authorization_code");

			sb.append("&client_id=KqnzzV13lgmW6NT6QATF"); // REST_API키 본인이 발급받은 key 넣어주기
			sb.append("&client_secret=wiPeI7TCEP"); // REDIRECT_URI 본인이 설정한 주소 넣어주기

			sb.append("&code=" + authorize_code);
			bw.write(sb.toString());
			bw.flush();

			// 결과 코드가 200이라면 성공
			int responseCode = conn.getResponseCode();
			System.out.println("responseCode : " + responseCode);

			// 요청을 통해 얻은 JSON타입의 Response 메세지 읽어오기
			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line = "";
			String result = "";

			while ((line = br.readLine()) != null) {
				result += line;
			}
			System.out.println("response body : " + result);

			// jackson objectmapper 객체 생성
			ObjectMapper objectMapper = new ObjectMapper();
			// JSON String -> Map
			Map<String, Object> jsonMap = objectMapper.readValue(result, new TypeReference<Map<String, Object>>() {
			});

			access_Token = jsonMap.get("access_token").toString();
			refresh_Token = jsonMap.get("refresh_token").toString();

			System.out.println("access_token : " + access_Token);
			System.out.println("refresh_token : " + refresh_Token);

			br.close();
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return access_Token;
	}
	
	@Override
	public HashMap<String, Object> getUserInfoNaver(String access_Token) throws Throwable {
		// 요청하는 클라이언트마다 가진 정보가 다를 수 있기에 HashMap타입으로 선언
		HashMap<String, Object> userInfo = new HashMap<String, Object>();
		String reqURL = "https://openapi.naver.com/v1/nid/me";
		
		String header = "Bearer " + access_Token;

		try {
			URL url = new URL(reqURL);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");

			// 요청에 필요한 Header에 포함될 내용
			conn.setRequestProperty("Authorization", header);

			 int responseCode = conn.getResponseCode();
             BufferedReader br;
             if(responseCode==200) { // 정상 호출
                 br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
             } else {  // 에러 발생
                 br = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
             }
             String inputLine;
             StringBuffer response = new StringBuffer();
             while ((inputLine = br.readLine()) != null) {
                 response.append(inputLine);
             }
             br.close();
             ObjectMapper objectMapper = new ObjectMapper(); // Jackson 라이브러리 사용
             try {
            	    Map<String, Object> naverInfo = objectMapper.readValue(response.toString(), new TypeReference<Map<String, Object>>() {});
            	    
            	    // 'response' 맵 안의 데이터 가져오기
            	    Map<String, Object> responseData = (Map<String, Object>) naverInfo.get("response");
            	    
            	    // response 데이터 확인
            	    System.out.println("response 데이터: " + responseData);
            	    
            	    // response 안의 특정 데이터 가져오기
            	    String id = "";
            	    String pw = (String) responseData.get("id");
            	    String gender = (String) responseData.get("gender");
            	    String email = (String) responseData.get("email");
            	    String name = (String) responseData.get("name");
            	    String birthday = (String) responseData.get("birthday");
            	    String birthyear = (String) responseData.get("birthyear");
            	    

            	    
            	    int atIndex = email.indexOf('@'); // '@'의 인덱스를 찾습니다

    				if (atIndex != -1) {
    				    id = email.substring(0, atIndex); // '@' 앞의 부분을 추출합니다
    				    userInfo.put("id", id);	    
    				    // 이제 'id' 변수를 사용할 수 있습니다
    				    System.out.println("추출된 ID: " + id);
    				} else {
    				    System.out.println("유효한 이메일 형식이 아닙니다.");
    				    userInfo.put("id", pw);	 
    				}
    				
            	    System.out.println("ID: " + id);
            	    System.out.println("Gender: " + gender);
            	    System.out.println("Email: " + email);
            	    System.out.println("Name: " + name);
            	    System.out.println("Birthday: " + birthday);
            	    System.out.println("Birthyear: " + birthyear);
            	    
            	    
            	    userInfo.put("pw", pw);
    				userInfo.put("nickname", name);
    				userInfo.put("email", email);
    				userInfo.put("gender", gender);
    				userInfo.put("birthyear", birthyear);
    				userInfo.put("birthday", birthday);
            	} catch (IOException e) {
            	    System.out.println(e);
            	}
         } catch (Exception e) {
             System.out.println(e);
         }
		return userInfo;
	}
	
	//goole
		@Override
		public String getAccessTokenGoogle(String authorize_code) throws Exception {
			String access_Token = "";
			String refresh_Token = "";
			String reqURL = "https://oauth2.googleapis.com/token";

			try {
				URL url = new URL(reqURL);

				HttpURLConnection conn = (HttpURLConnection) url.openConnection();
				// POST 요청을 위해 기본값이 false인 setDoOutput을 true로

				conn.setRequestMethod("POST");
				conn.setDoOutput(true);
				// POST 요청에 필요로 요구하는 파라미터 스트림을 통해 전송

				BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
				StringBuilder sb = new StringBuilder();
				sb.append("grant_type=authorization_code");

				sb.append("&client_id=40148344416-p4n6jrj21bhl3uk774ji4kr9uje2898t.apps.googleusercontent.com"); // REST_API키 본인이 발급받은 key 넣어주기
				sb.append("&redirect_uri=http://localhost/member/googleLogin"); // REDIRECT_URI 본인이 설정한 주소 넣어주기
				sb.append("&client_secret=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");
				sb.append("&grant_type=authorization_code");
				
				sb.append("&code=" + authorize_code);
				bw.write(sb.toString());
				bw.flush();

				// 결과 코드가 200이라면 성공
				int responseCode = conn.getResponseCode();
				System.out.println("responseCode : " + responseCode);

				// 요청을 통해 얻은 JSON타입의 Response 메세지 읽어오기
				BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
				String line = "";
				String result = "";

				while ((line = br.readLine()) != null) {
					result += line;
				}
				System.out.println("response body : " + result);

				// jackson objectmapper 객체 생성
				ObjectMapper objectMapper = new ObjectMapper();
				// JSON String -> Map
				Map<String, Object> jsonMap = objectMapper.readValue(result, new TypeReference<Map<String, Object>>() {
				});

				access_Token = jsonMap.get("access_token").toString();

				System.out.println("access_token : " + access_Token);

				br.close();
				bw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return access_Token;
		}
		
		@Override
		public HashMap<String, Object> getUserInfoGoogle(String access_Token) throws Throwable {
			// 요청하는 클라이언트마다 가진 정보가 다를 수 있기에 HashMap타입으로 선언
			HashMap<String, Object> userInfo = new HashMap<String, Object>();
			String reqURL = "https://www.googleapis.com/userinfo/v2/me";
			
			String header = "Bearer " + access_Token;

			try {
				URL url = new URL(reqURL);
				HttpURLConnection conn = (HttpURLConnection) url.openConnection();
				conn.setRequestMethod("GET");

				// 요청에 필요한 Header에 포함될 내용
				conn.setRequestProperty("Authorization", header);

				 int responseCode = conn.getResponseCode();
	             BufferedReader br;
	             if(responseCode==200) { // 정상 호출
	                 br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	             } else {  // 에러 발생
	                 br = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
	             }
	             String inputLine;
	             StringBuffer response = new StringBuffer();
	             while ((inputLine = br.readLine()) != null) {
	                 response.append(inputLine);
	             }
	             br.close();
	             ObjectMapper objectMapper = new ObjectMapper(); // Jackson 라이브러리 사용
	             try {
	            	    Map<String, Object> googleInfo = objectMapper.readValue(response.toString(), new TypeReference<Map<String, Object>>() {});
	            	    System.out.println("response 데이터: " + googleInfo);
	            	    
	            	    
	            	    // response 안의 특정 데이터 가져오기
	            	    String id = "";
	            	    String pw = (String) googleInfo.get("id");
	            	    String email = (String) googleInfo.get("email");
	            	    String name = (String) googleInfo.get("name");
	            	    

	            	    
	            	    int atIndex = email.indexOf('@'); // '@'의 인덱스를 찾습니다

	    				if (atIndex != -1) {
	    				    id = email.substring(0, atIndex); // '@' 앞의 부분을 추출합니다
	    				    userInfo.put("id", id);	    
	    				    // 이제 'id' 변수를 사용할 수 있습니다
	    				    System.out.println("추출된 ID: " + id);
	    				} else {
	    				    System.out.println("유효한 이메일 형식이 아닙니다.");
	    				    userInfo.put("id", pw);	 
	    				}
	    				
	            	    System.out.println("ID: " + id);
	            	    System.out.println("Email: " + email);
	            	    System.out.println("Name: " + name);

	            	    
	            	    userInfo.put("pw", pw);
	    				userInfo.put("nickname", name);
	    				userInfo.put("email", email);


	            	} catch (IOException e) {
	            	    System.out.println(e);
	            	}
	         } catch (Exception e) {
	             System.out.println(e);
	         }
			return userInfo;
		}
		
		
		//은연님 로그인
		@Override
		public int signupMember(MemberDto memberjojn) {
			
			return memberDao.signupMember(sqlSession,memberjojn);
		}

		//회원정보보기
			@Override
			public MemberDto readMember(int mnum) {
			
				MemberDto au = null;
				
				try {
					au = memberDao.readMember(sqlSession, mnum);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				return au;
			}
			
	//비밀번호 변경
			@Override
			public int pwup(int mnum, String memberPw) {
				return memberDao.pwup(sqlSession, mnum, memberPw);
			}
			
			
			//회원수정 
			@Override
			public int mypageup(int mnum, String memberName, String memberPostalcode, String memberAdd1, String memberAdd2,
					String memberGender, String memberDateBirth) {
				return memberDao.mypageup(sqlSession, mnum, memberName,memberPostalcode,memberAdd1,memberAdd2,memberGender,memberDateBirth);
			}
			
			
			//비밀번호 변경정보 보기
				public MemberDto pwupread(int mnum) {
	         	MemberDto au = null;
				
				try {
					au = memberDao.pwupread(sqlSession, mnum);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				return au;
			
			}
			//회원수정정보 보기
				public MemberDto mypageupread(int mnum) {
					MemberDto au = null;
					
					try {
						au = memberDao.mypageupread(sqlSession, mnum);
					} catch (Exception e) {
						e.printStackTrace();
					}
					
					return au;
				}
				
		//아이디 중복체크	
				@Override
				public MemberDto idCheck(String memberUserId) {
					return memberDao.idCheck(sqlSession,memberUserId);
				}
				
				
		//아이디 찾기	
				@Override
				public String find_id(HttpServletResponse response,String memberName, String memberDateBirth) {
					response.setContentType("text/html;charset=utf-8");
					PrintWriter out;
					try {
						out = response.getWriter();
						String id = memberDao.find_id(sqlSession,memberName,memberDateBirth);
						
						if (id == null) {
							out.println("<script>");
							out.println("alert('가입된 아이디가 없습니다.');");
							out.println("history.go(-1);");
							out.println("</script>");
							out.close();
							return null;
						} else {
							return id;
						}
					} catch (IOException e) {
					
						e.printStackTrace();
					}
					return null;
				}
				
				//비밀번호 찾기	
				@Override
				public String find_pw(HttpServletResponse response, String memberUserId,String memberName) {
					response.setContentType("text/html;charset=utf-8");
					PrintWriter out;
					try {
						out = response.getWriter();
						String pw = memberDao.find_pw(sqlSession,memberUserId,memberName);
						
						if (pw == null) {
							out.println("<script>");
							out.println("alert('가입된 비밀번호가 없습니다.');");
							out.println("history.go(-1);");
							out.println("</script>");
							out.close();
							return null;
						} else {
							return pw;
						}
					} catch (IOException e) {
					
						e.printStackTrace();
					}
					return null;
				}
				
				// 회원 탈퇴
				@Override
				public int memberDelete(MemberDto vo) {
					return memberDao.memberDelete(sqlSession, vo);
					
				}
				
				// 회원 탈퇴
				@Override
				public String selectMemberPw(int memberNum) {
					return memberDao.selectMemberPw(sqlSession, memberNum);
					
				}

}