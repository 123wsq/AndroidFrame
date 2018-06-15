package com.example.wsq.mvpframe.presenter;

import com.example.wsq.library.okhttp.HttpRequestModel;
import com.example.wsq.library.okhttp.callback.OnMvpCallBack;
import com.example.wsq.library.okhttp.callback.OnRequestCallBack;
import com.example.wsq.library.utils.LogUtils;
import com.example.wsq.mvpframe.view.BaseView;

import java.lang.ref.WeakReference;
import java.util.Map;

public class BasePresenter<V extends BaseView> {

    protected WeakReference<V> weakReference;
    private HttpRequestModel httpRequest;


    //绑定
    public void attachView(V view){
        weakReference =new WeakReference<V>(view);
        httpRequest = new HttpRequestModel();
    }
    //解绑

    public void deachView(){
        weakReference.clear();

    }
    protected V getView(){
        return weakReference.get();
    }

    public void  onSendGetRequest(String url, Map<String, String> param, final OnRequestCallBack<Map<String, Object>> callBack)throws Exception{


        if (httpRequest != null){
            httpRequest.onSendGetRequest(url, param, new OnMvpCallBack<Map<String, Object>>() {
                @Override
                public void onSuccess(Map<String, Object> data) {
                    callBack.onSuccess(data);
                }

                @Override
                public void onFailure(String msg) {
                    LogUtils.d(msg);
                }

                @Override
                public void onOutTime(String msg) {
                    if (weakReference.get()!=null)
                        weakReference.get().onReLogin();
                }

                @Override
                public void onComplete() {

                    if (weakReference.get() !=null){
                        weakReference.get().onDismissLoadding();
                    }
                }
            });
        }else{
            throw new Exception("model没有初始化");
        }
    }

    public void  onSendPostRequest(String url, Map<String, String> param, final OnRequestCallBack<Map<String, Object>> callBack)throws Exception{


        if (httpRequest != null){
            httpRequest.onSendPostRequest(url, param, new OnMvpCallBack<Map<String, Object>>() {
                @Override
                public void onSuccess(Map<String, Object> data) {
                    callBack.onSuccess(data);
                }

                @Override
                public void onFailure(String msg) {
                    LogUtils.d(msg);
                }

                @Override
                public void onOutTime(String msg) {
                    if (weakReference.get()!=null)
                        weakReference.get().onReLogin();
                }

                @Override
                public void onComplete() {

                    if (weakReference.get() !=null){
                        weakReference.get().onDismissLoadding();
                    }
                }
            });
        }else{
            throw new Exception("model没有初始化");
        }
    }

}
