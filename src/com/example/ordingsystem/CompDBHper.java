package com.example.ordingsystem;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class CompDBHper extends SQLiteOpenHelper {
//	private static final String DBname = "點餐系統.db";
//	private static final int DBversion = 1;
//	private static final String Order = "訂單";
//	private static final String Item = "商品";
//	private static final String Restaurant = "店家";
//	private static final String Have = "擁有";
	
	private static final String Order = "OO";
	private static final String Item = "II";
	private static final String Restaurant = "RR";
	private static final String Have = "HH";
	
	private static final String crTBsql1 = 
					"CREATE TABLE " + Order + " ( " +
					" Date date NOT NULL, " + 
					" restID VARCHAR(3) NOT NULL, " +
					" restNo INTEGER(4) NOT NULL, " + 					
					" itemName VARCHAR(50) NOT NULL, " +
					" amount INTEGER(4) NOT NULL, " +
					" price INTEGER(4) NOT NULL, " + 
					" OK INTEGER NOT NULL DEFAULT 0, PRIMARY KEY (Date,restID,restNo)); ";   //0=未出餐
					
	private static final String crTBsql2 = 
					"CREATE TABLE " + Item + " ( " +
					" itemID VARCHAR(5) NOT NULL, " +
					" itemName VARCHAR(50) NOT NULL, " +
					" price INTEGER(4) NOT NULL, PRIMARY KEY (itemID)); ";
	private static final String crTBsql3 = 
					"CREATE TABLE " + Restaurant + " ( " +
					" restID VARCHAR(3) NOT NULL, " +
					" loginID VARCHAR(20) NOT NULL, " +
					" loginPW VARCHAR(20) NOT NULL, " +
					" restName VARCHAR(40) NOT NULL, " +
					" TEL VARCHAR(20) NOT NULL, PRIMARY KEY (restID)); ";
	private static final String crTBsql4 = 
			"CREATE TABLE " + Have + " ( " +
			" restID VARCHAR(3) NOT NULL, " +
			" itemID VARCHAR(5) NOT NULL, PRIMARY KEY (restID,itemID)); ";

	public CompDBHper(Context context, String DBname, 
			CursorFactory factory, int DBversion) {
		super(context, DBname, null, DBversion);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(crTBsql1);
		db.execSQL(crTBsql2);
		db.execSQL(crTBsql3);
		db.execSQL(crTBsql4);
		
		System.out.println("Test Data");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS " + Order);
		db.execSQL("DROP TABLE IF EXISTS " + Item);
		db.execSQL("DROP TABLE IF EXISTS " + Restaurant);
		db.execSQL("DROP TABLE IF EXISTS " + Have);
		onCreate(db);
	}
	private String getDate() {
	    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
	    Date date = new Date();
	    return dateFormat.format(date);
	}
	
	
//--------------------------測試用資料-------------------------------
	public void createTB() {
		SQLiteDatabase db = getWritableDatabase();		
		ContentValues[] rec;
		rec = new ContentValues[3];
		for(int i=0; i<rec.length; i++)
			rec[i] = new ContentValues();
		
		rec[0].put("Date", "2012/12/11");
		rec[0].put("restID", "A1");
		rec[0].put("restNo", "0001");
		rec[0].put("itemName", "鐵路便當");
		rec[0].put("amount", "1");
		rec[0].put("price", "65");
		rec[0].put("OK", "0");
		
		rec[1].put("Date", "2012/12/11");
		rec[1].put("restID", "A2");
		rec[1].put("restNo", "0002");
		rec[1].put("itemName", "蛋炒飯");
		rec[1].put("amount", "1");
		rec[1].put("price", "60");
		rec[1].put("OK", "0");
		
		rec[2].put("Date", "2012/12/11");
		rec[2].put("restID", "A1");
		rec[2].put("restNo", "0003");
		rec[2].put("itemName", "雞腿炒飯");
		rec[2].put("amount", "1");
		rec[2].put("price", "75");
		rec[2].put("OK", "1");	
		
		for(ContentValues row : rec){
			db.insert(Order, null, row);
		}
//-----------------------------------------------------------		
		rec = new ContentValues[3];
		for(int i=0; i<rec.length; i++)
			rec[i] = new ContentValues();
		
		rec[0].put("itemID", "00001");
		rec[0].put("itemName", "鐵路便當");
		rec[0].put("price", "65");

		
		rec[1].put("itemID", "00002");
		rec[1].put("itemName", "蛋炒飯");
		rec[1].put("price", "60");
		
		rec[2].put("itemID", "10003");
		rec[2].put("itemName", "雞腿炒飯");
		rec[2].put("price", "75");	
		
		for(ContentValues row : rec){
			db.insert(Item, null, row);
		}
//-----------------------------------------------------------
		rec = new ContentValues[4];
		for(int i=0; i<rec.length; i++)
			rec[i] = new ContentValues();
//													測試資料 :				A1	原味鐵路便當	12345678
//																		A2	木村	87654321
//																		A3	豪客牛排	23456789
		rec[0].put("restID", "A1");
		rec[0].put("loginID", "A1123");
		rec[0].put("loginPW", "123123");
		rec[0].put("restName", "原味鐵路便當");
		rec[0].put("TEL", "12345678");
		
		
		rec[1].put("restID", "A2");
		rec[1].put("loginID", "A2456");
		rec[1].put("loginPW", "456456");
		rec[1].put("restName", "木村");
		rec[1].put("TEL", "87654321");
		
		rec[2].put("restID", "A3");
		rec[2].put("loginID", "A3789");
		rec[2].put("loginPW", "789789");
		rec[2].put("restName", "豪客牛排");
		rec[2].put("TEL", "23456789");
		
		rec[3].put("restID", "A4");
		rec[3].put("loginID", "A4741");
		rec[3].put("loginPW", "741741");
		rec[3].put("restName", "韓式料理");
		rec[3].put("TEL", "34567890");		
		
		for(ContentValues row : rec){
			db.insert(Restaurant, null, row);
		}
//-----------------------------------------------------------
		rec = new ContentValues[3];
		for(int i=0; i<rec.length; i++)
			rec[i] = new ContentValues();
		
		rec[0].put("restID", "A1");
		rec[0].put("itemID", "00001");
		
		rec[1].put("restID", "A2");
		rec[1].put("itemID", "10003");
		
		rec[2].put("restID", "A1");
		rec[2].put("itemID", "00002");
		
		
		for(ContentValues row : rec){
			db.insert(Have, null, row);
		}
		db.close();
	}

//--------------------------查詢菜單--------------------------

	public ArrayList<String> getMenuSet(int rest){
		SQLiteDatabase db = getReadableDatabase();

        String restID = null;
		switch(rest) {
	        case 1: 
	            restID = "A1";
	            break; 
	        case 2: 
	        	restID = "A2";
	            break; 
	        case 3: 
	        	restID = "A3";
	            break; 
	        case 4: 
	        	restID = "A4";
	            break;
		} 
		
		
		String sql = "SELECT itemName,price FROM " + Item + ","+ Have + " WHERE restID = \""+ restID + "\" AND "+Have+".itemID = "+ Item +".itemID;";
		System.out.println(sql);
		Cursor recSet = db.rawQuery(sql, null);
		ArrayList<String> recAry = new ArrayList<String>();
		int columnCount = recSet.getColumnCount();
		while(recSet.moveToNext()){
			String fldSet = "";
			for(int i=0; i<columnCount; i++)
				fldSet += recSet.getString(i) + "#";
			recAry.add(fldSet);	
		}
		recSet.close();
		db.close();
		return recAry;
	}
	
//--------------------------查詢訂單--------------------------
		
	public ArrayList<String> getOrderSet(int rest,String Date){				//Date = YYYY-MM-DD  use getDate() , "/"和"-"不影響SQL輸出
		SQLiteDatabase db = getReadableDatabase();
		
        String restID = null;
		switch(rest) { 
        	
        case 1: 
            restID = "A1";
            break; 
        case 2: 
        	restID = "A2";
            break; 
        case 3: 
        	restID = "A3";
            break; 
        case 4: 
        	restID = "A4";
            break; 
    }
		String sql = "SELECT Date, restNum, itemName, amount, price, OK FROM " + Order + " WHERE restID = \""+restID+"\" AND Date = "+ getDate(); 
		Cursor recSet = db.rawQuery(sql, null);
		
		ArrayList<String> recAry = new ArrayList<String>();
		
		int columnCount = recSet.getColumnCount();
		while(recSet.moveToNext()){
			String fldSet = "";
			for(int i=0; i<columnCount; i++)
				fldSet += recSet.getString(i) + "#";
			recAry.add(fldSet);	
		}
		recSet.close();
		db.close();
		return recAry;
	}

	
//--------------------------帳密抓取--------------------------	

	public ArrayList<String> getIDset(){	
		SQLiteDatabase db = getReadableDatabase();
		
		String sql = "SELECT loginID,loginPW FROM " + Restaurant;
		Cursor recSet = db.rawQuery(sql, null);
		
		ArrayList<String> recAry = new ArrayList<String>();
		
		int columnCount = recSet.getColumnCount();
		
		while(recSet.moveToNext()){
			String fldSet = "";
			for(int i=0; i<columnCount; i++)
				fldSet += recSet.getString(i) + "#";
			recAry.add(fldSet);	
		}
		recSet.close();
		db.close();
		return recAry;
	}
		
	//	以下尚未完成
//--------------------------菜單設定--------------------------
	
	public void getTestSet(String tableName){
		SQLiteDatabase db = getReadableDatabase();
		String sql = "SELECT * FROM " + tableName;
		Cursor recSet = db.rawQuery(sql, null);
		int columnCount = recSet.getColumnCount();
		while(recSet.moveToNext()){
			String fldSet = "";
			for(int i=0; i<columnCount; i++)
				fldSet += recSet.getString(i) + "#";
			System.out.println(fldSet);
		}
		recSet.close();
		db.close();		
	}
	
	//新增
	public long insertMenu(String itemID,String itemName,int price) {
		//System.out.println("INHere55555");
		SQLiteDatabase db = getWritableDatabase();
		//System.out.println("INHere33333");
		ContentValues rec = new ContentValues();
		rec.put("itemID", itemID);
		rec.put("itemName", itemName);
		rec.put("price", price);
		//System.out.println(rec.toString());
		long rowID  = db.insert(Item, null, rec);
		db.close();
		return rowID ;
	}
	
	
	public long insertHave(String restID,String itemID) {
		SQLiteDatabase db = getWritableDatabase();
		ContentValues rec = new ContentValues();
		rec.put("restID", restID);
		rec.put("itemID", itemID);
		long rowID  = db.insert(Have, null, rec);
		db.close();
		return rowID ;
	}

	
	public ArrayList<String> getRecSet(){
		SQLiteDatabase db = getReadableDatabase();
		String sql = "SELECT * FROM " + Item;
		Cursor recSet = db.rawQuery(sql, null);
		ArrayList<String> recAry = new ArrayList<String>();
		int columnCount = recSet.getColumnCount();
		while(recSet.moveToNext()){
			String fldSet = "";
			for(int i=0; i<columnCount; i++)
				fldSet += recSet.getString(i) + "#";
			recAry.add(fldSet);			
		}
		recSet.close();
		db.close();
		return recAry;		
	}
	
	public ArrayList<String> getOrderSet(String restID){
		SQLiteDatabase db = getReadableDatabase();
		//只抓未出餐的
		String sql = "SELECT Date,restID,restNo,itemName,amount,amount*price,OK FROM " + Order+" WHERE OK=0 AND restID = \""+restID+"\"";
		Cursor recSet = db.rawQuery(sql, null);
		ArrayList<String> recAry = new ArrayList<String>();
		int columnCount = recSet.getColumnCount();
		while(recSet.moveToNext()){
			String fldSet = "";
			for(int i=0; i<columnCount; i++)
				fldSet += recSet.getString(i) + " ";
			recAry.add(fldSet);			
		}
		recSet.close();
		db.close();
		return recAry;		
	}
	
	public ArrayList<String> getItemSet(){
		SQLiteDatabase db = getReadableDatabase();
		String sql = "SELECT itemName FROM " + Item;
		Cursor recSet = db.rawQuery(sql, null);
		ArrayList<String> recAry = new ArrayList<String>();
		while(recSet.moveToNext()){
			recAry.add(recSet.getString(0));
			System.out.println(recSet.getString(0));
		}
		recSet.close();
		db.close();
		return recAry;		
	}
	
	public String getdailyProfit(){
		SQLiteDatabase db = getReadableDatabase();
		String sql = "SELECT price FROM OO WHERE strftime('%m',ActionDate) AS Month";
		System.out.println(sql);
		Cursor recSet = db.rawQuery(sql, null);
		String recAry = new String();
			recAry = recSet.getString(0);
			System.out.println(recAry);
		recSet.close();
		db.close();
		return recAry;		
	}
	
	public ArrayList<String> getRestSet(String ItemName){
		SQLiteDatabase db = getReadableDatabase();
//		SELECT restID FROM item,have WHERE itemName = "鐵路便當" AND item.itemID = have.itemID 
		String sql = "SELECT restID,price FROM " + Have+","+Item+" WHERE itemName = \""+ItemName+"\" AND " + Have +".itemID = "+ Item +".itemID";
		//System.out.println(sql);
		Cursor recSet = db.rawQuery(sql, null);
		ArrayList<String> recAry = new ArrayList<String>();
		while(recSet.moveToNext()){
			recAry.add(recSet.getString(0));
			recAry.add(recSet.getString(1));
//			System.out.println(recSet.getString(0)+" "+recSet.getString(1));
		}
		recSet.close();
		db.close();
		return recAry;		
	}
	
	
	
	public int updateRec(String restID,String itemID,String itemName,int price){
		SQLiteDatabase db = getWritableDatabase();
		String sql = "SELECT * FROM " + Item +" , " + Have + " WHERE " + Have + ".restID = \"" + restID + "\"";
		Cursor recSet = db.rawQuery(sql, null);
		if(recSet.getCount()!=0){
			ContentValues rec = new ContentValues();
			rec.put("itemID", itemID);
			rec.put("itemName", itemName);
			rec.put("price", price);
			String whereClause = "itemID='" + itemID + "'";
			int rowsAffected = db.update(Item, rec, whereClause, null);
			db.close();
			return rowsAffected;
		}else {
			db.close();
			return -1;
		}
	}
	
	public int updateOrder(String restNo){
		SQLiteDatabase db = getWritableDatabase();
		String sql = "SELECT OK FROM " + Order + " WHERE restNo = \"" + restNo + "\"";
		Cursor recSet = db.rawQuery(sql, null);
		if(recSet.getCount()!=0){
			ContentValues rec = new ContentValues();
			rec.put("OK", 1);
			String whereClause = "restNo ='" + restNo + "'";
			int rowsAffected = db.update(Order, rec, whereClause, null);
			db.close();
			return rowsAffected;
		}else {
			db.close();
			return -1;
		}
	}
	
	public int deleteMenuRec(String itemName){
		SQLiteDatabase db = getWritableDatabase();
		String sql = "SELECT * FROM " + Item;
		Cursor recSet = db.rawQuery(sql, null);
		if(recSet.getCount()!=0){
			String whereClause = "itemName='" + itemName + "'";
			int rowsAffected = db.delete(Item, whereClause, null);
			db.close();
			return rowsAffected;
		}else {
			db.close();
			return -1;
		}
	}
	
	
//--------------------------新增訂單--------------------------
	
	public long insertOrder(String restID,String itemName,int amount,int price) {
		SQLiteDatabase db = getWritableDatabase();
		
		String sql = "SELECT MAX(restNo) FROM " + Order;
//		System.out.println(sql);
		Cursor recSet = db.rawQuery(sql, null);
		recSet.moveToNext();
//		System.out.println(recSet.getString(0));
		int maxValue = Integer.parseInt(recSet.getString(0));
		
		ContentValues rec = new ContentValues();
		rec.put("Date", getDate());
		rec.put("restID", restID);
		rec.put("restNo", String.format("%04d", maxValue+1));
//		System.out.println(String.format("%04d", maxValue));
		rec.put("itemName", itemName);
		rec.put("amount", amount);
		rec.put("price", price);
		
		System.out.println(rec);
		long rowID = db.insert(Order, null, rec);
		db.close();
		return rowID;
	}
}
