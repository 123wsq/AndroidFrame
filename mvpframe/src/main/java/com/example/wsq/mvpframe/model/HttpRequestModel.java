package com.example.wsq.mvpframe.model;

import com.example.wsq.library.okhttp.CallBackUtil;
import com.example.wsq.library.okhttp.OkhttpUtil;
import com.example.wsq.library.utils.LogUtils;
import com.example.wsq.library.utils.StringToMap;
import com.example.wsq.mvpframe.callback.OnMvpCallBack;

import java.util.Map;

import okhttp3.Call;

public class HttpRequestModel {

    public  void onSendGetRequest(final String url, Map<String, String> param, final OnMvpCallBack callBack)throws  Exception{

        OkhttpUtil.okHttpGet(url, param, new CallBackUtil.CallBackString() {
            @Override
            public void onFailure(Call call, Exception e) {

                callBack.onFailure("请求失败");
                callBack.onComplete();
            }

            @Override
            public void onResponse(String s) {

                LogUtils.d("请求返回 ： \nurl:"+url+"\n返回值："+s);
                try {
                    Map<String, Object> result = StringToMap.onString2Map(s);


                } catch (Exception e) {
                    callBack.onFailure("错误的数据格式");
                    e.printStackTrace();
                }
                callBack.onComplete();
            }
        });
    }

    public  void onSendPostRequest(final String url, Map<String, String> param, final OnMvpCallBack callBack) throws Exception{

        OkhttpUtil.okHttpPost(url, param, new CallBackUtil.CallBackString() {
            @Override
            public void onFailure(Call call, Exception e) {

                callBack.onFailure("请求失败");
                callBack.onComplete();
            }

            @Override
            public void onResponse(String s) {

                LogUtils.d("请求返回 ： \nurl:"+url+"\n返回值："+s);
                try {
                    Map<String, Object> result = StringToMap.onString2Map(s);


                } catch (Exception e) {
                    callBack.onFailure("错误的数据格式");
                    e.printStackTrace();
                }
                callBack.onComplete();
            }
        });
    }
}
