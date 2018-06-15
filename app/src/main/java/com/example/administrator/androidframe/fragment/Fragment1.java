package com.example.administrator.androidframe.fragment;

import android.view.View;
import android.widget.TextView;

import com.example.administrator.androidframe.R;
import com.example.administrator.androidframe.base.BaseFragment;
import com.example.wsq.mvpframe.presenter.BasePresenter;

import butterknife.BindView;
import butterknife.OnClick;

public class Fragment1 extends BaseFragment {

    public static final String TAG = Fragment1.class.getName();
    public static final String OPEN_FRAGMENT = TAG + _INTERFACE_WITHP;
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

    @OnClick({R.id.tv_name})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.tv_name:
                mFunctionsManage.invokeFunction(OPEN_FRAGMENT, 1);
                break;
        }
    }
}
