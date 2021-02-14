import java.io.Serializable;

public class BoardVO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private int num;
	private String id;
	private String name;
	private String content;
	
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String toString(int i) {
		/*
		MemberVO vo = new MemberVO();
		vo.getId();*/
		String str = "";
		
		str = String.format("%5s %20s %15s",
				(i+1),name,id);
		
		return str;
	}

	public String toString() {
		
		String str= "";
		
		str = String.format("%d %s\n -> %s",
				num,name,content);
		
		return str;
	}
}
