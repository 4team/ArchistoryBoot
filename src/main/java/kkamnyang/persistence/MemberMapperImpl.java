package kkamnyang.persistence;

import java.util.List;

import org.springframework.stereotype.Repository;

import kkamnyang.domain.MemberVO;

@Repository
public class MemberMapperImpl extends AbstractCRUDMapper<MemberVO, Integer> implements MemberMapper {

	@Override
	public int create(MemberVO vo) throws Exception {
		return session.insert(namespace+".create",vo);
	}

	@Override
	public MemberVO read(Integer memberNo) throws Exception {
		return session.selectOne(namespace+".read",memberNo);
	}

	@Override
	public List<MemberVO> list() throws Exception {
	
		return session.selectList(namespace+".list");
	}

	@Override
	public void delete(Integer memberNo) throws Exception {
		session.delete(namespace+".delete",memberNo);

	}

	@Override
	public void update(MemberVO vo) throws Exception {
		session.update(namespace+".update",vo);

	}

	@Override
	public MemberVO findByEmail(String email) {
		return session.selectOne(namespace+".getUser",email);
	}

}
