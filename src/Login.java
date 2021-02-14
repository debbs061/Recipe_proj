import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//�α��� ������ LoginView 

public class Login {
	
	Scanner sc = new Scanner(System.in);
	List<MemberVO> lists = null;

	File f = new File(".." + File.separator + "filefolder" + File.separator + "member.txt");
	
	@SuppressWarnings("unchecked")
	public Login() {
		
		if(f.exists()) {
			
			try {
				FileInputStream fis = new FileInputStream(f);
				ObjectInputStream ois = new ObjectInputStream(fis);
				
				lists = new ArrayList<MemberVO>();  
				lists = (List<MemberVO>) ois.readObject();
				
				fis.close();
				ois.close();
				
			} catch (Exception e) {
			
			}
			
		} else {
			lists = new ArrayList<MemberVO>();
			System.out.println("가입한 회원이 없습니다");
		}
	}
	
	public static void main(String[] args) throws IOException {
		
		Scanner sc = new Scanner(System.in);
		Member ob = new Member();
		int ch;

		try {	
			while(true) {
					
				do {
					System.out.println("▶▶︎︎▶︎︎▶︎︎▶︎︎▶︎︎ 레시피 월드에 오신걸 환영합니다 ◀︎◀︎◀︎◀︎◀◀︎");
					System.out.print("1. 로그인 2. 회원가입 3. 종료\n");
					ch = sc.nextInt();
				}while(ch<1 || ch>3);
				
				switch(ch) {
					
					case 1:
						ob.login(); ob.check(); break;
					case 2:
						ob.input(); ob.output(); ob.save(); break;
					default:
						System.out.println("\n프로그램을 종료합니다."); System.exit(0);
				}
			}
			
		} catch (Exception e) {
			
		}
		
	}
}
