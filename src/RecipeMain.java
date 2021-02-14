import java.util.Scanner;

public class RecipeMain {
	
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		
		int ch;
		
		try {
			while(true) {
				
				System.out.println("---------------------  레시  ---------------------");
				
				do {
					System.out.print("1. 레시피 검색 2. 게시판 3. 메인화면으로 돌아가기 => ");
					ch = sc.nextInt();
				}while(ch<1 || ch>3);
				
				switch(ch) {
				case 1:
					Recipe.main(args);
					break;
				case 2:
					BoardMain.main(args);
					break;
				default:
					Main.main(args);
				}
			}
			
		} catch (Exception e) {
			
		}
	}
}
