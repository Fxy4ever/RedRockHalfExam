package com.example.mac.oncqupthands.utils;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import com.example.mac.oncqupthands.bean.QuestionBean;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import com.example.mac.oncqupthands.bean.QuestionDetailBean;
import com.example.mac.oncqupthands.bean.RemarkBean;
import com.example.mac.oncqupthands.bean.UserBean;
import com.example.mac.oncqupthands.mUser;

import static android.content.ContentValues.TAG;

/**
 * Created by mac on 2018/4/3.
 */

public class JsonUtil<T> {
    public static UserBean userBean = new UserBean();
    public static String JsonToString(String response, String json){
        try {
            if(response!=null){
                JSONObject jsonObject = new JSONObject(response);
                String targetInfo = jsonObject.getString(json);
                return targetInfo;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static void AddQuestionList(String response, List<QuestionBean> beanList){
        try {
            String data = JsonUtil.JsonToString(response,"data");
            Log.d(TAG, "AddQuestionList: "+data);
            JSONArray jsonArray = new JSONArray(data);
            for(int i = 0 ; i < jsonArray.length();i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                QuestionBean questionBean = new QuestionBean.Builder()
                        .title(jsonObject.getString("title"))
                        .description(jsonObject.getString("description"))
                        .kind(jsonObject.getString("kind"))
                        .tags(jsonObject.getString("tags"))
                        .reward(jsonObject.getInt("reward"))
                        .answer_num(jsonObject.getInt("answer_num"))
                        .disappear_at(jsonObject.getString("disappear_at"))
                        .created_at(jsonObject.getString("created_at"))
                        .id(jsonObject.getInt("id"))
                        .nickname(jsonObject.getString("nickname"))
                        .photo_thumbnail_src(jsonObject.getString("photo_thumbnail_src"))
                        .gender(jsonObject.getString("gender"))
                        .is_anonymous(jsonObject.getInt("is_anonymous"))
                        .build();
                beanList.add(questionBean);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public static void AddPersonInfo(String response, Context context) throws JSONException {
        String data = JsonToString(response,"data");
        JSONObject jsonObject =new JSONObject(data);
        mUser user = (mUser)((Activity)context).getApplication();
        user.setStuNum(jsonObject.getString("stuNum"));
        user.setName(jsonObject.getString("name"));
        user.setCollege(jsonObject.getString("college"));
        user.setmClass(jsonObject.getString("class"));
        user.setGender(jsonObject.getString("gender"));
        user.setClassNum(jsonObject.getString("classNum"));
        user.setMajor(jsonObject.getString("major"));
        user.setGrade(jsonObject.getString("grade"));
        user.setIdNum(jsonObject.getString("idNum"));
    }

    public static void AddQuesDetailData(String response, List<QuestionDetailBean> list){
        try {
            String data = JsonToString(response,"data");
            JSONObject jsonObject = new JSONObject(data);
            String title = jsonObject.getString("title");
            String description = jsonObject.getString("description");
            String reward = jsonObject.getString("reward");
            String disappearTime= jsonObject.getString("disappear_at");
            String kind = jsonObject.getString("kind");
            String is_self = jsonObject.getString("is_self");
            String nickname = jsonObject.getString("questioner_nickname");
            String questioner_photo_thumbnail_src = jsonObject.getString("questioner_photo_thumbnail_src");
            String questioner_gender = jsonObject.getString("questioner_gender");
            QuestionDetailBean bean = new QuestionDetailBean();
            bean.setTitle(title);
            bean.setDescription(description);
            bean.setReward(reward);
            bean.setDisappear_at(disappearTime);
            bean.setKind(kind);
            bean.setIs_self(Integer.valueOf(is_self));
            bean.setQuestioner_nickname(nickname);
            bean.setQuestioner_photo_thumbnail_src(questioner_photo_thumbnail_src);
            bean.setQuestioner_gender(questioner_gender);
            Log.d("Fxy", "AddQuesDetailData: "+jsonObject.isNull("answers"));
            if(!jsonObject.isNull("answers")){
                String answer = JsonToString(data,"answers");
                JSONArray jsonArray = new JSONArray(answer);
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                    QuestionDetailBean.AnswersBean bean1 = new QuestionDetailBean.AnswersBean();
                    bean1.setId(jsonObject1.getString("id"));
                    bean1.setNickname(jsonObject1.getString("nickname"));
                    bean1.setPhoto_thumbnail_src(jsonObject1.getString("photo_thumbnail_src"));
                    bean1.setGender(jsonObject1.getString("gender"));
                    bean1.setContent(jsonObject1.getString("content"));
                    bean1.setCreated_at(jsonObject1.getString("created_at"));
                    bean1.setPraise_num(jsonObject1.getString("praise_num"));
                    bean1.setComment_num(jsonObject1.getString("comment_num"));
                    String photo_url = jsonObject1.getString("photo_url");
                    List<String> list1 = new ArrayList<>();
                    String[] url = photo_url.split("");
                    for (int j = 0; j < url.length; j++) {
                        list1.add(url[j]);
                    }
                    bean1.setPhoto_url(list1);
                    bean1.setIs_adopted(jsonObject1.getString("is_adopted"));
                    bean1.setIs_praised(jsonObject1.getInt("is_praised"));
                    bean.getAnswers().add(bean1);
                }
            }
            list.add(bean);
            for (int i = 0; i < list.get(0).getAnswers().size(); i++) {
                list.add(bean);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    public static void AddUserInfo(String response) throws JSONException {
        String data =JsonToString(response,"data");
        Log.d("Fxy", "AddUserInfo: "+data);
        JSONObject jsonObject = new JSONObject(data);

        userBean.setId(jsonObject.getInt("id"));
        userBean.setStunum(jsonObject.getString("stunum"));
        userBean.setIntroduction(jsonObject.getString("introduction"));
        userBean.setUsername(jsonObject.getString("username"));
        userBean.setNickname(jsonObject.getString("nickname"));
        userBean.setGender(jsonObject.getString("gender"));
        userBean.setPhoto_thumbnail_src(jsonObject.getString("photo_thumbnail_src"));
        userBean.setPhoto_src(jsonObject.getString("photo_src"));
        userBean.setUpdated_time(jsonObject.getString("updated_time"));
        userBean.setPhone(jsonObject.getString("phone"));
        userBean.setPhone(jsonObject.getString("qq"));
    }

    public static void AddRemardInfo(String response, List<RemarkBean> datalist){
        Log.d("Fxy", "AddRemardInfo: "+response);
        try {
            JSONObject jsonObject = new JSONObject(response);
            if(!jsonObject.isNull("data")){
                String data = JsonToString(response,"data");
                JSONArray jsonArray = new JSONArray(data);
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                    RemarkBean bean = new RemarkBean();
                    bean.setContent(jsonObject1.getString("content"));
                    bean.setCreated_at(jsonObject1.getString("created_at"));
                    bean.setNickname(jsonObject1.getString("nickname"));
                    bean.setGender(jsonObject1.getString("gender"));
                    bean.setPhoto_thumbnail_src(jsonObject1.getString("photo_thumbnail_src"));
                    datalist.add(bean);
                }
                RemarkBean bean = new RemarkBean();
                datalist.add(bean);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
