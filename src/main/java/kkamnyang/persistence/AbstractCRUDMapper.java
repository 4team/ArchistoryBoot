package kkamnyang.persistence;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;

public class AbstractCRUDMapper<V,K> implements CRUDMapper<V,K>{
	
	@Autowired
	protected SqlSession session;
	
	protected String namespace;
	
	public AbstractCRUDMapper(){
		String name = getClass().getName();
		this.namespace = name.substring(0, name.length()-4);		
	}
	
	@Override
	public int create(V vo) throws Exception {
		return session.insert(namespace+".create",vo);
	}

	@Override
	public V read(K routeno) throws Exception {
		return session.selectOne(namespace+".read",routeno);
	}

	@Override
	public List<V> list() throws Exception {
		return session.selectList(namespace+".list");
		
	}

	@Override
	public void delete(K routeno) throws Exception {
		session.delete(namespace+".delete",routeno);
	}

	@Override
	public void update(V vo) throws Exception {
		session.update(namespace+".update",vo);
	}

}
