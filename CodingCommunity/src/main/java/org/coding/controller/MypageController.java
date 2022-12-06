package org.coding.controller;

import javax.servlet.http.HttpSession;

import org.coding.model.CriteriaVO;
import org.coding.model.MemberVo;
import org.coding.model.MypageVO;
import org.coding.model.MyreplyListVO;
import org.coding.model.MywriteListVO;
import org.coding.model.Page2VO;
import org.coding.service.MypageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;




@Controller
public class MypageController {
		
	@Autowired
	MypageService ms;
	// 마이페이지 
	@RequestMapping(value = "/mypage", method = RequestMethod.GET)
	public String Mypage (MypageVO mypage, HttpSession session,Model model) { 
		MemberVo login= (MemberVo)session.getAttribute("login");
		//mypage.setId(id);
		
		System.out.println(login.getId());
		model.addAttribute("mypage",ms.mypage(login.getId()));	
				
		return "mypage/mypage";
	}
	// 내가쓴 글만 보기 mylist
	@RequestMapping(value = "/mypage/mylist", method = RequestMethod.GET)	
	public String directQue (MemberVo member,MywriteListVO mywritelist, Model model, CriteriaVO cri, HttpSession session) {
		MemberVo login= (MemberVo)session.getAttribute("login");
		//String id= session.getId();
		System.out.println("로그인된 아이디="+login.getId());
		
		member.setId(login.getId());
		mywritelist.setId(login.getId());
		
		model.addAttribute("mywrite2", ms.mywrite2(mywritelist));
		
		int total2 = ms.total2(mywritelist);
		model.addAttribute("paging", new Page2VO(cri, total2));
		
		return "/mypage/mylist";
		}
	
	// 내가쓴 글만 보기 myreply
	@RequestMapping(value = "/mypage/myreply", method = RequestMethod.GET)
	public String myreply (MemberVo member,MyreplyListVO myreply, Model model, CriteriaVO cri, HttpSession session) {
		MemberVo login= (MemberVo)session.getAttribute("login");
		
		System.out.println("로그인된 아이디="+login.getId());
		
		member.setId(login.getId());
		myreply.setId(login.getId());
		
		model.addAttribute("myreply", ms.myreply(myreply));
		
		int retotal2 = ms.retotal2(myreply);
		model.addAttribute("paging", new Page2VO(cri, retotal2));
		
		return "mypage/myreply";
	}
	
	// 마이페이지 개인정보 변경 GET
	@RequestMapping(value ="/mypage2", method = RequestMethod.GET)
		public String Mypage2 () {
		return "mypage/mypage2";
	
	}
	
	// 마이페이지 개인정보 변경 POST
	@RequestMapping(value ="/mypage2", method = RequestMethod.POST)
		public String UploadAjaxAction(MemberVo member, MultipartFile[] uploadFile) {
		System.out.println("controller="+member);
		ms.mywrite(member);
		return "redirect:/mypage";
	}


}