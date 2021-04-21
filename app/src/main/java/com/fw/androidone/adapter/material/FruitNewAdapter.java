package com.fw.androidone.adapter.material;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.fw.androidone.R;
import com.fw.androidone.activity.material.MaterialFruitActivity;
import com.fw.androidone.entity.material.Fruits;

import java.util.ArrayList;
import java.util.List;

/**
 * description :
 * author : apple
 * date : 2021/3/4 16:52
 */
public class FruitNewAdapter extends RecyclerView.Adapter<FruitNewAdapter.ViewHolder> {

    private Context mContext;
    private List<Fruits> list = new ArrayList<>();

    public FruitNewAdapter(List<Fruits> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (mContext == null) {
            mContext = parent.getContext();
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_fruit_material, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        viewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = viewHolder.getAdapterPosition();
                Fruits fruits = list.get(position);
                Intent intent = new Intent(mContext, MaterialFruitActivity.class);
                intent.putExtra(MaterialFruitActivity.FRUIT_NAME, fruits.getName());
                intent.putExtra(MaterialFruitActivity.FRUIT_IMAGE_ID, fruits.getImgId());
                mContext.startActivity(intent);
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Fruits fruit = list.get(position);
        holder.fruitName.setText(fruit.getName());
        Glide.with(mContext).load(fruit.getImgId()).into(holder.fruitImg);//Glide加载图片
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        CardView cardView;
        ImageView fruitImg;
        TextView fruitName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.card_view);
            fruitImg = itemView.findViewById(R.id.fruit_image);
            fruitName = itemView.findViewById(R.id.fruit_name);
        }
    }
}
