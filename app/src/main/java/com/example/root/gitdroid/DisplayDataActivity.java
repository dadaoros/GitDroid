package com.example.root.gitdroid;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.util.EntityUtils;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

public class DisplayDataActivity extends Activity {
	private String data_user, data_password,res;
	private TextView set_user, set_password;
	private ScrollView ser;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_display_data);
		data_user=getIntent().getStringExtra("user");
		set_user=(TextView) findViewById(R.id.set_user);
      	set_user.setText(data_user);

	}
  
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.display_data, menu);
		return true;
	}
	public class GetRepos extends AsyncTask<String,Void, Object>{
		Manager man=Manager.getInstancia();
		String url ="https://api.github.com/user/";
		
		@Override
		protected Integer doInBackground(String... arg0) {
			HttpUriRequest request = new HttpGet(url);
			HttpClient client=man.getContenedor();
			try {
				HttpResponse respon=client.execute(request);
				res=EntityUtils.toString(respon.getEntity());
				

			} catch (ClientProtocolException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			return 1;
		}
		@Override
		protected void onPostExecute(Object result)
		{
			set_user.setText(res);
			
			super.onPostExecute(result);	
		}
	
	}
	
}
