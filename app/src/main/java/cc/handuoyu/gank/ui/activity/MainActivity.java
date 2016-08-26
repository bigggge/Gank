package cc.handuoyu.gank.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.Date;
import java.util.List;

import butterknife.BindView;
import cc.handuoyu.gank.R;
import cc.handuoyu.gank.model.entity.GankEntity;
import cc.handuoyu.gank.presenter.MainPresenter;
import cc.handuoyu.gank.ui.adapter.MainAdapter;
import cc.handuoyu.gank.ui.view.IMainView;
import cc.handuoyu.gank.utils.LLog;

/**
 * Created by xiepan on 16/8/22.
 */
public class MainActivity extends BaseSwipeRefreshActivity<MainPresenter> implements IMainView<List<GankEntity>> {

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    MainAdapter mAdapter;

    private boolean mHasMoreData = true;


    @Override
    protected void onRefresh() {
        getData();
    }

    @Override
    protected void initPresenter() {
        mPresenter = new MainPresenter(this, this);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Gank", false);
        initRecycleView();
        getData();
    }

    private void getData() {
        mPresenter.loadPage(new Date(System.currentTimeMillis()));
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
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
