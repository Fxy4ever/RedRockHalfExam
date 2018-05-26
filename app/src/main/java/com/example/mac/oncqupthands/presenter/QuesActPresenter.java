package com.example.mac.oncqupthands.presenter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;
import com.example.mac.oncqupthands.adapter.mFragmentPagerAdapter;
import com.example.mac.oncqupthands.view.activity.IQuestionActivity;
import com.example.mac.oncqupthands.view.fragment.KebiaoFragment;
import com.example.mac.oncqupthands.view.fragment.QuesFragment;
import com.example.mac.oncqupthands.view.fragment.QuesMainFragment;
import com.example.mac.oncqupthands.view.fragment.UserFragment;

/**
 * Created by mac on 2018/5/25.
 */

public class QuesActPresenter {
    IQuestionActivity iQuestionActivity;

    private List<Fragment> fragmentList = new ArrayList<>();
    private List<String> titleList = new ArrayList<>();

    public QuesActPresenter(IQuestionActivity iQuestionActivity) {
        this.iQuestionActivity = iQuestionActivity;
    }


    public mFragmentPagerAdapter getAdapter(){
        /**
         * 这里可以动态加载
         */
        KebiaoFragment fragment1 = new KebiaoFragment(iQuestionActivity.getContext());
        QuesMainFragment fragment0 = new QuesMainFragment(iQuestionActivity.getContext());
        UserFragment fragment = new UserFragment(iQuestionActivity.getContext());
        fragmentList.add(fragment1);
        fragmentList.add(fragment0);
        fragmentList.add(fragment);

        titleList.add("课表");
        titleList.add("邮问");
        titleList.add("个人");

        FragmentManager fragmentManager = ((AppCompatActivity)iQuestionActivity.getContext()).getSupportFragmentManager();
        mFragmentPagerAdapter adapter = new mFragmentPagerAdapter
                (fragmentManager,fragmentList,titleList);
        return adapter;
    }


}
