import java.io.Serializable;

public class MarketVO implements Serializable{
	private String title;
	private String mallName;
	private  String link;
	private String lprice;
	private int tot;
	
	public String getLprice() {
		return lprice;
	}
	public void setLprice(String lprice) {
		this.lprice = lprice;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getMallName() {
		return mallName;
	}
	public void setMallName(String mallName) {
		this.mallName = mallName;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
     	return title + "\n링크: " + link + "\n가격: " +lprice+"원";
	}
	
	

}
