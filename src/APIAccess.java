
import java.net.MalformedURLException;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONObject;

public class APIAccess {
	
	String bearerToken;	
	
	private void login(String URL, String username, String password) throws MalformedURLException, IOException {
		System.out.println("Begin login test"); 
		
		String loginLink = URL + "/login";
		
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
		  
		System.out.println(response.toString() + "\n");
	}	

	private void createContact(String URL, String domain, String userID, String fName, String lName, String phone, 
			String email, String topic) throws IOException {
		
		String creationLink = URL + "/api/createContact";
		URL url = new URL(creationLink);
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
		
		System.out.println(response.toString() + "\n");
	}	
	
	private void updateContact(String URL, String domain, String userID, String oldRouteType, String oldRouteMethod, String newRouteType, 
			String newRouteMethod) throws IOException {
		
		String creationLink = URL + "/api/updateContact";
		URL url = new URL(creationLink);
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setRequestMethod("POST");
		con.setRequestProperty("Content-Type", "application/json;");
		con.setRequestProperty ("Authorization", bearerToken);
		
		con.setDoOutput(true);
		con.setDoInput(true);
		
		JSONObject j = new JSONObject();
		j.put("domain", domain);
		j.put("userID", userID);
		j.put("oldRouteType", oldRouteType);
		j.put("oldRouteMethod", oldRouteMethod);
		j.put("newRouteType", newRouteType);
		j.put("newRouteMethod", newRouteMethod);
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
		
		System.out.println(response.toString() + "\n");
	}
	
	private void deleteContact(String URL, String domain, String userID) throws IOException {
		
		String deletionLink = URL + "/api/deleteContact";
		URL url = new URL(deletionLink);
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setRequestMethod("POST");
		con.setRequestProperty("Content-Type", "application/json;");
		con.setRequestProperty ("Authorization", bearerToken);
		
		con.setDoOutput(true);
		con.setDoInput(true);
		
		JSONObject j = new JSONObject();
		j.put("domain", domain);
		j.put("userID", userID);
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
		
		System.out.println(response.toString() + "\n");
	}
	
	private void sendFreeform(String URL, String sender, String domain, ArrayList<String> usernames, String subject, String body, ArrayList<String> routeTypes) throws IOException {
		
		String freeformLink = URL + "/api/sendNotification";
		URL url = new URL(freeformLink);
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setRequestMethod("POST");
		con.setRequestProperty("Content-Type", "application/json;");
		con.setRequestProperty ("Authorization", bearerToken);
		
		con.setDoOutput(true);
		con.setDoInput(true);
		
		ArrayList<String> attachments = new ArrayList<String>();
		
		JSONObject j = new JSONObject();
		j.put("sender", sender);
		j.put("domain", domain);
		j.put("usernames", usernames);
		j.put("messageSubject", subject);
		j.put("messageBody", body);
		j.put("routeTypes", routeTypes);
		j.put("attachments", attachments);
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
		
		System.out.println(response.toString() + "\n");
	}
	
	private void sendTemplate(String URL, String sender, String domain, ArrayList<String> usernames, String templateID, JSONArray subs) throws IOException {
		String templateLink = URL + "/api/sendTemplateNotification";
		URL url = new URL(templateLink);
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setRequestMethod("POST");
		con.setRequestProperty("Content-Type", "application/json;");
		con.setRequestProperty ("Authorization", bearerToken);
		
		con.setDoOutput(true);
		con.setDoInput(true);
		
		ArrayList<String> attachments = new ArrayList<String>();
		
		JSONObject j = new JSONObject();
		j.put("sender", sender);
		j.put("domain", domain);
		j.put("usernames", usernames);
		j.put("tmpID", templateID);
		j.put("subs", subs);
		j.put("attachments", attachments);
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
		
		System.out.println(response.toString() + "\n");
	}
	
