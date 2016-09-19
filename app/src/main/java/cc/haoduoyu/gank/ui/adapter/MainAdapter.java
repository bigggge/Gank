package cc.haoduoyu.gank.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cc.haoduoyu.gank.R;
import cc.haoduoyu.gank.mvp.model.entity.GankEntity;

interface OnClickItemListener {
    void onTouch(View v, GankEntity entity);
}

/**
 * Created by xiepan on 16/8/26.
 */
public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {
    private OnClickItemListener listener;
    private Context mContext;
    private List<GankEntity> gankEntityList = new ArrayList<>();

    public MainAdapter(Context context) {
        this.mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_main, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String url = gankEntityList.get(position).getUrl();
        Glide.with(mContext)
                .load(url)
                .centerCrop()
                .crossFade()
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return gankEntityList.size();
    }

    public void updateWithClear(List<GankEntity> data) {
        gankEntityList.clear();
        update(data);
    }

    public void update(List<GankEntity> data) {
        gankEntityList = data;
        notifyDataSetChanged();
    }

    public void setListener(OnClickItemListener onClickItemListener) {
        this.listener = onClickItemListener;
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.image)
        ImageView imageView;
        GankEntity entity;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            imageView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            listener.onTouch(v, entity);
        }
    }
}