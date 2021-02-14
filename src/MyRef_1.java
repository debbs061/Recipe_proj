import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class MyRef_1 {

	private List<MyRefVO> lists = null;
	private static int n;
	Scanner sc = new Scanner(System.in);

	File f;
	public MyRef_1(String id){
		File f = new File("C:\\Users\\stu\\Desktop\\"+ id +".txt");

		try {
			if(f.exists()) {
				FileInputStream fis = new FileInputStream(f);
				ObjectInputStream ois = new ObjectInputStream(fis);
				
				lists = (ArrayList<MyRefVO>)ois.readObject();
				n = lists.size();
				
				fis.close();
				ois.close();
			} else {
				n = 0;
			}
		} catch (Exception e) {
			
		}
	}
	
	public void input() {
		
		MyRefVO vo = new MyRefVO();
				
		System.out.print("재료명: ");
		vo.setName(sc.next());
		
		System.out.print("구입날짜[yyyyMMdd]: ");
		vo.setBuyDate(sc.next());
		
		System.out.print("수량: ");
		vo.setNum(sc.next());
		
		System.out.print("유통기한[yyyyMMdd]: ");
		vo.setEndDate(sc.next());
		
		if(lists==null) {
			lists = new ArrayList<>();
		}
		
		lists.add(vo);
		
		writeFile();	// 파일저장
	}
	
	
	public void writeFile() {	// 파일저장
		
		try {
			
			if(lists!=null) {
				
				FileOutputStream fos = new FileOutputStream(f);
				ObjectOutputStream oos = new ObjectOutputStream(fos);
				
				oos.writeObject(lists);
				
				fos.close();
				oos.close();
				System.out.println("재료를 저장했습니다. ");
			}
						
		} catch (Exception e) {
			
		}
	}
	
	public void delete() {	// 파일삭제
		
		System.out.print("몇 번째 재료를 삭제하시겠습니까?");
		n = sc.nextInt();
		
		lists.remove(n-1);
			
		System.out.println("재료를 삭제하였습니다.");
		
		writeFile();	// 파일저장
		
	}
	
	public void print() {	// 파일출력

		System.out.println("\n----------------  냉장고 재료 목록   ----------------");
		System.out.println(" 순서   재료명   구입날짜   수량   유통기");

		try {
			
			Iterator<MyRefVO> it = lists.iterator();

			int i = 0;
			
			while(it.hasNext()) {
				MyRefVO vo = it.next();
				System.out.println(vo.toString(i));
				i++;
			}
			
		} catch (Exception e) {
			System.out.println("입력된 데이터가 없습니다");
		}
	}
	
	public void compare() {	// 현재날짜와 유통기한 비교
		
		try {
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			
			Date today = new Date();
			
			for(int i=0;i<n;i++) {
				
				String start = sdf.format(today);
				String end = lists.get(i).getEndDate();
				
				Date beginDate = sdf.parse(start);
		        Date endDate = sdf.parse(end);
		        
		        long diff = endDate.getTime() - beginDate.getTime();
		        long diffDays = diff / (24 * 60 * 60 * 1000);
		        
				if(diffDays<=5 && diffDays>0) {
					System.out.println(lists.get(i).getName() + 
							"의 유통기한이 " + diffDays + "일 남았습니다.");
				}else if(diffDays==0) {
					System.out.println(lists.get(i).getName() + "의 유통기한이 오늘까지 입니다.");
				}else if(diffDays<0) {
					System.out.println(lists.get(i).getName() + "의 유통기한이 지났습니다.");
				}
			}
		} catch (Exception e) {
			
		}
	}
}
