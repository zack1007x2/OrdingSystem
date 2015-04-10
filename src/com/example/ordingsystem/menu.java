package com.example.ordingsystem;

import java.util.ArrayList;

import android.app.Activity;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


public class menu extends Activity{
	private static final String DBname = "OrderSystem.db";
	private static final int DBversion = 1;
	private TextView tvItem;
	private EditText etAmount; 
	private Button btsend,btgoback;
	private CompDBHper dbHper; 
	private Spinner spinner1;
	
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.menu);
		initDB();
		
		buildViews();
	}
	
	private void buildViews() {
		tvItem = (TextView)findViewById(R.id.textViewItem2);
		etAmount = (EditText)findViewById(R.id.etAmount);
		btsend = (Button)findViewById(R.id.btsend);
		btgoback = (Button)findViewById(R.id.btgoback);
		spinner1 = (Spinner)findViewById(R.id.spinner1);
		
		ArrayList<String> recSet = dbHper.getItemSet(); 
		System.out.println(recSet);
		ArrayAdapter <String> OrderList = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1,recSet);		
//    	OrderList.setDropDownViewResource(
//				android.R.layout.simple_spinner_dropdown_item);
    	spinner1.setAdapter(OrderList);
    	spinner1.setOnItemSelectedListener(new OnItemSelectedListener() {
			public void onItemSelected(AdapterView <?> parent, View v, int position,long id) {
				tvItem.setText(parent.getSelectedItem().toString());
			}
			
			public void onNothingSelected(AdapterView <?> parent) {
			}
		});
//		//顯示店家ID
//		tvRestID.setText(restID);
//		tvRestID.setTextColor(Color.RED);
		    	
		btgoback.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				finish();
			}
		});
		
		btsend.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				String Amount = etAmount.getText().toString().trim();
				if(Amount.equals("")){
					Toast.makeText(menu.this, "請輸入欲購買數量 !", Toast.LENGTH_SHORT).show();
					return;
				}
				
				ArrayList<String> recSet = dbHper.getRestSet(tvItem.getText().toString()); 
//				System.out.println(recSet);
				String msg = null;
//				System.out.println(recSet.get(0) + " " + tvItem.getText().toString() + " " + Integer.parseInt(etAmount.getText().toString()) + " " + Integer.parseInt(recSet.get(1)));
				long rowID  = dbHper.insertOrder(recSet.get(0),tvItem.getText().toString(),Integer.parseInt(etAmount.getText().toString()), Integer.parseInt(recSet.get(1)));
				if(rowID != -1){
					msg ="新增成功 ";
				}else{
					msg ="新增失敗 !";
				}
				Toast.makeText(menu.this, msg,Toast.LENGTH_SHORT).show();
				dbHper.getTestSet("OO");
			}
		});
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
