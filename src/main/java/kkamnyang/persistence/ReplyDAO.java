package kkamnyang.persistence;

import java.util.List;

import kkamnyang.domain.Criteria;
import kkamnyang.domain.ReplyVO;

public interface ReplyDAO {
	
	public List<ReplyVO> list(Integer boardNo)throws Exception;
	
	public List<ReplyVO> listPage(Integer boardNo, Criteria cri) throws Exception;
	
	public int count(Integer boardNo) throws Exception;
	
	public void create(ReplyVO vo) throws Exception;
	
	public void update(ReplyVO vo) throws Exception;
	
	public void delete(Integer replyNo) throws Exception;
	
	public int getBoardNo(Integer replyNo) throws Exception;

}
