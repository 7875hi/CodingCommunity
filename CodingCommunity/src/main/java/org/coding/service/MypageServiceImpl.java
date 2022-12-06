package org.coding.service;

import java.util.ArrayList;

import org.coding.mapper.MypageMapper;
import org.coding.mapper.ProfileMapper;
import org.coding.model.MemberVo;
import org.coding.model.MypageVO;
import org.coding.model.MyreplyListVO;
import org.coding.model.MywriteListVO;
import org.coding.model.ReplyVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MypageServiceImpl implements MypageService{

	@Autowired
	MypageMapper mm;
	
	@Autowired
	ProfileMapper pm;
	
	//내가 쓴 글을 출력하는 구현
	public ArrayList<MypageVO> mypage(String id)	{
		return mm.mypage(id);
	}
	
	public void mywrite(MemberVo member) {
		System.out.println("service="+member);
		mm.mywrite(member);
	
	}
		
	public void pwrite(MemberVo member) {
		
		mm.pwrite(member);
		
		member.getUpfilevo2().forEach(upfilevo2->{
			
			upfilevo2.setUuid(member.getId());
			
			pm.insert(upfilevo2);
			
			
		});
	
	}
	// 내가쓴 글 목록 
	public int total2(MywriteListVO mywritelist) {
		return mm.total2(mywritelist);
	}
	
	
	
	public ArrayList<MywriteListVO> mywrite2(MywriteListVO mywritelist){
		System.out.println(mywritelist);
		System.out.println("서비스="+mm.mywrite2(mywritelist));
		return mm.mywrite2(mywritelist);
	}
		
	
	public MemberVo member(MemberVo member) {
		return mm.member(member);
	}
	
	
	// 내가쓴댓글의글들
	public int retotal2(MyreplyListVO myreply) {
		return mm.retotal2(myreply);
	}
	
	
	
	public ArrayList<MyreplyListVO> myreply(MyreplyListVO myreply){
		System.out.println(myreply);
		System.out.println("서비스="+mm.myreply(myreply));
		return mm.myreply(myreply);
	}
	
	
}
