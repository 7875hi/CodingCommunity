package org.coding.controller;


import java.util.ArrayList;

import org.coding.model.AttachVO;
import org.coding.model.BoardVO;
import org.coding.model.CriteriaVO;
import org.coding.model.PageVO;
import org.coding.service.reviewService;
import org.coding.service.shareService;
import org.coding.service.studyService;
import org.coding.service.tipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class reviewController {

	@Autowired
	reviewService bs;
	
	@Autowired
	shareService ss;
	
	@Autowired
	studyService sts;
	
	@Autowired
	tipService ts;
	
	// Q&A 게시판
	// 게시판 목록 리스트
	@RequestMapping(value = "/board/list", method = RequestMethod.GET)
	public String list(Model model, CriteriaVO cri) {
		model.addAttribute("list", bs.list(cri));
		
		// list.jsp 실행 할 때 PageVO에 저장되어 있는 데이터를 가져와라.
		//                           생성자 호출(매개변수가 2개인 생성자)
		// board테이블(게시판테이블)에 전체 건수(select해서)를 아래에 190대신에 대입
		int total=bs.total(cri);
		//model.addAttribute("paging", new PageVO(cri, 190));
		model.addAttribute("paging", new PageVO(cri, total));
		
		return "board/list";
	}
	
	// 게시판 상세 페이지
	@RequestMapping(value = "/detail", method = RequestMethod.GET)
	// public String detail(int bno){
	public String detail (BoardVO board, Model model) {
		System.out.println(board);
		// 조회수 증가
		bs.count(board);
		// bs.detail(bno);
		model.addAttribute("detail", bs.detail(board));
		return "board/detail";
	}
	
	@RequestMapping(value = "/board/modify", method = RequestMethod.GET)
	public String modifyget (BoardVO board, Model model) {
		System.out.println(board);
		model.addAttribute("detail", bs.detail(board));
		return "board/modify";
	}
	
	@RequestMapping(value = "/board/modify", method = RequestMethod.POST)
	public String modifypost (BoardVO board, RedirectAttributes rttr) {
		bs.modify(board);
		rttr.addFlashAttribute("result", "modify success");
		return "redirect:/board/list";
	}
	
	// 게시판 삭제
	@RequestMapping(value = "/board/remove", method = {RequestMethod.GET, RequestMethod.POST})
	public String remove (BoardVO board) {
		bs.remove(board);
		return "redirect:/board/list";
	}
	
	
	// 게시판 글쓰기 페이지(화면) 
	@RequestMapping(value = "/write", method = RequestMethod.GET)
	public String write() {
		return "board/write";
	}

	// 게시판 글쓰기 페이지(insert가 이루어짐)
	// 모델 사용해 데이터 수집하기 -> 메모리 효율적 관리 가능
	@RequestMapping(value = "/write", method = RequestMethod.POST)
	public String writePost (BoardVO board) {
		System.out.println(board);
		
		// 비즈니스 영역 연결한 후 BoardService
		bs.write(board);
		return "redirect:/board/list";
	}
	
	// 해당 게시물의 첨부파일의 데이터를 ajax로 전송
	@RequestMapping(value = "/attachlist", method = RequestMethod.GET)
	public ResponseEntity<ArrayList<AttachVO>> uploadAjaxPost(int bno) {
		
		return new ResponseEntity<>(bs.attachlist(bno),HttpStatus.OK);
	}
	
	
	// 지식공유 게시판
	// 게시판 목록 리스트
	@RequestMapping(value = "/shboard/shlist", method = RequestMethod.GET)
	public String shlist(Model model, CriteriaVO cri) {
		model.addAttribute("list", ss.list(cri));
		
		// list.jsp 실행 할 때 PageVO에 저장되어 있는 데이터를 가져와라.
		//                           생성자 호출(매개변수가 2개인 생성자)
		// board테이블(게시판테이블)에 전체 건수(select해서)를 아래에 190대신에 대입
		int total=ss.total(cri);
		//model.addAttribute("paging", new PageVO(cri, 190));
		model.addAttribute("paging", new PageVO(cri, total));
		
		return "shboard/shlist";
	}
	
	// 게시판 상세 페이지
		@RequestMapping(value = "/shdetail", method = RequestMethod.GET)
		// public String detail(int bno){
		public String shdetail (BoardVO board, Model model) {
			System.out.println(board);
			// 조회수 증가
			ss.count(board);
			// bs.detail(bno);
			model.addAttribute("detail", ss.detail(board));
			return "shboard/shdetail";
		}
		
		@RequestMapping(value = "/shboard/shmodify", method = RequestMethod.GET)
		public String shmodifyget (BoardVO board, Model model) {
			System.out.println(board);
			model.addAttribute("detail", ss.detail(board));
			return "shboard/shmodify";
		}
		
		@RequestMapping(value = "/shboard/shmodify", method = RequestMethod.POST)
		public String shmodifypost (BoardVO board, RedirectAttributes rttr) {
			ss.modify(board);
			rttr.addFlashAttribute("result", "modify success");
			return "redirect:/shboard/shlist";
		}
		
		// 게시판 삭제
		@RequestMapping(value = "/shboard/shremove", method = {RequestMethod.GET, RequestMethod.POST})
		public String shremove (BoardVO board) {
			ss.remove(board);
			return "redirect:/shboard/shlist";
		}
		
		
		// 게시판 글쓰기 페이지(화면) 
		@RequestMapping(value = "/shwrite", method = RequestMethod.GET)
		public String shwrite() {
			return "shboard/shwrite";
		}

		// 게시판 글쓰기 페이지(insert가 이루어짐)
		// 모델 사용해 데이터 수집하기 -> 메모리 효율적 관리 가능
		@RequestMapping(value = "/shwrite", method = RequestMethod.POST)
		public String shwritePost (BoardVO board) {
			System.out.println(board);
			
			// 비즈니스 영역 연결한 후 BoardService
			ss.write(board);
			return "redirect:/shboard/shlist";
		}
		
		// 해당 게시물의 첨부파일의 데이터를 ajax로 전송
		@RequestMapping(value = "/shattachlist", method = RequestMethod.GET)
		public ResponseEntity<ArrayList<AttachVO>> shuploadAjaxPost(int bno) {
			
			return new ResponseEntity<>(ss.attachlist(bno),HttpStatus.OK);
		}
		
		
		// 스터디 게시판
		// 게시판 목록 리스트
		@RequestMapping(value = "/stdboard/stdlist", method = RequestMethod.GET)
		public String stdlist(Model model, CriteriaVO cri) {
			model.addAttribute("list", sts.list(cri));
			
			// list.jsp 실행 할 때 PageVO에 저장되어 있는 데이터를 가져와라.
			//                           생성자 호출(매개변수가 2개인 생성자)
			// board테이블(게시판테이블)에 전체 건수(select해서)를 아래에 190대신에 대입
			int total=sts.total(cri);
			//model.addAttribute("paging", new PageVO(cri, 190));
			model.addAttribute("paging", new PageVO(cri, total));
			
			return "stdboard/stdlist";
		}
		
		// 게시판 상세 페이지
		@RequestMapping(value = "/stddetail", method = RequestMethod.GET)
		// public String detail(int bno){
		public String stddetail (BoardVO board, Model model) {
			System.out.println(board);
			// 조회수 증가
			sts.count(board);
			// bs.detail(bno);
			model.addAttribute("detail", sts.detail(board));
			return "stdboard/stddetail";
		}
			
		@RequestMapping(value = "/stdboard/stdmodify", method = RequestMethod.GET)
		public String stdmodifyget (BoardVO board, Model model) {
			System.out.println(board);
			model.addAttribute("detail", sts.detail(board));
			return "stdboard/stdmodify";
		}
			
		@RequestMapping(value = "/stdboard/stdmodify", method = RequestMethod.POST)
		public String stdmodifypost (BoardVO board, RedirectAttributes rttr) {
			sts.modify(board);
			rttr.addFlashAttribute("result", "modify success");
			return "redirect:/stdboard/stdlist";
		}
			
		// 게시판 삭제
		@RequestMapping(value = "/stdboard/stdremove", method = {RequestMethod.GET, RequestMethod.POST})
		public String stdremove (BoardVO board) {
			sts.remove(board);
			return "redirect:/stdboard/stdlist";
			}
			
			
		// 게시판 글쓰기 페이지(화면) 
		@RequestMapping(value = "/stdwrite", method = RequestMethod.GET)
		public String stdwrite() {
			return "stdboard/stdwrite";
		}

		// 게시판 글쓰기 페이지(insert가 이루어짐)
		// 모델 사용해 데이터 수집하기 -> 메모리 효율적 관리 가능
		@RequestMapping(value = "/stdwrite", method = RequestMethod.POST)
		public String stdwritePost (BoardVO board) {
			System.out.println(board);
				
			// 비즈니스 영역 연결한 후 BoardService
			sts.write(board);
			return "redirect:/stdboard/stdlist";
		}
			
		// 해당 게시물의 첨부파일의 데이터를 ajax로 전송
		@RequestMapping(value = "/stdattachlist", method = RequestMethod.GET)
		public ResponseEntity<ArrayList<AttachVO>> stduploadAjaxPost(int bno) {
				
			return new ResponseEntity<>(sts.attachlist(bno),HttpStatus.OK);
		}

		
		// Tip 게시판
		// 게시판 목록 리스트
		@RequestMapping(value = "/tipboard/tiplist", method = RequestMethod.GET)
		public String tiplist(Model model, CriteriaVO cri) {
			model.addAttribute("list", ts.list(cri));
			
			// list.jsp 실행 할 때 PageVO에 저장되어 있는 데이터를 가져와라.
			//                           생성자 호출(매개변수가 2개인 생성자)
			// board테이블(게시판테이블)에 전체 건수(select해서)를 아래에 190대신에 대입
			int total=ts.total(cri);
			//model.addAttribute("paging", new PageVO(cri, 190));
			model.addAttribute("paging", new PageVO(cri, total));
			
			return "tipboard/tiplist";
		}
		
		// 게시판 상세 페이지
		@RequestMapping(value = "/tipdetail", method = RequestMethod.GET)
		// public String detail(int bno){
		public String tipdetail (BoardVO board, Model model) {
			System.out.println(board);
			// 조회수 증가
			ts.count(board);
			// bs.detail(bno);
			model.addAttribute("detail", ts.detail(board));
			return "tipboard/tipdetail";
		}
			
		@RequestMapping(value = "/tipboard/tipmodify", method = RequestMethod.GET)
		public String tipmodifyget (BoardVO board, Model model) {
			System.out.println(board);
			model.addAttribute("detail", ts.detail(board));
			return "tipboard/tipmodify";
		}
			
		@RequestMapping(value = "/tipboard/tipmodify", method = RequestMethod.POST)
		public String tipmodifypost (BoardVO board, RedirectAttributes rttr) {
			ts.modify(board);
			rttr.addFlashAttribute("result", "modify success");
			return "redirect:/tipboard/tiplist";
		}
			
		// 게시판 삭제
		@RequestMapping(value = "/tipboard/tipremove", method = {RequestMethod.GET, RequestMethod.POST})
		public String tipremove (BoardVO board) {
			ts.remove(board);
			return "redirect:/tipboard/tiplist";
			}
			
			
		// 게시판 글쓰기 페이지(화면) 
		@RequestMapping(value = "/tipwrite", method = RequestMethod.GET)
		public String tipwrite() {
			return "tipboard/tipwrite";
		}

		// 게시판 글쓰기 페이지(insert가 이루어짐)
		// 모델 사용해 데이터 수집하기 -> 메모리 효율적 관리 가능
		@RequestMapping(value = "/tipwrite", method = RequestMethod.POST)
		public String tipwritePost (BoardVO board) {
			System.out.println(board);
				
			// 비즈니스 영역 연결한 후 BoardService
			ts.write(board);
			return "redirect:/tipboard/tiplist";
		}
			
		// 해당 게시물의 첨부파일의 데이터를 ajax로 전송
		@RequestMapping(value = "/tipattachlist", method = RequestMethod.GET)
		public ResponseEntity<ArrayList<AttachVO>> tipuploadAjaxPost(int bno) {
				
			return new ResponseEntity<>(ts.attachlist(bno),HttpStatus.OK);
		}
}
