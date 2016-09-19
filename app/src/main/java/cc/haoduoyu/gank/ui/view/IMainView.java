package cc.haoduoyu.gank.ui.view;

/**
 * Created by xiepan on 16/8/22.
 */
public interface IMainView<T> extends ISwipeRefreshView {

    void fillData(T data, boolean needClear);

    void hasNoMoreData();
}
