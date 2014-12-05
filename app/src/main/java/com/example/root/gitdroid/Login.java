/**
 * 
 */
package com.example.root.gitdroid;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import com.google.gson.Gson;

import android.util.Base64;
import android.widget.CheckBox;

/**
 * @author Andres
 *
 */
public class Login {
	private CheckBox rememberPassword;
	public Login() {

	}
	
	public String acceder(String user, String password) throws ClientProtocolException, IOException{
        String respuesta;
        // TODO ¿Deberia ser un HttpPost()?
        String url ="https://api.github.com/authorizations";
		HttpUriRequest request = new HttpGet(url); // Or HttpPost(), depends on your needs 
		String credentials = user  + ":" + password;  
		String base64EncodedCredentials = Base64.encodeToString(credentials.getBytes(), Base64.NO_WRAP);  
		request.addHeader("Authorization", "Basic " + base64EncodedCredentials);
/*
        ResponseHandler<String> responseHandler=new BasicResponseHandler();
        String responseBody=client.execute(post, responseHandler);
        JSONObject response=new JSONObject(responseBody); */


		HttpClient httpclient = new DefaultHttpClient();  
		
		HttpResponse response=httpclient.execute(request);

		if(response.getStatusLine().toString().equals("HTTP/1.1 200 OK"))
			respuesta=EntityUtils.toString(response.getEntity());
		else
	        respuesta="wrong";

		Manager manager = Manager.getInstancia();
		manager.setContenedor(httpclient);

        return respuesta;
	}

}



