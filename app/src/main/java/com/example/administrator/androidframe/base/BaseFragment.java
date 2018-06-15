package com.example.administrator.androidframe.base;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.androidframe.MainActivity;
import com.example.wsq.library.struct.FunctionsManage;
import com.example.wsq.library.utils.LogUtils;
import com.example.wsq.library.view.loadding.LoadingDialog;
import com.example.wsq.mvpframe.presenter.BasePresenter;
import com.example.wsq.mvpframe.view.BaseView;

import butterknife.ButterKnife;

public abstract class BaseFragment<V extends BaseView, T extends BasePresenter<V>> extends Fragment implements BaseView {


    public static String _INTERFACE_NPNR = "_NPNR";  //没参数没有返回值
    public static String _INTERFACE_WITHP = "_WITHP";  //只有参数
    public static String _INTERFACE_WITHR = "_WITHR";  //只有返回值
    public static String _INTERFACE_WITHPR = "_WITHPR";  //有参数有返回值

    private static final String STATE_SAVE_IS_HIDDEN = "STATE_SAVE_IS_HIDDEN";

    /**
     * Fragment当前状态是否可见
     */
    protected boolean isVisible = false;
    /**
     * Fragment的view是否已创建
     */
    protected boolean mIsViewCreated = false;

    public static String INTERFACE_BACK = BaseFragment.class.getName();

    public FunctionsManage mFunctionsManage;
    protected T ipresenter;
    private LoadingDialog dialog;
    private long start_Time;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        start_Time = System.currentTimeMillis();
        View view  = inflater.inflate(getLayoutId(), container, false);
        dialog = new LoadingDialog(getContext());
        ipresenter = createPresenter();
        if (ipresenter != null) {
            ipresenter.attachView((V) this);
        }
        ButterKnife.bind(this,view);
        mIsViewCreated = true;
        return view;
    }
    protected  abstract T createPresenter();

    protected abstract int getLayoutId();

    protected abstract void initView();


    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(STATE_SAVE_IS_HIDDEN, isHidden());
    }

    public void setFunctionsManager(FunctionsManage functionsManager){

        this.mFunctionsManage = functionsManager;
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            onAttachToContext(activity);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        onAttachToContext(context);

    }
    protected void onAttachToContext(Context context) {
        if (context instanceof MainActivity){
            MainActivity mBaseActivity = (MainActivity) context;

            try {
                if (getTag() != null)
                    mBaseActivity.setFunctionsForFragment(getTag());
            }catch (Exception e){
                e.printStackTrace();
            }

        }
    }

    @Override
    public void onAttachFragment(Fragment childFragment) {
        super.onAttachFragment(childFragment);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
//        initPrepare();
    }
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
    public void onReLogin() {

    }
}
