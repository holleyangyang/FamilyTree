package com.dao;

import java.util.ArrayList;
import java.util.List;

import com.entity.Person;
import com.util.db.DataBaseHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


/**
 * @author Administrator
 *
 */
public class PersonDao {
  
	private DataBaseHelper helper;
    private SQLiteDatabase db;

    public PersonDao(Context context)
    {
         helper = new DataBaseHelper(context);
        // 因为getWritableDatabase内部调用了mContext.openOrCreateDatabase(mName, 0,
        // mFactory);
        // 所以要确保context已初始化,我们可以把实例化DBManager的步骤放在Activity的onCreate里
        db = helper.getWritableDatabase();
    }
    /**
     * 
     * @param context
     * @param persons
     */
	   public  void add(Context context,List<Person> persons)
	    {

	        // 采用事务处理，确保数据完整性
	        db.beginTransaction(); // 开始事务
	        try
	        {
	            for (Person person : persons)
	            {
	                db.execSQL("INSERT INTO Person(id,name,age,sex) VALUES(null, ?, ?, ?)", new Object[] { person.getName(),
	                        person.getAge(), person.getSex() });
	                // 带两个参数的execSQL()方法，采用占位符参数？，把参数值放在后面，顺序对应
	                // 一个参数的execSQL()方法中，用户输入特殊字符时需要转义
	                // 使用占位符有效区分了这种情况
	            }
	            db.setTransactionSuccessful(); // 设置事务成功完成
	        }
	        finally
	        {
	            db.endTransaction(); // 结束事务
	            //db.close();
	        }
	    }
	   /**
	    * 
	    * @param person
	    */
	   public  void add(Person person)
	    {
 	        // 采用事务处理，确保数据完整性
	        db.beginTransaction(); // 开始事务
	        try
	        {
	        	ContentValues values = new ContentValues();
	        	values.put("name",person.getName());
	        	values.put("age",person.getAge());
	        	values.put("sex",person.getSex());
//	         
	        	values.put("fileName",person.getFileName());
	        	
	        	
	        	db.insert("Person",null, values);
	                
	                // 带两个参数的execSQL()方法，采用占位符参数？，把参数值放在后面，顺序对应
	                // 一个参数的execSQL()方法中，用户输入特殊字符时需要转义
	                // 使用占位符有效区分了这种情况
	            
	            db.setTransactionSuccessful(); // 设置事务成功完成
	        }
	        finally
	        {
	            db.endTransaction(); // 结束事务
 	        }
	    }
	   /**
	    * 
	    * @param start
	    * @param end
	    * @return
	    */
		public List<Person> page(int start , int end){
 			List<Person> page = new ArrayList<Person>();
 			Cursor cur = db.rawQuery("select id,name,age,sex,fileName from person order by id limit ?,?",
					new String[]{String.valueOf(start),String.valueOf(end)});
			
			while(cur.moveToNext()){
				int id = cur.getInt(cur.getColumnIndex("id"));
				String name = cur.getString(cur.getColumnIndex("name"));
				int age= cur.getInt(cur.getColumnIndex("age"));
				String sex= cur.getString(cur.getColumnIndex("sex"));
				String fileName = cur.getString(cur.getColumnIndex("fileName"));
				page.add(new Person(id,name,age,sex,fileName));
			}
			
			cur.close();
 			return page;
		}
		/**
		 * 
		 * @param id
		 * @return
		 */
		public Person getPersonDetail(int id){
			Person person =new Person();
 			List<Person> page = new ArrayList<Person>();
 			Cursor cur = db.rawQuery("select id,name,age,sex,fileName from person where id=? ",new String[]{String.valueOf(id)});
			System.out.println("==========1========="+cur.getCount());
			
			while(cur.moveToNext()){
			 
				String name = cur.getString(cur.getColumnIndex("name"));
				int age= cur.getInt(cur.getColumnIndex("age"));
				String sex= cur.getString(cur.getColumnIndex("sex"));
				String fileName = cur.getString(cur.getColumnIndex("fileName"));
				page.add(new Person(id,name,age,sex,fileName));
				System.out.println("===============");
			}
			
			cur.close();
			
			System.out.println("page.size()==="+page.size());
			if(page.size()>0){
				person=page.get(0);
			}
			System.out.println("====="+person);
			return person;
		}
}
