package com.familyTree;

import java.util.List;

import com.adapter.PersonAdapter;
import com.dao.PersonDao;
import com.entity.Person;
import com.familyTree.R;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class Fragment1 extends Fragment {
	OnBackListener mListener;
	private PersonDao personDao;
	private ListView listView1;
	private FragmentManager  fm=null;

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
    	listView1.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				fm=getActivity().getSupportFragmentManager();
				FragmentTransaction ft =fm.beginTransaction();
				Fragment1_detail detail= new Fragment1_detail();
				ft.replace(R.id.realtabcontent, detail)	;
				ft.addToBackStack(null);
				ft.commit();
			}
    	});
    	
		return parentView;
		// return super.onCreateView(, container, savedInstanceState);
	}
	 

}
