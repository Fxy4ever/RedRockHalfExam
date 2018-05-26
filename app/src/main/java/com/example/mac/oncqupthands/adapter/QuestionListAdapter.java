package com.example.mac.oncqupthands.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.frecyclerview.BaseHolder;
import com.example.frecyclerview.MultiLayoutBaseAdapter;
import com.example.mac.oncqupthands.R;
import com.example.mac.oncqupthands.bean.QuestionBean;
import com.example.mac.oncqupthands.config.Api;
import com.example.mac.oncqupthands.utils.JsonUtil;
import com.example.mac.oncqupthands.utils.NetUtil;
import com.example.mac.oncqupthands.utils.ShapeImageView;
import com.example.mac.oncqupthands.utils.TimeUtil;
import com.example.mac.oncqupthands.utils.UrlUtil;
import com.example.mac.oncqupthands.view.activity.QuestionDetailActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.sql.Time;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import config.RequestOptions;
import start.ImageLoader;

/**
 * Created by mac on 2018/5/25.
 */

public class QuestionListAdapter<T> extends MultiLayoutBaseAdapter {
    private List<QuestionBean> datalist;
    public static  String question_id;
    public static String create_time;
    public QuestionListAdapter(Context context, List<QuestionBean> data, int[] layoutIds) {
        super(context, data, layoutIds);
        this.datalist =data;
    }
    int NORMAL = 0;
    int FOOTER = 1;
    @Override
    public int getItemType(int i) {
        if(i==datalist.size()-1){
            return FOOTER;
        }else{
            return NORMAL;
        }
    }

    private final RequestOptions options = new RequestOptions()
            .setPreloadPic(R.mipmap.ic_launcher_round)
            .setErrorPic(R.mipmap.ic_launcher);
    @Override
    public void onBinds(final BaseHolder baseHolder, Object o, final int i, int i1) {
        switch (i1){
            case 0:
                ShapeImageView avatar = baseHolder.getView(R.id.question_avatar);
                TextView username = baseHolder.getView(R.id.question_username);
                TextView time = baseHolder.getView(R.id.question_time);
                TextView fenlei = baseHolder.getView(R.id.question_fenlei);
                TextView title = baseHolder.getView(R.id.question_title);
                final TextView content = baseHolder.getView(R.id.question_content);
                TextView jifen = baseHolder.getView(R.id.question_jifen);
                ImageView gender = baseHolder.getView(R.id.question_gender);
                ImageLoader
                        .with(getContext())
                        .load(UrlUtil.addS(datalist.get(i).getPhoto_thumbnail_src()))
                        .into(avatar)
                        .apply(options)
                        .display();
                username.setText(datalist.get(i).getNickname());
                time.setText(TimeUtil.FormatTime(datalist.get(i).getDisappear_at(),datalist.get(i).getCreated_at()));
                fenlei.setText("#"+datalist.get(i).getKind()+"#");
                title.setText(datalist.get(i).getTitle());
                content.setText(datalist.get(i).getDescription());
                jifen.setText(datalist.get(i).getReward()+"积分");
                try {
                    String gender1 = URLDecoder.decode(datalist.get(i).getGender(),"UTF-8");
                    if(gender1.equals("女")){
                        gender.setBackgroundResource(R.drawable.girl);
                    }
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                if(MyonItemClickListener != null){
                    baseHolder.itemView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            int position = baseHolder.getLayoutPosition();
                            MyonItemClickListener.OnClickItem(view,position);
                        }
                    });
                }

                baseHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        question_id = String.valueOf(datalist.get(i).getId());
                        create_time = datalist.get(i).getCreated_at();
                        Intent intent = new Intent(getContext(), QuestionDetailActivity.class);
                        getContext().startActivity(intent);
                    }
                });
                break;
            case 1:
                loadMore();
                break;
        }
    }
    int page=1;
    private void loadMore(){
        Map<String,String> map = new HashMap<>();
        map.put("page",String.valueOf(page));
        try {
            String kind = URLEncoder.encode("其他","UTF-8");
            map.put("kind",kind);
            map.put("size","5");
            NetUtil.Post(Api.getQuestionList, map, new NetUtil.Callback() {
                @Override
                public void onSucceed(String response) {
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        if(!jsonObject.isNull("data")){
                            JsonUtil.AddQuestionList(response,datalist);
                            notifyDataSetChanged();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }

                @Override
                public void onFailed(String response) {

                }
            });
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        page++;
    }

    public interface OnItemClickListener {
        void OnClickItem(View view, int position);
    }
    private OnItemClickListener MyonItemClickListener;
    public void onItemClickListner(OnItemClickListener onItemClickListener){
        MyonItemClickListener = onItemClickListener;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }
}
