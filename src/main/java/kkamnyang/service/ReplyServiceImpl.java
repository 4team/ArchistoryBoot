package kkamnyang.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kkamnyang.domain.Criteria;
import kkamnyang.domain.ReplyVO;
import kkamnyang.persistence.BoardDAO;
import kkamnyang.persistence.ReplyDAO;


@Service
public class ReplyServiceImpl implements ReplyService {
	
	@Inject
	private ReplyDAO replyDAO;
	
	@Inject
	private BoardDAO boardDAO;

	@Override
	public List<ReplyVO> listReply(Integer boardNo) throws Exception {
		
		return replyDAO.list(boardNo);
	}
	
	@Transactional
	@Override
	public void addReply(ReplyVO vo) throws Exception {
		 replyDAO.create(vo);
		 boardDAO.updateReplyCnt(vo.getBoardNo(), 1);
	}

	@Override
	public void modifyReply(ReplyVO vo) throws Exception {
		 replyDAO.update(vo);

	}

	@Transactional
	@Override
	public void removeReply(Integer replyNo) throws Exception {
		int bno = replyDAO.getBoardNo(replyNo);
		replyDAO.delete(replyNo);
		boardDAO.updateReplyCnt(bno, -1);

	}

	@Override
	public List<ReplyVO> listReplyPage(Integer boardNo, Criteria cri) throws Exception {
		// TODO Auto-generated method stub
		return replyDAO.listPage(boardNo, cri);
	}

	@Override
	public int count(Integer boardNo) throws Exception {
		// TODO Auto-generated method stub
		return replyDAO.count(boardNo);
	}

}
