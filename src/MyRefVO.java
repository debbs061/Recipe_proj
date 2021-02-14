import java.io.Serializable;

public class MyRefVO implements Serializable{

	private String name;	//재료명
	private String buyDate;	//구입날짜
	private String num;		//수량
	private String endDate;	//유통기한
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBuyDate() {
		return buyDate;
	}
	public void setBuyDate(String buyDate) {
		this.buyDate = buyDate;
	}
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	
	public String toString(int i) {
		
		String str = null;
		
		str =  String.format("%4d\t%8s\t%10s\t%8s\t%10s",
				(i+1),name,buyDate,num,endDate);

		return str;
		
	}
}
