package kkamnyang.persistence;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import kkamnyang.domain.EventVO;

@Repository
public class EventMapperImpl extends AbstractCRUDMapper<EventVO,Integer> implements EventMapper {

	@Override
	public List<EventVO> elist(Integer routeno) {
		return session.selectList(namespace+".elist",routeno);
	}

	@Transactional
	@Override
	public void attachCreate(EventVO vo) throws Exception{
		
		session.insert(namespace + ".create", vo);
		String efiles = vo.getEfiles();
		if (efiles.equals("")) {
			return;
		}else{
			System.out.println(efiles);
			session.insert(namespace + ".addAttach",efiles);
		}
	}

	@Override
	public List<String> getAttach(Integer eventno) {		
		return session.selectList(namespace + ".getAttach", eventno);
	}

	@Transactional
	@Override
	public void attachModify(EventVO vo) throws Exception{
		session.update(namespace + ".update", vo);
		session.update(namespace + ".updateAttach",vo.getEventno());
	}
}
