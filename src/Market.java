import java.io.IOException;
import java.util.Scanner;

public class Market {
	
	public static String id="";
	
	public Market() throws IOException {
		main(null);
	}
	
	public Market(String id) throws IOException {
		this.id = id;
		main(null);
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
	   Scanner sc = new Scanner(System.in);
		
		System.out.println("\n-------------------  마켓코너 -------------------");
		System.out.println("네이버 최저가 랭킹순으로 보여드립니다 ");
		System.out.print("▶︎︎▶︎︎▶︎︎▶︎︎▶︎︎ 구매할 식재료를 입력해주세요 ◀︎◀︎◀︎◀︎◀︎︎︎\n");
	 	
		MarketSearch ob = new MarketSearch(id);
		ob.set(sc.next());
		ob.Searching();
		
	}
}
