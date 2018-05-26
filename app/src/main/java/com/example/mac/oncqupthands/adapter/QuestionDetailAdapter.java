package com.example.mac.oncqupthands.adapter;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.frecyclerview.BaseHolder;
import com.example.frecyclerview.MultiLayoutBaseAdapter;
import com.example.mac.oncqupthands.R;
import com.example.mac.oncqupthands.bean.QuestionDetailBean;
import com.example.mac.oncqupthands.config.Api;
import com.example.mac.oncqupthands.utils.NetUtil;
import com.example.mac.oncqupthands.utils.ShapeImageView;
import com.example.mac.oncqupthands.utils.TimeUtil;
import com.example.mac.oncqupthands.utils.UrlUtil;
import com.example.mac.oncqupthands.view.activity.AQuestionActivity;
import com.example.mac.oncqupthands.view.activity.CommentActivity;
import com.example.mac.oncqupthands.view.activity.QuestionDetailActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
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
                ImageView avatar1 = baseHolder.getView(R.id.detail_normal_avatar);
                TextView name = baseHolder.getView(R.id.detail_normal_name);
                ImageView gender2 = baseHolder.getView(R.id.detail_normal_gender);
                TextView content1 = baseHolder.getView(R.id.detail_normal_content);
                TextView create_time = baseHolder.getView(R.id.detail_normal_createTime);
                TextView commit_num = baseHolder.getView(R.id.detail_normal_commit_num);
                final TextView good_num = baseHolder.getView(R.id.detail_normal_good_num);
                ImageButton commit = baseHolder.getView(R.id.detail_normal_commit);
                final ImageButton good = baseHolder.getView(R.id.detail_normal_good);
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
                commit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
                Map<String,String> map = new HashMap<>();
                map.put("stuNum","2017211851");
                map.put("idNum","091219");
                map.put("answer_id",datalist.get(0).getAnswers().get(i-1).getId());
                NetUtil.Post(Api.praise, map, new NetUtil.Callback() {
                    @Override
                    public void onSucceed(String response) throws JSONException {
                        Map<String,String> map = new HashMap<>();
                        map.put("stuNum","2017211851");
                        map.put("idNum","091219");
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
                            map.put("stuNum", "2017211851");
                            map.put("idNum", "091219");
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
                            map.put("stuNum", "2017211851");
                            map.put("idNum", "091219");
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
                        Intent intent = new Intent(getContext(), CommentActivity.class);
                        getContext().startActivity(intent);
                    }
                });
                break;
        }
    }
    private void loadMore(){

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

}
