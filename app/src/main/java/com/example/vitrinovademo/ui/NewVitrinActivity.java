package com.example.vitrinovademo.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.View;

import com.example.vitrinovademo.adapter.CollectionAdapter;
import com.example.vitrinovademo.adapter.NewVitrinActivityAdapter;
import com.example.vitrinovademo.databinding.ActivityNewVitrinBinding;
import com.example.vitrinovademo.model.Shops;

import java.util.ArrayList;

public class NewVitrinActivity extends AppCompatActivity {

    ActivityNewVitrinBinding newVitrinBinding;
    private NewVitrinActivityAdapter newVitrinActivityAdapter;
    private ArrayList<Shops> newVitrin;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        newVitrinBinding = ActivityNewVitrinBinding.inflate(getLayoutInflater());
        setContentView(newVitrinBinding.getRoot());

        newVitrin = getIntent().getParcelableArrayListExtra("newVitrin");
        setNewActivityAdapter(newVitrin);

         newVitrinBinding.backButton.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 finish();
             }
         });

    }

    private void setNewActivityAdapter(ArrayList<Shops> newVitrin) {
        newVitrinActivityAdapter = new NewVitrinActivityAdapter(newVitrin,this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        newVitrinBinding.rvCollectionDetails.setLayoutManager(layoutManager);
        newVitrinBinding.rvCollectionDetails.setAdapter(newVitrinActivityAdapter);
        newVitrinActivityAdapter.notifyDataSetChanged();
    }
}
