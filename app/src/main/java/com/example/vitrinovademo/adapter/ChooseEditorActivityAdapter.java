package com.example.vitrinovademo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.vitrinovademo.R;
import com.example.vitrinovademo.model.Shops;

import java.util.List;

public class ChooseEditorActivityAdapter extends RecyclerView.Adapter<ChooseEditorActivityAdapter.ChooseEditorActivityiewHolder> {

    private List<Shops> shops;
    private Context context;

    public ChooseEditorActivityAdapter(List<Shops> shops, Context context) {
        this.shops = shops;
        this.context = context;
    }

    @NonNull
    @Override
    public ChooseEditorActivityiewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_choose_activity_adapter, parent, false);
        return new ChooseEditorActivityiewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChooseEditorActivityiewHolder holder, int position) {
        Glide.with(context).load(shops.get(position)
                .getCover().getUrl()).into(holder.ivChooseCover);

        Glide.with(context).load(shops.get(position).getLogo().getThumbnail().getUrl())
                .apply(RequestOptions.circleCropTransform()).into(holder.ivChooseLogo);

        holder.name.setText(shops.get(position).getName());
        holder.definition.setText(shops.get(position).getDefinition());
        holder.productCount.setText(String.valueOf(shops.get(position).getProduct_count()));

    }

    @Override
    public int getItemCount() {
        return shops.size();
    }

    static class ChooseEditorActivityiewHolder extends RecyclerView.ViewHolder {

        ImageView ivChooseCover, ivChooseLogo;
        TextView name, definition, productCount;

        ChooseEditorActivityiewHolder(@NonNull View itemView) {
            super(itemView);

            ivChooseCover = itemView.findViewById(R.id.ivChooseCover);
            ivChooseLogo = itemView.findViewById(R.id.ivLogoActivity);
            name = itemView.findViewById(R.id.tvNameActivity);
            definition = itemView.findViewById(R.id.tvDefinitionActivity);
            productCount = itemView.findViewById(R.id.tvProductCount);
        }
    }
}
