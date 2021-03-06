package ko.co.ksyVer1.board.service.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class VBoardBean implements Serializable {
	private int num;
	private String userid;
	private String title;
	private String content;
	private int count;
	private Date regdate;
	private String filename;
	private MultipartFile file;
	private int ref;
	private int re_step;
	private int re_level;
	private List<VBoardFileBean> videoList;
	
	public VBoardBean() {
		this.videoList = new ArrayList<VBoardFileBean>();
	}
	public VBoardBean(BoardBean boardBean) {
		this.num      = boardBean.getNum();
		this.userid   = boardBean.getUserid();
		this.title    = boardBean.getTitle();
		this.content  = boardBean.getContent();
		this.count    = boardBean.getCount();
		this.regdate  = boardBean.getRegdate();
		this.filename = boardBean.getFilename();
		this.ref      = boardBean.getRef();
		this.re_step  = boardBean.getRe_step();
		this.re_level = boardBean.getRe_level();
	}
	
	public List<VBoardFileBean> getVideoList() {
		return videoList;
	}
	public void setVideoList(List<VBoardFileBean> videoList) {
		this.videoList = videoList;
	}
	public MultipartFile getFile() {
		return file;
	}
	public void setFile(MultipartFile file) {
		this.file = file;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
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
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public int getRef() {
		return ref;
	}
	public void setRef(int ref) {
		this.ref = ref;
	}
	public int getRe_step() {
		return re_step;
	}
	public void setRe_step(int re_step) {
		this.re_step = re_step;
	}
	public int getRe_level() {
		return re_level;
	}
	public void setRe_level(int re_level) {
		this.re_level = re_level;
	}
}
