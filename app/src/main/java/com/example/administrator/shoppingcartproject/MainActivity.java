package com.example.administrator.shoppingcartproject;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Looper;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,CheckInterface,ModifyCountInterface{

    private List<GoodsInfo> goodsInfoList=new ArrayList<>();
    String TAG="ShoppingCart";
    private GoodsAdapter goodsAdapter;
    private CheckBox ckAll;
    private TextView calc_all;
    private Button btn_count;
    private ModifyCountInterface modifyCountInterface;
    private int totalPrice=0;//购买商品总价
    private int totalCount=0;//购买商品总数
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_count=findViewById(R.id.btn_count);
        calc_all=findViewById(R.id.calc_all);
        ckAll=findViewById(R.id.check_all);
        ckAll.setOnClickListener(this);
        initGoodsInfo();
        RecyclerView recyclerView=findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        goodsAdapter=new GoodsAdapter(goodsInfoList);
        recyclerView.setAdapter(goodsAdapter);
        goodsAdapter.setModifyCountInterface(this);
        goodsAdapter.setCheckInterface(this);
    }

    private void initGoodsInfo(){

        for(int i=0;i<1;i++){
            GoodsInfo goodsInfo1=new GoodsInfo(false,R.drawable.img01,
                    "荣耀20青春版 AMOLED屏幕指纹 4000mAh大电池 20W快充 4800万 手机 4GB+64GB 冰岛幻境",999,1);
            goodsInfoList.add(goodsInfo1);
            GoodsInfo goodsInfo2=new GoodsInfo(false,R.drawable.img02,
                    "华为 HUAWEI Mate 30 5G 麒麟990 4000万超感光徕卡影像双超级快充8GB+128GB亮黑色5G全网通游戏手机",4499,1);
            goodsInfoList.add(goodsInfo2);
            GoodsInfo goodsInfo3=new GoodsInfo(false,R.drawable.img03,
                    "小米10 Pro 双模5G 骁龙865 1亿像素8K电影相机 50倍变焦 50W快充 12GB+512GB 珍珠白 拍照智能新品游戏手机",5999,1);
            goodsInfoList.add(goodsInfo3);
            GoodsInfo goodsInfo4=new GoodsInfo(false,R.drawable.img04,
                    "Apple iPhone XS Max (A2104) 256GB 银色 移动联通电信4G手机 双卡双待",7299,1);
            goodsInfoList.add(goodsInfo4);
        }
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.check_all:
             //   Toast.makeText(MainActivity.this,"点击全选",Toast.LENGTH_SHORT).show();
              if (goodsInfoList.size()!=0){
                  if(ckAll.isChecked()){
                      for(int i=0;i<goodsInfoList.size();i++){
                          goodsInfoList.get(i).setSelect_state(true);
                      }
                      goodsAdapter.notifyDataSetChanged();

                  }else {
                      for (int i=0;i<goodsInfoList.size();i++){
                          goodsInfoList.get(i).setSelect_state(false);

                      }

                      goodsAdapter.notifyDataSetChanged();
                  }
              }
                statistics();
                break;
            default:
                break;
        }
    }

    //统计
    private void statistics() {
        totalCount=0;
        totalPrice=0;
        for(int i=0;i<goodsInfoList.size();i++){
            GoodsInfo goodsInfo=goodsInfoList.get(i);
            if(goodsInfo.isSelect_state()){
                totalCount+=goodsInfo.getNum();
                totalPrice+=goodsInfo.getPrice() * goodsInfo.getNum();
            }
        }
        calc_all.setText("合计：¥"+totalPrice);
        btn_count.setText("去结算（"+totalCount+"）");

    }

    @Override
    public void checkGroup(int position, boolean isChecked) {
        goodsInfoList.get(position).setSelect_state(isChecked);
        if (isAllCheck())
            ckAll.setChecked(true);
        else
            ckAll.setChecked(false);
        goodsAdapter.notifyDataSetChanged();
        statistics();
    }

    //遍历集合
    private boolean isAllCheck() {
        for (GoodsInfo goodsInfo:goodsInfoList){
            if(!goodsInfo.isSelect_state())
                return false;
        }
        return true;
    }

    @Override
    public void doIncrease(int position, View showCountView, boolean isChecked) {
        GoodsInfo goodsInfo=goodsInfoList.get(position);
        int goodsNum=goodsInfo.getNum();
        goodsNum++;
        goodsInfo.setNum(goodsNum);
        ((TextView)showCountView).setText(goodsNum+"");
        goodsAdapter.notifyDataSetChanged();
        statistics();
    }

    @Override
    public void doDecrease(int position, View showCountView, boolean isChecked) {
        GoodsInfo goodsInfo=goodsInfoList.get(position);
        int currentCount=goodsInfo.getNum();

        currentCount--;
        goodsInfo.setNum(currentCount);
        ((TextView)showCountView).setText(currentCount+"");
        goodsAdapter.notifyDataSetChanged();
        statistics();
    }

    @Override
    public void childDelete(int position) {
        goodsInfoList.remove(position);
        goodsAdapter.notifyDataSetChanged();
        statistics();
    }



}
