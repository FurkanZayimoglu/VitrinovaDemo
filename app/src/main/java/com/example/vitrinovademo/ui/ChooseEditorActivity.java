package com.example.vitrinovademo.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.View;

import com.example.vitrinovademo.adapter.ChooseEditorActivityAdapter;
import com.example.vitrinovademo.databinding.ActivityChooseEditorBinding;
import com.example.vitrinovademo.model.Collection;
import com.example.vitrinovademo.model.Shops;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ChooseEditorActivity extends AppCompatActivity {

    ActivityChooseEditorBinding activityChooseEditorBinding;
    private ArrayList<Shops> shops;
    private ChooseEditorActivityAdapter chooseEditorActivityAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityChooseEditorBinding = ActivityChooseEditorBinding.inflate(getLayoutInflater());
        setContentView(activityChooseEditorBinding.getRoot());

        shops = getIntent().getParcelableArrayListExtra("chooseEditor");
        assert shops != null;
        Collections.reverse(shops);
        setChooseEditorActivityAdapter(shops);

        activityChooseEditorBinding.backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void setChooseEditorActivityAdapter(ArrayList<Shops> shops) {
        chooseEditorActivityAdapter = new ChooseEditorActivityAdapter(shops, this);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        activityChooseEditorBinding.rvChooseEditorActivity.setLayoutManager(layoutManager);
        activityChooseEditorBinding.rvChooseEditorActivity.setAdapter(chooseEditorActivityAdapter);
        chooseEditorActivityAdapter.notifyDataSetChanged();

    }
}
