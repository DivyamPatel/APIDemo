package tek.TestCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import tek.General.PostAction;
import tek.General.Utils;

public class TC4_Get_Total_Task_Count extends Utils  
{
  @Test
  public void getTaskCount() 
  {
	  PostAction actionPost=new PostAction();
	  int getTastCount_BeforePost = getCount();
	  actionPost.findAPIStatus("POST", getPostParameters("Category"));
	  int getTastCount_AfterPost = getCount();
	  Assert.assertEquals(getTastCount_AfterPost, getTastCount_BeforePost);
  }
}
