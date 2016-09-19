package cc.handuoyu.gank.presenter;

import android.app.Activity;

import cc.handuoyu.gank.api.GankApi;
import cc.handuoyu.gank.api.GankRetrofit;
import cc.handuoyu.gank.ui.view.IBaseView;

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