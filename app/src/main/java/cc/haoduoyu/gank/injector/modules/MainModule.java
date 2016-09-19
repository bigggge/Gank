package cc.haoduoyu.gank.injector.modules;

import cc.haoduoyu.gank.api.GankApi;
import cc.haoduoyu.gank.injector.ActivityScope;
import cc.haoduoyu.gank.mvp.presenter.MainPresenter;
import cc.haoduoyu.gank.ui.activity.MainActivity;
import dagger.Module;
import dagger.Provides;

/**
 * Created by xiepan on 2016/9/19.
 */
@Module
public class MainModule {

    MainActivity mainActivity;

    public MainModule(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

//    @Provides
//    @ActivityScope
//    MainActivity provideMainActivity() {
//        return mainActivity;
//    }

    @Provides
    @ActivityScope
    MainPresenter provideMainPresenter(GankApi gankApi) {
        return new MainPresenter(gankApi);
    }

}
