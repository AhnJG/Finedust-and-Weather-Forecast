package Gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import Weather.GetCountryTemp;
import Weather.GetCurrentFineDustValue;
import Weather.GetCurrentWeather;
import Weather.GetFineDustForecast;
import Weather.GetWeatherForecast;
import Weather.WeatherMain;

public class frame extends JFrame {

	private JPanel contentPane;
	JPanel panel_select, panel_main, panel_dust, panel_weekly, panel_hourly, panel_country;
	
	JLabel label_dust_pm10_value, label_dust_pm25_value, label_temp_min_value, label_temp_max_value, label_temp_current_value, label_dust_city;
	JLabel label_dust_forecast, label_current, label_futrue, label_icon_dust;
	JLabel label_time0_temp_min, label_time1_temp_min, label_time2_temp_min, label_time3_temp_min, label_time4_temp_min, label_time0_date, label_time1_date, label_time2_date, label_time3_date, label_time4_date;
	
	JButton button_dust, button_hourly, button_weekly, button_country, button_menu, button_refresh, button_temp_max, button_dust_pm25, button_dust_pm10;
	JButton button_time0, button_time1, button_time2, button_time3, button_time4, button_day0, button_day1, button_day2, button_day3, button_day4;
	JButton button_country_gwangju, button_country_seoul, button_country_daejeon, button_country_busan;
	
	int len;
	CityNameParse cnp = new CityNameParse();
	public static String cityEng, cityKor;
	
	boolean fileExist = false;
	private JLabel label_day0_temp_min;
	private JLabel label_day1_temp_min;
	private JLabel label_day2_temp_min;
	private JLabel label_day3_temp_min;
	private JLabel label_day4_temp_min;
	private JLabel label_day0_date;
	private JLabel label_day1_date;
	private JLabel label_day2_date;
	private JLabel label_day3_date;
	private JLabel label_day4_date;
	private JLabel label_country_seoul_temp;
	private JLabel label_country_daejeon_temp;
	private JLabel label_country_gwangju_temp;
	private JLabel label_country_busan_temp;
	
	String img_icon_weather[] = {"/Gui/image/icon_rain.png", "/Gui/image/icon_cloud.png", "/Gui/image/icon_sun.png"}; 
	private JLabel label_dust_currentLocation;
	private JLabel label_hourly_currentLocation;
	private JLabel label_hourly_city;
	private JLabel label_weekly_currentLocation;
	private JLabel label_weekly_city;
	private JButton button_dust_icon_weather;
	private JLabel label_dust_time;
	private JLabel label_hourly_time;
	private JLabel label_weekly_time;
	private JLabel hourly_bg, img_select_bg;
	private JLabel label;
	private JLabel label_day0_temp_max;
	private JLabel label_day1_temp_max;
	private JLabel label_day2_temp_max;
	private JLabel label_day3_temp_max;
	private JLabel label_day4_temp_max;
	private JLabel weekly_bg;
	private JButton button_select_daegu;
	private JButton button_select_daejeon;
	private JButton button_select_busan;
	private JButton button_select_seoul;
	private JButton button_select_sejong;
	private JButton button_select_ulsan;
	private JButton button_select_incheon;
	private JButton button_select_jeju;
	
	File filePath = new File("src/Gui/conf.txt");
	
	public frame() {
		setResizable(false);
		setTitle("\uC77C\uAE30 \uC608\uBCF4");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 515);
		contentPane = new JPanel();
		contentPane.setForeground(new Color(0, 0, 0));
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		if(filePath.exists())
		{
			try
			{
				FileReader fr = new FileReader(filePath);
				BufferedReader br = new BufferedReader(fr);
				String line;
				while((line = br.readLine()) != null)
				{
					String tmp[] = line.split(",");
					cityEng = tmp[0];
					cityKor = tmp[1]; 
				}
				fr.close();
			}
			catch(Exception e)
			{
				System.out.println("Error" + e.getMessage());
			}
			
			fileExist = true;
		}
		
		panel_select = new JPanel();
		panel_select.setBounds(0, 0, 446, 488);
		contentPane.add(panel_select);
		panel_select.setLayout(null);
		
		JButton button_select_gwangju = new JButton(CityNameParse.cityKor.get(0));
		button_select_gwangju.addActionListener(new citySelected());
		button_select_gwangju.setForeground(Color.WHITE);
		button_select_gwangju.setFont(new Font("굴림", Font.BOLD, 15));
		button_select_gwangju.setBounds(180, 335, 70, 50);
		button_select_gwangju.setContentAreaFilled(false);
		button_select_gwangju.setBorderPainted(false);
		panel_select.add(button_select_gwangju);
		
		button_select_daegu = new JButton("대구");
		button_select_daegu.addActionListener(new citySelected());
		button_select_daegu.setForeground(Color.WHITE);
		button_select_daegu.setFont(new Font("굴림", Font.BOLD, 15));
		button_select_daegu.setBounds(287, 251, 70, 50);
		button_select_daegu.setContentAreaFilled(false);
		button_select_daegu.setBorderPainted(false);
		panel_select.add(button_select_daegu);
		
		button_select_daejeon = new JButton("대전");
		button_select_daejeon.addActionListener(new citySelected());
		button_select_daejeon.setForeground(Color.WHITE);
		button_select_daejeon.setFont(new Font("굴림", Font.BOLD, 15));
		button_select_daejeon.setBounds(195, 204, 70, 50);
		button_select_daejeon.setContentAreaFilled(false);
		button_select_daejeon.setBorderPainted(false);
		panel_select.add(button_select_daejeon);
		
		button_select_busan = new JButton("부산");
		button_select_busan.addActionListener(new citySelected());
		button_select_busan.setForeground(Color.WHITE);
		button_select_busan.setFont(new Font("굴림", Font.BOLD, 15));
		button_select_busan.setBounds(310, 350, 70, 50);
		button_select_busan.setContentAreaFilled(false);
		button_select_busan.setBorderPainted(false);
		panel_select.add(button_select_busan);
		
		button_select_seoul = new JButton("서울");
		button_select_seoul.addActionListener(new citySelected());
		button_select_seoul.setForeground(Color.WHITE);
		button_select_seoul.setFont(new Font("굴림", Font.BOLD, 15));
		button_select_seoul.setBounds(185, 64, 70, 50);
		button_select_seoul.setContentAreaFilled(false);
		button_select_seoul.setBorderPainted(false);
		panel_select.add(button_select_seoul);
		
