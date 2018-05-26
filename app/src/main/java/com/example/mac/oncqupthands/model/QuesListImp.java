package com.example.mac.oncqupthands.model;

import android.util.Log;

import com.example.mac.oncqupthands.config.Api;
import com.example.mac.oncqupthands.utils.NetUtil;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by mac on 2018/5/25.
 */

public class QuesListImp implements QuesListInterface {
    QuesListInterface.loadCallBack callBack;

    @Override
    public void loadQuestionList(String kind1,final loadCallBack callBack) {
        Map<String, String> map = new HashMap<>();
        map.put("page", "0");
        try {
            String kind = URLEncoder.encode(kind1, "UTF-8");
            map.put("kind", kind);
            map.put("size", "5");
            NetUtil.Post(Api.getQuestionList, map, new NetUtil.Callback() {
                @Override
                public void onSucceed(String response) {
                    callBack.Succeed(response);
                }

                @Override
                public void onFailed(String response) {

                }
            });
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void refreshList(String kind1,final loadCallBack callBack) {
        Map<String,String> map = new HashMap<>();
        map.put("page","0");
        try {

            String kind = URLEncoder.encode(kind1,"UTF-8");
            map.put("kind",kind);
            map.put("size","5");
            NetUtil.Post(Api.getQuestionList, map, new NetUtil.Callback() {
                @Override
                public void onSucceed(String response) {
                    callBack.Succeed(response);
                }

                @Override
                public void onFailed(String response) {

                }
            });
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
