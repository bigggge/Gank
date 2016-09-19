package cc.haoduoyu.gank;

import android.app.Application;

import cc.haoduoyu.gank.api.GankRetrofit;

/**
 * Created by xiepan on 16/8/22.
 */
public class Gank extends Application {

    public static String cacheDir;

    @Override
    public void onCreate() {
        super.onCreate();
        GankRetrofit.i.init();

        if (getApplicationContext().getExternalCacheDir() != null && isExistSDCard()) {
            cacheDir = getApplicationContext().getExternalCacheDir().toString();
        } else {
            cacheDir = getApplicationContext().getCacheDir().toString();
        }
    }


    private boolean isExistSDCard() {
        return android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED);
    }
}
