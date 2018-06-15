package com.example.wsq.mvpframe.view;

import android.content.Context;

public interface BaseView {

    /**
     * 显示加载动画
     */
    void onShowLoadding();

    /**
     * 关闭加载动画
     */
    void onDismissLoadding();

    /**
     * 获取Context
     * @return
     */
    Context getContext();

    /**
     * 重新登录
     */
    void onReLogin();
}
