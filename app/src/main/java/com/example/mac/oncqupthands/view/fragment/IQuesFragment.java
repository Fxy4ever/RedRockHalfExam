package com.example.mac.oncqupthands.view.fragment;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;

/**
 * Created by mac on 2018/5/25.
 */

public interface IQuesFragment {
    Context getmContext();
    SwipeRefreshLayout getSwipreRefreshLayout();
    String getKind();
}
