package kr.co.campfire.common.controller;

import java.util.Iterator;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;

@Controller
public class LoginCheckController {

	public Boolean loginCheck(HttpSession session) {
	    Integer memberNum = (Integer) session.getAttribute("memberNum");
	    return memberNum != null;
	}
}