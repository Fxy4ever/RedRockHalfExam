package com.example.mac.oncqupthands.view.activity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.mac.oncqupthands.R;
import com.example.mac.oncqupthands.adapter.QuestionListAdapter;
import com.example.mac.oncqupthands.config.Api;
import com.example.mac.oncqupthands.utils.NetUtil;
import com.example.mac.oncqupthands.utils.ToastUtil;

import org.json.JSONException;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

public class AQuestionActivity extends AppCompatActivity {
    private Button back;
    private Button commit;
    private EditText content;
    private Button addPhoto;
    private TextView contentNum;
    private String mcontent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aquestion);
        initView();
        init();
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
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
    }
    private void initView(){
        back = findViewById(R.id.answerQ_back);
        commit = findViewById(R.id.answerQ_commit);
        content  = findViewById(R.id.answerQ_content);
        addPhoto = findViewById(R.id.answerQ_addPhoto);
        contentNum = findViewById(R.id.answerQ_contentNum);
    }
    private void init(){
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AQuestionActivity.this.finish();
            }
        });
        commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    mcontent = content.getText().toString();
                    mcontent = URLEncoder.encode(mcontent,"UTF-8");
                    Map<String,String> map = new HashMap<>();
                    Log.d("Fxy", "onClick: "+ QuestionListAdapter.question_id);
                    map.put("question_id",QuestionListAdapter.question_id);
                    map.put("content",mcontent);
                    map.put("idNum","091219");
                    map.put("stuNum","2017211851");
                    NetUtil.Post(Api.answerQ, map, new NetUtil.Callback() {
                        @Override
                        public void onSucceed(String response) throws JSONException {
//                            new ToastUtil(AQuestionActivity.this
//                                    ,R.layout.toast_layout
//                                    ,"回答成功")
//                                    .setColor(Color.WHITE,Color.BLACK)
//                                    .setGravity(Gravity.CENTER,0,0)
//                                    .show(3000);
                            new AlertDialog.Builder(AQuestionActivity.this)
                                    .setMessage("提交成功！如果被采纳，会及时通知的")
                                    .setCancelable(true)
                                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.dismiss();
                                            AQuestionActivity.this.finish();
                                        }
                                    })
                                    .create()
                                    .show();
                        }

                        @Override
                        public void onFailed(String response) throws JSONException {
                            Log.d("Fxy", "onFailed: "+response);
                            new ToastUtil(AQuestionActivity.this
                                    ,R.layout.toast_layout
                                    ,"回答失败")
                                    .setColor(Color.WHITE,Color.BLACK)
                                    .setGravity(Gravity.CENTER,0,0)
                                    .show(3000);
                        }
                    });
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
        });
        addPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        content.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                contentNum.setText((300-count)+"");
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
}
