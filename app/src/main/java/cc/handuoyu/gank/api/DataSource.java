package cc.handuoyu.gank.api;

import javax.inject.Inject;

import cc.handuoyu.gank.mvp.model.GankData;
import cc.handuoyu.gank.mvp.model.GirlData;
import cc.handuoyu.gank.mvp.model.VideoData;
import okhttp3.OkHttpClient;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Path;
import rx.Observable;

/**
 * 用于给
 * {@link cc.handuoyu.gank.injector.modules.AppModule#provideGankApiCommon(DataSource)}
 * 使用
 * Created by xiepan on 2016/9/19.
 */
public class DataSource implements GankApi {

    GankApi api;

    @Inject
    public DataSource() {
        OkHttpClient client = new OkHttpClient.Builder()
                .build();

        retrofit2.Retrofit retrofit = new retrofit2.Retrofit.Builder()
                .baseUrl(GankApi.HOST)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        api = retrofit.create(GankApi.class);
    }

    @Override
    public Observable<GirlData> getGirls(@Path("pagesize") int pagesize, @Path("page") int page) {
        return api.getGirls(pagesize, page);
    }

    @Override
    public Observable<VideoData> getVideos(@Path("pagesize") int pagesize, @Path("page") int page) {
        return api.getVideos(pagesize, page);
    }

    @Override
    public Observable<GankData> getGanks(@Path("year") int year, @Path("month") int month, @Path("day") int day) {
        return api.getGanks(year, month, day);
    }
}
