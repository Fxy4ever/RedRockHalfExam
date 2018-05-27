package com.example.mac.oncqupthands.view.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.mac.oncqupthands.R;
import com.example.mac.oncqupthands.config.Api;
import com.example.mac.oncqupthands.mUser;
import com.example.mac.oncqupthands.utils.NetUtil;
import com.example.mac.oncqupthands.utils.ToastUtil;
import com.example.mac.oncqupthands.view.fragment.QuesMainFragment;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class AskChooseActivity extends AppCompatActivity {
    private LinearLayout timeLayout;
    private LinearLayout jifenLayout;
    private TextView time;
    private TextView jifen;
    private LinearLayout parent;
    private mUser user;
    private Button back;
    private Button fabu;

    private String stuNum;
    private String idNum;
    private String reward;
    private String mtime;
    private String kind = QuesMainFragment.choose_kind;
    private String title = AskQuestionActivity.Qtitle;
    private String content = AskQuestionActivity.Qcontent;
    private String biaoqian = AskQuestionActivity.biaoqian;
    private boolean isHide = AskQuestionActivity.isHide;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ask_choose);
        initView();
        initPop();
        initChenjin();
        initWord();
        initSomething();
    }
    private void initView(){
        timeLayout = findViewById(R.id.ask_choose_time);
        jifenLayout = findViewById(R.id.ask_choose_jifen);
        time = findViewById(R.id.ask_choose_timeNum);
        jifen = findViewById(R.id.ask_choose_jifenNum);
        fabu = findViewById(R.id.ask_choose_commit);
        back = findViewById(R.id.ask_choose_back);
        parent = findViewById(R.id.ask_choose_activity);
        user = (mUser)getApplication();
    }
    private void initSomething(){
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AskChooseActivity.this.finish();
            }
        });
        fabu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!reward.isEmpty()&&!mtime.isEmpty()){
                    Log.d("Fxy", "onClick: succeed");
                    String hide="0";
                    if(isHide){
                        hide = "1";
                    }
                    Map<String,String> map = new HashMap<>();
                    map.put("stuNum",stuNum);
                    map.put("idNum",idNum);
                    map.put("title",title);
                    map.put("description",content);
                    map.put("is_anonymous",hide);
                    map.put("kind",kind);
                    map.put("tags",biaoqian);
                    map.put("reward",reward);
                    map.put("disappear_time",mtime);
                    Log.d("Fxt", "onClick: "+map);
                    NetUtil.Post(Api.add, map, new NetUtil.Callback() {
                        @Override
                        public void onSucceed(String response) throws JSONException {
                            Log.d("Fxy", "onSucceed:ask "+response);
                            new ToastUtil(AskChooseActivity.this
                                    ,R.layout.toast_layout
                                    ,"问题发布成功")
                                    .setColor(Color.WHITE,Color.BLACK)
                                    .show(3000);
                            AskChooseActivity.this.finish();
                        }

                        @Override
                        public void onFailed(String response) throws JSONException {
                            JSONObject jsonObject = new JSONObject(response);
                            String info = jsonObject.getString("info");
                            new ToastUtil(AskChooseActivity.this
                                    ,R.layout.toast_layout
                                    ,"问题发布失败"+info)
                                    .setColor(Color.WHITE,Color.BLACK)
                                    .show(3000);
                        }
                    });
                }else{
                    Log.d("Fxy", "onClick: failed");
                }
            }
        });
    }
    private void initPop(){
        final View contentView = LayoutInflater.from(AskChooseActivity.this).inflate(R.layout.popuplayout4,null);
        final PopupWindow popupWindow = new PopupWindow(contentView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT,true);
        popupWindow.setTouchable(true);
        popupWindow.setOutsideTouchable(false);
        popupWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        InputMethodManager m=(InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        m.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);


        View contentView1 = LayoutInflater.from(AskChooseActivity.this).inflate(R.layout.popupwindow5,null);
        final PopupWindow popupWindow1 = new PopupWindow(contentView1, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT,true);
        popupWindow1.setTouchable(true);
        popupWindow1.setOutsideTouchable(false);
        popupWindow1.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        InputMethodManager m1=(InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        m1.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
        timeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.showAtLocation(parent, Gravity.BOTTOM,0,0);
            }
        });
        jifenLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow1.showAtLocation(parent, Gravity.BOTTOM,0,0);
            }
        });
        Button commit1 = contentView.findViewById(R.id.pop_choose_commit);
        commit1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText year = contentView.findViewById(R.id.pop_choose_year);
                EditText month = contentView.findViewById(R.id.pop_choose_month);
                EditText day = contentView.findViewById(R.id.pop_choose_day);
                EditText hour = contentView.findViewById(R.id.pop_choose_hour);
                EditText minute = contentView.findViewById(R.id.pop_choose_minute);
                StringBuilder time2 = new StringBuilder();
                time2.append(year.getText().toString())
                        .append("-")
                        .append(month.getText().toString())
                        .append("-")
                        .append(day.getText().toString())
                        .append(" ")
                        .append(hour.getText().toString())
                        .append(":")
                        .append(minute.getText().toString())
                        .append(":")
                        .append("20");
                Log.d("Fxy", "onClick: "+time2.toString());
                if(time2.toString().length()!=19){
                    new ToastUtil(AskChooseActivity.this
                            ,R.layout.toast_layout
                            ,"信息未填写完整")
                            .setColor(Color.WHITE,Color.BLACK)
                            .show(3000);
                }else{
                    time.setText(time2.toString());
                    mtime = time2.toString();
                    popupWindow.dismiss();
                }
            }
        });
        Button back = contentView.findViewById(R.id.pop_choose_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });

        Button back1 = contentView1.findViewById(R.id.pop_jifen_back);
        back1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow1.dismiss();
            }
        });
        final TextView jifenShow = contentView1.findViewById(R.id.pop_jifen_jifen);
        Button J1 = contentView1.findViewById(R.id.pop_jifen_1);
        Button J2 = contentView1.findViewById(R.id.pop_jifen_2);
        Button J3 = contentView1.findViewById(R.id.pop_jifen_3);
        Button J5 = contentView1.findViewById(R.id.pop_jifen_5);
        Button J10 = contentView1.findViewById(R.id.pop_jifen_10);
        Button J15 = contentView1.findViewById(R.id.pop_jifen_15);
        J1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jifenShow.setText("1积分");
            }
        });
        J2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jifenShow.setText("2积分");
            }
        });
        J3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jifenShow.setText("3积分");
            }
        });
        J5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jifenShow.setText("5积分");
            }
        });
        J15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jifenShow.setText("15积分");
            }
        });
        J10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jifenShow.setText("10积分");
            }
        });
        EditText jifenEdit = contentView1.findViewById(R.id.pop_jifen_custom);
        jifenEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                jifenShow.setText(s+"积分");
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        Button commit2 = contentView1.findViewById(R.id.pop_jifen_commit);
        commit2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reward = jifenShow.getText().toString().substring(0,1);
                jifen.setText(jifenShow.getText());
                popupWindow1.dismiss();
            }
        });

    }



    private void initWord(){
        try {
            stuNum = user.getStuNum();
            idNum = user.getIdNum();
            kind = URLEncoder.encode(kind,"utf-8");
            title = URLEncoder.encode(title,"utf-8");
            content = URLEncoder.encode(content,"utf-8");
            biaoqian = URLEncoder.encode(biaoqian,"utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
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

}
