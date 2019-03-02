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

public class PinShopAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<ShopShowBean.ResultBean.PzshBean.CommodityListBeanX> mList;
    private Context mContext;

    public PinShopAdapter(Context mContext) {
        this.mContext = mContext;
        mList=new ArrayList<>();
    }

    public void setmList(List<ShopShowBean.ResultBean.PzshBean.CommodityListBeanX> lists) {
        mList.clear();
        if (lists!=null){
            mList.addAll(lists);
        }
        notifyDataSetChanged();


    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(mContext).inflate(R.layout.item_show_mo,viewGroup,false);
        return new PzViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int i) {
        PzViewHolder pzViewHolder= (PzViewHolder) viewHolder;
        Uri uri=Uri.parse(mList.get(i).getMasterPic());
        pzViewHolder.draweeView.setImageURI(uri);
        pzViewHolder.tv1.setText(mList.get(i).getCommodityName());
        pzViewHolder.tv2.setText(mList.get(i).getPrice()+"");
        pzViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
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

    static class PzViewHolder extends RecyclerView.ViewHolder{
        SimpleDraweeView draweeView;
        TextView tv1,tv2;
        public PzViewHolder(@NonNull View itemView) {
            super(itemView);

            draweeView=itemView.findViewById(R.id.moSimpleView);
            tv1=itemView.findViewById(R.id.tv1);
            tv2=itemView.findViewById(R.id.tv2);
        }
    }
    private HotShopAdapter.onClickListener listener;

    public void onClickListener(HotShopAdapter.onClickListener listener) {
        this.listener = listener;
    }

    public interface onClickListener{
        void setonclicklisener(int index);
    }

}
