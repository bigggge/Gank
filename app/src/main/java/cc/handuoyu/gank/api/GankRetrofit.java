package cc.handuoyu.gank.api;

import okhttp3.OkHttpClient;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by xiepan on 16/8/22.
 */
@Deprecated
public enum GankRetrofit {
    i;

    private GankApi mService;


    public GankApi getService() {
        return mService;
    }

    public void init() {

        OkHttpClient client = new OkHttpClient.Builder()
                .build();

        retrofit2.Retrofit retrofit = new retrofit2.Retrofit.Builder()
                .baseUrl(GankApi.HOST)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        mService = retrofit.create(GankApi.class);
    }
}
