package com.example.ordingsystem;


import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MenuDelete extends Activity{
	private TextView tvRestID;
	private EditText etItemName; 
	private Button btdelet,btgoback,btre;
	private CompDBHper dbHper;
	String msg=null;
	private static final String DBname = "OrderSystem.db";
	private static final int DBversion = 1;
	String restID;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.deletemenu);
        initDB();
        
        buildViews();  //user define
    }

	private void buildViews() {
		tvRestID = (TextView)findViewById(R.id.tvRestID);
		etItemName = (EditText)findViewById(R.id.editTextItemName);  
		btdelet = (Button)findViewById(R.id.btdelet);
		btgoback = (Button)findViewById(R.id.btgoback);
		btre = (Button)findViewById(R.id.btre);
		
//顯示店家ID
	
		tvRestID.setText(restID);
		
		btdelet.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				String itemName = etItemName.getText().toString().trim();
				int rowsAffected = dbHper.deleteMenuRec(itemName);
				if (rowsAffected==-1) {
					msg="資料表已空, 無法刪除 !";
				} else if (rowsAffected==0) {
					msg="找不到欲刪除的記錄, 無法刪除 !";
				} else {
					msg="已刪除 !";			
				}
				Toast.makeText(MenuDelete.this, msg,Toast.LENGTH_SHORT)
				     .show();
			}
		});         	
		
		btgoback.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				finish();
			}
		});
		
		btre.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				etItemName.setText("");
			}
		});
	}
	private void initDB() {
		if(dbHper == null)
			dbHper = new CompDBHper(this, DBname,null, DBversion);
//		dbHper.createTB();	
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
