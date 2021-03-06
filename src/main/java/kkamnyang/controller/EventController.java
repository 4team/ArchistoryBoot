package kkamnyang.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kkamnyang.domain.EventVO;
import kkamnyang.service.EventService;

@Controller
@RequestMapping(value="/event/*")
public class EventController {
	
	@Autowired
	EventService service;
	
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public @ResponseBody List<EventVO> list(HttpServletRequest request) throws Exception{
		System.out.println("?´ë²¤í¸ ë¦¬ì¤?¸GET ?¸ì¶ë¨.");
		List<EventVO> result = service.list();
		System.out.println(service.list());
		return result;
	}
	
	@RequestMapping(value="/elist", method=RequestMethod.GET)
	public @ResponseBody List<EventVO> elist(@RequestParam("routeno")Integer routeno, HttpServletRequest request) throws Exception{
		
		System.out.println("ëª¨ë  ?´ë²¤í¸ ë¦¬ì¤?¸ ?¸ì¶ë¨.=====");
		List<EventVO> result = service.elist(routeno);
		System.out.println(service.elist(routeno));
		return result;
	}
	
	@RequestMapping(value="/create", method=RequestMethod.POST)
	public ResponseEntity<String> createEvent(@RequestBody EventVO vo) throws Exception{
		System.out.println("["+vo.getTitle() + "] ?´ë²¤í¸? ??± ?¸ì¶ë¨.=====");
		System.out.println(vo);
		ResponseEntity<String> entity = null;
		try{
			service.regist(vo);
			entity = new ResponseEntity<String>("result",HttpStatus.OK);
		}catch(Exception e){
			entity = new ResponseEntity<String>("result",HttpStatus.BAD_REQUEST);
		}
		return entity;
	}

	
	@RequestMapping(value="/attachCreate", method=RequestMethod.POST)
	public ResponseEntity<String> attachCreate(@RequestBody EventVO vo) throws Exception{
		System.out.println("["+vo.getTitle() + "] ?´ë²¤í¸ ì²¨ë???¼??± ?¸ì¶ë¨.=====");
		System.out.println(vo);
		
		ResponseEntity<String> entity = null;
		try{
			service.attachCreate(vo);
			entity = new ResponseEntity<String>("result",HttpStatus.OK);
		}catch(Exception e){
			entity = new ResponseEntity<String>("result",HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
	
	
	@RequestMapping(value="/view", method = RequestMethod.GET)
	public @ResponseBody EventVO view(@RequestParam("eventno") Integer eventno, HttpServletRequest request ) throws Exception{
		System.out.println("Event View GET ?¸ì¶ë¨.");
		EventVO result = service.view(eventno);
		System.out.println(result);
		return result;
	}
	
	@RequestMapping(value="/modify", method = RequestMethod.POST)
	public ResponseEntity<String> modifyEvent(@RequestBody EventVO vo) throws Exception{
		System.out.println("Event ??  POST ?¸ì¶ë¨.");
		ResponseEntity<String> entity = null;
		
		try{
			service.modify(vo);
			entity = new ResponseEntity<String>("result",HttpStatus.OK);
		}catch(Exception e){
			entity = new ResponseEntity<String>("result", HttpStatus.BAD_REQUEST);		
		}
		return entity;
	}
	
	@RequestMapping(value="/attachModify", method = RequestMethod.POST)
	public ResponseEntity<String> attachModify(@RequestBody EventVO vo) throws Exception{
		System.out.println("ì»¨í¸ë¡¤ë¬? ì²¨ë???¼??  POST ?¸ì¶ë¨.");
		ResponseEntity<String> entity = null;		
		try{
			service.attachModify(vo);
			entity = new ResponseEntity<String>("result",HttpStatus.OK);
		}catch(Exception e){
			e.printStackTrace();
			entity = new ResponseEntity<String>("result", HttpStatus.BAD_REQUEST);		
		}
		return entity;
	}
	
	@RequestMapping(value="/remove", method = RequestMethod.POST)
	public ResponseEntity<String> removeEvent(@RequestBody EventVO vo) throws Exception{
		System.out.println("Event ?­?  POST ?¸ì¶ë¨.");
		ResponseEntity<String> entity = null;
		
		try{
			service.remove(vo.getEventno());
			entity = new ResponseEntity<String>("result",HttpStatus.OK);
		}catch(Exception e){
			e.printStackTrace();
			entity = new ResponseEntity<String>("result",HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
	  
	  @RequestMapping(value="/getAttach/{eventno}")
	  @ResponseBody
	  public List<String> getEattach(@PathVariable("eventno")Integer eventno)throws Exception{
		System.out.println("ì²¨ë???¼?´ ë¡ë?¨...");
		System.out.println(service.getAttach(eventno));
		return service.getAttach(eventno);  
	  }
	  
	  
	  
}
