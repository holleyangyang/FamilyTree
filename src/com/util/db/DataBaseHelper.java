package com.util.db;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseHelper  extends SQLiteOpenHelper{

	 private final static String DB_NAME ="test.db";//数据库名  
	    private final static int VERSION = 1;//版本号  
	      
	    //自带的构造方法  
	    public DataBaseHelper(Context context, String name, CursorFactory factory,  
	            int version) {  
	        super(context, name, factory, version);  
	    }  
	      
	    //为了每次构造时不用传入dbName和版本号，自己得新定义一个构造方法  
	    public DataBaseHelper(Context cxt){  
	        this(cxt, DB_NAME, null, VERSION);//调用上面的构造方法  
	    }  
	      
	    //版本变更时  
	    public DataBaseHelper(Context cxt,int version) {  
	        this(cxt,DB_NAME,null,version);  
	    }  
	      
	    //当数据库创建的时候调用  
	    public void onCreate(SQLiteDatabase db) {  
	        String sql = "create table student(" +  
	                     "id integer primary key autoincrement," +  
	                     "name varchar(20)," +  
	                     "age int)";  
	          
	        db.execSQL(sql);  
	    }  
	      
	    //版本更新时调用  
	    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {  
	        String sql  = "update student ....";//自己的Update操作  
	        db.execSQL(sql);  
	    }  

}
