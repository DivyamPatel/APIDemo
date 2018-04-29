package tek.TestCases;

import java.util.ArrayList;

import org.testng.annotations.Test;

import tek.General.Utils;

public class TC2_TaskList extends Utils 
{
  @Test
  public void getTakList() 
  {
		ArrayList<String> TaskList = getTaskName();
		for (String name:TaskList)
		{
			System.out.println(name);
		}  
  }
}
