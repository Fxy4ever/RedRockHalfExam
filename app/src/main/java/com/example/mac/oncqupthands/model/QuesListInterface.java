package com.example.mac.oncqupthands.model;

/**
 * Created by mac on 2018/5/25.
 */

public interface QuesListInterface {
    void loadQuestionList(String kind1,loadCallBack callBack);
    void refreshList(String kind1,loadCallBack callBack);
    interface loadCallBack{
        void Succeed(String response);
        void Failed(String response);
    }
}
