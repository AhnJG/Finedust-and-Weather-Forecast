package Weather;

public class PrintWeather {
	PrintWeather()
	{
		System.out.println("도시 : " + UserInfo.cityKor);
		System.out.println("현재 온도 : " + GetCurrentWeather.temperature);
		System.out.println("최저 온도 : " + GetCurrentWeather.temperatureMin);
		System.out.println("최고 온도 : " + GetCurrentWeather.temperatureMax);
		System.out.println("습도 : " + GetCurrentWeather.humidity);
		System.out.println("미세먼지 : " + GetCurrentFineDustValue.pm10[0]);
		System.out.println("초 미세먼지 : " + GetCurrentFineDustValue.pm25[0]);
		System.out.println("미세먼지 예보 : " + GetFineDustForecast.fineDustForecast);
	}
}
