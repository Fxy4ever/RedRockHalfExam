package com.example.mac.oncqupthands.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.Image;
import android.support.annotation.NonNull;
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
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.frecyclerview.BaseHolder;
import com.example.frecyclerview.MultiLayoutBaseAdapter;
import com.example.mac.oncqupthands.R;
import com.example.mac.oncqupthands.bean.QuestionDetailBean;
import com.example.mac.oncqupthands.config.Api;
import com.example.mac.oncqupthands.mUser;
import com.example.mac.oncqupthands.utils.NetUtil;
import com.example.mac.oncqupthands.utils.ShapeImageView;
import com.example.mac.oncqupthands.utils.TimeUtil;
import com.example.mac.oncqupthands.utils.ToastUtil;
import com.example.mac.oncqupthands.utils.UrlUtil;
import com.example.mac.oncqupthands.view.activity.AQuestionActivity;
import com.example.mac.oncqupthands.view.activity.CommentActivity;
import com.example.mac.oncqupthands.view.activity.LoginActivity;
import com.example.mac.oncqupthands.view.activity.QuestionDetailActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import config.RequestOptions;
import start.ImageLoader;

import static android.content.ContentValues.TAG;

/**
 * Created by mac on 2018/5/26.
 */

public class QuestionDetailAdapter<T> extends MultiLayoutBaseAdapter {
    List<QuestionDetailBean> datalist;
    String create_time = QuestionListAdapter.create_time;
    boolean isLoad=false;
    public static String KIND;
    public static String CONTENT;
    public static String AS_ID;
    public static QuestionDetailBean.AnswersBean answersBean;
    public static String quesion_ID;
    private boolean isPraise = false;
    private mUser user;
    private int num1;
    public QuestionDetailAdapter(Context context,  List<QuestionDetailBean> data, int[] layoutIds) {
        super(context, data, layoutIds);
        this.datalist = data;
    }
    int HEADER=0;
    int NORMAL=1;
    @Override
    public int getItemType(int i) {
        if(i==0){
            return HEADER;
        }else{
            return NORMAL;
        }
    }

    @Override
    public int getItemCount() {
        return datalist.size();
    }

