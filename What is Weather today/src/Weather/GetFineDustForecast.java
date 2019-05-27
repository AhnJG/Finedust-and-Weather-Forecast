package Weather;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class GetFineDustForecast {

    public static String fineDustForecast = "testtestt";

	GetFineDustForecast() 
	{
		try{
			String apiKey = "wpff%2FcDQNw9j4OtCpvDcGXJbqHnPwGBh4iEJ0uB44%2Fn3Z%2BOJVWsKRwEUfbbmSi0HJTlk%2B5G35ZS4adoGuQJOnw%3D%3D";
			String url = "http://openapi.airkorea.or.kr/openapi/services/rest/ArpltnInforInqireSvc/getMinuDustFrcstDspth?serviceKey="+apiKey+"&numOfRows=10&pageNo=1&searchDate="+GetCurrentWeather.day+"&InformCode=PM10";
			
			DocumentBuilderFactory dbFactoty = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactoty.newDocumentBuilder();
			Document doc = dBuilder.parse(url);
			
			// root tag 
			doc.getDocumentElement().normalize();
			
			NodeList nList = doc.getElementsByTagName("item");
			
			for(int temp = 0; temp < 1; temp++){
				Node nNode = nList.item(temp);
				if(nNode.getNodeType() == Node.ELEMENT_NODE){
					
					Element eElement = (Element) nNode;
					fineDustForecast = getTagValue("informCause", eElement);
				}
			}	
			
			
		} catch (Exception e){	
			e.printStackTrace();
		}
	}
	
		private static String getTagValue(String tag, Element eElement) {
		    NodeList nlList = eElement.getElementsByTagName(tag).item(0).getChildNodes();
		    Node nValue = (Node) nlList.item(0);
		    if(nValue == null) 
		        return null;
		    return nValue.getNodeValue();
		}
}
