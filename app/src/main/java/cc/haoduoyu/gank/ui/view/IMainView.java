package cc.haoduoyu.gank.ui.view;

/**
 * Created by xiepan on 16/8/22.
 */
public interface IMainView<T> extends ISwipeRefreshView {

    void updateData(T data, boolean needClear);

    void clearData();

    void hasNoMoreData();
}
