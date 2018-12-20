package apiTest;


import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import setUp.Setup;



public class Categories extends Setup {


   @Test
   public static void getCategories () throws Exception{
      getMethod(getApiUrl("categoriesUrl"));



       }



}
