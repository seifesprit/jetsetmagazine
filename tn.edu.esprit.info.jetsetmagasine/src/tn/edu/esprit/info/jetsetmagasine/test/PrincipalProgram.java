package tn.edu.esprit.info.jetsetmagasine.test;

import java.io.IOException;

import com.google.api.client.googleapis.auth.clientlogin.ClientLogin;
import com.google.api.client.http.HttpResponseException;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;

public class PrincipalProgram {

	public static void main(String[] args) throws IOException {
		 // HttpTransport used to send login request.
	    HttpTransport transport = new NetHttpTransport();
	    try {
	      // authenticate with ClientLogin
	      ClientLogin authenticator = new ClientLogin();
	      authenticator.transport = transport;
	      // Google service trying to access, e.g., "cl" for calendar.
	      authenticator.authTokenType = "cl";
	      authenticator.username = "medaymenjendoubi@gmail.com";
	      authenticator.password = "esperanza1919NOUNI";
	      authenticator.authenticate();
	      System.out.println("Authentication succeeded.");
	    } catch (HttpResponseException e) {
	      // Likely a "403 Forbidden" error.
	      System.err.println(e.getStatusMessage());
	      throw e;
	    }
		
	}

}
