package com.example.mac.oncqupthands.view.fragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
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
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.mac.oncqupthands.R;
import com.example.mac.oncqupthands.adapter.mFragmentPagerAdapter;
import com.example.mac.oncqupthands.utils.ToastUtil;
import com.example.mac.oncqupthands.view.activity.AskQuestionActivity;
import com.example.mac.oncqupthands.view.activity.LoginActivity;
import com.example.mac.oncqupthands.view.activity.QuestionListActivity;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
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
    private PopupWindow popupWindow1;
    private android.support.v7.widget.Toolbar toolbar;
    private CoordinatorLayout parent;
    public static String choose_kind = "";
    public QuesMainFragment(Context context) {
        this.context = context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.question_main_fragment,container,false);
        showPop();
        init();
        return view;
    }
    private void init(){
        titlelist = new ArrayList<>();
        fragmentList = new ArrayList<>();
        parent = view.findViewById(R.id.question_fragmentLayout);
        viewPager = view.findViewById(R.id.main_viewpager);
        viewPager.setOffscreenPageLimit(2);
        tabLayout = view.findViewById(R.id.main_tab_title);
        toolbar = view.findViewById(R.id.toolbar);
        fab = view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow1.showAtLocation(parent,Gravity.BOTTOM,0,0);
            }
        });
        QuesFragment fragment = new QuesFragment(getContext(),"其他");
        QuesFragment fragment2 = new QuesFragment(getContext(),"学习");
        QuesFragment fragment3 = new QuesFragment(getContext(),"情感");
        QuesFragment fragment4 = new QuesFragment(getContext(),"生活");
        fragmentList.add(fragment2);
        fragmentList.add(fragment3);
        fragmentList.add(fragment4);
        fragmentList.add(fragment);


        titlelist.add("学习");
        titlelist.add("情感");
        titlelist.add("生活");
        titlelist.add("其他");


        AppCompatActivity appCompatActivity = (AppCompatActivity) getActivity();
        appCompatActivity.setSupportActionBar(toolbar);

        adapter = new mFragmentPagerAdapter(getActivity().getSupportFragmentManager(),fragmentList,titlelist);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }
    private void showPop(){
        View contentView1 = LayoutInflater.from(getContext()).inflate(R.layout.popuplayout3,null);
        popupWindow1 = new PopupWindow(contentView1, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT,true);
        popupWindow1.setFocusable(true);
        popupWindow1.setOutsideTouchable(false);
        final ImageButton study = contentView1.findViewById(R.id.pop_help_study);
        final ImageButton life = contentView1.findViewById(R.id.pop_help_life);
        final ImageButton emotion = contentView1.findViewById(R.id.pop_help_emotion);
        final ImageButton other = contentView1.findViewById(R.id.pop_help_other);
        Button cancel = contentView1.findViewById(R.id.pop_help_cancel);
        Button next = contentView1.findViewById(R.id.pop_help_next);
            study.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                choose_kind = "学习";
//                        try {
//                            choose_kind = URLEncoder.encode("学习","utf-8");
//                        } catch (UnsupportedEncodingException e) {
//                            e.printStackTrace();
//                        }
                        life.setBackgroundResource(R.drawable.life_touch);
                        emotion.setBackgroundResource(R.drawable.emotion_touch);
                        other.setBackgroundResource(R.drawable.other_touch);
                        study.setBackgroundResource(R.drawable.study_touched);
                    }
            });
            life.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    choose_kind = "生活";
//                        try {
//                            choose_kind = URLEncoder.encode("生活","utf-8");
//                        } catch (UnsupportedEncodingException e) {
//                            e.printStackTrace();
//                        }
                        study.setBackgroundResource(R.drawable.study_touch);
                        emotion.setBackgroundResource(R.drawable.emotion_touch);
                        other.setBackgroundResource(R.drawable.other_touch);
                        life.setBackgroundResource(R.drawable.life_touched);
                }
            });
            emotion.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    choose_kind = "情感";
//                       try {
//
//                          choose_kind = URLEncoder.encode("情感","utf-8");
//                       } catch (UnsupportedEncodingException e) {
//                           e.printStackTrace();
//                       }
                       life.setBackgroundResource(R.drawable.life_touch);
                       study.setBackgroundResource(R.drawable.study_touch);
                       other.setBackgroundResource(R.drawable.other_touch);
                       emotion.setBackgroundResource(R.drawable.emotion_touched);
                }
            });
            other.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    choose_kind = "其他";
//                       try {
//                           choose_kind = URLEncoder.encode("其他","utf-8");
//                       } catch (UnsupportedEncodingException e) {
//                           e.printStackTrace();
//                       }
                       life.setBackgroundResource(R.drawable.life_touch);
                       study.setBackgroundResource(R.drawable.study_touch);
                       emotion.setBackgroundResource(R.drawable.emotion_touch);
                       other.setBackgroundResource(R.drawable.other_touched);
                }
            });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow1.dismiss();
            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!choose_kind.equals("")){
                    Intent intent = new Intent(getActivity(),AskQuestionActivity.class);
                    startActivity(intent);
                }else{
                    new ToastUtil(getActivity()
                            ,R.layout.toast_layout
                            ,"请选择求助类型")
                            .setColor(Color.WHITE,Color.BLACK)
                            .show(3000);
                }
            }
        });
        popupWindow1.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                life.setBackgroundResource(R.drawable.life_touch);
                study.setBackgroundResource(R.drawable.study_touch);
                emotion.setBackgroundResource(R.drawable.emotion_touch);
                other.setBackgroundResource(R.drawable.other_touch);
                choose_kind = "";
            }
        });
    }
}
