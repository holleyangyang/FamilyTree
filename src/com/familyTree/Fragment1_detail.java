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
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
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

public class Fragment1_detail extends Fragment {
	private PersonDao personDao;
	private FragmentManager  fm=null;
  	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		 
		personDao=new PersonDao(getActivity());
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		final	View parentView = inflater.inflate(R.layout.fragment1_detail, container, false);
		Button bthReturn =(Button) parentView.findViewById(R.id.btnReturn);
		bthReturn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				fm=getActivity().getSupportFragmentManager();
				FragmentTransaction ft =fm.beginTransaction();
				Fragment1 fragment1= new Fragment1();
				ft.replace(R.id.realtabcontent, fragment1)	;
				ft.addToBackStack(null);
				ft.commit();
			}
		});
		return parentView;
		// return super.onCreateView(, container, savedInstanceState);
	}
	 
	}


