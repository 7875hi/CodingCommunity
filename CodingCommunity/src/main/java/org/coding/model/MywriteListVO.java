package org.coding.model;

public class MywriteListVO {
	//boardvo
	// 게시판번호(bno)
	private int bno;
	// 아이디(id)
	private String id;
	// 제목(title)
	private String title;
	// 내용(content)
	private String content;
	// 작성일자(regdate)
	private String regdate;
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
	
	
	
	public int getBno() {
		return bno;
	}
	public void setBno(int bno) {
		this.bno = bno;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getRegdate() {
		return regdate;
	}
	public void setRegdate(String regdate) {
		this.regdate = regdate;
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
	@Override
	public String toString() {
		return "MywriteListVO [bno=" + bno + ", id=" + id + ", title=" + title + ", content=" + content + ", regdate="
				+ regdate + ", cnt=" + cnt + ", pageNum=" + pageNum + ", amount=" + amount + ", keyword=" + keyword
				+ ", type=" + type + "]";
	}
	
	public MywriteListVO() {
		this(1,10);
	}
	public MywriteListVO(int pageNum, int amount) {
		this.pageNum = pageNum;
		this.amount = amount;
	}
}
