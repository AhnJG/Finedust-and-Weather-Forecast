package Weather;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class GetCurrentWeather {

	static String city;
	static String day;
	public static String current_weather = "";
	public static int humidity = 0, temperature = 0, temperatureMin = 0, temperatureMax = 0;
    
	GetCurrentWeather() 
	{
		try{
			String cityN = UserInfo.cityEng;
			String api_key = "1c16fab1af96910f38cd22a38ff4b7f0";
			String url = "https://api.openweathermap.org/data/2.5/weather?q="+cityN+"&mode=xml&units=metric&lang=en&appid="+api_key;
			
			DocumentBuilderFactory dbFactoty = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactoty.newDocumentBuilder();
			Document doc = dBuilder.parse(url);
			
			// root tag 
			doc.getDocumentElement().normalize();
			
			NodeList nList = doc.getElementsByTagName("city");
			
			for(int temp = 0; temp < nList.getLength(); temp++){
				Node nNode = nList.item(temp);
				if(nNode.getNodeType() == Node.ELEMENT_NODE){
					
					Element eElement = (Element) nNode;
					city = eElement.getAttribute("name");
				}
			}
			
			nList = doc.getElementsByTagName("temperature");
			Node nNode = nList.item(0);
			if(nNode.getNodeType() == Node.ELEMENT_NODE){
				
				Element eElement = (Element) nNode;
				temperature = (int)Double.parseDouble(eElement.getAttribute("value"));
				temperatureMin = (int)Double.parseDouble(eElement.getAttribute("min"));
				temperatureMax = (int)Double.parseDouble(eElement.getAttribute("max"));
			}	// for end
			
			nList = doc.getElementsByTagName("humidity");
			nNode = nList.item(0);
			if(nNode.getNodeType() == Node.ELEMENT_NODE){
				
				Element eElement = (Element) nNode;
				humidity = Integer.parseInt(eElement.getAttribute("value"));
			}	// for end
			
			nList = doc.getElementsByTagName("lastupdate");
			nNode = nList.item(0);
			if(nNode.getNodeType() == Node.ELEMENT_NODE){
				
				Element eElement = (Element) nNode;
				day = eElement.getAttribute("value");
				day = day.substring(0, 10) + " " + day.substring(11, 13);
				day = GetWeatherForecast.utcToLocaltime(day);
				day = day.substring(0, 10);
			}	// for end
			
			nList = doc.getElementsByTagName("weather");
			nNode = nList.item(0);
			if(nNode.getNodeType() == Node.ELEMENT_NODE){
				
				Element eElement = (Element) nNode;
				current_weather = eElement.getAttribute("value");
				if(current_weather.contains("rain"))
				{
					current_weather = "rain";
				}
				else if(current_weather.contains("sky"))
				{
					current_weather = "sky";
				}
				else 
				{
					current_weather = "clouds";
				}
			}
			
		} 
		catch (Exception e)
		{	
			e.printStackTrace();
		}	
	}	

}	