		button_select_sejong = new JButton("세종");
		button_select_sejong.addActionListener(new citySelected());
		button_select_sejong.setForeground(Color.WHITE);
		button_select_sejong.setFont(new Font("굴림", Font.BOLD, 15));
		button_select_sejong.setBounds(175, 156, 70, 50);
		button_select_sejong.setContentAreaFilled(false);
		button_select_sejong.setBorderPainted(false);
		panel_select.add(button_select_sejong);
		
		button_select_ulsan = new JButton("울산");
		button_select_ulsan.addActionListener(new citySelected());
		button_select_ulsan.setForeground(Color.WHITE);
		button_select_ulsan.setFont(new Font("굴림", Font.BOLD, 15));
		button_select_ulsan.setBounds(330, 300, 70, 50);
		button_select_ulsan.setContentAreaFilled(false);
		button_select_ulsan.setBorderPainted(false);
		panel_select.add(button_select_ulsan);
		
		button_select_incheon = new JButton("인천");
		button_select_incheon.addActionListener(new citySelected());
		button_select_incheon.setForeground(Color.WHITE);
		button_select_incheon.setFont(new Font("굴림", Font.BOLD, 15));
		button_select_incheon.setBounds(125, 108, 70, 50);
		button_select_incheon.setContentAreaFilled(false);
		button_select_incheon.setBorderPainted(false);
		panel_select.add(button_select_incheon);
		
		button_select_jeju = new JButton("제주");
		button_select_jeju.setBounds(178, 440, 70, 50);
		panel_select.add(button_select_jeju);
		button_select_jeju.addActionListener(new citySelected());
		button_select_jeju.setForeground(Color.WHITE);
		button_select_jeju.setFont(new Font("굴림", Font.BOLD, 15));
		button_select_jeju.setContentAreaFilled(false);
		button_select_jeju.setBorderPainted(false);
		
		JLabel select_bg = new JLabel("");
		select_bg.setFont(new Font("굴림", Font.BOLD, 25));
		select_bg.setIcon(new ImageIcon(frame.class.getResource("/Gui/image/backGD_select.png")));
		select_bg.setBounds(0, 0, 435, 503);
		panel_select.add(select_bg);
		
		panel_main = new JPanel();
		panel_main.setBackground(new Color(255, 255, 255));
		panel_main.setBounds(0, 0, 446, 488);
		contentPane.add(panel_main);
		panel_main.setLayout(null);
		panel_main.setVisible(false);
		
