package com.familyTree;

import com.familyTree.R;
import com.familyTree.Fragment1.OnBackListener;
import com.util.db.DataBaseHelper;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TabHost.TabSpec;

public class MainActivity extends FragmentActivity implements OnBackListener {

	// 定义FragmentTabHost对象
	private FragmentTabHost mTabHost;
	private RadioGroup mTabRg;

	private final Class[] fragments = { Fragment1.class, Fragment2.class,
			Fragment3.class, Fragment4.class };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		initView();
		
	}

	private void initView() {
		mTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
		mTabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);
		// 得到fragment的个数
		int count = fragments.length;
		for (int i = 0; i < count; i++) {
			// 为每一个Tab按钮设置图标、文字和内容
			TabSpec tabSpec = mTabHost.newTabSpec(i + "").setIndicator(i + "");
			// 将Tab按钮添加进Tab选项卡中
			mTabHost.addTab(tabSpec, fragments[i], null);
		}

		mTabRg = (RadioGroup) findViewById(R.id.tab_rg_menu);
		mTabRg.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				FragmentManager   fm=getSupportFragmentManager();
				FragmentTransaction ft =fm.beginTransaction();
				switch (checkedId) {
				case R.id.tab_rb_1:

					Fragment1 f1=new Fragment1();
					ft.replace(R.id.realtabcontent, f1)	;

					
					//mTabHost.setCurrentTab(0);
					break;
				case R.id.tab_rb_2:

					Fragment2 f2=new Fragment2();
					ft.replace(R.id.realtabcontent, f2)	;
					break;
				case R.id.tab_rb_3:

					Fragment2 f3=new Fragment2();
					ft.replace(R.id.realtabcontent, f3)	;
					break;
				case R.id.tab_rb_4:

					Fragment2 f4=new Fragment2();
					ft.replace(R.id.realtabcontent, f4)	;
					break;

				default:
					break;
				}
				ft.addToBackStack(null);
				ft.commit();
			}
		});

		//mTabHost.setCurrentTab(0);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onBackPressed() {
	}
	
	@Override
	public void backEvent() {
		Toast.makeText(this, "back", Toast.LENGTH_SHORT).show();
	}

}
