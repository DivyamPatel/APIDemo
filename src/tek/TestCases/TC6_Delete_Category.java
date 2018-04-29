package tek.TestCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import tek.General.GetAction;
import tek.General.PostAction;
import tek.General.Utils;

public class TC6_Delete_Category extends Utils 
{
	GetAction actionGet = new GetAction();
	

	@Test
	public void deleteCategory() {
		
		//actionGet.getAction(deleteCategoryId);
		Assert.assertEquals(actionGet.findAPIStatus(actionGet.getAction()), true);
	}
}
