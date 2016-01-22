package kkamnyang.persistence;

import org.springframework.stereotype.Repository;

import kkamnyang.domain.AdminDTO;
import kkamnyang.domain.AdminVO;

@Repository
public class AdminMapperImpl extends AbstractCRUDMapper<AdminVO, Integer> implements AdminMapper {

	@Override
	public AdminVO login(AdminDTO dto) throws Exception {
		return session.selectOne(namespace+".login",dto);
	}

	@Override
	public String getPass(String email) throws Exception {
		return session.selectOne(namespace+".getPass",email);
	}

	@Override
	public String getName(String email) throws Exception {
		return session.selectOne(namespace+".getName",email);
	}

	@Override
	public int getNo(String email) throws Exception {
		return session.selectOne(namespace+".getNo",email);
	}

	@Override
	public boolean isEnable(String email) throws Exception {
		return session.selectOne(namespace+".isEnable",email);
	}


}
