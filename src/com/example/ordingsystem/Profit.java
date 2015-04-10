package com.example.ordingsystem;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class Profit extends Activity{
	
	private static final String DBname = "OrderSystem.db";
	private static final int DBversion = 1;
	private TextView byDay,byMonth;
	private CompDBHper dbHper;
	String day,month;
	
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.profit);
		initDB();
		buildViews();
	}
	
	private void buildViews() {
		byDay = (TextView)findViewById(R.id.byDay);
		byMonth = (TextView)findViewById(R.id.byMonth);
		
	}
	
	private void initDB() {
		if(dbHper == null)
			dbHper = new CompDBHper(this, DBname,null, DBversion);
	}
	
	public void onResume() {
		super.onResume();
		if(dbHper == null)
			dbHper = new CompDBHper(this, DBname,null, DBversion); 
	}

	public void onPause() {
		super.onPause();
		if(dbHper != null){
			dbHper.close(); 
			dbHper = null;
		}
	}
}
