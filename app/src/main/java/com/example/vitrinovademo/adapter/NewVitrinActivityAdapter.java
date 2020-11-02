package com.example.vitrinovademo.adapter;

import android.content.Context;
import android.graphics.Color;
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
import com.example.vitrinovademo.ui.NewVitrinActivity;

import java.util.List;

public class NewVitrinActivityAdapter extends RecyclerView.Adapter<NewVitrinActivityAdapter.NewVitrinViewHolder> {

    private List<Shops> shops;
    private Context context;

    public NewVitrinActivityAdapter(List<Shops> shops, Context context) {
        this.shops = shops;
        this.context = context;
    }

    @NonNull
    @Override
    public NewVitrinViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_new_vitrin_activity, parent, false);
        return new NewVitrinViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewVitrinViewHolder holder, int position) {
        if (shops.get(position).getCover() !=null){
            Glide.with(context).load(shops.get(position)
                    .getCover().getUrl()).into(holder.ivNewCover);
        }

        if (shops.get(position).getLogo() != null ){
            Glide.with(context).load(shops.get(position).getLogo().getThumbnail().getUrl())
                    .apply(RequestOptions.circleCropTransform()).into(holder.ivNewLogo);
        }else{
            holder.ivNewLogo.setBackgroundColor(Color.parseColor("#FF4500"));
            holder.ivNewLogo.setBackground(context.getDrawable(R.drawable.circle));
            holder.tvLogo.setVisibility(View.VISIBLE);
            holder.tvLogo.setText(Character.toString(shops.get(position).getName().toUpperCase().charAt(0)));

        }

        holder.nameNew.setText(shops.get(position).getName());
        holder.definitionNew.setText(shops.get(position).getDefinition());
        holder.productCountNew.setText(String.valueOf(shops.get(position).getProduct_count()));
    }

    @Override
    public int getItemCount() {
        return shops.size();
    }

    static class NewVitrinViewHolder extends RecyclerView.ViewHolder {

        ImageView ivNewCover, ivNewLogo;
        TextView nameNew, definitionNew, productCountNew,tvLogo;

        NewVitrinViewHolder(@NonNull View itemView) {
            super(itemView);

            ivNewCover = itemView.findViewById(R.id.ivNewCoverActivity);
            ivNewLogo = itemView.findViewById(R.id.ivNewVitrinLogoActivity);
            nameNew = itemView.findViewById(R.id.tvNewNameActivity);
            definitionNew = itemView.findViewById(R.id.tvNewDefinitionActivity);
            productCountNew = itemView.findViewById(R.id.tvNewProductCountActivity);
            tvLogo = itemView.findViewById(R.id.tvLogoActivity);
        }
    }
}


