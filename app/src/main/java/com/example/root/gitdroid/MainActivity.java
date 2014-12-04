package com.example.root.gitdroid;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import org.apache.http.client.ClientProtocolException;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.google.gson.Gson;

public class MainActivity extends Activity /*implements OAuthCallback */{
	
	
	private Intent intent_data;
	private EditText user, password;
	private String res;
	private ProgressDialog pd;
	private Context context;
    private ForgotPasswordF forgotPasswordF;
    private LoginFragment loginFragment ;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		context=this;
		initComponents();
	}

    private void initComponents() {
        user=(EditText) findViewById(R.id.user);
        password=(EditText) findViewById(R.id.password);
    }

    @Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	
	public void sendData(View view) throws IOException, InterruptedException, ExecutionException
	{
		intent_data= new Intent(MainActivity.this, DisplayDataActivity.class);
		LoginUser login=new LoginUser();
		login.execute("");
		pd = ProgressDialog.show(context, "Por favor espere","verificando cuenta", true, false);
	
	}
	
	public class LoginUser extends AsyncTask<String,Void, Object>{

		  // final ProgressDialog dialog=new ProgressDialog(context);

			@Override
			protected Integer doInBackground(String... oauthService) {
				Login login=new Login();
				try {
					res=login.acceder(user.getText().toString(), password.getText().toString());
				} catch (ClientProtocolException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				return 1;
			}

			protected void onPostExecute(Object result)
			{
				pd.dismiss();
				if(res.equals("wrong"))
				    Toast.makeText(context,"There was a problem with your login information. Please try again!",Toast.LENGTH_LONG).show();
				else{
					intent_data.putExtra("user",res);
				    startActivity(intent_data);
				}

				super.onPostExecute(result);
			}


	/*public void forgot(View password)
	{
	intent1=new Intent(this, ForgotPassword.class);
	intent1.putExtra("err", "ssds");
	startActivity(intent1);
//	}

	/*public void onFinished(OAuthData d) {
		final TextView t=d.provider.equals("github") ? TextViewLogin:TextViewLogin;
		if(!d.status.equals("success"))
		{
			t.setTextColor(Color.parseColor("#FFF000"));
			t.setText("error, " + d.error);
		}
	}*/

}
}































