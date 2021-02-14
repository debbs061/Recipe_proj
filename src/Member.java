//회원가입

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class Member extends MemberVO {

	private Main main;
	private String id;
	private String password;
	
	private static final long serialVersionUID = 1L;
	
	Scanner sc = new Scanner(System.in);
	List<MemberVO> lists = null;

	File f = new File(".." + File.separator + "filefolder" + File.separator + "member.txt");

	@SuppressWarnings("unchecked")
	public Member() throws IOException {
		if(f.exists()) {
			try {
				if(!f.getParentFile().exists()) {
					f.getParentFile().mkdirs();
				}
				if(!f.exists()) {
					lists = new ArrayList<MemberVO>();
					System.out.println("회원정보가 없습니다.");
				}else{
					FileInputStream fis = new FileInputStream(f);
					ObjectInputStream ois = new ObjectInputStream(fis);
					lists = (ArrayList<MemberVO>)ois.readObject();
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
		} else {
			if(!f.getParentFile().exists()) {
				f.getParentFile().mkdirs();
				f.createNewFile();
			}
		}
	}

	public void input() {
		
		MemberVO vo = new MemberVO();
		System.out.println("\n회원가입 페이지 입니다.");
	
		System.out.print("▶︎︎▶︎︎▶︎︎▶︎︎▶ 아이디를 입력해주세요. ◀︎◀︎◀︎◀︎◀︎");
		vo.setId(sc.next());
		
		if(!OverlapID(vo)){
			System.out.println("이미 존재하는 아이디입니다.");
			System.out.println("다른 아이디를 입력하세요.");
			return;
		}
		
		System.out.print("비밀번호: ");
		vo.setPassword(sc.next());
		System.out.print("이름: ");
		vo.setName(sc.next());

		if(lists==null) {
			lists = new ArrayList<MemberVO>();
		}
		lists.add(vo);
		//filestore(lists);
		
	}

	public void output() {
		
		Iterator<MemberVO> it = lists.iterator();
		try {
			while(it.hasNext()) {	
				MemberVO vo = it.next();	
			}	
		} catch (Exception e) {
		
		}
	}

	public void save() {
		try {
			if(lists!=null) {
				FileOutputStream fos = new FileOutputStream(f);
				ObjectOutputStream oos = new ObjectOutputStream(fos);

				oos.writeObject(lists);
				fos.close();
				oos.close();
			}
			// 회원가입 완료
		} catch (Exception e) {
			
		} 
		System.out.println();
	}
	
	public boolean OverlapID(MemberVO vo) {

		try {
			Iterator<MemberVO> it = lists.iterator();
			
			while(it.hasNext()) {	
				MemberVO checkid = it.next();
				if(checkid.getId().equals(vo.getId())) {
					return false;
				}
			}
			
		}catch (Exception e) {
			
		}
		return true;
	}
	
	public void login() {

		MemberVO vo = new MemberVO(); 

		System.out.print("아이디: ");
		id = sc.next();

		System.out.print("비밀번호: ");
		password = sc.next();
	}
	
	@SuppressWarnings("static-access")
	public void check() {
		
		int num=0;

		try {

			Iterator<MemberVO> it = lists.iterator(); 

			while (it.hasNext()) {
				MemberVO vo = it.next();
				
				if(  vo.getId().equals(id) && vo.getPassword().equals(password) ) {
					System.out.println("로그인 성공!\n");
					main = new Main(vo.getId());
					main.main(null);
					return;
				}else if ( vo.getId().equals(id) && !vo.getPassword().equals(password) ) {
					System.out.println("비밀번호가 일치하지 않습니다. ");
				}else {
					num ++;
				}
			}
			if(num== lists.size()) {
				System.out.println("존재하지 않는 아이디입니다.");
			}
			
		} catch (Exception e) {
			
		}
		System.out.println();
	}
}