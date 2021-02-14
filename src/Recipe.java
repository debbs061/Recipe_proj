import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Recipe {

	public static void main(String[] args) {
		
		String clientId = "P5V_oBtrrW_INOUvSjQA"; // application client id
		String clientSecret = "W8Bi0EGcLK"; // application client secret key
		
		try {
			Scanner sc = new Scanner(System.in);
			
			System.out.print("▶︎▶︎▶︎▶︎▶︎ 보고싶은 레시피를 선택해주세요 ◀◀︎◀◀︎◀◀\n");
			String recipe = sc.nextLine();
			
			String text = URLEncoder.encode(recipe, "UTF-8");
			String apiURL = "https://openapi.naver.com/v1/search/encyc.xml?query="+ text;
			//String apiURL = "https://openapi.naver.com/v1/search/encyc.xml?query="+ text +"&display=1000";

			URL url = new URL(apiURL);
			HttpURLConnection con = (HttpURLConnection)url.openConnection();
			con.setRequestMethod("GET");
			con.setRequestProperty("X-Naver-Client-Id", clientId);
			con.setRequestProperty("X-Naver-Client-Secret", clientSecret);

			DocumentBuilderFactory doc =
					DocumentBuilderFactory.newInstance();
			DocumentBuilder parser = 
					doc.newDocumentBuilder();
			Document xmlDoc = null;
			
			int responseCode = con.getResponseCode();
			BufferedReader br;
			
			if(responseCode==200) {
				xmlDoc = parser.parse(con.getInputStream());
			} else {
				br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
			}
			
			Element root = xmlDoc.getDocumentElement();
			
			NodeList items = root.getElementsByTagName("item");
		   
			for(int i=0; i<1; i++) {
				
				Node node = items.item(i);
				NodeList nodeList = node.getChildNodes();
				
				RecipeVO vo = new RecipeVO();
				
				for(int j=0; j<nodeList.getLength(); j++) {
					Node e = nodeList.item(j);
					String value = e.getNodeName();
					
					if(value.equals("title")) {
						String str = e.getTextContent();
						String str1 = str .replaceAll("<(/)?([a-zA-Z]*)(\\s[a-zA-Z]*=[^>]*)?(\\s)*(/)?>", "");
						//System.out.println(newText);
						vo.setTitle(str1);
					}
					if(value.equals("description")) {
						String stri = e.getTextContent();
						String stri1 = stri.replaceAll("<(/)?([a-zA-Z]*)(\\s[a-zA-Z]*=[^>]*)?(\\s)*(/)?>", "");
						vo.setDescription(stri1);
					}
				}
				
				System.out.println("\n"+vo.toString());
			}
			
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
