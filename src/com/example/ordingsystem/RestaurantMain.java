package com.example.ordingsystem;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class RestaurantMain extends Activity{
	private Button btinsert,btedit,btdelet,btgoback;
	
	String restID;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.restaurantmain);
		
		buildViews();
		Bundle bundle = this.getIntent().getExtras();
		restID = bundle.getString("restID");
	}
	 
	 private void buildViews(){
		 btinsert = (Button)findViewById(R.id.btinsert);    
		 btedit = (Button)findViewById(R.id.btedit);
		 btdelet = (Button)findViewById(R.id.btdelet);    
		 btgoback = (Button)findViewById(R.id.btgoback);
	    	
		 btgoback.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {
					finish();
				}
			});
			
		 btinsert.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {
					Intent intent = new Intent();
					intent.setClass(RestaurantMain.this, MenuInsert.class);
					Bundle bundle = new Bundle();
					bundle.putString("restID",restID);
					intent.putExtras(bundle);
					startActivity(intent);
				}
			});
		 btedit.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {
					Intent intent = new Intent();
					intent.setClass(RestaurantMain.this, MenuEdit.class);
					Bundle bundle = new Bundle();
					bundle.putString("restID",restID);
					intent.putExtras(bundle);
					System.out.println("INHERE");
					startActivity(intent);
				}
			}); 
		 btdelet.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {
					Intent intent = new Intent();
					intent.setClass(RestaurantMain.this, MenuDelete.class);
					Bundle bundle = new Bundle();
					bundle.putString("restID",restID);
					intent.putExtras(bundle);
					startActivity(intent);
				}
			}); 
		 
	    	   	
	    }
	    

}
