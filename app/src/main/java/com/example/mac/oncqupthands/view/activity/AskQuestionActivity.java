package com.example.mac.oncqupthands.view.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mac.oncqupthands.R;
import com.example.mac.oncqupthands.utils.ToastUtil;

public class AskQuestionActivity extends AppCompatActivity {
    private EditText title;
    private EditText content;
    private TextView kind;
    private Button Tokind;
    private Button photo;
    private Button next;
    private CheckBox check;
    private TextView title_num;
    private TextView content_num;
    private FrameLayout parent;
    private EditText kind_edit;
    private TextView fenlei;
    private CheckBox isHideName;
    private View mengban;

    public static String Qtitle="";
    public static String Qcontent="";
    public static String biaoqian="";
    public static boolean isHide;


    private String myKind;
    private boolean myHide;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ask_question);
        init();
        initChenjin();
        CarculateNum();
        initKind();
        initCheckBox();
        initChoosePic();
        initNext();
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
        parent = findViewById(R.id.ask_question_activity);
        kind = findViewById(R.id.ask_fenlei);
        fenlei = findViewById(R.id.ask_fenlei);
        next = findViewById(R.id.ask_next);
        Tokind = findViewById(R.id.ask_kind);
        mengban = findViewById(R.id.ask_mengban);
        isHideName = findViewById(R.id.ask_check);
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

    private void initKind(){
        View contentView = LayoutInflater.from(AskQuestionActivity.this).inflate(R.layout.popuplayout1,null);
        final PopupWindow popupWindow = new PopupWindow(contentView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT,true);
        popupWindow.setTouchable(true);
        popupWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        InputMethodManager m=(InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        m.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
        kind = contentView.findViewById(R.id.pop_add_edit);
        Button commit = contentView.findViewById(R.id.pop_add_commit);
        Button dawu = contentView.findViewById(R.id.pop_add_dawu);
        Button english = contentView.findViewById(R.id.pop_add_english);
        Button gaoshu = contentView.findViewById(R.id.pop_add_gaoshu);
        Button jihe  = contentView.findViewById(R.id.pop_add_jihe);
        Button xiandai  = contentView.findViewById(R.id.pop_add_xiandai);
        Button sixiu = contentView.findViewById(R.id.pop_add_sixiu);
        commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myKind = kind.getText().toString();
                fenlei.setText(myKind);
                mengban.setVisibility(View.GONE);
                popupWindow.dismiss();
            }
        });
        dawu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                kind.setText("#大物#");
            }
        });
        english.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                kind.setText("#英语#");
            }
        });
        gaoshu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                kind.setText("#高数#");
            }
        });

        jihe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                kind.setText("#几何#");
            }
        });
        sixiu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                kind.setText("#思修#");
            }
        });
        xiandai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                kind.setText("#线代#");
            }
        });

        Tokind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mengban.setVisibility(View.VISIBLE);
                popupWindow.showAtLocation(parent,Gravity.BOTTOM,0,0);
            }
        });
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                mengban.setVisibility(View.GONE);
            }
        });
    }
    private void initCheckBox(){
        isHideName.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                myHide = isChecked;
            }
        });
    }

    private void initChoosePic(){
        View contentView1 = LayoutInflater.from(AskQuestionActivity.this).inflate(R.layout.popuplayout2,null);
        final PopupWindow popupWindow1 = new PopupWindow(contentView1, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT,true);
        popupWindow1.setTouchable(true);
        Button from_xiangce = contentView1.findViewById(R.id.pop_choose_xiangce);
        Button from_take = contentView1.findViewById(R.id.pop_choose_take);
        from_xiangce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AskQuestionActivity.this,"从相册选取",Toast.LENGTH_SHORT).show();
            }
        });
        from_take.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AskQuestionActivity.this,"拍照",Toast.LENGTH_SHORT).show();
            }
        });
        photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mengban.setVisibility(View.VISIBLE);
                popupWindow1.showAtLocation(parent,Gravity.BOTTOM,0,0);
            }
        });
        popupWindow1.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                mengban.setVisibility(View.GONE);
            }
        });
    }

    private void initNext(){
       next.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Qtitle = title.getText().toString();
               Qcontent = content.getText().toString();
               biaoqian = myKind;
               isHide = myHide;
               if(Qtitle.equals("")||Qcontent.equals("")||biaoqian.equals("")){
                   new ToastUtil(AskQuestionActivity.this
                           ,R.layout.toast_layout
                           ,"请填写完整")
                           .setColor(Color.WHITE,Color.BLACK)
                           .show(3000);
               }else{
                   Intent intent = new Intent(AskQuestionActivity.this,AskChooseActivity.class);
                   AskQuestionActivity.this.finish();
                   startActivity(intent);
               }
           }
       });
    }
}
