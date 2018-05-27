package com.example.mac.oncqupthands.view.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.mac.oncqupthands.R;
import com.example.mac.oncqupthands.adapter.QuestionDetailAdapter;
import com.example.mac.oncqupthands.adapter.QuestionListAdapter;
import com.example.mac.oncqupthands.bean.QuestionDetailBean;
import com.example.mac.oncqupthands.config.Api;
import com.example.mac.oncqupthands.utils.JsonUtil;
import com.example.mac.oncqupthands.utils.NetUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QuestionDetailActivity extends AppCompatActivity {
    private String questionId = QuestionListAdapter.question_id;
    private RecyclerView recyclerView;
    private QuestionDetailAdapter adapter;
    private Button back;
    private Button question_more;
    private TextView showNothing;
    private LinearLayout ignore;
    private LinearLayout help;
    private List<QuestionDetailBean> datalist;
    private int layout[] ={R.layout.detail_header,R.layout.detail_normal,R.layout.question_footer};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_detail);
        initView();
        initRecyclerView();
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
    private void initView(){
        recyclerView = findViewById(R.id.question_detail_recylcerview);
        back = findViewById(R.id.question_back);
        question_more = findViewById(R.id.question_more);
        showNothing = findViewById(R.id.question_showNothing);
        ignore = findViewById(R.id.question_ignore);
        help = findViewById(R.id.question_detail_help);
        datalist = new ArrayList<>();
        adapter = new QuestionDetailAdapter(this,datalist,layout);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                QuestionDetailActivity.this.finish();
            }
        });
        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(QuestionDetailActivity.this, AQuestionActivity.class);
                startActivity(intent);
            }
        });
        ignore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        View contentView1 = LayoutInflater.from(QuestionDetailActivity.this).inflate(R.layout.popuplayout6,null);
        final PopupWindow popupWindow1 = new PopupWindow(contentView1, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT,true);
        popupWindow1.setTouchable(true);
        question_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow1.showAsDropDown(v);
            }
        });
    }
    private void initRecyclerView(){
        Map<String,String> map = new HashMap<>();
        map.put("question_id",questionId);
        map.put("idNum","091219");
        map.put("stuNum","2017211851");
        NetUtil.Post(Api.getDetailedInfo, map, new NetUtil.Callback() {
            @Override
            public void onSucceed(String response) {
                Log.d("Fxy", "onSucceed: "+response);
                JsonUtil.AddQuesDetailData(response,datalist);
                if(datalist.get(0).getAnswers().size()!=0){
                    showNothing.setText("");
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailed(String response) {

            }
        });
    }

}
