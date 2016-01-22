package kkamnyang.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import kkamnyang.domain.AdminDTO;
import kkamnyang.domain.AdminVO;
import kkamnyang.persistence.AdminDetails;
import kkamnyang.service.AdminService;

@RestController
@RequestMapping("/admin/*")
public class AdminController {

	@Autowired
	AdminService service;
	
	public AdminDetails getUser()
    {
        return (AdminDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
	
	@RequestMapping("/login")
	public void adminLogin() {
	        System.out.println("로그인이 호출되어 /admin/login.jsp를 보낸다.");
	}
	
	@RequestMapping(value = "/logout",method=RequestMethod.GET)
	public void adminLogout() {
		
		System.out.println("로그?��?��?��?�� /admin/logout.jsp�? 보낸?��.");
		
	}
	
	@RequestMapping(value="/login", method = RequestMethod.POST)
	public void login(@RequestBody AdminDTO dto, HttpSession session, Model model) throws Exception{
		System.out.println("로그인 처리 POST호출됨==================");
		AdminVO vo = service.login(dto);

		if(vo != null){
			System.out.println("["+vo.getUsername() + "] Admin이 로그인하였습니다.");
			session.setAttribute("LOGIN", vo);
			model.addAttribute("adminVO",vo);
		}else{
			System.out.println("Admin계정이 없는 사용자가 로그인 시도했었다.");
		}

	}
	
	@RequestMapping(value = "/login_success",method=RequestMethod.GET)
	public ModelAndView adminSuccess(Model model) {
		System.out.println("로그인에 성공하였다.");
		ModelAndView view = new ModelAndView();
	    view.setViewName("admin");
	    view.addObject("adminno", getUser().getAdminno());
	    view.addObject("name", getUser().getUsername());
	        
		return view;
	}
	
}
