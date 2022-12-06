package org.coding.controller;


import org.coding.model.BoardVO;
import org.coding.service.mainpageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainController {

	@Autowired
	mainpageService mps;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String Main (Model model, BoardVO board) { 
		//실시간 인기글
		model.addAttribute("mainlist", mps.mainlist(board));
				
		//조회수 top5 title
		model.addAttribute("toptitle", mps.toptitle(board));
		model.addAttribute("shtoptitle", mps.shtoptitle(board));
		model.addAttribute("stdtoptitle", mps.stdtoptitle(board));
	
		return "Main/mainhomepage";
	 }
}
