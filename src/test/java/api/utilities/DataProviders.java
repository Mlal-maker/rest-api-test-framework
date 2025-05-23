package api.utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {
	
	@DataProvider(name="Data")
	public String[][] getAllData() throws IOException{
		
		String path = System.getProperty("user.dir")+"//testData//Userdata.xlsx";
		XLUtility xl = new XLUtility(path);
		
		int rownum = xl.getRowCount("sheet1");
		int colcount = xl.getCellCount("sheet1", 1);
		
		String apiData[][]= new String[rownum][colcount];
		
		for(int i=1;i<=rownum;i++) {
			for(int j=0;j<colcount;j++) {
				apiData[i-1][j] = xl.getCellData("sheet1", i, j);
  			}
		}
		
		return apiData;
	}
	
	@DataProvider(name="UserNames" )
	public String[] getUserNames() throws IOException {
		
		String path = System.getProperty("user.dir")+"//testData//Userdata.xlsx";
		XLUtility xl = new XLUtility(path);
		
		int rownum = xl.getRowCount("sheet1");
		
		String apiData[] = new String[rownum];
		
		for(int i=1;i<=rownum;i++) {
			apiData[i-1]= xl.getCellData("sheet1", i, 1);
		}
		
		return apiData;
	}

}
