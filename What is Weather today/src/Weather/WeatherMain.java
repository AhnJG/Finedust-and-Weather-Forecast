package Weather;

import Gui.frame;

public class WeatherMain {

	public WeatherMain()
	{
		new UserInfo(frame.cityEng, frame.cityKor);
		new GetCountryTemp();
		new GetCurrentFineDustValue();
		new GetCurrentWeather();
		new GetWeatherForecast();
//		new GetFineDustForecast();
//		new PrintWeather();
	}
}
