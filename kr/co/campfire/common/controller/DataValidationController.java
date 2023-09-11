package kr.co.campfire.common.controller;

import java.util.Iterator;

import org.springframework.stereotype.Controller;

@Controller
public class DataValidationController {
   
   public Boolean nullCheck(String data) {
      if(data.isEmpty()) {
         return false;
      } else {
         return true;
      }
   }
   
   public Boolean titleLanguageCheck(String data, int titleLenght) {
      int byteLength = 0;
      
      for ( char c : data.toCharArray()) {
         //영어일때
         //문자형에서 문자열로 바꿈
         if(Character.toString(c).matches("[a-zA-Z]")){
            byteLength   +=  1; //영어 1바이트
         }
         else if (Character.toString(c).matches("[ㄱ-ㅎㅏ-ㅣ가-힣]")) {
            byteLength += 3; //한글 3바이트
         }
      }
      
      //총 문자열의 크기(바이트)가 FREE가 테이블의 컬럼 크기(VARCHAR2()) 보다 큰 경우
      if(byteLength > titleLenght ) {
         return false;
      } else {
         return true;
      }
   }
   
   
   public Boolean contentLanguageCheck(String data, int contextLenght) {
      int byteLength = 0;
      
      for ( char c : data.toCharArray()) {
         //영어일때
         if(Character.toString(c).matches("[a-zA-Z]")){
            byteLength   +=  1; //영어 1바이트
         }
         else if (Character.toString(c).matches("[ㄱ-ㅎㅏ-ㅣ가-힣]")) {
            byteLength += 3; //한글 3바이트
         }
      }
      
      //총 문자열의 크기(바이트)가 FREE가 테이블의 컬럼 크기(VARCHAR2()) 보다 큰 경우
      if(byteLength > contextLenght ) {
         return false;
      } else {
         return true;
      }
   }
}