package Main;
import static io.restassured.RestAssured.given;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import static io.restassured.RestAssured.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;


public class Method {

	public static void main(String[] args) {
		
			// TODO Auto-generated method stub
			RestAssured.baseURI="https://swapi.dev/api/planets";
			String response = given().log().all().when().get().then().assertThat().log().all().statusCode(200).extract().response().asString();
			//System.out.println(response);
			JsonPath js1=ReusableMethods.rawToJson(response);
			JsonPath j = new JsonPath(response);
			String p1 = j.get("results[0].name");
			System.out.println(p1);
			String result = js1.getString("results");
			int planetcount = j.getInt("results.size()");
			//System.out.println(result);
	        FileInputStream file = null;
			try {
				file = new FileInputStream(new File("C:\\Users\\hp\\eclipse-workspace\\api\\src\\test\\resources\\testdata.xlsx"));
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	        XSSFWorkbook workbook = null;
			try {
				workbook = new XSSFWorkbook(file);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        XSSFSheet sheet = workbook.getSheet("Sheet1");
	        Row row = sheet.getRow(1);
	        Cell cell = row.getCell(0);
	        String ans = cell.getStringCellValue();
	        XSSFSheet sheet1 = workbook.getSheet("Sheet2");
	        Row r = sheet1.getRow(1);
	        Cell c = r.getCell(0);
	        String p = c.getStringCellValue();
	        
	     // Assertion for the response
	        Assert.assertEquals(ans, result);
	        if(ans.equalsIgnoreCase(result))
	        	System.out.println("Test 1 passed for getting the correct response");

	        //Assertion for validating the 1st planet name
	        Assert.assertEquals(p1, p);
	        if(p1.equalsIgnoreCase(p)) {
	        	System.out.println("Test 2 passed for validating the first planet name");
	        //Assertion for validating planetcount
	        Assert.assertEquals(planetcount, 10);
	        if(planetcount==10) {
	        	System.out.println("Test 3 passed for validating planetcount");
	        }
	        }
		}


	

}
