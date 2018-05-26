package com.example.mac.oncqupthands.view.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.example.mac.oncqupthands.R;
import com.example.mac.oncqupthands.adapter.CustomVIewPager;
import com.example.mac.oncqupthands.adapter.mFragmentPagerAdapter;
import com.example.mac.oncqupthands.presenter.QuesActPresenter;
import com.example.mac.oncqupthands.view.fragment.KebiaoFragment;
import com.example.mac.oncqupthands.view.fragment.QuesFragment;
import com.example.mac.oncqupthands.view.fragment.UserFragment;

public class QuestionListActivity extends AppCompatActivity implements IQuestionActivity {
    private mFragmentPagerAdapter fragmentPagerAdapter;
    private CustomVIewPager viewPager;
    private QuesActPresenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_list);
        BottomNavigationView navigation =findViewById(R.id.navigation);

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        FindView();
        initFragment();
        initChenjin();
    }

    private void initChenjin(){
        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @SuppressLint("ResourceType")
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_kebiao:
                    viewPager.setCurrentItem(0);
                    return true;
                case R.id.navigation_question:
                    viewPager.setCurrentItem(1);
                    return true;
                case R.id.navigation_user:
                    viewPager.setCurrentItem(2);
                    return true;
            }
            return false;
        }
    };
    private void FindView(){
        viewPager = findViewById(R.id.viewpager);
        viewPager.setScanScroll(false);
        viewPager.setOffscreenPageLimit(2);
        presenter = new QuesActPresenter(this);
    }
    private void initFragment(){
        fragmentPagerAdapter = presenter.getAdapter();
        viewPager.setAdapter(fragmentPagerAdapter);
    }

    @Override
    public Context getContext() {
        return this;
    }
}
