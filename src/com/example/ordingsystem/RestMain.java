package com.example.ordingsystem;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class RestMain extends Activity{
	
	private Button bt2Order,bt2Profit,bt2Edit,btgoback;
	String restID;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.restmain);
		
		buildViews();
		Bundle bundle = this.getIntent().getExtras();
		restID = bundle.getString("restID");
	}
	 
	 private void buildViews(){
		 bt2Order = (Button)findViewById(R.id.bt2Order);    
		 bt2Profit = (Button)findViewById(R.id.bt2Profit);
		 bt2Edit = (Button)findViewById(R.id.bt2Edit);
		 btgoback = (Button)findViewById(R.id.btgoback);
		 
		 btgoback.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {
					finish();
				}
			});
		 bt2Order.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {
					Intent intent = new Intent();
					intent.setClass(RestMain.this, OrderCheck.class);
					Bundle bundle = new Bundle();
					bundle.putString("restID",restID);
					intent.putExtras(bundle);
					startActivity(intent);
				}
			});
		 bt2Profit.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {
					Intent intent = new Intent();
					intent.setClass(RestMain.this, Profit.class);
					Bundle bundle = new Bundle();
					bundle.putString("restID",restID);
					intent.putExtras(bundle);
					startActivity(intent);
				}
			});
		 bt2Edit.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {
					Intent intent = new Intent();
					intent.setClass(RestMain.this, RestaurantMain.class);
					Bundle bundle = new Bundle();
					bundle.putString("restID",restID);
					intent.putExtras(bundle);
					startActivity(intent);
				}
			});

		 

	 }
}