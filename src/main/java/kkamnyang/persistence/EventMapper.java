package kkamnyang.persistence;

import java.util.List;

import kkamnyang.domain.EventVO;

public interface EventMapper extends CRUDMapper<EventVO,Integer> {

	public List<EventVO> elist(Integer routeno);

	public void attachCreate(EventVO vo) throws Exception;
	
	public List<String> getAttach(Integer eventno) throws Exception;
	
	public void attachModify(EventVO vo) throws Exception;

	public void updateAttach(EventVO vo) throws Exception;
	
}
