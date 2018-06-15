package com.example.administrator.androidframe.fragment;

import android.widget.TextView;

import com.example.administrator.androidframe.R;
import com.example.administrator.androidframe.base.BaseFragment;
import com.example.wsq.mvpframe.presenter.BasePresenter;

import butterknife.BindView;

public class Fragment4 extends BaseFragment {
    public static final String TAG = Fragment4.class.getName();
    @BindView(R.id.tv_name) TextView tv_name;

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.layout_fragment;
    }

    @Override
    protected void initView() {
        tv_name.setText(getArguments().getString("name"));
    }
}
