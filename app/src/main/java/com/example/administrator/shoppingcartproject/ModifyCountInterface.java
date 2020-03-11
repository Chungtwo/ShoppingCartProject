package com.example.administrator.shoppingcartproject;

import android.view.View;

/**
 * Created by Administrator on 2020/3/7.
 */

public interface ModifyCountInterface {
    void doIncrease(int position,View showCountView,boolean isChecked); //增加
    void doDecrease(int position,View showCountView,boolean isChecked);//减少
    void childDelete(int position);//删除
}
