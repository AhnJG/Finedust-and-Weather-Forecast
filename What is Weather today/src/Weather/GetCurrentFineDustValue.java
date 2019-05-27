package Weather;

import java.net.URLEncoder;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class GetCurrentFineDustValue {
	public static String date[], city[];
	public static int pm10[] = {0}, pm25[] = {0};
	public static int pm10_avg = 0, pm25_avg = 0;
    
	GetCurrentFineDustValue() 
	{
		try 
		{	
			String cityN = UserInfo.cityKor;
			String cityEncoded = URLEncoder.encode(cityN, "UTF-8");
			String api_key = "wpff%2FcDQNw9j4OtCpvDcGXJbqHnPwGBh4iEJ0uB44%2Fn3Z%2BOJVWsKRwEUfbbmSi0HJTlk%2B5G35ZS4adoGuQJOnw%3D%3D";
			String url = "http://openapi.airkorea.or.kr/openapi/services/rest/ArpltnInforInqireSvc/getCtprvnMesureSidoLIst?serviceKey="+
			api_key+"&numOfRows=10&pageNo=1&sidoName="+cityEncoded+"&searchCondition=DAILY";
			
			DocumentBuilderFactory dbFactoty = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactoty.newDocumentBuilder();
			Document doc = dBuilder.parse(url);
			
			// root tag 
			doc.getDocumentElement().normalize();
			
			NodeList nList = doc.getElementsByTagName("item");
			int nLen = nList.getLength();
			
			date = new String[nLen];
			city = new String[nLen];
			pm10 = new int[nLen];
			pm25 = new int[nLen];
			
			for(int temp = 0; temp < nList.getLength(); temp++){
				Node nNode = nList.item(temp);
				if(nNode.getNodeType() == Node.ELEMENT_NODE){
					
					Element eElement = (Element) nNode;
					date[temp] = getTagValue("dataTime", eElement);
					date[temp] = date[temp].substring(0, 13); // 2019-05-16 19
					city[temp] = getTagValue("cityName", eElement);
					String tmp_pm10 = getTagValue("pm10Value", eElement);
					String tmp_pm25 = getTagValue("pm25Value", eElement);
					
					if(tmp_pm10.contentEquals("-"))
					{
						pm10[temp] = 0;
					}
					else
					{
						pm10[temp] = Integer.parseInt(tmp_pm10);
					}
					
					if(tmp_pm25.contentEquals("-"))
					{
						pm25[temp] = 0;
					}
					else
					{
						pm25[temp] = Integer.parseInt(tmp_pm25);
					}
					
				}	
			}	
			
			for(int i=0; i<pm10.length; i++)
			{
				pm10_avg += pm10[i];
				pm25_avg += pm25[i];
			}
			
			pm10_avg = (int)pm10_avg/pm10.length;
			pm25_avg = (int)pm25_avg/pm25.length;
			
		} 
		catch (Exception e)
		{	
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