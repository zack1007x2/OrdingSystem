package com.example.ordingsystem;

import java.util.ArrayList;
import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class MenuEdit extends Activity{
	private TextView tvRestID;
	private EditText etItemID,etItemName,etPrice; 
	private Button btpre,btnext,btedit,btgoback,btre;
	private CompDBHper dbHper;
	private ArrayList<String> recSet; 
	private int index=0; 
	String msg=null;
	private static final String DBname = "OrderSystem.db";
	private static final int DBversion = 1;	
	String restID;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editmenu);

        initDB();  
        Bundle bundle = this.getIntent().getExtras();
		restID = bundle.getString("restID");
		buildViews(); 
    }
		
	private void buildViews() {
		tvRestID = (TextView)findViewById(R.id.tvRestID);
		etItemID = (EditText)findViewById(R.id.editTextItemID);
		etItemName = (EditText)findViewById(R.id.editTextItemName);
		etPrice = (EditText)findViewById(R.id.editTextItemPrice);   
		btedit = (Button)findViewById(R.id.btedit);
		btgoback = (Button)findViewById(R.id.btgoback);
		btre = (Button)findViewById(R.id.btre);
		btpre = (Button)findViewById(R.id.btpre);
		btnext = (Button)findViewById(R.id.btnext);

		
      //顯示店家ID
		tvRestID.setText(restID);
		tvRestID.setTextColor(Color.RED);

		btedit.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				String ItemID = etItemID.getText().toString().trim();
				String ItemName = etItemName.getText().toString().trim();
				String Price = etPrice.getText().toString().trim();
				int rowsAffected  =  dbHper.updateRec(restID,ItemID,ItemName,Integer.parseInt(Price));
				if (rowsAffected==-1) {
					msg="資料表已空, 無法修改 !";
				} else if (rowsAffected==0) {
					msg="找不到欲修改的記錄, 無法修改 !";
				} else {
					msg="已修改 !";
					recSet = dbHper.getRecSet();
					showRec(index);				
				}
				Toast.makeText(MenuEdit.this, msg,Toast.LENGTH_SHORT).show();
			}
		});         	
		
		btgoback.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				finish();
			}
		});
		btre.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				etItemID.setText("");
				etItemName.setText("");
				etPrice.setText("");
			}
		});
		
		btpre.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				index--;
				if(index < 0) index = recSet.size()-1;
				showRec(index);
			}
		});
		
		btnext.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				index++;
				if(index >= recSet.size()) index = 0;
				showRec(index);
			}
		});
	}
	private void initDB() {
		if(dbHper == null)
			dbHper = new CompDBHper(this, DBname,null, DBversion);
//		dbHper.createTB();	
		recSet = dbHper.getRecSet();
	}
	@Override
	public void onResume() {
		super.onResume();
		if(dbHper == null)
			dbHper = new CompDBHper(this, DBname,null, DBversion); 
		recSet = dbHper.getRecSet();
		showRec(index);		
	}
	@Override
	public void onPause() {
		super.onPause();
		if(dbHper != null){
			dbHper.close(); 
			dbHper = null;
		}
		recSet.clear(); 
	}
	
	private void showRec(int index) {	
		if(recSet.size()!=0){
			String[] fld=recSet.get(index).split("#");
			
			etItemID.setText(fld[0]);
			etItemName.setText(fld[1]);
			etPrice.setText(fld[2]);	
		}else{
			etItemID.setText("");
			etItemName.setText("");
			etPrice.setText("");				
		}
	}

}
