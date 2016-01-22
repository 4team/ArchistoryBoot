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
@RequestMapping(value="/admin/*")
public class AdminController {

	@Autowired
	AdminService service;
	
	public AdminDetails getUser()
    {
        return (AdminDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
	
	@RequestMapping(value = "/login",method=RequestMethod.GET)
	public void adminLogin() {
	        System.out.println("λ‘κ·Έ?Έ?λ©΄μ΄ ?ΈμΆλ?΄ /admin/login.jspλ₯? λ³΄λΈ?€.");
	}
	
	@RequestMapping(value = "/logout",method=RequestMethod.GET)
	public void adminLogout() {
		
		System.out.println("λ‘κ·Έ????¬ /admin/logout.jspλ₯? λ³΄λΈ?€.");
		
	}
	
	@RequestMapping(value="/login", method = RequestMethod.POST)
	public void login(@RequestBody AdminDTO dto, HttpSession session, Model model) throws Exception{
		System.out.println("?κ΅°κ?? λ‘κ·Έ?Έ ??==================");
		AdminVO vo = service.login(dto);

		if(vo != null){
			System.out.println("["+vo.getUsername() + "] Admin?΄ λ‘κ·Έ?Έ ????€.");
			session.setAttribute("LOGIN", vo);
			model.addAttribute("adminVO",vo);
		}else{
			System.out.println("Adminκ³μ ? ?? κ²μ€?Έ? λ‘κ·Έ?Έ ?????€.");
		}

	}
	
	@RequestMapping(value = "/login_success",method=RequestMethod.GET)
	public ModelAndView adminSuccess(Model model) {
		System.out.println("λ‘κ·Έ?Έ? ?±κ³΅ν?¬ /login_success.jspλ₯? λ³΄λΈ?€.");
		ModelAndView view = new ModelAndView();
	    view.setViewName("admin");
	    view.addObject("adminno", getUser().getAdminno());
	    view.addObject("name", getUser().getUsername());
	        
		return view;
	}
	
}
