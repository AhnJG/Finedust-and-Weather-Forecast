package Gui;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class CityNameParse {
	static HashMap<String, String> cityName = new HashMap<String, String>();
	static ArrayList<String> cityKor = new ArrayList<String>();
	
	CityNameParse() 
	{
		File filePath = new File("src/Gui/cityName.txt");
	
		try
		{
			FileReader fr = new FileReader(filePath);
			BufferedReader br = new BufferedReader(fr);
			String line;
			while((line = br.readLine()) != null)
			{
				//System.out.println(line);
				String tmp[] = line.split(",");
	
				cityKor.add(tmp[1]);
				cityName.put(tmp[1], tmp[0]);
			}
			fr.close();
		}
		catch(Exception e)
		{
			System.out.println("Error" + e.getMessage());
		}
	}
}

