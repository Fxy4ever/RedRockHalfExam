package com.example.mac.oncqupthands.view.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mac.oncqupthands.R;
import com.example.mac.oncqupthands.adapter.QuestionListAdapter;
import com.example.mac.oncqupthands.mUser;
import com.example.mac.oncqupthands.presenter.QuestionFragPresenter;
import com.example.mac.oncqupthands.view.activity.LoginActivity;

import java.util.List;

/**
 * Created by mac on 2018/5/25.
 */

@SuppressLint("ValidFragment")
public class QuesFragment extends Fragment implements IQuesFragment {
    private Context context;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private View view;
    private CallBackValue callBackValue;
    private QuestionFragPresenter presenter;
    private String kind;


    public QuesFragment(Context context,String kind) {
        this.context = context;
        this.kind = kind;
    }

//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        callBackValue = (CallBackValue) getActivity();
//    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.question_fragment,container,false);
        initView();
        return view;
    }

    private void initView(){
        recyclerView = view.findViewById(R.id.question_recyclerview);
        swipeRefreshLayout = view.findViewById(R.id.question_swipe);
        presenter = new QuestionFragPresenter(this);
        presenter.loadData();
        QuestionListAdapter adapter = presenter.initAdapter();
        adapter.setKind(kind);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        presenter.Refresh();
    }

    @Override
    public SwipeRefreshLayout getSwipreRefreshLayout() {
        return swipeRefreshLayout;
    }

    @Override
    public String getKind() {
        return kind;
    }

    @Nullable
    @Override
    public Context getmContext() {
        return getContext();
    }



    public interface CallBackValue{
        void SendMediaValue(String Value);
    }

}
