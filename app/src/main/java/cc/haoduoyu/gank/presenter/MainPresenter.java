package cc.haoduoyu.gank.presenter;

import android.app.Activity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import cc.haoduoyu.gank.model.GankData;
import cc.haoduoyu.gank.model.entity.GankEntity;
import cc.haoduoyu.gank.ui.view.IMainView;
import cc.haoduoyu.gank.utils.LLog;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by xiepan on 16/8/22.
 */
public class MainPresenter extends BasePresenter<IMainView> {

    private static final int DAY_OF_MILLISECOND = 24 * 60 * 60 * 1000;
    List<GankEntity> mGankList = new ArrayList<>();
    private Date mCurrentDate;
    //没有数据的天数
    private int mCountOfDataEmptyDay = 0;
    private boolean hasLoadMoreData = false;
    private boolean needClear = true;

    public MainPresenter(Activity context, IMainView view) {
        super(context, view);
    }

    @SuppressWarnings("unchecked")
    public void loadPage(final Date date) {
        mView.showRefreshView();
        mCurrentDate = date;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        LLog.d("year: " + year + " month: " + month + " day: " + day);
        mGank.getGanks(year, month, day)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                //转换数据格式
                .map(new Func1<GankData, GankData.Result>() {
                    @Override
                    public GankData.Result call(GankData gankData) {
                        return gankData.results;
                    }
                })
                .map(new Func1<GankData.Result, List<GankEntity>>() {
                    @Override
                    public List<GankEntity> call(GankData.Result result) {
                        return addAllResults(result);
                    }
                })
                .subscribe(new Subscriber<List<GankEntity>>() {
                    @Override
                    public void onCompleted() {
                        mCurrentDate = new Date(date.getTime() - DAY_OF_MILLISECOND);
                        mView.hideRefreshView();
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        LLog.e(e.getMessage());
                    }

                    @Override
                    public void onNext(List<GankEntity> gankEntities) {
                        if (gankEntities.isEmpty()) {
                            mCountOfDataEmptyDay++;
                            if (mCountOfDataEmptyDay > 5) {
                                mView.hasNoMoreData();
                                return;
                            }
                            loadPage(new Date(date.getTime() - DAY_OF_MILLISECOND));
                        } else {
                            mCountOfDataEmptyDay = 0;
                            mView.updateData(gankEntities, needClear);
                        }
                    }
                });
    }

    public void loadNextPage() {
        needClear = false;
        loadPage(mCurrentDate);
    }


    private List<GankEntity> addAllResults(GankData.Result results) {
        mGankList.clear();
//        if (results.androidList != null) mGankList.addAll(results.androidList);
//        if (results.iOSList != null) mGankList.addAll(results.iOSList);
//        if (results.休息视频List != null) mGankList.addAll(results.休息视频List);
//        if (results.拓展资源List != null) mGankList.addAll(results.拓展资源List);
//        if (results.瞎推荐List != null) mGankList.addAll(results.瞎推荐List);
        if (results.福利List != null) mGankList.addAll(0, results.福利List);
        return mGankList;
    }

}
