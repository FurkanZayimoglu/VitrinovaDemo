package com.example.vitrinovademo.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;

import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;

import com.example.vitrinovademo.R;
import com.example.vitrinovademo.adapter.ProductAdapter;
import com.example.vitrinovademo.databinding.ActivityAllProductBinding;
import com.example.vitrinovademo.model.Product;

import java.util.ArrayList;

public class AllProductActivity extends AppCompatActivity {

    private ArrayList<Product> allProducts;
    private ProductAdapter productAdapter;
    ActivityAllProductBinding activityAllProductBinding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityAllProductBinding = ActivityAllProductBinding.inflate(getLayoutInflater());
        setContentView(activityAllProductBinding.getRoot());

        allProducts = getIntent().getParcelableArrayListExtra("all");
        setProductAdapter(allProducts);

        activityAllProductBinding.backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }

    public void setProductAdapter(ArrayList<Product> products) {
        productAdapter = new ProductAdapter(products);
        activityAllProductBinding.rvAllProduct.setLayoutManager(new GridLayoutManager(this, 2));
        activityAllProductBinding.rvAllProduct.setAdapter(productAdapter);
        productAdapter.notifyDataSetChanged();


    }
}
