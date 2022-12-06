package org.coding.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;
import org.coding.model.CriteriaVO;
import org.coding.model.ReplyVO;

public interface ReplyMapper {
	
	// 댓글쓰기에 해당되는 DB작업 설계
			// return타입이 int일 때, 1이면 insert 성공, 0이면 insert 실패
			public int rewrite(ReplyVO reply);
			
			// 댓글목록리스트에 해당하는 DB작업 설계
			//public ArrayList<ReplyVO> list(int bno);  //resultType을 배열로 가져와라.
			
			public ArrayList<ReplyVO> list(@Param("cri") CriteriaVO cri,@Param("bno") int bno); 

			// 댓글 수정에 해당하는 DB작업 설계
			public int modify(ReplyVO reply);
			
			// 댓글 삭제에 해당하는 DB작업 설계
			public int remove(ReplyVO reply);
			
			// 게시물별 댓글 총갯수 파악.
			public int rpycnt(int bno);

}
