package kkamnyang.persistence;

import java.util.List;

import kkamnyang.domain.BoardVO;
import kkamnyang.domain.Criteria;
import kkamnyang.domain.SearchCriteria;

public interface BoardDAO {
	
	public void create(BoardVO vo)throws Exception;
	
	public BoardVO read(Integer boardNo)throws Exception;
	
	public void update(BoardVO vo)throws Exception;
	
	public void delete(Integer boardNo)throws Exception;
	
	public List<BoardVO> listAll()throws Exception;
	
	public List<BoardVO> listPage(int page)throws Exception;
	
	public List<BoardVO> listCriteria(Criteria cri)throws Exception;
	
	public int countPaging(Criteria cri)throws Exception;

	public List<BoardVO> listSearch(SearchCriteria cri)throws Exception;
	
	public int listSearchCount(SearchCriteria cri)throws Exception;

	public void addAttach(String fullName)throws Exception;
	
	public List<String> getAttach(Integer boardNo)throws Exception;
	
	public void updateReplyCnt(Integer boardNo, int amount) throws Exception;
	
	public void updateViewCnt(Integer boardNo)throws Exception;

	public void deleteAttach(Integer boardNo)throws Exception;
	
	public void replaceAttach(String fullName, Integer boardNo)throws Exception;
	
}