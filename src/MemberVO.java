//ȸ������ �� ������

import java.io.Serializable;

public class MemberVO implements Serializable { 

	private static final long serialVersionUID = 1L;
	
	private String id;
	private String password;
	private String name;

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "이름: "+name+", 아이디: "+id+", 비밀번호:"+password;
	}
}
