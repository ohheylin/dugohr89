package ko.co.ksyVer1.board.service.impl;

import java.util.List;
import java.util.Map;

import ko.co.ksyVer1.board.service.bean.BoardBean;
import ko.co.ksyVer1.board.service.bean.BoardFileBean;

public interface BoardMapper {
	//게시판 목록조회
	List<BoardBean> getBoardList(Map<String, Object> map);
	//게시뭉 갯수 취득
	int getBoardCnt();
	//상세조회시 카운트 증가
	void updateCount(int num);
	//상세조회
	BoardBean getBoard(int num);
	//동영상 파일 조회
	List<BoardFileBean> getBoardFileList(int num);
	//게시물 수정
	void updArticle(BoardBean boardBean);
	//게시물 등록
	void addArticle(BoardBean boardBean);
	//동영상 파일 추가
	void addVideo(BoardFileBean video);
	//게시물 삭제
	void delArticle(int num);
		
}