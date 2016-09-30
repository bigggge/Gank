package cc.haoduoyu.gank.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.List;

import butterknife.BindView;
import cc.haoduoyu.gank.R;
import cc.haoduoyu.gank.model.entity.GankEntity;
import cc.haoduoyu.gank.presenter.GirlPresenter;
import cc.haoduoyu.gank.ui.adapter.MainAdapter;
import cc.haoduoyu.gank.ui.view.IMainView;

/**
 * Created by xiepan on 16/8/22.
 */
public class MainActivity extends BaseSwipeRefreshActivity<GirlPresenter> implements IMainView<List<GankEntity>> {

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    MainAdapter mAdapter;

    private boolean mHasMoreData = true;


    @Override
    protected void initPresenter() {
        mPresenter = new GirlPresenter(this, this);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Gank", false);
        initRecycleView();
        getData();
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
                boolean isBottom = layoutManager.findLastCompletelyVisibleItemPosition() >= mAdapter.getItemCount() - 2;
                if (!mSwipeRefreshLayout.isRefreshing() && isBottom && mHasMoreData) {
                    mPresenter.loadNextPage();
                } else if (!mHasMoreData) {
                    Toast.makeText(MainActivity.this, "has no more data", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void startRefresh() {
        mPresenter.refreshPage();
    }

    private void getData() {
        mPresenter.loadPage();
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void updateData(List<GankEntity> data, boolean needClear) {
        if (needClear) {
            mAdapter.updateWithClear(data);
        } else {
            mAdapter.update(data);
        }
    }

    @Override
    public void clearData() {
        mAdapter.clearData();
    }

    @Override
    public void hasNoMoreData() {
        mHasMoreData = false;
    }

    @Override
    public void showEmptyView() {
        Toast.makeText(this, "empty", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showErrorView() {
        Toast.makeText(this, "error", Toast.LENGTH_SHORT).show();
    }
}