	private void setContactTemplateRouting(String URL, String domain, String userID, String tmpID, ArrayList<String> routeTypes) throws IOException {
		 
		String setRoutesLink = URL + "/api/setContactTemplateRouting";
		
		URL url = new URL(setRoutesLink);
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
	
	private void get(String URL, String endpoint) throws IOException {
		
		String fullLink = URL + endpoint;
		
		URL url = new URL(fullLink);
		HttpURLConnection con = (HttpURLConnection) url.openConnection();

		con.setRequestMethod("GET");
		con.setRequestProperty ("Authorization", bearerToken);

		try {
			int responseCode = con.getResponseCode();
			System.out.println("\nSending 'GET' request to URL : " + url);
			System.out.println("Response Code : " + responseCode);
			if (responseCode == 403) {
				System.out.println("Unauthorized");
			}
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

		JSONArray responseJSON = new JSONArray(response.toString());
		
		
		// This is how you get the whole array
		System.out.println(responseJSON);
		
		// This is how you get a single json object
		//System.out.println(responseJSON.getJSONObject(0));
		
		// This is how you get a specific value from a specific index
		//System.out.println(responseJSON.getJSONObject(0).get("firstname"));
	}	
	
	public static void main(String[] args) throws MalformedURLException, IOException {
			
		APIAccess a = new APIAccess();
		JSONArray jsonSubs = new JSONArray();
		ArrayList<String> usernamesTemplate = new ArrayList<String>();
		ArrayList<String> usernamesFree = new ArrayList<String>();

		Scanner scan = new Scanner(System.in);
		
		System.out.println("Enter IP Address and port number (ex. http://10.1.4.16:8089)\n");
		String URL = scan.nextLine();
		
		while (true) {
			System.out.println("Would you like to login, alter a contact (create, update, delete), set route preferences, "
					+ "send a freeform notification (freeform), send a template notification (template), "
					+ "or perform a GET request? Type 'quit' to quit\n");
			String answer = scan.nextLine();
			
			switch (answer) {
				case "login":
					System.out.println("Enter username: ");
					String username = scan.nextLine();
					System.out.println("Enter password: ");
					String password = scan.nextLine();
					a.login(URL, username, password);
					break;
					
				case "create":
					System.out.println("Enter Domain: ");
					String domain = scan.nextLine();
					System.out.println("Enter User ID: ");
					String userID = scan.nextLine();
					System.out.println("Enter First Name: ");
					String fName = scan.nextLine();
					System.out.println("Enter Last Name: ");
					String lName = scan.nextLine();
					System.out.println("Enter Phone Number: ");
					String phone = scan.nextLine();
					System.out.println("Enter Email Address: ");
					String email = scan.nextLine();
					String topic = domain + userID;
					topic = topic.replaceAll("[^A-Za-z0-9]", "");
					if (topic.length() > 24) {
						topic = topic.substring(0,  24);
					}
					
					System.out.println("TOPIC IS: *" + topic + "*");
					
					a.createContact(URL, domain, userID, fName, lName, phone, email, topic);
					break;
					
				case "update":
					System.out.println("Enter Domain: ");
					String domainUpdate = scan.nextLine();
					System.out.println("Enter User ID: ");
					String userIDUpdate = scan.nextLine();
					System.out.println("Enter Old Route Type: ");
					String oldRouteTypeUpdate = scan.nextLine();
					System.out.println("Enter Old Route Method: ");
					String oldRouteMethodUpdate = scan.nextLine();
					System.out.println("Enter New Route Type: ");
					String newRouteTypeUpdate = scan.nextLine();
					System.out.println("Enter New Route Method: ");
					String newRouteMethodUpdate = scan.nextLine();
					
					a.updateContact(URL, domainUpdate, userIDUpdate, oldRouteTypeUpdate, oldRouteMethodUpdate, 
							newRouteTypeUpdate, newRouteMethodUpdate);
					break;
					
				case "delete":
					System.out.println("Enter Domain: ");
					String domainDelete = scan.nextLine();
					System.out.println("Enter User ID: ");
					String userIDDelete = scan.nextLine();
					
					a.deleteContact(URL, domainDelete, userIDDelete);
					break;
					
				case "freeform": 
					usernamesFree.clear();
					System.out.println("Enter Sender User ID: ");
					String senderFree = scan.nextLine();
					System.out.println("Enter Domain of Recipient: ");
					String domainFree = scan.nextLine();
					
					while (true) {
						System.out.println("Enter Usernames of recipients (Press 'Enter' after each one, type 'done' when finished): ");
						String usernameFree = scan.nextLine();
						if (usernameFree.equals("done")) {
							break;
						} else {
							usernamesFree.add(usernameFree);
						}
					}
					
					System.out.println("Enter Notification Subject: ");
					String subjectFree = scan.nextLine();
					System.out.println("Enter Notification Body: ");
					String bodyFree = scan.nextLine();
					System.out.println("Send email? (y/n) ");
					String emailFree = scan.nextLine();
					System.out.println("Send sms? (y/n) ");
					String smsFree = scan.nextLine();
					System.out.println("Send push? (y/n) ");
					String pushFree = scan.nextLine();
					
					ArrayList<String> routeTypesFree = new ArrayList<String>();
					if (emailFree.equals("y")) {
						routeTypesFree.add("email");
					}
					if (smsFree.equals("y")) {
						routeTypesFree.add("sms");
					}
					if (pushFree.equals("y")) {
						routeTypesFree.add("push");
					}
					
					a.sendFreeform(URL, senderFree, domainFree, usernamesFree, subjectFree, bodyFree, routeTypesFree);
					break;
					
				case "template": 
					jsonSubs = new JSONArray();
					usernamesTemplate.clear();
					System.out.println("Enter Sender User ID: ");
					String senderTemplate = scan.nextLine();
					System.out.println("Enter Domain of Recipient(s): ");
					String domainTemplate = scan.nextLine();
					while (true) {
						System.out.println("Enter Usernames of recipients (Press 'Enter' after each one, type 'done' when finished): ");
						String usernameTemplate = scan.nextLine();
						if (usernameTemplate.equals("done")) {
							break;
						} else {
							usernamesTemplate.add(usernameTemplate);
						}
					}
					System.out.println("Enter Template ID: ");
					String templateIDTemplate = scan.nextLine();
					while (true) {
						System.out.println("Enter Name of substitution variable. Enter 'done' when finished: ");
						String subName = scan.nextLine();
						if (subName.equals("done")) {
							break;
						} else {
							System.out.println("Enter value of substitution variable: ");
							String subValue = scan.nextLine();
							JSONObject sub = new JSONObject();
							sub.put(subName, subValue);
							jsonSubs.put(sub);
						}						
					}					
					
					a.sendTemplate(URL, senderTemplate, domainTemplate, usernamesTemplate, templateIDTemplate, jsonSubs);
					break;
					
				case "routes":
					System.out.println("Enter Domain: ");
					String domainPref = scan.nextLine();
					System.out.println("Enter User ID: ");
					String userIDPref = scan.nextLine();
					System.out.println("Enter Template ID: ");
					String tmpIDPref = scan.nextLine();
					System.out.println("Email? (y/n): ");
					String emailPref = scan.nextLine();
					System.out.println("SMS? (y/n): ");
					String smsPref = scan.nextLine();
					System.out.println("Push? (y/n): ");
					String pushPref = scan.nextLine();
					
					ArrayList<String> routeTypes = new ArrayList<String>();
					if (emailPref.equals("y")) {
						routeTypes.add("email");
					}
					if (smsPref.equals("y")) {
						routeTypes.add("sms");
					}
					if (pushPref.equals("y")) {
						routeTypes.add("push");
					}
					
					a.setContactTemplateRouting(URL, domainPref, userIDPref, tmpIDPref, routeTypes);
					break;
					
				case "get":
					System.out.println("Which endpoint would you like to access? (ex. /api/getUsers");
					String endpoint = scan.nextLine();
					
					a.get(URL, endpoint);
					break;
					
				case "quit":
					System.exit(0);
					
				default: 
					System.out.println("Usage: 'login', 'create', 'update', 'delete', 'freeform', 'template', 'routes', "
							+ "'get', 'quit'\n");
					break;
					
			}
		
		}
		
	}	

}