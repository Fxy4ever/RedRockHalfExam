package com.example.mac.oncqupthands.view.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mac.oncqupthands.R;

/**
 * Created by mac on 2018/5/25.
 */

public class KebiaoFragment extends Fragment {
    private Context context;
    private View view;

    @SuppressLint("ValidFragment")
    public KebiaoFragment(Context context) {
        this.context = context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.kebiao_fragment,container,false);
        return view;
    }
}
