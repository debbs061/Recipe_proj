import java.util.Scanner;

public class BoardMain {
	
	public static String id ="";
	
	public BoardMain(String id) {
		
		this.id = id;
	}
	
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		
		BoardImpl ob = new BoardImpl();
		
		int ch;
		
		try {	
			while(true) {
				
				System.out.println("\n---------------------  게시판  ---------------------");
				
				ob.print();
				
				do {
					System.out.print("\n1.게시물 등록 2. 게시물 검색 3. 메인화면 ");
					ch = sc.nextInt();
				}while(ch<1 || ch>4);
				
				switch(ch) {
				case 1:
					ob.input(id);
					ob.save();
					break;
				case 2:
					ob.findBoard();
					break;
				default:
					Main.main(args);
				}
			}
			
		} catch (Exception e) {
			
		}
	}
}
