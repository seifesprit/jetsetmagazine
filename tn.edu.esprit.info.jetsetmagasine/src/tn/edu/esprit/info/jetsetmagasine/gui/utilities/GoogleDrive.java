package tn.edu.esprit.info.jetsetmagasine.gui.utilities;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Date;

import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.client.http.FileContent;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.DriveScopes;
import com.google.api.services.drive.model.File;
import com.google.api.services.drive.model.ParentReference;

public class GoogleDrive {
	
	private  String CLIENT_ID = "368769490067-qum1aq5ivsonh18af0rt3s4gsm6dee6a.apps.googleusercontent.com";
	  private  String CLIENT_SECRET = "ck3zMBU65iTfjDWidvNn2C57";

	  private  String REDIRECT_URI = "urn:ietf:wg:oauth:2.0:oob";
	  private String path;
	
	public GoogleDrive(String path) {
		this.path = path;
	}
	
	public String uplodeGoogleDrive() throws IOException{
		
		HttpTransport httpTransport = new NetHttpTransport();
	    JsonFactory jsonFactory = new JacksonFactory();
	   
	    GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
	        httpTransport, jsonFactory, CLIENT_ID, CLIENT_SECRET, Arrays.asList(DriveScopes.DRIVE))
	        .setAccessType("online")
	        .setApprovalPrompt("auto").build();
	    
	    String url = flow.newAuthorizationUrl().setRedirectUri(REDIRECT_URI).build();
	    System.out.println("Please open the following URL in your browser then type the authorization code:");
	    System.out.println("  " + url);
	    
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    String code = br.readLine();
	    
	    GoogleTokenResponse response = flow.newTokenRequest(code).setRedirectUri(REDIRECT_URI).execute();
	    GoogleCredential credential = new GoogleCredential().setFromTokenResponse(response);
	    
	    //Create a new authorized API client
	    Drive service = new Drive.Builder(httpTransport, jsonFactory, credential).build();

	    //Insert a file  
	    File body = new File();
	    body.setTitle(new Date().getTime()+"");
	    //body.setDescription("A test document");
	    body.setMimeType("image/jpeg");
	    
	    java.io.File fileContent = new java.io.File(path);
	    FileContent mediaContent = new FileContent("image/jpeg", fileContent);

	    File file = service.files().insert(body, mediaContent).execute();
	    System.out.println("File ID: " + file.getId());
	    return file.getId();
		
	}
	
	//id = 0B7GNL_KltwuPRzdJdUd1bzlMMm8
	
	public  File printFile() {

	    try {
	    	
	    	HttpTransport httpTransport = new NetHttpTransport();
		    JsonFactory jsonFactory = new JacksonFactory();
		   
		    GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
		        httpTransport, jsonFactory, CLIENT_ID, CLIENT_SECRET, Arrays.asList(DriveScopes.DRIVE))
		        .setAccessType("online")
		        .setApprovalPrompt("auto").build();
		    
		    String url = flow.newAuthorizationUrl().setRedirectUri(REDIRECT_URI).build();
		    System.out.println("Please open the following URL in your browser then type the authorization code:");
		    System.out.println("  " + url);
		    
		    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		    String code = br.readLine();
		    
		    GoogleTokenResponse response = flow.newTokenRequest(code).setRedirectUri(REDIRECT_URI).execute();
		    GoogleCredential credential = new GoogleCredential().setFromTokenResponse(response);
		    
		    //Create a new authorized API client
		    Drive service = new Drive.Builder(httpTransport, jsonFactory, credential).build();
	    	
	      File file = service.files().get(path).execute();

	      System.out.println("Title: " + file.getTitle());
	      System.out.println("Description: " + file.getDescription());
	      System.out.println("MIME type: " + file.getMimeType());
	      return file;
	    } catch (IOException e) {
	      System.out.println("An error occured: " + e);
	      return null;
	    }
	  }

	  /**
	   * Download a file's content.
	   *
	   * @param service Drive API service instance.
	   * @param file Drive File instance.
	   * @return InputStream containing the file's content if successful,
	   *         {@code null} otherwise.
	 * @throws IOException 
	   */
	  public InputStream downloadFile(File file) throws IOException {
		  
		  HttpTransport httpTransport = new NetHttpTransport();
		    JsonFactory jsonFactory = new JacksonFactory();
		   
		    GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
		        httpTransport, jsonFactory, CLIENT_ID, CLIENT_SECRET, Arrays.asList(DriveScopes.DRIVE))
		        .setAccessType("online")
		        .setApprovalPrompt("auto").build();
		    
		    String url = flow.newAuthorizationUrl().setRedirectUri(REDIRECT_URI).build();
		    System.out.println("Please open the following URL in your browser then type the authorization code:");
		    System.out.println("  " + url);
		    
		    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		    String code = br.readLine();
		    
		    GoogleTokenResponse response = flow.newTokenRequest(code).setRedirectUri(REDIRECT_URI).execute();
		    GoogleCredential credential = new GoogleCredential().setFromTokenResponse(response);
		    
		    //Create a new authorized API client
		    Drive service = new Drive.Builder(httpTransport, jsonFactory, credential).build();
		  
		  
	    if (file.getDownloadUrl() != null && file.getDownloadUrl().length() > 0) {
	      try {
	        HttpResponse resp =
	            service.getRequestFactory().buildGetRequest(new GenericUrl(file.getDownloadUrl()))
	                .execute();
	        return resp.getContent();
	      } catch (IOException e) {
	        // An error occurred.
	        e.printStackTrace();
	        return null;
	      }
	    } else {
	      // The file doesn't have any content stored on Drive.
	      return null;
	    }
	  }

}
