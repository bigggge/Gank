package cc.handuoyu.gank.injector.components;

import cc.handuoyu.gank.injector.ActivityScope;
import cc.handuoyu.gank.injector.modules.MainModule;
import cc.handuoyu.gank.ui.activity.MainActivity;
import dagger.Component;

/**
 * MainComponent 依赖 {@link AppComponent} 的{@link AppComponent#getGankApi()}
 * Created by xiepan on 2016/9/19.
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = MainModule.class)
public interface MainComponent {
    void inject(MainActivity mainActivity);
}
