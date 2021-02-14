import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class RecipeImpl {
	
	static String search="";
	
	public RecipeImpl() {
	}
	
	public RecipeImpl(String s) {
		search = s;
		main(null);
	}
	
	public static void main(String[] args) {
		try{
			while(true){
				
				String url = "http://211.237.50.150:7080/openapi/"
						+"abee26697630ba27152152bec571b1ca6bc010152484b554cbe036e00b5d4c65/xml/Grid_20150827000000000228_1/1/5?"
						+"RECIPE_ID="+search;


				DocumentBuilderFactory f = DocumentBuilderFactory.newInstance();
				DocumentBuilder parser = f.newDocumentBuilder();
				Document xmlDoc = null; 
				
				xmlDoc = parser.parse(url);
				
				Element root = xmlDoc.getDocumentElement();
				
				NodeList n1 = root.getElementsByTagName("row");
				Node rowNode = n1.item(1);
				Element rowElement = (Element)rowNode;
				
				for (int i=0; i<n1.getLength(); i++) {
					Node bNode = n1.item(i);
					Element bElement = (Element)bNode;
					System.out.println(bElement.getElementsByTagName("COOKING_DC").item(0).getTextContent());
					
				}
				break;
			}

		} catch (Exception e){	
			e.printStackTrace();
		}
	}
}
