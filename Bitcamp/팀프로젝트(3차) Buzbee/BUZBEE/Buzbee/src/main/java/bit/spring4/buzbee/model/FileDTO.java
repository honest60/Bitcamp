package bit.spring4.buzbee.model;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class FileDTO {
	MultipartFile uploadFile;
	long b_no;
	
	public FileDTO() {}
	
	public FileDTO(MultipartFile uploadFile, long b_no) {
		this.uploadFile = uploadFile;
		this.b_no = b_no;
	}
}
