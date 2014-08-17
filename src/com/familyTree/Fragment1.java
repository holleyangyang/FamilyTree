package com.familyTree;

import java.util.List;

import com.adapter.PersonAdapter;
import com.dao.PersonDao;
import com.entity.Person;
  import com.familyTree.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

public class Fragment1 extends Fragment {
	OnBackListener mListener;
	private PersonDao personDao;
	private ListView listView1;

	public interface OnBackListener {
		public void backEvent();
	}
	
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnBackListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement OnArticleSelectedListener");
        }
    }


	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		personDao=new PersonDao(getActivity());

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View parentView = inflater.inflate(R.layout.fragment1, container, false);
		
		listView1 = (ListView) parentView.findViewById(R.id.listView1);
    	List<Person> students = personDao.page(0, 15);
    	
    	PersonAdapter adapter = new PersonAdapter(getActivity(), students, R.layout.list_item);
    	
    	listView1.setAdapter(adapter);
		return parentView;
		// return super.onCreateView(, container, savedInstanceState);
	}
	 

}
