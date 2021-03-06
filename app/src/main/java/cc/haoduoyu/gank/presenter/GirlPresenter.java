package cc.haoduoyu.gank.presenter;

import android.app.Activity;

import java.util.List;

import cc.haoduoyu.gank.model.GirlData;
import cc.haoduoyu.gank.model.entity.GankEntity;
import cc.haoduoyu.gank.ui.view.IMainView;
import cc.haoduoyu.gank.utils.LLog;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by xiepan on 2016/9/19.
 */
public class GirlPresenter extends BasePresenter<IMainView> {

    private int mCurrentPage = 1;
    private static final int PAGE_SIZE = 10;

    public GirlPresenter(Activity context, IMainView view) {
        super(context, view);
    }

    @SuppressWarnings("unchecked")
    public void loadPage() {
        mGank.getGirls(PAGE_SIZE, mCurrentPage)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Func1<GirlData, List<GankEntity>>() {
                    @Override
                    public List<GankEntity> call(GirlData girlData) {
                        return girlData.results;
                    }
                })
                .subscribe(new Subscriber<List<GankEntity>>() {
                    @Override
                    public void onCompleted() {
                        mView.hideRefreshView();
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        mView.showErrorView();
                        LLog.e(e.getMessage());
                    }

                    @Override
                    public void onNext(List<GankEntity> girlEntities) {
                        if (girlEntities.isEmpty()) {
                            //没有数据
                            mView.showEmptyView();
                        } else if (girlEntities.size() < PAGE_SIZE) {
                            //最后一页
                            mView.updateData(girlEntities, false);
                            mView.hasNoMoreData();
                        } else if (girlEntities.size() == PAGE_SIZE) {
                            //正常情况
                            mView.updateData(girlEntities, false);
                        }
                    }
                });
    }

    public void loadNextPage() {
        mCurrentPage++;
        loadPage();
    }

    public void refreshPage() {
        mCurrentPage = 1;
        mView.clearData();
        loadPage();
    }
}
