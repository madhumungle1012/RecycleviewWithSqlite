package com.example.recycleview_with_sqlite;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
//import de.hdodenhof.circleimageview.CircleImageView;


public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.MyViewHolder> {

    private ArrayList<ProductList> feedsListnull;
    private Context context;
    private LayoutInflater inflater;

    public ProductAdapter(Context context, ArrayList<ProductList> feedsListnull) {
        this.context = context;
        this.feedsListnull = feedsListnull;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        //Instantiates a layout XML file into its corresponding View objects.

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_list_item, null);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        ProductList feeds = feedsListnull.get(position);

        holder.name.setText(feeds.getProduct_Name());
        holder.packsizes.setText(feeds.getProduct_Id());
        Picasso.with(context).load("http://tl.devmantech.com/" + feeds.getProduct_Image_URL().replace(" ", "%20")).into(holder.productimageview);

    }

    @Override
    public int getItemCount() {
        return feedsListnull.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView name, quantity;

        TextView packsizes;
        ImageView productimageview;


        public MyViewHolder(View itemView) {
            super(itemView);

            quantity = (TextView) itemView.findViewById(R.id.quantity);
            productimageview = (ImageView) itemView.findViewById(R.id.productimageview);
            name = (TextView) itemView.findViewById(R.id.name);
            packsizes = (TextView) itemView.findViewById(R.id.packsizes);
        }
    }
}
