package com.example.ravinderreddy.pagination;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Ravinder Reddy on 21-07-2017.
 */

public class CustomAdapter1 extends BaseAdapter {

    List<Model> modelList = new LinkedList<>();
    Context mContext;
    LayoutInflater mInflater;

    public CustomAdapter1(Context mContext, List<Model> modelList) {
        this.mContext = mContext;
        this.modelList = modelList;

    }

    @Override
    public int getCount() {
        return modelList.size();
    }

    @Override
    public Object getItem(int position) {
        return modelList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
         ViewHolder viewHolder = new ViewHolder();
        if (mInflater == null) {
            mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.custom_layout, parent,false);
            viewHolder.productid = (TextView) convertView.findViewById(R.id.product_id);
            viewHolder.productName = (TextView) convertView.findViewById(R.id.product_name);
            viewHolder.productPrice = (TextView) convertView.findViewById(R.id.product_price);
            convertView.setTag(viewHolder);
        } else {
             viewHolder = (ViewHolder) convertView.getTag();
        }

        Model model = modelList.get(position);
        viewHolder.productid.setText(model.getProduct_id());
        viewHolder.productName.setText(model.getProductName());
        viewHolder.productPrice.setText(model.getProductPrice());


        return convertView;
    }

    public class ViewHolder {
        TextView productid, productName, productPrice;

    }

}
