package com.example.mac.oncqupthands.view.fragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.Layout;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Toolbar;

import com.example.mac.oncqupthands.R;
import com.example.mac.oncqupthands.adapter.mFragmentPagerAdapter;
import com.example.mac.oncqupthands.utils.CustomPopupWindow;
import com.example.mac.oncqupthands.view.activity.AskQuestionActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mac on 2018/5/25.
 */

@SuppressLint("ValidFragment")
public class QuesMainFragment extends Fragment {
    private Context context;
    private View view;
    private ViewPager viewPager;
    private List<String> titlelist;
    private List<Fragment> fragmentList;
    private mFragmentPagerAdapter adapter;
    private TabLayout tabLayout;
    private FloatingActionButton fab;
    private android.support.v7.widget.Toolbar toolbar;
    private PopupWindow popupWindow;
    public QuesMainFragment(Context context) {
        this.context = context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.question_main_fragment,container,false);
        init();
        return view;
    }
    private void init(){
        titlelist = new ArrayList<>();
        fragmentList = new ArrayList<>();
        viewPager = view.findViewById(R.id.main_viewpager);
        tabLayout = view.findViewById(R.id.main_tab_title);
        toolbar = view.findViewById(R.id.toolbar);
        fab = view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AskQuestionActivity.class);
                startActivity(intent);
            }
        });
        QuesFragment fragment = new QuesFragment(getContext(),"其他");
        QuesFragment fragment2 = new QuesFragment(getContext(),"学习");
        QuesFragment fragment3 = new QuesFragment(getContext(),"情感");
        fragmentList.add(fragment);
        fragmentList.add(fragment2);
        fragmentList.add(fragment3);
        titlelist.add("学习");
        titlelist.add("情感");
        titlelist.add("其他");

        AppCompatActivity appCompatActivity = (AppCompatActivity) getActivity();
        appCompatActivity.setSupportActionBar(toolbar);

        adapter = new mFragmentPagerAdapter(getActivity().getSupportFragmentManager(),fragmentList,titlelist);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }
}
