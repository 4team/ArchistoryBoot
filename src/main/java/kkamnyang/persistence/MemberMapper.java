package kkamnyang.persistence;

import kkamnyang.domain.MemberVO;

public interface MemberMapper extends CRUDMapper<MemberVO,Integer>{

	public MemberVO findByEmail(String email);

	
}
