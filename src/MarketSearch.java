//���̹� �˻�
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.stream.XMLEventFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
/*
 * 네이버 쇼핑 랭킹순으로 보여줌 (top 10) - 최저가순
 */

public class MarketSearch {

	private static String id = "";
	private static String foods = "";

	List<MarketVO> lists = null;
	File f; 

	@SuppressWarnings("unchecked")
	public MarketSearch(String id) throws IOException {

		this.id = id; 

		f = new File(".." + File.separator + "filefolder" + File.separator + "cart.txt");

		if(f.exists()) {

			try {
				FileInputStream fis = new FileInputStream(f);
				ObjectInputStream ois = new ObjectInputStream(fis);

				lists = new ArrayList<MarketVO>();  
				lists = (ArrayList<MarketVO>)ois.readObject();

				fis.close();
				ois.close();
			} catch (Exception e) {
				// TODO: handle exception
			}
		}else {
			if(!f.getParentFile().exists()) {
				f.getParentFile().mkdirs();
				f.createNewFile();
			}
			lists = new ArrayList<>();
		}
	} //......end MarketSearch() 


	public void set(String food) {

		this.foods = food; 

	}

	public void Searching() {
		
		Scanner sc = new Scanner(System.in);

		String clientId = "VkXABDBOJykvMlDZslTj"; // application client id
		String clientSecret = "UZkgvBSz8k"; // application client secret key

		try {
			String text = URLEncoder.encode(foods, "UTF-8");
			String apiURL = "https://openapi.naver.com/v1/search/shop.xml?query="+ text; // 입력받은 재료명을 파라미터로 넘김

			// open api 호출
			URL url = new URL(apiURL);
			HttpURLConnection con = (HttpURLConnection)url.openConnection();
			con.setRequestMethod("GET");
			con.setRequestProperty("X-Naver-Client-Id", clientId);
			con.setRequestProperty("X-Naver-Client-Secret", clientSecret);

			DocumentBuilderFactory dom = DocumentBuilderFactory.newInstance();
			DocumentBuilder parser = dom.newDocumentBuilder();
			Document xmlDoc = null;

			// 정상적으로 GET 호출 됐는지 확인
			int responseCode = con.getResponseCode();
			BufferedReader br;

			if(responseCode==200) { // 정상 호출
				xmlDoc = parser.parse(con.getInputStream());
			} else {  				// 에러 발생
				br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
			}

			Element root = xmlDoc.getDocumentElement(); // 최상위 노드

			NodeList items =  //item node
					root.getElementsByTagName("item");

			List<MarketVO> imsi = new ArrayList<>(); // 결과 데이터 담을 리스트
			String str = "";
			String newText = ""; 

			for(int i=0; i<items.getLength(); i++) {

				Node node = items.item(i); // <item>[0]번부터 불러오기
				NodeList nodeList = node.getChildNodes();
				//NamedNodeMap map = node.getAttributes();

				MarketVO vo = new MarketVO();

				System.out.print("["+ (i+1) + "번 상품] ");

				for(int j=0; j<nodeList.getLength(); j++) { //<title>,<lprice>...불러오기
					Node e = nodeList.item(j);
					String value = e.getNodeName(); // <title> 나와야함


					if(value.equals("title")) {
						str = e.getTextContent();
						newText = str .replaceAll("<(/)?([a-zA-Z]*)(\\s[a-zA-Z]*=[^>]*)?(\\s)*(/)?>", ""); 
						//<br></br>����
						vo.setTitle(newText);
					}

					if(value.equals("link")) vo.setLink(e.getTextContent());
					if(value.equals("mallName")) vo.setMallName(e.getTextContent());
					if(value.equals("lprice")) vo.setLprice(e.getTextContent());

				}

				imsi.add(vo);
				System.out.println(vo.toString());
			}

			//*******장바구니로 잇기********
			System.out.print("▶︎︎▶︎︎▶︎︎▶︎︎▶︎︎ 장바구니에 담을 상품을 입력해주세요. ◀︎◀︎◀︎◀︎◀︎\n");
			int su= sc.nextInt(); // 1~10 사이만 입력 가능하게
			int n=0, tot=0;

			Iterator<MarketVO> it = imsi.iterator();  

			while (it.hasNext()) {
				n++;
				MarketVO vo = it.next();

				if(n==su) {
					writeFile(vo);
					//System.out.println("debug] 총 가격: "+Integer.parseInt(vo.getLprice()));
					Cart c = new Cart(id);
					break;
				}
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	} //....end main
	
	public void writeFile(MarketVO vo) {	// 파일 저장

		try {

			if(lists!=null) {
			    lists.add(vo);
			    
				FileOutputStream fos = new FileOutputStream(f);
				ObjectOutputStream oos = new ObjectOutputStream(fos);

				oos.writeObject(lists);

				fos.close();
				oos.close();

				System.out.println("장바구니에 담겨졌습니다.");

			}

		} catch (Exception e) {
			
		}
	} //...end writeFile
}