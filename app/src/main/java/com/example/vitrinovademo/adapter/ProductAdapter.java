package com.example.vitrinovademo.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vitrinovademo.R;
import com.example.vitrinovademo.model.Product;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    private List<Product> products;

    public ProductAdapter( List<Product> products) {
        this.products = products;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_products, parent,false);
        return new ProductViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {


        Picasso.get().load(products.get(position).getImages().get(0).getThumbnail().getUrl()).into(holder.logo);
        holder.title.setText(products.get(position).getTitle());

        holder.price.setText(String.valueOf(products.get(position).getPrice()) + "TL");
        holder.oldPrice.setText((products.get(position).getOld_price()) + "TL");
        holder.name.setText(products.get(position).getShop().getName());

        if (products.get(position).getOld_price() != 0) {
            holder.oldPrice.setVisibility(View.VISIBLE);
            holder.line.setVisibility(View.VISIBLE);
        }else{
            holder.oldPrice.setVisibility(View.GONE);
            holder.line.setVisibility(View.GONE);
        }

    }

    @Override
    public int getItemCount() {
        return 5;   // normalde listenin size ı da 5 geliyor bana lakin ben normalde an sayfada 5 gosterilcek şeklinde yapıyorum. tümü ne tıklayınca
                    // tüm sıze ı gostermil olacağım lakin bana her türlü size 5 geliyor api den.....
    }

   static class ProductViewHolder extends RecyclerView.ViewHolder {

        ImageView logo;
        TextView title, name, price, oldPrice,line;


         ProductViewHolder(@NonNull View itemView) {
            super(itemView);

            logo = itemView.findViewById(R.id.ivLogo);
            title = itemView.findViewById(R.id.tvTitle);
            name = itemView.findViewById(R.id.tvName);
            price = itemView.findViewById(R.id.tvPrice);
            oldPrice = itemView.findViewById(R.id.tvOldPrice);
            line = itemView.findViewById(R.id.line);
        }
    }
}
