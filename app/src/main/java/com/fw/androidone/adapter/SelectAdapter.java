package com.fw.androidone.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.fw.androidone.R;
import com.fw.androidone.entity.Select;

import java.util.ArrayList;
import java.util.List;

/**
 * description :
 * author : apple
 * date : 2021/3/8 13:44
 */
public class SelectAdapter extends RecyclerView.Adapter<SelectAdapter.ViewHolder> {

    private ItemClickListener itemClickListener;

    private List<Select> list = new ArrayList<>();

    public SelectAdapter(List<Select> list) {
        this.list = list;
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_select, parent, false);
        ViewHolder holder = new ViewHolder(view);
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                itemClickListener.onClick(position);
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Select select = list.get(position);
        holder.selectType.setText(select.getType());
        holder.selectText.setText(select.getText());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView selectType;
        TextView selectText;
        View view;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView;
            selectType = itemView.findViewById(R.id.select_type);
            selectText = itemView.findViewById(R.id.select_text);
        }
    }

    public interface ItemClickListener {
        void onClick(int position);
    }
}
