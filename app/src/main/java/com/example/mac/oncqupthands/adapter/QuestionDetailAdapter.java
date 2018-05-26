package com.example.mac.oncqupthands.adapter;

import android.content.Context;
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
import com.example.mac.oncqupthands.utils.ShapeImageView;
import com.example.mac.oncqupthands.utils.TimeUtil;
import com.example.mac.oncqupthands.utils.UrlUtil;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

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
    public void onBinds(BaseHolder baseHolder, Object o, int i, int i1) {
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
                ImageView gender = baseHolder.getView(R.id.detail_gender);
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
                TextView good_num = baseHolder.getView(R.id.detail_normal_good_num);
                ImageButton commit = baseHolder.getView(R.id.detail_normal_commit);
                ImageButton good = baseHolder.getView(R.id.detail_normal_good);
                Log.d("Fxy", "onBinds: "+datalist.get(0).getAnswers().size());
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
                good.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

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
