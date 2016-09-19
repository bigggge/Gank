package cc.haoduoyu.gank.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import cc.haoduoyu.gank.model.entity.GankEntity;

/**
 * Created by xiepan on 16/8/22.
 */
public class GankData extends BaseData {

    public List<String> category;
    public Result results;

    public class Result {
        @SerializedName("Android")
        public List<GankEntity> androidList;
        @SerializedName("iOS")
        public List<GankEntity> iOSList;
        @SerializedName("休息视频")
        public List<GankEntity> 休息视频List;
        @SerializedName("拓展资源")
        public List<GankEntity> 拓展资源List;
        @SerializedName("瞎推荐")
        public List<GankEntity> 瞎推荐List;
        @SerializedName("福利")
        public List<GankEntity> 福利List;

    }

}
