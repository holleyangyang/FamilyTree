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

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
 
public class Fragment2 extends Fragment {
	private PersonDao personDao;
	private String fileName = "";
	/* 用来标识请求照相功能的activity */
	private static final int CAMERA_WITH_DATA = 1001;
	/* 用来标识请求gallery的activity */
	private static final int PHOTO_PICKED_WITH_DATA = 1002;
	// 用来保存图片
	private Bitmap bitMap;

 
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		personDao = new PersonDao(getActivity());
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		final View parentView = inflater.inflate(R.layout.fragment2, container,
				false);
		Button bthSub = (Button) parentView.findViewById(R.id.bthSub);

		bthSub.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				ImageView iv = (ImageView) parentView
						.findViewById(R.id.image_pre);
				iv.setDrawingCacheEnabled(false);
				Person person = new Person();
				EditText nameText = (EditText) parentView
						.findViewById(R.id.name);
				person.setName(nameText.getText().toString());

				RadioButton rbSex1 = (RadioButton) parentView
						.findViewById(R.id.sex1);
				RadioButton rbSex0 = (RadioButton) parentView
						.findViewById(R.id.sex0);
				String sex1 = StringUtil.getString(rbSex1.getText());
				String sex0 = StringUtil.getString(rbSex0.getText());
				String sex = "";
				if (!"".equals(sex1)) {
					sex = sex1;
				} else {
					sex = sex0;
				}
				EditText ageText = (EditText) parentView.findViewById(R.id.age);
				person.setAge(Integer.valueOf(ageText.getText().toString()));

				person.setSex(sex);

				person.setFileName(fileName);
				personDao.add(person);

			}
		});

		/**
		 * 拍照
		 */
		Button btnPz = (Button) parentView.findViewById(R.id.bthPZ);
		btnPz.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);// 调用android自带的照相机
				// photoUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
				startActivityForResult(intent, CAMERA_WITH_DATA);

			}
		});
		Button btnXC = (Button) parentView.findViewById(R.id.bthXC);
		btnXC.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent localIntent = new Intent();
				localIntent.setType("image/*");
				localIntent.setAction("android.intent.action.GET_CONTENT");
				Intent localIntent2 = Intent.createChooser(localIntent, "选择图片");
				startActivityForResult(localIntent2, PHOTO_PICKED_WITH_DATA);
			}
		});
		
		
		RadioGroup rb=(RadioGroup) parentView.findViewById(R.id.radioGroupSex);
		rb.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				// TODO Auto-generated method stub
				
			}
			
		});

		return parentView;
		// return super.onCreateView(, container, savedInstanceState);
	}
	@SuppressWarnings("static-access")
	@SuppressLint("SdCardPath")
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == Activity.RESULT_OK) {
			FileOutputStream b = null;
			File file = new File("/sdcard/myImage/");
			file.mkdirs();// 创建文件夹
			String name = new DateFormat().format("yyyyMMdd_hhmmss",
					Calendar.getInstance(Locale.CHINA)) + ".jpg";
			fileName = "/sdcard/myImage/" + name;
			try {
				b = new FileOutputStream(fileName);
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}finally{
			
				switch (requestCode) {
					case CAMERA_WITH_DATA:
						String sdStatus = Environment.getExternalStorageState();
						if (!sdStatus.equals(Environment.MEDIA_MOUNTED)) { // 检测sd是否可用
							return;
						}
		
						//Toast.makeText(getActivity(), name, Toast.LENGTH_LONG).show();
						Bundle bundle = data.getExtras();
						bitMap = (Bitmap) bundle.get("data");// 获取相机返回的数据，并转换为Bitmap图片格式
						break;
					case PHOTO_PICKED_WITH_DATA:
						if (bitMap != null && !bitMap.isRecycled()) {
							bitMap.recycle();
						}
						Uri selectedImageUri = data.getData();
		 				
		 				if (selectedImageUri != null) {
							try {
								bitMap = BitmapFactory.decodeStream(getActivity()
										.getContentResolver().openInputStream(
												selectedImageUri));
							} catch (FileNotFoundException e) {
								e.printStackTrace();
							}
						}
	 					
					break;
				}
				bitMap.compress(Bitmap.CompressFormat.JPEG, 100, b);// 把数据写入文件
			try {
			
				b.flush();
				b.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		((ImageView) getView().findViewById(R.id.image_pre)).setImageBitmap(bitMap);// 将图片显示在ImageView里

		}
	}
}
