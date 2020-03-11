package com.example.administrator.shoppingcartproject;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Message;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by Administrator on 2020/3/7.
 */

public class GoodsAdapter extends RecyclerView.Adapter<GoodsAdapter.ViewHolder> {

    String TAG = "ShoppingCart";
    private List<GoodsInfo> goodsInfoList;
    private CheckInterface checkInterface;
    private ModifyCountInterface modifyCountInterface;


    public GoodsAdapter(List<GoodsInfo> goodsInfoList) {
        this.goodsInfoList = goodsInfoList;
    }

    public void setModifyCountInterface(ModifyCountInterface modifyCountInterface) {
        this.modifyCountInterface = modifyCountInterface;

    }

    public void setCheckInterface(CheckInterface checkInterface) {
        this.checkInterface = checkInterface;
    }


    @Override
    public int getItemCount() {
        return goodsInfoList.size();
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    public Object getItem(int position) {
        return goodsInfoList.get(position);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView goodsImage;
        TextView goodsTitle;
        TextView goodsPrice;
        CheckBox goodsSelectedState;
        TextView goodsNum;
        Button btn_add;
        Button btn_reduce;


        public ViewHolder(View view) {
            super(view);
            goodsSelectedState = view.findViewById(R.id.check_chose);
            goodsImage = (ImageView) view.findViewById(R.id.image_show_pic);
            goodsTitle = view.findViewById(R.id.tv_title);
            goodsPrice = view.findViewById(R.id.tv_price);
            goodsNum = view.findViewById(R.id.tv_num);
            btn_add = view.findViewById(R.id.btn_add);
            btn_reduce = view.findViewById(R.id.btn_reduce);

        }


    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final GoodsInfo goodsInfo = goodsInfoList.get(position);

        holder.goodsSelectedState.setChecked(goodsInfo.isSelect_state());
        holder.goodsImage.setImageResource(goodsInfo.getGoods_pic_id());
        holder.goodsTitle.setText(goodsInfo.getGoods_title());
        holder.goodsPrice.setText("¥"+goodsInfo.getPrice());
        holder.goodsNum.setText(goodsInfo.getNum() + "");

        //选中单项
        holder.goodsSelectedState.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goodsInfo.setSelect_state(((CheckBox) view).isChecked());
                checkInterface.checkGroup(position, (((CheckBox) view).isChecked()));
            }
        });
        //增加数量
        holder.btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                modifyCountInterface.doIncrease(position, holder.goodsNum, holder.goodsSelectedState.isChecked());
            }
        });
        //减少数量
        holder.btn_reduce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                modifyCountInterface.doDecrease(position, holder.goodsNum, holder.goodsSelectedState.isChecked());
                int i = Integer.parseInt((holder.goodsNum.getText()).toString());
                if (i <= 0) {
            /*        AlertDialog alertDialog = new AlertDialog.Builder(context).create();
                    alertDialog.setTitle("操作提示");
                    alertDialog.setMessage("您确定要删除该商品吗？");
                    alertDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            return;
                        }
                    });
                    alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "狠心删除", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            //删除商品
                        }
                    });
                    alertDialog.show();*/
                    modifyCountInterface.childDelete(position);

                }
            }
        });
    }
}