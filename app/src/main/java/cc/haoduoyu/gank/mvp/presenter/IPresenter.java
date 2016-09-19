package cc.haoduoyu.gank.mvp.presenter;

import cc.haoduoyu.gank.mvp.view.IBaseView;

/**
 * Created by xiepan on 2016/9/19.
 */
public interface IPresenter {

    void attachView(IBaseView v);

    void onCreate();

    void onStart();

    void onPause();

    void onStop();

}
