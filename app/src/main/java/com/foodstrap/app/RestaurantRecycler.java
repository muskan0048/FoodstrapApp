package com.foodstrap.app;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.List;

public class RestaurantRecycler extends RecyclerView.Adapter<RestaurantRecycler.ProductViewHolder> {

    private Context mCtx;
    private List<Restaurant> restaurantList,restaurantList1;
    public RestaurantRecycler(Context mCtx, List<Restaurant> restaurantList) {
        this.mCtx = mCtx;
        this.restaurantList = restaurantList;
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.layout_restau, null);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {
        final Restaurant restaurant = restaurantList.get(position);
        holder.textViewTitle.setText(restaurant.getRname().toString().trim());
        final String rid=restaurant.getRid().toString().trim();
        System.out.print("bbb"+restaurant.getRname().toString().trim());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(mCtx,MenuActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("rid", rid);
                mCtx.startActivity(intent);
            }
        });
    }

        @Override
        public int getItemCount () {
            return restaurantList.size();
        }

        public void setRestaurantList (List < Restaurant > restaurantList) {
            this.restaurantList = restaurantList;
            notifyDataSetChanged();
        }

        @Override
        public long getItemId ( int position){
            return position;
        }

        @Override
        public int getItemViewType ( int position){
            return position;
        }


    class ProductViewHolder extends RecyclerView.ViewHolder {

        TextView textViewTitle, textViewShortDesc, textViewRating, textViewPrice,tvtime,tvdelivery1, tag,textv;
        ImageView imageView, imageView1, tags, imgclosed;
        LinearLayout layout;

        public ProductViewHolder(View itemView) {
            super(itemView);
            textv = itemView.findViewById(R.id.textv);
            textViewTitle = itemView.findViewById(R.id.textViewrname);
            tvdelivery1 = itemView.findViewById(R.id.tvdelivery);
            textViewShortDesc = itemView.findViewById(R.id.textViewrloc);
            tvtime = itemView.findViewById(R.id.textView3);
            tag= itemView.findViewById(R.id.textView32);
            textViewRating = itemView.findViewById(R.id.textViewrcity);
            textViewPrice = itemView.findViewById(R.id.textViewrmin);
            imageView1 = itemView.findViewById(R.id.shine);
            tags = itemView.findViewById(R.id.imageView7);
            imageView = itemView.findViewById(R.id.imageViewres);
            imgclosed = itemView.findViewById(R.id.imageView11);
        }
    }
}