		button_dust = new JButton("");
		button_dust.setBounds(19, 400, 58, 52);
		button_dust.setBorderPainted(false);
		button_dust.setContentAreaFilled(false);
		panel_main.add(button_dust);
		button_dust.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				page_dust();
			}
		});
		button_dust.setIcon(new ImageIcon(frame.class.getResource("/Gui/image/icon_dust_bad.png")));
		
		button_hourly = new JButton("");
		button_hourly.setBounds(130, 400, 58, 52);
		button_hourly.setBorderPainted(false);
		button_hourly.setContentAreaFilled(false);
		panel_main.add(button_hourly);
		button_hourly.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel_dust.setVisible(false);
				panel_hourly.setVisible(true);
				panel_weekly.setVisible(false);
				panel_country.setVisible(false);
				page_hourly();
			}
		});
		button_hourly.setIcon(new ImageIcon(frame.class.getResource("/Gui/image/icon_hourly.png")));
		
		button_weekly = new JButton("");
		button_weekly.setBounds(245, 400, 58, 52);
		button_weekly.setBorderPainted(false);
		button_weekly.setContentAreaFilled(false);
		panel_main.add(button_weekly);
		button_weekly.setIcon(new ImageIcon(frame.class.getResource("/Gui/image/icon_monthly.png")));
		
		button_country = new JButton("");
		button_country.setBounds(352, 400, 58, 52);
		button_country.setBorderPainted(false);
		button_country.setContentAreaFilled(false);
		panel_main.add(button_country);
		button_country.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel_dust.setVisible(false);
				panel_hourly.setVisible(false);
				panel_weekly.setVisible(false);
				panel_country.setVisible(true);
				page_country();
			}
		});
		button_country.setIcon(new ImageIcon(frame.class.getResource("/Gui/image/icon_country.png")));
		
		JLabel label_timeForecast = new JLabel("\uBBF8\uC138\uBA3C\uC9C0");
		label_timeForecast.setBounds(22, 450, 60, 20);
		panel_main.add(label_timeForecast);
		label_timeForecast.setForeground(new Color(255, 255, 255));
		label_timeForecast.setFont(new Font("굴림", Font.BOLD, 13));
		
		JLabel label_hourly = new JLabel("\uC2DC\uAC04\uBCC4 \uB0A0\uC528");
		label_hourly.setBounds(127, 450, 90, 20);
		panel_main.add(label_hourly);
		label_hourly.setForeground(new Color(255, 255, 255));
		label_hourly.setFont(new Font("굴림", Font.BOLD, 13));
		
		JLabel label_weekly = new JLabel("\uC8FC\uAC04 \uB0A0\uC528");
		label_weekly.setBounds(243, 450, 60, 20);
		panel_main.add(label_weekly);
		label_weekly.setForeground(new Color(255, 255, 255));
		label_weekly.setFont(new Font("굴림", Font.BOLD, 13));
		
		JLabel label_country = new JLabel("\uC804\uAD6D \uB0A0\uC528");
		label_country.setBounds(352, 450, 60, 20);
		panel_main.add(label_country);
		label_country.setForeground(new Color(255, 255, 255));
		label_country.setFont(new Font("굴림", Font.BOLD, 13));
		
		button_menu = new JButton("");
		button_menu.setBounds(10, 12, 60, 52);
		button_menu.setBorderPainted(false);
		button_menu.setContentAreaFilled(false);
		panel_main.add(button_menu);
		button_menu.setForeground(new Color(255, 255, 255));
		button_menu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel_select.setVisible(true);
				panel_main.setVisible(false);
				panel_dust.setVisible(false);
				panel_weekly.setVisible(false);
				panel_hourly.setVisible(false);
				panel_country.setVisible(false);
			}
		});
		button_menu.setIcon(new ImageIcon(frame.class.getResource("/Gui/image/icon_menu.png")));
		
		button_refresh = new JButton("");
		button_refresh.setBounds(380, 20, 40, 40);
		button_refresh.setBorderPainted(false);
		button_refresh.setContentAreaFilled(false);
		panel_main.add(button_refresh);
		button_refresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new WeatherMain();
				label_dust_time.setText(getCurrentTime());
				label_hourly_time.setText(getCurrentTime());
				label_weekly_time.setText(getCurrentTime());
			}
		});
		button_refresh.setIcon(new ImageIcon(frame.class.getResource("/Gui/image/icon_refresh.png")));
		
		panel_dust = new JPanel();
		panel_dust.setBackground(new Color(255, 255, 255));
		panel_dust.setBounds(0, 0, 446, 488);
		panel_main.add(panel_dust);
		panel_dust.setLayout(null);
		panel_dust.setVisible(false);
		
		label_current = new JLabel("Current");
		label_current.setFont(new Font("굴림", Font.BOLD, 13));
		label_current.setForeground(new Color(255, 255, 255));
		label_current.setBounds(0, 270, 446, 20);
		panel_dust.add(label_current);
		label_current.setBackground(new Color(0, 191, 255));
		
		JLabel label_dust_pm10 = new JLabel("\uBBF8\uC138\uBA3C\uC9C0");
		label_dust_pm10.setForeground(new Color(255, 255, 255));
		label_dust_pm10.setBounds(33, 295, 60, 25);
		panel_dust.add(label_dust_pm10);
		label_dust_pm10.setVerticalAlignment(SwingConstants.TOP);
		label_dust_pm10.setIcon(null);
		label_dust_pm10.setFont(new Font("굴림", Font.BOLD, 13));
		
		JLabel label_dust_pm25 = new JLabel("\uCD08\uBBF8\uC138\uBA3C\uC9C0");
		label_dust_pm25.setForeground(new Color(255, 255, 255));
		label_dust_pm25.setBounds(137, 295, 80, 25);
		panel_dust.add(label_dust_pm25);
		label_dust_pm25.setFont(new Font("굴림", Font.BOLD, 13));
		label_dust_pm25.setVerticalAlignment(SwingConstants.TOP);
		
		JLabel label_temp_min = new JLabel("\uCD5C\uC800\uAE30\uC628");
		label_temp_min.setForeground(new Color(255, 255, 255));
		label_temp_min.setBounds(253, 295, 60, 25);
		panel_dust.add(label_temp_min);
		label_temp_min.setFont(new Font("굴림", Font.BOLD, 13));
		label_temp_min.setVerticalAlignment(SwingConstants.TOP);
		
		JLabel label_temp_max = new JLabel("\uCD5C\uACE0\uAE30\uC628");
		label_temp_max.setForeground(new Color(255, 255, 255));
		label_temp_max.setBounds(362, 295, 60, 25);
		panel_dust.add(label_temp_max);
		label_temp_max.setFont(new Font("굴림", Font.BOLD, 13));
		label_temp_max.setVerticalAlignment(SwingConstants.TOP);
		
		JButton button_temp_min = new JButton("");
		button_temp_min.setBounds(253, 320, 42, 42);
		button_temp_min.setBorderPainted(false);
		button_temp_min.setContentAreaFilled(false);
		panel_dust.add(button_temp_min);
		button_temp_min.setIcon(new ImageIcon(frame.class.getResource("/Gui/image/icon_+.png")));
		
		button_temp_max = new JButton("");
		button_temp_max.setBounds(362, 320, 42, 42);
		button_temp_max.setBorderPainted(false);
		button_temp_max.setContentAreaFilled(false);
		panel_dust.add(button_temp_max);
		button_temp_max.setIcon(new ImageIcon(frame.class.getResource("/Gui/image/icon_+.png")));
		
		button_dust_pm25 = new JButton("");
		button_dust_pm25.setBounds(143, 315, 60, 59);
		button_dust_pm25.setContentAreaFilled(false);
		button_dust_pm25.setBorderPainted(false);
		panel_dust.add(button_dust_pm25);
		button_dust_pm25.setIcon(new ImageIcon(frame.class.getResource("/Gui/image/icon_+.png")));
		
		button_dust_pm10 = new JButton("");
		button_dust_pm10.setBounds(32, 315, 60, 59);
		button_dust_pm10.setContentAreaFilled(false);
		button_dust_pm10.setBorderPainted(false);
		panel_dust.add(button_dust_pm10);
		button_dust_pm10.setIcon(new ImageIcon(frame.class.getResource("/Gui/image/icon_+.png")));
		
		label_dust_pm10_value = new JLabel(GetCurrentFineDustValue.pm10[0] + " µg/m³");
		label_dust_pm10_value.setFont(new Font("굴림", Font.BOLD, 13));
		label_dust_pm10_value.setForeground(new Color(255, 255, 255));
		label_dust_pm10_value.setBounds(33, 380, 65, 15);
		panel_dust.add(label_dust_pm10_value);
		
		label_dust_pm25_value = new JLabel(GetCurrentFineDustValue.pm25[0] + " µg/m³");
		label_dust_pm25_value.setFont(new Font("굴림", Font.BOLD, 13));
		label_dust_pm25_value.setForeground(new Color(255, 255, 255));
		label_dust_pm25_value.setBounds(145, 380, 65, 15);
		panel_dust.add(label_dust_pm25_value);
		
		label_temp_min_value = new JLabel(GetCurrentWeather.temperatureMin + " ℃");
		label_temp_min_value.setFont(new Font("굴림", Font.BOLD, 13));
		label_temp_min_value.setForeground(new Color(255, 255, 255));
		label_temp_min_value.setBounds(260, 380, 60, 15);
		panel_dust.add(label_temp_min_value);
		
		label_temp_max_value = new JLabel(GetCurrentWeather.temperatureMax + " ℃");
		label_temp_max_value.setFont(new Font("굴림", Font.BOLD, 13));
		label_temp_max_value.setForeground(new Color(255, 255, 255));
		label_temp_max_value.setBounds(370, 380, 60, 15);
		panel_dust.add(label_temp_max_value);
		
		label_icon_dust = new JLabel("");
		label_icon_dust.setIcon(new ImageIcon(frame.class.getResource("/Gui/image/icon_dust.png")));
		label_icon_dust.setBounds(143, 90, 175, 170);
		panel_dust.add(label_icon_dust);
		
		button_dust_icon_weather = new JButton("");
		button_dust_icon_weather.setIcon(new ImageIcon(frame.class.getResource("/Gui/image/icon_sun.png")));
		button_dust_icon_weather.setBounds(10, 210, 50, 50);
		button_dust_icon_weather.setContentAreaFilled(false);
		button_dust_icon_weather.setBorderPainted(false);
		panel_dust.add(button_dust_icon_weather);
		
		label_temp_current_value = new JLabel("");
		label_temp_current_value.setFont(new Font("굴림", Font.BOLD, 20));
		label_temp_current_value.setForeground(new Color(255, 255, 255));
		label_temp_current_value.setBounds(65, 225, 75, 30);
		panel_dust.add(label_temp_current_value);
		
		label_dust_currentLocation = new JLabel("Current Location");
		label_dust_currentLocation.setForeground(new Color(255, 255, 255));
		label_dust_currentLocation.setFont(new Font("굴림", Font.BOLD, 16));
		label_dust_currentLocation.setBounds(155, 20, 140, 25);
		panel_dust.add(label_dust_currentLocation);
		
		label_dust_city = new JLabel("");
		label_dust_city.setHorizontalAlignment(SwingConstants.CENTER);
		label_dust_city.setFont(new Font("굴림", Font.BOLD, 16));
		label_dust_city.setForeground(new Color(255, 255, 255));
		label_dust_city.setBounds(185, 40, 70, 25);
		panel_dust.add(label_dust_city);
		
		label_dust_time = new JLabel("2019-05-20 02:21");
		label_dust_time.setForeground(new Color(255, 255, 255));
		label_dust_time.setFont(new Font("굴림", Font.BOLD, 16));
		label_dust_time.setBounds(155, 60, 155, 25);
		panel_dust.add(label_dust_time);
		
		label_dust_forecast = new JLabel("");
		label_dust_forecast.setHorizontalAlignment(SwingConstants.CENTER);
		label_dust_forecast.setFont(new Font("굴림", Font.BOLD, 13));
		label_dust_forecast.setForeground(new Color(255, 255, 255));
		label_dust_forecast.setBounds(70, 220, 300, 30);
		panel_dust.add(label_dust_forecast);
		
		panel_hourly = new JPanel();
		panel_hourly.setBackground(new Color(102, 153, 204));
		panel_hourly.setBounds(0, 0, 446, 488);
		panel_main.add(panel_hourly);
		panel_hourly.setLayout(null);
		
		label_hourly_currentLocation = new JLabel("Current Location");
		label_hourly_currentLocation.setForeground(Color.WHITE);
		label_hourly_currentLocation.setFont(new Font("굴림", Font.BOLD, 16));
		label_hourly_currentLocation.setBounds(155, 20, 140, 25);
		panel_hourly.add(label_hourly_currentLocation);
		
		label_hourly_city = new JLabel("");
		label_hourly_city.setHorizontalAlignment(SwingConstants.CENTER);
		label_hourly_city.setForeground(Color.WHITE);
		label_hourly_city.setFont(new Font("굴림", Font.BOLD, 16));
		label_hourly_city.setBounds(185, 40, 70, 25);
		panel_hourly.add(label_hourly_city);
		
		label_hourly_time = new JLabel("2019-05-20 02:21");
		label_hourly_time.setForeground(Color.WHITE);
		label_hourly_time.setFont(new Font("굴림", Font.BOLD, 16));
		label_hourly_time.setBounds(155, 60, 155, 25);
		panel_hourly.add(label_hourly_time);
		
		button_time0 = new JButton("");
		button_time0.setBounds(30, 200, 60, 60);
		button_time0.setContentAreaFilled(false);
		button_time0.setBorderPainted(false);
		panel_hourly.add(button_time0);
		
		button_time1 = new JButton("");
		button_time1.setBounds(110, 200, 60, 60);
		button_time1.setContentAreaFilled(false);
		button_time1.setBorderPainted(false);
		panel_hourly.add(button_time1);
		
		button_time2 = new JButton("");
		button_time2.setBounds(190, 200, 60, 60);
		button_time2.setContentAreaFilled(false);
		button_time2.setBorderPainted(false);
		panel_hourly.add(button_time2);
		
		button_time3 = new JButton("");
		button_time3.setBounds(270, 200, 60, 60);
		button_time3.setContentAreaFilled(false);
		button_time3.setBorderPainted(false);
		panel_hourly.add(button_time3);
		
		button_time4 = new JButton("");
		button_time4.setBounds(350, 200, 60, 60);
		button_time4.setContentAreaFilled(false);
		button_time4.setBorderPainted(false);
		panel_hourly.add(button_time4);
		
		label_time0_temp_min = new JLabel(GetWeatherForecast.weatherFiveTime[0]);
		label_time0_temp_min.setBounds(35, 270, 60, 25);
		label_time0_temp_min.setForeground(Color.WHITE);
		label_time0_temp_min.setFont(new Font("굴림", Font.BOLD, 20));
		panel_hourly.add(label_time0_temp_min);
		
		label_time1_temp_min = new JLabel(GetWeatherForecast.weatherFiveTime[1]);
		label_time1_temp_min.setBounds(115, 270, 60, 25);
		label_time1_temp_min.setForeground(Color.WHITE);
		label_time1_temp_min.setFont(new Font("굴림", Font.BOLD, 20));
		panel_hourly.add(label_time1_temp_min);
		
		label_time2_temp_min = new JLabel(GetWeatherForecast.weatherFiveTime[2]);
		label_time2_temp_min.setBounds(195, 270, 60, 25);
		label_time2_temp_min.setForeground(Color.WHITE);
		label_time2_temp_min.setFont(new Font("굴림", Font.BOLD, 20));
		panel_hourly.add(label_time2_temp_min);
		
		label_time3_temp_min = new JLabel(GetWeatherForecast.weatherFiveTime[3]);
		label_time3_temp_min.setBounds(275, 270, 60, 25);
		label_time3_temp_min.setForeground(Color.WHITE);
		label_time3_temp_min.setFont(new Font("굴림", Font.BOLD, 20));
		panel_hourly.add(label_time3_temp_min);
		
		label_time4_temp_min = new JLabel(GetWeatherForecast.weatherFiveTime[4]);
		label_time4_temp_min.setBounds(355, 270, 60, 25);
		label_time4_temp_min.setForeground(Color.WHITE);
		label_time4_temp_min.setFont(new Font("굴림", Font.BOLD, 20));
		panel_hourly.add(label_time4_temp_min);
		
		label_time0_date = new JLabel(GetWeatherForecast.dateFiveTime[0]);
		label_time0_date.setBounds(30, 170, 60, 25);
		label_time0_date.setForeground(Color.WHITE);
		label_time0_date.setFont(new Font("굴림", Font.BOLD, 20));
		panel_hourly.add(label_time0_date);
		
		label_time1_date = new JLabel(GetWeatherForecast.dateFiveTime[1]);
		label_time1_date.setBounds(110, 170, 60, 25);
		label_time1_date.setForeground(Color.WHITE);
		label_time1_date.setFont(new Font("굴림", Font.BOLD, 20));
		panel_hourly.add(label_time1_date);
		
		label_time2_date = new JLabel(GetWeatherForecast.dateFiveTime[2]);
		label_time2_date.setBounds(190, 170, 60, 25);
		label_time2_date.setForeground(Color.WHITE);
		label_time2_date.setFont(new Font("굴림", Font.BOLD, 20));
		panel_hourly.add(label_time2_date);
		
		label_time3_date = new JLabel(GetWeatherForecast.dateFiveTime[3]);
		label_time3_date.setBounds(270, 170, 60, 25);
		label_time3_date.setForeground(Color.WHITE);
		label_time3_date.setFont(new Font("굴림", Font.BOLD, 20));
		panel_hourly.add(label_time3_date);
		
		label_time4_date = new JLabel(GetWeatherForecast.dateFiveTime[4]);
		label_time4_date.setBounds(350, 170, 60, 25);
		label_time4_date.setForeground(Color.WHITE);
		label_time4_date.setFont(new Font("굴림", Font.BOLD, 20));
		panel_hourly.add(label_time4_date);
		
		hourly_bg = new JLabel("");
		hourly_bg.setIcon(new ImageIcon(frame.class.getResource("/Gui/image/backGD.png")));
		hourly_bg.setBounds(0, 0, 435, 503);
		panel_hourly.add(hourly_bg);
		
		panel_weekly = new JPanel();
		panel_weekly.setBackground(new Color(51, 51, 255));
		panel_weekly.setBounds(0, 0, 446, 488);
		panel_main.add(panel_weekly);
		panel_weekly.setLayout(null);
		
		label_weekly_currentLocation = new JLabel("Current Location");
		label_weekly_currentLocation.setForeground(Color.WHITE);
		label_weekly_currentLocation.setFont(new Font("굴림", Font.BOLD, 16));
		label_weekly_currentLocation.setBounds(155, 20, 140, 25);
		panel_weekly.add(label_weekly_currentLocation);
		
		label_weekly_city = new JLabel("");
		label_weekly_city.setHorizontalAlignment(SwingConstants.CENTER);
		label_weekly_city.setForeground(Color.WHITE);
		label_weekly_city.setFont(new Font("굴림", Font.BOLD, 16));
		label_weekly_city.setBounds(185, 40, 70, 25);
		panel_weekly.add(label_weekly_city);
		
		label_weekly_time = new JLabel("2019-05-20 02:21");
		label_weekly_time.setForeground(Color.WHITE);
		label_weekly_time.setFont(new Font("굴림", Font.BOLD, 16));
		label_weekly_time.setBounds(155, 60, 155, 25);
		panel_weekly.add(label_weekly_time);
		
		button_day0 = new JButton("");
		button_day0.setIcon(new ImageIcon(frame.class.getResource("/Gui/image/icon_+.png")));
		button_day0.setBounds(30, 200, 60, 60);
		button_day0.setContentAreaFilled(false);
		button_day0.setBorderPainted(false);
		panel_weekly.add(button_day0);
		
		button_day1 = new JButton("");
		button_day1.setIcon(new ImageIcon(frame.class.getResource("/Gui/image/icon_+.png")));
		button_day1.setBounds(110, 200, 60, 60);
		button_day1.setContentAreaFilled(false);
		button_day1.setBorderPainted(false);
		panel_weekly.add(button_day1);
		
		button_day2 = new JButton("");
		button_day2.setIcon(new ImageIcon(frame.class.getResource("/Gui/image/icon_+.png")));
		button_day2.setBounds(190, 200, 60, 60);
		button_day2.setContentAreaFilled(false);
		button_day2.setBorderPainted(false);
		panel_weekly.add(button_day2);
		
		button_day3 = new JButton("");
		button_day3.setIcon(new ImageIcon(frame.class.getResource("/Gui/image/icon_+.png")));
		button_day3.setBounds(270, 200, 60, 60);
		button_day3.setContentAreaFilled(false);
		button_day3.setBorderPainted(false);
		panel_weekly.add(button_day3);
		
		button_day4 = new JButton("");
		button_day4.setIcon(new ImageIcon(frame.class.getResource("/Gui/image/icon_+.png")));
		button_day4.setBounds(350, 200, 60, 60);
		button_day4.setContentAreaFilled(false);
		button_day4.setBorderPainted(false);
		panel_weekly.add(button_day4);
		
		label_day0_temp_min = new JLabel("19 ℃");
		label_day0_temp_min.setFont(new Font("굴림", Font.BOLD, 20));
		label_day0_temp_min.setForeground(new Color(255, 255, 255));
		label_day0_temp_min.setBounds(35, 300, 60, 25);
		panel_weekly.add(label_day0_temp_min);
		
		label_day1_temp_min = new JLabel("19 ℃");
		label_day1_temp_min.setForeground(Color.WHITE);
		label_day1_temp_min.setFont(new Font("굴림", Font.BOLD, 20));
		label_day1_temp_min.setBounds(115, 300, 60, 25);
		panel_weekly.add(label_day1_temp_min);
		
		label_day2_temp_min = new JLabel("19 ℃");
		label_day2_temp_min.setForeground(Color.WHITE);
		label_day2_temp_min.setFont(new Font("굴림", Font.BOLD, 20));
		label_day2_temp_min.setBounds(195, 300, 60, 25);
		panel_weekly.add(label_day2_temp_min);
		
		label_day3_temp_min = new JLabel("19 ℃");
		label_day3_temp_min.setForeground(Color.WHITE);
		label_day3_temp_min.setFont(new Font("굴림", Font.BOLD, 20));
		label_day3_temp_min.setBounds(275, 300, 60, 25);
		panel_weekly.add(label_day3_temp_min);
		
		label_day4_temp_min = new JLabel("19 ℃");
		label_day4_temp_min.setForeground(Color.WHITE);
		label_day4_temp_min.setFont(new Font("굴림", Font.BOLD, 20));
		label_day4_temp_min.setBounds(355, 300, 60, 25);
		panel_weekly.add(label_day4_temp_min);
		
		label_day0_temp_max = new JLabel("1");
		label_day0_temp_max.setForeground(Color.WHITE);
		label_day0_temp_max.setFont(new Font("굴림", Font.BOLD, 20));
		label_day0_temp_max.setBounds(35, 270, 60, 25);
		panel_weekly.add(label_day0_temp_max);
		
		label_day1_temp_max = new JLabel("2");
		label_day1_temp_max.setForeground(Color.WHITE);
		label_day1_temp_max.setFont(new Font("굴림", Font.BOLD, 20));
		label_day1_temp_max.setBounds(115, 270, 60, 25);
		panel_weekly.add(label_day1_temp_max);
		
		label_day2_temp_max = new JLabel("3");
		label_day2_temp_max.setForeground(Color.WHITE);
		label_day2_temp_max.setFont(new Font("굴림", Font.BOLD, 20));
		label_day2_temp_max.setBounds(195, 270, 60, 25);
		panel_weekly.add(label_day2_temp_max);
		
		label_day3_temp_max = new JLabel("4");
		label_day3_temp_max.setForeground(Color.WHITE);
		label_day3_temp_max.setFont(new Font("굴림", Font.BOLD, 20));
		label_day3_temp_max.setBounds(275, 270, 60, 25);
		panel_weekly.add(label_day3_temp_max);
		
		label_day4_temp_max = new JLabel("5");
		label_day4_temp_max.setForeground(Color.WHITE);
		label_day4_temp_max.setFont(new Font("굴림", Font.BOLD, 20));
		label_day4_temp_max.setBounds(355, 270, 60, 25);
		panel_weekly.add(label_day4_temp_max);
		
		label_day0_date = new JLabel("05 18");
		label_day0_date.setForeground(Color.WHITE);
		label_day0_date.setFont(new Font("굴림", Font.BOLD, 17));
		label_day0_date.setBounds(30, 180, 60, 25);
		panel_weekly.add(label_day0_date);
		
		label_day1_date = new JLabel("05 18");
		label_day1_date.setForeground(Color.WHITE);
		label_day1_date.setFont(new Font("굴림", Font.BOLD, 17));
		label_day1_date.setBounds(110, 180, 60, 25);
		panel_weekly.add(label_day1_date);
		
		label_day2_date = new JLabel("05 18");
		label_day2_date.setForeground(Color.WHITE);
		label_day2_date.setFont(new Font("굴림", Font.BOLD, 17));
		label_day2_date.setBounds(190, 180, 60, 25);
		panel_weekly.add(label_day2_date);
		
		label_day3_date = new JLabel("05 18");
		label_day3_date.setForeground(Color.WHITE);
		label_day3_date.setFont(new Font("굴림", Font.BOLD, 17));
		label_day3_date.setBounds(270, 180, 60, 25);
		panel_weekly.add(label_day3_date);
		
		label_day4_date = new JLabel("05 18");
		label_day4_date.setForeground(Color.WHITE);
		label_day4_date.setFont(new Font("굴림", Font.BOLD, 17));
		label_day4_date.setBounds(350, 180, 60, 25);
		panel_weekly.add(label_day4_date);
		
		weekly_bg = new JLabel("");
		weekly_bg.setIcon(new ImageIcon(frame.class.getResource("/Gui/image/backGD.png")));
		weekly_bg.setBounds(0, 0, 435, 503);
		panel_weekly.add(weekly_bg);
		
		panel_country = new JPanel();
		panel_country.setBackground(new Color(51, 153, 255));
		panel_country.setBounds(0, 0, 446, 488);
		panel_main.add(panel_country);
		panel_country.setLayout(null);
		
		button_country_gwangju = new JButton("");
		button_country_gwangju.setIcon(new ImageIcon(frame.class.getResource("/Gui/image/icon_sun.png")));
		button_country_gwangju.setBounds(165, 305, 50, 50);
		button_country_gwangju.setContentAreaFilled(false);
		button_country_gwangju.setBorderPainted(false);
		panel_country.add(button_country_gwangju);
		
		JLabel label_country_gwangju = new JLabel("광주");
		label_country_gwangju.setForeground(new Color(255, 255, 255));
		label_country_gwangju.setFont(new Font("굴림", Font.BOLD, 18));
		label_country_gwangju.setBounds(120, 315, 50, 25);
		panel_country.add(label_country_gwangju);
		
		label_country_gwangju_temp = new JLabel("광주");
		label_country_gwangju_temp.setForeground(new Color(255, 255, 255));
		label_country_gwangju_temp.setFont(new Font("굴림", Font.BOLD, 18));
		label_country_gwangju_temp.setBounds(120, 335, 50, 25);
		panel_country.add(label_country_gwangju_temp);
		
		button_country_busan = new JButton("");
		button_country_busan.setIcon(new ImageIcon(frame.class.getResource("/Gui/image/icon_sun.png")));
		button_country_busan.setBounds(340, 295, 50, 50);
		button_country_busan.setContentAreaFilled(false);
		button_country_busan.setBorderPainted(false);
		panel_country.add(button_country_busan);
		
		JLabel label_country_busan = new JLabel("부산");
		label_country_busan.setForeground(new Color(255, 255, 255));
		label_country_busan.setFont(new Font("굴림", Font.BOLD, 18));
		label_country_busan.setBounds(300, 305, 50, 25);
		panel_country.add(label_country_busan);
		
		label_country_busan_temp = new JLabel("부산");
		label_country_busan_temp.setForeground(new Color(255, 255, 255));
		label_country_busan_temp.setFont(new Font("굴림", Font.BOLD, 18));
		label_country_busan_temp.setBounds(300, 325, 50, 25);
		panel_country.add(label_country_busan_temp);
		
		button_country_daejeon = new JButton("");
		button_country_daejeon.setIcon(new ImageIcon(frame.class.getResource("/Gui/image/icon_sun.png")));
		button_country_daejeon.setBounds(200, 210, 50, 50);
		button_country_daejeon.setContentAreaFilled(false);
		button_country_daejeon.setBorderPainted(false);
		panel_country.add(button_country_daejeon);
		
		JLabel label_country_daejeon = new JLabel("대전");
		label_country_daejeon.setForeground(new Color(255, 255, 255));
		label_country_daejeon.setFont(new Font("굴림", Font.BOLD, 18));
		label_country_daejeon.setBounds(158, 218, 50, 25);
		panel_country.add(label_country_daejeon);
		
		label_country_daejeon_temp = new JLabel("15도");
		label_country_daejeon_temp.setForeground(new Color(255, 255, 255));
		label_country_daejeon_temp.setFont(new Font("굴림", Font.BOLD, 18));
		label_country_daejeon_temp.setBounds(158, 238, 50, 25);
		panel_country.add(label_country_daejeon_temp);
		
		button_country_seoul = new JButton("");
		button_country_seoul.setIcon(new ImageIcon(frame.class.getResource("/Gui/image/icon_sun.png")));
		button_country_seoul.setBounds(180, 100, 50, 50);
		button_country_seoul.setContentAreaFilled(false);
		button_country_seoul.setBorderPainted(false);
		panel_country.add(button_country_seoul);
		
		JLabel label_country_seoul = new JLabel("서울");
		label_country_seoul.setForeground(new Color(255, 255, 255));
		label_country_seoul.setFont(new Font("굴림", Font.BOLD, 18));
		label_country_seoul.setBounds(235, 105, 50, 25);
		panel_country.add(label_country_seoul);
		
		label_country_seoul_temp = new JLabel("15도");
		label_country_seoul_temp.setForeground(new Color(255, 255, 255));
		label_country_seoul_temp.setFont(new Font("굴림", Font.BOLD, 18));
		label_country_seoul_temp.setBounds(235, 125, 50, 25);
		panel_country.add(label_country_seoul_temp);
		
		label = new JLabel("");
		label.setIcon(new ImageIcon(frame.class.getResource("/Gui/image/back_country.jpg")));
		label.setBounds(0, 0, 435, 503);
		panel_country.add(label);
		panel_weekly.setVisible(false);
		
		button_weekly.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel_dust.setVisible(false);
				panel_hourly.setVisible(false);
				panel_weekly.setVisible(true);
				panel_country.setVisible(false);
				page_weekly();
			}
		});
		
		if(fileExist)
		{
			new WeatherMain();
			page_dust();
		}
		else
		{
			panel_select.setVisible(true);
		}
	}
	
	void page_dust()
	{
		label_dust_city.setText(cityKor);
		label_dust_time.setText(getCurrentTime());
		label_dust_pm10_value.setText(GetCurrentFineDustValue.pm10_avg + " µg/m³");
		label_dust_pm25_value.setText(GetCurrentFineDustValue.pm25_avg + " µg/m³");
		label_temp_min_value.setText(GetWeatherForecast.current_temp_min + "℃");
		label_temp_max_value.setText(GetWeatherForecast.current_temp_max + "℃");
		label_temp_current_value.setText(GetCurrentWeather.temperature + "℃");
		
		int r[] = {51, 0, 51, 0, 255, 255, 204, 0};
		int g[] = {102, 204, 204, 153, 153, 102, 0 ,0};
		int b[] = {255, 255, 204, 51, 0, 0, 0, 0};
		
		int pm10_st[] = {16, 31, 41, 51, 76, 101, 151};
		int pm25_st[] = {9, 16, 21, 26, 38, 51, 76};
		
		int rgb_num = 0;
		int pm10_num = 0, pm25_num = 0;
		int pm10 = GetCurrentFineDustValue.pm10_avg;
		int pm25 = GetCurrentFineDustValue.pm25_avg;
		
		for(int i=6; i>=0; i--)
		{
			if(pm10 >= pm10_st[i] || pm25 >= pm25_st[i])
			{
				rgb_num = i+1;
				break;
			}
		}
		
		for(int i=6; i>=0; i--)
		{
			if(pm10 >= pm10_st[i])
			{
				pm10_num = i+1;
				break;
			}
		}
		
		for(int i=6; i>=0; i--)
		{
			if(pm25 >= pm25_st[i])
			{
				pm25_num = i+1;
				break;
			}
		}
		
		panel_main.setBackground(new Color(r[rgb_num], g[rgb_num], b[rgb_num]));
		panel_dust.setBackground(new Color(r[rgb_num], g[rgb_num], b[rgb_num]));
		label_current.setBackground(new Color(r[rgb_num], g[rgb_num], b[rgb_num]));
		
		String img_dust[] = {"/Gui/image/icon_dust_1.png", 
							"/Gui/image/icon_dust_2.png",
							"/Gui/image/icon_dust_3.png",
							"/Gui/image/icon_dust_4.png",
							"/Gui/image/icon_dust_5.png", 
							"/Gui/image/icon_dust_6.png",
							"/Gui/image/icon_dust_7.png",
							"/Gui/image/icon_dust_8.png"};
		
		String img_dust_small[] = {"/Gui/image/icon_dust_1_small.png", 
				"/Gui/image/icon_dust_2_small.png",
				"/Gui/image/icon_dust_3_small.png",
				"/Gui/image/icon_dust_4_small.png",
				"/Gui/image/icon_dust_5_small.png", 
				"/Gui/image/icon_dust_6_small.png",
				"/Gui/image/icon_dust_7_small.png",
				"/Gui/image/icon_dust_8_small.png"};
		label_icon_dust.setIcon(new ImageIcon(frame.class.getResource(img_dust[rgb_num])));
		button_dust_pm10.setIcon(new ImageIcon(frame.class.getResource(img_dust_small[pm10_num])));
		button_dust_pm25.setIcon(new ImageIcon(frame.class.getResource(img_dust_small[pm25_num])));
		
		panel_select.setVisible(false);
		panel_main.setVisible(true);
		panel_dust.setVisible(true);
		panel_hourly.setVisible(false);
		panel_weekly.setVisible(false);
		panel_country.setVisible(false);
		
		int time_weather_value = 0;
		if(GetCurrentWeather.current_weather.contentEquals("rain"))
		{
			time_weather_value = 0;
		}
		else if(GetCurrentWeather.current_weather.contentEquals("clouds"))
		{
			time_weather_value = 1;
		}
		else if(GetCurrentWeather.current_weather.contentEquals("sky"))
		{
			time_weather_value = 2;
		}
		button_dust_icon_weather.setIcon(new ImageIcon(frame.class.getResource(img_icon_weather[time_weather_value])));
	}
	
	void page_hourly()
	{
		label_hourly_city.setText(cityKor);
		label_hourly_time.setText(getCurrentTime());
		
		label_time0_temp_min.setText(GetWeatherForecast.temperature[1] + "℃");
		label_time1_temp_min.setText(GetWeatherForecast.temperature[2] + "℃");
		label_time2_temp_min.setText(GetWeatherForecast.temperature[3] + "℃");
		label_time3_temp_min.setText(GetWeatherForecast.temperature[4] + "℃");
		label_time4_temp_min.setText(GetWeatherForecast.temperature[5] + "℃");

		label_time0_date.setText(GetWeatherForecast.dateFiveTime[0] + " 시");
		label_time1_date.setText(GetWeatherForecast.dateFiveTime[1] + " 시");
		label_time2_date.setText(GetWeatherForecast.dateFiveTime[2] + " 시");
		label_time3_date.setText(GetWeatherForecast.dateFiveTime[3] + " 시");
		label_time4_date.setText(GetWeatherForecast.dateFiveTime[4] + " 시");
		
		int time_weather_value[] = {0, 0, 0, 0, 0};
		for(int i=0; i<5; i++)
		{
			if(GetWeatherForecast.weatherFiveTime[i].contains("rain"))
			{
				time_weather_value[i] = 0;
			}
			else if(GetWeatherForecast.weatherFiveTime[i].contains("clouds"))
			{
				time_weather_value[i] = 1;
			}
			else if(GetWeatherForecast.weatherFiveTime[i].contains("sky"))
			{
				time_weather_value[i] = 2;
			}
		}
		button_time0.setIcon(new ImageIcon(frame.class.getResource(img_icon_weather[time_weather_value[0]])));
		button_time1.setIcon(new ImageIcon(frame.class.getResource(img_icon_weather[time_weather_value[1]])));
		button_time2.setIcon(new ImageIcon(frame.class.getResource(img_icon_weather[time_weather_value[2]])));
		button_time3.setIcon(new ImageIcon(frame.class.getResource(img_icon_weather[time_weather_value[3]])));
		button_time4.setIcon(new ImageIcon(frame.class.getResource(img_icon_weather[time_weather_value[4]])));
	}
	
	void page_weekly()
	{
		label_weekly_city.setText(cityKor);
		label_weekly_time.setText(getCurrentTime());
		
		label_day0_temp_min.setText(GetWeatherForecast.weekly_temp_min[0] + "℃");
		label_day1_temp_min.setText(GetWeatherForecast.weekly_temp_min[1] + "℃");
		label_day2_temp_min.setText(GetWeatherForecast.weekly_temp_min[2] + "℃");
		label_day3_temp_min.setText(GetWeatherForecast.weekly_temp_min[3] + "℃");
		label_day4_temp_min.setText(GetWeatherForecast.weekly_temp_min[4] + "℃");
		
		label_day0_temp_max.setText(GetWeatherForecast.weekly_temp_max[0] + "℃");
		label_day1_temp_max.setText(GetWeatherForecast.weekly_temp_max[1] + "℃");
		label_day2_temp_max.setText(GetWeatherForecast.weekly_temp_max[2] + "℃");
		label_day3_temp_max.setText(GetWeatherForecast.weekly_temp_max[3] + "℃");
		label_day4_temp_max.setText(GetWeatherForecast.weekly_temp_max[4] + "℃");
		
		label_day0_date.setText(GetWeatherForecast.dateFiveDay[0]);
		label_day1_date.setText(GetWeatherForecast.dateFiveDay[1]);
		label_day2_date.setText(GetWeatherForecast.dateFiveDay[2]);
		label_day3_date.setText(GetWeatherForecast.dateFiveDay[3]);
		label_day4_date.setText(GetWeatherForecast.dateFiveDay[4]);
		
		int day_weather_value[] = {0, 0, 0, 0, 0};
		for(int i=0; i<5; i++)
		{
			if(GetWeatherForecast.weatherFiveDay[i].contains("rain"))
			{
				day_weather_value[i] = 0;
			}
			else if(GetWeatherForecast.weatherFiveDay[i].contains("clouds"))
			{
				day_weather_value[i] = 1;
			}
			else if(GetWeatherForecast.weatherFiveDay[i].contains("sky"))
			{
				day_weather_value[i] = 2;
			}
			
		}
		button_day0.setIcon(new ImageIcon(frame.class.getResource(img_icon_weather[day_weather_value[0]])));
		button_day1.setIcon(new ImageIcon(frame.class.getResource(img_icon_weather[day_weather_value[1]])));
		button_day2.setIcon(new ImageIcon(frame.class.getResource(img_icon_weather[day_weather_value[2]])));
		button_day3.setIcon(new ImageIcon(frame.class.getResource(img_icon_weather[day_weather_value[3]])));
		button_day4.setIcon(new ImageIcon(frame.class.getResource(img_icon_weather[day_weather_value[4]])));
	}
	
	void page_country()
	{
		label_country_seoul_temp.setText(GetCountryTemp.country_temp[0] + "℃");
		label_country_daejeon_temp.setText(GetCountryTemp.country_temp[1] + "℃");
		label_country_gwangju_temp.setText(GetCountryTemp.country_temp[2] + "℃");
		label_country_busan_temp.setText(GetCountryTemp.country_temp[3] + "℃");
		
		int country_weather_value[] = {0, 0, 0, 0};
		for(int i=0; i<4; i++)
		{
			if(GetCountryTemp.country_weather[i].contentEquals("rain"))
			{
				country_weather_value[i] = 0;
			}
			else if(GetCountryTemp.country_weather[i].contentEquals("clouds"))
			{
				country_weather_value[i] = 1;
			}
			else if(GetCountryTemp.country_weather[i].contentEquals("sky"))
			{
				country_weather_value[i] = 2;
			}
			else
			{
				country_weather_value[i] = 1;
			}
		}
		
		button_country_seoul.setIcon(new ImageIcon(frame.class.getResource(img_icon_weather[country_weather_value[0]])));
		button_country_daejeon.setIcon(new ImageIcon(frame.class.getResource(img_icon_weather[country_weather_value[1]])));
		button_country_gwangju.setIcon(new ImageIcon(frame.class.getResource(img_icon_weather[country_weather_value[2]])));
		button_country_busan.setIcon(new ImageIcon(frame.class.getResource(img_icon_weather[country_weather_value[3]])));
	}
	
	String getCurrentTime()
	{
		long time = System.currentTimeMillis(); 
		SimpleDateFormat dayTime = new SimpleDateFormat("yyyy-MM-dd hh:mm");
		String str = dayTime.format(new Date(time));
		return str;
	}
	
	class citySelected implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) {
			JButton j = (JButton)e.getSource();
			
			cityEng = CityNameParse.cityName.get(j.getText());
			cityKor = j.getText();
			try 
			{
				FileWriter fw = new FileWriter(filePath);
				fw.write(cityEng + "," + cityKor);
				fw.close();
		    } 
			catch (IOException ioe) 
			{
				ioe.printStackTrace();
		    }
			
			
			
			panel_select.setVisible(false);
			new WeatherMain();
			page_dust();
		}
	}
}
