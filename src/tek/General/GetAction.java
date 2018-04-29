package tek.General;

import java.io.InputStream;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class GetAction extends Utils
{

	public String getAction()
	{
		try 
		{
			String parameters = getPostParameters("DeleteCategory");
			CloseableHttpClient httpClient = HttpClients.createDefault();
			String baseurl = getPropertiesInstance().getProperty("baseurl");
			String path = "/index.php";
			
			String test = escapeSpecialCharacters(baseurl + path+parameters);
			System.out.println(test);
			
			CloseableHttpResponse response = null;
			HttpGet httpget = new HttpGet(test);
			httpget.setHeader("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
			response = httpClient.execute(httpget);
			log.info(response.getStatusLine().toString());
			HttpEntity entity = response.getEntity();
			InputStream instream = entity.getContent();
			String apiResponse = convertStreamToString(instream);
			//String contentType=response.getEntity().getContentType().toString();
			
			log.info("Final API Response is: "+apiResponse);
			return response.getStatusLine().toString();
			
		}
		catch (Exception ex) 
		{
			log.error("Error: "+ex.getMessage());
			return "FAILED:"+ ex.getMessage();
		}
	
	}
	/*
	public String getAction(String Category)
	{
		try 
		{
			String parameters = getPostParameters("DeleteCategory");
			CloseableHttpClient httpClient = HttpClients.createDefault();
			String txtresponse = "";
			String baseurl = getPropertiesInstance().getProperty("baseurl");
			String path = "/dotdash-project-alpha/index.php";
			
			String test = escapeSpecialCharacters(baseurl + path + parameters);
	
			
			CloseableHttpResponse response = null;
			HttpGet httpget = new HttpGet(test);
			httpget.setHeader("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,*//*;q=0.8");
			response = httpClient.execute(httpget);
			log.info(response.getStatusLine().toString());
			HttpEntity entity = response.getEntity();
			InputStream instream = entity.getContent();
			String apiResponse = convertStreamToString(instream);
			//String contentType=response.getEntity().getContentType().toString();
			
			log.info("Final API Response is: "+apiResponse);
			return response.getStatusLine().toString();
			
		}
		catch (Exception ex) 
		{
			log.error("Error: "+ex.getMessage());
			return "FAILED:"+ ex.getMessage();
		}
	
	} */
	public boolean findAPIStatus(String apiData)
	{
		String getAPIStatusCode = apiData;
		// if(statusCode.contains("200")||statusCode.contains("302"))
		if (getAPIStatusCode.contains("200") || getAPIStatusCode.contains("201") || getAPIStatusCode.contains("409")
				|| getAPIStatusCode.contains("400") || getAPIStatusCode.contains("401")) {
			return true;
		}

		return false;
	}

}
