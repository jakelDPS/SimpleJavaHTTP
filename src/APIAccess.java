
import java.net.MalformedURLException;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONArray;

public class APIAccess {
	
	private void post(String URL, String username, String password) throws MalformedURLException, IOException {
		System.out.println("Begin login test"); 
		System.out.println(URL + " " + username + " " + password);
		
		URL loginURL = new URL(URL);
		HttpURLConnection con = (HttpURLConnection)loginURL.openConnection();
		con.setRequestMethod("POST");
		con.setRequestProperty("Content-Type", "application/json;");

		con.setDoOutput(true);
		con.setDoInput(true);		
		
		//String postJsonData = "{\"username\": \"YOUR USERNAME HERE\", \"password\": \"YOUR PASSWORD HERE\"}";
		String postJsonData = "{\"username\": \"" + username + "\", \"password\": \"" + password + "\"}";
		
		try {
			DataOutputStream wr = new DataOutputStream(con.getOutputStream());
			wr.writeBytes(postJsonData);
			wr.flush();
			wr.close();
			
			int responseCode = con.getResponseCode();
			System.out.println("Sending 'POST' request to URL : " + loginURL);
			System.out.println("Post Data : " + postJsonData);
			System.out.println("Response Code : " + responseCode);
			
		} catch(IOException e) {
			System.out.println("Caught exception");
		}
		 
		BufferedReader in = new BufferedReader(
		        new InputStreamReader(con.getInputStream()));
		String output;
		StringBuffer response = new StringBuffer();
		 
		while ((output = in.readLine()) != null) {
		 response.append(output);
		}
		in.close();
		  
		System.out.println(response.toString());
	}	
	
	private void get(String URL) throws IOException {
		
		URL url = new URL(URL);
		HttpURLConnection con = (HttpURLConnection) url.openConnection();

		// optional default is GET
		con.setRequestMethod("GET");

		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'GET' request to URL : " + url);
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(
		        new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		JSONArray responseJSON = new JSONArray(response.toString());
		
		// This is how you get the whole array
		System.out.println(responseJSON);
		
		// This is how you get a single json object
		//System.out.println(responseJSON.getJSONObject(0));
		
		// This is how you get a specific value from a specific index
		//System.out.println(responseJSON.getJSONObject(0).get("firstname"));
	}	
	
	public static void main(String[] args) {
		APIAccess a = new APIAccess();

		String URL = args[0];
		String username = args[1];
		String password = args[2];
		String method = args[3];
			
		switch (method) {
			case "post":
				try {			
					a.post(URL, username, password);
				} catch (MalformedURLException e) {
					System.out.println("Caught MalformedURLException");
					e.printStackTrace();
				} catch (IOException e) {
					System.out.println("Caught IOException");
					e.printStackTrace();
				}
				
				break;
				
			case "get":
				
				try {
					a.get(URL);
				} catch (MalformedURLException e) {
					System.out.println("Caught MalformedURLException");
					e.printStackTrace();
				} catch (IOException e) {
					System.out.println("Caught IOException");
					e.printStackTrace();
				}
				
				break;
		}
		
	}	

}