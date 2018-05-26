package com.example.mac.oncqupthands.adapter;

import android.content.Context;
import android.graphics.drawable.shapes.Shape;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.frecyclerview.BaseHolder;
import com.example.frecyclerview.MultiLayoutBaseAdapter;
import com.example.mac.oncqupthands.R;
import com.example.mac.oncqupthands.bean.RemarkBean;
import com.example.mac.oncqupthands.utils.ShapeImageView;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

import config.RequestOptions;
import start.ImageLoader;

/**
 * Created by mac on 2018/5/26.
 */

public class CommentsAdapter<T> extends MultiLayoutBaseAdapter {
    private List<RemarkBean> datalist;
    public CommentsAdapter(Context context, List<RemarkBean> data, int[] layoutIds) {
        super(context, data, layoutIds);
        this.datalist = data;
    }
    int NORMAL = 1;
    int HEADER = 0;
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
                TextView kind = baseHolder.getView(R.id.comment_kind);
                TextView content0 = baseHolder.getView(R.id.comment_content);
                ShapeImageView avatar = baseHolder.getView(R.id.comment_avatar);
                TextView time = baseHolder.getView(R.id.comment_time);
                TextView content1 = baseHolder.getView(R.id.comment_content1);
                ImageView gender = baseHolder.getView(R.id.comment_gender);
                try {
                    String gender1 = URLDecoder.decode(QuestionDetailAdapter.answersBean.getGender(),"UTF-8");
                    if(gender1.equals("女")){
                        gender.setBackgroundResource(R.drawable.girl);
                    }
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                kind.setText("#"+QuestionDetailAdapter.KIND+"#");
                content0.setText("              "+QuestionDetailAdapter.CONTENT);
                ImageLoader.with(getContext())
                            .load(QuestionDetailAdapter.answersBean.getPhoto_thumbnail_src())
                            .into(avatar)
                            .apply(options)
                            .display();
                time.setText(QuestionDetailAdapter.answersBean.getCreated_at().substring(0,10));
                content1.setText(QuestionDetailAdapter.answersBean.getContent());
                break;
            case 1:
                TextView name = baseHolder.getView(R.id.comment_answer_name);
                TextView content = baseHolder.getView(R.id.comment_answer_content);
                TextView time1 = baseHolder.getView(R.id.comment_answer_time);
                ShapeImageView avatar1 = baseHolder.getView(R.id.comment_answer_avatar);
                ImageView gender1 = baseHolder.getView(R.id.comment_answer_gender);
                try {
                    String gender2 = URLDecoder.decode(QuestionDetailAdapter.answersBean.getGender(),"UTF-8");
                    if(gender2.equals("女")){
                        gender1.setBackgroundResource(R.drawable.girl);
                    }
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                name.setText(datalist.get(i).getNickname());
                content.setText(datalist.get(i).getContent());
                time1.setText(datalist.get(i).getCreated_at().substring(0,10));
                ImageLoader.with(getContext())
                        .load(datalist.get(i).getPhoto_thumbnail_src())
                        .into(avatar1)
                        .apply(options)
                        .display();
                break;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

}
