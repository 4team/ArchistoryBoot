package kkamnyang.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import kkamnyang.domain.AdminDTO;
import kkamnyang.domain.AdminVO;
import kkamnyang.persistence.AdminDetails;
import kkamnyang.service.AdminService;

@Controller
@RequestMapping("/admin/*")
public class AdminController {

	@Autowired
	AdminService service;
	
	public AdminDetails getUser()
    {
        return (AdminDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
	
	@RequestMapping(value = "/login",method=RequestMethod.GET)
	public void adminLogin() {
	        System.out.println("로그?��?��면이 ?��출되?�� /admin/login.jsp�? 보낸?��.");
	}
	
	@RequestMapping(value = "/logout",method=RequestMethod.GET)
	public void adminLogout() {
		
		System.out.println("로그?��?��?��?�� /admin/logout.jsp�? 보낸?��.");
		
	}
	
	@RequestMapping(value="/login", method = RequestMethod.POST)
	public void login(@RequestBody AdminDTO dto, HttpSession session, Model model) throws Exception{
		System.out.println("?��군�??�� 로그?�� ?��?��==================");
		AdminVO vo = service.login(dto);

		if(vo != null){
			System.out.println("["+vo.getUsername() + "] Admin?�� 로그?�� ?��???��.");
			session.setAttribute("LOGIN", vo);
			model.addAttribute("adminVO",vo);
		}else{
			System.out.println("Admin계정?�� ?��?�� 게스?��?�� 로그?�� ?��?��???��.");
		}

	}
	
	@RequestMapping(value = "/login_success",method=RequestMethod.GET)
	public ModelAndView adminSuccess(Model model) {
		System.out.println("로그?��?�� ?��공하?�� /login_success.jsp�? 보낸?��.");
		ModelAndView view = new ModelAndView();
	    view.setViewName("admin");
	    view.addObject("adminno", getUser().getAdminno());
	    view.addObject("name", getUser().getUsername());
	        
		return view;
	}
	
}
