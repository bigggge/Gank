package cc.haoduoyu.gank.ui.activity;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;

import butterknife.BindView;
import cc.haoduoyu.gank.R;
import cc.haoduoyu.gank.mvp.view.ISwipeRefreshView;

/**
 * Created by xiepan on 16/8/22.
 */
public abstract class BaseSwipeRefreshActivity extends BaseActivity implements ISwipeRefreshView {

    @BindView(R.id.swipe_refresh_layout)
    protected SwipeRefreshLayout mSwipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initSwipeLayout();
    }

    private void initSwipeLayout() {
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary, R.color.colorPrimaryDark, R.color.colorAccent);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (prepareRefresh()) {
                    onRefresh();
                } else {
                    hideRefreshView();
                }
            }
        });
    }

    protected boolean prepareRefresh() {
        return true;
    }

    protected abstract void onRefresh();

    @Override
    public void hideRefreshView() {
        mSwipeRefreshLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (mSwipeRefreshLayout != null) {
                    mSwipeRefreshLayout.setRefreshing(false);
                }
            }
        }, 1000);
    }

    @Override
    public void showRefreshView() {
        mSwipeRefreshLayout.setRefreshing(true);
    }

    protected boolean isRefreshing() {
        return mSwipeRefreshLayout.isRefreshing();
    }

}
