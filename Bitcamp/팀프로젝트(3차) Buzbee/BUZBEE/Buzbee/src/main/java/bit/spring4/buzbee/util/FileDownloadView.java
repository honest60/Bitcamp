package bit.spring4.buzbee.util;

import java.io.*;
import java.net.*;
import java.util.Map;
import javax.servlet.http.*;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.servlet.view.AbstractView;

public class FileDownloadView extends AbstractView {
	
	public FileDownloadView() {
		setContentType("application/download;charset=utf-8"); 
	}
	
	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		File file = (File)model.get("downloadFile");
		
		response.setContentType(getContentType());
		response.setContentLength((int)file.length());
		String value = "attachment; filename='" + URLEncoder.encode(file.getName(), "utf-8") + "'";
		response.setHeader("Content-Disposition", value);
		response.setHeader("Content-Transfer-Encoding", "binary");
		
		OutputStream os = response.getOutputStream();
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(file);
			FileCopyUtils.copy(fis, os);
			os.flush();
		} catch (IOException ie) {
			ie.printStackTrace();
		} finally {
			if(fis != null) fis.close();
			if(os != null) os.close();
		}
	}
}
