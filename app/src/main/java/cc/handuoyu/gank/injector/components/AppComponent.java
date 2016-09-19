package cc.handuoyu.gank.injector.components;

import android.app.Application;

import javax.inject.Singleton;

import cc.handuoyu.gank.api.GankApi;
import cc.handuoyu.gank.injector.modules.AppModule;
import dagger.Component;

/**
 * Components 从根本上来说就是一个注入器，也可以说是@Inject和@Module的桥梁，它的主要作用就是连接这两个部分。
 *
 * Created by xiepan on 2016/9/19.
 */
@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {

//    Application getApplication();

    //    @Named("simple")
    // @Named("common")
    GankApi getGankApi();
}
