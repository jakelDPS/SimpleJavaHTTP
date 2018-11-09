
import java.net.MalformedURLException;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

public class APIAccess {
	
	String bearerToken;	
	String loginLink = "http://10.1.4.16:8089/login";
	String setContactTemplateRoutingLink = "http://10.1.4.16:8089/api/setContactTemplateRouting";
	
	private void login(String username, String password) throws MalformedURLException, IOException {
		System.out.println("Begin login test"); 
		
		URL loginURL = new URL(loginLink);
		HttpURLConnection con = (HttpURLConnection)loginURL.openConnection();
		con.setRequestMethod("POST");
		con.setRequestProperty("Content-Type", "application/json;");

		con.setDoOutput(true);
		con.setDoInput(true);		
		
		JSONObject j = new JSONObject();
		j.put("username", username);
		j.put("password", password);
		String postJsonData = j.toString();
				
		try {
			DataOutputStream wr = new DataOutputStream(con.getOutputStream());
			wr.writeBytes(postJsonData);
			wr.flush();
			wr.close();
			
			int responseCode = con.getResponseCode();
			bearerToken = con.getHeaderField("Authorization");
			System.out.println(bearerToken);
			
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

	private void createContact(String URL, String domain, String userID, String fName, String lName, String phone, 
			String email, String topic) throws IOException {
		
		URL url = new URL(URL);
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setRequestMethod("POST");
		con.setRequestProperty("Content-Type", "application/json;");
		con.setRequestProperty ("Authorization", bearerToken);
		
		con.setDoOutput(true);
		con.setDoInput(true);
		
		JSONObject j = new JSONObject();
		j.put("domain", domain);
		j.put("userID", userID);
		j.put("fName", fName);
		j.put("lName", lName);
		j.put("phone", phone);
		j.put("email", email);
		j.put("topic", topic);
		String postJsonData = j.toString();

		try {
			DataOutputStream wr = new DataOutputStream(con.getOutputStream());
			wr.writeBytes(postJsonData);
			wr.flush();
			wr.close();
		
			int responseCode = con.getResponseCode();
			System.out.println("\nSending 'POST' request to URL : " + url);
			System.out.println("Response Code : " + responseCode);
		} catch (IOException e) {
			System.out.println("Caught exception");
		}

		BufferedReader in = new BufferedReader(
		        new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
		
		System.out.println(response.toString());
	}	
	
	private void setContactTemplateRouting(String domain, String userID, String tmpID, String[] routeTypes) throws IOException {
		
		URL url = new URL(setContactTemplateRoutingLink);
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setRequestMethod("POST");
		con.setRequestProperty("Content-Type", "application/json;");
		con.setRequestProperty ("Authorization", bearerToken);
		
		con.setDoOutput(true);
		con.setDoInput(true);
		
		JSONObject j = new JSONObject();
		j.put("domain", domain);
		j.put("userID", userID);
		j.put("tmpID", tmpID);
		j.put("routeTypes", routeTypes);		

		String postJsonData = j.toString();
		
		System.out.println(postJsonData);

		try {
			DataOutputStream wr = new DataOutputStream(con.getOutputStream());
			wr.writeBytes(postJsonData);
			wr.flush();
			wr.close();
		
			int responseCode = con.getResponseCode();
			System.out.println("\nSending 'POST' request to URL : " + url);
			System.out.println("Response Code : " + responseCode);
		} catch (IOException e) {
			System.out.println("Caught exception");
		}

		BufferedReader in = new BufferedReader(
		        new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
		
		System.out.println(response.toString());
	}
	
	private void get(String URL) throws IOException {
		
		URL url = new URL(URL);
		HttpURLConnection con = (HttpURLConnection) url.openConnection();

		con.setRequestMethod("GET");
		con.setRequestProperty ("Authorization", bearerToken);

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
		
		// Login will be run every time. User can choose POST or GET, then 
		// provide the specific endpoint that they want
		// (Code modification will need to be done when different JSON post data is needed, 
		// this is a simple program not intended to be fully worked out)
		
		APIAccess a = new APIAccess();

		// I know this is a bit ridiculous. Generally wouldn't make these kinds of calls using the command line		
		String URL = args[0];
		String username = args[1];
		String password = args[2];
		String method = args[3];
		String domain = args[4];
		String userID = args[5];
		String fName = args[6];
		String lName = args[7];
		String phone = args[8];
		String email = args[9];
		String topic = args[10];
		String tmpID = args[11];
		String[] routeTypes = {"email"};

		try {			
			a.login(username, password);
		} catch (MalformedURLException e) {
			System.out.println("Caught MalformedURLException");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Caught IOException");
			e.printStackTrace();
		}
			
		switch (method) {
			case "post":
				try {			
					a.createContact(URL, domain, userID, fName, lName, phone, email, topic);
				} catch (MalformedURLException e) {
					System.out.println("Caught MalformedURLException");
					e.printStackTrace();
				} catch (IOException e) {
					System.out.println("Caught IOException");
					e.printStackTrace();
				}
				
				try {			
					a.setContactTemplateRouting(domain, userID, tmpID, routeTypes);
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