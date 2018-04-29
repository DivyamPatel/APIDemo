package tek.General;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Properties;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class Utils 
{
	static String baseURL="";
	static Properties pro= null;
	static Logger log = Logger.getLogger(Utils.class.getName());
	
	protected static Properties getPropertiesInstance() {
		try
		{
			if(pro == null) {
				pro =  new Properties();
				InputStream stream = new FileInputStream(new File(System.getProperty("user.dir")+"\\TestData\\Config.properties"));
				pro.load(stream);
				//pro.load(APITest.class.getClassLoader().getResourceAsStream("Config.properties"));
			}
		}
		catch(Exception ex)
		{
			//log.error("Error: "+ex.getMessage());
		}
		return pro;
	}
	protected String convertStreamToString(InputStream is) {

	    BufferedReader reader = new BufferedReader(new InputStreamReader(is));
	    StringBuilder sb = new StringBuilder();

	    String line = null;
	    try {
	        while ((line = reader.readLine()) != null) {
	            sb.append(line + "\n");
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            is.close();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	    return sb.toString();
	}
	protected String escapeSpecialCharacters(String input) 
	{
        StringBuilder resultStr = new StringBuilder();
        for (char ch : input.toCharArray()) {
            if (isSafe(ch)) {
                resultStr.append(ch);
            } else{
                resultStr.append('%');
                resultStr.append(toHex(ch / 16));
                resultStr.append(toHex(ch % 16));
            }
        }
        return resultStr.toString();
    }

	private static char toHex(int ch) {
		return (char) (ch < 10 ? '0' + ch : 'A' + ch - 10);
	}

	private static boolean isSafe(char ch) {
		return ((ch >= 'A' && ch <= 'Z') || (ch >= 'a' && ch <= 'z')
				|| (ch >= '0' && ch <= '9') || "[]=+&?{}:\",-_.~/".indexOf(ch) >= 0);
	}
	protected ArrayList<String> getTaskName()
	{
		//String[] TaskName=null;
		//getHTMLString();
		ArrayList<String> TaskName=new ArrayList<String>();
		
		Document html = Jsoup.parse(getHTMLString());
		Elements TaskList = html.body().getElementsByTag("li");
		for (int i = 0; i<TaskList.size();i++)
		{		
			//System.out.println(TaskList.get(i).getElementsByTag("a").text());			
			//System.out.println(TaskList.get(i).getElementsByTag("strike").text());
			//System.out.println(TaskList.get(i).getElementsByTag("span").text());
			TaskName.add(TaskList.get(i).getElementsByTag("li").text());
		}
		return TaskName;
	}
	protected void getCatetory()
	{
		Document html = Jsoup.parse(getHTMLString()); 
		//String title = html.title();
		Elements test = html.body().getElementsByTag("li");
		
		
		String fullString="";
		List<String[]> Allstring=new ArrayList<String[]>();
		
		ArrayList<String> datestring=new ArrayList<String>();
		for (int i=1;i<test.size();i++)
		{
			String span = test.get(i).getElementsByTag("span").attr("Style").toString();
			//String sb = span.get(i).getElementsByAttribute("style").text();
			
			System.out.println(span);
			/*fullString  =span.get(i).getElementsByTag("li").text();//+" "+test.get(i).getElementsByTag("strike").text()+" "+ test.get(i).getElementsByTag("span").text();
			if (fullString.contains("(")&& fullString.contains(")"))
			{
					String taskName = fullString.substring(0, fullString.indexOf("(")-1);
					String test1= fullString.substring(fullString.indexOf("(")+1, fullString.indexOf(")"));
					if (!test1.equals("None"))
					{
						datestring.add(test1);
						Allstring.add(new String[] {taskName,test1});
					}
			}*/
		}
		//shortingvalue(Allstring);
	}
	protected void readHTML()
	{
		Document html = Jsoup.parse(getHTMLString()); 
		//String title = html.title();
		Elements test = html.body().getElementsByTag("li");
		String fullString="";
		List<String[]> Allstring=new ArrayList<String[]>();
		
		ArrayList<String> datestring=new ArrayList<String>();
		for (int i=0;i<test.size();i++)
		{
			fullString  =test.get(i).getElementsByTag("li").text();//+" "+test.get(i).getElementsByTag("strike").text()+" "+ test.get(i).getElementsByTag("span").text();
			if (fullString.contains("(")&& fullString.contains(")"))
			{
					String taskName = fullString.substring(0, fullString.indexOf("(")-1);
					String test1= fullString.substring(fullString.indexOf("(")+1, fullString.indexOf(")"));
					if (!test1.equals("None"))
					{
						datestring.add(test1);
						Allstring.add(new String[] {taskName,test1});
					}
			}
		}
		shortingvalue(Allstring);
	}
	protected int getCount()
	{
		Document html = Jsoup.parse(getHTMLString()); 
		//String title = html.title();
		Elements test = html.body().getElementsByTag("li");
		/*Elements span = html.body().getElementsByTag("span");
		String fullString="";
		List<String[]> Allstring=new ArrayList<String[]>();
		
		ArrayList<String> datestring=new ArrayList<String>();
		for (int i=0;i<test.size();i++)
		{
			fullString  =test.get(i).getElementsByTag("li").text();//+" "+test.get(i).getElementsByTag("strike").text()+" "+ test.get(i).getElementsByTag("span").text();
			if (fullString.contains("(")&& fullString.contains(")"))
			{
					String taskName = fullString.substring(0, fullString.indexOf("(")-1);
					String test1= fullString.substring(fullString.indexOf("(")+1, fullString.indexOf(")"));
					String[] myData = fullString.split(" ");
					if (!test1.equals("None"))
					{
						datestring.add(test1);
						Allstring.add(new String[] {taskName,test1});
					}
			}
		}
		shortingvalue(Allstring);*/
		return test.size();
	}
	protected String getPostParameters(String ActionType)
	{
		baseURL =getPropertiesInstance().getProperty("baseurl"); 
		String CategoryName = getPropertiesInstance().getProperty("CategoryName");
		String CategoryColor = getPropertiesInstance().getProperty("CategoryColor");
		String TaskName =getPropertiesInstance().getProperty("TaskName");
		String TaskCategory = getPropertiesInstance().getProperty("TaskCategory");
		String DueDate = getPropertiesInstance().getProperty("DueDate");
		String DueMonth = getPropertiesInstance().getProperty("DueMonth");
		String DueYear = getPropertiesInstance().getProperty("DueYear");
		String deleteCategoryId = getPropertiesInstance().getProperty("Delete_CategoryID"); 
		
		String Parameters="";
		if(ActionType.equalsIgnoreCase("Category"))
		{
			Parameters =  "ohash=9ae5a016a12f27e3ea3621708ae54df7572f832d&data=&category=None&due_day="+DueDate+"&due_month="+DueMonth+"&due_year="+DueYear+"&categorydata="+CategoryName+"&submit=Add+category&colour="+CategoryColor;
			//String Category = "ohash=9ae5a016a12f27e3ea3621708ae54df7572f832d&data=&category=None&due_day=None&due_month=None&due_year=2018&categorydata=Category1&submit=Add+category&colour=%230000FF";
		}
		else if (ActionType.equalsIgnoreCase("Task"))
		{
			Parameters = "category=2&categorydata=&colour=&data="+TaskName+"&due_day="+DueDate+"&due_month="+DueMonth+"&due_year="+DueYear+"&ohash=b7812720c4c276b9dcf11e09b8290e483372313b&submit=Add";
		}
		else if (ActionType.equalsIgnoreCase("DeleteCategory"))
		{
			Parameters = "cid="+deleteCategoryId+"&confirm=yes";
		}
		else if (ActionType.equalsIgnoreCase("EditTask"))
		{
			Parameters = "cid="+deleteCategoryId+"&confirm=yes";
		}
		else
		{
			Parameters="";
		}
		return "?"+Parameters;
	}
	
	private void shortingvalue(List<String[]> datestring)
	{    
		ArrayList<String> lst = new ArrayList<String>();
		for (String[] finalString: datestring)
		{
			lst.add(finalString[1]);
		}
	    Collections.sort(lst, new Comparator<String>() {
	        DateFormat f = new SimpleDateFormat("dd/MM/yy");
	        @Override
	        public int compare(String o1, String o2) {
	            try {
	                return f.parse(o2).compareTo(f.parse(o1));
	            } catch (ParseException e) {
	                throw new IllegalArgumentException(e);
	            }
	        }
	    });
	    System.out.println("Desc Short order By Due Date");
	    for (String c: lst)
	    {
	    	for(String[] s:datestring)
	    	{
	    		if (c.equals(s[1].toString()))
	    		{
	    			System.out.println("Task Name: "+s[0]+" & Due Date: "+s[1]);
	    		}
	    	}
	    }
	    
	}
	
	private String getHTMLString()
	{
		try 
		{
			String parameters = "";
			CloseableHttpClient httpClient = HttpClients.createDefault();
			//String txtresponse = "";
			String path = "/dotdash-project-alpha/index.php";
			String baseurl = "http://localhost";
			String test = escapeSpecialCharacters(baseurl + path + parameters);

			
			CloseableHttpResponse response = null;
			HttpGet httpget = new HttpGet(test);
			httpget.setHeader("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
			response = httpClient.execute(httpget);
			//log.info(response.getStatusLine().toString());
			// EndTime = new Date().getTime();
			
			HttpEntity entity = response.getEntity();
			InputStream instream = entity.getContent();
			String apiResponse = convertStreamToString(instream);
			//String contentType=response.getEntity().getContentType().toString();
			//log.info("Content Type is: "+ contentType);
			//log.info("Final API Response is: "+apiResponse);
			//readHTML(apiResponse);
			return apiResponse;
		}
		catch (Exception ex) 
		{
			//log.error("Error: "+ex.getMessage());
			return "Failed: "+ex.getMessage();
		}
		
	}


}
