package com.example.administrator.androidframe;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.example.administrator.androidframe.base.BaseActivity;
import com.example.administrator.androidframe.base.BaseFragment;
import com.example.administrator.androidframe.fragment.Fragment1;
import com.example.administrator.androidframe.fragment.Fragment5;
import com.example.administrator.androidframe.fragment.MainFragment;
import com.example.wsq.library.struct.FunctionNoParamNoResult;
import com.example.wsq.library.struct.FunctionWithParamOnly;
import com.example.wsq.library.struct.FunctionsManage;
import com.example.wsq.library.utils.LogUtils;
import com.example.wsq.mvpframe.presenter.BasePresenter;

import java.util.ArrayList;
import java.util.List;


@Route(path = "/app/mainactivity")
public class MainActivity extends BaseActivity implements View.OnClickListener {

   TextView start_break;
   TextView start_samebreak;
   TextView start_parambreak;
   TextView start_resultbreak;
   TextView start_openfragment;

    private Fragment curFragment;
    private FragmentManager fragmentManager;
    private List<Fragment> mListFragment;



    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        start_break =findViewById(R.id.start_break);
        start_samebreak =findViewById(R.id.start_samebreak);
        start_parambreak =findViewById(R.id.start_parambreak);
        start_resultbreak = findViewById(R.id.start_resultbreak);
        start_openfragment = findViewById(R.id.start_openfragment);
        start_break.setOnClickListener(this);
        start_samebreak.setOnClickListener(this);
        start_parambreak.setOnClickListener(this);
        start_resultbreak.setOnClickListener(this);
        start_openfragment.setOnClickListener(this);

        mListFragment = new ArrayList<>();
        fragmentManager = getSupportFragmentManager();

            onEnter(new MainFragment(), MainFragment.TAG, false);

    }

    @Override
    public void onClick(View view){
        switch (view.getId()){
            case R.id.start_break:  //从当前model跳转到别的model
                ARouter.getInstance().build("/test/testactivity").navigation();
                break;
            case R.id.start_samebreak://从当前跳转到当前Model
                ARouter.getInstance().build("/app/testrouter").navigation();
                break;
            case R.id.start_parambreak: //携带参数跳转

                ARouter.getInstance().build("/app/testrouter")
                        .withString("name","wsq").withInt("age", 20).navigation();
                break;
            case R.id.start_resultbreak:
                ARouter.getInstance().build("/app/testrouter")
                        .withString("name","wsq").withInt("age", 20).navigation(this, 201);
                break;
            case R.id.start_openfragment:

                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        LogUtils.d("返回码： "+resultCode);
    }

    /**
     *
     * @param fragment
     * @param tag
     * @param isBack  是否支持返回
     */
    private void onEnter( Fragment fragment, String tag, boolean isBack){
        onEnter(fragment, tag, null, isBack);
    }

    /**
     *
     * @param fragment
     * @param tag
     * @param param 传递的参数
     * @param isBack  是否支持返回
     */
    private void onEnter(Fragment fragment, String tag, Bundle param, boolean isBack){
        FragmentTransaction fTransaction = fragmentManager.beginTransaction();

        if (curFragment != null){
            LogUtils.d("当前的fragment: "+curFragment.getTag());
        }
        if (curFragment != null) fTransaction.hide(curFragment);

        if (!fragment.isAdded()) {
            if (param != null) fragment.setArguments(param);

            mListFragment.add(fragment);
            fTransaction.add(R.id.layout_content, fragment, tag);
            if (isBack)fTransaction.addToBackStack(tag);
            fTransaction.show(fragment).commit();
        } else {
            fTransaction.show(fragment).commit();
        }
        curFragment = fragment;
    }
    public void setFunctionsForFragment(String tag){

        FragmentManager fm = getSupportFragmentManager();
        final BaseFragment fragment = (BaseFragment) fm.findFragmentByTag(tag);

        FunctionsManage functionsManage = FunctionsManage.getInstance();

        functionsManage.addFunction(new FunctionWithParamOnly<Integer>(Fragment1.OPEN_FRAGMENT) {
            @Override
            public void function(Integer data) {
                switch (data){
                    case 1:
                        Bundle bundle = new Bundle();
                        bundle.putString("name", "这个是在测试");
                        onEnter(new Fragment5(), Fragment5.TAG, bundle, true);
                        break;
                }
            }
        });


        functionsManage.addFunction(new FunctionNoParamNoResult(BaseFragment.INTERFACE_BACK) {
            @Override
            public void function() {

                onKeyBack();
            }
        });

        fragment.setFunctionsManager(functionsManage);
    }

    private void onKeyBack(){

        fragmentManager.popBackStack();
        if (mListFragment.size() > 1) {
            mListFragment.remove(mListFragment.size() - 1);
            curFragment = mListFragment.get(mListFragment.size()-1);
        }else{
            finish();
        }
    }
}
