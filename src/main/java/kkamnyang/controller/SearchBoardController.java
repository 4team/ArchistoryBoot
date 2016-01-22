package kkamnyang.controller;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kkamnyang.domain.BoardVO;
import kkamnyang.domain.PageMaker;
import kkamnyang.domain.SearchCriteria;
import kkamnyang.service.BoardService;
import kkamnyang.service.EventService;

@Controller
@RequestMapping("/sboard/*")
public class SearchBoardController {

  private static final Logger logger = LoggerFactory.getLogger(SearchBoardController.class);

  @Inject
  private BoardService service;
  private EventService eservice;

  @RequestMapping(value = "/list", method = RequestMethod.GET)
  public void listPage(@ModelAttribute("cri") SearchCriteria cri, Model model) throws Exception {

    logger.info(cri.toString());
    

    model.addAttribute("list", service.listSearchCriteria(cri));

    PageMaker pageMaker = new PageMaker();
    pageMaker.setCri(cri);

    pageMaker.setTotalCount(service.listSearchCount(cri));

    model.addAttribute("pageMaker", pageMaker);
  }
  
  @RequestMapping(value="/read", method=RequestMethod.GET)
  public void read(@RequestParam("boardNo") int boardNo, @ModelAttribute("cri")SearchCriteria cri, Model model)throws Exception{
	  model.addAttribute(service.read(boardNo));
  }
  
  @RequestMapping(value="/remove", method=RequestMethod.POST)
  public String remove(@RequestParam("boardNo") int boardNo, SearchCriteria cri, RedirectAttributes rttr)throws Exception{
	  
	  service.remove(boardNo);
	  
	  rttr.addAttribute("page", cri.getPage());
	  rttr.addAttribute("perPageNum", cri.getPerPageNum());
	  rttr.addAttribute("searchType", cri.getSearchType());
	  rttr.addAttribute("keyword", cri.getKeyword());
	
	  rttr.addAttribute("msg", "SUCCESS");
    
	  return "redirect:/sboard/list";
  }
  
  @RequestMapping(value="/modify", method=RequestMethod.GET)
  public void modifyPagingGET(int boardNo, @ModelAttribute("cri") SearchCriteria cri, Model model)throws Exception{
	  model.addAttribute(service.read(boardNo));
	  }

  @RequestMapping(value="/modify", method=RequestMethod.POST)
  public String modifyPagingPOST(BoardVO board, SearchCriteria cri, RedirectAttributes rttr)throws Exception{
	  
	  logger.info(cri.toString());
	  service.modify(board);
	  
	  rttr.addAttribute("page", cri.getPage());
	  rttr.addAttribute("perPageNum", cri.getPerPageNum());
	  rttr.addAttribute("searchType", cri.getSearchType());
	  rttr.addAttribute("keyword", cri.getKeyword());
	
	  rttr.addAttribute("msg", "SUCCESS");
   
	  logger.info(rttr.toString());
	  
	  return "redirect:/sboard/list";
  }
  
  @RequestMapping(value="/register", method=RequestMethod.GET)
  public void registGET()throws Exception{
	  logger.info("regist get..............");
	  }  
  
  @RequestMapping(value="/register", method=RequestMethod.POST)
  public String registPOST(BoardVO board, RedirectAttributes rttr)throws Exception{
	  
	  logger.info("regist post.........");
	  logger.info(board.toString());
	  service.regist(board);
	  
	  rttr.addFlashAttribute("msg", "SUCCESS");
	    
	  return "redirect:/sboard/list";
  }
  
  @RequestMapping(value="/getAttach/{boardNo}")
  @ResponseBody
  public List<String> getAttach(@PathVariable("boardNo")Integer boardNo)throws Exception{
	System.out.println("Ï≤®Î??åå?ùº?ù¥ Î°úÎìú?ê®...");
	System.out.println(service.getAttach(boardNo));
	return service.getAttach(boardNo);  
  }
  
  
  @RequestMapping(value="/usage" , method=RequestMethod.GET)
	public void usageGET() throws Exception{
		logger.info("usage get....");
	}
}
