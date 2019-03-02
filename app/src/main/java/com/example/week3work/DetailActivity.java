package com.example.week3work;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.week3work.shop.bean.DetailBean;
import com.example.week3work.shop.bean.EventBean;
import com.example.week3work.shop.bean.ShopCarBean;
import com.example.week3work.shop.bean.ShopShowBean;
import com.example.week3work.shop.bean.SyncShopBean;
import com.example.week3work.utils.Apis;
import com.example.week3work.utils.presenter.IPresenterImpl;
import com.example.week3work.utils.view.IView;
import com.stx.xhb.xbanner.XBanner;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailActivity extends AppCompatActivity implements IView {


    @BindView(R.id.syncShop)
    Button syncShop;
    @BindView(R.id.contents)
    Banner contents;
    @BindView(R.id.tv1)
    TextView tv1;
    private DetailBean goodsBean;
    private int commodityId;
    private IPresenterImpl iPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        iPresenter=new IPresenterImpl(this);
        syncShop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectorShopCar();
            }
        });
    }

    private void selectorShopCar() {
        iPresenter.startRequestGet(Apis.FIND_SHOPPING_CART_GET, null, ShopCarBean.class);
    }

    private void initData() {
        Map<String,String> params=new HashMap<>();
        iPresenter.startRequestPut(Apis.SYNC_SHOPCAR,params, ShopShowBean.class);
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Subscribe(threadMode = ThreadMode.POSTING, sticky = true)
    public void onEvent(EventBean evBean) {
        if (evBean.getName().equals("goods")) {
            goodsBean = (DetailBean) evBean.getClazz();
            commodityId = goodsBean.getResult().getCommodityId();
            loadData();
        }
    }

    private void loadData() {
        String details = goodsBean.getResult().getDetails();
        String picture = goodsBean.getResult().getPicture();
        String[] split = picture.split(",");
        List<String> list = Arrays.asList(split);
        contents.setImageLoader(new GlideImageLoader());
        contents.setImages(list);
        contents.start();

    }

    private class GlideImageLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            Glide.with(context).load(path).into(imageView);
        }

    }
    @Override
    public void getDataSuccess(Object data) {
        if (data instanceof ShopCarBean) {
            ShopCarBean shopCarBean = (ShopCarBean) data;
            if (shopCarBean.getMessage().equals("查询成功")) {

                
            }
        }
    }

    @Override
    public void getDataFail(String error) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
