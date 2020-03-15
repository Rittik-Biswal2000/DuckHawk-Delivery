package com.duckhawk.duckhawk_delivery.ui.slideshow;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.duckhawk.duckhawk_delivery.R;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    Context mContext;
    List<Slide> mSlide;

    public RecyclerViewAdapter(Context mContext, List<Slide> mSlide) {
        this.mContext = mContext;
        this.mSlide = mSlide;
    }

    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v;
        v = LayoutInflater.from(mContext).inflate(R.layout.item_order,parent,false);
        MyViewHolder vHolder = new MyViewHolder(v);

        return vHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.tv_buyer.setText(mSlide.get(position).getBuyer());
        holder.tv_location.setText(mSlide.get(position).getAddress());
    }

    @Override
    public int getItemCount() {
        return mSlide.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        private TextView tv_buyer;
        private TextView tv_location;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_buyer = (TextView) itemView.findViewById(R.id.order_buyer);
            tv_location = (TextView) itemView.findViewById(R.id.order_location);
        }
    }
}
