package com.familyTree;



import com.dao.PersonDao;
import com.entity.Person;
import com.familyTree.R;
import com.util.ImageUtil;

import android.graphics.Bitmap;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;


public class Fragment1_detail extends Fragment {
	private PersonDao personDao;
	private FragmentManager fm = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		personDao = new PersonDao(getActivity());
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		final View parentView = inflater.inflate(R.layout.fragment1_detail,
				container, false);
		Button bthReturn = (Button) parentView.findViewById(R.id.btnReturn);
		bthReturn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				fm = getActivity().getSupportFragmentManager();
				FragmentTransaction ft = fm.beginTransaction();
				Fragment1 fragment1 = new Fragment1();
				ft.replace(R.id.realtabcontent, fragment1);
				ft.addToBackStack(null);
				ft.commit();
			}
		});

		Person person = personDao.getPersonDetail(getShowIndex());

		TextView txtName = (TextView) parentView.findViewById(R.id.name);
		txtName.setText(person.getName());

		TextView txtSex = (TextView) parentView.findViewById(R.id.sex);
		txtSex.setText(person.getSex());
		
		TextView txtAge = (TextView) parentView.findViewById(R.id.age);
		txtAge.setText(Integer.valueOf(person.getAge()).toString());
		
		ImageView imageview = (ImageView) parentView.findViewById(R.id.image_pre);
		
		

		Bitmap bitmap = ImageUtil.getLoacalBitmap(person.getFileName()); // 从本地取图
		imageview.setImageBitmap(bitmap);

		
		
		return parentView;
		// return super.onCreateView(, container, savedInstanceState);
	}

	public static Fragment1_detail newInstance(int index) {

		Fragment1_detail df = new Fragment1_detail();

		Bundle args = new Bundle();

		args.putInt("index", index);

		df.setArguments(args);

		return df;

	}

	public int getShowIndex() {

		int index = getArguments().getInt("index", 0);
		System.out.println("index===" + index);
		return index;

	}
	
	
	


}
