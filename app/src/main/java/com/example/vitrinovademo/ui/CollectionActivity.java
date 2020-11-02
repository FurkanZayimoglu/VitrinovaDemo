package com.example.vitrinovademo.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.example.vitrinovademo.R;
import com.example.vitrinovademo.adapter.CollectionAdapter;
import com.example.vitrinovademo.adapter.ProductAdapter;
import com.example.vitrinovademo.databinding.ActivityCollectionBinding;
import com.example.vitrinovademo.model.Collection;

import java.util.ArrayList;

public class CollectionActivity extends AppCompatActivity {

    private ArrayList<Collection> collections;
    private CollectionAdapter collectionAdapter;
    ActivityCollectionBinding activityCollectionBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityCollectionBinding = ActivityCollectionBinding.inflate(getLayoutInflater());
        setContentView(activityCollectionBinding.getRoot());

        collections = getIntent().getParcelableArrayListExtra("collection");
        setCollectionaAdapter(collections);

        activityCollectionBinding.backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void setCollectionaAdapter(ArrayList<Collection> collections) {
        collectionAdapter = new CollectionAdapter(collections);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        activityCollectionBinding.rvCollectionDetails.setLayoutManager(layoutManager);
        activityCollectionBinding.rvCollectionDetails.setAdapter(collectionAdapter);
        collectionAdapter.notifyDataSetChanged();
    }
}
