package com.example.week3work;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.widget.Toast;

import com.example.week3work.shop.adapter.HotShopAdapter;
import com.example.week3work.shop.adapter.MoShopAdapter;
import com.example.week3work.shop.adapter.PinShopAdapter;
import com.example.week3work.shop.bean.DetailBean;
import com.example.week3work.shop.bean.EventBean;
import com.example.week3work.shop.bean.ShopShowBean;
import com.example.week3work.utils.Apis;
import com.example.week3work.utils.presenter.IPresenterImpl;
import com.example.week3work.utils.view.IView;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements IView {

    @BindView(R.id.hotRecycle)
    RecyclerView hotRecycle;
    @BindView(R.id.moRecycle)
    RecyclerView moRecycle;
    @BindView(R.id.pinRecycle)
    RecyclerView pinRecycle;
    private HotShopAdapter hotShopAdapter;
    private MoShopAdapter moShopAdapter;
    private PinShopAdapter pinShopAdapter;
    private IPresenterImpl iPresenter;
    private ShopShowBean shopShowBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        iPresenter=new IPresenterImpl(this);

        hotRecycle.setLayoutManager(new LinearLayoutManager(MainActivity.this,LinearLayoutManager.HORIZONTAL,false));
        hotShopAdapter=new HotShopAdapter(this);
        hotRecycle.setAdapter(hotShopAdapter);
        hotShopAdapter.onClickListener(new HotShopAdapter.onClickListener() {
            @Override
            public void setonclicklisener(int index) {
                int commodityId = shopShowBean.getResult().getRxxp().getCommodityList().get(index).getCommodityId();
                goods(commodityId);
            }
        });
        moRecycle.setLayoutManager(new LinearLayoutManager(MainActivity.this,LinearLayoutManager.VERTICAL,false));
        moShopAdapter=new MoShopAdapter(this);
        moRecycle.setAdapter(moShopAdapter);
        moShopAdapter.onClickListener(new HotShopAdapter.onClickListener() {
            @Override
            public void setonclicklisener(int index) {
                int commodityId = shopShowBean.getResult().getMlss().getCommodityList().get(index).getCommodityId();
                goods(commodityId);
            }
        });
        pinRecycle.setLayoutManager(new LinearLayoutManager(MainActivity.this,LinearLayoutManager.VERTICAL,false));
        pinShopAdapter=new PinShopAdapter(this);
        pinRecycle.setAdapter(pinShopAdapter);
        pinShopAdapter.onClickListener(new HotShopAdapter.onClickListener() {
            @Override
            public void setonclicklisener(int index) {
                int commodityId = shopShowBean.getResult().getPzsh().getCommodityList().get(index).getCommodityId();
                goods(commodityId);
            }
        });
        initData();
    }

    private void goods(int id) {
        iPresenter.startRequestGet(Apis.DETAIL_SHOP+"?commodityId="+id,null, DetailBean.class);
    }

    private void initData() {
        iPresenter.startRequestGet(Apis.URL_SHOP_LIST,null, ShopShowBean.class);
    }

    @Override
    public void getDataSuccess(Object data) {
        if (data instanceof ShopShowBean){
            shopShowBean = (ShopShowBean) data;
            hotShopAdapter.setmList(shopShowBean.getResult().getRxxp().getCommodityList());
            moShopAdapter.setmList(shopShowBean.getResult().getMlss().getCommodityList());
            pinShopAdapter.setmList(shopShowBean.getResult().getPzsh().getCommodityList());

        }else if (data instanceof DetailBean){
            EventBus.getDefault().postSticky(new EventBean("goods",data));
            startActivity(new Intent(MainActivity.this,DetailActivity.class));
        }
    }

    @Override
    public void getDataFail(String error) {
        Toast.makeText(MainActivity.this,error,Toast.LENGTH_LONG).show();
    }
}
