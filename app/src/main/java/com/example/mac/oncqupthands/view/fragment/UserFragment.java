package com.example.mac.oncqupthands.view.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mac.oncqupthands.R;
import com.example.mac.oncqupthands.bean.UserBean;
import com.example.mac.oncqupthands.config.Api;
import com.example.mac.oncqupthands.mUser;
import com.example.mac.oncqupthands.utils.JsonUtil;
import com.example.mac.oncqupthands.utils.NetUtil;
import com.example.mac.oncqupthands.utils.UrlUtil;
import com.example.mac.oncqupthands.view.activity.LoginActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import start.ImageLoader;

import static com.example.mac.oncqupthands.view.activity.LoginActivity.isLogin;

/**
 * Created by mac on 2018/5/25.
 */

@SuppressLint("ValidFragment")
public class UserFragment extends Fragment {
    private View view;
    private Context context;

    private TextView username;
    private ImageView avatar;
    private TextView edit;
    private mUser user;
    private UserBean userBean;

    @SuppressLint("ValidFragment")
    public UserFragment(Context context) {
        this.context = context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.user_fragment,container,false);
        username = view.findViewById(R.id.user_name);
        avatar = view.findViewById(R.id.user_avatar);
        edit = view.findViewById(R.id.user_sign);
        username.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isLogin){
                    Intent intent = new Intent(getActivity(), LoginActivity.class);
                    startActivity(intent);
                }
            }
        });
        return view;
    }

    @Override
    public void onStart() {
        if(isLogin){
            Log.d("Fxy", "onCreateView: ");
            user = (mUser)(getActivity().getApplication());
            Map<String,String> map = new HashMap<>();
            Log.d("Fxy", "onCreateView: "+user.getIdNum());
            map.put("idNum",user.getIdNum());
            map.put("stuNum",user.getStuNum());
            NetUtil.Post(Api.search, map, new NetUtil.Callback() {
                @Override
                public void onSucceed(String response) throws JSONException {
                    Log.d("Fxy", "onSucceed: "+response);
                    JsonUtil.AddUserInfo(response);
                    userBean = JsonUtil.userBean;
                    initInfo();
                }

                @Override
                public void onFailed(String response) {
                    Log.d("Fxy", "onFailed: "+response);

                }
            });
        }
        super.onStart();
    }

    private void initInfo(){
        if(isLogin){
            ImageLoader.with(context)
                    .load(UrlUtil.addS(userBean.getPhoto_thumbnail_src()))
                    .into(avatar)
                    .display();
            username.setText(userBean.getNickname());
//            edit.setText(userBean.getIntroduction());
        }
    }
}