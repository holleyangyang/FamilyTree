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

	 private final static String DB_NAME ="test.db";//���ݿ���  
	    private final static int VERSION = 1;//�汾��  
	      
	    //�Դ��Ĺ��췽��  
	    public DataBaseHelper(Context context, String name, CursorFactory factory,  
	            int version) {  
	        super(context, name, factory, version);  
	    }  
	      
	    //Ϊ��ÿ�ι���ʱ���ô���dbName�Ͱ汾�ţ��Լ����¶���һ�����췽��  
	    public DataBaseHelper(Context cxt){  
	        this(cxt, DB_NAME, null, VERSION);//��������Ĺ��췽��  
	    }  
	      
	    //�汾���ʱ  
	    public DataBaseHelper(Context cxt,int version) {  
	        this(cxt,DB_NAME,null,version);  
	    }  
	      
	    //�����ݿⴴ����ʱ�����  
	    public void onCreate(SQLiteDatabase db) {  
	        String sql = "create table student(" +  
	                     "id integer primary key autoincrement," +  
	                     "name varchar(20)," +  
	                     "age int)";  
	          
	        db.execSQL(sql);  
	    }  
	      
	    //�汾����ʱ����  
	    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {  
	        String sql  = "update student ....";//�Լ���Update����  
	        db.execSQL(sql);  
	    }  

}
