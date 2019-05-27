package Weather;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class GetWeatherForecast {
	static String date[];
	public static int temperature[] = {0}, humidity[];
	static String cloudsDes[];
	static int clouds[];
	public static int temp_min[], temp_max[];
	public static int weekly_temp_min[] = {0, 0, 0, 0, 0}, weekly_temp_max[] = {0, 0, 0, 0, 0};
	public static String weatherDes[], weatherFiveDay[] = {"", "", "", "", ""}, weatherFiveTime[] = {"", "", "", "", ""};
	public static int current_temp_max = 0, current_temp_min = 0, temp_avg[] = {0, 0, 0, 0, 0};
	public static String dateFiveDay[] = {"", "", "", "", ""}, dateFiveTime[] = {"", "", "", "", ""};
	
	GetWeatherForecast() 
	{
		try{
			String city = UserInfo.cityEng;
			String api_key = "1c16fab1af96910f38cd22a38ff4b7f0";
			String url = "http://api.openweathermap.org/data/2.5/forecast?q="+city+"&mode=xml&units=metric&lang=en&appid="+api_key;
			
			DocumentBuilderFactory dbFactoty = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactoty.newDocumentBuilder();
			Document doc = dBuilder.parse(url);
			
			// root tag 
			doc.getDocumentElement().normalize();
			
			NodeList nList = doc.getElementsByTagName("time");
			
			date = new String[40];
			temperature = new int[40];
			humidity = new int[40];
			cloudsDes = new String[40];
			clouds = new int[40];
			weatherDes = new String[40];
			temp_min = new int[40];
			temp_max = new int[40];
			
			for(int temp = 0; temp < 40; temp++){
				Node nNode = nList.item(temp);
				if(nNode.getNodeType() == Node.ELEMENT_NODE){
					
					
					//get date
					Element eElement = (Element) nNode;
					date[temp] = eElement.getAttribute("from");
					date[temp] = date[temp].substring(0, 10) + " " + date[temp].substring(11, 13);
					date[temp] = utcToLocaltime(date[temp]);
					//2019-05-16 03
					
					NodeList childeren2 = eElement.getChildNodes();
					
					//get temp
					Node node2 = childeren2.item(4);
					if(node2.getNodeType() == Node.ELEMENT_NODE){
						Element ele2 = (Element)node2;
						temperature[temp] = (int)Double.parseDouble(ele2.getAttribute("value"));
						temp_min[temp] = (int)Double.parseDouble(ele2.getAttribute("min"));
						temp_max[temp] = (int)Double.parseDouble(ele2.getAttribute("max"));
					}
					
					//get humidity
					node2 = childeren2.item(6);
					if(node2.getNodeType() == Node.ELEMENT_NODE){
						Element ele2 = (Element)node2;
						humidity[temp] = Integer.parseInt(ele2.getAttribute("value"));
					}
					
					//get clouds percents
					node2 = childeren2.item(7);
					if(node2.getNodeType() == Node.ELEMENT_NODE){
						Element ele2 = (Element)node2;
						cloudsDes[temp] = ele2.getAttribute("value");
						clouds[temp] = Integer.parseInt(ele2.getAttribute("all"));
					}
					
					//get weatherDes
					node2 = childeren2.item(0);
					if(node2.getNodeType() == Node.ELEMENT_NODE){
						Element ele2 = (Element)node2;
						weatherDes[temp] = ele2.getAttribute("name");
					}
				}	// for end
			}	// if end
			
			current_temp_max = current_temp_min = GetWeatherForecast.temperature[0];
			for(int i=1; i<8; i++)
			{
				if(current_temp_max < temperature[i])
				{
					current_temp_max = temperature[i];
				}
				else if(current_temp_min > temperature[i])
				{
					current_temp_min = temperature[i];
				}
			}
			
			//get current_temp_max
			if(current_temp_max < GetCurrentWeather.temperatureMax)
			{
				current_temp_max = GetCurrentWeather.temperatureMax;
			}
			
			//get current_temp_min
			if(current_temp_min > GetCurrentWeather.temperatureMin)
			{
				current_temp_min = GetCurrentWeather.temperatureMin;
			}
			
			//get temp
			for(int i=0; i<40; i++)
			{
				temp_avg[(int)i/8] += temperature[i];
				
				if(i%8 == 7)
				{
					temp_avg[(int)i/8] = (int)temp_avg[(int)i/8]/8;
				}
			}
			
			//get weekly temp_max, temp_min
			for(int i=0; i<40; i++)
			{
				if(i%8 == 0)
				{
					weekly_temp_max[(int)i/8] = temp_max[i];
					weekly_temp_min[(int)i/8] = temp_min[i];
				}
				
				if(weekly_temp_max[(int)i/8] < temp_max[i])
				{
					weekly_temp_max[(int)i/8] = temp_max[i];
				}
				
				if(weekly_temp_min[(int)i/8] > temp_min[i])
				{
					weekly_temp_min[(int)i/8] = temp_min[i];
				}
			}
			
			//get weatherFiveDay
			for(int i=0; i<40; i++)
			{
				if(weatherDes[i].contains("rain"))
				{
					weatherFiveDay[(int)i/8] = "rain";
				}
				else if(weatherDes[i].contains("clouds") && !weatherFiveDay[(int)i/8].contains("rain"))
				{
					weatherFiveDay[(int)i/8] = "clouds";
				}
				else if(weatherDes[i].contains("sky") && !weatherFiveDay[(int)i/8].contains("clouds") && !weatherFiveDay[(int)i/8].contains("rain"))
				{
					weatherFiveDay[(int)i/8] = "sky";
				}
				else
				{
					weatherFiveDay[(int)i/8] = "clouds";
				}
			}
			
			//get WeatherFiveTime
			for(int i=1; i<6; i++)
			{
				weatherFiveTime[i-1] = weatherDes[i];
			}
			
			//get dateFiveDay
			for(int i=0; i<5; i++)
			{
				dateFiveDay[i] = date[i*8].substring(5, 10); // 2019-05-16 03
			}
			
			//get dateFiveTime
			for(int i=1 ;i<6; i++)
			{
				dateFiveTime[i-1] = date[i].substring(11);
			}
			
		} catch (Exception e){	
			e.printStackTrace();
		}	// try~catch end
	}	// main end
	
	public static String utcToLocaltime(String datetime) throws Exception 
	{
	    String locTime = null;
	    TimeZone tz = TimeZone.getDefault();
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH");
	     
	    try {
	        Date parseDate = sdf.parse(datetime);
	        long milliseconds = parseDate.getTime();
	        int offset = tz.getOffset(milliseconds);
	        locTime = sdf.format(milliseconds + offset);
	        locTime = locTime.replace("+0000", "");
	    } catch(Exception e) {
	        e.printStackTrace();
	        throw new Exception(e);
	    }
	     
	    return locTime;
	}
}	// class end