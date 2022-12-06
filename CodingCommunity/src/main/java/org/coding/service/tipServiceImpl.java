package org.coding.service;

import java.util.ArrayList;

import org.coding.mapper.AttachMapper;
import org.coding.mapper.tipMapper;
import org.coding.model.AttachVO;
import org.coding.model.BoardVO;
import org.coding.model.CriteriaVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class tipServiceImpl implements tipService{
	
	@Autowired
	tipMapper tm;

	@Autowired
	AttachMapper am;
	
	// BoardService 에서 설계되어진 write 추상메서드를 구현
		public void write(BoardVO board) {
			// BoardMapper에 있는 write메서드를 호출
			// 메서드의 매개변수를 통해 BoardVO 값을
			// BoardMapper의 write 메서드로 전달
			tm.write(board);
			
			//System.out.println("aaaaaaaa="+board.getAttach());
			
			// 첨부파일 있을때 실행
			if(board.getAttach() != null) {
			
				board.getAttach().forEach(attach->{
					
					// AttachFileVO 의 bno 에 BoardVO 의 bno 를 저장.
					attach.setBno(board.getBno());
					am.attach(attach);				
				});
			
			}
		}
		

		// BoardService에서 설계되어진 list추상메서드를 구현
		public ArrayList<BoardVO> list(CriteriaVO cri) {
			return tm.list(cri);
		}

		
		public BoardVO detail(BoardVO board) {
			return tm.detail(board);
		}

		
		// BoardService에서 설계되어진 modify 추상메서드를 구현
		public void modify(BoardVO board) {
			tm.modify(board);
		}
		
		
		// BoardService에서 설계되어진 remove 추상메서드를 구현
		public void remove(BoardVO board) {
			tm.remove(board);
		}
		
		public int total(CriteriaVO cri) {
			return tm.total(cri);
		}


		// BoardService에서 설계되어진 attachlist 추상메서드를 구현
		public ArrayList<AttachVO> attachlist(int bno){
			return am.attachlist(bno);
		};
		
		// // BoardService에서 설계되어진 count 추상메서드를 구현
		public int count(BoardVO board) {
			return tm.count(board);
		}

}
