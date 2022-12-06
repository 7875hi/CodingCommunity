package org.coding.model;

public class MyreplyListVO {
	private int rno;  
	// 댓글번호
	private String reply;
	// 댓글내용
	private String id; 
	// id
	private String replaydate;
	// 댓글 작성일자
	private int bno; 
	// 게시판 번호
	
	// 조회수(cnt)
	private int cnt;
	//CriteriaVO
	private int pageNum;
	// 페이지번호
	private int amount;
	// 한 페이지당 게시물 갯수
	private String keyword; 
	// 키워드를 통해 검색
	private String type;  
	// 키워드 기준

	
	
	public MyreplyListVO() {
		this(1,10);
	}
	public int getRno() {
		return rno;
	}
	public void setRno(int rno) {
		this.rno = rno;
	}
	public String getReply() {
		return reply;
	}
	public void setReply(String reply) {
		this.reply = reply;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getReplaydate() {
		return replaydate;
	}
	public void setReplaydate(String replaydate) {
		this.replaydate = replaydate;
	}
	public int getBno() {
		return bno;
	}
	public void setBno(int bno) {
		this.bno = bno;
	}
	public int getCnt() {
		return cnt;
	}
	public void setCnt(int cnt) {
		this.cnt = cnt;
	}
	public int getPageNum() {
		return pageNum;
	}
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public MyreplyListVO(int pageNum, int amount) {
		this.pageNum = pageNum;
		this.amount = amount;
	}
}
