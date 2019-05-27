package Weather;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class GetCountryTemp {
	String country_name[] = {"Seoul", "Daejeon", "Gwangju", "Busan"};
	public static int country_temp[] = {0, 0, 0, 0};
	public static String country_weather[] = {"", "", "", ""};
	
	public GetCountryTemp() {
		try{
			for(int i=0; i<country_name.length; i++)
			{
				String cityN = country_name[i];
				String api_key = "1c16fab1af96910f38cd22a38ff4b7f0";
				String url = "https://api.openweathermap.org/data/2.5/weather?q="+cityN+"&mode=xml&units=metric&lang=en&appid="+api_key;
				
				DocumentBuilderFactory dbFactoty = DocumentBuilderFactory.newInstance();
				DocumentBuilder dBuilder = dbFactoty.newDocumentBuilder();
				Document doc = dBuilder.parse(url);
				
				// root tag 
				doc.getDocumentElement().normalize();
				
				NodeList nList = doc.getElementsByTagName("temperature");
				Node nNode = nList.item(0);
				if(nNode.getNodeType() == Node.ELEMENT_NODE){
					
					Element eElement = (Element) nNode;
					country_temp[i] = (int)Double.parseDouble(eElement.getAttribute("value"));
				}	// for end
				
				nList = doc.getElementsByTagName("weather");
				nNode = nList.item(0);
				if(nNode.getNodeType() == Node.ELEMENT_NODE){
					
					Element eElement = (Element) nNode;
					country_weather[i] = eElement.getAttribute("value");
					if(country_weather[i].contains("rain"))
					{
						country_weather[i] = "rain";
					}
					else if(country_weather[i].contains("sky"))
					{
						country_weather[i] = "sky";
					}
					else 
					{
						country_weather[i] = "clouds";
					}
				}	// for end
			}
		} 
		catch (Exception e)
		{	
			e.printStackTrace();
		}	// try~catch end
	}
}
