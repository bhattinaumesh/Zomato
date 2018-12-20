package setUp;


import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class Setup {

    public static Object[][] getXlObject(String path, String sheetName) throws Exception {  //user for data import
        FileInputStream k = new FileInputStream(path);
        Workbook k1 = WorkbookFactory.create(k);
        //System.out.println("Test TEst");
        Sheet s = k1.getSheet(sheetName);
        //System.out.println("Test TEst1");
        Row p = s.getRow(0);
        // int oli = s.getLastRowNum();

        //System.out.println(s.getLastRowNum()+p.getLastCellNum());
        Object data[][] = new Object[s.getLastRowNum()][p.getLastCellNum()];
        //System.out.println("Test TEsto");


        for (int i = 1; i <= s.getLastRowNum(); i++) {
            System.out.println(i + "Last Row No " + s.getLastRowNum() + "lanj" + p.getLastCellNum());
            for (int j = 0; j <= p.getLastCellNum() - 1; j++) {
                String l = s.getRow(i).getCell(j).getStringCellValue();
                data[i - 1][j] = l;
                System.out.println(data[i - 1][j]);
            }
        }
        return data;
    }


    public static Response getMethod(String apiUrl) {
        RestAssured.baseURI = getApiUrl("baseUrl");
        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");
        request.header("user-key", "3909afba4a08315145ca63269dd00a77");  // key
        Response response = request.get(apiUrl);   //api end point
        ResponseBody res = response.getBody();
        Assert.assertEquals(response.getStatusCode(), 200);  //to validate success response
        String value = res.asString();
        System.out.println(value);
        return response;
    }


    public static String getApiUrl(String api) {
        Properties prop = new Properties();
        FileInputStream inStream = null;
        try {
            inStream = new FileInputStream("C:\\Users\\Ezetap005\\qa\\zomato\\src\\test\\scala\\Api.properties");
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        try {
            prop.load(inStream);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return prop.getProperty(api);
    }
}
