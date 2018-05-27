package com.example.mac.oncqupthands.view.activity;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
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
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.mac.oncqupthands.R;
import com.example.mac.oncqupthands.adapter.CommentsAdapter;
import com.example.mac.oncqupthands.adapter.QuestionDetailAdapter;
import com.example.mac.oncqupthands.bean.QuestionDetailBean;
import com.example.mac.oncqupthands.bean.RemarkBean;
import com.example.mac.oncqupthands.config.Api;
import com.example.mac.oncqupthands.mUser;
import com.example.mac.oncqupthands.utils.JsonUtil;
import com.example.mac.oncqupthands.utils.NetUtil;
import com.example.mac.oncqupthands.utils.ToastUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommentActivity extends AppCompatActivity {
    private Button back;
    private Button good;
    private Button comment;
    private RecyclerView recyclerView;
    private CommentsAdapter adapter;
    private List<RemarkBean> datalist;
    private mUser user;
    private TextView good_num;
    private int num1;
    private TextView comment_num;
    private int num2;
    private int[] layouts={R.layout.comment_header,R.layout.comment_normal};
    private boolean isPraise = false;
    private LinearLayout parent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
        user = (mUser)getApplication();
        initView();
        initData();
        initChenjin();
        initPop();
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
        back = findViewById(R.id.comment_back);
        good = findViewById(R.id.comment_good);
        parent = findViewById(R.id.comment_activity);
        comment = findViewById(R.id.comment_comments);
        good_num = findViewById(R.id.comment_good_num);
        comment_num = findViewById(R.id.comment_comments_num);
        num1 = Integer.valueOf(QuestionDetailAdapter.answersBean.getPraise_num());
        num2 = Integer.valueOf(QuestionDetailAdapter.answersBean.getComment_num());
        good_num.setText("("+num1+")");
        comment_num.setText("("+num2+")");
        recyclerView = findViewById(R.id.comment_recylcerview);
        datalist = new ArrayList<>();
        adapter = new CommentsAdapter(this,datalist,layouts);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CommentActivity.this.finish();
            }
        });
        GOOD();
        good.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if(!isPraise){
                   Map<String,String> map = new HashMap<>();
                   map.put("stuNum",user.getStuNum());
                   map.put("idNum",user.getIdNum());
                   map.put("answer_id",QuestionDetailAdapter.answersBean.getId());
                   NetUtil.Post(Api.praise, map, new NetUtil.Callback() {
                       @Override
                       public void onSucceed(String response) throws JSONException {
                           Log.d("Fxy", "Praise onSucceed: ");
                           JSONObject jsonObject =new JSONObject(response);
                           if(jsonObject.getInt("status")==200){
                               isPraise=true;
                               num1++;
                               good_num.setText("("+num1+")");
                               good.setBackgroundResource(R.drawable.good_succeed);
                           }
                       }
                       @Override
                       public void onFailed(String response) {

                       }
                   });
               }else{
                   Map<String,String> map = new HashMap<>();
                   map.put("stuNum",user.getStuNum());
                   map.put("idNum",user.getIdNum());
                   map.put("answer_id",QuestionDetailAdapter.answersBean.getId());
                   NetUtil.Post(Api.cancelPraise, map, new NetUtil.Callback() {
                       @Override
                       public void onSucceed(String response) throws JSONException {
                           Log.d("Fxy", "Cancel onSucceed: ");
                           JSONObject jsonObject =new JSONObject(response);
                           if(jsonObject.getInt("status")==200){
                               isPraise=false;
                               num1--;
                               good_num.setText("("+num1+")");
                               good.setBackgroundResource(R.drawable.good);
                           }
                       }
                       @Override
                       public void onFailed(String response) {

                       }
                   });
               }
            }
        });
        comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }
    private void initData(){
        Map<String,String> map = new HashMap<>();
        map.put("stuNum",user.getStuNum());
        map.put("idNum",user.getIdNum());
        map.put("answer_id",QuestionDetailAdapter.AS_ID);
        NetUtil.Post(Api.getRemarkList, map, new NetUtil.Callback() {
            @Override
            public void onSucceed(String response) throws JSONException {
                JsonUtil.AddRemardInfo(response,datalist);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailed(String response) {

            }
        });
    }
    private void GOOD(){
        Map<String,String> map = new HashMap<>();
        map.put("stuNum",user.getStuNum());
        map.put("idNum",user.getIdNum());
        map.put("answer_id",QuestionDetailAdapter.answersBean.getId());
        NetUtil.Post(Api.praise, map, new NetUtil.Callback() {
            @Override
            public void onSucceed(String response) throws JSONException {
                Map<String,String> map = new HashMap<>();
                map.put("stuNum",user.getStuNum());
                map.put("idNum",user.getIdNum());
                map.put("answer_id",QuestionDetailAdapter.answersBean.getId());
                NetUtil.Post(Api.cancelPraise, map, new NetUtil.Callback() {
                    @Override
                    public void onSucceed(String response) throws JSONException {

                    }
                    @Override
                    public void onFailed(String response) {

                    }
                });
            }
            @Override
            public void onFailed(String response) throws JSONException {
                JSONObject jsonObject =new JSONObject(response);
                if(jsonObject.getString("info").equals("you have praise the answer once")){
                    isPraise=true;
                    good.setBackgroundResource(R.drawable.good_succeed);
                }
            }
        });
    }

    private void initPop(){
        View contentView1 = LayoutInflater.from(CommentActivity.this).inflate(R.layout.popuplayout7,null);
        final PopupWindow popupWindow1 = new PopupWindow(contentView1, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT,true);
        popupWindow1.setTouchable(true);
        popupWindow1.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        final InputMethodManager m1=(InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        m1.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
        final EditText comment1 = contentView1.findViewById(R.id.detail_comment_edit);
        Button button1 = contentView1.findViewById(R.id.detail_comment_commit);
        comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow1.showAtLocation(parent, Gravity.BOTTOM,0,0);
            }
        });
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String,String> map = new HashMap<>();
                map.put("stuNum",user.getStuNum());
                map.put("idNum",user.getIdNum());
                map.put("answer_id",QuestionDetailAdapter.answersBean.getId());
                map.put("content",comment1.getText().toString());
                if(comment1.getText().toString().length()>0){
                    NetUtil.Post(Api.remark, map, new NetUtil.Callback() {
                        @Override
                        public void onSucceed(String response) throws JSONException {
                            new ToastUtil(CommentActivity.this
                                    ,R.layout.toast_layout
                                    ,"评论成功")
                                    .setColor(Color.WHITE,Color.BLACK)
                                    .show(3000);
                        }

                        @Override
                        public void onFailed(String response) throws JSONException {
                            new ToastUtil(CommentActivity.this
                                    ,R.layout.toast_layout
                                    ,"评论失败"+response)
                                    .setColor(Color.WHITE,Color.BLACK)
                                    .show(3000);
                        }
                    });
                }
            }
        });
    }
}
