package cc.haoduoyu.gank.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import butterknife.BindView;
import butterknife.ButterKnife;
import cc.haoduoyu.gank.R;
import cc.haoduoyu.gank.injector.components.AppComponent;

/**
 * Created by xiepan on 16/8/22.
 */
public abstract class BaseActivity extends AppCompatActivity {
    @BindView(R.id.toolbar)
    protected Toolbar mToolbar;

    protected abstract int getLayout();

    protected abstract void setupActivityComponent();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        setupActivityComponent();
        ButterKnife.bind(this);
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
