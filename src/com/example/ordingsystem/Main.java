package com.example.ordingsystem;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Main extends Activity {
	
	private static final String DBname = "OrderSystem.db";
	private static final int DBversion = 1;
	private CompDBHper dbHper = null; 
	private Button btprofile,btorder;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		buildViews();
		
		initDB();
	}
	 private void buildViews(){
    	btprofile = (Button)findViewById(R.id.btprofile);    
    	btorder = (Button)findViewById(R.id.btorder);
    	
    	btprofile.setOnClickListener(btprofileListener);         	
    	btorder.setOnClickListener(btorderListener);         	
    	   	
    }
    
    private OnClickListener btprofileListener = new OnClickListener() {    
    	public void onClick(View v) {     
			Intent intent = new Intent();
			intent.setClass(Main.this, login.class);
			startActivity(intent);
    	}
    };

    private OnClickListener btorderListener = new OnClickListener() {    
    	public void onClick(View v) {      
			Intent intent = new Intent();
			intent.setClass(Main.this, menu.class);
			startActivity(intent);
    	}
    };
    
	private void initDB() {
		if(dbHper == null)
			dbHper = new CompDBHper(this, DBname,null, DBversion);
		dbHper.createTB();
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
