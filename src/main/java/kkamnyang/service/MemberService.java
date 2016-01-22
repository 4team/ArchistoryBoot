package kkamnyang.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kkamnyang.domain.MemberVO;
import kkamnyang.persistence.MemberMapper;

@Service
public class MemberService {

	@Autowired
	MemberMapper mapper;
	
	public int register(MemberVO vo) throws Exception{
		return mapper.create(vo);
	}
	
	public MemberVO view(Integer MemberNo) throws Exception{
		return mapper.read(MemberNo);
	}
	
	public List<MemberVO> list() throws Exception{
		List<MemberVO> list = mapper.list();
		return list;
	}
	
	public void modify(MemberVO vo) throws Exception{
		mapper.update(vo);
	}
	
	public void remove(Integer MemberNo) throws Exception{
		mapper.delete(MemberNo);
	}
	
}
