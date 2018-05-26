package com.example.mac.oncqupthands.view.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.mac.oncqupthands.R;
import com.example.mac.oncqupthands.config.Api;
import com.example.mac.oncqupthands.mUser;
import com.example.mac.oncqupthands.utils.JsonUtil;
import com.example.mac.oncqupthands.utils.NetUtil;
import com.example.mac.oncqupthands.utils.ToastUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {
    private EditText account;
    private EditText password;
    private Button login;
    private Button back;
    private mUser user;
    public static boolean isLogin=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
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
    }
    private void init(){
        account = findViewById(R.id.edit_acc);
        password = findViewById(R.id.edit_pass);
        login = findViewById(R.id.Btn_login);
        back = findViewById(R.id.user_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginActivity.this.finish();
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String acc = account.getText().toString();
                final String pass = password.getText().toString();
                Map<String,String> maps = new HashMap<>();
                maps.put("idNum",pass);
                maps.put("stuNum",acc);
                NetUtil.Post(Api.verify, maps, new NetUtil.Callback() {
                    @Override
                    public void onSucceed(String response) {
                        try {
                            JsonUtil.AddPersonInfo(response,LoginActivity.this);
                            new ToastUtil(LoginActivity.this
                                    ,R.layout.toast_layout
                                    ,"登陆成功")
                                    .setColor(Color.WHITE,Color.BLACK)
                                    .setGravity(Gravity.CENTER,0,0)
                                    .show(3000);
                            isLogin=true;
                            LoginActivity.this.finish();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }

                    @Override
                    public void onFailed(String response) {
                        Log.d("Fxy", "onFailed: "+response);
                        new ToastUtil(LoginActivity.this
                                ,R.layout.toast_layout
                                ,"登陆失败")
                                .setColor(Color.WHITE,Color.BLACK)
                                .setGravity(Gravity.CENTER,0,0)
                                .show(3000);
                    }
                });
            }
        });
    }

}
