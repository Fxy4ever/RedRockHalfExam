package com.example.mac.oncqupthands.view.activity;

import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.example.mac.oncqupthands.R;

public class AskQuestionActivity extends AppCompatActivity {
    private EditText title;
    private EditText content;
    private TextView kind;
    private Button Tokind;
    private Button photo;
    private CheckBox check;
    private TextView title_num;
    private TextView content_num;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ask_question);
        init();
        initChenjin();
        CarculateNum();
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
    private void init(){
        title = findViewById(R.id.ask_title);
        content = findViewById(R.id.ask_content);
        kind = findViewById(R.id.ask_fenlei);
        Tokind = findViewById(R.id.ask_kind);
        photo = findViewById(R.id.ask_photo);
        check = findViewById(R.id.ask_check);
        title_num = findViewById(R.id.ask_title_name);
        content_num = findViewById(R.id.ask_content_num);
    }
    private void CarculateNum(){
        title.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                title_num.setText(String.valueOf(10-count));
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        content.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                content_num.setText(String.valueOf(100-count));
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
}
