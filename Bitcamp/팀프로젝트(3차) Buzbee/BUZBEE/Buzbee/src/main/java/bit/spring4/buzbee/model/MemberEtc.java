package bit.spring4.buzbee.model;

import lombok.Data;

@Data
public class MemberEtc {
	private long buzzes;
	private long follower;
	private long following;
	private long likes;
	
	public MemberEtc() {}

	public MemberEtc(long buzzes, long follower, long following, long likes) {
		this.buzzes = buzzes;
		this.follower = follower;
		this.following = following;
		this.likes = likes;
	}
}
