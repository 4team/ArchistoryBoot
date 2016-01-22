package kkamnyang.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kkamnyang.domain.AdminDTO;
import kkamnyang.domain.AdminVO;
import kkamnyang.persistence.AdminMapper;

@Service
public class AdminService{

	@Autowired
	AdminMapper mapper;
	
	public int regist(AdminVO vo) throws Exception{
		return mapper.create(vo);
	}
	
	public AdminVO view(Integer adminno) throws Exception{
		return mapper.read(adminno);
	}
	
	public void modify(AdminVO vo) throws Exception{
		mapper.update(vo);
	}
	
	public void remove(Integer adminno) throws Exception{
		mapper.delete(adminno);
	}
	
	public AdminVO login(AdminDTO dto) throws Exception{
		return mapper.login(dto);
	}
	
	
	public String getPass(String useremail) throws Exception{
		return mapper.getPass(useremail);
	}
	
	public int getNo(String useremail) throws Exception{
		return mapper.getNo(useremail);
	}
}
