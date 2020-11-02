package com.example.vitrinovademo.adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.vitrinovademo.R;
import com.example.vitrinovademo.model.Featured;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class SlidingAdapter extends PagerAdapter {

    private ArrayList<Featured> featureds;

    public SlidingAdapter(ArrayList<Featured> featureds){
        this.featureds = featureds;
    }


    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.layout_sliding,container,false);

        ImageView imageView = view.findViewById(R.id.ivSliding);
        TextView title = view.findViewById(R.id.tvTitle);
        TextView sub_title = view.findViewById(R.id.tvSubTitle);
        title.setText(featureds.get(position).getTitle().toUpperCase());
        sub_title.setText(featureds.get(position).getSub_title().toUpperCase());
        Picasso.get().load(featureds.get(position).getCover().getUrl()).into(imageView);
        container.addView(view,0);
        return  view;

    }


    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return featureds.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return object == view ;
    }
}
