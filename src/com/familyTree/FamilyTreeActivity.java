package com.familyTree;

import java.io.IOException;

import com.util.db.DataBaseHelper;

import android.app.Activity;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class FamilyTreeActivity extends Activity {
	@Override
	protected void onCreate(android.os.Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		Button btnSub=(Button) findViewById(R.id.bthSub);
		
		
		btnSub.setOnClickListener(new View.OnClickListener() {
			TextView username =(TextView) findViewById(R.id.username);
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				System.out.println("userNAEM:=============="+username.getText().toString());
				DataBaseHelper myDbHelper = new DataBaseHelper(null);
		         myDbHelper = new DataBaseHelper(FamilyTreeActivity.this);
		  
		         String sql = "insert into student (name,age)values(?,?)";  
		         SQLiteDatabase db = myDbHelper.getWritableDatabase();  
		         db.execSQL(sql, new Object[] { username.getText(), "34" });  
			}
		});
	}
	
	
	

}
