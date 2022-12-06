package org.coding.service;

import java.util.ArrayList;

import org.coding.model.MemberVo;
import org.coding.model.MypageVO;
import org.coding.model.MyreplyListVO;
import org.coding.model.MywriteListVO;
import org.coding.model.ReplyVO;

public interface MypageService {

	public ArrayList<MypageVO> mypage(String id);
	
	public void mywrite(MemberVo member);
	
	public void pwrite(MemberVo member);
	
	
	// 내가 쓴 글 목록
	public int total2(MywriteListVO mywritelist);
	
	public ArrayList<MywriteListVO> mywrite2(MywriteListVO mywritelist);

	public MemberVo member(MemberVo member);

	
	// 내가 쓴 댓글들의 글 목록
	public ArrayList<MyreplyListVO> myreply(MyreplyListVO myreply);
	
	public int retotal2(MyreplyListVO myreply);

	
	
	
	
		
}
