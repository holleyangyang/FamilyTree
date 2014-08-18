package com.familyTree;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.Locale;

import com.dao.PersonDao;
import com.entity.Person;
import com.familyTree.R;
import com.util.StringUtil;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Toast;

public class Fragment2 extends Fragment {
	private PersonDao personDao;
	private String fileName ="";
 	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		personDao=new PersonDao(getActivity());
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		final	View parentView = inflater.inflate(R.layout.fragment2, container, false);
		Button bthSub = (Button)parentView.findViewById(R.id.bthSub);
		ImageView iv = (ImageView) parentView.findViewById(R.id.list_image_pre);	
		iv.setDrawingCacheEnabled(true);
		
		bthSub.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ImageView iv = (ImageView) parentView.findViewById(R.id.list_image_pre);	
		 		iv.setDrawingCacheEnabled(false);
				Person person =new Person();
				EditText nameText=(EditText)parentView.findViewById(R.id.username);
				person.setName(nameText.getText().toString());
				
				RadioButton rbSex1=(RadioButton) parentView.findViewById(R.id.sex1);
				RadioButton rbSex0=(RadioButton) parentView.findViewById(R.id.sex0);
				String sex1=StringUtil.getString(rbSex1.getText());
				String sex0=StringUtil.getString(rbSex0.getText());
				String sex="";
				if(!"".equals(sex1)){
					sex = sex1;
				}else{
					sex = sex0;
				}
				EditText ageText=(EditText)parentView.findViewById(R.id.age);
				person.setAge(Integer.valueOf(ageText.getText().toString()));
			
				person.setSex(sex);
				
				person.setFileName(fileName);
				personDao.add( person);
			
			}
		});
		
		Button bthPz=(Button)parentView.findViewById(R.id.bthPZ);
		bthPz.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) { 
				
				Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);//调用android自带的照相机 
				//photoUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI; 
				startActivityForResult(intent, 1); 
				
			}
		});
		
		 
		
		return parentView;
		// return super.onCreateView(, container, savedInstanceState);
	}
	public void onActivityResult(int requestCode, int resultCode, Intent data) { 
		 super.onActivityResult(requestCode, resultCode, data);  
 		 if (resultCode == Activity.RESULT_OK) {  
 	            String sdStatus = Environment.getExternalStorageState();  
	            if (!sdStatus.equals(Environment.MEDIA_MOUNTED)) { // 检测sd是否可用  
	                return;  
	            }  
	            String name = new DateFormat().format("yyyyMMdd_hhmmss",Calendar.getInstance(Locale.CHINA)) + ".jpg";     
	            Toast.makeText(getActivity(), name, Toast.LENGTH_LONG).show();  
	            Bundle bundle = data.getExtras();  
	            Bitmap bitmap = (Bitmap) bundle.get("data");// 获取相机返回的数据，并转换为Bitmap图片格式  
	          
	            FileOutputStream b = null;  
	           //
	            File file = new File("/sdcard/myImage/");  
	            file.mkdirs();// 创建文件夹  
	             fileName = "/sdcard/myImage/"+name;  
	  
	            try {  
	                b = new FileOutputStream(fileName);  
	                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, b);// 把数据写入文件  
	                
	               
	            } catch (FileNotFoundException e) {  
	                e.printStackTrace();  
	            } finally {  
	                try {  
	                    b.flush();  
	                    b.close();  
	                } catch (IOException e) {  
	                    e.printStackTrace();  
	                }  
	            }  
 	            ((ImageView) getActivity().findViewById(R.id.list_image_pre)).setImageBitmap(bitmap);// 将图片显示在ImageView里  
	       
		 }   
	    }  
	}


