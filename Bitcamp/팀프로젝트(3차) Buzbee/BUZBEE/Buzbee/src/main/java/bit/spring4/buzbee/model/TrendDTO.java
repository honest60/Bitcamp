package bit.spring4.buzbee.model;

import lombok.Data;

@Data
public class TrendDTO {
	private String t_word;
	private long t_count;
	
	public TrendDTO() {}
	
	public TrendDTO(String t_word, long t_count) {
		this.t_word = t_word;
		this.t_count = t_count;
	}
}
