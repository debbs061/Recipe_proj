import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Recipe_name {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		System.out.println();
		
		try{
			
			while(true){
				
				String url = "http://211.237.50.150:7080/openapi/abee26697630ba27152152bec571b1ca6bc010152484b554cbe036e00b5d4c65/"
								+"xml/Grid_20150827000000000226_1/1/30"; 
				
				DocumentBuilderFactory f = DocumentBuilderFactory.newInstance();
				DocumentBuilder parser = f.newDocumentBuilder();
				Document xmlDoc = null; 
				
				xmlDoc = parser.parse(url);
				
				Element root = xmlDoc.getDocumentElement();
				
				NodeList n1 = root.getElementsByTagName("row");
				Node rowNode = n1.item(0);
				Element rowElement = (Element)rowNode;
				
				for (int i=0; i<n1.getLength(); i++) {
					
					Node bNode = n1.item(i);
					Element bElement = (Element)bNode;
					System.out.print(bElement.getElementsByTagName("RECIPE_ID").item(0).getTextContent()+". ");
					System.out.println(bElement.getElementsByTagName("RECIPE_NM_KO").item(0).getTextContent());
				}
				break;
			}
			System.out.print("\n원하는 레시피 번호를 입력해주세요: ");
			String search = sc.next();
			
			System.out.println();
			RecipeImpl ob = new RecipeImpl(search);
			System.out.println();
			
		} catch (Exception e){	
			e.printStackTrace();
		}
	}
}
