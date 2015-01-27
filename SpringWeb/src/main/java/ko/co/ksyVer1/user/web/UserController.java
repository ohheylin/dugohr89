package ko.co.ksyVer1.user.web;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import ko.co.ksyVer1.board.web.BoardController;
import ko.co.ksyVer1.user.service.UserService;
import ko.co.ksyVer1.user.service.bean.UserInfo;
import ko.co.ksyVer1.user.service.bean.VUserInfo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {
	
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	@Autowired
	private UserService userService;
	
	//초기화면
	@RequestMapping("/index.html")
	public ModelAndView handleRequestIndex(HttpServletRequest req,
			HttpServletResponse res) throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		
		String timestamp = new SimpleDateFormat("yyyy년 MM월 dd일 HH시 mm분 ss초").format(Calendar.getInstance().getTime());

		modelAndView.setViewName("index");

		modelAndView.addObject("now", timestamp);
		return modelAndView;
	}
	
	//로그인 초기화면
	@RequestMapping(value="login.html", method=RequestMethod.GET)
	public String toLoginView(Model model) {
		// 현재시간
		String timestamp = new SimpleDateFormat("yyyy년 MM월 dd일 HH시 mm분 ss초")
			.format(Calendar.getInstance().getTime());
		model.addAttribute("now", timestamp);
		VUserInfo vUserInfo = new VUserInfo();
		model.addAttribute("loginForm", vUserInfo);
		return "login";
	}
	
	//로그인
	@RequestMapping(value="login.html", method=RequestMethod.POST)
	public String onSubmit(	@ModelAttribute VUserInfo loginForm,
							HttpSession session, Model model) {
		UserInfo userInfo = new UserInfo(loginForm);
		UserInfo loginUser = userService.login(userInfo);
		if (userService.login(userInfo)==null) {
			return "redirect:/login.html";
		} else {
			session.setAttribute("userInfo", loginUser);
			return "redirect:/index.html";
		}
	}
	
	//로그아웃
	@RequestMapping(value="logout.html", method=RequestMethod.GET)
	public ModelAndView handleRequestLogout(HttpServletRequest req,
			HttpServletResponse res) throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		
		HttpSession session = req.getSession();
		session.setAttribute("user", null);
		session.invalidate();
		
		modelAndView.setViewName("redirect:/index.html"); 
		return modelAndView;
	}
	
	//회춴가입 초기화면
	@RequestMapping(value="/signUp.html", method=RequestMethod.GET)
	public String signUp(Model model) {
		model.addAttribute("signUpForm", new VUserInfo());
		return "signUp";
	}
	
	//회원가입
	@RequestMapping(value="/signUp.html", method=RequestMethod.POST)
	public String onSubmitSignUp(	@Valid @ModelAttribute("signUpForm") VUserInfo signUpForm, 
							BindingResult result, Model model) {
		//에러가 발생하면 돌아간다
		if (result.hasErrors()) {
			return "signUp";
		}
		
		//사용자 처리 에러(아이디 중복 체크)
		List<UserInfo> list = userService.searchByIdAjax(signUpForm.getUserid());
		if (list.size() > 0) {
			result.rejectValue("userid", "userid.dup", null, "아이디가 중복되었습니다");
			model.addAttribute("signUpForm", signUpForm);
			return "signUp";
		}
		
		//회원가입 처리
		userService.addUser(signUpForm);
		return "redirect:/login.html";
	}
	
	//이름으로 사용자 검색
	@RequestMapping(value="/userSearch.json", method={RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public List<UserInfo> searchUser(@RequestParam(required=false) String name) {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		List<UserInfo> list = userService.searchByName(name);
		return list;
	}
	
	//사용자 조회(아이디)
	@RequestMapping(value="/userSearId.json", method={RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public List<UserInfo> searchUserId(@RequestParam(required=false) String userid) {
		List<UserInfo> list = userService.searchByIdAjax(userid);
		return list;
	}

}
