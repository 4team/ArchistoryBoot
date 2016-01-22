package kkamnyang.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kkamnyang.domain.BoardVO;
import kkamnyang.domain.Criteria;
import kkamnyang.domain.SearchCriteria;

@Repository
public class BoardDAOImpl implements BoardDAO {

	@Autowired
	private SqlSession session;

	private static String namespace = "org.kkamnyang.mapper.BoardMapper";

	@Override
	public void create(BoardVO vo) throws Exception {
		System.out.println("Í≤åÏãúÎ¨? ?ì±Î°ùÌï®...");
		session.insert(namespace + ".create", vo);
	}

	@Override
	public BoardVO read(Integer boardNo) throws Exception {
		return session.selectOne(namespace + ".read", boardNo);
	}

	@Override
	public void update(BoardVO vo) throws Exception {
		session.update(namespace + ".update", vo);
	}

	@Override
	public void delete(Integer boardNo) throws Exception {
		session.delete(namespace + ".delete", boardNo);
	}

	@Override
	public List<BoardVO> listAll() throws Exception {
		return session.selectList(namespace + ".listAll");
	}

	@Override
	public List<BoardVO> listPage(int page) throws Exception {
		if (page <= 0) {
			page = 1;
		}
		page = (page - 1) * 10;
		return session.selectList(namespace + ".listPage", page);
	}

	@Override
	public List<BoardVO> listCriteria(Criteria cri) throws Exception {
		return session.selectList(namespace + ".listCriteria", cri);
	}

	@Override
	public int countPaging(Criteria cri) throws Exception {
		return session.selectOne(namespace + ".countPaging", cri);
	}

	@Override
	public List<BoardVO> listSearch(SearchCriteria cri) throws Exception {
		return session.selectList(namespace + ".listSearch", cri);
	}

	@Override
	public int listSearchCount(SearchCriteria cri) throws Exception {
		return session.selectOne(namespace + ".listSearchCount", cri);
	}

	@Override
	public void addAttach(String fullName) throws Exception {
		System.out.println("?åå?ùºÏ≤®Î? ?ò∏Ï∂úÎê®!" + fullName);
		session.insert(namespace + ".addAttach", fullName);
	}

	@Override
	public List<String> getAttach(Integer boardNo) throws Exception {
		return session.selectList(namespace + ".getAttach", boardNo);
	}

	@Override
	public void updateReplyCnt(Integer boardNo, int amount) throws Exception {
		Map<String, Object> paramMap = new HashMap<String, Object>();

		paramMap.put("boardNo", boardNo);
		paramMap.put("amount", amount);

		session.update(namespace + ".updateReplyCnt", paramMap);

	}

	@Override
	public void updateViewCnt(Integer boardNo) throws Exception {

		session.update(namespace + ".updateViewCnt", boardNo);

	}

	@Override
	public void deleteAttach(Integer boardNo) throws Exception {
		session.delete(namespace + ".deleteAttach", boardNo);
	}

	@Override
	public void replaceAttach(String fullName, Integer boardNo) throws Exception {
		Map<String, Object> paramMap = new HashMap<String, Object>();

		paramMap.put("boardNo", boardNo);
		paramMap.put("fullName", fullName);

		session.insert(namespace + ".replaceAttach", paramMap);
	}

}
