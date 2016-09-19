package cc.handuoyu.gank.mvp.view;

import java.util.List;

import cc.handuoyu.gank.mvp.model.entity.GankEntity;

/**
 * Created by xiepan on 16/8/22.
 */
public interface IMainView extends ISwipeRefreshView {

    void fillData(List<GankEntity> data, boolean needClear);

    void hasNoMoreData();
}
