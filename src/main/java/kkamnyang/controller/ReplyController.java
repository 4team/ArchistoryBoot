package kkamnyang.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import kkamnyang.domain.Criteria;
import kkamnyang.domain.PageMaker;
import kkamnyang.domain.ReplyVO;
import kkamnyang.service.ReplyService;

@RestController
@RequestMapping("/replies")
public class ReplyController {

	@Inject
	private ReplyService service;
	
	@RequestMapping(value="",method=RequestMethod.POST)
	public ResponseEntity<String> register(@RequestBody ReplyVO vo){
		
		ResponseEntity<String> entity = null;
		
		try{
			service.addReply(vo);
			entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
			
		}catch(Exception e){
			e.printStackTrace();
			entity = new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
		
		return entity;
	}
	
	
	@RequestMapping(value="/all/{boardNo}", method=RequestMethod.GET)
	public ResponseEntity<List<ReplyVO>> list(@PathVariable("boardNo")Integer boardNo){
		
		ResponseEntity<List<ReplyVO>> entity = null;
		
		try{
			entity= new ResponseEntity<>(service.listReply(boardNo),HttpStatus.OK);
			
		}catch(Exception e){
			e.printStackTrace();
			entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			
		}
		
		return entity;
	}
	
	@RequestMapping(value="/{replyNo}", method={RequestMethod.PUT, RequestMethod.PATCH})
	public ResponseEntity<String> update(@PathVariable("replyNo") Integer replyNo, @RequestBody ReplyVO vo){
		
		ResponseEntity<String> entity = null;
		
		try{
			vo.setReplyNo(replyNo);
			service.modifyReply(vo);
			
			entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
			
		}catch(Exception e){
			
			e.printStackTrace();
			entity = new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);	
		}	
		return entity;
	}
	
	@RequestMapping(value="/{replyNo}", method=RequestMethod.DELETE)
	public ResponseEntity<String> remove(@PathVariable("replyNo")Integer replyNo){
		
		ResponseEntity<String> entity = null;
		
		try{
			service.removeReply(replyNo);
			entity = new ResponseEntity<String>("SUCCESS",HttpStatus.OK);
			
		}catch(Exception e){
			e.printStackTrace();
			entity = new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);	
		}
		return entity;
	}
	
	@RequestMapping(value = "/{boardNo}/{page}", method = RequestMethod.GET)
	  public ResponseEntity<Map<String, Object>> listPage(
	      @PathVariable("boardNo") Integer boardNo,
	      @PathVariable("page") Integer page) {

	    ResponseEntity<Map<String, Object>> entity = null;
	    
	    try {
	      Criteria cri = new Criteria();
	      cri.setPage(page);

	      PageMaker pageMaker = new PageMaker();
	      pageMaker.setCri(cri);

	      Map<String, Object> map = new HashMap<String, Object>();
	      List<ReplyVO> list = service.listReplyPage(boardNo, cri);

	      map.put("list", list);

	      int replyCount = service.count(boardNo);
	      pageMaker.setTotalCount(replyCount);

	      map.put("pageMaker", pageMaker);

	      entity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);

	    } catch (Exception e) {
	      e.printStackTrace();
	      entity = new ResponseEntity<Map<String, Object>>(HttpStatus.BAD_REQUEST);
	    }
	    return entity;
	  }

	
	
}
