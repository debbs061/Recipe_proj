import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class Cart {

	//public static File f;
	static File f;
	List<MarketVO> lists = null;
	static String id = "";
	Scanner sc = new Scanner(System.in);

	Market m;

	@SuppressWarnings("unchecked")
	public Cart(String id) throws IOException {
		this.id = id;
		f = new File(".." + File.separator + "filefolder" + File.separator + "cart.txt");

		if(f.exists()) {

			try {
				FileInputStream fis = new FileInputStream(f);
				ObjectInputStream ois = new ObjectInputStream(fis);
				// 회원 정보 파일과 연결

				lists = new ArrayList<MarketVO>();
				lists = (List<MarketVO>) ois.readObject();
				
				fis.close();
				ois.close();
			} catch (Exception e) {
				// TODO: handle exception
			}

		} else {

			System.out.println("장바구니에 담긴 목록이 없습니다");
			System.out.println();
			Main ob = new Main(id);
			System.exit(0);
		}

		print();

	} // 생성자 끝

	// 파일이 존재하면
	public  void print()  throws IOException{
		System.out.println("\n<<<< "+id+"님의 장바구니 목록  >>>>");
		int i=0, tot=0;
		
		if(lists!=null) {
			
			Iterator<MarketVO> it = lists.iterator(); 
			while (it.hasNext()) {
				i++;
				MarketVO vo = it.next();
				System.out.print("["+i+"번째 상품] ");
				System.out.println(vo.getTitle());
				System.out.println(Integer.parseInt(vo.getLprice())+"원");
				tot += Integer.parseInt(vo.getLprice());
			}
		} 
		

		System.out.println("===========>  총 가격: "+tot+"원");
		
		System.out.print("1. 상품제거 2. 메인화면 ====> ");
		int su = sc.nextInt();
		
		if (su==1)
			delete();
		else if (su==2) {
			Main ob = new Main();
		}
//		else if (su==3)
//			m  = new Market(id);
		
	} //...end print()
	
	public void delete() throws IOException {	// 파일삭제
		
		System.out.print("몇 번째 상품을 삭제하시겠습니까: ");
		int n = sc.nextInt();
		
		lists.remove(n-1);
			
		writeFile();	
		
		print();
		
	}
	
	public void writeFile() {	// 파일저장
		
		try {
			
			if(lists!=null) {
				
				FileOutputStream fos = new FileOutputStream(f);
				ObjectOutputStream oos = new ObjectOutputStream(fos);
				
				oos.writeObject(lists);
				
				fos.close();
				oos.close();
				//System.out.println("debug] 장바구니 재저장 완료 ");
				
			}
						
		} catch (Exception e) {
			
		}
	}
}
