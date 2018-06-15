package com.example.administrator.androidframe.fragment;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.example.administrator.androidframe.R;
import com.example.administrator.androidframe.base.BaseFragment;
import com.example.wsq.mvpframe.presenter.BasePresenter;

import butterknife.BindView;
import butterknife.OnClick;




/**
 * Created by wsq on 2018/1/23.
 */

public class MainFragment extends BaseFragment {


    @BindView(R.id.tv_tab_1) TextView tv_tab_1;
    @BindView(R.id.tv_tab_2) TextView tv_tab_2;
    @BindView(R.id.tv_tab_3) TextView tv_tab_3;
    @BindView(R.id.tv_tab_4) TextView tv_tab_4;
    public static final String TAG = MainFragment.class.getName();
    private Fragment curFragment;
    private FragmentManager fragmentManager;

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    public int getLayoutId() {
        return R.layout.layout_fragment_main;
    }

    @Override
    public void initView() {
        fragmentManager = getActivity().getSupportFragmentManager();
        onEnter(new Fragment1(), Fragment1.TAG, "这是第一个");
    }

    @OnClick({R.id.tv_tab_1, R.id.tv_tab_2, R.id.tv_tab_3, R.id.tv_tab_4})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.tv_tab_1:
                onEnter(new Fragment1(), Fragment1.TAG, "这是第一个");
                break;
            case R.id.tv_tab_2:
                onEnter(new Fragment2(), Fragment2.TAG, "这是第二个");
                break;
            case R.id.tv_tab_3:
                onEnter(new Fragment3(), Fragment3.TAG,"这是第三个");
                break;
            case R.id.tv_tab_4:
                onEnter(new Fragment4(), Fragment4.TAG, "这是第四个");
                break;
        }
    }

    private void onEnter(Fragment fragment,String tag,  String name){
        FragmentTransaction fTransaction = fragmentManager.beginTransaction();
        Bundle bundle = new Bundle();
        bundle.putString("name", name);
        fragment.setArguments(bundle);
        fTransaction.replace(R.id.ll_content, fragment,tag).commit();
    }
}
