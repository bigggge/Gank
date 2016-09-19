package cc.haoduoyu.gank.injector.modules;

import android.app.Application;

import javax.inject.Singleton;

import cc.haoduoyu.gank.api.GankApi;
import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Module 可以说就是依赖的原材料的制造工厂
 *
 * Created by xiepan on 2016/9/19.
 */
@Module
public class AppModule {

    private Application application;

    public AppModule(Application application) {
        this.application = application;
    }

//    @Provides
//    @Singleton
//    public Application provideApplication() {
//        return application;
//    }

//    @Named("common")
//    @Provides
//    @Singleton
//    GankApi provideGankApiCommon(DataSource dataSource) {
//        return dataSource;
//    }

    //    @Named("simple")
    @Provides
    @Singleton
    GankApi provideGankApiSimple() {

        retrofit2.Retrofit retrofit = new retrofit2.Retrofit.Builder()
                .baseUrl(GankApi.HOST)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(provideOkHttpClient())
                .build();

        return retrofit.create(GankApi.class);
    }

    private OkHttpClient provideOkHttpClient() {
        return new OkHttpClient.Builder().build();
    }
}
