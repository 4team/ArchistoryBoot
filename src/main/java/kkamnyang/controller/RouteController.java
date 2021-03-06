package kkamnyang.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kkamnyang.domain.RouteVO;
import kkamnyang.service.RouteService;

@Controller
@RequestMapping(value="/route/*")
public class RouteController{
		
	@Autowired
	RouteService service;
	
	@RequestMapping(value="/listAll",method=RequestMethod.GET)
	public @ResponseBody List<RouteVO> listAll(HttpServletRequest request) throws Exception{
		System.out.println("?λͺ¨λ  λ£¨νΈ λ¦¬μ€?Έ? μΆλ ₯.");
		List<RouteVO> result = service.listAll();
		return result;
	}
	
	
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public @ResponseBody List<RouteVO> list(@Param(value="adminno") int adminno, HttpServletRequest request) throws Exception{
		System.out.println(adminno + "λ²? ADMIN? λ£¨νΈλ¦¬μ€?Έ μΆλ ₯.");
		List<RouteVO> result = service.list(adminno);
		return result;
	}
	
	@RequestMapping(value="/create", method=RequestMethod.POST)
	public void createRoute(@RequestBody RouteVO vo,HttpServletResponse response) throws Exception{
		System.out.println("["+vo.getRoutename()+"] λ£¨νΈ ??± ?ΈμΆλ¨.=====");
		service.regist(vo);
		Integer nowSequnece = vo.getRouteno();
		response.getWriter().print(nowSequnece);
		
	}
	
	@RequestMapping(value="/view", method = RequestMethod.GET)
	public @ResponseBody RouteVO view(@RequestParam("routeno") Integer routeno, HttpServletRequest request) throws Exception{
		System.out.println("route view GET ?ΈμΆλ¨");
		RouteVO vo = service.view(routeno);
		System.out.println(vo);
		return vo;
	}
	
	@RequestMapping(value="/modify", method = RequestMethod.POST)
	public void modifyRoute(RouteVO vo) throws Exception{
		service.modify(vo);
	}
	
	@RequestMapping(value="/remove", method = RequestMethod.POST)
	public ResponseEntity<String> removeRoute(@RequestBody RouteVO vo) throws Exception{
		System.out.println("route ?­?  Post ?ΈμΆλ¨");
		ResponseEntity<String> entity = null;
		try{
			System.out.println(vo.getRouteno()+"λ²? λ£¨νΈ ?­? ... ?? ??");
			service.remove(vo.getRouteno());
			System.out.println("?­?  ?λ£?");
			entity = new ResponseEntity<String>("SUCCESS",HttpStatus.OK);
		}catch(Exception e){
			e.getStackTrace();
			e.getMessage();
			entity = new ResponseEntity<String>("FAIL",HttpStatus.BAD_REQUEST);
		}
		
		return entity;
	}
}
