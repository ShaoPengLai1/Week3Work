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

public class MoShopAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<ShopShowBean.ResultBean.MlssBean.CommodityListBeanXX> mList;
    private Context mContext;

    public MoShopAdapter(Context mContext) {
        this.mContext = mContext;
        mList=new ArrayList<>();
    }

    public void setmList(List<ShopShowBean.ResultBean.MlssBean.CommodityListBeanXX> lists) {
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
        return new MoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int i) {
        MoViewHolder moViewHolder= (MoViewHolder) viewHolder;
        Uri uri=Uri.parse(mList.get(i).getMasterPic());
        moViewHolder.moSimpleView.setImageURI(uri);
        moViewHolder.tv1.setText(mList.get(i).getCommodityName());
        moViewHolder.tv2.setText(mList.get(i).getPrice()+"");
        moViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
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
    static class MoViewHolder extends RecyclerView.ViewHolder{
        SimpleDraweeView moSimpleView;
        TextView tv1;
        TextView tv2;
        public MoViewHolder(@NonNull View itemView) {
            super(itemView);
            moSimpleView=itemView.findViewById(R.id.moSimpleView);
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
