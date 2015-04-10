package com.example.ordingsystem;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
//import android.widget.Toast;

import android.widget.AdapterView.OnItemClickListener;

public class OrderCheck extends Activity{
	
	private static final String DBname = "OrderSystem.db";
	private static final int DBversion = 1;
	private CompDBHper dbHper;
    private ListView lvOrder;
    private Button btgoback;
    static String msg;
    ArrayAdapter <String> OrderList;
    String restID;
    

    
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ordercheck);

        initDB();
        buildViews(); 
        

    }

    private void buildViews(){
    	Bundle bundle = this.getIntent().getExtras();
    	restID = bundle.getString("restID");
    	lvOrder = (ListView)findViewById(R.id.lvOrder);
    	btgoback = (Button)findViewById(R.id.btgoback);
    	
    	
		ArrayList<String> recSet = dbHper.getOrderSet(restID);
		System.out.println(recSet);
		OrderList = new ArrayAdapter<String>(
				this, android.R.layout.simple_expandable_list_item_1,recSet);

    	lvOrder.setAdapter(OrderList);
    	lvOrder.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
				final String strSelectedItem = parent.getItemAtPosition(position).toString();
				OrderList.remove(strSelectedItem);
				
				System.out.println(parent.getItemAtPosition((int) position).toString());

				String[] fld = parent.getItemAtPosition((int) position).toString().split(" ");
				System.out.println(fld[2]);
				dbHper.updateOrder(fld[2]);
				

			}
			
		});
		    	
		btgoback.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				finish();
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
