package com.example.week3work.shop.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.week3work.R;
import com.example.week3work.shop.bean.ShopShowBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class HotShopAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private List<ShopShowBean.ResultBean.RxxpBean.CommodityListBean> mList;
    private Context mContext;

    public HotShopAdapter(Context mContext) {
        this.mContext = mContext;
        mList = new ArrayList<>();
    }

    public void setmList(List<ShopShowBean.ResultBean.RxxpBean.CommodityListBean> lists) {
        mList.clear();
        if (lists != null) {
            mList.addAll(lists);
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_show_hot, viewGroup, false);
        return new HotViewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int i) {
        HotViewholder hotViewholder = (HotViewholder) viewHolder;
        Uri uri = Uri.parse(mList.get(i).getMasterPic());
        hotViewholder.simpleView.setImageURI(uri);
        hotViewholder.tv1.setText(mList.get(i).getCommodityName());
        hotViewholder.tv2.setText(mList.get(i).getPrice()+"");
        hotViewholder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener!=null){
                    listener.setonclicklisener(i);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    static class HotViewholder extends RecyclerView.ViewHolder {
        SimpleDraweeView simpleView;TextView tv1;TextView tv2;
        public HotViewholder(@NonNull View itemView) {
            super(itemView);
            simpleView=itemView.findViewById(R.id.simpleView);
            tv1=itemView.findViewById(R.id.tv1);
            tv2=itemView.findViewById(R.id.tv2);
        }
    }

    private onClickListener listener;

    public void onClickListener(onClickListener listener) {
        this.listener = listener;
    }

    public interface onClickListener{
        void setonclicklisener(int index);
    }

}
