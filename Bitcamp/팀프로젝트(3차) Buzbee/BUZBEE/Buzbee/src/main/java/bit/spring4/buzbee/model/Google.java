package bit.spring4.buzbee.model;

import lombok.Data;

@Data
public class Google {
	private String query;
	private String hit;
	
	public Google() {}
	
	public Google(String query, String hit) {
		this.query = query;
		this.hit = hit;
	}
}
