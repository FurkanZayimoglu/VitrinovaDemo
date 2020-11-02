package com.example.vitrinovademo.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.vitrinovademo.R;
import com.example.vitrinovademo.model.Shops;
import com.example.vitrinovademo.ui.MainActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ChooseEditorAdapter extends RecyclerView.Adapter<ChooseEditorAdapter.ChooseEditorViewHolder> {

    private List<Shops> shops;
    private MainActivity mainActivity;

    public List<Shops> getShops() {
        return shops;
    }

    public ChooseEditorAdapter(List<Shops> shops, MainActivity activity) {
       this.mainActivity = activity;
        this.shops = shops;
    }

    @NonNull
    @Override
    public ChooseEditorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_choose_editor,parent,false);
        return new ChooseEditorViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChooseEditorViewHolder holder, int position) {


        holder.tvName.setText(shops.get(position).getName());
        holder.tvDefinition.setText(shops.get(position).getDefinition());
        Picasso.get().load(shops.get(position).getPopular_products().get(0).getImages().get(0).getThumbnail().getUrl()).into(holder.ivFirst);
        Picasso.get().load(shops.get(position).getPopular_products().get(1).getImages().get(0).getThumbnail().getUrl()).into(holder.ivSecond);
        Picasso.get().load(shops.get(position).getPopular_products().get(2).getImages().get(0).getThumbnail().getUrl()).into(holder.ivThird);

        Glide.with(mainActivity.getApplicationContext()).load(shops.get(position).getLogo().getThumbnail().getUrl())
                .apply(RequestOptions.circleCropTransform()).into(holder.ivLogo);
    }


    @Override
    public int getItemCount() {
        return shops.size();
    }

    static class ChooseEditorViewHolder extends RecyclerView.ViewHolder {


        ImageView ivLogo, ivFirst, ivSecond, ivThird;
        TextView tvName, tvDefinition;
        Button buttonVitrin;

        public ChooseEditorViewHolder(@NonNull View itemView) {
            super(itemView);

            ivLogo = itemView.findViewById(R.id.ivChooseLogo);
            ivFirst = itemView.findViewById(R.id.ivFirst);
            ivSecond = itemView.findViewById(R.id.ivSecond);
            ivThird = itemView.findViewById(R.id.ivThird);
            tvName = itemView.findViewById(R.id.tvChooseName);
            tvDefinition = itemView.findViewById(R.id.tvChooseDefination);
            buttonVitrin = itemView.findViewById(R.id.buttonVitrin);


        }
    }
}
