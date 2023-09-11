package kr.co.campfire.common.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class IndexController {
	@RequestMapping("/")
	public String loginIndex(HttpSession session, Model model) {
		model.addAttribute("msg", (String) session.getAttribute("msg"));
		model.addAttribute("status", (String) session.getAttribute("status"));

		session.removeAttribute("msg");
		session.removeAttribute("status");
		
		return "member/login";
	}
}