    private final RequestOptions options = new RequestOptions()
            .setPreloadPic(R.mipmap.ic_launcher_round)
            .setErrorPic(R.mipmap.ic_launcher);
    @Override
    public void onBinds(BaseHolder baseHolder, Object o, final int i, int i1) {
        switch (i1){
            case 0:
                TextView title = baseHolder.getView(R.id.detail_title);
                TextView kind = baseHolder.getView(R.id.detail_kind);
                TextView content = baseHolder.getView(R.id.detail_content);
                ShapeImageView avatar = baseHolder.getView(R.id.detail_avatar);
                TextView username = baseHolder.getView(R.id.detail_username);
                TextView disspeartime = baseHolder.getView(R.id.detail_time);
                TextView jifen = baseHolder.getView(R.id.detail_jifen);
                TextView size = baseHolder.getView(R.id.detail_size);
                title.setText(datalist.get(0).getTitle());
                kind.setText("#"+datalist.get(0).getKind()+"#");
                content.setText("             "+datalist.get(0).getDescription()+"");
                ImageLoader.with(getContext())
                        .load(UrlUtil.addS(datalist.get(0).getQuestioner_photo_thumbnail_src()))
                        .into(avatar)
                        .apply(options)
                        .display();
                final ImageView gender = baseHolder.getView(R.id.detail_gender);
                try {
                    String gender1 = URLDecoder.decode(datalist.get(0).getQuestioner_gender(),"UTF-8");
                    if(gender1.equals("女")){
                        gender.setBackgroundResource(R.drawable.girl);
                    }
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                username.setText(datalist.get(0).getQuestioner_nickname());
                disspeartime.setText(TimeUtil.FormatTime(datalist.get(0).getDisappear_at(),create_time)+"消失");
                jifen.setText(datalist.get(0).getReward()+"积分");
                if(datalist.get(0).getAnswers()!=null){
                    size.setText(datalist.get(0).getAnswers().size()+"个回答");
                }else{
                    size.setText("0个回答");
                }
                break;
            case 1:
                final LinearLayout parent = baseHolder.getView(R.id.item_parent);
                ImageView avatar1 = baseHolder.getView(R.id.detail_normal_avatar);
                TextView name = baseHolder.getView(R.id.detail_normal_name);
                ImageView gender2 = baseHolder.getView(R.id.detail_normal_gender);
                TextView content1 = baseHolder.getView(R.id.detail_normal_content);
                TextView create_time = baseHolder.getView(R.id.detail_normal_createTime);
                TextView commit_num = baseHolder.getView(R.id.detail_normal_commit_num);
                final TextView good_num = baseHolder.getView(R.id.detail_normal_good_num);
                ImageButton commit = baseHolder.getView(R.id.detail_normal_commit);
                final ImageButton good = baseHolder.getView(R.id.detail_normal_good);
                final Button button = baseHolder.getView(R.id.detail_normal_accept);

                if(datalist.get(0).getAnswers().get(i-1).getIs_adopted().equals("1")){
                    if(datalist.get(0).getIs_self()==1){
                        button.setText("已采纳");
                        button.setVisibility(View.VISIBLE);
                    }
                }else{
                    if(datalist.get(0).getIs_self()==1){
                        button.setVisibility(View.VISIBLE);
                        button.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Map<String,String> map = new HashMap<>();
                                map.put("stuNum",user.getStuNum());
                                map.put("idNum",user.getIdNum());
                                map.put("answer_id",datalist.get(0).getAnswers().get(i-1).getId());
                                map.put("question_id",QuestionListAdapter.question_id);
                                NetUtil.Post(Api.adopt, map, new NetUtil.Callback() {
                                    @Override
                                    public void onSucceed(String response) throws JSONException {
                                        new ToastUtil(getContext()
                                                ,R.layout.toast_layout
                                                ,"采纳成功")
                                                .setColor(Color.WHITE,Color.BLACK)
                                                .show(3000);
                                        button.setText("已采纳");
                                    }

                                    @Override
                                    public void onFailed(String response) throws JSONException {
                                        Log.d(TAG, "onFailed: "+response);
                                    }
                                });
                            }
                        });
                    }
                }
                Log.d("Fxy", "onBinds: "+datalist.get(0).getAnswers().size());
                num1 = Integer.valueOf(datalist.get(0).getAnswers().get(i-1).getPraise_num());
                if(datalist.get(0).getAnswers().size()!=0){
                    ImageLoader.with(getContext())
                            .load(UrlUtil.addS(datalist.get(0).getAnswers().get(i-1).getPhoto_thumbnail_src()))
                            .into(avatar1).display();
                    name.setText(datalist.get(0).getAnswers().get(i-1).getNickname());
                    try {
                        String gender1 = URLDecoder.decode(datalist.get(0).getAnswers().get(i-1).getGender(),"UTF-8");
                        if(gender1.equals("女")){
                            gender2.setBackgroundResource(R.drawable.girl);
                        }
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                    content1.setText(datalist.get(0).getAnswers().get(i-1).getContent());
                    create_time.setText(datalist.get(0).getAnswers().get(i-1).getCreated_at());
                    commit_num.setText(datalist.get(0).getAnswers().get(i-1).getComment_num());
                    good_num.setText(datalist.get(0).getAnswers().get(i-1).getPraise_num());
                }

                View contentView1 = LayoutInflater.from(getContext()).inflate(R.layout.popuplayout7,null);
                final PopupWindow popupWindow1 = new PopupWindow(contentView1, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT,true);
                popupWindow1.setTouchable(true);
                popupWindow1.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
                final InputMethodManager m1=(InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                m1.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
                final EditText comment1 = contentView1.findViewById(R.id.detail_comment_edit);
                Button button1 = contentView1.findViewById(R.id.detail_comment_commit);
                commit.setOnClickListener(new View.OnClickListener() {
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
                        map.put("answer_id",datalist.get(0).getAnswers().get(i-1).getId());
                        try {
                            map.put("content", URLEncoder.encode(comment1.getText().toString(),"utf-8"));
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                        if(comment1.getText().toString().length()>0){
                            NetUtil.Post(Api.remark, map, new NetUtil.Callback() {
                                @Override
                                public void onSucceed(String response) throws JSONException {
                                    new ToastUtil(getContext()
                                            ,R.layout.toast_layout
                                            ,"评论成功")
                                            .setColor(Color.WHITE,Color.BLACK)
                                            .show(3000);
                                    popupWindow1.dismiss();
                                }

                                @Override
                                public void onFailed(String response) throws JSONException {
                                    new ToastUtil(getContext()
                                            ,R.layout.toast_layout
                                            ,"评论失败"+response)
                                            .setColor(Color.WHITE,Color.BLACK)
                                            .show(3000);
                                }
                            });
                        }
                    }
                });
                user = (mUser) getContext().getApplicationContext();
                final String stuNum = user.getStuNum();
                final String idNum = user.getIdNum();
                Map<String,String> map = new HashMap<>();
                map.put("stuNum",stuNum);
                map.put("idNum",idNum);
                map.put("answer_id",datalist.get(0).getAnswers().get(i-1).getId());
                NetUtil.Post(Api.praise, map, new NetUtil.Callback() {
                    @Override
                    public void onSucceed(String response) throws JSONException {
                        Map<String,String> map = new HashMap<>();
                        map.put("stuNum",stuNum);
                        map.put("idNum",idNum);
                        map.put("answer_id",datalist.get(0).getAnswers().get(i-1).getId());
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
                good.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (!isPraise) {
                            Map<String, String> map = new HashMap<>();
                            map.put("stuNum", stuNum);
                            map.put("idNum", idNum);
                            map.put("answer_id",datalist.get(0).getAnswers().get(i-1).getId());
                            NetUtil.Post(Api.praise, map, new NetUtil.Callback() {
                                @Override
                                public void onSucceed(String response) throws JSONException {
                                    Log.d("Fxy", "Praise onSucceed: ");
                                    JSONObject jsonObject = new JSONObject(response);
                                    if (jsonObject.getInt("status") == 200) {
                                        isPraise = true;
                                        num1++;
                                        good_num.setText(num1+"");
                                        good.setBackgroundResource(R.drawable.good_succeed);
                                    }
                                }

                                @Override
                                public void onFailed(String response) {

                                }
                            });
                        } else {
                            Map<String, String> map = new HashMap<>();
                            map.put("stuNum", stuNum);
                            map.put("idNum", idNum);
                            map.put("answer_id", datalist.get(0).getAnswers().get(i-1).getId());
                            NetUtil.Post(Api.cancelPraise, map, new NetUtil.Callback() {
                                @Override
                                public void onSucceed(String response) throws JSONException {
                                    Log.d("Fxy", "Cancel onSucceed: ");
                                    JSONObject jsonObject = new JSONObject(response);
                                    if (jsonObject.getInt("status") == 200) {
                                        isPraise = false;
                                        num1--;
                                        good_num.setText(num1+"");
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

                baseHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        KIND = datalist.get(0).getKind();
                        CONTENT = datalist.get(0).getDescription();
                        AS_ID = datalist.get(0).getAnswers().get(i-1).getId();
                        answersBean = datalist.get(0).getAnswers().get(i-1);
                        Log.d(TAG, "onClick: "+answersBean.getNickname());
                        Intent intent = new Intent(getContext(), CommentActivity.class);
                        getContext().startActivity(intent);
                    }
                });
                break;
        }
    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    public void refresh(List<QuestionDetailBean> datalist1){
        datalist = datalist1;
    }
}
