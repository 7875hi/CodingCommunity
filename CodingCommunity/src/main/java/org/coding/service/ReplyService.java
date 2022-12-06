package org.coding.service;

import org.coding.model.CriteriaVO;
import org.coding.model.ReplyPageVO;
import org.coding.model.ReplyVO;

public interface ReplyService {
	
	// 댓글 쓰기를 위한 설계
	public int rewrite(ReplyVO reply);
	
	// 댓글목록리스트 위한 설계
	//public ArrayList<ReplyVO> list(int bno);
	//      배열          <타입>
	
	public ReplyPageVO list(CriteriaVO cri, int bno);
	
	// 댓글 수정 위한 설계
	public int modify(ReplyVO reply);
	
	// 댓글 삭제 위한 설계
	public int remove(ReplyVO reply);

}
