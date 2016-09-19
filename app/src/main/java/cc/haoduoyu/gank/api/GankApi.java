package cc.haoduoyu.gank.api;

import cc.haoduoyu.gank.model.GankData;
import cc.haoduoyu.gank.model.GirlData;
import cc.haoduoyu.gank.model.VideoData;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by xiepan on 16/8/22.
 */
public interface GankApi {
    String HOST = "http://gank.io/api/";

    /**
     * @param pagesize
     * @param page
     * @return
     */
    @GET("data/福利/{pagesize}/{page}")
    Observable<GirlData> getGirls(@Path("pagesize") int pagesize, @Path("page") int page);

    /**
     * @param pagesize
     * @param page
     * @return
     */
    @GET("data/休息视频/{pagesize}/{page}")
    Observable<VideoData> getVideos(@Path("pagesize") int pagesize, @Path("page") int page);

    /**
     * http://gank.io/api/day/2015/08/07
     *
     * @param year
     * @param month
     * @param day
     * @return
     */
    @GET("day/{year}/{month}/{day}")
    Observable<GankData> getGanks(@Path("year") int year, @Path("month") int month, @Path("day") int day);
}
