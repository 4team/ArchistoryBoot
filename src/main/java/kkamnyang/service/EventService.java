package kkamnyang.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kkamnyang.domain.EventVO;
import kkamnyang.persistence.EventMapper;


@Service
public class EventService {

	@Autowired
	EventMapper mapper;
	
	public List<EventVO> list() throws Exception{
		return mapper.list();
	}
	
	public List<EventVO> elist(Integer routeno) throws Exception{
		return mapper.elist(routeno);
	}

	public void regist(EventVO vo) throws Exception{
		mapper.create(vo);
	}
	
	public EventVO view(Integer eventno) throws Exception{
		return mapper.read(eventno);
	}
	
	public void modify(EventVO vo) throws Exception{
		mapper.update(vo);
	}
	
	public void remove(Integer eventno) throws Exception{
		mapper.delete(eventno);
	}
	
	public void attachCreate(EventVO vo)throws Exception{
		System.out.println("?��벤서비스?���?..");
		mapper.attachCreate(vo);
	}
	
	public List<String> getAttach(Integer eventno)throws Exception{
		return mapper.getAttach(eventno);
	}
	
	public void attachModify(EventVO vo)throws Exception{
		System.out.println("첨�??��?��?��?�� ?��비스 ?���?.....");
		mapper.update(vo);
		mapper.updateAttach(vo);
	}
}
