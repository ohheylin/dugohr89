package ko.co.ksyVer1.board.web;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import ko.co.ksyVer1.aop.Authorize;
import ko.co.ksyVer1.board.service.BoardService;
import ko.co.ksyVer1.board.service.bean.BoardBean;
import ko.co.ksyVer1.board.service.bean.BoardFileBean;
import ko.co.ksyVer1.board.service.bean.VBoardBean;
import ko.co.ksyVer1.board.service.bean.VBoardFileBean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class BoardController {
	
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	@Autowired
	private BoardService boardService;
	
	//페이지 싸이즈
	private int pageSize;
	//페이지 블록 싸이즈
	private int pageBlockSize;
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public void setPageBlockSize(int pageBlockSize) {
		this.pageBlockSize = pageBlockSize;
	}
	
	//게시판 목록 보기
	@RequestMapping(value="/board/boardList.html")
	public ModelAndView handleRequest(@RequestParam(value="pageNum", required=false) String pageNum) throws Exception {
//		logger.debug("boardList");
		
		BoardBean boardBean = new BoardBean();
		
		if (pageNum == null) pageNum = "1";
		int currentPage = Integer.parseInt(pageNum);
		int startRow = (currentPage-1) * pageSize + 1;
		int endRow = currentPage * pageSize;
		
		int count = boardService.getBoardCnt();
		int totalPage = (count-1) / pageSize + 1;
		int prevPage = ((currentPage-1) / pageBlockSize) * pageBlockSize;
		int nextPage = prevPage + (pageBlockSize + 1);
		nextPage = (nextPage > totalPage) ? totalPage+1 : nextPage;

		ModelAndView modelAndView = new ModelAndView();
		
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("start", startRow);
		map.put("end", endRow);

		List<BoardBean> list = this.boardService.getBoardList(map);
		modelAndView.addObject("list", list);
		modelAndView.addObject("count", count);
		modelAndView.addObject("currentPage", currentPage);
		modelAndView.addObject("totalPage", totalPage);
		modelAndView.addObject("prevPage", prevPage);
		modelAndView.addObject("nextPage", nextPage);
		modelAndView.addObject("nextPage", nextPage);
		modelAndView.addObject("pageSize", pageSize);
		
		modelAndView.setViewName("board/boardList");
		
		return modelAndView;
	}
	
	//게시판 상세보기
	@RequestMapping("/board/detail.html")
	public ModelAndView prosess(@RequestParam("num") int num) {
		
		ModelAndView modelAndView = new ModelAndView();
		
		boardService.updateCount(num);

		BoardBean boardBean = boardService.getBoard(num);
		modelAndView.addObject("boardBean", boardBean);
		
		List<BoardFileBean> boardFileBean = boardService.getBoardFileList(num);
		modelAndView.addObject("boardFileBean", boardFileBean);
		
		modelAndView.setViewName("board/boardSelect");
	
		return modelAndView;
	}
	
	//게시물 수정 조회
	@RequestMapping(value = "/board/update.html", method = RequestMethod.GET)
	@Authorize
	public ModelAndView form(@RequestParam("num") int num) {
		BoardBean boardBean = boardService.getBoard(num);
		return new ModelAndView("/board/updateBoardForm", "boardBean", boardBean);
	}

	//게시물 수정
	@RequestMapping(value = "/board/update.html", method = RequestMethod.POST)
	@Authorize
	public String submit(VBoardBean vboardBean, MultipartHttpServletRequest req)
			throws IllegalStateException, IOException {
		//파일 업로드
		MultipartFile file = vboardBean.getFile();
		
		//파일을 업로드 했다면
		if (file.getSize() > 0) {
			//파일이름 설정
			vboardBean.setFilename(file.getOriginalFilename());
			File imageFile = new File(req.getRealPath("/") + "/images", file.getOriginalFilename());
			file.transferTo(imageFile);
		}
		
		BoardBean boardBean = new BoardBean(vboardBean);
		boardService.updArticle(boardBean);
		
		return "redirect:/board/boardList.html";
	}
	
	//게시물 등록 초기화면
	@RequestMapping(value = "/board/insert.html", method = RequestMethod.GET)
	@Authorize
	public String form(Model model) {
		VBoardBean board = new VBoardBean();
		for (int i=0; i<2; i++) {
			board.getVideoList().add(new VBoardFileBean());
		}
		model.addAttribute("boardForm", board);
		return "/board/insertBoardForm";
	}
	
	//게시물 등록
	@RequestMapping(value = "/board/insert.html", method = RequestMethod.POST)
	@Authorize
	public String submit(	@Valid @ModelAttribute("boardForm")VBoardBean vboard, 
							MultipartHttpServletRequest req,
							BindingResult result,
							Model model)
			throws IllegalStateException, IOException {
		//파일 업로드
		MultipartFile file = vboard.getFile();
		
		//파일을 업로드 했다면
		if (file.getSize() > 0) {
			//파일이름 설정
			vboard.setFilename(file.getOriginalFilename());
			File imageFile = new File(req.getRealPath("/") + "/images", file.getOriginalFilename());
			file.transferTo(imageFile);
		}
		
		//멀티 파일업로드
		for (VBoardFileBean item : vboard.getVideoList()) {
			MultipartFile videoFile = item.getFile();
			//파일이 비었을 때 퍼리
			if (videoFile.isEmpty()) {
				continue;
			}
			
			String contentType = videoFile.getContentType();
			if (!contentType.equals("video/mp4")) {
				result.rejectValue("videoList", "videoList.invalidFile", null, "올바른 비디오 형식이 아닙니다.");
				model.addAttribute("boardForm", vboard);
				return "/board/insertBoardForm";
			}
			String filename = videoFile.getOriginalFilename();
			
			String filepath = req.getRealPath("/") + "/video";
			File uploadFile = new File(filepath, filename);
			videoFile.transferTo(uploadFile);
		}
		
		boardService.addArticle(vboard);
		
		return "redirect:/board/boardList.html";
	}
	
	//게시물 삭제
	@RequestMapping(value="/board/delete.html", method=RequestMethod.GET)
	@Authorize
	public ModelAndView process(@RequestParam("num") int num) {
		boardService.delArticle(num);
		return new ModelAndView("redirect:/board/boardList.html");
	}
	
}
