package ko.co.ksyVer1.board.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class VideoDownloadController {

	@RequestMapping(value="/board/download.html", method={RequestMethod.GET})
	@ResponseBody
	public ResponseEntity<byte[]> download(	@RequestParam String filename,
											@RequestParam int level,
											HttpServletResponse resp,
											HttpServletRequest req) throws IOException {
		//HTTP 헤더
		HttpHeaders headers = new HttpHeaders();
		String filepath = "";
		if (level==1) {
			filepath = req.getRealPath("/") + "/video";
		}
		else if (level==2) {
			filepath = req.getRealPath("/") + "/images";
		}
		File serverFile = new File(filepath, filename);
		
		InputStream inputStream = null;
		try {
			inputStream = new FileInputStream(serverFile);
		} catch (FileNotFoundException e) {
			String msg = "그런 파일은 없는데요...";
			headers.setContentType(MediaType.TEXT_PLAIN);
			return new ResponseEntity<byte[]>(msg.getBytes(), HttpStatus.NOT_FOUND); 
		}

		//resp.setContentType("video/mp4");
		filename = URLEncoder.encode(filename, "UTF8");
		resp.setContentType("application/octet-stream");
		resp.setHeader("Content-Disposition", "attachment; filename=\"" + filename + "\"");
      
		//아웃풋스트림 생성
		Long fileSize = serverFile.length();
		resp.setContentLength(fileSize.intValue());

		OutputStream outputStream = null;
		try {
			outputStream = resp.getOutputStream();
		} catch(IOException e) {
			String msg = "다운로드가 안됩니다...";
			headers.setContentType(MediaType.TEXT_PLAIN);
			return new ResponseEntity<byte[]>(msg.getBytes(), HttpStatus.NOT_FOUND); 
		}

		//다운로드
		byte[] buffer = new byte[1024];

		int read = 0;
		try {
			while ((read = inputStream.read(buffer)) != -1) {
				outputStream.write(buffer, 0, read);
			}

			//close를 해서 메모리 누수를 방지
			outputStream.flush();
			outputStream.close();
			inputStream.close();
		} catch (Exception e) {
			String msg = "다운로드가 안됩니다...";
			headers.setContentType(MediaType.TEXT_PLAIN);
			return new ResponseEntity<byte[]>(msg.getBytes(), HttpStatus.NOT_FOUND); 
		}

		String ok = "정상종료";
		return new ResponseEntity<byte[]>(ok.getBytes(), HttpStatus.OK); 
	}
}

























