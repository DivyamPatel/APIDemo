package tek.TestCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import tek.General.PostAction;
import tek.General.Utils;

public class TC7_Edit_Task extends Utils
{
	PostAction actionPost = new PostAction();

	@Test
	public void edit_Task_Details() 
	{
		Assert.assertEquals(actionPost.findAPIStatus("POST", getPostParameters("EditTask")),true);
	}
}
