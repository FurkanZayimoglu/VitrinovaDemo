package com.example.vitrinovademo.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vitrinovademo.R;
import com.example.vitrinovademo.model.Collection;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CollectionAdapter extends RecyclerView.Adapter<CollectionAdapter.CollectionViewHolder> {

    private List<Collection> collections;

    public CollectionAdapter(List<Collection> collections) {
        this.collections = collections;
    }

    @NonNull
    @Override
    public CollectionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_collection,parent,false);
        return new CollectionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CollectionViewHolder holder, int position) {

        holder.name.setText(collections.get(position).getTitle());
        holder.defination.setText(collections.get(position).getDefinition());
        Picasso.get().load(collections.get(position).getCover().getMedium().getUrl()).into(holder.ivCollection);

    }

    @Override
    public int getItemCount() {
        return collections.size();
    }




    public static class CollectionViewHolder extends RecyclerView.ViewHolder {

        TextView name, defination;
        ImageView ivCollection;

        public CollectionViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.tvCollectionName);
            defination = itemView.findViewById(R.id.tvCollectionDefination);
            ivCollection = itemView.findViewById(R.id.ivCollection);

        }
    }
}
