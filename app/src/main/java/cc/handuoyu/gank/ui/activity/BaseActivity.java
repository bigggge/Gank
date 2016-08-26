package cc.handuoyu.gank.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import butterknife.BindView;
import butterknife.ButterKnife;
import cc.handuoyu.gank.R;
import cc.handuoyu.gank.presenter.BasePresenter;

/**
 * Created by xiepan on 16/8/22.
 */
public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity {
    @BindView(R.id.toolbar)
    protected Toolbar mToolbar;

    protected P mPresenter;

    protected abstract void initPresenter();

    protected abstract int getLayout();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        ButterKnife.bind(this);
        initPresenter();
        checkPresenterIsNull();
        initToolBar();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    private void checkPresenterIsNull() {
        if (mPresenter == null) {
            throw new IllegalStateException("please init mPresenter in initPresenter() method ");
        }
    }

    protected int getMenuRes() {
        return -1;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (getMenuRes() < 0) return true;
        getMenuInflater().inflate(getMenuRes(), menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void initToolBar() {
        if (mToolbar == null) {
            throw new NullPointerException("please add a toolbar in your layout.");
        }
        setSupportActionBar(mToolbar);
    }

    public void setTitle(String title, boolean showHome) {
        setTitle(title);
        getSupportActionBar().setDisplayShowHomeEnabled(showHome);
        getSupportActionBar().setDisplayHomeAsUpEnabled(showHome);
    }
}
