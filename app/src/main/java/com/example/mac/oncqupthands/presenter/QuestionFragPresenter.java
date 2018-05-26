package com.example.mac.oncqupthands.presenter;

import android.app.Activity;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.View;

import com.example.mac.oncqupthands.R;
import com.example.mac.oncqupthands.adapter.QuestionListAdapter;
import com.example.mac.oncqupthands.bean.QuestionBean;
import com.example.mac.oncqupthands.model.QuesListImp;
import com.example.mac.oncqupthands.model.QuesListInterface;
import com.example.mac.oncqupthands.utils.JsonUtil;
import com.example.mac.oncqupthands.view.fragment.IQuesFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mac on 2018/5/25.
 */

public class QuestionFragPresenter {
    private IQuesFragment fragment;
    private QuesListInterface quesListInterface;
    private List<QuestionBean> datalist=new ArrayList<>();
    private QuestionListAdapter adapter;
    private int[] layout ={R.layout.question_item,R.layout.question_footer};
    private boolean isRefresh=false;


    public QuestionFragPresenter(IQuesFragment fragment) {
        this.fragment = fragment;
        quesListInterface = new QuesListImp();
    }

    public void loadData(){
        quesListInterface.loadQuestionList(fragment.getKind(),new QuesListInterface.loadCallBack() {
            @Override
            public void Succeed(String response) {
                JsonUtil.AddQuestionList(response,datalist);
                ((Activity)fragment.getmContext()).runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adapter.notifyDataSetChanged();
                    }
                });
            }

            @Override
            public void Failed(String response) {

            }
        });
    }
    public QuestionListAdapter initAdapter(){
        adapter = new QuestionListAdapter(fragment.getmContext(),datalist,layout);
        return adapter;
    }

   public void Refresh(){
       final SwipeRefreshLayout swipeRefreshLayout = fragment.getSwipreRefreshLayout();
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if(!isRefresh){
                    isRefresh = true;
                    swipeRefreshLayout.setRefreshing(false);
                    RefreshData();
                    isRefresh = false;
                }
            }
        });
    }
    private void RefreshData(){
        Log.d("Fxy", "RefreshData: ");
        quesListInterface.refreshList(fragment.getKind(), new QuesListInterface.loadCallBack() {
            @Override
            public void Succeed(String response) {
                Log.d("Fxy", "Succeed: "+datalist.size());
                datalist.clear();
                Log.d("Fxy", "Succeed: "+datalist.size());

                JsonUtil.AddQuestionList(response,datalist);
                Log.d("Fxy", "Succeed: "+datalist.size());

                ((Activity)fragment.getmContext()).runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adapter.notifyDataSetChanged();
                    }
                });
            }

            @Override
            public void Failed(String response) {

            }
        });
    }
}
