package tek.General;

import java.io.InputStream;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class PostAction extends Utils
{
	private String postAction(String parameters)
	{
		try
		{
			String postdata = parameters;
			String path = "/dotdash-project-alpha/todo.php";
			String baseurl = "http://localhost";
			CloseableHttpClient httpClient = HttpClients.createDefault();
			
			String testURL = escapeSpecialCharacters(baseurl + path);
			CloseableHttpResponse response= null;
			HttpPost httpPost = new HttpPost(testURL);
			httpPost.setHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
			httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded");
			
			HttpEntity hentity = new ByteArrayEntity(postdata.getBytes("utf-8"));
	     	httpPost.setEntity(hentity);
	     	response = httpClient.execute(httpPost);
	     	log.info("Response got: "+response.getStatusLine());
	     	HttpEntity entity = response.getEntity();
			InputStream instream = entity.getContent();
			String apiResponse = convertStreamToString(instream);
			log.info("API Response: "+ apiResponse);
			return response.getStatusLine().toString();
		}
		catch(Exception ex)
		{
			log.error("Error: "+ex.getMessage());
			return "FAILED:"+ ex.getMessage();
		}

	
	}
	public boolean findAPIStatus(String APIAction,String data)
	{
		String postAPIStatusCode = postAction(data);
		if (postAPIStatusCode.contains("200") || postAPIStatusCode.contains("302") || postAPIStatusCode.contains("201")
				|| postAPIStatusCode.contains("409") || postAPIStatusCode.contains("400")
				|| postAPIStatusCode.contains("401"))

		{
			return true;
		}

		return false;
	}

}
