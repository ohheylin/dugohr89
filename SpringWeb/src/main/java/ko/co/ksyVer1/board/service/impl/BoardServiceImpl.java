package ko.co.ksyVer1.board.service.impl;

import java.util.List;
import java.util.Map;

import ko.co.ksyVer1.board.service.BoardService;
import ko.co.ksyVer1.board.service.bean.BoardBean;
import ko.co.ksyVer1.board.service.bean.BoardFileBean;
import ko.co.ksyVer1.board.service.bean.VBoardBean;
import ko.co.ksyVer1.board.service.bean.VBoardFileBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BoardServiceImpl implements BoardService {
	
	@Autowired
	private BoardMapper boardMapper;
	
	//게시판 목록조회
	@Override
	public List<BoardBean> getBoardList(Map<String, Object> map) {
		return boardMapper.getBoardList(map);
	}
	//게시뭉 갯수 취득
	@Override
	public int getBoardCnt() {
		return boardMapper.getBoardCnt();
	}
	//상세조회시 카운트 증가
	@Override
	public void updateCount(int num) {
		boardMapper.updateCount(num);
	}
	//상세조회
	@Override
	public BoardBean getBoard(int num) {
		return boardMapper.getBoard(num);
	}
	//동영상 파일 조회
	@Override
	public List<BoardFileBean> getBoardFileList(int num) {
		return boardMapper.getBoardFileList(num);
	}
	//게시물 수정
	@Override
	public void updArticle(BoardBean boardBean) {
		boardMapper.updArticle(boardBean);
	}
	//게시물 등록
	@Override
	public void addArticle(VBoardBean vboardBean) {
		BoardBean board = new BoardBean(vboardBean);
		boardMapper.addArticle(board);
		
		for (VBoardFileBean item:vboardBean.getVideoList())
		{
			if (!item.getFile().isEmpty()) {
				String filename = item.getFile().getOriginalFilename();
				int boardnum = board.getNum();
				BoardFileBean video = new BoardFileBean();
				video.setNum(boardnum);
				video.setFilename(filename);
				boardMapper.addVideo(video);	
			}
		}
	}
	//게시물 삭제
	@Override
	public void delArticle(int num) {
		boardMapper.delArticle(num);
	}

}
