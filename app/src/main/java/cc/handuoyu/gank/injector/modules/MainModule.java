package cc.handuoyu.gank.injector.modules;

import cc.handuoyu.gank.api.GankApi;
import cc.handuoyu.gank.injector.ActivityScope;
import cc.handuoyu.gank.mvp.presenter.MainPresenter;
import cc.handuoyu.gank.ui.activity.MainActivity;
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
