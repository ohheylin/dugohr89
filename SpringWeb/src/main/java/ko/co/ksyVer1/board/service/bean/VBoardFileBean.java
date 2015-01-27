package ko.co.ksyVer1.board.service.bean;

import java.io.Serializable;

import org.springframework.web.multipart.MultipartFile;

public class VBoardFileBean implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private String name;
	
	private MultipartFile file;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public MultipartFile getFile() {
		return file;
	}
	public void setFile(MultipartFile file) {
		this.file = file;
	}

}
