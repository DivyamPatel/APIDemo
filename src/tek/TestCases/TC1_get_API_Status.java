package tek.TestCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import tek.General.*;


public class TC1_get_API_Status extends Utils 
{
	
	GetAction actionGet=new GetAction();
	PostAction actionPost=new PostAction();
  @Test
  public void getAPIStatus() 
  {
	  	
		Assert.assertEquals(actionGet.findAPIStatus(actionGet.getAction()),true);
		Assert.assertEquals(actionPost.findAPIStatus("POST", getPostParameters("Category")),true);
		Assert.assertEquals(actionPost.findAPIStatus("POST", getPostParameters("Task")),true);
  }
}
