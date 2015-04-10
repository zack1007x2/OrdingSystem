package com.example.ordingsystem;



import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MenuInsert extends Activity{
	
	private static final String DBname = "OrderSystem.db";
	private static final int DBversion = 1;
	private TextView tvRestID;
	private EditText etItemID,etItemName,etPrice; 
	private Button btinsert,btgoback,btre;
	private CompDBHper dbHper; 
	
	String restID;
	
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.insertmenu);
        
		initDB();
        Bundle bundle = this.getIntent().getExtras();
		restID = bundle.getString("restID");
		System.out.println(restID);
		
		buildViews();
    }
	

	
	private void buildViews() {
		tvRestID = (TextView)findViewById(R.id.tvRestID);
		etItemID = (EditText)findViewById(R.id.editTextItemID);
		etItemName = (EditText)findViewById(R.id.editTextItemName);
		etPrice = (EditText)findViewById(R.id.editTextItemPrice);   
		btinsert = (Button)findViewById(R.id.btinsert);
		btgoback = (Button)findViewById(R.id.btgoback);
		btre = (Button)findViewById(R.id.btre);

		//顯示店家ID
		tvRestID.setText(restID);
		tvRestID.setTextColor(Color.RED);

		btinsert.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				String ItemID = etItemID.getText().toString().trim();
				String ItemName = etItemName.getText().toString().trim();
				String Price = etPrice.getText().toString().trim();
				if(ItemID.equals("") || ItemName.equals("") || Price.equals("")){
					Toast.makeText(MenuInsert.this, "請輸入欲新增的商品資料 !", Toast.LENGTH_SHORT).show();
					return;
				}
				
				String msg = null;
				long rowID  = dbHper.insertMenu(ItemID,ItemName,Integer.parseInt(Price));
				long insertHave  = dbHper.insertHave(restID,ItemID);
				if(rowID != -1 && insertHave != -1){
//				if(rowID != -1){
					msg ="新增記錄  成功 ";
				}else{
					msg ="新增記錄  失敗 !";
				}
				Toast.makeText(MenuInsert.this, msg,Toast.LENGTH_SHORT).show();
				dbHper.getTestSet("II");
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
