package cc.haoduoyu.gank;

import android.app.Application;

import cc.haoduoyu.gank.injector.components.AppComponent;
import cc.haoduoyu.gank.injector.components.DaggerAppComponent;
import cc.haoduoyu.gank.injector.modules.AppModule;

/**
 * Created by xiepan on 16/8/22.
 */
public class GankApplication extends Application {

    public static String cacheDir;
    private AppComponent mAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();

//        GankRetrofit.i.init();

        mAppComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();

        if (getApplicationContext().getExternalCacheDir() != null && isExistSDCard()) {
            cacheDir = getApplicationContext().getExternalCacheDir().toString();
        } else {
            cacheDir = getApplicationContext().getCacheDir().toString();
        }
    }

    public AppComponent getAppComponent() {
        return mAppComponent;
    }


    private boolean isExistSDCard() {
        return android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED);
    }
}
