package com.adapter;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

import com.util.ImageUtil;
import com.viewCache.ViewCache;
import com.entity.Person;
import com.familyTree.R;

 // Downloads By http://www.veryhuo.com
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class PersonAdapter extends BaseAdapter {
	
	private List<Person> persons;	//
	private int source;	//
	private LayoutInflater inflater;	//
	
	public PersonAdapter(Context context,List<Person> persons , int source){
		this.persons = persons;
		this.source = source;
		//
		inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	public int getCount() {
		// TODO Auto-generated method stub
		return persons.size();
	}

	public Object getItem(int item) {
		// TODO Auto-generated method stub
		return persons.get(item);
	}

	public long getItemId(int item) {
		// TODO Auto-generated method stub
		return item;
	}

	//
	/* (non-Javadoc)
	 * @see android.widget.Adapter#getView(int, android.view.View, android.view.ViewGroup)
	 * arg
	 * 
	 */
	public View getView(int arg0, View arg1, ViewGroup arg2) {
		
		TextView idview = null;
		TextView nameview = null;
		TextView ageview = null;
		TextView sexview = null;
		ImageView imageview=null;
		// TODO Auto-generated method stub
		if(arg1 == null){
			arg1 = inflater.inflate(source, null);
			idview = (TextView)arg1.findViewById(R.id.id);
			nameview = (TextView)arg1.findViewById(R.id.name);
			ageview = (TextView)arg1.findViewById(R.id.age);
			sexview = (TextView)arg1.findViewById(R.id.sex);
			imageview = (ImageView) arg1.findViewById(R.id.list_image);  

			ViewCache cache = new ViewCache();
			
			cache.id = idview;
			cache.name = nameview;
			cache.age = ageview;
			cache.sex = sexview;
			cache.fileName=imageview;
			arg1.setTag(cache);
		}
		else{
			ViewCache cache = (ViewCache)arg1.getTag();
			idview = cache.id;
			nameview = cache.name;
			ageview = cache.age;
			sexview = cache.sex;
			imageview=cache.fileName;
		}
		
		//
		Person person = persons.get(arg0);
		
		//
		idview.setText(" "+person.getId()+" ");
		nameview.setText(person.getName().toString());
		ageview.setText(" "+person.getAge()+" ");
		sexview.setText(" "+person.getSex()+" ");
		Bitmap bitmap = ImageUtil.getLoacalBitmap(person.getFileName()); //从本地取图
		imageview.setImageBitmap(bitmap);
		
		
		return arg1;
	}


 

}
