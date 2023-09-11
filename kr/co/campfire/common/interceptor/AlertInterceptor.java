package kr.co.campfire.common.interceptor;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.lang.Nullable;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class AlertInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			@Nullable ModelAndView modelAndView) throws Exception {

		if (modelAndView != null) {
			String viewName = modelAndView.getViewName();

			// !go to redirect == !go to controller == if go to view
			if (!viewName.startsWith("redirect:")) {

				HttpSession session = request.getSession();
				String msg = (String) session.getAttribute("tw_msg");
				String status = (String) session.getAttribute("tw_status");

				if ((msg != null) && (!msg.equals("")) && (status != null) && (!status.equals(""))) {

					ModelMap modelMap = (ModelMap) modelAndView.getModel();
					modelMap.addAttribute("tw_msg", msg);
					modelMap.addAttribute("tw_status", status);

//					session.removeAttribute("tw_msg");
//					session.removeAttribute("tw_status");
				}
			} // End of if (!viewName.startsWith("redirect:")) {...}
		} // End of if (modelAndView != null) {...}
		
	} // End of postHandle(...) {...}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
			@Nullable Exception ex) throws Exception {
	}
}
