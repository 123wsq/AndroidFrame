package com.example.administrator.androidframe.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.wsq.library.view.loadding.LoadingDialog;
import com.example.wsq.mvpframe.presenter.BasePresenter;
import com.example.wsq.mvpframe.view.BaseView;

import butterknife.ButterKnife;

public abstract  class BaseActivity<V extends BaseView, T extends BasePresenter<V>> extends AppCompatActivity implements BaseView{

    protected T presenter;
    private LoadingDialog dialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(getLayoutId());
        ButterKnife.bind(this);
        presenter = createPresenter();
        dialog = new LoadingDialog(getContext());
        if (presenter != null){
            presenter.attachView((V) this);
        }
        initView(savedInstanceState);
    }

    protected abstract T createPresenter();

    protected abstract int getLayoutId();

    protected abstract void initView(Bundle savedInstanceState);


    @Override
    public void onShowLoadding() {
        if (!dialog.isShowing())
            dialog.show();
    }

    @Override
    public void onDismissLoadding() {
        if (dialog.isShowing())
            dialog.dismiss();
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void onReLogin() {

    }
}
