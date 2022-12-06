package org.coding.service;

import java.util.ArrayList;
import org.coding.model.BoardVO;



public interface mainpageService {
	
	
	public ArrayList<BoardVO> mainlist(BoardVO board);
	
	public ArrayList<BoardVO> toptitle(BoardVO board);
	
	public ArrayList<BoardVO> shtoptitle(BoardVO board);
	
	public ArrayList<BoardVO> stdtoptitle(BoardVO board);
	
	
	
}
