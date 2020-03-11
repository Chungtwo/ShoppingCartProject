package com.example.administrator.shoppingcartproject;

/**
 * Created by Administrator on 2020/3/7.
 */

public class GoodsInfo {


    private boolean select_state;
    private int num;
    private int goods_pic_id;
    private String goods_title;
    private int price;

    public GoodsInfo(boolean select_state, int goods_pic_id,String goods_title,int price,int num){
        this.select_state=select_state;
        this.goods_pic_id=goods_pic_id;
        this.goods_title=goods_title;
        this.price=price;
        this.num=num;
    }

    public boolean isSelect_state() {
        return select_state;
    }

    public void setSelect_state(boolean select_state) {
        this.select_state = select_state;
    }
    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }



    public int getGoods_pic_id() {
        return goods_pic_id;
    }


    public String getGoods_title() {
        return goods_title;
    }

    public int getPrice() {
        return price;
    }



}
