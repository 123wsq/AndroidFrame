package com.example.wsq.library.interceptor;

import android.content.Context;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.annotation.Interceptor;
import com.alibaba.android.arouter.facade.callback.InterceptorCallback;
import com.alibaba.android.arouter.facade.template.IInterceptor;
import com.alibaba.android.arouter.launcher.ARouter;
import com.example.wsq.library.utils.LogUtils;

@Interceptor(priority = 1)
public class LoginInterceptor implements IInterceptor{
    @Override
    public void process(Postcard postcard, InterceptorCallback callback) {

        LogUtils.d(postcard.getPath() +"========="+ postcard.getGroup());

        boolean isLogin = true;
        if (isLogin){
            callback.onContinue(postcard);
        }else {
            callback.onInterrupt(null);
//            ARouter.getInstance().build("login").navigation();
        }
    }

    @Override
    public void init(Context context) {

    }
}
