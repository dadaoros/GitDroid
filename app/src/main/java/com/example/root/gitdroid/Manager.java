package com.example.root.gitdroid;

import org.apache.http.client.HttpClient;



public class Manager {
	private HttpClient contenedor;
	private static Manager manager;
	private Manager()
	{
	
	}

	public static Manager getInstancia()
	{
		if(manager==null)
		{
			manager=new Manager();
		}
		return manager;
	}

	public HttpClient getContenedor() {
		return contenedor;
	}

	public void setContenedor(HttpClient contenedor) {
		this.contenedor = contenedor;
	}
	
	
}
