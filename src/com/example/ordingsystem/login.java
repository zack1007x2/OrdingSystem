package com.example.ordingsystem;


import java.util.ArrayList;
import java.util.Iterator;

import com.example.ordingsystem.CompDBHper;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class login extends Activity{
	
	private static final String DBname = "OrderSystem.db";
	private static final int DBversion = 1;
	private EditText editTextID,editTextPW;
	private Button btgoback,btlogin;
	private CompDBHper dbHper;
	private ArrayList<String> recSet; 
	private int index=0;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		
		initDB();
		
		editTextID = (EditText)findViewById(R.id.editTextID);
		editTextPW = (EditText)findViewById(R.id.editTextPW);
		btgoback = (Button)findViewById(R.id.btgoback);
		btlogin = (Button)findViewById(R.id.btlogin);
				
		
		btgoback.setOnClickListener(new OnClickListener() {
			//@Override
			public void onClick(View v) {
				finish();
			}
		});
		
		btlogin.setOnClickListener(new OnClickListener() {
			//@Override
			public void onClick(View v) {
				if(validate()){
					int login = restlogin();
					String restID = "A"+Integer.toString(login);
					System.out.println("login: " + restID);
					if(login != 0){
						//夾帶店家資訊
						Intent intent = new Intent(login.this,RestMain.class);
						Bundle bundle = new Bundle();
						bundle.putString("restID", restID);
						intent.putExtras(bundle);
						startActivity(intent);
					}else{
						showDialog("用戶名稱或密碼錯誤，請重新輸入！");
					}
				}
			}
		});
	}
	
	private void initDB() {
		if(dbHper == null)
			dbHper = new CompDBHper(this, DBname, null, DBversion);
		
		dbHper.createTB();	
		recSet = dbHper.getIDset();
	}
	
	public void onResume() {
		super.onResume();
		if(dbHper == null)
			dbHper = new CompDBHper(this, DBname, null, DBversion); 
		recSet = dbHper.getIDset();	
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
	
	private void showDialog(String msg){
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage(msg)
		       .setCancelable(false)
		       .setPositiveButton("確定", new DialogInterface.OnClickListener() {
		           public void onClick(DialogInterface dialog, int id) {
		           }
		       });
		AlertDialog alert = builder.create();
		alert.show();
	}
	
	private boolean validate(){
		String username = editTextID.getText().toString();
		if(username.equals("")){
			showDialog("用戶名稱不可空白！");
			return false;
		}
		String pwd = editTextPW.getText().toString();
		if(pwd.equals("")){
			showDialog("用戶密碼不可空白！");
			return false;
		}
		return true;
	}
	Iterator<String> iter;
	private int restlogin(){
		String userID = editTextID.getText().toString().toUpperCase();
		String password = editTextPW.getText().toString().toUpperCase();
		
		iter = recSet.iterator();
		while (iter.hasNext()) {
			String[] fld = iter.next().toString().split("#");

			System.out.println(fld[0] + " " + userID + " " + fld[1] + " " + password);
			if(fld[0].equals(userID) && fld[1].equals(password))
				return index+1;
			else
				index++;
		}
		
		return 0;
		
	}
}
