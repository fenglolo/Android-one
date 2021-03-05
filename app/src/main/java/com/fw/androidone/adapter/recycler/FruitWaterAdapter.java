package com.fw.androidone.adapter.recycler;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.fw.androidone.R;
import com.fw.androidone.entity.recycler.Fruit;

import java.util.ArrayList;
import java.util.List;

/**
 * description :
 * author : apple
 * date : 2021/3/4 16:52
 */
public class FruitWaterAdapter extends RecyclerView.Adapter<FruitWaterAdapter.ViewHolder> {

    List<Fruit> list = new ArrayList<>();

    public FruitWaterAdapter(List<Fruit> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_fruit_water, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        viewHolder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = viewHolder.getAdapterPosition();
                Fruit fruit = list.get(position);
                Toast.makeText(v.getContext(), "点击item-->" + fruit.getFruitName(), Toast.LENGTH_SHORT).show();
            }
        });
        viewHolder.fruitImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = viewHolder.getAdapterPosition();
                Fruit fruit = list.get(position);
                Toast.makeText(v.getContext(), "点击img-->" + fruit.getFruitResource(), Toast.LENGTH_SHORT).show();
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Fruit fruit = list.get(position);
        holder.fruitImg.setImageResource(fruit.getFruitResource());
        holder.fruitText.setText(fruit.getFruitName());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        View view;
        ImageView fruitImg;
        TextView fruitText;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView;
            fruitImg = itemView.findViewById(R.id.fruit_image);
            fruitText = itemView.findViewById(R.id.fruit_text);
        }
    }
}
