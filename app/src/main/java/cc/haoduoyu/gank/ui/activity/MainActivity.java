package cc.haoduoyu.gank.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import cc.haoduoyu.gank.GankApplication;
import cc.haoduoyu.gank.R;
import cc.haoduoyu.gank.injector.components.DaggerMainComponent;
import cc.haoduoyu.gank.injector.modules.MainModule;
import cc.haoduoyu.gank.mvp.model.entity.GankEntity;
import cc.haoduoyu.gank.mvp.presenter.MainPresenter;
import cc.haoduoyu.gank.mvp.view.IMainView;
import cc.haoduoyu.gank.ui.adapter.MainAdapter;

/**
 * Created by xiepan on 16/8/22.
 */
public class MainActivity extends BaseSwipeRefreshActivity implements IMainView {

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    @Inject
    MainPresenter mPresenter;

    MainAdapter mAdapter;
    private boolean mHasMoreData = true;


    @Override
    protected void onRefresh() {
        mPresenter.loadPage(new Date(System.currentTimeMillis()));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Gank", false);
        initRecycleView();

        mPresenter.attachView(this);
        mPresenter.onCreate();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mPresenter.onStart();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mPresenter.onPause();
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void setupActivityComponent() {
        DaggerMainComponent.builder()
                .appComponent(((GankApplication) getApplication()).getAppComponent())
                .mainModule(new MainModule(this))
                .build()
                .inject(this);
//        .builder()
//                .activityModule(new ActivityModule(this))
//                .appComponent(((GankApplication) getApplication()).getAppComponent())
//                .avengerInformationModule(new AvengerInformationModule(avengerId))
//                .build().inject(this);


    }

    @Override
    public void fillData(List<GankEntity> data, boolean needClear) {
        if (needClear) {
            mAdapter.updateWithClear(data);
        } else {
            mAdapter.update(data);
        }
    }

    @Override
    public void hasNoMoreData() {
        mHasMoreData = false;
    }

    private void initRecycleView() {
        final LinearLayoutManager layoutManager = new GridLayoutManager(this, 2);
        mRecyclerView.setLayoutManager(layoutManager);
        mAdapter = new MainAdapter(this);
//        mAdapter.setListener();
        mRecyclerView.setAdapter(mAdapter);

        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                boolean isBottom = layoutManager.findLastCompletelyVisibleItemPosition() >= mAdapter.getItemCount() - 6;
                if (!mSwipeRefreshLayout.isRefreshing() && isBottom && mHasMoreData) {
                    mPresenter.loadNextPage();
                } else if (!mHasMoreData) {
                    hasNoMoreData();
                }
            }
        });
    }
}
