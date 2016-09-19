package cc.haoduoyu.gank.presenter;

import android.app.Activity;

import cc.haoduoyu.gank.api.GankApi;
import cc.haoduoyu.gank.api.GankRetrofit;
import cc.haoduoyu.gank.ui.view.IBaseView;

/**
 * Created by xiepan on 16/8/22.
 */
public class BasePresenter<V extends IBaseView> {
    public static final GankApi mGank = GankRetrofit.i.getService();
    protected V mView;
    protected Activity mContext;

    public BasePresenter(Activity context, V view) {
        mContext = context;
        mView = view;
    }
}
